package com.study.erum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.study.erum.model.FileInfo;
import com.study.erum.service.FileUploadService;

@Controller
public class FileUploadController {
    
    @RequestMapping("/")
    public String form() {
        return "form";
    }
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @PostMapping("/deleteImage")
    public void deleteImage(@RequestParam("url") String imageUrl) {
        // 이미지 삭제 처리
        fileUploadService.deleteImageByUrl(imageUrl);
    }
    
    
    
    @PostMapping("/uploadMultiple")
    public String uploadMultiple(Model model,
                                 @RequestParam("files") MultipartFile[] files) {
        // 최대 3개까지만 업로드
        int maxUploads = Math.min(files.length, 3);
        List<FileInfo> fileInfos = new ArrayList<>();

        for (int i = 0; i < maxUploads; i++) {
            MultipartFile file = files[i];
            String url = fileUploadService.restore(file);

            // 파일 정보를 리스트에 추가
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(file.getOriginalFilename());
            fileInfo.setFilePath(url);
            fileInfos.add(fileInfo);
        }
        
        // 모든 파일 정보를 한꺼번에 데이터베이스에 저장
        fileUploadService.saveFileInfoList(fileInfos);

        return "result"; // 업로드 완료 후의 페이지로 이동하거나 다른 처리 수행
    }
    @RequestMapping("/upload")
    public String upload(Model model,
                         @RequestParam("email") String email,
                         @RequestParam("file1") MultipartFile file) {
        String url = fileUploadService.restore(file);
        model.addAttribute("url", url);

        // 파일 정보를 DB에 저장
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setFilePath(url);
        fileUploadService.saveFileInfo(fileInfo);

        return "result";
    }
    
    @RequestMapping("/list")
    public String showImages(Model model) {
        List<String> imageUrls = fileUploadService.getAllImageUrls();
        model.addAttribute("imageUrls", imageUrls);
        System.out.println(imageUrls);
        return "list";
    }
    
    @GetMapping("/everland")
    public String showEverland(Model model) {
        List<String> imageUrls = fileUploadService.getAllImageByname();
        model.addAttribute("imageUrls", imageUrls);
        return "everland";
    }
    
    @RequestMapping("/gung")
    public String showGung(Model model) {
    	List<String> imageUrls = fileUploadService.getAllImageUrls();
        model.addAttribute("imageUrls", imageUrls);
        return "gung";
    }
}
