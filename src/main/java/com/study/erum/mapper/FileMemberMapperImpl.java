package com.study.erum.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.erum.model.FileMember;

@Repository
public class FileMemberMapperImpl implements FileMemberMapper {

    private final SqlSession sqlSession;

    @Autowired
    public FileMemberMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertMemberInfo(FileMember fileMember) {
        sqlSession.insert("com.study.erum.mapper.FileMemberMapper.insertMemberInfo", fileMember);
    }
}
