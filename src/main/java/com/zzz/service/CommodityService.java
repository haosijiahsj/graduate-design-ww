package com.zzz.service;


import com.zzz.model.vo.CommodityBookVo;
import com.zzz.model.vo.CommodityVo;
import java.util.List;



/**
 * Created by  on 12/3 0003.
 */
public interface CommodityService {

    /**
     * 录入消费信息
     * @param commodityBookVos
     */
    void saveCommodityBook(List<CommodityBookVo> commodityBookVos);
    
    CommodityVo getById(Integer id);
    
    List<CommodityVo> findAll();
    
    void save(CommodityVo commodity);
    
    void update(CommodityVo commodity);
}
