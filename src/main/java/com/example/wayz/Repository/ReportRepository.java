package com.example.wayz.Repository;


import com.example.wayz.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {
    Report findReportById(Integer id);

    @Query("select count (c) from report c where c.driver.id=?1 and c.status='approved'")
    Integer countApproved(Integer id);

    @Query("select c from report c where c.status='pending'")
    List<Report> allPending();


}
