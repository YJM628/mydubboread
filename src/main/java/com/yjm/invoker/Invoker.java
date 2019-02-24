package com.yjm.invoker;

import com.yjm.common.Invocation;
import com.yjm.common.Result;
import com.yjm.common.URL;

/**
 * com.yjm.invoker
 * Created by YJM6280 .
 */
public interface Invoker<T> {
    Class<T> getInterface();
    Result invoke(Invocation invocation);
    URL getUrl();
}
