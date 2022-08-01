package com.piserver.raspberry.fileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private IFileRepo fileRepo;

    public FileModel findFile(Long id) throws Exception {
        return fileRepo.findById(id).orElseThrow(()->new Exception("File not found"));
    }

    public void storeFileInDB(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        FileModel fileToStore = new FileModel(filename,
                                    file.getContentType(),
                                    file.getBytes());
        fileRepo.save(fileToStore);
    }

    public void saveInformation(MultipartFile file, String filePath){
        FileModel fileInfo = new FileModel(
                file.getOriginalFilename(),
                file.getContentType(),
                filePath);
        fileRepo.save(fileInfo);
    }

    public Resource getFileAsResource(Long id){
        if(fileRepo.findById(id).isPresent()){
            Path fileDir = Path.of(fileRepo.findById(id).get().getFilePath());
            try {
                return new UrlResource(fileDir.toUri());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new RuntimeException();
        }
    }

    public List<FileModel> getFileList(){
        return fileRepo.findAll();
    }
}