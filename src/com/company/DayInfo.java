package com.company;

import java.util.HashMap;

public class DayInfo {
    int month=0;
    int day=0;
    int income=0;
    int spent=0;
    HashMap<String, Integer> incomeMap = new HashMap<>();
    HashMap<String, Integer> spentMap = new HashMap<>();
    public DayInfo(int month, int day) {
        this.month = month; this.day = day;
    }
    public void out() {
        DayInfoHandler dayInfoHandler = new DayInfoHandler(this);
        System.out.printf("  ||%3s월%3s일 수입: %10d원 소비: %10d원 ",month+1, day+1, dayInfoHandler.getIncome(), dayInfoHandler.getSpent());
        System.out.printf("일별 통계수치: 수입 비율: %s",dayInfoHandler.dailyIncomePercentage());
        System.out.print("% ");
        System.out.printf("지출 비율: %s",dayInfoHandler.dailySpentPercentage());
        System.out.print("% ||");
    }
}
