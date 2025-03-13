package kiosk.lv4;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    // 카테고리 이름 필드 추가
    private String[] category = {"Burgers","Drinks","Desserts","Orders","Cancel"};

    private MenuItem shackburger = new MenuItem(1,"ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
    private MenuItem smokeshack = new MenuItem(2,"SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
    private MenuItem cheeseburger = new MenuItem(3,"Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
    private MenuItem hamburger = new MenuItem(4,"Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거");

    // MenuItem 클래스를 List로 관리
    private List<MenuItem> menuItem = new ArrayList<>();

    public Menu(){
        menuItem.add(shackburger);
        menuItem.add(smokeshack);
        menuItem.add(cheeseburger);
        menuItem.add(hamburger);
    }

    // List를 리턴하는 함수
    public List<MenuItem> getMenuItem() {
        return menuItem;
    }

    public void displayCategory(int on){
        int index = 1;
        System.out.println("[ MAIN MENU ]");
        if(on==0){
            for (String item : this.category) {
                System.out.printf("%d. %s\n", index, item);
                index++;
                if(index== category.length-1){
                    break;
                }
            }
        }else{
            for (String item : this.category) {
                System.out.printf("%d. %s\n", index, item);
                index++;
                if(index==4){
                    System.out.println("[ ORDER MENU ]");
                }
            }
        }
        System.out.printf("%d. %-14s | %-4s\n", 0, "종료", "종료");
    }

    // List에 포함된 MenuItem을 순차적으로 보여주는 함수
    public void displayMenuItems(){
        System.out.println("[ SHAKESHACK MENU ]");

        // 기존에 생성한 Menu의 MenuItem을 조회 할 때 스트림을 사용하여 출력하도록 수정
        menuItem.stream()
                .forEach(item -> System.out.printf("%d. %-15s | W %-4.1f | %s\n", item.getIndex() , item.getName(), item.getPrice(), item.getDescription()));
//        for (MenuItem item : this.menuItem) {
//            System.out.printf("%d. %-15s | W %-4.1f | %s\n", item.getIndex(), item.getName(), item.getPrice(), item.getDescription());
//        }
        System.out.printf("%d. %-14s | %-4s\n", 0, "종료", "종료");
    }

    public void selectMenu(MenuItem m){
        System.out.printf("%s: %-15s | W %-4.1f | %s\n", "선택한 메뉴", m.getName(), m.getPrice(), m.getDescription());
    }

    // 형식 지정자
    // printf() 또는 String.format()에 사용되는 형식 지정자로 일정 간격으로 출력
    // %-15s : 좌측 정렬 최소 15칸 확보
    // %-4.1f : 최소 4칸 확보 및 소수점 1까지 출력

}
