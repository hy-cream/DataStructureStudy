package com.hy.datastructure.dp;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by huyu on 2017/12/25
 * Description: 快速并查集(森林)-----一般用于判断属于某个树
 * 对问题建模：1.给出两个节点，判断它们是否连通，如果连通，不需要给出具体的路径 (uf)
 *            2.给出两个节点，判断它们是否连通，如果连通，需要给出具体的路径（dfs）
 * 建模思路：
 * 对于连通的节点，我们可以认为他们属于一个组，然后看这两个组是否相等，相同则说明他们是连通的，不相同则合并。
 * 简单起见，将所有节点都已整数表示， 可以用哈希表或者散列表来表示映射的关系
 *
 */
public class UnionFind {

    private int[] id; //节点的index数组
    private int count; //当前树的数量

    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = -1; //负数的绝对值代表树中的节点数
        }
    }

    /**
     * 查找k的代表节点（根）
     * @param k 节点的index
     * @return
     */
    public int find(int k){
        if(id[k] < 0){//他就是独立的树代表节点
            return k;
        }else {
            //这里采用路径压缩，将路径上的节点的值都变为根节点的值
            id[k] = find(id[k]);
            return id[k]; //每次递归返回的都是最后一个找到的那个代表节点
        }
    }

    /**
     * 快速合并
     * @param a
     * @param b
     * @return a,b在一个树中则返回false;不在一个树中就合并，并且返回true
     */
    public boolean quickUnion(int a, int b){
        if(a == b){
            return false;
        }else {
            int aRoot = find(a);
            System.out.println("aRoot->"+aRoot);
            int bRoot = find(b);
            System.out.println("bRoot->"+bRoot);
            if(aRoot == bRoot){
                return false;
            }
            if(id[aRoot] < id[bRoot]){ //a所在的树是大树，这里做优化，避免树的高度太高，不利于查找。那么，这里大树合并小树
                id[aRoot] += id[bRoot];
                id[bRoot] = aRoot;
            }else {
                id[bRoot] += id[aRoot];
                id[aRoot] = bRoot;
            }
            count--; //每合并一次，集合的数量就少了一个
            return true;
        }
    }

}
