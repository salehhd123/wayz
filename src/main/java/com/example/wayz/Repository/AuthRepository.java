package com.example.wayz.Repository;

import com.example.wayz.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserById(Integer id);

    @Query("select c from users c where c.role='ADMIN' and c.id=?1")
    User findAdmin(Integer id);

}
