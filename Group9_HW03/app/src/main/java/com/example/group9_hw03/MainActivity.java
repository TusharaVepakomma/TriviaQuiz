package com.example.group9_hw03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    TextView tv2;
    Button b1;
    QuestionScreen q;
   ProgressBar p;
    ArrayList<QuestionScreen> ql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p = findViewById(R.id.p1);
        p.setVisibility(View.VISIBLE);
        new Main(this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        Button b2=(Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Trivia_Activity.class);
                i.putExtra("key",ql);
                startActivity(i);
            }
        });
        b1=(Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);

            }
        });

    }

    public void screen(ArrayList<String> a) {
  /*      Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

            }
        }, 3000);*/
        iv1 = (ImageView) findViewById(R.id.iv);
        iv1.setImageResource(R.drawable.trivia);

        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText("Trivia is Ready");
        Button b2 = (Button) findViewById(R.id.b2);
        b2.setEnabled(true);
ql=new ArrayList<QuestionScreen>();
for(int i=0;i<a.size();i++) {
    String[] questions = a.get(i).split(";");
    for (int j = 0; j < (questions.length); j++) {
        ArrayList options = new ArrayList();
        for (int k = 3; k < (questions.length - 1); k++) {
            options.add(questions[k]);
        }
        q = new QuestionScreen(Integer.parseInt(questions[0]), questions[1], questions[2], options, Integer.parseInt(questions[questions.length - 1]));
    }
ql.add(q);
}

        }

    }

