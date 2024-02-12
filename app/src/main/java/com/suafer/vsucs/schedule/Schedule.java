package com.suafer.vsucs.schedule;

import com.suafer.vsucs.enumpack.DayType;
import com.suafer.vsucs.enumpack.ScheduleType;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private String name;
    private ScheduleType scheduleType;

    private List<Week> weeks;

    public Schedule(String name, List<Week> weeks, ScheduleType scheduleType) {
        this.name = name;
        this.weeks = weeks;
        this.scheduleType = scheduleType;
    }

    public Schedule(String name, ScheduleType scheduleType) {
        this.name = name;
        this.weeks = new ArrayList<>();
        this.scheduleType = scheduleType;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWeek(Week week){
        this.weeks.add(week);
    }

    public List<Day> getDaysByDayType(DayType dayType){
        for(Week week : weeks){
            if(week.getDayType()==dayType){
                return week.getDays();
            }
        }
        return null;
    }
}
