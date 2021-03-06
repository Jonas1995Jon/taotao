package com.taotao.rest.service.impl;

import com.taotao.common.result.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 下午12:00
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private JedisClient jedisClient;

    @Value("INDEX_CONTENT_REDIS_KEY")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public TaotaoResult syncContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
