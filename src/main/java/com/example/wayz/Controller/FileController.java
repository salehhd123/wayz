package com.example.wayz.Controller;

import com.example.wayz.Api.ApiResponse.ApiResponse;
import com.example.wayz.Model.User;
import com.example.wayz.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;


    // * for admins only *//
    @GetMapping("/get-driver-files{studentId}")
    public ResponseEntity<ApiResponse<?>> getAllStudentReportMedia(@PathVariable Integer studentId) {

        return ResponseEntity.ok(new ApiResponse<>(fileService.getAllStudentReportsMedia(studentId)));
    }

    // * for admins only *//
    @GetMapping("/download-by-id/{studentId}/{fileId}")
    public ResponseEntity<?> downloadFileById(@PathVariable Integer studentId, @PathVariable Integer fileId) throws IOException {

        var file = fileService.downloadFileById(studentId, fileId);
        return ResponseEntity.status(HttpStatus.OK).contentType(file.mediaType()).body(file.data());
    }

    // * for admins only *//
    @GetMapping("/download-by-name/{driverId}")
    public ResponseEntity<?> downloadDriverLicence(@PathVariable Integer driverId) throws IOException {

        var file = fileService.downloadDriverLicence(driverId);
        return ResponseEntity.status(HttpStatus.OK).contentType(file.mediaType()).body(file.data());
    }


    // * for admins only *//
    @GetMapping("/download-by-name/{driverId}")
    public ResponseEntity<?> downloadDriverId(@PathVariable Integer driverId) throws IOException {

        var file = fileService.downloadDriverId(driverId);
        return ResponseEntity.status(HttpStatus.OK).contentType(file.mediaType()).body(file.data());
    }

    // * for admins only *//
    @GetMapping("/download-by-name/{driverId}")
    public ResponseEntity<?> downloadDriverPic(@PathVariable Integer driverId) throws IOException {

        var file = fileService.downloadDriverPic(driverId);
        return ResponseEntity.status(HttpStatus.OK).contentType(file.mediaType()).body(file.data());
    }

    @GetMapping("/download-by-name/{driverId}")
    public ResponseEntity<?> downloadDriverRegistration(@PathVariable Integer driverId) throws IOException {

        var file = fileService.downloadDriverRegistration(driverId);
        return ResponseEntity.status(HttpStatus.OK).contentType(file.mediaType()).body(file.data());
    }

    @GetMapping("/get-allowed")
    public ResponseEntity<ApiResponse<?>> getMaximumUploadSize() {

        final int MAX_FILE_SIZE = 100;
        return ResponseEntity.ok(new ApiResponse<>("You can upload up to: " + MAX_FILE_SIZE + "MB"));
    }
}
