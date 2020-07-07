package com.example.rorydemo.leecode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rorydemo.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/15.
 */
public class LeeCodeActivity extends AppCompatActivity {

    int[] arr= {5,2,6,7,3,1,10,9,8,4};
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leet_code);
        Button btn1=(Button) findViewById(R.id.btn1);
        Button btn2=(Button) findViewById(R.id.btn2);
        Button btn3=(Button) findViewById(R.id.btn3);
        Button btn4=(Button) findViewById(R.id.btn4);
        Button btn5=(Button) findViewById(R.id.btn5);
        Button btn6=(Button) findViewById(R.id.btn6);
        Button btn7=(Button) findViewById(R.id.btn7);
        Button btn8=(Button) findViewById(R.id.btn8);
        Button btn9=(Button) findViewById(R.id.btn9);
        Button btn10=(Button) findViewById(R.id.btn10);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode100();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode101();
            }
        });

         tv=(TextView) findViewById(R.id.tv_number);

        StringBuilder sb = new StringBuilder();
        for (int i =0;i<= arr.length-1;i++ ){
            sb.append(arr[i]);
            sb.append(" ");
        }
        tv.setText(sb.toString());
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickSort(arr,0,arr.length -1);

                StringBuilder sb = new StringBuilder();
                for (int i =0;i<= arr.length-1;i++ ){
                    sb.append(arr[i]);
                    sb.append(" ");
                }
                tv.setText(sb.toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode104();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode107();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode1614();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode1572();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode572();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode1546();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode1551();
            }
        });

    }

    private void leetCode1551() {
        TreeNode tree= new TreeNode(0);
        TreeNode tree1= new TreeNode(1);
        TreeNode tree2= new TreeNode(2);
        TreeNode tree3= new TreeNode(3);
        TreeNode tree4= new TreeNode(4);
        tree.left = tree1;
        tree.right = tree2;
        tree1.left = tree3;
        tree1.right = tree4;
        printATree(tree);
    }

    private void printATree(TreeNode node){
         List<List<Integer>> list= LevelOrder(node);
         StringBuilder sb = new StringBuilder();
         sb.append("[");
         for (int i = 0;i<=list.size()-1;i++){
             List<Integer> data=list.get(i);
             for (int j= 0;j<=data.size()-1;j++){
                 sb.append(""+data.get(j));
                 sb.append("、");
             }
         }
         sb.append("]");
         System.out.println(sb.toString());
    }

    private List<List<Integer>> LevelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for (int i=queue.size();i>0;i--){
                TreeNode node = queue.poll();
                temp.add(node.value);
                if (node.left != null) queue.add(node.left);
                if (node.right!= null) queue.add(node.right);
            }
            res.add(temp);
        }
        return res;
    }

    private void leetCode1546() {
        TreeNode tree= new TreeNode(0);
        TreeNode tree1= new TreeNode(1);
        TreeNode tree2= new TreeNode(2);
        TreeNode tree3= new TreeNode(3);
        TreeNode tree4= new TreeNode(4);
        tree.left = tree1;
        tree.right = tree2;
        tree1.left = tree3;
        tree1.right = tree4;
        printATree(mirrorTree(tree));
    }

    public TreeNode mirrorTree(TreeNode treeNode){
        if (treeNode == null) return null;
        TreeNode temp = treeNode.left;
        treeNode.left = mirrorTree(treeNode.right);
        treeNode.right = mirrorTree(temp);
        return  treeNode;
    }

    /**
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     */
    private void leetCode572() {
        TreeNode tree= new TreeNode(0);
        TreeNode tree1= new TreeNode(2);
        TreeNode tree2= new TreeNode(3);
        TreeNode tree3= new TreeNode(4);
        TreeNode tree4= new TreeNode(5);
        TreeNode tree5= new TreeNode(6);
        tree.left = tree1;
        tree.right = tree2;
        tree1.left = tree3;
        tree1.right = tree4;

        Toast.makeText(this,"是否是一棵树的子树"+isSubTree(tree,tree1),Toast.LENGTH_SHORT).show();
    }

    private Boolean isSubTree(TreeNode s,TreeNode t){
        if (t == null) return true;
        if (s == null) return false;
        return isSubTree(s.left,t) || isSubTree(s.right,t) ||isSameTree1(s,t);
    }

    private Boolean isSameTree1(TreeNode t1,TreeNode t2){
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.value != t2.value) return  false;
        return isSubTree(t1.left,t2.left) && isSubTree(t1.right,t2.right);
    }

    /**
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     */
    private void leetCode1572() {
        TreeNode tree= new TreeNode(0);
        TreeNode tree1= new TreeNode(2);
        TreeNode tree2= new TreeNode(3);
        TreeNode tree3= new TreeNode(4);
        TreeNode tree4= new TreeNode(5);
        TreeNode tree5= new TreeNode(6);
        tree.left = tree1;
        tree.right = tree2;
        tree1.left = tree3;
        tree1.right = tree4;
        //tree4.left = tree5;
        Toast.makeText(this, "是否为平衡树："+isBalanced(tree),Toast.LENGTH_SHORT).show();
    }

    private Boolean isBalanced(TreeNode treeNode){
        if (treeNode == null) return true;
        return isBalanced(treeNode.left) && isBalanced(treeNode.right) && Math.abs(depth(treeNode.left) - depth(treeNode.right)) <= 1;
    }

    private int depth(TreeNode treeNode){
        if (treeNode == null) return 0;
        return Math.max(depth(treeNode.left),depth(treeNode.right)) +1;
    }
    private void quickSort(int[] arr, int low, int high) {
        int x,y,tmp;
        if (low > high){
            return;
        }
        x = low;
        y = high;
        tmp = arr[low];
        while (x < y){
            while (tmp<=arr[y] && x<y){
                y--;
            }
            while (tmp >= arr[x] && x < y){
                x++;
            }

            if (x < y){
                int i,j;
                i = arr[x];
                j = arr[y];

                arr[x] = j;
                arr[y] = i;
            }
        }

        arr[low] = arr[x];
        arr[x] = tmp;
        quickSort(arr,low,x -1);
        quickSort(arr,x+1,high);
    }

    /**
     * 判断是否是对称数
     */
    private void leetCode101() {
        TreeNode tree= new TreeNode(0);
        TreeNode tree1= new TreeNode(2);
        TreeNode tree2= new TreeNode(2);
        TreeNode tree3= new TreeNode(3);
        TreeNode tree4= new TreeNode(4);
        tree.left = tree1;
        tree.right = tree2;
        tree1.left = tree3;
        tree1.right = tree4;
        tree2.left = tree4;
        tree2.right = tree3;
        StringBuilder sb = new StringBuilder();
        sb.append("是否是对称的树");
        sb.append(isSymTree(tree,tree));
        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
    }

    private Boolean isSymTree(TreeNode node1,TreeNode node2){
        if (node1 == null && node2 == null){
            return true;
        }else if (node1 == null || node2 == null){
            return false;
        }else if (node1.value != node2.value){
            return false;
        }else {
            return isSymTree(node1.left,node2.right) && isSymTree(node1.right,node2.left);
        }
    }

    //判断2棵树是否相同
    private void leetCode100() {
        TreeNode tree1= new TreeNode(0);
        TreeNode node1= new TreeNode(1);
        TreeNode node2= new TreeNode(2);
        TreeNode tree2= new TreeNode(0);
        TreeNode node3= new TreeNode(3);
        TreeNode node4= new TreeNode(4);
        tree1.left = node1;
        tree1.right = node2;
        tree2.left = node1;
        tree2.right = node2;
        node1.left = node3;
        node3.right = node4;
        StringBuilder sb = new StringBuilder();
        sb.append("是否是相同的树");
        sb.append(isSameTree(tree1,tree2));

        sb.append(" 最大层数：");
        //sb.append(treeMax(tree1,1));
        sb.append(maxDepth(tree1));

        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();

    }

    private void leetCode104(){
        TreeNode test = new TreeNode(0);
        TreeNode node1= new TreeNode(1);
        TreeNode node2= new TreeNode(2);
        TreeNode tree2= new TreeNode(0);
        TreeNode node3= new TreeNode(3);
        TreeNode node4= new TreeNode(4);
        TreeNode node5= new TreeNode(5);
        new TreeNode(6);
        test.left = node1;
        test.right = node2;
        tree2.left = node1;
        tree2.right = node2;
        node1.left = node3;
        node3.right = node4;
        node3.left = node5;
        StringBuilder sb = new StringBuilder();
        sb.append("二叉树最大层数：");
        sb.append(maxDepth(test));

        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();

    }

    /**
     * 个人解法
     * @param oneTree
     * @param num
     * @return
     */
    private int treeMax(TreeNode oneTree,int num){
        int x,y;
        x = num;
        y = num;
         if (oneTree.left == null && oneTree.right ==null){
            return x;
        }else if (oneTree.left == null && oneTree.right != null){
            return treeMax(oneTree.right,++x);
        }else if (oneTree.right == null && oneTree.left != null){
            return treeMax(oneTree.left,++y);
        }else {
            int left = treeMax(oneTree.left,++x);
            int right = treeMax(oneTree.right,++y);
            return left > right ? left :right;
        }
    }

    /**
     * 官方解法
     * @param oneTree
     * @return
     */
    private int maxDepth(TreeNode oneTree){
        if (oneTree == null){
            return 0;
        }
        int left = maxDepth(oneTree.left);
        int right = maxDepth(oneTree.right);
        return Math.max(left,right) + 1;
    }

    /**
     * 比较相同的树
     * @param oneTree
     * @param towTree
     * @return
     */
   private Boolean isSameTree(TreeNode oneTree,TreeNode towTree){
        if (oneTree == null && towTree== null){
            return true;
        }else if (oneTree == null || towTree == null){
            return false;
        }else if (oneTree.value != towTree.value){
            return false;
        }else {
           return isSameTree(oneTree.left,towTree.left) && isSameTree(oneTree.right,towTree.right);
        }
   }


   private void leetCode107(){
       TreeNode tree1= new TreeNode(0);
       TreeNode node1= new TreeNode(1);
       TreeNode node2= new TreeNode(2);
       TreeNode tree2= new TreeNode(0);
       TreeNode node3= new TreeNode(3);
       TreeNode node4= new TreeNode(4);
       TreeNode node5= new TreeNode(5);
       tree1.left = node1;
       tree1.right = node2;
       tree2.left = node1;
       tree2.right = node2;
       node1.left = node3;
       node3.right = node4;
       node3.left = node5;
       Toast.makeText(this,""+printTree(tree1),Toast.LENGTH_SHORT).show();
   }

    /**
     * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     * 给定有序数组: [-10,-3,0,5,9],
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     */
   private void leetCode1614(){
       int[] arr = {-10,-3,0,5,9};
       TreeNode tt =helper(arr,0,arr.length -1);
       Toast.makeText(this, ""+printTree(tt),Toast.LENGTH_SHORT).show();
   }

   private TreeNode helper(int[] arr,int start,int end){
       if (start > end){
           return  null;
       }
       int middle = (start + end + 1) /2;
       TreeNode tr = new TreeNode(arr[middle]);
       tr.left =  helper(arr,start,middle -1);
       tr.right = helper(arr,middle +1 ,end);
       return  tr;
   }

    /**
     * 树的遍历
     * @param tree
     * @return
     */
   private List<List<Integer>> printTree(TreeNode tree){
       List<List<Integer>> list = new ArrayList<>();
      find(tree,1,list);
      return list;
   }

   private void find(TreeNode tree,int level, List<List<Integer>> list){
        if (tree == null){
            return;
        }
        if (level > list.size()){
            list.add(0,new ArrayList<Integer>());
        }
        list.get(list.size() - level).add(tree.value);
        find(tree.left,level + 1,list);
        find(tree.right,level + 1,list);
   }

    //树类的定义
    class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int num) {
            value = num;
        }
    }


}
