package com.taotao.rest;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 下午7:40
 * @Description:
 */
public class SolrJTest {
    @Test
    public void addDocument() throws Exception{
        //创建一个链接
        SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8080/solr");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("item_title","测试商品adsfa");
        document.addField("item_price",123456);
        document.addField("id","test001");
        solrServer.add(document);


        //提交请求
        solrServer.commit();
    }

    @Test
    public void delelteDocument()throws Exception{
        //创建一个链接
        SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8080/solr");
//        solrServer.deleteById("test001");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }
}
