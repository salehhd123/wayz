package com.example.wayz.Repository;

import com.example.wayz.Model.MyFile;
import com.example.wayz.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<MyFile, Integer> {

    List<MyFile> findAllByUser(User user);

    MyFile findMyFileByFileNameAndUser(String filename, User user);

    MyFile findMyFileByIdAndUser(Integer fileId, User user);

//    List<MyFile> getAllByFileTypeContainingIgnoreCaseAndUser(String mediaType, User user);

}
