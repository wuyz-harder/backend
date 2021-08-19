package com.example.myblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Data
public class paper {
    private Integer id;
    private String title;
    private String author;
    private Date date;
    private String tag;
    private String textarea;
}
