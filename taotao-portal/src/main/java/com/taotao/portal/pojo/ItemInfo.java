package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * @Author: 黄运锐
 * @Date: 18-4-30 下午2:36
 * @Description:
 */
public class ItemInfo extends TbItem {
    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
