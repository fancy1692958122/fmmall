package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.UserAddrService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fancy
 * @create 2023-02-20 12:59
 */
@RestController
@CrossOrigin
@Api(value = "提供收货地址管理相关接口",tags = "收货地址管理")
@RequestMapping("/useraddr")
public class UserAddrController {

    @Resource
    private UserAddrService userAddrService;

    @GetMapping("/list")
    @ApiOperation("查询收货地址的接口")
    @ApiImplicitParam(dataType = "int",name = "userId",value = "用户ID",required = true)
    public ResultVO listAddr(Integer userId, @RequestHeader("token") String token){
        ResultVO resultVO = userAddrService.listAddrsByUserId(userId);
        return resultVO;
    }

}
