package com.hy.datastructure.search;

/**
 * created by huyu8 on 2018/3/22
 * 深度优先搜索---图的遍历
 * 从起点，在绕过障碍物的情况下，到达终点,并且路程最短
 */
public class HelpSmallHa {

    private static int n = 3,m = 2; // 最终的目的地坐标
    private static int min = 1000000;

    public static void main(String[] args){

        int[][] map = {{0,0,1,0},{0,0,0,0},{0,0,1,0},{0,1,0,0},{0,0,0,1}}; // 表示一个二维地图，0可以走，1为障碍
        int[][] book = new int[5][4];

        // 假设起点是0，0
        book[0][0] = 1;
        dfs(map, book, 0, 0, 0);
        System.out.println(min);

    }

    public static void dfs(int[][] map,int[][] book,int x, int y,int step){ // x,y代表是当前的位置，step代表当前走了多少步

        int[][] next = {{0,1},{1,0},{0,-1},{-1,0}}; // 按照左下右上的顺序遍历这张图,表示深搜的深度

        // 搜索递归类的一定要控制好 返回条件
        if (x == n && y == m){
            if (step < min){
                min = step;
            }
            return;
        }

        // 枚举四种走法
        int tx, ty;
        for (int i = 0; i < 4; i++){
            //下一步的坐标
            tx = x + next[i][0];
            ty = y + next[i][1];

            //判断是否出界
            if (tx < 0 || tx > 4 || ty < 0 || ty > 3){
                continue;
            }
            //走该点
            if (book[tx][ty] == 0 && map[tx][ty] != 1){ // 该点没走过，并且不是障碍物
                book[tx][ty] = 1;
                dfs(map,book,tx,ty,step+1); // 走下一步

                book[tx][ty] = 0;
            }
        }
    }


}
