package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

/**
 * @author fancy
 * @create 2023-02-18 2:18
 */
public interface ProductService {

    public ResultVO listRecommendProducts();

    public ResultVO getProductBasicInfo(String productId);

    public ResultVO getProductParamsById(String productId);


    public ResultVO getProductsByCategoryId(int categoryId,int pageNum,int limit);

    public ResultVO listBrands(int categoryId);

    public ResultVO searchProduct(String kw,int pageNum,int limit);

    public ResultVO listBrands(String kw);


}
