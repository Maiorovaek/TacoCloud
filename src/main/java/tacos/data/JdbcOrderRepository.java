package tacos.data;


import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tacos.TacoOrder;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public abstract class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        //1. создание объекта PreparedStatementCreatorFactory, описывающего
        // запрос insert вместе с типами параметров запроса.
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into TACO_ORDER "
                        + "(delivery_name, delivery_street, delivery_city, "
                        + "delivery_state, delivery_zip, cc_number, "
                        + "cc_expiration, cc_cvv, placed_at) "
                        + "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        //2 Поскольку позднее нам понадобится идентификатор сохраненного заказа
        pscf.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());
        //3. pscf используем  для создания объекта PreparedStatementCreator,
        // передавая значения из объекта TacoOrder, которые требуется сохранить.
        // Последний аргумент в вызове PreparedStatementCreator – это дата создания заказа,
        // которую также нужно будет установить в самом объекте TacoOrder,
        // чтобы возвращаемый экземпляр TacoOrder содержал эту информацию.
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        new Timestamp(order.getPlacedAt().getTime())
                        )
                );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        //4.сохраняем заказ
        jdbcOperations.update(psc, keyHolder);
        // 5  в keyHolder будет храниться значение поля id, назначенное базой данных,
        //   которое затем следует скопировать в свойство id объекта TacoOrder.
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);
        //6 после сохранения заказа,  также сохраняем объекты Taco,
        // связанные с заказом.
        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return order;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, TACO_ORDER, taco_order_key) " + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId,
                        orderKey));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        //для создания записи в таблице Ingredient_Ref,
        // чтобы связать запись в таблице Taco с записью в таблице Ingredient
        saveIngredientRefs(tacoId, taco.getIngredients());
        return tacoId;
    }


    private void saveIngredientRefs(
            long tacoId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), tacoId, key++);
        }
    }
}
