package com.hy.datastructure.search;

/**
 * created by huyu8 on 2018/3/22
 * 深度优先搜索之---排列组合
 * 有m张卡片和n个容器
 * 有多少种排列组合方式
 */
public class PlayingCard {

    public static int[] result = new int[10];
    public static int n = 4; //表示有5个盒子
    public static int[] cards = {1,2,3,4,5};
    public static int[] book = new int[cards.length];

    public static void main(String[] args){

        dfs(1);
    }

    // 深度优先搜索
    public static void dfs(int step){ // step表示现在在第几个盒子

        // 如果在第n+1个盒子面前，则说明排好了
        if (step == n+1){
            for (int i = 0; i < n; i++){
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return; // 返回上一次调用dfs的地方
        }

        // 遍历所有的卡片
        for (int i = 0; i < cards.length; i++){

            if (book[i] == 0){ // 如果当前的卡片没有放过
                book[i] = 1;
                result[i] = cards[i];

                // 接下来走到写一个盒子
                dfs(step+1);
                // 从过上一个状态返回后，要重置状态
                book[i] = 0;
            }
        }


    }

}
