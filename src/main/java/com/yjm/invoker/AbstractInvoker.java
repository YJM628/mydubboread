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
public abstract  class AbstractInvoker<T> implements Invoker<T> {
    private IDirectory<T> directory;
    public AbstractInvoker(IDirectory<T> directory) {
        this.directory = directory;
    }

    public Result invoke(Invocation invocation) {
        LoadBalance loadBalance = null;
        List<Invoker<T>> invokers = list(invocation);
        Result result = doInvoke(invokers, invocation, loadBalance);
        return result;
    }

    protected abstract Result doInvoke(List<Invoker<T>> invokers, Invocation invocation, LoadBalance loadBalance);

    private List<Invoker<T>>  list(Invocation invocation) {
        List<Invoker<T>> list = directory.list(invocation);
        return  list;
    }
    public Class<T> getInterface() {
        return null;
    }
    public URL getUrl() {
        return directory.getUrl();
    }
}
