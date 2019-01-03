package com.yjm.directory;

import com.yjm.common.Invocation;
import com.yjm.common.URL;
import com.yjm.invoker.Invoker;

import java.util.List;

/**
 * com.yjm.directory
 * Created by YJM6280 .
 */
public interface IDirectory<T> {
    List<Invoker<T>> list(Invocation invocation);

    URL getUrl();
}
