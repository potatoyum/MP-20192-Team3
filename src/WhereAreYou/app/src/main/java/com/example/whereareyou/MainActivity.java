package com.example.whereareyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* String next[] = {};
        ArrayList<String> list = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.hospitalList);
        int i =0;
        try {
            AssetManager am = getResources().getAssets();
            CSVReader reader = new CSVReader(new InputStreamReader(am.open("hospital.csv")));
            while(true) {
                next = reader.readNext();
                if(next != null) {
                    list.add(next[2]);
                    i++;
                    System.out.println(next[1]);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this,
                R.layout.item,
                R.id.name,
                list
        );

        listView.setAdapter(adapter);
    }
*/

    }
}