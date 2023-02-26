package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

/**
 * @author fancy
 * @create 2023-02-13 21:13
 */
public interface UserService {

    //注册
    public ResultVO userResgit(String name,String pwd);

    //登录
    public ResultVO checkLogin(String name,String pwd);

}
