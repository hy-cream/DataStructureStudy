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

    public AVLTree() {
        this.rootNode = null;
    }

    public AVLTreeNode<T> getRootNode() {
        return rootNode;
    }

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

    //高度都是要计算得到的，不能直接获得
    public int getHeight(){
        return height(rootNode);
    }

    /**
     * 节点左旋 LL ，往右转
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
     * 节点右旋 RR 往左转
     * @param node
     * @return 当前根节点
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> node){
        AVLTreeNode<T> k;
        //旋转
        k = node.rightNode;
        node.rightNode = k.leftNode;
        k.leftNode= node;
        //更新高度
        node.height = max(height(node.leftNode),height(node.rightNode))+1;
        k.height = max(node.height,height(k.rightNode))+1;

        return k;
    }

    /**
     * LR---> 先转换为LL的情况，再做处理
     * 先对插入节点的父节点进行左旋，此时变成LL，再对祖父节点进行右旋，达到平衡
     * @param node 祖父节点
     * @return
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> node){
        node.leftNode = rightRightRotation(node.leftNode);
        return leftLeftRotation(node);
    }


    /**
     * RL---> 先转换为RR的情况，再做处理
     * 先对插入节点的父节点进行右旋，此时变成RR，再对祖父节点进行左旋，达到平衡
     * 对node的右节点进行右旋，再对node本身左旋1
     * @param node 祖父节点
     * @return
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> node){
        node.rightNode = leftLeftRotation(node.rightNode);
        return rightRightRotation(node);
    }

    /**
     * 查找当前子树的最大值
     * @param treeNode 当前节点
     * @return
     */
    private AVLTreeNode<T> searchMaximum(AVLTreeNode<T> treeNode){
        if (treeNode == null){
            return null;
        }
        while (treeNode.rightNode != null){
            treeNode = treeNode.rightNode;
        }
        return treeNode;
    }

    /**
     * 查找当前子树的最小值
     * @param treeNode 当前节点
     * @return
     */
    private AVLTreeNode<T> searchMinimum(AVLTreeNode<T> treeNode){
        if (treeNode == null){
            return null;
        }
        while (treeNode.leftNode != null){
            treeNode = treeNode.leftNode;
        }
        return treeNode;
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
            int cmp = key.compareTo(treeNode.key);
            if(cmp < 0){//插入左子树
                treeNode.leftNode = insertNode(treeNode.leftNode, key);
                if(height(treeNode.leftNode)-height(treeNode.rightNode) == 2){ //treeNode节点的左子树高度与右子树高度相差大于1
                    if(key.compareTo(treeNode.leftNode.key) < 0){//LL(不平衡，并且当前插入的节点是左子树),需要对当前节点进行右旋
                        treeNode = leftLeftRotation(treeNode);
                    }else { //LR(不平衡，当前插入的节点是右子树)
                        treeNode = leftRightRotation(treeNode);
                    }
                }
            }else if(cmp > 0){ //插入右子树
                treeNode.rightNode = insertNode(treeNode.rightNode, key);
                if(height(treeNode.rightNode)-height(treeNode.leftNode) == 2){ //treeNode节点的右子树太高
                    if(key.compareTo(treeNode.leftNode.key) > 0){ //RR(不平衡，并且当前插入的节点是右子树),需要对当前节点进行左旋
                        treeNode = rightRightRotation(treeNode);
                    }else { //RL(不平衡，当前插入的节点是左子树)
                        treeNode = rightLeftRotation(treeNode);
                    }
                }
            }else {
                System.out.println("不能插入重复数据");
            }
        }

        treeNode.height = max(height(treeNode.leftNode),height(treeNode.rightNode))+1; //左右子树中最大的高度+本身节点的长度
        return treeNode;
    }

    public void insert(T key){
        rootNode = insertNode(rootNode, key);
    }


    /**
     * 删除节点
     * @param treeNode 树的根节点
     * @param key 删除的键值
     * @return
     */
    private AVLTreeNode<T> deleteNode(AVLTreeNode<T> treeNode, T key){

        if(treeNode == null || key ==null){
            return null;
        }

        int cmp = key.compareTo(treeNode.key);

        if(cmp < 0){ //待删除的节点在treeNode的左子树中

            treeNode.leftNode = deleteNode(treeNode.leftNode, key);

            if(height(treeNode.rightNode) - height(treeNode.leftNode) == 2){ //不平衡，则需要调节

                //我的理解：删除的是左子树的节点，那么不平衡时肯定要左旋，那么右节点的左子树不能过高，要先将右节点的左子树平衡

                AVLTreeNode<T> right = treeNode.rightNode;

                if(height(right.leftNode) > height(right.rightNode)){ //左子树太高
                    treeNode = rightLeftRotation(treeNode);
                }else { //右子树太高
                    treeNode = rightRightRotation(treeNode);
                }
            }

        }else if(cmp > 0){ //待删除的节点在treeNode的右子树中

            //我的理解：删除的是右子树的节点，那么不平衡时肯定要右旋，那么左节点的右子树不能过高，要先将左节点的右子树平衡

            treeNode.rightNode =deleteNode(treeNode.rightNode, key);

            if(height(treeNode.leftNode) - height(treeNode.rightNode) == 2){ //不平衡
                AVLTreeNode<T> left = treeNode.leftNode;

                if(height(left.leftNode) < height(left.rightNode)){
                    treeNode = leftRightRotation(treeNode);
                }else {
                    treeNode = leftLeftRotation(treeNode);
                }
            }

        }else {//treeNode对应要被删除的节点

            //当前节点的左右子树都不为空
            if(treeNode.leftNode != null && treeNode.rightNode != null){ //情况1：删除后还是平衡的
                if(height(treeNode.leftNode) > height(treeNode.rightNode)){ //左子树大于右子树
                    //在左子树中寻找最大值,然后替换要删除节点的值，在子树中删除该节点
                    //这样做的好处是：删除节点后，AVL树仍然是平衡的
                    AVLTreeNode<T> maxInLeft=searchMaximum(treeNode.leftNode);
                    treeNode.key = maxInLeft.key; //交换两个值
                    treeNode.leftNode = deleteNode(treeNode.leftNode , maxInLeft.key);

                }else { //右边的高度更高,或者相等
                    //在右子树中找最小的节点
                    //这样做的好处是：删除节点后，AVL树仍然是平衡的
                    AVLTreeNode<T> minInRight=searchMinimum(treeNode.rightNode);
                    treeNode.key = minInRight.key;
                    treeNode.rightNode = deleteNode(treeNode.rightNode,minInRight.key);
                }

            }else { //情况二2： 删除节点后可能造成不平衡

                AVLTreeNode<T> tmp = treeNode;
                treeNode = treeNode.leftNode == null ? treeNode.rightNode : treeNode.leftNode;
                tmp = null;

            }
        }

        //返回之前要先刷新高度
         if(treeNode != null){
            treeNode.height = max(height(treeNode.leftNode),height(treeNode.rightNode)) + 1;
        }

        return treeNode;
    }

    /**
     * 中序遍历：做检查用的
     * @param treeNode
     */
    private void inOrder(AVLTreeNode<T> treeNode){
        if(treeNode == null){
            return;
        }
        inOrder(treeNode.leftNode);
        System.out.print(treeNode.key+"  ");
        inOrder(treeNode.rightNode);
    }

    public void sort(){
        inOrder(rootNode);
    }

    public static void main(String[] args) {

        AVLTree<Integer> avlTree=new AVLTree<>();
        avlTree.insert(6);
        avlTree.insert(3);
        avlTree.insert(9);
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(11);
        avlTree.insert(7);
        avlTree.insert(12);
        avlTree.sort();
        System.out.println();

        System.out.println(avlTree.getRootNode().key);
        avlTree.deleteNode(avlTree.getRootNode(),7);

        avlTree.sort();
        System.out.println();

    }
}


