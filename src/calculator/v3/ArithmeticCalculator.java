package calculator.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticCalculator<T extends Number> {
    // 피연산자를 여러 타입으로 받을 수 있도록 기능을 확장하기 위해 Double형으로 변환
    List<Double> results = new ArrayList<>();

    public Double calculate(T a, T b, OperatorType op) {
        double result = 0;
        switch (op) {
            case PLUS:
                result = a.doubleValue() + b.doubleValue();
                break;
            case MINUS:
                result = a.doubleValue() - b.doubleValue();
                break;
            case MULTIPLY:
                result = a.doubleValue() * b.doubleValue();
                break;
            case DIVIDE:
                if(b.doubleValue() == 0){
                    System.out.println("0으로 나눌 수 없습니다.");
                    break;
                }else{
                    result = a.doubleValue() / b.doubleValue();
                    break;
                }
        }
        results.add(result);
        return result;
    }

    public List<Double> getResults() {
        return results;
    }

    public List<Double> getBiggerResults(Double a){
        return results.stream().filter(x -> x.doubleValue() > a).collect(Collectors.toList());
    }

}
