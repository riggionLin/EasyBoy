package com.example.rorydemo.leecode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rorydemo.R;

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/15.
 */
public class LeeCodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leet_code);
        Button btn1=(Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode100();
            }
        });
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
