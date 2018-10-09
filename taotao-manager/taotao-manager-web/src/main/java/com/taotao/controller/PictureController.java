package com.taotao.controller;

import com.taotao.service.PictureService;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: 黄运锐
 * @Date: 18-4-24 下午10:56
 * @Description:
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        String json = JsonUtils.objectToJson(result);
        System.out.println(json);
        return json;

    }

}
