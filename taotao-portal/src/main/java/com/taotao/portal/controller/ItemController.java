package com.taotao.portal.controller;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.UnsupportedEncodingException;

/**
 * 商品详情页面展示
 * @Author: 黄运锐
 * @Date: 18-4-30 下午2:15
 * @Description:
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String getItem(@PathVariable Long itemId, Model model){
        ItemInfo item = itemService.getItemById(itemId);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId){
        String result  = itemService.getItemDescById(itemId);
        if (result != null){
            try {
                result = new String(result.getBytes("iso8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping(value = "/item/param/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId){
        String string  = itemService.getItemParamById(itemId);
        return string;
    }
}
