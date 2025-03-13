package kiosk.lv6;

import java.util.Scanner;

// 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스
//- [ ]  콘솔에 햄버거 메뉴를 출력합니다.
//- [ ]  사용자의 입력을 받아 메뉴를 선택하거나 프로그램을 종료합니다.
//- [ ]  유효하지 않은 입력에 대해 오류 메시지를 출력합니다.
//- [ ]  `0`을 입력하면 프로그램이 ‘뒤로가기’되거나 ‘종료’됩니다.
public class Kiosk {

    public Kiosk() {
    }

    public void start() {
        // 스캐너 선언
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        // 반복문 시작
        int num = 123;
        do {
            try {
                System.out.println("[ MAIN MENU ]");
                // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
                menu.displayCategory();
                num = sc.nextInt();
                System.out.println();

                if(num==1){
                    System.out.println("[ SHAKESHACK MENU ]");
                    menu.displayMenuItems();
                    num = sc.nextInt();
                    if(num!=0){
                        menu.selectMenu(menu.getMenuItem().get(num-1));
                        System.out.println();
                    }
                }
                // 입력 받은 숫자가 올바르다면 인덱menu.selectMenu(menu.getMenuItem().get(num));스로 활용하여 List에 접근하기
                // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력
                // 숫자 입력 받기
                // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
                // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.

            } catch (RuntimeException e) { // 유효하지 않은 입력에 대한 예외처리
                System.out.println("!!!숫자를 입력해주세요!!!");
                System.out.println();
                sc.nextLine(); // 버퍼 비우기
                continue;
            }
        } while (num != 0);

    }
}
