package com.example.yana_kryvoshey_hw1;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yana_kryvoshey_hw1.Fragments.Fragment_List;
import com.example.yana_kryvoshey_hw1.Fragments.Fragment_Map;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MySp {


    public MySp(Fragment_List context) {

    }

    public MySp(Fragment_Map context) {

    }

    public interface KEYS {
        public static final String TOP_TEN_NAME = "TOP_TEN_NAME";
        public static final String TOP_TEN_SCORE = "TOP_TEN_SCORE";
        public static final String TOP_TEN_LATITUDE = "TOP_TEN_LATITUDE";
        public static final String TOP_TEN_LONGITUDS = "TOP_TEN_LONGITUDS";


    }

    private static MySp instance;
    private SharedPreferences prefs;

    public static MySp getInstance() {
        return instance;
    }

    public MySp() {
    }
    public MySp(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("MY_SP", Context.MODE_PRIVATE);
    }

    public static void init(Fragment_List context) {
        if (instance == null) {
            instance = new MySp(context);
        }
    }
    public static void init(Context context) {
        if (instance == null) {
            instance = new MySp(context);
        }
    }

    public static void init(Fragment_Map context) {
        if (instance == null) {
            instance = new MySp(context);
        }
    }
    public static void init(OnMapReadyCallback onMapReadyCallback) {
    }
    //// ---------------------------------------------------------- ////


    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putInt(String key, int score) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, score);
        editor.apply();
    }
    public void putlatitude(String key, double latitude) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, Double.doubleToRawLongBits(latitude));
        editor.apply();
    }
    public void putlongituds(String key, double longituds) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, Double.doubleToRawLongBits(longituds));
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public int getIntback(String key,int def) {
        return prefs.getInt(key,def);
    }

    public double getlatitude(String key,int def){
        return Double.longBitsToDouble(prefs.getLong(key, def));}

    public double getlongituds(String key,int def){
        return Double.longBitsToDouble(prefs.getLong(key, def));}


}
