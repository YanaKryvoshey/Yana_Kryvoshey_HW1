package com.example.yana_kryvoshey_hw1.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yana_kryvoshey_hw1.MySp;
import com.example.yana_kryvoshey_hw1.Objects.Record;
import com.example.yana_kryvoshey_hw1.R;

import java.util.ArrayList;
import java.util.ResourceBundle;
public class Fragment_List extends Fragment {

    private TextView listfragment_LBL_TopTen;
    ArrayList<Record> allRecords = new ArrayList<Record>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        printTopTen();
        return view;
    }

// print the name and the score of top ten
    private void printTopTen() {

        for (int i=0; i<10;i++){
            MySp.init(this);
            String name = MySp.getInstance().getString(MySp.KEYS.TOP_TEN_NAME + i,"NONE");
            int point = MySp.getInstance().getIntback(MySp.KEYS.TOP_TEN_SCORE+i, -1);
            Log.d("pttt", "Fragment_List TOP_TEN " + i + "  "+ name + "  " +""+ point);
            listfragment_LBL_TopTen.setText(listfragment_LBL_TopTen.getText() + "" + (i+1) +".  " + name + "  "+""+ point + "\n");
        }
    }
    private void findViews(View view) {
        listfragment_LBL_TopTen = view.findViewById(R.id.listfragment_LBL_TopTen);
    }

}