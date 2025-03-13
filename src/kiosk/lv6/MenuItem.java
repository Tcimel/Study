package kiosk.lv6;

//세부 메뉴 속성 가지는 클래스
public class MenuItem {
//    Map<String, Object> shackBurger = Map.of(
//            "name", "ShackBurger",
//            "price", 6.9,
//            "description","토마토, 양상추, 쉑소스가 토핑된 치즈버거"
//    );
//
//    Map<String, Object> smokeShack = Map.of(
//            "name", "SmokeShack",
//            "price", 8.9,
//            "description","베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
//    );
//
//    Map<String, Object> cheeseBurger = Map.of(
//            "name", "CheeseBurger",
//            "price", 6.9,
//            "description","포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"
//    );
//
//    Map<String, Object> hamburger = Map.of(
//            "name", "Hamburger",
//            "price", 5.4,
//            "description","비프패티를 기반으로 야채가 들어간 기본버거"
//    );
    // 이름, 가격, 설명 필드 선언하여 관리
    private String name;
    private Double price;
    private String description;

    public MenuItem(String name, Double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
