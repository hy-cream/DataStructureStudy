package com.hy.multithread.threadpool;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by huyu on 2017/12/22
 * Description: 模拟银行流水处理的服务类
 */
class BankWaterServicee implements Runnable{
    /**
     * 创建四个屏障，处理完之后再执行该run方法
     */
    private CyclicBarrier barrier = new CyclicBarrier(4,this);
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(4,4,200,TimeUnit.SECONDS,workQueue);

    /**
     * 保存每个sheet计算出的银行流水结果*/
    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();


    public void count(){
        for(int i = 0; i < 4; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前的账户流水数据
                    sheetBankWaterCount.put(Thread.currentThread().getName(),5);
                    //计算完成则插入一个屏障
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        executor.shutdown();
    }

    @Override
    public void run() {
        int result = 0;
        //汇总每个银行流水的计算结果
        for(Map.Entry<String,Integer> entry : sheetBankWaterCount.entrySet()){
            result += entry.getValue();
        }
        System.out.println(result);
    }

  /*  public static void main(String[] args) {
        BankWaterService service = new BankWaterService();
        service.count();
    }*/
}
public class BankWaterService{
    public static void main(String[] args) {
        BankWaterServicee service = new BankWaterServicee();
        service.count();
        System.out.println("end");
    }
}