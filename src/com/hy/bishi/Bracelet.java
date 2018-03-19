package com.hy.bishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * created by huyu8 on 2018/3/19
 * 头条2-18年笔试题1
 * 手串颜色
 * 思路： 将环形化成序列，然后再优化
 */
public class Bracelet {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String line;
        String[] arr = null;
        line = scan.nextLine();
        int n , m , c;
        arr = line.split(" ");
        n = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);
        c = Integer.parseInt(arr[2]);

        int[][] color = new int[n+m+1][51];

        for (int i = 1; i <= n; i++){
            line = scan.nextLine();
            arr = line.split(" ");
            int colorNum = Integer.parseInt(arr[0]);
            if (colorNum == 0){
                color[i][0] = 0; // 标志无色
            }else {
                color[i][0] = colorNum; // 有色的0位置代表有几种颜色
                for (int j = 1; j <= colorNum; j++){
                    color[i][j] = Integer.parseInt(arr[j]);
                }
            }
        }

        // 将环形换成序列，往后追加m个相同的珠子
        for (int i = 1; i <= m; i++){
            color[n+i] = color[i];
        }

        // 初始化
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i <= c; i++){
            colorList.add(0);
        }
        int[] book =new int[c+1];

        // 遍历n颗珠子
        for (int i = 1; i <= n; i++) {
            // 做优化
            if (i == 1) { //往后数m颗珠子
                for (int j = i; j < i+m; j++) {
                    if (color[j][0] == 0) { // 无色
                        continue;
                    } else { // 将颜色值放入List,并计数
                        for (int k = 1; k <= color[j][0]; k++) {
                            colorList.set(color[j][k], colorList.get(color[j][k]) + 1);
                        }
                    }
                }
            } else { // 当前只需要添加第m颗珠子的颜色
                for (int k = 1; k <= color[i + m -1][0]; k++) {
                    colorList.set(color[i + m -1][k], colorList.get(color[i + m -1][k]) + 1);
                }

            }
            // 判断颜色
            for (int p = 1; p <= c; p++){
                if (colorList.get(p) > 1){
                    book[p] = 1;
                }
            }

            // 将当前第一个珠子的颜色去除
            if (color[i][0] != 0){
                for (int k = 1; k <= color[i][0]; k++) {
                    colorList.set(color[i][k], colorList.get(color[i][k] - 1));
                }
            }
        }

        int result = 0;
        // 遍历book
        for (int k = 1; k <= c; k++){
            if (book[k] == 1){
                result++;
            }
        }
        System.out.println(result);

    }
}
