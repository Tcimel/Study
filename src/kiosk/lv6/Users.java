package kiosk.lv6;

public enum Users {
    일반(0.0),
    국가유공자(0.1),
    군인(0.05),
    학생(0.03);

    private Double discount=0.0;

    Users(Double d){
        this.discount = d;
    }

    public Double getDiscount() {
        return discount;
    }
}
