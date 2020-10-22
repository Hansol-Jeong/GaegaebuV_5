package com.company;

import java.util.HashMap;

public class CalenderHandler {
    Account.Calender calender;

    public CalenderHandler(Account.Calender calender) {
        this.calender = calender;
    }
    public int getMonthlyIncomeSum (int month) {
        int sum = 0;
        for (DayInfo day :calender.monthList.get(month)) {
            DayInfoHandler dayInfoHandler = new DayInfoHandler(day);
            sum += dayInfoHandler.getIncome();
        }
        return sum;
    }
    public int getMonthlySpentSum (int month) {
        int sum = 0;
        for (DayInfo day : calender.monthList.get(month)) {
            DayInfoHandler dayInfoHandler = new DayInfoHandler(day);
            sum += dayInfoHandler.getSpent();
        }
        return sum;
    }
    public int getMonthlySum(int month) {
        int sum=0;
        for(DayInfo day :calender.monthList.get(month)) {
            DayInfoHandler dayInfoHandler = new DayInfoHandler(day);
            sum+= dayInfoHandler.getDailyTotal();
        }
        return sum;
    }
    public HashMap getMonthlyIncomePercentage (int month) {
        int monthlySum = getMonthlyIncomeSum(month);
        HashMap<String, Integer> standardMap= new HashMap<>();
        standardMap.put("시드머니",1);
        for(DayInfo day : calender.monthList.get(month)) {
            loop:  for(String key : day.incomeMap.keySet()) {
                int count =0;
                for(String standardKey: standardMap.keySet()) {
                    if(standardKey.equals(key)) {
                        int value = standardMap.get(standardKey);
                        value += day.incomeMap.get(key);
                        standardMap.put(standardKey,value);
                        break loop;//어차피 중복값은 없어, 넣어줫다면 바로 담거 돌려야지
                    }
                }
                if (count==0)
                    standardMap.put(key,day.incomeMap.get(key));
            }
        }
        HashMap<String, Integer> percentage = new HashMap<>();
        for(String key: standardMap.keySet()) {
            int value = (int)(((float)standardMap.get(key)/monthlySum)*100);
            percentage.put(key,value);
        }
        return percentage;
    }
    public HashMap getMonthlySpentPercentage (int month) {
        int monthlySum = getMonthlySpentSum(month);
        HashMap<String, Integer> standardMap= new HashMap<>();
        standardMap.put("시드머니",1);
        for(DayInfo day : calender.monthList.get(month)) {
            loop:  for(String key : day.spentMap.keySet()) {
                int count =0;
                for(String standardKey: standardMap.keySet()) {
                    if(standardKey.equals(key)) {
                        int value = standardMap.get(standardKey);
                        value += day.spentMap.get(key);
                        standardMap.put(standardKey,value);
                        break loop;//어차피 중복값은 없어, 넣어줫다면 바로 담거 돌려야지
                    }
                }
                if (count==0)
                    standardMap.put(key,day.spentMap.get(key));
            }
        }
        HashMap<String, Integer> percentage = new HashMap<>();
        for(String key: standardMap.keySet()) {
            int value = (int)(((float)standardMap.get(key)/monthlySum)*100);
            percentage.put(key,value);
        }
        return percentage;
    }
}
