package com.yjm.proxy;

import com.yjm.netty.NettyClient;
import com.yjm.remote.Request;
import com.yjm.remote.Response;
import com.yjm.springconfig.ConsumerConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * com.yjm.proxy 客户端代理
 * Created by YJM6280 .
 */
public class ConsumerInvocationHandler implements InvocationHandler{
    private final NettyClient nettyClient;

    private final ConsumerConfig consumerConfig;

    public ConsumerInvocationHandler(NettyClient nettyClient, ConsumerConfig consumerConfig) {
        this.nettyClient = nettyClient;
        this.consumerConfig = consumerConfig;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();
        Class<?>[] parameterTypes = method.getParameterTypes();
        //1.建立连接
        nettyClient.connect("127.0.0.1",8011);
        Request request = new Request();
        request.setInterfaceName(consumerConfig.getInterfaceName());
        request.setMethodName(methodName);
        request.setParameterTypes(parameterTypes);
        request.setArgs(args);
        request.setReturnType(returnType);
        //2.发送并且获取响应
        Response response = nettyClient.sendAndGetResponse(request);
        return response;
    }
}
