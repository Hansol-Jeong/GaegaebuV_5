package com.company;

import com.company.DayInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Account implements Serializable {
    static final long serialVersionUID = 1L;
    String ID;
    String pass;
    Calender calender;
    public Account() {
    }
    @Override
    public String toString() {
        return ID;
    }

    class Calender implements Serializable{
        static final long serialVersionUID = 1L;
        List<List<DayInfo>> monthList = new ArrayList<>();
        public Calender() {
            for(int i = 0; i<12; i++) {
                List<DayInfo> dayInfoList = new ArrayList<>();
                for(int j = 0; j<31; j++) {
                    DayInfo dayInfo = new DayInfo(i,j);
                    dayInfoList.add(dayInfo);
                }
                monthList.add(dayInfoList);
            }
        }
    }

    public static void main(String[] args) {
    }
}
