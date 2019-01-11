package com.yjm.springconfig;

import com.yjm.netty.NettyClient;
import com.yjm.proxy.ConsumerInvocationHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.lang.reflect.Proxy;

/**
 * com.yjm.springconfig
 * Created by YJM6280 .
 */
public class ConsumerConfig implements FactoryBean<Object> ,Serializable{
    private  String interfaceName;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public ConsumerConfig() {

    }

    @Nullable
    @Override
    public Object getObject() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> intefaceClazz = Class.forName(interfaceName, false, classLoader);
        NettyClient nettyClient = new NettyClient();
        ConsumerInvocationHandler invocationHandler = new ConsumerInvocationHandler(
                nettyClient, this);
        Class<?>[] intefaceClazzArray = new Class[] { intefaceClazz };
        Object consumerProxy = Proxy.newProxyInstance(classLoader,
                intefaceClazzArray, invocationHandler);
        return consumerProxy;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        try {
            return Class.forName(interfaceName).getClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
