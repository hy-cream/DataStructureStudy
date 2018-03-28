package com.hy.datastructure.math;

import java.util.HashMap;
import java.util.Map;

/**
 * created by huyu8 on 2018/3/28
 * 求解和为k的连续区间
 * 给定一个数组，其中有正数也有负数，求和为k的区间 [i，j]
 * 题解：
 * 前缀和+map优化
 */
public class KNumberSum {

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6};
        long[] sum = new long[100000];
        int k = 10;
        Map<Long,Integer> map = new HashMap<>();

        //前缀和
        for (int i = 0; i < arr.length; i++){
            sum[i+1] = sum[i]+arr[i];
            map.put(sum[i+1],i+1);
        }

        Integer j = null;
        for (int i = 0; i <= arr.length; i++){
            j = map.get(sum[i] + k);
            if(j != null && j > 0){ // 存在一个j,让 sum[j]-sum[i] = k 成立
                System.out.println((i+1)+" "+j);
            }
        }

    }

}
