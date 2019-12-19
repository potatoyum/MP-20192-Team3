package com.example.whereareyou;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

public class MissingViewer extends LinearLayout {
    ImageView imageView;
    TextView textView;

    public MissingViewer(Context context) {
        super(context);
        init(context);
    }

    public MissingViewer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_missing, this, true);

        imageView = (ImageView)findViewById(R.id.img);
        textView = (TextView)findViewById(R.id.txt);
    }

    public void setItem(MissingItem mItem) {
        try {
            URL url = new URL(mItem.getImage());
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imageView.setImageBitmap(bm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView.setText(mItem.getText());
    }
}
