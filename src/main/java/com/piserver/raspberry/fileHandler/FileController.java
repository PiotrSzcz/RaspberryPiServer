package com.piserver.raspberry.fileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(path = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity fileToUpload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.storeFile(file);
        return ResponseEntity.ok().body("File saved: " + file.getOriginalFilename());
    }

    @GetMapping("/list")
    public ResponseEntity getList(){
        return ResponseEntity.ok().body(fileService.getFileList());
    }
}
