package com.example.group9_hw03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by akhilareddydepa on 16/02/18.
 */


public class Image extends AsyncTask<String,Void,Bitmap>
{


    Trivia_Activity activity;

    public Image(Trivia_Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        activity.p.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        HttpURLConnection htp1=null;
        try {
            URL imgurl = new URL(strings[0]);
          htp1 = (HttpURLConnection) imgurl.openConnection();
            htp1.connect();
            Bitmap bitmap = null;
            if (htp1.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(htp1.getInputStream());

            }
            return bitmap;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (htp1 != null) {
                htp1.disconnect();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        activity.setImage(bitmap);
        activity.p.setVisibility(View.GONE);
    }
}





