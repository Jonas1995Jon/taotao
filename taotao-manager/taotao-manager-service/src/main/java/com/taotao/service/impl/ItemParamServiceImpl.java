package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数模板管理
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月5日下午2:36:57
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		if (list != null && list.size() > 0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page,rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

}