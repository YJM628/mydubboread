package com.yjm.cluster;

import com.yjm.directory.IDirectory;
import com.yjm.invoker.Invoker;

/**
 * com.yjm.cluster
 * Created by YJM6280 .
 * 集群容错
 */
public interface ICluster {
    //加入集群
    <T>Invoker<T> join(IDirectory<T> directory);
}
