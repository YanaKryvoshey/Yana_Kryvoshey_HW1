package com.example.yana_kryvoshey_hw1.Objects;

public class Record implements Comparable<Record>{

    private String name = "";
    private int score = 0;
    private double latitude;
    private double longituds;

    public Record() { }

    public Record(String name, int score,double latitude,double longituds) {
        this.name = name;
        this.score = score;
        this.latitude  = latitude;
        this.longituds = longituds;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public double getLongituds() {
        return longituds;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }


    @Override
    public int compareTo(Record o) {
        return o.getScore() - this.getScore() ;
    }
}
