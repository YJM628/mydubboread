package com.yjm.remote;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.yjm.remote
 * Created by YJM6280 .
 */
public class DefaultFuture {

    private Channel channel;

    private Request request;

    private long invokeId;

    private int timeout;

    private static  final long start = System.currentTimeMillis();

    private static  final Map<Long,DefaultFuture> FUTURES = new ConcurrentHashMap<Long, DefaultFuture>();

    private  static final  Lock lock = new ReentrantLock();

    private static  final Condition done = lock.newCondition();

    private volatile  Response response ;
    //启动线程 轮询是否接收到响应
    static {
        RemoteResponseReceiveScan remoteResponseReceiveScan = new RemoteResponseReceiveScan();
        Thread thread = new Thread(remoteResponseReceiveScan);
        thread.setDaemon(true);//设置为守护线程 主线程退出时也跟着退出
        thread.start();
    }
    public DefaultFuture(Channel channel, Request request, long invokeId,int timeout) {
        this.channel = channel;
        this.request = request;
        this.invokeId = request.getId();
        this.timeout = timeout;
        FUTURES.put(invokeId,this);
    }

    public Response get(int timeout){
        if(timeout < 0){
           timeout = 1000;
        }
        long start = System.currentTimeMillis();
        lock.lock();
        try {
            while(!isDone()){
                done.await(timeout, TimeUnit.MILLISECONDS);
                if(isDone() || start -System.currentTimeMillis()>timeout){
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return  response;
    }

    public  boolean isDone(){
        return  response!=null;
    }
    public  static  class  RemoteResponseReceiveScan implements Runnable{

        @Override
        public void run() {
            while(true){
                try {
                    for (DefaultFuture future:FUTURES.values()){
                        if(null == future){
                            continue;
                        }
                        //超时默认返回
                        if(start -System.currentTimeMillis() > future.getTimeout()){
                            System.out.println("超时返回");
                            Response response = new Response(future.getInvokeId());
                            response.setStatus(Response.SERVER_TIMEOUT);
                            DefaultFuture.receive(future.getChannel(),response);
                        }
                    }
                    TimeUnit.MICROSECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void receive(Channel channel,Response res){
        DefaultFuture defaultFuture = FUTURES.remove(res.getInvokeId());
        if(defaultFuture!=null){
            defaultFuture.doReceive(res);
        }
    }

    public  void doReceive(Response res){
        lock.lock();
        try {
            response = res;
            if(done!=null){
                done.signal();
            }

        } finally {
            lock.unlock();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public long getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(long invokeId) {
        this.invokeId = invokeId;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public long getStart() {
        return start;
    }

    public static Map<Long, DefaultFuture> getFUTURES() {
        return FUTURES;
    }
}
