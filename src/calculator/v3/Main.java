package calculator.v3;

import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArithmeticCalculator calculator = new ArithmeticCalculator();

        boolean flag = true;

        while (flag) {
            System.out.print("Enter number: ");
            double num1 = scanner.nextDouble();
            double num2 = scanner.nextDouble();

            System.out.print("Enter operator: ");
            String op = scanner.next();

            switch (op) {
                case "+":
                    calculator.calculate(num1,num2,OperatorType.PLUS);
                    break;
                case "-":
                    calculator.calculate(num1,num2,OperatorType.MINUS);
                    break;
                case "*":
                    calculator.calculate(num1,num2,OperatorType.MULTIPLY);
                    break;
                case "/":
                    calculator.calculate(num1,num2,OperatorType.DIVIDE);
                    break;
                default:
                    System.out.println("wrong opreator");
                    break;
            }

            System.out.println("Result List: " + calculator.getResults());

            System.out.println("결과값 중 입력값 보다 높은값 조회해보기");
            double num3 = scanner.nextDouble();
            System.out.println(calculator.getBiggerResults(num3));

            System.out.println("계산종료 : exit 입력");
            scanner.nextLine(); // 버퍼 비우기
            String end = scanner.nextLine();
            if(end.equals("exit")){
                flag =false;
            }
        }

        //저장된 결과들 중 입력받은 값보다 큰 결과값 출력하기 : 람다 & 스트림 활용


    }
}
