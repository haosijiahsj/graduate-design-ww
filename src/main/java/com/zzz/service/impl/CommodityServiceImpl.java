package com.zzz.service.impl;

import com.google.common.base.Preconditions;
import com.zzz.dao.CommodityBookRepository;
import com.zzz.dao.CommodityRepository;
import com.zzz.model.po.CommodityBookPo;
import com.zzz.model.po.CommodityPo;
import com.zzz.model.po.RoomBookPo;
import com.zzz.model.vo.CommodityBookVo;
import com.zzz.model.vo.CommodityVo;
import com.zzz.service.CommodityService;
import com.zzz.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by on 12/3 0003.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityBookRepository commodityBookRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public void saveCommodityBook(List<CommodityBookVo> commodityBookVos) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(commodityBookVos), "入参commodityBookVos不能为空！");

        commodityBookVos.forEach(commodityBookVo -> {
            CommodityBookPo commodityBookPo = new CommodityBookPo();
            BeanUtils.copyProperties(commodityBookVo, commodityBookPo);
            commodityBookRepository.save(commodityBookPo);
        });
    }

    @Override
    public CommodityVo getById(Integer id) {
        CommodityPo commodityPo = commodityRepository.getById(id);
        if (commodityPo == null) {
            return null;
        }
        CommodityVo commodityVo = new CommodityVo();
        BeanUtils.copyProperties(commodityPo, commodityVo);
        return commodityVo;
    }

    @Override
    public List<CommodityVo> findAll() {
        List<CommodityPo> commodityPos = commodityRepository.findAll();
        if (CollectionUtils.isEmpty(commodityPos)) {
            return Collections.emptyList();
        }
        return commodityPos.stream()
                .map(commodityPo -> {
                    CommodityVo commodityVo = new CommodityVo();
                    BeanUtils.copyProperties(commodityPo, commodityVo);
                    return commodityVo;
                })
                .collect(Collectors.toList());

    }

    @Override
    public void save(CommodityVo commodity) {
        Preconditions.checkNotNull(commodity, "入参employeeVo不能为空！");
        CommodityPo commodityPo = new CommodityPo();
        BeanUtils.copyProperties(commodity, commodityPo);
        commodityRepository.save(commodityPo);
    }

    @Override
    public void update(CommodityVo commodity) {
        Preconditions.checkNotNull(commodity, "入参employeeVo不能为空！");
        commodityRepository.update(commodity.getId(), commodity.getCommodityName(), commodity.getPrice(), commodity.getStatus());
    }

    @Override
    public List<CommodityBookVo> findByRoomBook(Integer roomBook) {
        Preconditions.checkNotNull(roomBook, "入参roomBook不能为空！");

        List<CommodityBookPo> commodityBookPos = commodityBookRepository.findByRoomBook(roomBook);
        if (CollectionUtils.isEmpty(commodityBookPos)) {
            log.info("通过：{}没有查询到商品订单信息！", roomBook);
        }

        List<CommodityBookVo> commodityBookVos = ConvertUtils.convertPos2Vos(commodityBookPos, CommodityBookVo.class);
        return commodityBookVos.stream()
                .map(commodityBookVo -> {
                    CommodityVo commodityVo = new CommodityVo();
                    BeanUtils.copyProperties(commodityRepository.getById(commodityBookVo.getCommodity()), commodityVo);
                    commodityBookVo.setCommodityVo(commodityVo);
                    return commodityBookVo;
                })
                .collect(Collectors.toList());
    }

}
