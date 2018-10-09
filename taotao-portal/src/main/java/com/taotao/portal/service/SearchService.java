package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * @Author: 黄运锐
 * @Date: 18-4-30 上午9:58
 * @Description:
 */
public interface SearchService {
    SearchResult search(String queryString,int page);
}
