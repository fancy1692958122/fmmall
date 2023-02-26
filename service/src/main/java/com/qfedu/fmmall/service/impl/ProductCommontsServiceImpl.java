package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ProductCommentsMapper;
import com.qfedu.fmmall.entity.ProductComments;
import com.qfedu.fmmall.entity.ProductCommentsVO;
import com.qfedu.fmmall.service.ProductCommontsService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author fancy
 * @create 2023-02-18 22:22
 */
@Service
public class ProductCommontsServiceImpl implements ProductCommontsService {

    @Resource
    private ProductCommentsMapper productCommentsMapper;

    @Override
    public ResultVO ListCommontsByProductId(String productId,int pageNum,int limit) {
       //List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId(productId);

        //1.根据商品id查询总记录数
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        int count = productCommentsMapper.selectCountByExample(example);


        //2.计算总页数 （必须知道每页显示多少条 pageSize(limit)）
        int pageCount = count%limit==0? count/limit : count/limit+1;

        //3.查询当前页的数据（因为评论中需要用户信息 所以连表查询）
        //start 计算规则（pageNumber-1） * limt
        int start = (pageNum-1) * limit;
        List<ProductCommentsVO> list = productCommentsMapper.selectCommontsByProductId(productId,start,limit);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", new PageHelper<ProductCommentsVO>(count,pageCount,list));
        return resultVO;
    }

    @Override
    public ResultVO getCommontsByProductId(String productId) {
        //1.查询当前商品评价的总数
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        int total = productCommentsMapper.selectCountByExample(example);

        //2.查询好评评价数量
        criteria.andEqualTo("commType",1);
        int goodTotal = productCommentsMapper.selectCountByExample(example);

        //3.查询中评数量
        Example example1 = new Example(ProductComments.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("productId",productId);
        criteria1.andEqualTo("commType",0);
        int midTotal = productCommentsMapper.selectCountByExample(example1);

        //4.查询差评数量
        Example example2 = new Example(ProductComments.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("productId",productId);
        criteria2.andEqualTo("commType",-1);
        int badTotal = productCommentsMapper.selectCountByExample(example2);

        //5.计算好评率
        double percent = (Double.parseDouble(goodTotal+"")/Double.parseDouble(total+"")) * 100;
        String percentValue = (percent+"").substring(0,(percent+"").lastIndexOf(".")+3);

        HashMap<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("goodTotal",goodTotal);
        map.put("midTotal",midTotal);
        map.put("badTotal",badTotal);
        map.put("percent",percentValue);

        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", map);
        return resultVO;
    }


}
