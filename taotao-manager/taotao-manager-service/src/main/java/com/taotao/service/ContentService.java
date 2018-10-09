package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbContent; /**
 * @Author: 黄运锐
 * @Date: 18-4-28 上午11:55
 * @Description:
 */
public interface ContentService {
    TaotaoResult insertContent(TbContent content);

    EUDataGridResult getContentList(Integer page, Integer rows, long categoryId);

    TaotaoResult deleteContent(long ids);
}
