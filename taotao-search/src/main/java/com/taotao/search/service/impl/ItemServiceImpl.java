package com.taotao.search.service.impl;

import com.taotao.common.result.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 下午8:34
 * @Description:
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult importAllItems() {
        try {
            List<Item> list = itemMapper.getItemList();
            //将商品信息写入索引库
            for (Item item:list){
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",item.getId());
                document.addField("item_title",item.getTitle());
                document.addField("item_sell_point",item.getSell_point());
                document.addField("item_price",item.getPrice());
                document.addField("item_category_name",item.getCategory_name());
                document.addField("item_image",item.getImage());
                document.addField("item_desc",item.getItem_desc());
                //写入索引库
                solrServer.add(document);
            }
            //提交数据
            solrServer.commit();

        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }

}
