package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 * @Author: 黄运锐
 * @Date: 18-4-21 下午5:23
 * @Description:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper mapper;

    @Override
    public List<EUTreeNode> getCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = mapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();

        for (TbItemCat tbItemCat:list){
            EUTreeNode node = new EUTreeNode();
            node.setId(tbItemCat.getId());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            node.setText(tbItemCat.getName());
            resultList.add(node);
        }

        return resultList;
    }
}
