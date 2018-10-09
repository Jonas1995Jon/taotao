package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.result.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-28 上午10:32
 * @Description:
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> result = contentCategoryService.getCategoryList(parentId);
        return result;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(long parentId,String name){
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId,name);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(long parentId,long id){
        TaotaoResult result = contentCategoryService.deleteContentCategory(parentId,id);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id,name);
        return result;
    }
}
