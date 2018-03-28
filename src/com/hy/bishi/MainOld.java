package com.hy.bishi;

import java.util.Scanner;

public class MainOld {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        String[] array = line.split("");
        boolean flag = true;
        for (int i = 0; i<array.length;i++){
            if (array[i].equals("1")){
                flag = false;
                break;
            }
        }
        if (flag){
            System.out.println("1");
        }else {
            quickSort(array,0,array.length-1);

            for (int i = 0;i<array.length;i++){
                System.out.print(array[i]+"");
            }
            String zhuhe = null;
            if (array[0].equals("0")){
                zhuhe = array[1]+array[0];
            }else {
                //能组合的最小数是前两位
                zhuhe=array[0]+array[1];
            }
            int[] temp = new int[Integer.parseInt(zhuhe)+1];

            for (int i =0;i<array.length;i++){
                temp[Integer.parseInt(array[i])] = 1;
            }
            flag = true;
            for (int i =1;i<Integer.parseInt(zhuhe);i++) {
                if (temp[i] == 0) {
                    System.out.println(i);
                    flag = false;
                    break;
                }
            }
            if (flag){
                line = line+"1";
                System.out.println(line);
            }


        }

    }

    public static void quickSort(String[] array, int left, int right){
        if (left > right){
            return;
        }
        int temp = Integer.parseInt(array[left]);
        int i = left;
        int j = right;
        while (i != j){
            // 顺序很重要，要从右往左找
            while (Integer.parseInt(array[j]) >= temp && i < j){
                j--;
            }

            while(Integer.parseInt(array[i]) <= temp && i < j){
                i++;
            }

            int t;
            if (i < j){
                t = Integer.parseInt(array[i]);
                array[i] = array[j];
                array[j] = String.valueOf(t);
            }
        }

        // 最终将基准数归位
        array[left] = array[i];
        array[i] = String.valueOf(temp);

        quickSort(array,left, i-1); // 继续处理左边的，这是一个递归的过程
        quickSort(array,i+1,right); // 继续处理右边的，这是一个递归的过程
    }

}
