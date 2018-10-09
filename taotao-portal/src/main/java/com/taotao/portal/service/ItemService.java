package com.taotao.portal.service;


import com.taotao.portal.pojo.ItemInfo;

/**
 * @Author: 黄运锐
 * @Date: 18-4-30 下午2:18
 * @Description:
 */
public interface ItemService {
    ItemInfo getItemById(long itemId);

    String getItemDescById(long itemId);

    String getItemParamById(long itemId);
}
