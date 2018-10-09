package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 * 商品类目管理
 * @Author: 黄运锐
 * @Date: 18-4-21 下午5:33
 * @Description:
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public Object getItemList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> result = itemCatService.getCatList(parentId);
        return result;
    }

}

