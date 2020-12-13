package com.example.yana_kryvoshey_hw1.Objects;

import android.widget.ImageView;

public class Card {
    private String name;
    private int Cardpoint;

public Card(){}
    public Card(String name) {
        this.name = name;
        this.Cardpoint = Integer.parseInt(name.replaceAll("[^0-9]", ""));
    }

    public int getCardpoint() {
        return Cardpoint;
    }
    public String getName() {
        return name;
    }
}
