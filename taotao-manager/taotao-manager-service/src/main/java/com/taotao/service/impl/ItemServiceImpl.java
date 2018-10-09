package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import com.taotao.common.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-21 下午2:59
 * @Description:
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Value("${SEARCH_IMPORT_ALL_URL}")
    private String SEARCH_IMPORT_ALL_URL;
    /**
     * 查询单个商品
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(long itemId) {
//        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
//        根据条件查询
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list !=null && list.size() > 0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        TbItemExample tbItemExample = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> list = itemMapper.selectByExample(tbItemExample);
        EUDataGridResult result = new EUDataGridResult();
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception {
        //生成ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //存入数据库
        itemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId,desc);
        if (result.getStatus() != 200){
            throw  new Exception();
        }
        //添加规格参数
        TaotaoResult result1 = insertItemParamItem(itemId,itemParam);
        if (result1.getStatus() != 200){
            throw  new Exception();
        }
        try {
            String importString = HttpClientUtil.doGet(SEARCH_IMPORT_ALL_URL);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(importString,null);
            if (taotaoResult.getStatus() == 200){
                return TaotaoResult.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }



    /**
     * 添加商品描述
     * @param desc
     */
    private TaotaoResult insertItemDesc(Long itemId,String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    /*
    * 添加规格参数
    * */
    private TaotaoResult insertItemParamItem(Long itemId,String itemparam){
        //创建pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemparam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //插入数据
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }

}
