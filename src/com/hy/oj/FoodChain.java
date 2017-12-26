package com.hy.oj;


import java.util.Scanner;

/**
 * Created by huyu on 2017/12/25
 * Description:POJ1182食物链
 * 带权并查集：
 * 当两个元素之间的关系可以量化，并且关系可以合并时，可以使用带权并查集来维护元素之间的关系。
 * 其中每个元素的权代表和祖先的某个关系，这种管关系如何合并，那么路径就如何压缩
 *
 */
public class FoodChain {

    private static int[] id = new int[50005];
    private static int[] rank = new int[100005];
    private static int n = 0;
    private static int k = 0;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        n = scan.nextInt(); //动物总数
        k = scan.nextInt(); //总共k句

        //初始化
        for(int j = 0; j <= n; j++){
            id[j] = j;
            rank[j] = 0;
        }

        for(int i = 1; i <= k; i++){
            int r = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            if(check(r,x,y)){ //不冲突
                //合并
                merge(r,x,y);
            }
        }

    }

    public static void merge(int r, int x, int y){

        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot != yRoot){
            id[xRoot] = yRoot; //这里应该有问题?待测试
            rank[xRoot] = (rank[yRoot] - rank[xRoot] + r + 3) % 3;
        }

    }

    public static boolean check(int r, int x, int y){
        //冲突条件1：x,y超过n
        if(x > n || y > n){
            return false;
        }
        //冲突条件2： x吃x
        if(r == 2 && x == y){
            return false;
        }

        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot == yRoot){
            return ((rank[x]-rank[y]) % 3 + 3) % 3 == r;
        } else {
            return true;
        }

    }


    public static int find(int x){
        if (id[x] == x){
            return x;
        }else {
            int temp = id[x];
            id[x] = find(id[x]);
            //路径压缩
            rank[x] = (rank[x] + rank[temp]) % 3;
            return id[x];
        }
    }
}
