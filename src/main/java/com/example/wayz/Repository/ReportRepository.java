package com.example.wayz.Repository;


import com.example.wayz.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {
Report findReportById(Integer id);

@Query("select count (c) from report c where c.driver.user=?1 and c.status='approved'")
Integer countApproved(Integer id);

}
