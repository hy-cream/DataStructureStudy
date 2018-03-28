package com.hy.datastructure.math;

/**
 * created by huyu8 on 2018/3/28
 * 递增的排序数组，求和为S的两个数字
 */
public class TwoNumberSum {

    public static void main(String[] args){
        int k = 15;
        int[] num = {1,4,5,9,10,11};
        for (int head = 0,tail = num.length-1; head < tail;){
            if (num[head]+num[tail] == k){
                System.out.println(num[head]+" "+num[tail]);
                break;
            }else if (num[head]+num[tail] < k){
                head++;
            }else {
                tail--;
            }
        }
    }

}
