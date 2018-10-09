package com.taotao.rest.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理
 * @Author: 黄运锐
 * @Date: 18-4-28 下午3:49
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("{INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long contentCId) {
        try{
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCId + "");
            if (!StringUtils.isBlank(result)){
                //字符串转List
                List<TbContent> list = JsonUtils.jsonToList(result,TbContent.class);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //根据内容分类id查询内容
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCId);
        List<TbContent> list = contentMapper.selectByExample(example);

        //向缓存添加内容
        try{
            //List转换成字符串
            String cahceString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCId + "",cahceString);
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
