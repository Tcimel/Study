package calculator.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    private List<Integer> history;

    public Calculator() {
        history = new ArrayList<>();
    }

    public List<Integer> getHistory() {
        return history;
    }

    public void setHistory(List<Integer> history) {
        this.history = history;
    }

    public void removeResult(){
        if(history.isEmpty()){
            System.out.println("Nothing to remove");
        }else {
            history.clear();
        }
    }

    public int calculate(int a, int b,String c){
        int result = 0;
        switch (c){
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if(b==0){
                    System.out.println("0으로 나눌 수 없습니다.");
                    return 0;
                }else{
                    result = a/b;
                    break;
                }
            default:
                System.out.println("wrong input data");
                return 0;
        }

        history.add(result);
        return result;
    }
}
