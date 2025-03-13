package kiosk.lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Kiosk kiosk = new Kiosk();

        System.out.println("[ SHAKESHACK MENU ]");
        kiosk.start();

        int num = 123;
        do{
            try{
                num = sc.nextInt();
            } catch (RuntimeException e) { // 유효하지 않은 입력에 대한 예외처리
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine(); // 버퍼 비우기
                continue;
            }
        }while(num != 0);

    }
}
