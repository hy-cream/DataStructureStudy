package com.hy.oj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * created by huyu8 on 2018/3/15
 * 二分算法之求区间的上下界
 * 题目：
 * 数字在排序数组中出现的次数
 * 思路：
 * 求数字的上下界，时间复杂度是O(logn)
 */
public class BinaryAlgor {

    public static void main(String[] args){
        Scanner scan =new Scanner(System.in);
        String line = scan.nextLine();
        int sum = Integer.parseInt(line.split(" ")[0]);
        int target = Integer.parseInt(line.split(" ")[1]);
        line = scan.nextLine();
        String[] strs = line.split(" ");
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < sum; i++){
            numList.add(Integer.parseInt(strs[i]));
        }
        System.out.println(getNumInList(numList,target));
        scan.close();

    }
    public static int getNumInList(List<Integer> list,int target){
        int left, right;
        int pre = 0, last = 0;
        int mid = 0;
        // 求左边界
        // 这里要注意，right = list.size()而不是list.size()-1
        // 因为是从左端开始移动的，如果当前不存在目标数字，那么他的左边界应该是大于右边界，最终是在 l = r = list.size()
        for (left = 0,right = list.size(); left < right; ){
            mid = (left + right) / 2;
            if(list.get(mid) >= target){
                right = mid; // right只是用来缩小范围
            }else {
                left = mid + 1; //left是移动来寻找左边界的
            }
        }
        System.out.println("left-->"+left+"--right-->"+right);
        pre = left;
        System.out.println("pre-->"+pre);

        //右边界,向下取整，取得mid整数应该是去掉小数点的最大值
        //left = -1,是因为如果当前不存在比目标数字小的数，而且不存在目标数字，那么最終left = right = -1
        for ( left = -1, right = list.size()-1; left < right; ){
            mid = (left + right + 1) / 2;
            if(list.get(mid) <= target){
                left = mid; //left用来缩小范围
            }else {
                right = mid-1;
            }
        }
        System.out.println("left-->"+left+"--right-->"+right);
        last = right;
        System.out.println("last-->"+last);

        return last-pre+1;
    }
}
