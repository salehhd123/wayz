package com.example.wayz.Controller;

import com.example.wayz.Api.ApiResponse.ApiResponse;
import com.example.wayz.DTO.ReportDto;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.ReportRepository;
import com.example.wayz.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(reportService.getALlReport());
    }

    @GetMapping("/get-all-pending")
    public ResponseEntity getPending() {
        return ResponseEntity.status(200).body(reportService.getAllReportPending());
    }

    @PostMapping("/add-report/{id}")
    public ResponseEntity addReport(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id, @RequestBody ReportDto reportDto,
            @RequestParam(value = "file", required = false) MultipartFile reportMedia) throws IOException {

        reportService.addReport(user.getId(), id, reportDto, reportMedia);
        return ResponseEntity.status(200).body(new ApiResponse("the report added"));
    }


    @DeleteMapping("/delete-report/{id}")
    public ResponseEntity deleteReport(Integer id) {
        reportService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("the report deleted"));
    }


    @PutMapping("/ignore/{id}")
    public ResponseEntity ignore(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        reportService.ignoreReport(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("the report change to ignore"));
    }


    @PutMapping("/approve/{id}")
    public ResponseEntity approve(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        reportService.approveReport(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("the report change to approve"));
    }

}
