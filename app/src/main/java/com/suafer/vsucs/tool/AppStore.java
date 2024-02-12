package com.suafer.vsucs.tool;


import androidx.fragment.app.Fragment;

import com.suafer.vsucs.enumpack.DayType;
import com.suafer.vsucs.enumpack.PageType;
import com.suafer.vsucs.R;
import com.suafer.vsucs.enumpack.ScheduleType;
import com.suafer.vsucs.enumpack.SubjectType;
import com.suafer.vsucs.enumpack.ThemeType;
import com.suafer.vsucs.page.AdditionalPage;
import com.suafer.vsucs.page.SchedulePage;
import com.suafer.vsucs.classes.Setting;
import com.suafer.vsucs.page.TimePage;
import com.suafer.vsucs.enumpack.VersionStatus;
import com.suafer.vsucs.page.Page;
import com.suafer.vsucs.schedule.Day;
import com.suafer.vsucs.schedule.Schedule;
import com.suafer.vsucs.schedule.Week;

import java.util.ArrayList;
import java.util.List;

public class AppStore{


    public static final int[] backgrounds = new int[]{
            R.drawable.background_space,
            R.drawable.background_sea,
            R.drawable.background_crystals,
            R.drawable.background_lens,
            R.drawable.background_sand,
            R.drawable.background_aircraft,
            R.drawable.background_plants,
            R.drawable.background_texture,
            R.drawable.background_circles
    };

    public static int getBackground(int id){
        return backgrounds[id];
    }


    public static List<Fragment> createFragmentList(List<Page> pages){
        List<Fragment> list = new ArrayList<>();
        for(Page page : pages){
            if(page.getStatus()){
                if(page.getPageType()==PageType.Timer){
                    list.add(new TimePage());
                }
                if(page.getPageType()==PageType.Schedule){
                    list.add(new SchedulePage());
                }
                if(page.getPageType()==PageType.Additional){
                    list.add(new AdditionalPage());
                }
            }

        }
        return list;
    }
    public static Setting createDefoult(){
        List<Page> list = new ArrayList<>();
        list.add(new Page(PageType.Timer, true));
        list.add(new Page(PageType.Schedule, true));
        list.add(new Page(PageType.Additional,true));
        return new Setting(1, ThemeType.Black, DayType.Numerator, null, list, createSchedule1());
    }
    public static String getPageName(PageType pageType){
        if(pageType==PageType.Timer){return "Таймер";}
        if(pageType==PageType.Schedule){return "Расписание";}
        if(pageType==PageType.Additional){return "Дополнительное";}

        return "";
    }

    public static int getVersionImage(VersionStatus versionStatus){
        if(versionStatus == VersionStatus.New){
            return R.drawable.icon_ok_green;
        }else{
            if(versionStatus == VersionStatus.Outdated){
                return R.drawable.icon_no_red;
            }else{
                return R.drawable.icon_wifi_off_yellow;
            }
        }
    }

    public static int getVersionString(VersionStatus versionStatus){
        if(versionStatus == VersionStatus.New){
            return R.string.vertsion_new;
        }else{
            if(versionStatus == VersionStatus.Outdated){
                return R.string.vertsion_outdated;
            }else{
                return R.string.vertsion_cannot_determined;
            }
        }
    }


    public static Schedule createSchedule1(){
        Schedule schedule = new Schedule("10.1", ScheduleType.First);
        Week week = new Week(DayType.Numerator);
 /**Числитель */

        Day day = new Day("Понедельник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Организационное и правовое обеспечение иб.","505П","Филиппова Н.В."});
        day.addSubject(new String[]{"Организационное и правовое обеспечение иб.","307П","Филиппова Н.В."});
        day.addSubject(new String[]{"Физкультура",null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Вторник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","-","Иванков А.Ю."});
        day.addSubject(new String[]{"Методы вычислений","-","Крыловецкий А.А."});
        day.addSubject(new String[]{"Теория инф. процессов и систем","-","Десятирикова Е.Н."});
        day.addSubject(new String[]{"Электротехника","-","Зуев С.А."});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Среда");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Английский","303П","Вострикова И.Ю."});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","290","Иванков А.Ю."});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Методы вычислений","301П","Бородин А.В."});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Четверг");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Пятница");
        day.addSubject(new String[]{"ЯИСП","290","Чекмарев А.И."});
        day.addSubject(new String[]{"Культурология","316П","Якушкина Е.И."});
        day.addSubject(new String[]{"Гуманитарные аспекты иб.","305П","Филиппова Н.В."});
        day.addSubject(new String[]{"Физкультура",null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Суббота");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Воскресенье");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);



        schedule.addWeek(week);

/**Знаменатель */
        week = new Week(DayType.Denominator);

        day = new Day("Понедельник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Физкультура",null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);


        day = new Day("Вторник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Культурология","-","Якушкина Е.И."});
        day.addSubject(new String[]{"Организационное и правовое обеспечение иб.","-","Филиппова Н.В."});
        day.addSubject(new String[]{"ЯИСП","-","Чекмарев А.И."});
        day.addSubject(new String[]{"Теория инф. процессов и систем","-","Десятирикова Е.Н."});
        day.addSubject(new String[]{"Гуманитарные аспекты иб.","-","Филиппова Н.В."});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Среда");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Электротехника","307П","Зуев С.А."});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","290","Иванков А.Ю."});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","290","Иванков А.Ю."});
        day.addSubject(new String[]{"Методы вычислений","301П","Бородин А.В."});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Четверг");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Пятница");
        day.addSubject(new String[]{"ЯИСП","293","Чекмарев А.И."});
        day.addSubject(new String[]{"Английский","303П","Вострикова И.Ю."});
        day.addSubject(new String[]{"Гуманитарные аспекты иб.","305П","Филиппова Н.В."});
        day.addSubject(new String[]{"Физкультура","-","-"});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Суббота");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Воскресенье");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        schedule.addWeek(week);

        return schedule;
    }


    public static Schedule createSchedule2(){
        Schedule schedule = new Schedule("10.2", ScheduleType.Second);
        Week week = new Week(DayType.Numerator);
        /**Числитель */

        Day day = new Day("Понедельник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Организационное и правовое обеспечение иб.","505П","Филиппова Н.В."});
        day.addSubject(new String[]{"Организационное и правовое обеспечение иб.","307П","Филиппова Н.В."});
        day.addSubject(new String[]{"Физкультура",null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Вторник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","-","Иванков А.Ю."});
        day.addSubject(new String[]{"Методы вычислений","-","Крыловецкий А.А."});
        day.addSubject(new String[]{"Теория инф. процессов и систем","-","Десятирикова Е.Н."});
        day.addSubject(new String[]{"Электротехника","-","Зуев С.А."});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Среда");
        day.addSubject(new String[]{"Английский","303П","Вострикова И.Ю."});
        day.addSubject(new String[]{"Методы вычислений","297","Бородин А.В."});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","290","Иванков А.Ю."});
        day.addSubject(new String[]{"Электротехника","-","Зуев С.А."});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Четверг");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Пятница");
        day.addSubject(new String[]{"ЯИСП","290","Чекмарев А.И."});
        day.addSubject(new String[]{"Культурология","316П","Якушкина Е.И."});
        day.addSubject(new String[]{"Гуманитарные аспекты иб.","305П","Филиппова Н.В."});
        day.addSubject(new String[]{"Физкультура",null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Суббота");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Воскресенье");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);



        schedule.addWeek(week);

/**Знаменатель */
        week = new Week(DayType.Denominator);

        day = new Day("Понедельник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Физкультура",null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);


        day = new Day("Вторник");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Культурология","-","Якушкина Е.И."});
        day.addSubject(new String[]{"Организационное и правовое обеспечение иб.","-","Филиппова Н.В."});
        day.addSubject(new String[]{"ЯИСП","-","Чекмарев А.И."});
        day.addSubject(new String[]{"Теория инф. процессов и систем","-","Десятирикова Е.Н."});
        day.addSubject(new String[]{"Гуманитарные аспекты иб.","-","Филиппова Н.В."});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Среда");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{"Методы вычислений","301П","Бородин А.В."});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","290","Иванков А.Ю."});
        day.addSubject(new String[]{"Пакет п. программ для н. исследований","290","Иванков А.Ю."});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Четверг");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Пятница");
        day.addSubject(new String[]{"Английский","303П","Вострикова И.Ю."});
        day.addSubject(new String[]{"ЯИСП","293","Чекмарев А.И."});
        day.addSubject(new String[]{"Гуманитарные аспекты иб.","305П","Филиппова Н.В."});
        day.addSubject(new String[]{"Физкультура","-","-"});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Суббота");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        day = new Day("Воскресенье");
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});
        day.addSubject(new String[]{null,null,null});week.add(day);

        schedule.addWeek(week);

        return schedule;
    }

    public static String getDayName(int id){
        if(id==0)return "Понедельник";
        if(id==1)return "ВтОрник";
        if(id==2)return "Среда";
        if(id==3)return "Четверг";
        if(id==4)return "Пятница";
        return "СуббОта";
    }
}
