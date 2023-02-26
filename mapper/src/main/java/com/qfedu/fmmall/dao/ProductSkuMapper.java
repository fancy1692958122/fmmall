package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.general.GeneralDAO;

import java.util.List;

public interface ProductSkuMapper extends GeneralDAO<ProductSku> {

    /**
     * 根据商品id 查询当前商品所有套餐中价格最低的套餐（虽然只有一只=。= 就要list！）
     * @param productId
     * @return
     */
    public List<ProductSku> selectLowerestPriceByProductId(String productId);

}