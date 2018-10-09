package com.taotao.search.service;

import com.taotao.common.result.TaotaoResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 下午8:33
 * @Description:
 */
public interface ItemService {
    TaotaoResult importAllItems() throws IOException, SolrServerException;
}
