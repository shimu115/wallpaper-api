package com.shimu.ramdomimg.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GitHubJsonResult <T> {

    private Integer code;
    private String msg;
    private Integer total;
    private T data;
}
