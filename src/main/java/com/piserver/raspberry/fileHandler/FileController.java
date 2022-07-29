package com.piserver.raspberry.fileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws Exception {
        FileModel file = fileService.downloadFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "Download file=\""+file.getFilename() +"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    /*@GetMapping("/list")
    public ResponseEntity getList(){
        return ResponseEntity.ok().body(fileService.getFileList());
    }*/
}
