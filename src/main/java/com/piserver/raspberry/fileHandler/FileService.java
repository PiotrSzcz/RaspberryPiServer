package com.piserver.raspberry.fileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private IFileRepo fileRepo;

    public FileModel storeFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        FileModel fileToStore = new FileModel(filename,
                                    file.getContentType(),
                                    file.getBytes());
        return fileRepo.save(fileToStore);
    }

    public List<FileModel> getFileList(){
        return fileRepo.findAll();
    }
}
