package com.study.erum.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileInfo {
    private Long id;
    private String fileName;
    private String filePath;
    private Date createdAt;

    // Getter 및 Setter 메서드 생략

   
}
