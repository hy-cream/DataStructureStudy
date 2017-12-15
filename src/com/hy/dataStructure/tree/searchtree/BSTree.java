package com.hy.oj.tree.bs;

/**
 * Created by huyu on 2017/11/30
 * Description: 二叉查找树：当前节点的左子树中的节点的值都小于当前节点的值，右子树中的节点的值都大于当前节点的值
 *（这里存在是否有想等值的节点，严格的是没有）
 * 二叉查找树的特性：
 * 1.left.key<=root.key
 * 2.right.key>=root.key
 * 3.没有键值相等的节点
 *
 * 二叉查找树的遍历：
 * 1.前序遍历
 * 2.中序遍历
 * 3.后序遍历
 */
public class BSTree<T extends Comparable<T>> {

    private BSTNode<T> mRoot; //根节点

    public class BSTNode<T extends Comparable<T>>{
        T key; //键值
        BSTNode<T> left; //左节点
        BSTNode<T> right; //右节点
        BSTNode<T> parent; //父节点

        public BSTNode(T key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public BSTree(BSTNode<T> mRoot) {
        this.mRoot = mRoot;
    }

    public BSTNode<T> getmRoot() {
        return mRoot;
    }

    public void setmRoot(BSTNode<T> mRoot) {
        this.mRoot = mRoot;
    }

    /*-----------------三种遍历--------------*/
    /**
     * 前序遍历
     * 顺序: 根节点---》左子树---》右子树
     * @param tree 遍历的起点，为当前树的“根节点”
     */
    private void preOrder(BSTNode<T> tree){
        if(tree != null){ //该节点不存在子节点时，返回
            System.out.println("key-->"+tree.key);
            preOrder(tree.left);
            preOrder(tree.left);
        }
    }

    public void preOrder(){
        preOrder(mRoot);//从根节点开始
    }

    /**
     * 中序遍历
     * 顺序是： 左子树---》根节点---》右子树
     * @param tree
     */
    private void inOrder(BSTNode<T> tree){
        if(tree != null){ //该节点不存在子节点时，返回
            inOrder(tree.left);
            System.out.println("key-->"+tree.key);
            inOrder(tree.right);
        }
    }

    public void inOrder(){
        inOrder(mRoot);//从根节点开始
    }


    /**
     * 后序遍历
     * 顺序是： 左子树---》右子树---》根节点
     * @param tree
     */
    private void postOrder(BSTNode<T> tree){
        if(tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println("key--->"+tree.key);
        }
    }


    public void postOrder(){
        inOrder(mRoot);//从根节点开始
    }

    /*--------------查找二叉树的中的值  时间复杂度log2(n)----------*/
    /**
     * (递归实现)查找“二叉树x”中键值为key的节点
     * @param x
     * @param key
     * @return
     */
    private BSTNode<T> search(BSTNode<T> x,T key){
        if(x == null){
            return x;
        }

        int cmp=key.compareTo(x.key);
         if(cmp < 0){ //要查找的key要比当前节点的key要小，则在当前节点的左子树下查找
             return search(x.left, key);
         }else if(cmp > 0){
             return search(x.right, key);
         }else {
             return x;
         }
    }

    public BSTNode<T> search(){
        return search(mRoot, mRoot.key);
    }

    /**
     * (非递归实现) 查找“二叉树x”中键值为key的节点
     * @param x
     * @param key
     * @return
     */
    private BSTNode<T> iterativeSearch(BSTNode<T> x,T key){
        while (x != null){
            int cmp = key.compareTo(x.key);
            if(cmp < 0){ //要查找的key要比当前节点的key要小，则在当前节点的左子树下查找
                x = x.left;
            }else if(cmp > 0){
                x = x.right;
            }else {
                return x;
            }
        }
        return x;
    }

    public BSTNode<T> iterativeSearch(){
        return iterativeSearch(mRoot, mRoot.key);
    }

     /*--------------查找二叉树的中的最值----------*/

    /**
     * 查找最大值的节点--》实质是找到最右节点
     * @param tree
     * @return
     */
    private BSTNode<T> searchMaximum(BSTNode<T> tree){
        if(tree == null){
            return null;
        }
        while (tree.right != null){
            tree = tree.right;
        }
        return tree;
    }
    //查找最大值
    public T searchMaximum(){
        BSTNode<T> p=searchMaximum(mRoot);
        if(p != null){
            return p.key;
        }
        return null;
    }

    /**
     * 查找当前二叉树中最小值的节点--》实质是找到最左节点
     * @param tree
     * @return
     */
    private BSTNode<T> searchMinimum(BSTNode<T> tree){
        if(tree == null){
            return null;
        }
        while (tree.left != null){
            tree = tree.left;
        }
        return tree;
    }
    //查找最小值
    public T searchMinimum(){
        BSTNode<T> p=searchMinimum(mRoot);
        if(p != null){
            return p.key;
        }
        return null;
    }

    /*--------------------查找节点的前驱节点和后继节点---------------*/

    /**
     * 查找节点的前驱节点
     * 前驱节点是指：比当前节点的值小的最大节点（通俗的理解是排完大小的顺序后，在当前节点的前一个节点）
     * @param x
     * @return
     */
    public BSTNode<T> precursorNode(BSTNode<T> x){
        if( x == null){
            return null;
        }
        //存在左子节点，则前驱节点是左子树中的最大节点
        if(x.left != null){
            return searchMaximum(x);
        }
       /* 如果不存在:(这里须知构建查找二叉树时的规则)
         1.当前节点可能是“一个右孩子”，那么前驱节点是他的父节点
         2.当前节点可能是“一个左孩子”，则查找x“最低的父节点”，并且该父节点要具有右孩子
         对于第二点，我的理解：当前节点在哪个父节点的右子树上，那么那个父节点就是最低的父节点，也是相当于求该节点是谁的后继节点
       */
        BSTNode<T> parent=x.parent;
        while (parent != null && x==parent.left){
            x=parent;
            parent= parent.parent;
        }
        return parent;
    }

    /**
     * 查找节点的后继节点：比当前节点的值大的最小节点
     * @param x
     * @return
     */
    public BSTNode<T>  subsequentNode(BSTNode<T> x){
        if( x == null){
            return null;
        }
        //存在右子节点，则后继节点是右子树中的最小节点
        if(x.right != null){
            return searchMinimum(x);
        }
        /* 如果不存在:
         1.当前节点可能是“一个左孩子”，那么后继节点是他的父节点
         2.当前节点可能是“一个右孩子”，则查找x“最低的父节点”，并且该父节点要具有左孩子
         那么，找当前节点是在哪个父节点的左子树下
       */
        BSTNode<T> parent=x.parent;
        while (parent != null && x==parent.right){
            x=parent;
            parent= parent.parent;
        }
        return parent;
    }

    /*--------------插入节点-------------*/

    /**
     * 在当前二叉树中插入节点
     * 该方法是： 可以插入相同值的二叉树
     * @param tree
     * @param node
     */
    public void insertNode(BSTree<T> tree,BSTNode<T> node){
        int cmp;
        BSTNode<T> p=null; // 要插入节点的父节点
        BSTNode<T> tmp=tree.mRoot;

        //查找z插入的（父节点）位置(插入在哪个父节点下)
        while(tmp != null){
            p = tmp;
            cmp=node.key.compareTo(p.key);
            if(cmp < 0){
                tmp=tmp.left;
            }else{
                tmp=tmp.right;
            }
        }

        node.parent=p;
        if(p == null){
            //当前节点是根节点
            tree.mRoot=node;
        }else { //存在根节点
            cmp=node.key.compareTo(p.key);
            if(cmp < 0){
                p.left=node;
            }else {
                p.right=node;
            }
        }

    }

    /**
     * 创建一个新的节点，然后插入当前树
     * @param key
     */
    public void insertNode(T key){
        BSTNode<T> node=new BSTNode<>(key,null,null,null);
        if(node != null){
            insertNode(this,node);
        }
    }

    /*---------------删除节点（难点）-----------------*/

    /**
     * 删除节点，并返回被删除的节点(将树理解成双向链表)
     * 删除节点的三种情况：
     * 1.节点是叶子结点: 该节点就是真正要删除的节点, 1（确定真正删除的节点是自己）-->4（全量子树是null）-->7/8(将父节点的左或右设为全量子树)
     * 2.节点只存在一个子节点 该节点就是真正要删除的节点, 1--> 3/4（确定单边全量子树）-->5（全量子树更换父节点，下绑定上）--->7/8（上绑定下）
     * 3.节点存在两个子节点 真正删除的节点是后继节点， 2--》4（后继节点肯定没有左子树,是否有右子树就变成了情况1或者2）--》7/8
     * @param tree
     * @param node
     * @return
     */
    public BSTNode<T> deleteNode(BSTree<T> tree,BSTNode<T> node){

        BSTNode<T> delNodeChildTree = null;  //被删除节点的全量子树(左右子树合体的抽象)
        BSTNode<T> realDelNode = null; //真正被删除的节点

        //（1）获取真正删除的节点
        // 情况1和2：要删除的节点不具有双子树，此时删除的节点就是真正被删除的节点
        if(node.left == null || node.right == null){
            realDelNode = node;
        }else {
            //（2）要删除的节点是含有双子树，此时删除动作为杉树转移，转移删除realDelNode
            //查找他的后继节点，后继节点是右子树中最小的值
            realDelNode = subsequentNode(node); //其实这我觉得选前驱节点也可以，选前驱或者后继只是为了保证删除后还是一颗查找二叉树
        }

        //（3）获得真正删除的子树
        // 情况2：说明node本来只有左子树的单点情况，那么他的右子树绝对为空，所以他的左子树代表被删除的全量子树
        //这里引出全量孩子是为了利用双向链表的思想删除节点realDelNode
        if(realDelNode.left != null){
            delNodeChildTree = realDelNode.left;
        }else {
            //（4）
            delNodeChildTree = realDelNode.right;
        }

       /* (5)删除真正的节点
          模拟双向链表的删除
          删除realDelNode：通过指针越过自己达到删除的目的，并且保证他的子树连接上他的父亲
          但此时只保证了从下到上的绑定，理解成双向链表，需要双向绑定
        */
        if(delNodeChildTree != null){
            delNodeChildTree.parent = realDelNode.parent;
        }

        //(6)删除后把子树折断了，准备焊接
        // 完善互指，这里需要从上往下指
        if(realDelNode.parent == null){
            tree.mRoot=delNodeChildTree;
        }else if(realDelNode == realDelNode.parent.left){
            //(7)
            realDelNode.parent.left=delNodeChildTree;
        }else {
            //(8)
            realDelNode.parent.right=delNodeChildTree;
        }

        //(9)针对 情况3：删除转移，删除的替身要把值互换
        if( realDelNode != node){
            node.key=realDelNode.key;
        }

        return realDelNode;
    }

    /**
     * 删除节点，并返回被删除的节点
     * @param key
     */
    public void deleteNode(T key){
        BSTNode<T> tmp,node;

        if((tmp = search(mRoot,key)) != null){
            if((node = deleteNode(this,tmp)) != null){
                node = null;
            }
        }
    }

    /*-------------打印二叉树--------------*/

    /*-------------销毁二叉树---------------*/

    public void destoryTree(BSTNode<T> tree){
        if(tree == null){
            return;
        }

        if(tree.left != null){
            destoryTree(tree.left);
        }
        if(tree.right != null){
            destoryTree(tree.right);
        }
        tree=null;
    }

    public void clear(){
        destoryTree(mRoot);
        mRoot=null;
    }

}
