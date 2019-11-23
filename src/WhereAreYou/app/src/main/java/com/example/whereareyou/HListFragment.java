package com.example.whereareyou;


import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HListFragment extends Fragment {
    ListView listView;
    Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity)
            activity = (Activity) context;
    }

    public HListFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hlist, container, false);

        listView = (ListView)v.findViewById(R.id.hospitalList);

        String next[] = {};
        ArrayList<String> list = new ArrayList<String>();


        int i =0;


        try {
            AssetManager am = getActivity().getResources().getAssets();
            CSVReader reader = new CSVReader(new InputStreamReader(am.open("hospital.csv")));
            while(true) {
                next = reader.readNext();
                if(next != null) {
                    if(i !=0){
                        list.add(next[2]);
                    }
                   else{
                       i++;
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                getActivity(),
                R.layout.item,
                R.id.name,
                list
        );

        listView.setAdapter(adapter);

        return v;
    }

}
