package com.piserver.raspberry.fileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

@RestController
@RequestMapping(path = "/file")
public class FileController {

    private String postPath = "/Users/piotrek/Desktop/Programy/inzynierka?/raspberry/savedFiles/";
    @Autowired
    private FileService fileService;

    @PostMapping("/uploadDB")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.storeFileInDB(file);
        return ResponseEntity.ok().body("Plik zapisany: " + file.getOriginalFilename());
    }

    @GetMapping("/downloadDB/{id}")
    public ResponseEntity<Resource> downloadFromDB(@PathVariable Long id) throws Exception {
        FileModel file = fileService.findFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "Pobrano plik=\""+file.getFilename() +"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file){
        String filename = LocalDateTime.now().format(ISO_LOCAL_TIME) + file.getOriginalFilename();
        try {
            fileService.saveInformation(file, postPath+filename);
            file.transferTo(new File(postPath + filename));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd zapisu pliku: " + filename);
        }
        return ResponseEntity.ok().body("Plik " + filename + " został zapisany pomyślnie");
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) throws Exception {
        FileModel file = fileService.findFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "Download file=\""+file.getFilename() +"\"")
                .body(fileService.getFileAsResource(id));
    }

    @GetMapping("/dblist")
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok().body(fileService.getFileList());
    }
}