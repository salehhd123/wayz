package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.Orders;
import com.example.wayz.Repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;


    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void addOrders(Orders orders){
        ordersRepository.save(orders);
    }

    public void updateOrders(Integer id,Orders orders){
        Orders orders1=ordersRepository.findOrdersById(id);
        if (orders1==null) {
            throw new ApiException("Order with ID " + id + " not found");
        }
        orders1.setDays(orders.getDays());
        orders1.setTripPrice(orders.getTripPrice());
    }

    public void deleteOrders(Integer id){
        Orders orders=ordersRepository.findOrdersById(id);
        if (orders==null){
            throw new ApiException("Order with ID " + id + " not found");
        }
        ordersRepository.deleteById(id);
    }

    public Orders getOrderById(Integer id) {
        Orders order1=ordersRepository.findOrdersById(id);
        if (order1 == null) {
            throw new ApiException("ID Not Found");
        }
        return ordersRepository.findOrdersById(id);
    }


    // get total revenue from all orders
    public Integer getTotalRevenueFromOrders() {
        List<Orders> allOrders = ordersRepository.findAll();
        Integer totalRevenue = 0;
        for (Orders order : allOrders) {
            totalRevenue += order.getTripPrice() * order.getDays();
        }
        return totalRevenue;
    }


    // get average completion time for orders
    public Double getAverageCompletionTime() {
        List<Orders> allOrders = ordersRepository.findAll();
        if (allOrders.isEmpty()) {
            return 0.0;
        }
        int totalDays = 0;
        for (Orders order : allOrders) {
            totalDays += order.getDays();
        }
        return (double) totalDays / allOrders.size();
    }

    // get orders by a specific student
    public List<Orders> getOrdersByStudentId(Integer studentId) {
        return ordersRepository.findByStudentId(studentId);
    }


    // get the most expensive order using query method
    public Orders getMostExpensiveOrder() {
        List<Orders> mostExpensiveOrders = ordersRepository.findMostExpensiveOrder();
        return mostExpensiveOrders.isEmpty() ? null : mostExpensiveOrders.get(0);
    }

}
