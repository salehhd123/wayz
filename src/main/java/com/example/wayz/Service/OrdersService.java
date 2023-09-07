package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.DTO.OrderDTO;
import com.example.wayz.Model.Orders;
import com.example.wayz.Model.Student;
import com.example.wayz.Repository.OrdersRepository;
import com.example.wayz.Repository.StudentRepository;
import com.example.wayz.Repository.StudentTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final StudentTripsRepository studentTripsRepository;
    private final StudentRepository studentRepository;


    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void addOrders(OrderDTO orders){
        Student student = studentRepository.findStudentByuUser(orders.getStudent_id());
        Integer tripsNum = studentTripsRepository.tripsNumber(orders.getStudent_id());
        Integer g = 18;
        Orders orders1 = new Orders(null,g,orders.getCreatedAt(),student);
        student.setTripsLeft(student.getTripsLeft()+tripsNum);
        ordersRepository.save(orders1);
    }

    public double totalOrderPrice(Integer order_id){
        Orders orders = ordersRepository.findOrdersById(order_id);
        Student student=orders.getStudent();
        Integer TripsNum= studentTripsRepository.tripsNumber(student.getId());
        double total=orders.getTripPrice()*TripsNum;
        return total;
    }

    public void updateOrders(Integer id,Orders orders){
        Orders orders1=ordersRepository.findOrdersById(id);
        if (orders1==null) {
            throw new ApiException("Order with ID " + id + " not found");
        }
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
