package com.hy.oj;

import java.util.Scanner;

/**
 * Created by huyu on 2017/12/1
 * Description: 北大oj 2418 查找二叉树
 */
public class HardwoodSpecies {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String nodeName="";
        SpeciesTree tree=new SpeciesTree(null,0);
        while (scan.hasNextLine() && !"".equals((nodeName=scan.nextLine()).trim())){
            tree.addNode(tree.getRoot(),nodeName);
        }
        //中序遍历
        tree.inOrder(tree.getRoot());
    }
}
//二叉树
class  SpeciesTree{
    private SpeciesNode root;
    private int nodeNum;
    public SpeciesTree(SpeciesNode root, int nodeNum) {
        this.root = root;
        this.nodeNum = nodeNum;
    }
    public SpeciesNode getRoot() {
        return root;
    }
    /*  中序遍历,默认从根节点开始*/
    public void inOrder(SpeciesNode node){
        if(node != null){
            inOrder(node.leftNode);
            System.out.println(node.treeName+" "+String.format("%.4f",(1.0*node.num/nodeNum*100)));
            inOrder(node.rightNode);
        }
    }
    public void addNode(SpeciesNode node,String nodeName){
        nodeNum++;
        SpeciesNode parent=null;
        boolean flag=true;
        while (node != null){
            int cmp=node.treeName.compareTo(nodeName);
            if(cmp > 0){
                parent=node;
                node=node.leftNode;
            }else if(cmp < 0){
                parent=node;
                node=node.rightNode;
            }else {
                //该节点已存在
                node.num++;
                flag=false;
                break;
            }
        }
        if (flag) {
            SpeciesNode newNode=new SpeciesNode(nodeName, 1, null, null, null);
            if (parent == null) {
                //他是根节点
                this.root = newNode;
            } else {
                int cmp=parent.treeName.compareTo(nodeName);
                if(cmp > 0){
                    parent.leftNode=newNode;
                }else {
                    parent.rightNode=newNode;
                }
                newNode.setParentNode(parent);
            }
        }
    }
    class SpeciesNode {
        private String treeName;
        private int num;
        private SpeciesNode leftNode;
        private SpeciesNode rightNode;
        private SpeciesNode parentNode;

        public SpeciesNode(String treeName, int num, SpeciesNode leftNode, SpeciesNode rightNode, SpeciesNode parentNode) {
            this.treeName = treeName;
            this.num = num;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
        }
        public void setParentNode(SpeciesNode parentNode) {
            this.parentNode = parentNode;
        }
    }
}