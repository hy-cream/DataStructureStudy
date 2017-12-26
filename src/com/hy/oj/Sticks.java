package com.hy.oj.search;

import java.util.Scanner;

/**
 * 典型深度搜索，注重剪枝技巧
 * 北大oj-1011题
 * 题目大意：
 * 给一些任意长度的被分割后的木棍，组合成相同长度的原始木棍，求得组成后的木棍长度最短
 * 例：
 * 9
 * 15 3 2 11 4 1 8 8 8
 * result：20
 * 深度搜索+剪枝优化
 * 1.initLen必须除尽sumLen
 * 2.组成后的木棍长度应该在 [initLen,sumLen-initLen] 剩下木棍总和大于或者等于初始化木棍的长度
 * 3.如果某根木棍无法组合，那么跳过后面与其相等的木棍
 * 4.如果作为初始长度得木棍第一轮都无法组合，说明该长度就是失败的。
 *
 * 总结：在写深搜时不必要纠结人为的组合方式，要去发现大方向，不能一开始就纠结具体细节部分
 */
public class Sticks {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int num=0;
        while(scan.hasNext()){
            num=scan.nextInt();
            if(num==0) {
                break;
            }
            int[] sticksArr=new int[num];
            boolean[] visit=new boolean[num];
            int sum=0;
            for (int i=0;i<num;i++){
                sticksArr[i]=scan.nextInt();
                sum+=sticksArr[i];
                visit[i]=false;
            }
           // System.out.println(sum);

            quickSort(sticksArr,0,sticksArr.length-1);

          /*  for(int j=0;j<sticksArr.length;j++){
                System.out.print(sticksArr[j]+"->");
            }*/

           // System.out.println("-----------");
            int initLen=sticksArr[0];//最大的木棍最为初始值
            boolean flag=true;
            for(int i=initLen; i<=sum-initLen; i++){//截枝2
               // System.out.println("当前的initLen是："+i+"--"+sum%i);
                if(sum%i==0 && dfs(sticksArr,visit,i,0,0,0)){//截枝1
                    System.out.println(i);
                    flag=false;
                    break;
                }
            }
            if (flag){
                System.out.println(sum);
            }

        }
        scan.close();
    }

    private static boolean dfs(int[] arr, boolean[] visit, int initLen, int currentLen, int pos, int stickNum) {//pos当前搜索的位置，snum当前使用的木棍的数量，sindex当前是第几根木棍

        if (stickNum == arr.length) {//所有的木棍都用完了
            return true;
        }

        int temp = -1;//截枝3，记录与当前位置的木棍长度

        for (int k = pos; k < arr.length; k++) {

            if (visit[k] || arr[k] == temp) {

                //当前的木棍长度等于上一次失败木棍的长度，则不搜索
                continue;
            }

            visit[k] = true;
            if (arr[k] + currentLen < initLen) { //
                //当前总长度小于目标值，继续搜索
                //System.out.println("arr[k] + currentLen < initLen---" + arr[k] + "---" + currentLen);

                if (dfs(arr, visit, initLen, arr[k] + currentLen, k, stickNum+1)) {
                    return true;
                } else {
                    temp = arr[k];
                }
            } else if (arr[k] + currentLen == initLen) {
                //System.out.println("arr[k] + currentLen == initLen---" + arr[k] + "---" + currentLen);

                if (dfs(arr, visit, initLen, 0, 0, stickNum+1)) {
                    return true;
                } else {
                    temp = arr[k];
                }
            }

            visit[k] = false;

            if (currentLen == 0) { //剪枝4，构建新棒时，对于新棒的第一根棒子，在搜索完所有棒子后都无法组合
                //System.out.println("currentLen == 0");
                break;  //则说明该棒子无法在当前组合方式下组合，不用往下搜索(往下搜索会令该棒子被舍弃)，直接返回上一层
            }
        }
        //System.out.println("当前无法找到合适的木棍,返回上一个状态");
        return false; //一直没有找到合适的值与initLen值相等，则说明失败

    }
    private static void quickSort(int[] arr,int left,int right) {

        if(left > right){ //一定要先判断，否则会arr[left]会数组越界
            return;
        }

        int refer = arr[left];
        int i = left;
        int j = right;
        int t = 0;

        while (i != j){
            while(i < j && arr[j] <= refer) {//从右向左找一个比参照值小的数
                j--;
            }
            while(i < j && arr[i] >= refer) {//从左向右找一个比参照值大的数
                i++;
            }
            //如果存在，则交换他们的值
            if(i < j){
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        //基准数归位
        arr[left]=arr[i];
        arr[i]=refer;
        quickSort(arr,left,i-1);
        quickSort(arr,i+1,right);
    }
}


























