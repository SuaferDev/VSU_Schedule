package com.suafer.vsucs.classes;

import com.suafer.vsucs.enumpack.DayType;
import com.suafer.vsucs.enumpack.PageType;
import com.suafer.vsucs.enumpack.ThemeType;
import com.suafer.vsucs.page.Page;
import com.suafer.vsucs.schedule.Schedule;

import java.util.List;

public class Setting {
    private int background;
    private ThemeType theme;
    private DayType dayType;
    private String pathBackground;
    private List<Page> pages;
    private Schedule schedule;

    public Setting(int background, ThemeType theme, DayType dayType, String pathBackground, List<Page> pages, Schedule schedule) {
        this.background = background;
        this.theme = theme;
        this.dayType = dayType;
        this.pathBackground = pathBackground;
        this.pages = pages;
        this.schedule = schedule;
    }

    public DayType getDayType() {
        return dayType;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Page getPage(int i){
        return pages.get(i);
    }

    public void deletePage(PageType pageType){
        for(int i=0; i<pages.size(); i++){
            if(pages.get(i).getPageType()==pageType){
                pages.get(i).setStatus(false);
                return;
            }
        }
    }

    public void addPage(PageType pageType){
        for(Page page : pages){
            if(page.getPageType() == pageType){
                page.setStatus(true);
                return;
            }
        }
    }

    public String getPathBackground() {
        return pathBackground;
    }

    public void setPathBackground(String pathBackground) {
        this.pathBackground = pathBackground;
    }

    public void setFragments(List<Page> pages) {
        this.pages = pages;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public ThemeType getTheme() {
        return theme;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
    }

}
