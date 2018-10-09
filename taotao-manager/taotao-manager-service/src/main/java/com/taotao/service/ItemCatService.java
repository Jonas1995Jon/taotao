package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-21 下午5:22
 * @Description:
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);
}
