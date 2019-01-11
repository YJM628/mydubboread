package com.yjm.remote;

/**
 * com.yjm.remote
 * Created by YJM6280 .
 */
public class Response {
    //响应状态
    public static final byte SUCCESS = 0;
    /**
     * server side timeout.
     */
    public static final byte SERVER_TIMEOUT = -1;
    //默认成功
    private int status = SUCCESS;
    private long invokeId;
    public Response(long invokeId) {
       this.invokeId = invokeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(long invokeId) {
        this.invokeId = invokeId;
    }
}
