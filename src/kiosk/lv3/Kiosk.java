package kiosk.lv3;

import java.util.ArrayList;
import java.util.List;

// 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스
//- [ ]  콘솔에 햄버거 메뉴를 출력합니다.
//- [ ]  사용자의 입력을 받아 메뉴를 선택하거나 프로그램을 종료합니다.
//- [ ]  유효하지 않은 입력에 대해 오류 메시지를 출력합니다.
//- [ ]  `0`을 입력하면 프로그램이 ‘뒤로가기’되거나 ‘종료’됩니다.
public class Kiosk {
    private MenuItem shackburger = new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
    private MenuItem smokeshack = new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
    private MenuItem cheeseburger = new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
    private MenuItem hamburger = new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거");

    private List<MenuItem> menuItem = new ArrayList<>();

    public Kiosk(){
        menuItem.add(shackburger);
        menuItem.add(smokeshack);
        menuItem.add(cheeseburger);
        menuItem.add(hamburger);
    }

    public void start(){
        // 메뉴 출력
        // printf() 또는 String.format()에 사용되는 형식 지정자로 일정 간격으로 출력
        // %-15s : 좌측 정렬 최소 15칸 확보
        // %-4.1f : 최소 4칸 확보 및 소수점 1까지 출력

        int index = 1;
        for (MenuItem item : menuItem) {
            System.out.printf("%d. %-15s | W %-4.1f | %s\n", index, item.getName(), item.getPrice(), item.getDescription());
            index++;
        }
        System.out.printf("%d. %-14s | %-4s\n", 0, "종료", "종료");

        // 입력 처리
    }
}
