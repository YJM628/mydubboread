package com.yjm.directory;

import com.yjm.common.Invocation;
import com.yjm.common.URL;
import com.yjm.invoker.Invoker;
import com.yjm.route.Router;

import java.util.List;

/**
 * com.yjm.directory
 * Created by YJM6280 .
 */
public abstract class AbstractDirectory<T> implements   IDirectory<T> {
    private  List<Router> routers;
    private  final  URL url;

    protected AbstractDirectory(List<Router> routers, URL url) {
        this.routers = routers;
        this.url = url;
    }
    public List<Invoker<T>> list(Invocation invocation) {
        List<Invoker<T>> invokers = dolist(invocation);
        List<Router> routers = this.routers;
        for (Router router : routers) {
            try {
                invokers = router.route(invokers, url, invocation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invokers;
    }

    protected abstract List<Invoker<T>> dolist(Invocation invocation);
}
