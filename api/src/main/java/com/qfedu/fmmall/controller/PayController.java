package com.qfedu.fmmall.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fancy
 * @create 2023-02-21 1:17
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private OrderService orderService;

    /**
     * 回调接口：当用户支付成功后 微信平台就会请求这个接口 将支付状态的接口传递过来
     * 1.接受微信支付平台传递的数据（传递的数据格式）
     */
    @RequestMapping("/callback")
    public String success(HttpServletRequest request) throws Exception {
        ServletInputStream is = request.getInputStream();
        byte[] bs = new byte[1024];
        int len = -1;
        StringBuilder stringBuilder = new StringBuilder();
        while((len = is.read(bs)) != -1){
            stringBuilder.append(new String(bs,0,len));
        }
        String s = stringBuilder.toString();

        //使用帮助类将xml格式转换为map
        Map<String, String> map = WXPayUtil.xmlToMap(s);

        if(map != null&& "success".equalsIgnoreCase(map.get("result_code"))){
            //支付成功
            //2.修改订单状态为，已支付/待发货
            String orderId = map.get("out_trade_no");
            int i = orderService.updateOrderStatus(orderId, "2");
            //3.通过websocket连接向前端推送消息
            WebSocketServer.sendMsg(orderId,"1");
            //4.响应微信支付平台
            if(i > 0){
                HashMap<String,String> resp = new HashMap<>();
                resp.put("return_code","success");
                resp.put("return_msg","OK");
                resp.put("app_id",map.get("appid"));
                resp.put("result_code","success");
                return WXPayUtil.mapToXml(resp);
            }
        }
        //支付失败
        return null;
    }
}
