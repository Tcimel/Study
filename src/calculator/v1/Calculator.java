package calculator.v1;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){

            System.out.println("계산할 숫자 2개 입력");
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            System.out.println("사칙연산 기호 하나 입력(+, -, *, /)");
            String op = scanner.next();

            switch (op){
                case "+":
                    System.out.println(a + b);
                    break;
                case "-":
                    System.out.println(a - b);
                    break;
                case "*":
                    System.out.println(a * b);
                    break;
                case "/":
                    System.out.println(a / b);
                    break;
                default:
                    System.out.println("wrong input data");
//                    throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            System.out.println("추가 계산 진행시 아무 문자 입력(exit입력 시 종료)");
            scanner.nextLine();
            String end = scanner.nextLine();

            if(end.equals("exit")){
                flag =false;
            }
        }
    }
}
