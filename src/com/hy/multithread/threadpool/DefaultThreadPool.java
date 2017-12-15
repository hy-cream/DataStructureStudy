package com.hy.multithread.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by huyu on 2017/12/7
 * Description: 模拟实现一个线程池
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{
    //线程池最大线程数
    private static final int MAX_WORKER_NUMBERS = 10;
    //默认线程池的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //线程池的最小数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //job列表
    private final LinkedList<Job> jobs=new LinkedList<>();

    //worker列表
    private final List<Worker> workers=new ArrayList<>();

    //工作线程数量
    private int workNum=DEFAULT_WORKER_NUMBERS;

    //线程编号的生成
    private AtomicLong threadNum=new AtomicLong();

    public DefaultThreadPool(){
        initializeworks(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workNum=num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeworks(workNum);
    }


    public void initializeworks(int num){
        for(int i = 1; i <= num; i++){
            Worker worker=new Worker();
            workers.add(worker);
            Thread thread=new Thread(worker,"Thread-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if(job != null){
            synchronized (jobs){
                jobs.addLast(job);
                notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        //我认为这里应该是只要一个work中的running为false就可以了
        for (Worker worker:workers){
            worker.shutDown();
        }
    }

    @Override
    public void addWorks(int num) {
        synchronized (jobs){
            if(num + workers.size() > MAX_WORKER_NUMBERS){
                num=MAX_WORKER_NUMBERS-workers.size();
            }
            //初始化num数量的worker
            initializeworks(num);
            this.workNum+=num;
        }
    }

    @Override
    public void removeWorks(int num) {
        if(num > 0){
            synchronized (jobs){
                if(num >= workNum){
                    throw new IllegalArgumentException("beyond workNum");
                }
                //按照给定数量停止线程
                int count=0;
                while (count < num){
                    Worker worker=workers.get(count);
                    worker.shutDown();
                    count++;
                }
                workNum-=num;
            }

        }

    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    //工作者线程，从job列表中读取job,并且执行runnable的run()方法
    class Worker implements Runnable{
        private volatile boolean running=true;
        @Override
        public void run() {
            while (running){
                Job job=null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //感知到外部对workerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job=jobs.removeFirst();
                }
                if(job != null){
                    try {
                        job.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("job 执行过程中的异常");
                    }
                }
            }
        }
        //安全停止线程的一个方式
        public void shutDown(){
            running=false;
        }
    }
}
