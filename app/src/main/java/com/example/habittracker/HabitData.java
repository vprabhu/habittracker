package com.example.habittracker;

/**
 * Created by root on 5/20/17.
 */

public class HabitData {

    private String habitName;
    private String habitResoource;
    private int habitTimeSpent;
    private String habitModuleName;
    private int ishabitModuleDone;

    public HabitData(String habitName, String habitResoource, int habitTimeSpent, String habitModuleName, int ishabitModuleDone) {
        this.habitName = habitName;
        this.habitResoource = habitResoource;
        this.habitTimeSpent = habitTimeSpent;
        this.habitModuleName = habitModuleName;
        this.ishabitModuleDone = ishabitModuleDone;
    }
    public String getHabitName() {
        return habitName;
    }

    public String getHabitResoource() {
        return habitResoource;
    }

    public int getHabitTimeSpent() {
        return habitTimeSpent;
    }

    public String getHabitModuleName() {
        return habitModuleName;
    }

    public int getIshabitModuleDone() {
        return ishabitModuleDone;
    }
}
