package kiosk.lv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuItem shackburger = new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        MenuItem smokeshack = new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        MenuItem cheeseburger = new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        MenuItem hamburger = new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거");

        List<MenuItem> menuItem = new ArrayList<>();
        menuItem.add(shackburger);
        menuItem.add(smokeshack);
        menuItem.add(cheeseburger);
        menuItem.add(hamburger);

        Scanner sc = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");

        // printf() 또는 String.format()에 사용되는 형식 지정자로 일정 간격으로 출력
        // %-15s : 좌측 정렬 최소 15칸 확보
        // %-4.1f : 최소 4칸 확보 및 소수점 1까지 출력

        int index = 1;
        for (MenuItem item : menuItem) {
            System.out.printf("%d. %-15s | W %-4.1f | %s\n", index, item.getName(), item.getPrice(), item.getDescription());
            index++;
        }
        System.out.printf("%d. %-14s | %-4s", 0, "종료", "종료");


    }
}
