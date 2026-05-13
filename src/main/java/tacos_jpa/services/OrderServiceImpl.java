package tacos_jpa.services;

import org.springframework.stereotype.Service;
import tacos_jpa.entities.TacoOrder;
import tacos_jpa.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public TacoOrder saveOrder(TacoOrder tacoOrder) {

        return orderRepository.save(tacoOrder);
    }
}