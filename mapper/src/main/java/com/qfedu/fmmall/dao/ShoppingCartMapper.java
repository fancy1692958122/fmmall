package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.fmmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart> {

    public List<ShoppingCartVO> selectShopCartByUserId(int userId);

    public int updateCartNumByCartId(@Param("cartId") int cartId,
                                     @Param("cartNum") int cartNum);


    public List<ShoppingCartVO> selectShopcartByCids(@Param("cids") List<Integer> cids);


}