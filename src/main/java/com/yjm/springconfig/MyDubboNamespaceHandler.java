package com.yjm.springconfig;

import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * com.yjm.springconfig
 * Created by YJM6280 .
 */
public class MyDubboNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
         registerBeanDefinitionDecorator("provider", (BeanDefinitionDecorator) new MyDubboBeanDefinitionParser(ProviderConfig.class,true));
    }
}
