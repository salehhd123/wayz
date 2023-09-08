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
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {


    private final FileRepository fileRepository;
    private final AuthRepository userRepository;
    private final DriverRepository driverRepository;
    private final ReportRepository reportRepository;

    private final String SERVER_FILES_FOLDER = "C:/Users/isaud/IdeaProjects/System/src/main/resources/users_files/";

    //// record to put the file info and the file itself in one place I think it's more readable this way, plus we can return both from a function.
    public record FileInfoRecord(MediaType docType, byte[] data) {
    }

    public Driver uploadDriverDocuments(HashMap<String, MultipartFile> driverDocsMap, Integer driverId) throws IOException, RuntimeException {

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

            file.transferTo(new File(fileLocation));

            int fileSizeInMb = Math.toIntExact((file.getSize() >> 20));

            MyFile uploadFile = new MyFile();
            uploadFile.setFileName(docType);
            uploadFile.setFileType(file.getContentType());
            uploadFile.setSize(fileSizeInMb > 0 ? fileSizeInMb : 1); //// if file is less than 1mb it'll be 0 after shifting so we'll just put 1mb as an approximation in that case.
            uploadFile.setUser(driver.getUser());
            fileRepository.save(uploadFile);
        }

        driverRepository.save(driver);
        return driver;
    }

    public void uploadReportMedia(MultipartFile file, Integer reportId) throws IOException, RuntimeException {

        Report report = reportRepository.findReportById(reportId);

        if (report == null) {
            throw new ApiException("Could not find a driver with this id.");
        }
        String fileLocation = SERVER_FILES_FOLDER + "student_" + report.getStudent().getUser().getId() + "/";

        Files.createDirectories(Paths.get(fileLocation));


        if (file.isEmpty()) {
            throw new ApiException("There was a problem with file named: " + file.getOriginalFilename());
        }

        file.transferTo(new File(fileLocation));

        int fileSizeInMb = Math.toIntExact((file.getSize() >> 20));

        MyFile uploadFile = new MyFile();
        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setFileType(file.getContentType());
        uploadFile.setSize(fileSizeInMb > 0 ? fileSizeInMb : 1); //// if file is less than 1mb it'll be 0 after shifting so we'll just put 1mb as an approximation in that case.
        uploadFile.setUser(report.getStudent().getUser());
        fileRepository.save(uploadFile);

        reportRepository.save(report);
    }

    public List<MyFile> getMyFiles(Integer userId) throws RuntimeException {

        User filesOwner = userRepository.findUserById(userId);

        List<MyFile> userFilesList = fileRepository.findAllByUser(filesOwner);

        if (userFilesList.isEmpty()) {

            throw new ApiException("You have no files yet.");
        }
        return userFilesList;
    }

    public FileInfoRecord downloadFileById(Integer userId, Integer fileID) throws IOException, RuntimeException {


        ///// doing this way allows us to prevent any unwanted access to any user's files since this token is generated and given everytime the user login to their account.
        User user = userRepository.findUserById(userId);


        MyFile downloadFile = fileRepository.findMyFileByIdAndUser(fileID, user);

        if (downloadFile == null) {
            throw new ApiException("There was a problem with the file");

        }

        String downloadFilePath = SERVER_FILES_FOLDER + user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());

        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }

    public FileInfoRecord downloadFileByName(Integer userId, String fileName) throws IOException, RuntimeException {


        User user = userRepository.findUserById(userId);

        MyFile downloadFile = fileRepository.findMyFileByFileNameAndUser(fileName, user);

        if (downloadFile == null) throw new RuntimeException();

        String downloadFilePath = SERVER_FILES_FOLDER + user.getId() + "/" + downloadFile.getFileName();

        byte[] file = Files.readAllBytes(new File(downloadFilePath).toPath());


        return new FileInfoRecord(MediaType.valueOf(downloadFile.getFileType()), file);

    }
}
