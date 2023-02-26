package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ProductImgMapper;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.dao.ProductParamsMapper;
import com.qfedu.fmmall.dao.ProductSkuMapper;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author fancy
 * @create 2023-02-18 2:20
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductImgMapper productImgMapper;

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Resource
    private ProductParamsMapper productParamsMapper;

    @Override
    public ResultVO listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", productVOS);
        return resultVO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO getProductBasicInfo(String productId) {

        //1.商品基本信息
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        criteria.andEqualTo("productStatus",1); //状态为1 为上架
        List<Product> products = productMapper.selectByExample(example);
        if(products.size() > 0){
            //表示商品存在
            //2.商品图片
            Example example1 = new Example(ProductImg.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("itemId",productId);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);
            //3.商品套餐
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId",productId);
            criteria2.andEqualTo("status",1);
            List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);

            HashMap<String,Object> basicInfo = new HashMap<>();
            basicInfo.put("product",products.get(0));
            basicInfo.put("productImgs",productImgs);
            basicInfo.put("productSkus",productSkus);

            return new ResultVO(ResStatus.OK,"success",basicInfo);
        }else{
            return new ResultVO(ResStatus.NO,"查询的商品不存在",null);
        }

    }

    @Override
    public ResultVO getProductParamsById(String productId) {
        Example example = new Example(ProductParams.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        List<ProductParams> productParams = productParamsMapper.selectByExample(example);
        if(productParams.size()>0){
            return new ResultVO(ResStatus.OK,"success",productParams.get(0));
        }else{
            return new ResultVO(ResStatus.NO,"此商品可能为三无产品",null);
        }


    }

    @Override
    public ResultVO getProductsByCategoryId(int categoryId, int pageNum, int limit) {
        int start = (pageNum - 1) * limit;
        List<ProductVO> productVOS = productMapper.selectProductByCategoryId(categoryId,start,limit);
        
        //2。查询当前类别下的记录的总数
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);
        int count = productMapper.selectCountByExample(example);

        //3。计算总页数
        int pageCount = count%limit==0? count/limit : count/limit+1;

        //4.封装返回数据
        PageHelper<ProductVO> pageHelper = new PageHelper<>(count,pageCount,productVOS);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", pageHelper);
        return resultVO;
    }

    @Override
    public ResultVO listBrands(int categoryId) {
        List<String> brands = productMapper.selectBrandByCategoryId(categoryId);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", brands);
        return resultVO;
    }

    @Override
    public ResultVO searchProduct(String kw, int pageNum, int limit) {
        //1.查询搜索结果
        kw = '%'+kw+'%';
        int start = ( pageNum -1 ) * limit;
        List<ProductVO> productVOS = productMapper.selectProductByKeyword(kw, start, limit);
        //2.查询总记录数
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("productName",kw);
        int count = productMapper.selectCountByExample(example);
        //3.计算总页数
        int pageCount = count%limit==0? count/limit : count/limit+1;

        PageHelper<ProductVO> PageHelper = new PageHelper<>(count,pageCount,productVOS);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "SUCCESS", PageHelper);
        return resultVO;
    }

    @Override
    public ResultVO listBrands(String kw) {
        kw = '%'+kw+'%';
        List<String> brands = productMapper.selectBrandByKeyword(kw);
        return new ResultVO(ResStatus.OK,"success",brands);
    }
}
