package ParseService;

import java.util.*;
public  class ParseService {
    static char[] pusher(String str){    //Преобразовуем входную сроку в символьный массив
        char[] charArray = str.toCharArray();
        return charArray;
    }

    String stringType = "";
    double value;
    LinkedList<Character> priority1 = new LinkedList<>();
    LinkedList<Character> priority2 = new LinkedList<>();
    LinkedList<Character> stackList = new LinkedList<>();
    LinkedList<Character> outputList = new LinkedList<>();
    LinkedList<Object> resultList = new LinkedList<>();
    Run object = new Run();

    LinkedList<Character> parseService() {
        priority1.add('+');
        priority1.add('-');
        priority2.add('/');
        priority2.add('*');
        //парсим в польскую запись
        for (Character c : pusher(Run.expression)) {
            if (stackList.isEmpty() && (c == '(' || c == '+' || c == '-' || c == '*' || c == '/')) {
                stackList.add(c);
                if (c == '+' || c == '-' || c == '*' || c == '/'){
                    outputList.add(' ');
                }
                continue;
            }

            if (c == ' ') {
                continue;
            }
            if (c == '(') {
                stackList.add(c);
                continue;
            }
            if (Character.isDigit(c)) {
                outputList.add(c);
                continue;
            }
            if (c == ')') {
                while (stackList.peekLast() != '(') {
                    outputList.add(stackList.pollLast());
                }
                stackList.pollLast();
            }
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                stackList.add(c);
                if (stackList.get(stackList.size()-1) != '('){
                    outputList.add(' ');
                }
                while (stackList.size() >= 2) {
                    if (stackList.peekLast() != '(') {
                        if ( (priority1.contains(stackList.peekLast()) && priority1.contains(stackList.get(stackList.size() - 2)) ) ||
                           (priority1.contains(stackList.peekLast()) && priority2.contains(stackList.get(stackList.size() - 2)) ) ||
                           (priority2.contains(stackList.peekLast()) && priority2.contains(stackList.get(stackList.size() - 2)) )  ) {
                            outputList.add(stackList.get(stackList.size() - 2));
                            stackList.remove(stackList.size() - 2);
                        }
                    }
                    break;
                }
            }
        }
        for(int last = stackList.size()-1; stackList.size()>0; last--){
            outputList.add(stackList.get(last));
            stackList.remove(last);
        }
        System.out.println(outputList);
            return outputList;
    }

    double finalResult (){  //Перобразовываем Лист символов в дабл
        for(char k : parseService()){
            System.out.println(resultList);
            if(Character.isDigit(k)){
                    stringType += String.valueOf(k);
                continue;
            }
            if (k == ' '){
                if (stringType != "") {
                    value = Double.parseDouble(stringType);
                    stringType = "";
                    resultList.add(value);
                    continue;
                } else continue;
            }
            if (k == '+' || k == '-' || k == '/' || k == '*'){
                if (stringType == ""){
                    resultList.add(k);
                } else {
                    value=Double.parseDouble(stringType);
                    resultList.add(value);
                    stringType = "";
                    resultList.add(k);
                }
            }
        }
        while (resultList.size() != 1) {  //Ходим по листу даблов и операторов и подсчитываем конечный результат
            for (int e = 0; e < resultList.size(); e++) {
                if (resultList.get(e) == '+') {
                    resultList.add(e - 2, (Double) (resultList.get(e - 2)) + (Double) (resultList.get(e - 1)));
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    break;
                }
                if (resultList.get(e) == '-') {
                    resultList.add(e - 2, (Double) (resultList.get(e - 2)) - (Double) (resultList.get(e - 1)));
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    break;
                }
                if (resultList.get(e) == '*') {
                    resultList.add(e - 2, (Double) (resultList.get(e - 1)) * (Double) (resultList.get(e - 2)));
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    break;
                }
                if (resultList.get(e) == '/') {
                    resultList.add(e - 2, (Double) (resultList.get(e - 2)) / (Double) (resultList.get(e - 1)));
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e - 1);
                    System.out.println(resultList);
                    resultList.remove(e-1);
                    System.out.println(resultList);
                    break;
                }
            }
        }
    return (Double)resultList.get(0);
    }
}



