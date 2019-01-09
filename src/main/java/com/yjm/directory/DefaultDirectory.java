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
public class DefaultDirectory<T> extends  AbstractDirectory<T>{

    protected DefaultDirectory(List<Router> routers, URL url) {
        super(routers, url);
    }

    protected List<Invoker<T>> dolist(Invocation invocation) {
        return null;
    }

    @Override
    public URL getUrl() {
        return null;
    }
}
