package com.example.wayz.Controller;

import com.example.wayz.Repository.ReportRepository;
import com.example.wayz.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/report")
public class ReportController {

    private final ReportService reportService;





}
