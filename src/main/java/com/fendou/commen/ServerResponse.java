package com.fendou.commen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用泛型来定义所有的返回数据
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    //成功与否的状态码
    private int status;
    /**
     * 返回的数据，json或者字符串
     */
    private T data;
    //提示信息
    private String msg;


    //getter  setter方法


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //提供构造方法
    private ServerResponse() {
    }

    private ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private ServerResponse(int status) {
        this.status = status;
    }


    //提供方法供外部使用

    /**
     * 成功时的方法
     * @return
     */
    public static ServerResponse ResponseWhenSuccess(){
        return new ServerResponse(ResponseCode.CODE_SUCCESS);
    }
    public static <T> ServerResponse ResponseWhenSuccess(T data){
        return new ServerResponse(ResponseCode.CODE_SUCCESS,data);
    }
    public static <T> ServerResponse ResponseWhenSuccess(T data,String msg){
        return new ServerResponse(ResponseCode.CODE_SUCCESS,data,msg);
    }


    /**
     * 出现错误时的方法
     */


    public static ServerResponse ResponseWhenError(){
        return new ServerResponse(ResponseCode.CODE_ERROR);
    }
    public static ServerResponse ResponseWhenError(String msg){
        return new ServerResponse(ResponseCode.CODE_ERROR,msg);
    }

    public static ServerResponse ResponseWhenError(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse ResponseWhenError(int status,String msg){
        return new ServerResponse(status,msg);
    }

    /**
     *
     * 判断接口是否正确返回
     */
    @JsonIgnore
    public boolean isSuccess(){

        return this.status == ResponseCode.CODE_SUCCESS;
    }

}
