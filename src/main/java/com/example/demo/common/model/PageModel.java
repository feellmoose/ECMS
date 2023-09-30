package com.example.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageModel<T>{
    private List<T> result;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalSize;
}
