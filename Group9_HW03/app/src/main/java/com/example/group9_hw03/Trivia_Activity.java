package com.example.group9_hw03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.LinkedList;

public class Trivia_Activity extends AppCompatActivity {
    ProgressBar p;
int REQ_CODE=100;
    static ImageView iv1;
    int index=0;
    TextView tv3, tv1, timer;
    ArrayList<QuestionScreen> ql;
    LinkedList<QuestionScreen> ql1;
    QuestionScreen q;
    RadioGroup[] rg;
    RadioButton[] rb;
    LinearLayout l;
    CountDownTimer ctimer;
    int answer;
    static ArrayList<Integer> stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_);
        setTitle("Trivia Activity");
        // p=new ProgressDialog(Trivia_Activity.this);
        p = (ProgressBar) findViewById(R.id.progressBar);
        p.setVisibility(View.GONE);
        ql = (ArrayList<QuestionScreen>) getIntent().getExtras().getSerializable("key");
        Log.d("Demo", "" + ql);
        stat = new ArrayList<Integer>();
        ql1 = new LinkedList<QuestionScreen>();
        ql1.addAll(ql);
        l = (LinearLayout) findViewById(R.id.l);
        rg = new RadioGroup[ql1.size()];
        Display(index);
        timer=(TextView)findViewById(R.id.tv2);
        ctimer=new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Time Left:"+millisUntilFinished/1000+" Seconds");
            }

            @Override
            public void onFinish() {
                Intent stats = new Intent(Trivia_Activity.this, StatsActivity.class);
                stats.putExtra("stats", stat);
                startActivityForResult(stats,REQ_CODE);
               // finish();
            }
        }.start();
        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index + 1;
                if (index < ql.size()) {
                    //iv1.setImageResource(R.drawable.ic_launcher_background);
                    Display(index);
                } else if (index >= ql.size()) {
                    Intent stats = new Intent(Trivia_Activity.this, StatsActivity.class);
                    stats.putExtra("stats", stat);
                    startActivityForResult(stats,REQ_CODE);

                }

            }
        });

        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//Display Questions and validate answers
    public void Display(int index) {
        q = (QuestionScreen) ql1.get(index);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv1 = (TextView) findViewById(R.id.tv1);
        iv1 = (ImageView) findViewById(R.id.iv1);
        if (index == 0) {
            new Image(Trivia_Activity.this).execute(q.imgurl);
            tv3.setText(q.question);
            int no=q.qno+1;
            tv1.setText("Q" +no);
            rg[index] = new RadioGroup(this);
            rb = new RadioButton[q.options.size()];
            for (int i = 0; i < q.options.size(); i++) {
                rb[i] = new RadioButton(this);
                rb[i].setText("" + q.options.get(i));
                rg[index].addView(rb[i]);

            }
            l.addView(rg[index]);

            rg[index].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //Log.d("Demo", "" + checkedId);
                    for (int j = 0; j < group.getChildCount(); j++) {
                        if (rb[j].getId() == checkedId) {
                            answer = j;
                            Answer(answer,q.answer);

                        }
                    }
                }
            });

        } else {
            l.removeAllViews();
            new Image(Trivia_Activity.this).execute(q.imgurl);
            tv3.setText(q.question);
            int no=q.qno+1;
            tv1.setText("Q" +no);
            rg[index] = new RadioGroup(this);
            rb = new RadioButton[q.options.size()];
            for (int i = 0; i < q.options.size(); i++) {
                rb[i] = new RadioButton(this);
                rb[i].setText("" + q.options.get(i));
                rg[index].addView(rb[i]);

            }
            l.addView(rg[index]);
            rg[index].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                   // Log.d("Demo", "" + checkedId);
                    for (int j = 0; j < group.getChildCount(); j++) {
                        if (rb[j].getId() == checkedId) {
                            answer = j;
                            Answer(answer,q.answer);
                        }
                    }

                }
            });
        }
    }
    public static void setImage(Bitmap bitmap) {
        if (bitmap == null) {
iv1.setImageResource(android.R.color.transparent);
        } else {
            iv1.setImageBitmap(bitmap);
        }
    }
    public static void Answer(int answer, int result) {
        if (answer == result) {
            stat.add(1);
        }
        else{
            stat.add(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                Display(0);
                    ctimer.cancel();
                    timer.setText("");
                    ctimer.start();

                //Log.d("demo", "Result received is" + value);

            } else if (resultCode == RESULT_CANCELED) {
                Log.d("demo", "Result not received");
finish();

            }
        }
    }
}
