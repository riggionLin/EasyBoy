package com.example.rorydemo.generi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rorydemo.R;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/12.
 */
public class GenericActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //泛型类的使用
                GenericTest genericTest= new  GenericTest();
                genericTest.main();
            }
        });

    }



}



