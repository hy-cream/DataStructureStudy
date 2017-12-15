package com.hy.multithread.threadpool;

/**
 * Created by huyu on 2017/12/7
 * Description:实现一个简单的
 */
public interface ThreadPool <Job extends Runnable>{

    void execute(Job job);    //执行一个任务

    void shutdown();    //关闭线程池

    void addWorks(int num); //增加工作者线程

    void removeWorks(int num);//移除工作者线程

    int getJobSize(); //获取正在等在执行的任务数量

}
