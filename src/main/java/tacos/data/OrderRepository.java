package tacos.data;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long>
{
    //   CRUD (Create, Read, Up- date, Delete – создать, прочитать, изменить, удалить)
    TacoOrder save(TacoOrder order);

    //Методы репозитория формируются из
// глагола, необязательного подлежащего, слова By и предиката.
    //   List<TacoOrder> findByDeliveryZip(String deliveryZip);
//find (найти), read (прочитать) и get (получить) как синонимы, count - чтобы вернул целое число, подсчитал
//    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
    //           String deliveryZip, Date startDate, Date endDate);


//List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);
    //@Query("Order o where o.deliveryCity=’Seattle’")
    // List<TacoOrder> readOrdersDeliveredInSeattle();
}

