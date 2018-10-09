package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.common.result.TaotaoResult;

/**
 * @Author: 黄运锐
 * @Date: 18-4-21 下午2:58
 * @Description:
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(Integer page, Integer rows);

    TaotaoResult createItem(TbItem item, String desc,String itemParam) throws Exception;

}
