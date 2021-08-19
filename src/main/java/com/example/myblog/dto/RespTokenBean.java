package com.example.myblog.dto;

import lombok.Data;

@Data
public class RespTokenBean {
    private Integer status;
    private String token;

    public RespTokenBean() {
    }

    public RespTokenBean(Integer status, String token) {

        this.status = status;
        this.token = token;
    }
}
