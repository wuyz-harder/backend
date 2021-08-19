package com.example.myblog.service;

import com.example.myblog.entity.paper;
import com.example.myblog.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PaperService {
    @Autowired
    PaperMapper paperMapper;
    public List<paper> getPaper(){
        return paperMapper.getPaper();
    }
}
