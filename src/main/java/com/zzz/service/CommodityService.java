package com.zzz.service;

import com.zzz.model.vo.CommodityBookVo;

import java.util.List;

/**
 * Created by 胡胜钧 on 12/3 0003.
 */
public interface CommodityService {

    /**
     * 录入消费信息
     * @param commodityBookVos
     */
    void saveCommodityBook(List<CommodityBookVo> commodityBookVos);

}
