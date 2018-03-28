 package com.hy.datastructure.sort;

/**
 * Created by huyu on 2017/11/13
 * Description: 堆排序
 * 主要排序单独的边，都是直接按照大小顺序依次排序，他的每个值都代表当前的排列是有序的
 * 1.支持插入元素和寻找最大、小元素的数据结构称为优先队列
 * 2.求当前数列的第K大的值，或者第k小的值，建立一个k大小的堆，
 * 例求一个第K大的值：维护一个k个元素的最小堆，剩下的值与堆顶作比较，小于则丢弃，大于则替换，并且维护当前为最小堆 时间复杂度为O(nlogk)
 * 时间复杂度：O(nlogn)
 *
 */
public class HeapSort {

        public static void main(String[] args) {

        int[] h=new int[]{-1,5,6,4,8,2,9,1,3,0};
        int[] h2=new int[10];
        int len=h.length-1;

       //创建堆1.从第len/2--1,进行向下调整
       for(int i=len/2; i>=1; i--){
           siftDown(i,len,h);
       }

       //创建堆2：将元素一个个添加进堆，将节点添加到尾部，进行向上调整
       /*for(int j=1; j<=len; j++){
            h2[j]=h[j];
            siftUp(j,len,h2);
        }
        */

       //堆排序
        sort(len,h);

        for(int k=1;k<h.length;k++){
            System.out.print(h[k]+"-->");
        }
    }

    public static void sort(int len,int[] h){
        //删除节点的方式
        for(int k=len; k>=1; k--){
            deleteEnd(k,h);
        }
    }

    public static void deleteEnd(int num,int[] heap){//num 当前堆的大小
        swap(1,num,heap);
        siftDown(1,num-1,heap);
    }

    /**
     *向下调整(父节点找子节点调整)，构建堆，时间复杂度是O(n)
     */
    public static void siftDown(int pos,int len,int[] heap){

        boolean flag=true;
        int  min=pos;//每次都算当前的节点为最小值
        while(pos*2<=len && flag){//和子节点比较，获得最小的值最为父节点
            if(heap[min]>heap[pos*2]){
                min=pos*2;
            }
            if(pos*2+1<=len && heap[min]>heap[pos*2+1]){
                min=pos*2+1;
            }
            if (min != pos){
                swap(pos,min,heap);
                pos=min;
            }else {
                flag=false;
            }
        }
    }

    /**
     * 向上调整（子节点找父节点调整），维护最小堆 创建堆的时间复杂度：O(nlogn)
     * @param pos 向上调整的编号
     */
    public static void siftUp(int pos,int len,int[] heap){
        if(pos==1){
            System.out.println("pos==1");return;}//顶点
        boolean flag=true;
        while (pos!=1 && flag){
            if(heap[pos]<heap[pos/2]){
                swap(pos,pos/2,heap);
                System.out.println(pos+"<---->"+pos/2);
                pos=pos/2;
            }else {
                System.out.println("pos->"+pos+"--value-->"+heap[pos]);
                flag=false;
            }
        }

    }

    public static void swap(int i,int j,int[] heap){
        int t;
        t=heap[i];
        heap[i]=heap[j];
        heap[j]=t;
    }

}
