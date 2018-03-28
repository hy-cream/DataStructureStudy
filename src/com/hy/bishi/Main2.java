package com.hy.bishi;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * created by huyu8 on 2018/3/24
 */
public class Main2 {

    /*public static int max = -1;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int m = Integer.parseInt(line.split(" ")[1]);
        line = scan.nextLine();
        TreeSet<Integer> aSet = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 <= o2 ? 1 : -1;
            }
        });
        String[] str = line.split(" ");
        for (int i = 0; i < n ; i++){
            aSet.add(Integer.parseInt(str[i]));
        }
        TreeSet<Integer> bSet = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 <= o2 ? 1 : -1;
            }
        });
        str = line.split(" ");
        for (int i = 0; i < n ; i++){
            bSet.add(Integer.parseInt(str[i]));
        }

        System.out.println(max);

    }

    public static void dfs(int step, TreeSet<Integer> aSet,TreeSet<Integer> bSet){
        if (step > max){
            max = step;
        }
        String aMid,nMid;
        if (aSet.size() == 1 &&){
            if (step < min){
                min = step;
                return;
            }
        }
        for (int i = 0;i <= 1; i++){
            if (i == 0){
                tm = s;
                ts = s+s;
            }else {
                ts = m + s;
                tm = m;
            }
            if (ts.length() > length){
                continue;
            }else {
                dfs(step+1,ts,tm,length);
            }
        }
    }*/
}
