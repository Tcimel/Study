package calculator.v2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /* Calculator 인스턴스 생성 */
        Calculator calculator = new Calculator();

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){

            System.out.println("계산할 숫자 2개 입력");
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            System.out.println("사칙연산 기호 하나 입력(+, -, *, /)");
            String op = scanner.next();

            int result = calculator.calculate(a, b, op);
            System.out.println("결과 : "+result);

            scanner.nextLine();

            System.out.println("계산 기록을 보시겠습니까? (Y/N(enter))");
            String choice = null;
            while (true) {
                choice = scanner.nextLine();
                if(choice.equals("Y")||choice.equals("y")){
                    System.out.println(calculator.getHistory());
                    break;
                }else if(choice.equals("N")||choice.equals("n")||choice.isEmpty()){
                    break;
                }else{
                    System.out.println("please, y or n(enter)");
                }
            }


            System.out.println("계산 기록을 삭제하시겠습니까? (Y/N(enter))");

            while (true){
                choice = scanner.nextLine();
                if(choice.equals("Y")||choice.equals("y")){
                    calculator.removeResult();
                    System.out.println("삭제 후 리스트 내용 : " + calculator.getHistory());
                    break;
                }else if(choice.equals("N")||choice.equals("n")||choice.isEmpty()){
                    System.out.println("현재 리스트 내용 : " + calculator.getHistory());
                    break;
                }else{
                    System.out.println("please, y or n");
                }
            }


            System.out.println("추가 계산 진행하시겠습니까?(exit입력 시 종료)");
            String end = scanner.nextLine();

            if(end.equals("exit")){
                flag =false;
            }
        }
    }
}
