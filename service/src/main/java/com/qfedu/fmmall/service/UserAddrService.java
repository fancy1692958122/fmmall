package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

/**
 * @author fancy
 * @create 2023-02-20 12:49
 */
public interface UserAddrService {

    public ResultVO listAddrsByUserId(int userId);

}
