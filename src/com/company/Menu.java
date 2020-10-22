package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Menu {
    Account account = new Account();
    Scanner scan= new Scanner(System.in);
    public Menu() {
    }
    public HashMap intro() {
        String ID;
        String pass;
        System.out.println("아이디를 입력하세요");
        ID = scan.nextLine(); scan.nextLine();
        System.out.println("비밀번호를 입력하세요");
        pass = scan.nextLine(); scan.nextLine();
        HashMap tempMap = new HashMap();
        tempMap.put(ID,pass);
        return tempMap;
    }
    public void loginMenu() {
        int number;
        AccountHandler accountHandler = new AccountHandler(account);//이게 어떤 계정인지 찾아줘야지.
        System.out.println("1. 로그인 하기");
        System.out.println("2. 회원가입");
        number = scan.nextInt();
        switch (number) {
            case 1: {
                while(account.ID==null) {//로그인 정보가 있을 때 까지
                    accountHandler.login();//로그인시도
                    if (account.ID == null) {
                        accountHandler.loginFail();//실패시 다시시도& 새계정 생성
                   }
                    account = accountHandler.account;
                }
                break;//account 상태 변경}

            }
            case 2: {
                account.ID = null;
                while(account.ID == null) {
                    accountHandler.signUp();
                    account = accountHandler.account;
                    break;
                }
            }
        }
    }
    public void selectActionMenu() {
        int number;
        while(true) {
            System.out.println("1. 나의 자산 현황 보기");
            System.out.println("2. 일별 가계부 쓰기");
            System.out.println("3. 뒤로 가기");
            number = scan.nextInt();
            switch (number) {
                case 1:
                    viewMyAssets();
                    break;//해당 월 쭉 보기
                case 2:
                    write();
                    break;
                case 3:
                    return;
            }
        }
    }
    public void viewMyAssets() {
        CalenderView calenderView = new CalenderView(account);
        calenderView.viewMonth();
        System.out.println("보고 싶은 달을 입력(3. 뒤로 가기)");
        int day = scan.nextInt();
        if (day == 3)
            return;
        calenderView.viewDays(day);

    }
    public void write() {
        System.out.println("가계부를 쓸 달 입력");
        int month = scan.nextInt();
        System.out.println("가계부를 쓸 날 입력");
        int day = scan.nextInt();
        System.out.println(account+" 님의"+ month + "월 " + day +"일 의 가계부 작성");
        DayInfo dayInfo = account.calender.monthList.get(month-1).get(day-1);
        DayInfoHandler dayInfoHandler = new DayInfoHandler(dayInfo);
        dayInfo.out();
        int number;
        while (true) {
            String category="11";
            int mount;
            System.out.println();
            System.out.println("수입 : 1 || 소비 : 2 || 나가기 : 3");
            number = scan.nextInt();
            switch (number) {
                case 1:
                    System.out.println("수입의 종류");
                    category = scan.nextLine();
                    System.out.println();
                    System.out.println("해당 날의 수입");
                    mount = scan.nextInt();
                    dayInfoHandler.addIncome(mount, category);
                    break;
                case 2:
                    System.out.println("지출의 종류");
                    category = scan.nextLine();
                    System.out.println();
                    System.out.println("해당 날의 지출");
                    mount = scan.nextInt();
                    dayInfoHandler.addSpent(mount, category);
                    break;
                case 3:
                    return;
            }
        }
    }


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.intro();
    }
}
