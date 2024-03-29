package com.study.erum.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.erum.model.FileInfo;

@Repository
public class FileInfoMapperImpl implements FileInfoMapper {
    
    private final SqlSession sqlSession;
    
    @Autowired
    public FileInfoMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertFileInfo(FileInfo fileInfo) {
        sqlSession.insert("com.study.erum.mapper.FileInfoMapper.insertFileInfo", fileInfo);
    }

	@Override
	public List<String> getAllImageUrls() {
		 return sqlSession.selectList("com.study.erum.mapper.FileInfoMapper.getAllImageUrls");
	}

	@Override
    public void deleteImageByUrl(String imageUrl) {
        sqlSession.delete("com.study.erum.mapper.FileInfoMapper.deleteImageByUrl", imageUrl);
    }

	@Override
	public List<String> getAllImageByname() {
		return sqlSession.selectList("com.study.erum.mapper.FileInfoMapper.getAllImageByname");
	}

}
