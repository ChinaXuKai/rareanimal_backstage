package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guangyou.rareanimal_backstage.mapper.OpinionMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Opinion;
import com.guangyou.rareanimal_backstage.pojo.vo.OpinionVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.OpinionService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 22:55
 */
@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private CopyUtils copyUtils;

    @Override
    public PageDataVo<OpinionVo> getOpinionsByPage(PageDto pageDto) {
        PageDataVo<OpinionVo> pageDataVo = new PageDataVo<>();
        List<Opinion> opinionList = opinionMapper.getOpinionsByPage(pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());
        List<OpinionVo> opinionVoList = copyUtils.opinionListCopy(opinionList);
        pageDataVo.setPageData(opinionVoList);
        //设置 数据库中用户意见总数（total）、每页显示数量（size）、当前第几页（current）、总共有多少页数据（pages）
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());
        int total = opinionMapper.selectCount(new LambdaQueryWrapper<Opinion>().eq(Opinion::getIsDelete,0)).intValue();
        pageDataVo.setTotal(total);
        int isRemainZero = total%pageDto.getPageSize();
        if (isRemainZero != 0){
            pageDataVo.setPages( (total/pageDto.getPageSize()) + 1);
        }else {
            pageDataVo.setPages( total/pageDto.getPageSize() );
        }
        return pageDataVo;
    }
}
