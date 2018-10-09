package com.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-21 下午4:03
 * @Description:
 */
public class TestPageHelper {

    @Test
    public void testPageHelper(){
        //创建一个spring容器
        //从容器中获取mapper代理对象
        //执行查询，分页

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper mapper = context.getBean(TbItemMapper.class);

        TbItemExample example = new TbItemExample();
        PageHelper.startPage(10,10);
        List<TbItem> list = mapper.selectByExample(example);
        //
        for (TbItem tbItem:list){
            System.out.println(tbItem.getTitle());
        }

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total =  pageInfo.getTotal();
        System.out.println("商品总数："+total);
    }
}
