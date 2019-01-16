package com.lumia.lostfound.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应对象
 * @param <T>
 */
@Data
public class ResultResponse<T> implements Serializable {
    //判断是否成功
    private Integer status;
    //提示消息
    private String msg;
    //数据
    private T data;

    private ResultResponse(int status){
        this.status = status;
    }
    private ResultResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }
    private ResultResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    /**
     * 状态码枚举类
     */
    enum StatusCode{
        ERROR(0, "ERROR"),
        SUCCESS(1, "SUCCESS"),
        ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");
        private final int code;
        private final String desc;
        StatusCode(int code, String desc){
            this.code = code;
            this.desc = desc;
        }
        public int getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
        public static StatusCode getStatusCode(int code){
            for(StatusCode statusCode : StatusCode.values()){
                if(statusCode.getCode() == code){
                    return statusCode;
                }
            }
            return null;
        }
    }
    //判断是否成功
    public boolean isSuccess(){
        return this.status == StatusCode.SUCCESS.getCode();
    }

    //创建成功实例
    public static <T> ResultResponse<T> createBySuccess(){
        return new ResultResponse<T>(StatusCode.SUCCESS.getCode());
    }
    public static <T> ResultResponse<T> createBySuccessMessage(String msg){
        return new ResultResponse<T>(StatusCode.SUCCESS.getCode(), msg);
    }
    public static <T> ResultResponse<T> createBySuccessData(String msg, T data){
        return new ResultResponse<T>(StatusCode.SUCCESS.getCode(), msg, data);
    }
    //创建失败实例
    public static <T> ResultResponse<T> createByError(){
        return new ResultResponse<T>(StatusCode.ERROR.getCode());
    }
    public static <T> ResultResponse<T> createByErrorMessage(String msg){
        return new ResultResponse<T>(StatusCode.ERROR.getCode(), msg);
    }
    public static <T> ResultResponse<T> createByErrorData(String msg, T data){
        return new ResultResponse<T>(StatusCode.ERROR.getCode(), msg, data);
    }


}
