package com.example.rorydemo.leecode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rorydemo.R;

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
        StringBuilder sb = new StringBuilder();
        sb.append("是否是相同的树");
        sb.append(isSameTree(tree1,tree2));
        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();

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
