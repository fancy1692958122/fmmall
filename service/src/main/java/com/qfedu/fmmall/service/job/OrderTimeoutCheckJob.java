package com.qfedu.fmmall.service.job;

import com.github.wxpay.sdk.WXPay;
import com.qfedu.fmmall.dao.OrdersMapper;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fancy
 * @create 2023-02-21 20:29
 */
@Component
public class OrderTimeoutCheckJob {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderService orderService;
    private WXPay wxPay = new WXPay(new MyPayConfig());


    @Scheduled(cron = "0/60 * * * * ?")
    public void checkAndCloseOrder() {
        //1.查询超过30分钟订单状态依然为待支付状态的订单
        try {
            Example example = new Example(Orders.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("status", "1");

            Date time = new Date(System.currentTimeMillis() - 30 * 60 * 1000);
            criteria.andLessThan("createTime", time);

            List<Orders> orders = ordersMapper.selectByExample(example);

            //2.访问微信支付平台接口，确认当前订单的最终的支付状态
            for (int i = 0; i < orders.size(); i++) {
                Orders order = orders.get(i);
                HashMap<String, String> params = new HashMap<>();
                params.put("out_trade_no", order.getOrderId());
                Map<String, String> resp = wxPay.orderQuery(params);
                if ("SUCCESS".equalsIgnoreCase(resp.get("trade_state"))) {
                    //表示订单支付成功，进行补偿机制
                    Orders updateOrder = new Orders();
                    updateOrder.setOrderId(order.getOrderId());
                    updateOrder.setStatus("2");
                    ordersMapper.updateByPrimaryKeySelective(updateOrder);
                }else if ("NOTPAY".equalsIgnoreCase(resp.get("trade_state"))) {
                    //3。1 如果已经支付，则修改订单为已支付订单
                    //3。2.如果确实为未支付，则取消订单（先关支付 再关订单）
                    // 向微信支付平台发送请求 关闭当前订单的支付连接
                    wxPay.closeOrder(params);

                    //关闭订单
                    orderService.closeOrder(order.getOrderId());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
