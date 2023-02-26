package com.qfedu.fmmall.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fancy
 * @create 2023-02-21 16:22
 */
@Component
@ServerEndpoint("/webSocket/{oid}")
public class WebSocketServer {

    private static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 前端发送请求 建立websocket连接就会执行onopen方法
     */
    @OnOpen
    public void open(@PathParam("oid") String orderId, Session session){
        sessionMap.put(orderId,session);
    }

    /**
     * 前端关闭页面/主动关闭websocket连接，都会执行close
     */
    @OnClose
    public void close(@PathParam("oid") String orderId){
        sessionMap.remove(orderId);
    }


    public static void sendMsg(String orderId,String msg){
        try {
            Session session = sessionMap.get(orderId);
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
