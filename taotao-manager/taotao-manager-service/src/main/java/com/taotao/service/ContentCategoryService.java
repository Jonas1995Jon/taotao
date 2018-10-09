package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.result.TaotaoResult;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-28 上午10:25
 * @Description:
 */
public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);

    TaotaoResult insertContentCategory(long parentId,String name);

    TaotaoResult deleteContentCategory(long parentId, long id);

    TaotaoResult updateContentCategory(long id, String name);
}
