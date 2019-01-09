package com.yjm.springconfig;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

/**
 * com.yjm.springconfig
 * Created by YJM6280 .
 */
public class MyDubboBeanDefinitionParser implements BeanDefinitionParser{
    private MyDubboBeanDefinitionParser(Class<ProviderConfig> providerConfigClass, boolean b) {

    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return null;
    }
}
