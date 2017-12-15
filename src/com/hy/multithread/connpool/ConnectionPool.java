package com.hy.multithread.connpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by huyu on 2017/12/7
 * Description: 模拟连接池
 */
public class ConnectionPool {
    private LinkedList<Connection> pool=new LinkedList<>();

    public ConnectionPool(int initialSize){
        if(initialSize > 0){
            for(int k = 0; k < initialSize; k++){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    //获取连接（超时）
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //完全超时
            if (mills <= 0){
                while (pool.isEmpty()){
                    wait();
                }
                return pool.removeFirst();
            }else {
                long future=System.currentTimeMillis()+mills;
                long remaining=mills;
                while (pool.isEmpty() && remaining > 0){
                    wait();
                    remaining=future-System.currentTimeMillis();
                }
                Connection result=null;
                if(!pool.isEmpty()){
                    result= pool.removeFirst();
                }
                return result;
            }
        }
    }

    //释放连接
    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                //连接池归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();//通知
            }
        }
    }
}



















