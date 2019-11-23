package com.example.whereareyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.hospital:
                intent = new Intent(MainActivity.this, HospitalAct.class);
                startActivity(intent);
                break;
            case R.id.pharmacy:
                intent = new Intent(MainActivity.this, PharmacyAct.class);
                startActivity(intent);
                break;
            case R.id.shelter:
                intent = new Intent(MainActivity.this, ShelterAct.class);
                startActivity(intent);
                break;
            case R.id.search:
                intent = new Intent(MainActivity.this, SearchAct.class);
                startActivity(intent);
                break;
            case R.id.tips:
                intent = new Intent(MainActivity.this, TipsAct.class);
                startActivity(intent);
                break;
            case R.id.support:
                intent = new Intent(MainActivity.this, SupportAct.class);
                startActivity(intent);
                break;
        }
    }
}
