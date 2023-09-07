package com.example.wayz.Repository;


import com.example.wayz.Model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {


}
