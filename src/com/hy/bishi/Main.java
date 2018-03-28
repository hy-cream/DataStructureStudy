package com.hy.bishi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * created by huyu8 on 2018/3/24
 */
public class Main {

    public static int result = 0;
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        String line = scan.nextLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int m = Integer.parseInt(line.split(" ")[1]);
        line = scan.nextLine();
        String[] str = line.split(" ");
        List<Integer> number = new ArrayList<>(n);
        for(int i = 0;i<n;i++){
            number.add(Integer.parseInt(str[i]));
        }
        Collections.sort(number);
        int iFlag = -1;
        int jFlag = -1;
        for (int i = 0;i < n-1;i ++){
            if (iFlag == number.get(i)){
                continue;
            }
            iFlag = number.get(i);
            for (int j = i+1; j < n;j++){
                if (jFlag == number.get(j)){
                    continue;
                }
                    jFlag = number.get(j);
                    if (number.get(j)-number.get(i) == m){
                        result++;
                    }else if(number.get(j)-number.get(i) > m ){
                        break;
                    }
            }
        }

        System.out.println(result);
    }

    // 深度优先搜索
    public static void dfs(int step,int m,List<Integer> number,int[] book,int[] temp,int flag){ // step表示现在在第几个盒子

        if (step == 2){
            if (temp[1] - temp[0] == m){
                result++;
            }
            return;
        }

        // 遍历所有的卡片
        for (int i = flag+1; i < number.size(); i++){
            if (step == 0){
                flag = i;
            }
            if (book[number.get(i)] == 0 ){ // 如果当前的卡片没有放过
                book[number.get(i)] = 1;
                temp[step] = number.get(i);

                // 接下来走到写一个盒子
                dfs(step+1,m, number,book,temp,flag);
                // 从过上一个状态返回后，要重置状态
                //book[number.get(i)] = 0;
            }
        }


    }
}
