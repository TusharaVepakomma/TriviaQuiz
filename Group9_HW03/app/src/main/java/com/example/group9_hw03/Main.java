package com.example.group9_hw03;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by akhilareddydepa on 15/02/18.
 */

public class Main extends AsyncTask<String, Void, ArrayList<String>> {

    MainActivity activity;

    public Main(MainActivity activity) {
        this.activity = activity;
    }

    StringBuilder str;
BufferedReader b;

    @Override
    protected void onPreExecute() {
        activity.p.setVisibility(View.VISIBLE);

    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
ArrayList<String> result=new ArrayList<String >();
        HttpURLConnection htp1=null;
        try {
            URL url=new URL(strings[0]);
            htp1=(HttpURLConnection) url.openConnection();
            htp1.connect();
            if(htp1.getResponseCode()==HttpURLConnection.HTTP_OK)
            {
                 b=new BufferedReader(new InputStreamReader(htp1.getInputStream()));
                str=new StringBuilder();
                String line="";
                while((line=b.readLine())!=null)
                {
                    str.append(line);
                    result.add(line);

                }
                //activity.p.show();
            }

return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (htp1 != null) {
                htp1.disconnect();
            }
            if (b!= null) {
                try {
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }



    @Override
    protected void onPostExecute(ArrayList<String> s) {
//Log.d("Demo",""+s.toString());
activity.screen(s);
activity.p.setVisibility(View.GONE);
    }
}
