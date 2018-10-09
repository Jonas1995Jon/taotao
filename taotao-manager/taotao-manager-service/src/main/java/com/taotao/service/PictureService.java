package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: 黄运锐
 * @Date: 18-4-24 下午10:22
 * @Description:
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
