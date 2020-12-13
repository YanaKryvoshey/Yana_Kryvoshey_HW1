package com.example.yana_kryvoshey_hw1;

import android.content.Context;
import android.util.Log;

import com.example.yana_kryvoshey_hw1.Objects.Record;

import java.util.ArrayList;
import java.util.Collections;

public class MySpShareInformation {

    //get all the records from the ShareInformation
    public static void saveTheRecords(ArrayList<Record> allRecords, Context context) {
        for (int i=0; i<10;i++){
            MySp.init(context);
            MySp.getInstance().putString(MySp.KEYS.TOP_TEN_NAME+i, allRecords.get(i).getName());
            MySp.getInstance().putInt(MySp.KEYS.TOP_TEN_SCORE+i ,allRecords.get(i).getScore());
            MySp.getInstance().putlatitude(MySp.KEYS.TOP_TEN_LATITUDE + i,allRecords.get(i).getLatitude());
            MySp.getInstance().putlongituds(MySp.KEYS.TOP_TEN_LONGITUDS + i,allRecords.get(i).getLongituds());
            Log.d("pttt", "MySpShareInformation in saveTheRecords TOP_TEN " + i + "  "+ allRecords.get(i).getName() + "  "+ allRecords.get(i).getScore() +" latitude: "+ allRecords.get(i).getLatitude() +" longituds: "+ allRecords.get(i).getLongituds());
        }
    }
    //save all the records to the ShareInformation
    public static ArrayList<Record> getAllTheRecords(Context context) {
        ArrayList<Record> allRecords = new ArrayList<>();
        MySp.init(context);
        for (int i=0;i<10;i++){
            String name = MySp.getInstance().getString(MySp.KEYS.TOP_TEN_NAME + i,"NONE");
            int point = MySp.getInstance().getIntback(MySp.KEYS.TOP_TEN_SCORE + i,-1);
            double latitude = MySp.getInstance().getlatitude(MySp.KEYS.TOP_TEN_LATITUDE + i,-1);
            double longituds = MySp.getInstance().getlongituds(MySp.KEYS.TOP_TEN_LONGITUDS + i,-1);
            Log.d("pttt", "MySpShareInformation in getAllTheRecords TOP_TEN " + i + "  "+ name + "  "+ point +" latitude: "+ latitude +" longituds: "+ longituds);
            allRecords.add(new Record(name, point,latitude,longituds));
        }
        Collections.sort(allRecords);
        return allRecords;
    }
}
