package com.example.group9_hw03;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
Button b1,b2;
ProgressBar p;
TextView tv2,tv3;
ArrayList<Integer> stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ActionBar ac=getSupportActionBar();
        ac.hide();
        b1=(Button)findViewById(R.id.b1);
        stat=(ArrayList<Integer>)getIntent().getIntegerArrayListExtra("stats");
        Log.d("Demo",""+stat);
        int count=0;
        for(int i=0;i<stat.size();i++)
        {
           if(stat.get(i)==1){
            count=count+1;
           }
        }
        Log.d("Demo",""+count);
        Float percent;
        percent=(float)(count*100)/16;
        Log.d("Demo",""+percent);
        tv2=(TextView)findViewById(R.id.tv2);
        tv2.setText(percent+"%");
        p=(ProgressBar)findViewById(R.id.p1);
        p.setVisibility(View.VISIBLE);
        p.setProgress(Math.round(percent));
        tv3=(TextView)findViewById(R.id.tv3);
        if(percent<100)
        {
            tv3.setText("Try again and see if you can get all the answers correct");
        }
        else{
            tv3.setText("Congratulations!!");
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        b2=(Button)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent();
               setResult(RESULT_OK,i);
               finish();
            }
        });

    }
}
