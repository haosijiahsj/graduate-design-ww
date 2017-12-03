package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.CommodityBookRepository;
import com.zzz.model.po.CommodityBookPo;
import com.zzz.model.vo.CommodityBookVo;
import com.zzz.service.CommodityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 胡胜钧 on 12/3 0003.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommodityServiceImpl implements CommodityService {

    private CommodityBookRepository commodityBookRepository;

    @Override
    public void saveCommodityBook(List<CommodityBookVo> commodityBookVos) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(commodityBookVos), "入参commodityBookVos不能为空！");

        commodityBookVos.forEach(commodityBookVo -> {
            CommodityBookPo commodityBookPo = new CommodityBookPo();
            BeanUtils.copyProperties(commodityBookVo, commodityBookPo);
            commodityBookRepository.save(commodityBookPo);
        });
    }

}
