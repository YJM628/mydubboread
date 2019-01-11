package com.yjm.remote;

import java.util.concurrent.atomic.AtomicLong;

/**
 * com.yjm.remote
 * Created by YJM6280 .
 */
public class Request {
    private static final AtomicLong INVOKE_ID = new AtomicLong(0);

    private long id;

    //接口名称
    private String interfaceName;
    //方法名称
    private String methodName;
    //参数类型
    private Class<?>[] parameterTypes;
    //参数值
    private Object[] args;
    //返回值类型
    private Class<?> returnType;

    public Request() {
        this.id = INVOKE_ID.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
