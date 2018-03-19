package com.hy.bishi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * created by huyu8 on 2018/3/19
 * 头条2-18年笔试题1
 * 用户喜爱度
 * 思路： 用二分法求上下边界
 */
public class UserPrefer {
    public static void main(String[] a){

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();
        int personNum = Integer.parseInt(line);
        line = in.nextLine();
        List<Person> personList = new ArrayList<Person>();
        for (int i = 1; i <= personNum; i++){
            personList.add(new Person(i,Integer.parseInt(line.split(" ")[i-1])));
        }

        line = in.nextLine();
        int areaNum = Integer.parseInt(line);
        int[][] areaArray = new int[areaNum+1][3];
        for (int i = 0; i < areaNum; i++){
            line = in.nextLine();
            areaArray[i][0] =  Integer.parseInt(line.split(" ")[0]);
            areaArray[i][1] =  Integer.parseInt(line.split(" ")[1]);
            areaArray[i][2] =  Integer.parseInt(line.split(" ")[2]);
        }

        //将List中的值排序,这个需要看源码
        Collections.sort(personList);

        for (int i = 0; i < areaNum; i++){
            int topIndex = getTopIndex(areaArray[i][2], personList);
            int endIndex = getEndIndex(areaArray[i][2], personList);
            while ( topIndex <= endIndex && personList.get(topIndex).number < areaArray[i][0]){
                topIndex ++;
            }
            while (topIndex <= endIndex && personList.get(endIndex).number > areaArray[i][1]){
                endIndex--;
            }
            if (topIndex > endIndex){
                System.out.println(0);
            }else {
                System.out.println(endIndex-topIndex+1);
            }
        }
        in.close();

    }

    public static int getTopIndex(int target, List<Person> personList){
        int mid;
        int left,right;
        for (left = 0, right = personList.size(); left < right; ){
            mid = (left+right)/2;
            if(personList.get(mid).like >= target){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    public static int getEndIndex(int target, List<Person> personList){
        int mid;
        int left,right;
        for (left = -1, right = personList.size()-1; left < right; ){
            mid = (left+right+1)/2;
            if(personList.get(mid).like <= target){
                left = mid;
            }else {
                right = mid-1;
            }
        }
        return right;
    }

    static class Person implements Comparable<Person>{
        private int number;
        private int like;

        public Person(int number, int like) {
            this.number = number;
            this.like = like;
        }

        public int compareTo(Person o) {
            if (this.like > o.like){
                return 1;
            }else if(this.like < o.like){
                return -1;
            }
            if(this.number > o.number){
                return 1;
            }else if(this.number > o.number){
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "number=" + number +
                    ", like=" + like +
                    '}';
        }
    }
}
