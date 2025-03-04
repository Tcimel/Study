# 📝 과제 : 계산기

### Lv 1: 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기
### Lv 2: 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기
### Lv 3: Enum, 제네릭, 람다 & 스트림을 활용한 계산기 만들기

## 📂 파일 경로
### Lv 1 경로: `ch2-calculator/calculator/v1` : [GitHub 링크](https://github.com/Tcimel/Study/tree/ch2-calculator/src/calculator/v1)

### Lv 2 경로: `ch2-calculator/calculator/v2` : [GitHub 링크](https://github.com/Tcimel/Study/tree/ch2-calculator/src/calculator/v2)
  
## 📌 Lv 1: 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기
### ✅ **기능 요구사항**
- **양의 정수(0 포함) 입력받기**  
  - `Scanner`를 사용하여 양의 정수 2개(0 포함)를 입력받음  
  - 각 정수를 **적합한 타입의 변수에 저장**  

#### 🖥 **예제 코드**
```java
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 숫자를 입력하세요: ");
        int num1 = sc.nextInt();  

        System.out.print("두 번째 숫자를 입력하세요: ");
        int num2 = sc.nextInt();
    }
}
```  
  
- **사칙연산 기호(➕,➖,✖️,➗) 입력받기**
  - `Scanner`를 사용하여 연산 기호를 입력받음
  - 적합한 타입(char)으로 저장 (charAt(0) 활용)  
    
#### 🖥 **예제 코드**  
```java
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("사칙연산 기호를 입력하세요: ");
        char operator = sc.next().charAt(0);
    }
}
```
- **입력받은 값으로 연산 수행 후 결과 출력**
  - if 또는 switch 문을 활용하여 연산 수행
  - 연산 오류 처리 (ex: 0으로 나누기 오류 방지)  
  
#### 🖥 **예제 코드**
```java
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 숫자를 입력하세요: ");
        int num1 = sc.nextInt();

        System.out.print("두 번째 숫자를 입력하세요: ");
        int num2 = sc.nextInt();

        System.out.print("사칙연산 기호를 입력하세요: ");
        char operator = sc.next().charAt(0);

        int result = 0;
        switch (operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/':
                if (num2 == 0) {
                    System.out.println("❌ 0으로 나눌 수 없습니다.");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("❌ 잘못된 연산 기호입니다.");
                return;
        }

        System.out.println("결과: " + result);
    }
}
```  
- **반복문을 사용하여 무한 계산 수행 (exit 입력 시 종료)**
  - while 또는 for 반복문을 활용
  - “exit”을 입력하면 반복 종료  
  
#### 🖥 **예제 코드**  
```java
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            System.out.print("사칙연산 기호를 입력하세요: ");
            char operator = sc.next().charAt(0);

            if (operator == 'e') {
                System.out.println("✅ 계산기 종료");
                break;
            }

            int result = 0;
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("❌ 0으로 나눌 수 없습니다.");
                        continue;
                    }
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("❌ 잘못된 연산 기호입니다.");
                    continue;
            }

            System.out.println("결과: " + result);
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
        }
    }
}
```  
## 📌 Lv 2: 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기  
  
### ✅ **기능 요구사항**  
-	연산 결과를 저장하는 Calculator 클래스 구현  
  -	계산 기능을 수행하는 calculate 메서드 구현  
  -	결과값을 저장하는 컬렉션 타입 필드 추가  
  
#### 🖥 **예제 코드**  
```java
public class Calculator {
    private List<Integer> results = new ArrayList<>(); 

    public int calculate(int num1, int num2, char operator) {
        int result = 0;
        switch (operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': 
                if (num2 == 0) {
                    throw new ArithmeticException("❌ 0으로 나눌 수 없습니다.");
                }
                result = num1 / num2;
                break;
        }
        results.add(result);
        return result;
    }
}
```  
## 📌 Lv 3: Enum, 제네릭, 람다 & 스트림을 활용한 계산기 만들기  
  
### ✅ 기능 요구사항  
- Enum을 활용하여 연산자 타입 관리  
- 제네릭을 활용하여 다양한 타입(정수, 실수) 지원  
- 람다 & 스트림을 활용한 연산 결과 필터링  
  
#### 🖥 **예제 코드**  
```java
public enum OperatorType {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');

    private final char symbol;

    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
```  
