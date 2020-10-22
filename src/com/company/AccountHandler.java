package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class AccountHandler {
    Account account;
    Scanner scan = new Scanner(System.in);

    public AccountHandler(Account account) {
        this.account = account;
    }

    public void login() {
        String ID;
        String pass;
        System.out.println("아이디를 입력하세요");
        ID = scan.nextLine();
      //  scan.nextLine();
        System.out.println("비밀번호를 입력하세요");
        pass = scan.nextLine();
       // scan.nextLine();
        File filePath = new File("C:/Test");
        File targetFile = null;
        for (File file : filePath.listFiles()) {
            if (file.getName().equals(ID)) {
                targetFile = file;
                break;
            }
        }
        Account readA=null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(targetFile))) {
            Object read= in.readObject();
            readA =(Account) read;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(readA.pass.equals(pass)) {
            account = readA;
            System.out.println("환영합니다" + account + "님");
        }
        else {
            account = null;
            System.out.println("비밀번호가 틀렸습니다. 다시 로그인 시도를 해주세요");
        }
    }
    public void loginFail() {
        System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
        System.out.println("1. 재시도 2. 새 계정 만들기");
        int number;
        number = scan.nextInt();
        switch (number) {
            case 1:return;
            case 2:signUp();
        }
    }
    public void signUp () {
        String ID;
        String pass;
        account = new Account();
        System.out.println("새로운 아이디를 입력하세요");
        ID = scan.nextLine(); scan.nextLine();
        System.out.println("새로운 비밀번호를 입력하세요");
        pass = scan.nextLine(); scan.nextLine();
        account.ID = ID; account.pass = pass;
        File targetFile = null;
        File filePath = new File("C:\\Test");
        File newFile = new File(filePath, ID);
        for (File file : filePath.listFiles()) {
            if (file.getName().equals(ID)) {
                targetFile = file;
                break;
            }
        }
        if(targetFile==null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newFile))) {
                out.writeObject(account);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("해당 계정이 이미존재합니다");
            return;
        }
    }
}
