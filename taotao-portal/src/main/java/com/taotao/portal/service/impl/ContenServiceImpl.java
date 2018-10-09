package com.taotao.portal.service.impl;

import com.taotao.common.result.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用服务层查询列表
 * @Author: 黄运锐
 * @Date: 18-4-28 下午7:41
 * @Description:
 */

@Service
public class ContenServiceImpl implements ContenService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_URL}")
    private String REST_INDEX_URL;

    @Override
    public String getContentList() {
        String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_URL);
        //字符串装换成taotaoresult
        try{
            TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
            //取列表内容
            List<TbContent> list = (List<TbContent>) taotaoResult.getData();
            //创建一个jsp需要的pojo列表
            List<Map> resultMap = new ArrayList<>();
            for (TbContent tbContent:list){
                Map map = new HashMap();
                map.put("src",tbContent.getPic());
                map.put("height",240);
                map.put("width",670);
                map.put("srcB",tbContent.getPic2());
                map.put("widthB",550);
                map.put("heightB",240);
                map.put("href",tbContent.getUrl());
                map.put("alt",tbContent.getSubTitle());
                resultMap.add(map);
            }
            return JsonUtils.objectToJson(resultMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
