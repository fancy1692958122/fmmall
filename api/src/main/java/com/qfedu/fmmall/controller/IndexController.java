package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fancy
 * @create 2023-02-17 20:13
 */

@RestController
@CrossOrigin
@RequestMapping("/index")
@Api(value = "提供首页数据显示所需的接口",tags = "首页管理")
public class IndexController {

    @Resource
    private IndexImgService indexImgService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @ApiOperation("首页轮播图接口")
    @GetMapping("/indeximg")
    public ResultVO listIndexImgs(){
        return indexImgService.listIndexImgs();
    }


    @GetMapping("/category-list")
    @ApiOperation("商品分类信息接口")
    public ResultVO listCategory(){
        return categoryService.listCategories();
    }

    @GetMapping("/list-recommends")
    @ApiOperation("新品推荐信息接口")
    public ResultVO listRecommendProduct(){
        return productService.listRecommendProducts();
    }

    @GetMapping("/category-recommends")
    @ApiOperation("分类推荐信息接口")
    public ResultVO listRecommendProductByCategory(){
        return categoryService.listFirstLevelCategories();
    }


}
