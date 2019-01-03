package com.yjm.route;

import com.yjm.common.Invocation;
import com.yjm.common.URL;
import com.yjm.invoker.Invoker;

import java.util.List;

/**
 * com.yjm.route
 * Created by YJM6280 .
 */
public interface Router {
    <T> List<Invoker<T>>  route(List<Invoker<T>> invokers, URL url, Invocation invocation);
}
