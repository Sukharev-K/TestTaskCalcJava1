import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Давай посчитаем?");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            System.out.println(calc(input));
            if (calc(input) =="формат математической операции не удовлетворяет заданию - два операнда и один оператор"
                    || calc(input) == "Введены не корректные данные либо данные в разных системах счисления"
                    || calc(input) == "Математический знак не удовлетворяет условию"
                    || calc(input) == "Введены арабские числа, выходящие за диапазон 1..10"
                    || calc(input) == "Введены римские цифры, выходящие за диапазон 1..10" ){break;}
        }
        scanner.close();
    }

    public static String calc(String input){
        String[] mathematicalExpressionByParts = splitALineIntoParts(input);
        if (mathematicalExpressionByParts.length != 3) {
            return "формат математической операции не удовлетворяет заданию - два операнда и один оператор";
        }
        if (!isArabicNumber(mathematicalExpressionByParts[0],mathematicalExpressionByParts[2])){
            try {
                InputParameter.valueOf(mathematicalExpressionByParts[0]);
                InputParameter.valueOf(mathematicalExpressionByParts[2]);
            } catch (Exception e){
                return "Введены не корректные данные либо данные в разных системах счисления";
            }
        }
        if (!mathematicalExpressionByParts[1].equals("+") && !mathematicalExpressionByParts[1].equals("-") && !mathematicalExpressionByParts[1].equals("/") && !mathematicalExpressionByParts[1].equals("*")){
            return "Математический знак не удовлетворяет условию";
        }
        if (isArabicNumber(mathematicalExpressionByParts[0],mathematicalExpressionByParts[2])){
            int firstNum = Integer.valueOf(mathematicalExpressionByParts[0]);
            int secondNum = Integer.valueOf(mathematicalExpressionByParts[2]);
            if (firstNum > 10 || firstNum < 1 || secondNum > 10 || secondNum < 1){
                return "Введены арабские числа, выходящие за диапазон 1..10";
            }else {
                switch (mathematicalExpressionByParts[1]){
                    case "+" :
                        return String.valueOf(firstNum + secondNum);
                    case "-" :
                        return String.valueOf(firstNum - secondNum);
                    case "/" :
                        return String.valueOf(firstNum / secondNum);
                    case "*" :
                        return String.valueOf(firstNum * secondNum);
                }
            }
        }
        if (!isRomanNumeral(mathematicalExpressionByParts[0],mathematicalExpressionByParts[2])){
            return "Введены римские цифры, выходящие за диапазон 1..10";
        }
        if (isRomanNumeral(mathematicalExpressionByParts[0],mathematicalExpressionByParts[2])){
            OutputParameter firstNumEnm = OutputParameter.valueOf(mathematicalExpressionByParts[0]);
            OutputParameter secondNumEnm = OutputParameter.valueOf(mathematicalExpressionByParts[2]);
            int firstNum = firstNumEnm.getArabicNumerals();
            int secondNum = secondNumEnm.getArabicNumerals();
            if (firstNum - secondNum <= 0 && mathematicalExpressionByParts[1] == "-"){
                return "Ошибка! В римской системе счисления нет отрицательных чисел или нуля";
            } else {
                switch (mathematicalExpressionByParts[1]){
                    case "+":
                        return String.valueOf(OutputParameter.values()[firstNum + secondNum - 1]);
                    case "-":
                        return String.valueOf(OutputParameter.values()[firstNum - secondNum - 1]);
                    case "/":
                        return String.valueOf(OutputParameter.values()[firstNum / secondNum - 1]);
                    case "*":
                        return String.valueOf(OutputParameter.values()[firstNum * secondNum - 1]);
                }
            }

        }
        return "";

    }
    static String[] splitALineIntoParts(String input){
        return input.split(" ");
    }
    static boolean isArabicNumber (String firstNum, String secondNum){
        int first, second;
        try {
            first = Integer.parseInt(firstNum);
            second = Integer.parseInt(secondNum);
            return true;
        } catch (Exception e){
        }
        return false;
    }
    static boolean isRomanNumeral (String firstNum, String secondNum){
        try{
            InputParameter.valueOf(firstNum);
            InputParameter.valueOf(secondNum);
            return true;
        } catch (Exception e){}
        return false;
    }
}
