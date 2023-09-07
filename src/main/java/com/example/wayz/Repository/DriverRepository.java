package com.example.wayz.Repository;

import com.example.wayz.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    Driver findDriverById(Integer id);

    @Query("select count (c) from driver c where c.Reports='approved'")
    Integer countApproved();

}
