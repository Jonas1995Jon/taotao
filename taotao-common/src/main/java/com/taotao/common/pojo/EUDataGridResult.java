package com.taotao.common.pojo;

import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-21 下午4:20
 * @Description:
 */
public class EUDataGridResult {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
