package com.example.wayz.Service;


import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.DTO.ReportDto;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.Report;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.ReportRepository;
import com.example.wayz.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final AuthRepository authRepository;

    public List<Report> getALlReport(){
        return reportRepository.findAll();
    }



    public List<Report>getAllReportPending(){
    return reportRepository.allPending();
    }

    public void addReport(Integer user_id, Integer driver_id, ReportDto reportDto){
        Student student= studentRepository.findStudentById(user_id);
        Driver driver=driverRepository.findDriverById(driver_id);
        if (driver== null){
            throw new ApiException("Driver with ID " + driver_id + " not found");
        }
        Report report = new Report(null,null, reportDto.getDescription(),"pending",student,driver);
        reportRepository.save(report);
    }

    public void delete(Integer id){
        Report report= reportRepository.findReportById(id);
        if (report==null){
            throw new ApiException("you enter wrong id");
        }
        reportRepository.delete(report);
    }


    public void ignoreReport(Integer id , Integer report_id){
        User admin = authRepository.findAdmin(id);
        Report report = reportRepository.findReportById(report_id);
        if (admin==null){
            throw new ApiException("only admin can approve reports");
        }
        if(report==null){
            throw new ApiException("Driver with ID " + report_id + " not found");
        }
        report.setStatus("ignore");
        reportRepository.save(report);
    }
    public void approveReport(Integer id , Integer report_id){
        User admin = authRepository.findAdmin(id);
        Report report = reportRepository.findReportById(report_id);
        Driver driver1 = report.getDriver();
        if (admin==null){
            throw new ApiException("only admin can approve reports");
        }
        if(report==null){
            throw new ApiException("Driver with ID " + report_id + " not found");
        }
        report.setStatus("approved");
        reportRepository.save(report);
        if (reportRepository.countApproved(driver1.getId())==3){
            driver1.setStatus("fired");
            driverRepository.save(driver1);
        }
    }


















}
