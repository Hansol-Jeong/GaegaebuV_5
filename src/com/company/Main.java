package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            menu.loginMenu();
            menu.selectActionMenu();
        }
    }
}
