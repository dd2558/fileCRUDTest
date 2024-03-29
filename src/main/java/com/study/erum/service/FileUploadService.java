package com.study.erum.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.study.erum.mapper.FileInfoMapper;
import com.study.erum.model.FileInfo;

@Service
public class FileUploadService {
	// 리눅스 기준으로 파일 경로를 작성 ( 루트 경로인 /으로 시작한다. )
	// 윈도우라면 workspace의 드라이브를 파악하여 JVM이 알아서 처리해준다.
	// 따라서 workspace가 C드라이브에 있다면 C드라이브에 upload 폴더를 생성해 놓아야 한다.
	private static final String SAVE_PATH = "/upload";
	private static final String PREFIX_URL = "/upload/";
	
	@Autowired
    private FileInfoMapper fileInfoMapper;

    public void saveFileInfo(FileInfo fileInfo) {
        fileInfoMapper.insertFileInfo(fileInfo);
    }
	
    public String restore(MultipartFile multipartFile) {
        String url = null;

        try {
            // 파일 정보
            String originFilename = multipartFile.getOriginalFilename();
            String extName = "";
            int lastIndex = originFilename.lastIndexOf(".");
            if (lastIndex != -1) {
                extName = originFilename.substring(lastIndex);
            } else {
                // 확장자가 없는 경우 기본 확장자를 설정
                extName = ".jpg"; // 예시로 기본 확장자를 .jpg로 설정
            }
            Long size = multipartFile.getSize();

            // 서버에서 저장할 파일 이름
            String saveFileName = genSaveFileName(extName);

            System.out.println("originFilename : " + originFilename);
            System.out.println("extensionName : " + extName);
            System.out.println("size : " + size);
            System.out.println("saveFileName : " + saveFileName);

            writeFile(multipartFile, saveFileName);
            url = PREFIX_URL + saveFileName;

            // 파일 정보를 데이터베이스에 저장
			/*
			 * FileInfo fileInfo = new FileInfo(); fileInfo.setFileName(originFilename);
			 * fileInfo.setFilePath(url); saveFileInfo(fileInfo);
			 */
        } catch (IOException e) {
            // 파일 저장 중 예외 발생 시
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.");
        }

        return url;
    }

	
	
	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
	
	
	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName)
								throws IOException{
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}

	public List<String> getAllImageUrls() {
		 return fileInfoMapper.getAllImageUrls();
	}

	public void saveFileInfoList(List<FileInfo> fileInfoList) {
    for (FileInfo fileInfo : fileInfoList) {
        fileInfoMapper.insertFileInfo(fileInfo);
    }
  }
	
	public List<String> getAllImageByname() {
        return fileInfoMapper.getAllImageByname();
    }
	

	public void deleteImageByUrl(String imageUrl) {
    // URL에서 파일 이름 추출
    String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    
    // 파일 정보 삭제
    fileInfoMapper.deleteImageByUrl(imageUrl);
    
    // 파일 시스템에서 이미지 파일 삭제 (이 부분은 필요에 따라 구현)
    // deleteFileFromFileSystem(fileName);
}
}
