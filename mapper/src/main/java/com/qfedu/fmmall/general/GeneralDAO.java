package com.qfedu.fmmall.general;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author fancy
 * @create 2023-02-14 15:05
 */
public interface GeneralDAO<T> extends Mapper<T>, MySqlMapper<T> {
}
