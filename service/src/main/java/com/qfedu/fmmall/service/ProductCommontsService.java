package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

/**
 * @author fancy
 * @create 2023-02-18 22:21
 */
public interface ProductCommontsService {

    /**
     * 根据商品id实现评论的分页查询
     * @param productId 商品id
     * @param pageNum 查询页码
     * @param limit 每页显示条数
     * @return
     */
    public ResultVO ListCommontsByProductId(String productId,int pageNum,int limit);

    /**
     * 根据商品id统计当前商品的评价信息
     * @param productId
     */
    public ResultVO getCommontsByProductId(String productId);

}
