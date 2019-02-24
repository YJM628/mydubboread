package com.yjm.loadbalance;

import com.yjm.common.Invocation;
import com.yjm.common.URL;
import com.yjm.invoker.Invoker;

import java.util.List;
import java.util.Random;

/**
 * com.yjm.loadbalance
 * Created by YJM6280 .
 */
public class RandomLoadBalance extends  AbstractLoadBalance implements  LoadBalance{
    private static Random random = new Random();
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        int sumWeight = 0;
        boolean sameWeight = true;
        int lenght =0;
        for (int i =0;i<invokers.size();i++) {
            int weight = getWeight(invocation,invokers.get(i));
            sumWeight = + weight;
            if(sameWeight && i>0 && weight!=getWeight(invocation,invokers.get(i-1))){
              sameWeight = false;
            }
        }

        if(sumWeight >0 && !sameWeight){
            for (int i = 0; i < lenght; i++) {
                int offset = random.nextInt(sumWeight);

                offset = offset - getWeight(invocation,invokers.get(i));

                if(offset <0){
                    return  invokers.get(i);
                }
                
            }
        }
        return invokers.get(random.nextInt(invokers.size()));
    }
}
