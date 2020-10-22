package com.company;

public class CalenderView {
    Account.Calender calender;

    public CalenderView(Account account) {
        this.calender = account.calender;
    }
    public void viewMonth() {
        for(int i = 0; i<calender.monthList.toArray().length; i++) {
            CalenderHandler calenderHandler = new CalenderHandler(calender);
            int monthlySum = calenderHandler.getMonthlySum(i);
            if(monthlySum<0){
                System.err.printf("%3d 월: %-8d", i+1 , monthlySum);
            }
            else
                System.out.printf("%3d 월: %-8d", i+1 , monthlySum);

            System.out.println();
        }
    }
    void viewDays(int month) {
        CalenderHandler calenderHandler = new CalenderHandler(calender);
        System.out.println(month+"월 조회");
        System.out.printf("통계수치: 수입 비율: %20s",calenderHandler.getMonthlyIncomePercentage(month-1));
        System.out.print("% ");
        System.out.printf("지출비율: %20s",calenderHandler.getMonthlySpentPercentage(month-1));
        System.out.print("%");
        System.out.println();
        int i = 0;
        for(DayInfo day : calender.monthList.get(month-1)) {
            day.out();
            if(i%2==0)
                System.out.println();
            i++;
        }
    }
}
