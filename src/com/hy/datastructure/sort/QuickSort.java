package com.hy.datastructure.sort;

/**
 * created by huyu8 on 2018/3/22
 * 快速排序
 * 时间复杂度是：N*logN
 * */
public class QuickSort {

    public static void main(String[] args){
        int[] array = {10,5,3,6,5,7,3,8,2};
        //quickSort(array,0,array.length-1);
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i]+" ");
        }
    }


}
