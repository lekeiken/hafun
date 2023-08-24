package org.hafunstar.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:响应的万能类
 */
@Component
public class ResponseData {

    private String message;
    private int code;
    private Map<String,Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public void putDataValue(String key, Object value) {
        data.put(key, value);
    }

}
