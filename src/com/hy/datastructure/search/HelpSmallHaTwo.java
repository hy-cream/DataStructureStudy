package com.hy.datastructure.search;

/**
 * created by huyu8 on 2018/3/22
 * 广度优先搜索--图的遍历
 * 从起点，在绕过障碍物的情况下，到达终点,并且路程最短
 */
public class HelpSmallHaTwo {

    private static int n = 3, m = 2; // 最终的目的地坐标
    private static int min = 1000000;

    public static void main(String[] args) {
        int[][] map = {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}}; // 表示一个二维地图，0可以走，1为障碍
        int[][] book = new int[5][4];
    }

    public static void dfs(int[][] map, int[][] book, int x, int y, int step) { // x,y代表是当前的位置，step代表当前走了多少步


    }
}
