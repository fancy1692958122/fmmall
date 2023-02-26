package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

/**
 * @author fancy
 * @create 2023-02-17 23:33
 */
public interface CategoryService {

    public ResultVO listCategories();

    public ResultVO listFirstLevelCategories();

}
