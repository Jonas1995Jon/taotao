package com.taotao.search.controller;

import com.taotao.common.result.TaotaoResult;
import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 下午8:46
 * @Description:
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /*
    * 导入所有数据到索引库
    * */
    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItems(){
        TaotaoResult result = null;
        try {
            result = itemService.importAllItems();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return result;
    }
}
