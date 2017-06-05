package com.weather.weathertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ofedzhora on 05.06.2017.
 */

public class ImageViewLoader {

    static Bitmap bitmap;

    public static void loadFromUrl(final String imageUrl, final ImageView view) {
        final Handler handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                view.setImageBitmap(bitmap);
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {
                    url = new URL(imageUrl);
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
}
