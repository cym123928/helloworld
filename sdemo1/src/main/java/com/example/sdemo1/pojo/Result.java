package com.example.sdemo1.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: 沐
 * @Date: 2021/11/27 21:39
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    int code;
    String message;
    T data;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public static<T> Result<T> success(T data)
    {
        Result result=new Result<>();
        result.code=0;
        result.message="成功！";
        result.data=data;
        return result;
    }
    public  void success(T data,String message)
    {
        this.code=0;
        this.message="成功！";
        this.data=data;
    }
    public void error(int code,String message)
    {
        this.code=code;
        this.message=message;

    }
}
