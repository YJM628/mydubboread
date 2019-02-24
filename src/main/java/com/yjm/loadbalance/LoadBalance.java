package com.yjm.loadbalance;

import com.yjm.common.Invocation;
import com.yjm.common.URL;
import com.yjm.invoker.Invoker;

import java.util.List;

/**
 * com.yjm.loadbalance
 * Created by YJM6280 .
 */
public interface LoadBalance {
    <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation);
}
