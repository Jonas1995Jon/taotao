package com.taotao.rest.service;

import com.taotao.common.result.TaotaoResult;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 上午11:59
 * @Description:
 */
public interface RedisService {
    TaotaoResult syncContent(long contentCid);
}
