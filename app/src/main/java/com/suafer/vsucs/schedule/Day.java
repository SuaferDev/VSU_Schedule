package com.suafer.vsucs.schedule;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private final String name;
    private List<String[]> subjects;

    public Day(String name,  List<String[]> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public Day(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public List<String[]> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String[]> subjects) {
        this.subjects = subjects;
    }

    public String[] getSubject(int i){
        return subjects.get(i);
    }

    public String getName() {
        return name;
    }

    public void addSubject(String[] arr){
        this.subjects.add(arr);
    }
}
