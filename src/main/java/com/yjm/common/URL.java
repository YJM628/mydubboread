package com.yjm.common;

import java.util.Map;

/**
 * com.yjm.common
 * Created by YJM6280 .
 * 统一的请求参数
 * protocol:ip:端口?key=value&key=value
 * 如:dubbo:127.0.0.1:8080?name='张三'&age=18
 */
public class URL {
    private  String protocol;
    private  String ip;
    private  Integer port;
    private Map<String,Object> params;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
