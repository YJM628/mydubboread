package com.yjm.invoker;

import com.yjm.common.Invocation;
import com.yjm.common.Result;
import com.yjm.common.URL;
import com.yjm.directory.IDirectory;
import com.yjm.loadbalance.LoadBalance;

import java.util.List;

/**
 * com.yjm.invoker
 * Created by YJM6280 .
 */
public class DefaultInvoker<T> extends  AbstractInvoker<T>{
    public DefaultInvoker(IDirectory<T> directory) {
        super(directory);
    }

    protected Result doInvoke(List<Invoker<T>> invokers, Invocation invocation, LoadBalance loadBalance) {
        Invoker<T> invoker = select(invokers,invocation,loadBalance);
        Result result = invoker.invoke(invocation);
        return  result;
    }

    private Invoker<T> select(List<Invoker<T>> invokers, Invocation invocation, LoadBalance loadBalance) {
        List<Invoker<T>> select = loadBalance.select(invokers, getUrl(), invocation);
        //Todo 具体的轮询算法后面再写
        return select.get(0);
    }
}
