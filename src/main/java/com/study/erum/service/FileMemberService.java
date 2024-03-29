package com.study.erum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.erum.mapper.FileMemberMapper;
import com.study.erum.model.FileMember;

@Service
public class FileMemberService {

    private final FileMemberMapper fileMemberMapper;

    @Autowired
    public FileMemberService(FileMemberMapper fileMemberMapper) {
        this.fileMemberMapper = fileMemberMapper;
    }

    @Transactional
    public void addMember(FileMember fileMember) {
        fileMemberMapper.insertMemberInfo(fileMember);
    }
}
