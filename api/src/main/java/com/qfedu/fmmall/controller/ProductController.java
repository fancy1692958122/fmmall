package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.ProductCommontsService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fancy
 * @create 2023-02-18 16:49
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品信息相关接口",tags = "商品管理")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductCommontsService productCommontsService;

    @ApiOperation("商品基本信息接口")
    @GetMapping("/detail-info/{pid}")
    public ResultVO getProductBasicInfo(@PathVariable("pid") String pid){
        return productService.getProductBasicInfo(pid);
    }

    @ApiOperation("商品参数信息查询接口")
    @GetMapping("/detail-params/{pid}")
    public ResultVO getProductParams(@PathVariable("pid") String pid){
        return productService.getProductParamsById(pid);
    }

    @ApiOperation("商品评论信息查询接口")
    @GetMapping("/detail-commonts/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true)})
    public ResultVO getProductCommonts(@PathVariable("pid") String pid,int pageNum,int limit){
        return productCommontsService.ListCommontsByProductId(pid,pageNum,limit);
    }


    @ApiOperation("商品评论统计查询接口")
    @GetMapping("/detail-commontscount/{pid}")
    public ResultVO getProductCommontsCount(@PathVariable("pid") String pid){
        return productCommontsService.getCommontsByProductId(pid);
    }


    @ApiOperation("根据类别查询商品接口")
    @GetMapping("/listbycid/{cid}")
    public ResultVO getProductsByCategoryId(@PathVariable("cid") int cid,int pageNum,int limit){
        return productService.getProductsByCategoryId(cid,pageNum,limit);
    }

    @ApiOperation("根据类别查询商品品牌接口")
    @GetMapping("/listbrands/{cid}")
    public ResultVO getBrandsByCategoryId(@PathVariable("cid") int cid){
        return productService.listBrands(cid);
    }


    @ApiOperation("根据名称进行商品查询接口")
    @GetMapping("/listbykeyword")
    public ResultVO searchProducts(String keyword,int pageNum,int limit){
        return productService.searchProduct(keyword, pageNum, limit);
    }


    @ApiOperation("根据关键字查询商品品牌接口")
    @GetMapping("/listbrands-keyword")
    public ResultVO getBrandsByKeyword(String keyword){
        return productService.listBrands(keyword);
    }


}
