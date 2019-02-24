package com.yjm.loadbalance;

import com.yjm.common.Constants;
import com.yjm.common.Invocation;
import com.yjm.common.URL;
import com.yjm.invoker.Invoker;

/**
 * com.yjm.loadbalance 模板类
 * Created by YJM6280 .
 */
public abstract  class AbstractLoadBalance {
    protected  int getWeight(Invocation invocation, Invoker invoker){
// 从 url 中获取权重 weight 配置值
        int weight = (int) invoker.getUrl().getMethodParameter(invocation.getMethodName(), Constants.WEIGHT_KEY, Constants.DEFAULT_WEIGHT);
        return  weight;
    };
}
