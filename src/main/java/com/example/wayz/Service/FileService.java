package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.MyFile;
import com.example.wayz.Model.Report;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.FileRepository;
import com.example.wayz.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {


    private final FileRepository fileRepository;
    private final AuthRepository userRepository;
    private final DriverRepository driverRepository;
    private final ReportRepository reportRepository;

    private final String SERVER_FILES_FOLDER = "C:/Users/isaud/IdeaProjects/wayz/src/main/resources/users_files/";

//    private final String SERVER_FILES_FOLDER = "/home/alharbi/projects/toys/wayz/src/main/resources/users_files/";

    //// record to put the file info and the file itself in one place I think it's more readable this way, plus we can return both from a function.
    public record FileInfoRecord(MediaType mediaType, byte[] data) {
    }

    public void uploadDriverDocuments(HashMap<String, MultipartFile> driverDocsMap, Integer driverId) throws IOException, RuntimeException {

        Driver driver = driverRepository.findDriverById(driverId);

        if (driver == null) {
            throw new ApiException("Could not find a driver with this id.");
        }
        String fileLocation = SERVER_FILES_FOLDER + "driver_" + driver.getId() + "/";

        Files.createDirectories(Paths.get(fileLocation));

        for (String docType : driverDocsMap.keySet()) {

            MultipartFile file = driverDocsMap.get(docType);
            if (file.isEmpty()) {
                throw new ApiException("There was a problem with file named: " + file.getOriginalFilename());
            }

            file.transferTo(new File(fileLocation + docType));

            int fileSizeInMb = Math.toIntExact((file.getSize() >> 20));

            MyFile uploadFile = new MyFile();
            uploadFile.setFileName(docType);
            uploadFile.setFileType(file.getContentType());
            uploadFile.setSize(fileSizeInMb > 0 ? fileSizeInMb : 1); //// if file is less than 1mb it'll be 0 after shifting so we'll just put 1mb as an approximation in that case.
            uploadFile.setUser(driver.getUser());
            fileRepository.save(uploadFile);
        }

        driverRepository.save(driver);
    }

    public void uploadReportMedia(MultipartFile file, Integer reportId) throws IOException, RuntimeException {

        Report report = reportRepository.findReportById(reportId);

        if (report == null) {
            throw new ApiException("Could not find a report with this id.");
        }
        String fileLocation = SERVER_FILES_FOLDER + "student_" + report.getStudent().getId() + "/";

        System.out.println("********AAA");
        Files.createDirectories(Paths.get(fileLocation));


        if (file.isEmpty()) {
            throw new ApiException("There was a problem with file named: " + file.getOriginalFilename());
        }
        String fileRandomName = UUID.randomUUID().toString();

        file.transferTo(new File(fileLocation + fileRandomName));

        int fileSizeInMb = Math.toIntExact((file.getSize() >> 20));

        MyFile uploadFile = new MyFile();
        uploadFile.setFileName(fileRandomName);
        uploadFile.setFileType(file.getContentType());
        uploadFile.setSize(fileSizeInMb > 0 ? fileSizeInMb : 1); //// if file is less than 1mb it'll be 0 after shifting so we'll just put 1mb as an approximation in that case.
        uploadFile.setUser(report.getStudent().getUser());

        report.setMedia(fileRandomName);

        fileRepository.save(uploadFile);
        reportRepository.save(report);
    }

    public List<MyFile> getAllStudentReportsMedia(Integer studentId) throws RuntimeException {

        User filesOwner = userRepository.findUserById(studentId);

        List<MyFile> userFilesList = fileRepository.findAllByUser(filesOwner);

        if (userFilesList.isEmpty()) {

            throw new ApiException("You have no files yet.");
        }
        return userFilesList;
    }

    public FileInfoRecord downloadFileById(Integer studentId, Integer fileID) throws IOException, ApiException {

        User user = userRepository.findUserById(studentId);


        MyFile downloadFile = fileRepository.findMyFileByIdAndUser(fileID, user);

        if (downloadFile == null) {
            throw new ApiException("There was a problem with the file");

        }

        String downloadFilePath = SERVER_FILES_FOLDER + "student_" + +user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());

        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }

    public FileInfoRecord downloadDriverLicense(Integer driverId) throws IOException, RuntimeException {

        User user = userRepository.findUserById(driverId);

        MyFile downloadFile = fileRepository.findMyFileByFileNameAndUser("license", user);

        if (downloadFile == null) {
            throw new ApiException("This file does not exist");
        }

        String downloadFilePath = SERVER_FILES_FOLDER + "driver_" + user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());


        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }

    public FileInfoRecord downloadDriverId(Integer driverId) throws IOException, RuntimeException {

        User user = userRepository.findUserById(driverId);

        MyFile downloadFile = fileRepository.findMyFileByFileNameAndUser("id", user);

        if (downloadFile == null) {
            throw new ApiException("This file does not exist");
        }

        String downloadFilePath = SERVER_FILES_FOLDER + "driver_" + user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());


        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }

    public FileInfoRecord downloadDriverRegistration(Integer driverId) throws IOException, RuntimeException {

        User user = userRepository.findUserById(driverId);

        MyFile downloadFile = fileRepository.findMyFileByFileNameAndUser("registration", user);

        if (downloadFile == null) {
            throw new ApiException("This file does not exist");
        }

        String downloadFilePath = SERVER_FILES_FOLDER + "driver_" + user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());


        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }

    public FileInfoRecord downloadDriverPic(Integer driverId) throws IOException, RuntimeException {

        User user = userRepository.findUserById(driverId);

        MyFile downloadFile = fileRepository.findMyFileByFileNameAndUser("pic", user);

        if (downloadFile == null) {
            throw new ApiException("This file does not exist");
        }

        String downloadFilePath = SERVER_FILES_FOLDER + "driver_" + user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());


        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }
}
