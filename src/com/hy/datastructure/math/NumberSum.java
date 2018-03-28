package com.hy.datastructure.math;

import java.util.ArrayList;

/**
 * created by huyu8 on 2018/3/28
 * 求和为sum的连续整数序列
 * 题解1：
 * 连续正整数的和为sum,假设长度为n,则平均值是 Sum/n
 * n为奇数时，序列的中间值就是平均值，则满足 (sum % n == 0 && n & 1 == 1) 位与运算，只有两个非0的数与才为1
 * n为偶数时，中间的两个值才为平均值，则满足(sum % n)*2 == n
 * n>=2, 由等差数列求和公式可得，(1+n)*n/2 = sum ,则只需要计算 n = 2s开根号
 *
 * 题解2：
 * 头尾指针法
 * 用两个数small和big分别表示最小值和最大值，初始化 small==1 big==2,
 * 如果small到big的序列大于sum,则移动small的指针，去掉最小值，
 * 小于sum,移动big
 * 限制条件：small <= (sum+1)/2, 长度至少为 2
 */
public class NumberSum {

    public static void main(String[] args){
        int sum = 15;
       /* ArrayList<ArrayList<Integer>> result = solutionOne(sum);
        for (int i = 0;i<result.size();i++){
            ArrayList<Integer> list = result.get(i);
            for (int j = 0; j < list.size(); j++){
                if (j != 0){
                    System.out.print(",");
                }
                System.out.print(list.get(j));
            }
            System.out.println();
        }*/
       solutionTwo(15);
    }

    public static ArrayList<ArrayList<Integer>> solutionOne(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int n = (int) Math.sqrt(2 * sum); n >= 2; n--) {
            if (sum % n == 0 && (n & 1) == 1 || (sum % n) * 2 == n) {
                ArrayList<Integer> list = new ArrayList<>();
                for(int i = 0, k =(sum/n)-(n-1)/2 ; i < n; k++,i++){
                    list.add(k);
                }
                result.add(list);
            }

        }
        return result;
    }

    public static void solutionTwo(int sum) {
        if (sum < 3){
            return;
        }
        int small = 1, big = 2;
        int mid = (sum+1)/2;
        int num = small+big;
        while(small < mid){
            if (num == sum){
                System.out.println(small +" to "+ big); // 简单输出下
            }
            while (num > sum){
                num -= small;
                small++;

                if (num == sum){
                    System.out.println(small +" to "+ big); // 简单输出下
                }
            }
            big++;
            num+=big;
        }
    }
}
