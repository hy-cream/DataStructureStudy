package com.hy.datastructure.tree.searchtree;


import static sun.swing.MenuItemLayoutHelper.max;

/**
 * Created by huyu on 2017/12/15
 * Description:平衡二叉树（高度平衡二叉树 AVL树）
 * 特点：
 * AVl树中任何节点的两个子树的高度最大差别为1
 */
public class AVLTree<T extends Comparable<T>> {

    private AVLTreeNode<T> rootNode;
    private int height;

    class AVLTreeNode<T extends Comparable<T>>{
        T key; //关键字
        AVLTreeNode<T> leftNode;
        AVLTreeNode<T> rightNode;
        int height; //树的高度

        public AVLTreeNode(T key, AVLTreeNode<T> leftNode, AVLTreeNode<T> rightNode) {
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.height = 0;
        }
    }

    /**
     * 获取树的高度
     * @param treeNode
     * @return
     */
    private int height(AVLTreeNode<T> treeNode){
        return treeNode != null ? treeNode.height : 0;
    }

    public int getHeight(){
        return height(rootNode);
    }

    /**
     * 节点左旋 LL
     * @param node
     * @return 当前根节点
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> node){
        AVLTreeNode<T> k;
        //旋转
        k = node.leftNode;
        node.leftNode = k.rightNode;
        k.rightNode= node;
        //更新高度
        node.height = max(height(node.leftNode),height(node.rightNode))+1;
        k.height = max(height(k.leftNode),node.height)+1;

        return k;
    }


    /**
     * 节点右旋 RR
     * @param node
     * @return 当前根节点
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> node){
        AVLTreeNode<T> k;
        //旋转
        k = node.leftNode;
        node.leftNode = k.rightNode;
        k.rightNode= node;
        //更新高度
        node.height = max(height(node.leftNode),height(node.rightNode))+1;
        k.height = max(height(k.leftNode),node.height)+1;

        return k;
    }



    /**
     * 插入值 (利用该树的特性，采用递归的写法)
     * @param treeNode 插入树的根节点
     * @param key 插入的节点的键值
     * @return
     */
    private AVLTreeNode<T> insertNode(AVLTreeNode<T> treeNode,T key){

        if(treeNode == null){
            treeNode = new AVLTreeNode<T>(key,null,null);//新建一个节点
        }else {
            int cmp = treeNode.key.compareTo(key);
            if(cmp < 0){//插入左子树
                treeNode.leftNode = insertNode(treeNode.leftNode, key);
                if(height(treeNode.leftNode)-height(treeNode.rightNode) == 2){
                    if(key.compareTo(treeNode.leftNode.key) < 0){//LL(treeNode节点的左子树高度与右子树高度相差大于1),需要对当前节点进行左旋
                        treeNode = leftLeftRotation(treeNode);
                    }else {

                    }
                }
            }
        }

        treeNode.height = max(height(treeNode.leftNode),height(treeNode.rightNode))+1; //左右子树中最大的高度+本身节点的长度
        return treeNode;
    }

    public void insert(T key){
        rootNode = insertNode(rootNode, key);
    }


}
