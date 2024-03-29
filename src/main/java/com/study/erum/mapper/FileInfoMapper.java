package com.study.erum.mapper;

import java.util.List;

import com.study.erum.model.FileInfo;

public interface FileInfoMapper {
	void insertFileInfo(FileInfo fileInfo);

	List<String> getAllImageUrls();
	
	List<String> getAllImageByname();
	
	void deleteImageByUrl(String imageUrl);
}
