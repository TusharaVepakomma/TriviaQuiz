package com.example.group9_hw03;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by akhilareddydepa on 15/02/18.
 */

public class QuestionScreen implements Serializable {
    int qno;
    String question;
    String imgurl;
    ArrayList options;
    int answer;

    public QuestionScreen(int qno, String question, String imgurl, ArrayList options, int answer) {
        this.qno = qno;
        this.question = question;
        this.imgurl = imgurl;
        this.options = options;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionScreen{" +
                "qno=" + qno +
                ", question='" + question + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}
