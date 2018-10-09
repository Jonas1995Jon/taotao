package com.taotao.portal.controller;

import com.taotao.common.result.TaotaoResult;
import com.taotao.portal.service.ContenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 黄运锐
 * @Date: 18-4-26 下午8:54
 * @Description:
 */
@Controller
public class IndexController {

    @Autowired
    private ContenService contenService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        String adJson = contenService.getContentList();
        model.addAttribute("ad1",adJson);
        return "index";
    }

    /*
    * 文本乱码问题
    * */
    @RequestMapping(value = "/httpclient/post",method = RequestMethod.POST,produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String testPost(String username,String password){
        return "hello world"+username+"："+password;
    }
}
