package kiosk.lv4;

import kiosk.lv6.Users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스
//- [ ]  콘솔에 햄버거 메뉴를 출력합니다.
//- [ ]  사용자의 입력을 받아 메뉴를 선택하거나 프로그램을 종료합니다.
//- [ ]  유효하지 않은 입력에 대해 오류 메시지를 출력합니다.
//- [ ]  `0`을 입력하면 프로그램이 ‘뒤로가기’되거나 ‘종료’됩니다.
public class Kiosk {

    private List<MenuItem> cart = new ArrayList<>();

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
                // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
                // 장바구니에 물건이 들어 있으면 아래와 같이 [ ORDER MENU ] 가 추가로 출력됩니다.
                // 만약에 장바구니에 물건이 들어 있지 않다면 [ ORDER MENU ] 가 출력되지 않습니다.
                // 미출력일 때 4,5 번을 누르면 예외를 던저줘야 합니다.
                if(cart.isEmpty()){
                    menu.displayCategory(0);
                }else{
                    System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
                    System.out.println();
                    menu.displayCategory(1);
                }
                num = sc.nextInt();
                System.out.println();

                // 햄버거 메뉴 선택 시
                if (num == 1) {
                    // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력
                    menu.displayMenuItems();
                    num = sc.nextInt(); // 숫자 입력 받기
                    if (num != 0) {
                        // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                        // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.
                        menu.selectMenu(menu.getMenuItem().get(num - 1));
                        System.out.println();
                        addCart(menu.getMenuItem().get(num-1));
                        sc.nextLine(); // 버퍼 비우기
                    }
                }else if(num==4){ // 주문 메뉴 선택 시
                    num = orderMenu(menu.getMenuItem().get(num-1));
                    sc.nextLine(); // 버퍼 비우기
                    if(num!=0){
                        continue;
                    }
                }else if(num==5){
                    cancelItem();
                }


            } catch (RuntimeException e) { // 유효하지 않은 입력에 대한 예외처리
                System.out.println("!!!숫자를 입력해주세요!!!");
                System.out.println();
                sc.nextLine(); // 버퍼 비우기
                continue;
            }
        } while (num != 0);
    }

    public void addCart(MenuItem item) {

        System.out.printf("\"%-15s | W %-4.1f | %s\"", item.getName(), item.getPrice(), item.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.printf("%-15s %-15s\n", "1. 확인", "2. 취소");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        try {
            if (choice == 1) {
                cart.add(item);
                System.out.printf("%s %s\n", item.getName(), "이 장바구니에 추가되었습니다.");
                System.out.println();
            }
        } catch (RuntimeException ex) {
            System.out.println("\"1 또는 2를 입력해주세요\"");
            System.out.println();
            sc.nextLine(); // 버퍼 비우기
        }
    }

    public void cancelItem(){
        Scanner sc = new Scanner(System.in);
        int[] index = {1};
        // int index = 1 이 안되는 이유
        // 람다식 내부에서는 지역 변수를 변경 할 수 없음.
        System.out.println("취소할 주문을 선택해 주세요. (취소 : enter)");
        System.out.println("[ Cart ]");
        cart.stream().forEach(item -> System.out.printf("%d. %-15s | W %-4.1f | %s\n", index[0]++, item.getName(), item.getPrice(), item.getDescription()));

        index[0] = 1;
        String input = sc.nextLine();
        if(!input.isEmpty()){
            cart.remove((Integer.parseInt(input)-1));
            System.out.println("[ Cart ]");
            cart.stream().forEach(item -> System.out.printf("%d. %-15s | W %-4.1f | %s\n", index[0]++ , item.getName(), item.getPrice(), item.getDescription()));
        }
    }

    public int orderMenu(MenuItem item){
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        for(MenuItem i : cart){
            System.out.printf("\"%-15s | W %-4.1f | %s\"\n", i.getName(), i.getPrice(), i.getDescription());
        }

        System.out.println();
        System.out.println("[ Total ]");

        Double total = (double) 0;
        for(MenuItem i : this.cart){
            total += i.getPrice();
        }
        System.out.printf("W %-4.1f\n", total);
        System.out.printf("%-15s %-15s\n", "1. 주문", "2. 메뉴판");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        try{
            if(choice==1){
                System.out.println("할인 정보를 입력해주세요.");
                System.out.printf("%-10s : %-4d%%\n","1. 국가유공자", 10);
                System.out.printf("%-10s : %-4d%%\n","2. 군인", 5);
                System.out.printf("%-10s : %-4d%%\n","3. 학생", 3);
                System.out.printf("%-10s : %-4d%%\n","4. 일반", 0);

                int n = sc.nextInt();
                switch (n){
                    case 1 :
                        total -= total*Users.국가유공자.getDiscount();
                        break;
                    case 2 :
                        total -= total*Users.군인.getDiscount();
                        break;
                    case 3 :
                        total -= total*Users.학생.getDiscount();
                        break;
                    case 4 :
                        total -= total*Users.일반.getDiscount();
                        break;
                    default:
                        System.out.println("적용된 할인이 없습니다.");
                        break;
                }

                System.out.printf("%s W %-4.1f %s\n", "주문이 완료되었습니다. 금액은",total,"입니다.");
                return 0; // 주문 후 키오스크 종료
            }
        } catch (RuntimeException e) {
            System.out.println("\"1 또는 2를 입력해주세요\"");
            System.out.println();
            sc.nextLine(); // 버퍼 비우기
        }

        return 123;
    }

}
