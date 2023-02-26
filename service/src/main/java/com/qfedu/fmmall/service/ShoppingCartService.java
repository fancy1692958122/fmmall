package com.qfedu.fmmall.service;

import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.vo.ResultVO;

/**
 * @author fancy
 * @create 2023-02-19 15:11
 */
public interface ShoppingCartService {

    public ResultVO addShoppingCart(ShoppingCart cart);

    public ResultVO listShoppingCartByUserId(int userId);

    public ResultVO updateCartNum(int cartId,int cartNum);

    public ResultVO listShoppingCartsByCids(String cids);

}
