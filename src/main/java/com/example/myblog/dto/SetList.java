package com.example.myblog.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SetList {
    private String username;
    private List<String> set;
}
