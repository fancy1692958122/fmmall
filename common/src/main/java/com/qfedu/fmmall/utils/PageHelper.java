package com.qfedu.fmmall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fancy
 * @create 2023-02-19 0:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageHelper<T> {

    //总记录数
    private int count;

    //总页数
    private int PageCount;

    //分页数据
    private List<T> list;
}
