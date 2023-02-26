package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.Category;
import com.qfedu.fmmall.entity.CategoryVO;
import com.qfedu.fmmall.general.GeneralDAO;

import java.util.List;

public interface CategoryMapper extends GeneralDAO<Category> {

    //1.连接查询分类列表
    public List<CategoryVO> selectAllCategories();

    //2.子查询 根据parentid查询子分类
    public List<CategoryVO> selectAllCategories(int parentId);

    //3.查询一级类别
    public List<CategoryVO> selectFirstLevelCategories();


}