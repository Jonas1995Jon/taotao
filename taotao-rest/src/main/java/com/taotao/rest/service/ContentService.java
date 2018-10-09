package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-28 下午3:47
 * @Description:
 */
public interface ContentService {
    List<TbContent> getContentList(long contentCId);
}
