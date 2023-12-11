package service;

import dao.ItemDao;
import dao.OrderDao;
import dto.OrderDto;

import java.util.List;

public class OrderService {
    private static final OrderService INSTANCE = new OrderService();
    private OrderService() {};
    public static OrderService getInstance(){
        return INSTANCE;
    }
    private final OrderDao orderDao = OrderDao.getInstance();
    private final ItemDao itemDao = ItemDao.getInstance();

    public List<OrderDto> findAll() {
        return orderDao.findAll().stream().map(order ->
                new OrderDto(order.getCreated(),
                        order.getType(),
                        order.getModel(),
                        order.getProblem(),
                       itemDao.getOrderSum(order.getId())))
                .toList();
    }

}
