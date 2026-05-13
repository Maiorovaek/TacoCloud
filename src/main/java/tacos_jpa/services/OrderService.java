package tacos_jpa.services;

import tacos_jpa.entities.TacoOrder;

public interface OrderService {
    TacoOrder saveOrder(TacoOrder tacoOrder);
}
