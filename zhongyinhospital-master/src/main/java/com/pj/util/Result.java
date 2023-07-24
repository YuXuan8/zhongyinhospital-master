package com.pj.util;

public class Result {
    private Integer status;
    //状态码 ， //0--失败， 1--成功
    private long total;
    //返回数据的总长度
    private Object data;
    //返回的数据
    private Object rows;
    //bootStrapTable返回的数据
    private String message;
    public static final Integer RESPONSE_SUCCESS = 1; //成功
    public static final Integer RESPONSE_FAIL = 0;//失败


    public Result() {
    }

    public Result(Integer status, long total, Object data, Object rows, String message, Integer RESPONSE_SUCCESS, Integer RESPONSE_FAIL) {
        this.status = status;
        this.total = total;
        this.data = data;
        this.rows = rows;
        this.message = message;
    }

    /**
     * 获取
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取
     * @return total
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 获取
     * @return rows
     */
    public Object getRows() {
        return rows;
    }

    /**
     * 设置
     * @param rows
     */
    public void setRows(Object rows) {
        this.rows = rows;
    }

    /**
     * 获取
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "Result{status = " + status + ", total = " + total + ", data = " + data + ", rows = " + rows + ", message = " + message + ", RESPONSE_SUCCESS = " + RESPONSE_SUCCESS + ", RESPONSE_FAIL = " + RESPONSE_FAIL + "}";
    }
}
