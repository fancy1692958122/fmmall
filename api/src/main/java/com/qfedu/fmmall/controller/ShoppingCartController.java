package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.service.ShoppingCartService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fancy
 * @create 2023-02-19 15:15
 */
@RestController
@CrossOrigin
@RequestMapping("/shopcart")
@Api(value = "提供购物车业务相关接口",tags = "购物车管理")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    @ApiOperation("添加购物车信息接口")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart cart,@RequestHeader("token") String token){
        ResultVO resultVO = shoppingCartService.addShoppingCart(cart);
        return resultVO;
    }

    @GetMapping("/list")
    @ApiOperation("查看购物车信息接口")
    @ApiImplicitParam(dataType = "int",name = "userId",value = "用户Id",required = true)
    public ResultVO listShoppingCartByUserId(Integer userId,@RequestHeader("token") String token){
        ResultVO resultVO = shoppingCartService.listShoppingCartByUserId(userId);
        return resultVO;
    }


    @PutMapping("/update/{cid}/{cnum}")
    @ApiOperation("修改购物车信息接口")
    public ResultVO updateNum(@PathVariable("cid") Integer cartId,
                              @PathVariable("cnum") Integer cartNum,
                              @RequestHeader("token") String token){
        ResultVO resultVO = shoppingCartService.updateCartNum(cartId, cartNum);
        return  resultVO;
    }


    @GetMapping("/listbycids")
    @ApiImplicitParam(dataType = "String",name = "cids", value = "选择的购物车记录的id",required = true)
    public ResultVO listByCids(String cids, @RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.listShoppingCartsByCids(cids);
        return resultVO;
    }

}
