package com.taotao.common.pojo;

/**
 * easyUI树型节点
 * @Author: 黄运锐
 * @Date: 18-4-21 下午5:20
 * @Description:
 */
public class EUTreeNode {
    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
