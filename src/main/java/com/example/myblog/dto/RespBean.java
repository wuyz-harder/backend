package com.example.myblog.dto;

import lombok.Data;

/**
 * Created by sang on 2017/12/17.
 */
@Data
public class RespBean {
    private Integer status;
    private String msg;

    public RespBean() {
    }

    public RespBean(Integer status, String msg) {

        this.status = status;
        this.msg = msg;
    }

}
