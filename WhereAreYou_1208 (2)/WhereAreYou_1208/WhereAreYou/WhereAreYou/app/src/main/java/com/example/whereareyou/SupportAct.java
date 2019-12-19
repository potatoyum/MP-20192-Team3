package com.example.whereareyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SupportAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);


        Button btn = (Button)findViewById(R.id.button);

    }

    public void onClickButton(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("https://www.ekara.org/parttake/issue/read/11886"));

        startActivity(intent);


    }
}
