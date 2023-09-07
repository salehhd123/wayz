package com.example.wayz.Controller;

import com.example.wayz.DTO.OrderDTO;
import com.example.wayz.Model.Orders;
import com.example.wayz.Model.User;
import com.example.wayz.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;


    @GetMapping("/get-all")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.status(200).body(ordersService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody OrderDTO orders) {
        ordersService.addOrders(orders);
        return ResponseEntity.status(200).body("Order added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @RequestBody @Valid Orders order) {
        ordersService.updateOrders(id, order);
        return ResponseEntity.status(200).body("Order updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        ordersService.deleteOrders(id);
        return ResponseEntity.status(200).body("Order deleted successfully");
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(ordersService.getOrderById(id));
    }

    @GetMapping("/getOrdersByStudentId")
    public ResponseEntity getOrdersByStudentId(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(ordersService.getOrdersByStudentId(user.getId()));
    }

    @GetMapping("/MostExpensiveOrder")
    public ResponseEntity getMostExpensiveOrder() {
        return ResponseEntity.status(200).body(ordersService.getMostExpensiveOrder());
    }

    @GetMapping("/totalOrderPrice/{id}")
    public ResponseEntity getTotalOrderPrice(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(ordersService.totalOrderPrice(id));
    }

}
