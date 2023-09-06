package com.example.wayz.Repository;

import com.example.wayz.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {


    Orders findOrdersById(Integer id);

    List<Orders> findByStudentId(Integer studentId);

    //method to find the most expensive order
    @Query("SELECT o FROM Orders o ORDER BY o.tripPrice DESC")
    List<Orders> findMostExpensiveOrder();
}
