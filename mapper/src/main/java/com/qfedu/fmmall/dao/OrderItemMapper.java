package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.fmmall.general.GeneralDAO;

import java.util.List;

public interface OrderItemMapper extends GeneralDAO<OrderItem> {

    public List<OrderItem> listOrderItemsByOrderId(String orderId);

}