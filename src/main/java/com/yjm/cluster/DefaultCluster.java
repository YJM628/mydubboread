package com.yjm.cluster;

import com.yjm.directory.IDirectory;
import com.yjm.invoker.Invoker;

/**
 * com.yjm.cluster
 * Created by YJM6280 .
 */
public class DefaultCluster implements  ICluster {

    public <T> Invoker<T> join(IDirectory<T> directory) {
        return null;
    }
}
