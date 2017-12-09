package com.zzz.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzz.model.vo.CommodityVo;
import com.zzz.service.CommodityService;
import com.zzz.support.ResponseEntity;
@RestController
@RequestMapping("/commodity")
public class CommodityController {
	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("/getById")
	public ResponseEntity getById(Integer id){
		CommodityVo commodityVo=commodityService.getById(id);
		ResponseEntity responseEntity=new ResponseEntity();
		if(commodityVo == null){
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("查询失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("查询成功");
		responseEntity.setResult(commodityVo);
		return responseEntity;
	}
	@RequestMapping("/findAll")
	public ResponseEntity findAll(){
		List<CommodityVo> commodityVos=commodityService.findAll();
		ResponseEntity responseEntity=new ResponseEntity();
		if(CollectionUtils.isEmpty(commodityVos)){
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("查询失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("查询成功");
		responseEntity.setResult(commodityVos);
		return responseEntity;
	}
	@RequestMapping("/save")
	public ResponseEntity save(CommodityVo commodity){
		ResponseEntity responseEntity=new ResponseEntity();
		try {
			commodityService.save(commodity);
		} catch (Exception e) {
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("保存失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("保存成功");
		return responseEntity;
	}
	@RequestMapping("/update")
	public ResponseEntity update(CommodityVo commodity){
		ResponseEntity responseEntity=new ResponseEntity();
		try {
			commodityService.update(commodity);
		} catch (Exception e) {
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("更新失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("更新成功");
		return responseEntity;
	}
}
