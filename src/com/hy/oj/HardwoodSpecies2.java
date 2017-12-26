package com.hy.oj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by huyu on 2017/12/1
 * Description:2418用map解
 */
public class HardwoodSpecies2 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String nodeName="";
        int nodeNum=0;
        Map<String,Integer> map=new HashMap<>();
        while (scan.hasNextLine() && !"".equals((nodeName=scan.nextLine()).trim())){
            nodeNum++;
            if(null == map.get(nodeName)){
                map.put(nodeName,1);
            }else {
                map.replace(nodeName,map.get(nodeName)+1);
            }
        }
        //排序输出



    }
}
