package com.company;

import java.util.HashMap;

public class DayInfoHandler {
    DayInfo day;
    public DayInfoHandler(DayInfo day) {
        this.day = day;
    }
    public void addIncome(Integer mount) {
        day.income += mount;
//        day.notifyAllObservers();
    }
    public void addIncome(Integer mount, String category) {
        day.income += mount;
//        day.notifyAllObservers();
        int count = 0;
        for (String key: day.incomeMap.keySet()) {
            if(key.equals(category)) {//이미 있는 키와 카테고리가 일치한다면
                int value = day.incomeMap.get(key);
                value+=mount;
                day.incomeMap.put(key,value);//그 카테고리에 금액 추가
                count++;
            }
        }
        //만약 이 과정이 끝낫는데도 카운트가 0이라면
        if(count == 0)
            day.incomeMap.put(category,mount);
//        day.notifyAllObservers();//캘린더업데이트
//        day.notifyAllObservers2();//계정 저장
    }
    public void addSpent(Integer mount) {
        day.spent +=mount;
//        day.notifyAllObservers();
    }
    public void addSpent(Integer mount, String category) {
        day.spent +=mount;
//        day.notifyAllObservers();
        int count = 0;

        for (String key: day.spentMap.keySet()) {
            if(key.equals(category)) {//이미 있는 키와 카테고리가 일치한다면
                int value = day.spentMap.get(key);
                value+=mount;
                day.spentMap.put(key,value);//그 카테고리에 금액 추가
                count++;
            }
        }
        if(count == 0)
            day.spentMap.put(category,mount);
//        day.notifyAllObservers();
//        day.notifyAllObservers2();
    }
    public int getDailyTotal() {
        return day.income-day.spent;
    }
    public int getIncome() {
        return day.income;
    }

    public int getSpent() {
        return day.spent;
    }
    public HashMap dailyIncomePercentage() {
        HashMap<String, Integer> percentage= new HashMap<>();
//        percentage = day.incomeMap; //이 해쉬맵을 건든다는게아니라 값만 받아온다는 거였는데 왜 자체값이 다바뀌어버리지
        for(String key : day.incomeMap.keySet()) {
            int value = day.incomeMap.get(key);
            value = (int)(((float)value/getIncome())*100);
            percentage.put(key,value);
        }
        return percentage;
    }
    public HashMap dailySpentPercentage() {
        HashMap<String, Integer> percentage = new HashMap<>();
//        percentage = day.incomeMap; //이 해쉬맵을 건든다는게아니라 값만 받아온다는 거였는데 왜 자체값이 다바뀌어버리지
        for(String key : day.spentMap.keySet()) {
            int value = day.spentMap.get(key);
            value = (int)(((float)value/getSpent())*100);
            percentage.put(key,value);
        }
        return percentage;
    }
    public void writingMemo() {}



}
