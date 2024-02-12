package com.suafer.vsucs.schedule;

import com.suafer.vsucs.enumpack.DayType;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private final DayType dayType;

    private List<Day> days;

    public Week(DayType dayType, List<Day> days) {
        this.dayType = dayType;
        this.days = days;
    }

    public Week(DayType dayType) {
        this.dayType = dayType;
        this.days = new ArrayList<>();
    }

    public DayType getDayType() {return dayType;}
    public Day getDays(int id) {
        if(id>=0 && id<=6){
            return days.get(id);
        }
        return null;
    }
    public List<Day> getDays() {
        return days;
    }
    public void add(Day day){
        this.days.add(day);
    }


}
