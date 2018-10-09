package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;


/**
 * @author rui
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(long ids) {
		TaotaoResult result = contentService.deleteContent(ids);
		return result;
	}

	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(Integer page, Integer rows, Integer categoryId) {

		EUDataGridResult result = contentService.getContentList(page,rows,categoryId);
		return result;
	}


}
