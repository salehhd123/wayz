package com.example.wayz.Repository;


import com.example.wayz.Model.CreditCard;
import com.example.wayz.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {



    @Query("select c from credit_card c where c.student.id=?1")
    CreditCard findCreditCardById(Integer id);

}
