package com.piserver.raspberry.httpControlers;

import com.piserver.raspberry.fileHandler.FileModel;
import com.piserver.raspberry.fileHandler.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListController {

    @Autowired
    private FileService fileService;

    @GetMapping("file/list")
    String getList(Model model){
        model.addAttribute("files", fileService.getFileList());
        return "fileList";
    }
}
