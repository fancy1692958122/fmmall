package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.IndexImgMapper;
import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fancy
 * @create 2023-02-17 20:10
 */
@Service("IndexImgService")
public class IndexImgServiceImpl implements IndexImgService {

    @Resource
    private IndexImgMapper indexImgMapper;

    @Override
    public ResultVO listIndexImgs() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        if(indexImgs.size() == 0){
            return new ResultVO(ResStatus.NO,"fail",null);
        }else{
            return new ResultVO(ResStatus.OK,"success",indexImgs);
        }
    }


}
