package com.example.demo.common.model;

import java.util.List;

public class PageModel<T>{
    private List<T> result;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalSize;
}
