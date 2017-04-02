import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Test1 {
    public static int N = 10;      //设定每个数的范围大小

    public static void main(String[] arge) {
        System.out.println("请输入答题数：");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int right = 0, fz, fm, gys, gbs;
        int sign1, sign2, sign3;
        String answer = "";
        String result = "";
        String expression = "";
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            int s = r.nextInt(11);
            int a = r.nextInt(N);
            int b = r.nextInt(N);
            int c = r.nextInt(N);
            int d = r.nextInt(N);
            if (s > 3 && s < 8) {
                while (a == b || a > b) {
                    a = r.nextInt(N);
                    b = r.nextInt(N - 1) + 1;
                }
                while (c == d || c > d) {
                    c = r.nextInt(N);
                    d = r.nextInt(N - 1) + 1;
                }
            }
            switch (s) {
                case 0:
                    System.out.println(a + " + " + b + " = ?");
                    result = "" + (a + b);
                    break;
                case 1:
                    System.out.println(a + " - " + b + " = ?");
                    result = "" + (a - b);
                    break;
                case 2:
                    System.out.println(a + " × " + b + " = ?");
                    result = "" + (a * b);
                    break;
                case 3:
                    while (b == 0) b = r.nextInt(N);
                    System.out.print(a + " ÷ " + b + " = ?");
//                    System.out.println("（请保留一位小数）");
//                    float x = a;
//                    float y = b;
//                    float z = (float) (Math.round((x / y) * 10)) / 10;
//                    result = "" + z;
//                    break;
                    System.out.println("（若整除，则输入整数，否则输入最简分数）");
                    if (a % b == 0) {
                        result = "" + a / b;
                    } else {
                        int temp_maxgys = maxgys(a, b);
                        a = a / temp_maxgys;
                        b = b / temp_maxgys;
                        result = a + "/" + b;
                    }
                    break;
                case 4:
                    System.out.println(a + "/" + b + " + " + c + "/" + d + " = ?");
                    if (b == d) {
                        fz = a + c;
                        fm = b;
                    } else {
                        gbs = mingbs(b, d);
                        a = gbs / b * a;
                        c = gbs / d * c;
                        fz = a + c;
                        fm = gbs;
                    }
                    gys = maxgys(fz, fm);
                    fz = fz / gys;
                    fm = fm / gys;
                    if (fz == fm) result = "" + 1;
                    else if (fm == 1) result = "" + fz;
                    else result = fz + "/" + fm;
                    break;
                case 5:
                    System.out.println(a + "/" + b + " - " + c + "/" + d + " = ?");
                    if (b == d) {
                        fz = a - c;
                        fm = b;
                    } else {
                        gbs = mingbs(b, d);
                        a = gbs / b * a;
                        c = gbs / d * c;
                        fz = a - c;
                        fm = gbs;
                    }
                    gys = maxgys(fz, fm);
                    fz = fz / gys;
                    fm = fm / gys;
                    if (check(fz, fm) == false) {
                        fz = -fz;
                        fm = -fm;
                    }
                    if (fz == fm) result = "" + 1;
                    else if (fm == 1) result = "" + fz;
                    else result = fz + "/" + fm;
                    break;
                case 6:
                    System.out.println(a + "/" + b + " × " + c + "/" + d + " = ?");
                    fz = a * c;
                    fm = b * d;
                    gys = maxgys(fz, fm);
                    fz = fz / gys;
                    fm = fm / gys;
                    if (fz == fm) result = "" + 1;
                    else if (fm == 1) result = "" + fz;
                    else result = fz + "/" + fm;
                    break;
                case 7:
                    while (c == 0) c = r.nextInt(N);
                    System.out.println(a + "/" + b + " ÷ " + c + "/" + d + " = ?");
                    int t = c;
                    c = d;
                    d = t;
                    fz = a * c;
                    fm = b * d;
                    gys = maxgys(fz, fm);
                    fz = fz / gys;
                    fm = fm / gys;
                    if (fz == fm) result = "" + 1;
                    else if (fm == 1) result = "" + fz;
                    else result = fz + "/" + fm;
                    break;
//                case 8:                 //随机生成2个运算符的算式
//                    int sign1;
//                    int sign2;
//                    int temp=0;
//                    sign1 = creatRandom();
//                    sign2 = creatRandom();
//                    while(sign2==3&&c==0)
//                        c = r.nextInt(10);
//                    System.out.print(a + returnSign(sign1) + b + returnSign(sign2) + c + " = ?");
//                    System.out.println("（若答案是整数则输入整数，否则输入最简分数）");
//                    if(sign1!=3&&sign2!=3)
//                    {
//                        if(sign1>sign2||sign1==sign2)
//                        {
//                            temp=calc(a,b,sign1);
//                            temp=calc(temp,c,sign2);
//                        }
//                        else
//                        {
//                            temp=calc(b,c,sign2);
//                            temp=calc(a,temp,sign1);
//                        }
//                        result=""+temp;
//                    }
//                    else
//                    {
//                        if(sign1==3)
//                        {
//                            switch(sign2)
//                            {
//                                case 0:a=a+b*c;temp=maxgys(a,b);a=a/temp;b=b/temp;
//                                        if (a == b) result = "" + 1;
//                                        else if (b == 1) result = "" + a;
//                                        else result = a + "/" + b;break;
//                                case 1:a=a-b*c;temp=maxgys(a,b);a=a/temp;b=b/temp;
//                                        if (a == b) result = "" + 1;
//                                        else if (b == 1) result = "" + a;
//                                        else result = a + "/" + b;break;
//                                case 2:a=a*c;temp=maxgys(a,b);a=a/temp;b=b/temp;
//                                        if (a == b) result = "" + 1;
//                                        else if (b == 1) result = "" + a;
//                                        else result = a + "/" + b;break;
//                                case 3:b=b*c;temp=maxgys(a,b);a=a/temp;b=b/temp;
//                                        if (a == b) result = "" + 1;
//                                        else if (b == 1) result = "" + a;
//                                        else result = a + "/" + b;break;
//                            }
//                        }
//                        else
//                        {
//                            switch (sign1)
//                            {
//                                case 0:b=b+a*c;temp=maxgys(b,c);b=b/temp;c=c/temp;
//                                        if (b == c) result = "" + 1;
//                                        else if (c == 1) result = "" + b;
//                                        else result = b + "/" + c;break;
//                                case 1:b=a*c-b;temp=maxgys(b,c);b=b/temp;c=c/temp;
//                                        if (b == c) result = "" + 1;
//                                        else if (c == 1) result = "" + b;
//                                        else result = b + "/" + c;break;
//                                case 2:b=a*b;temp=maxgys(b,c);b=b/temp;c=c/temp;
//                                        if (b == c) result = "" + 1;
//                                        else if (c == 1) result = "" + b;
//                                        else result = b + "/" + c;break;
//                            }
//                        }
//                    }
                case 8:        //2个运算符的算式
                    sign1 = creatRandom();
                    sign2 = creatRandom();
                    while (sign1 == 3 && b == 0) b = r.nextInt(N);
                    while (sign2 == 3 && c == 0) c = r.nextInt(N);
                    expression = a + returnSign(sign1) + b + returnSign(sign2) + c;
                    System.out.println(expression + " = ?");
                    result = evaluateExpression(expression);
                    break;
                case 9:        //3个运算符的算式
                    sign1 = creatRandom();
                    sign2 = creatRandom();
                    sign3 = creatRandom();
                    while (sign1 == 3 && b == 0) b = r.nextInt(N);
                    while (sign2 == 3 && c == 0) c = r.nextInt(N);
                    while (sign3 == 3 && d == 0) d = r.nextInt(N);
                    expression = a + returnSign(sign1) + b + returnSign(sign2) + c + returnSign(sign3) + d;
                    System.out.println(expression + " = ?");
                    result = evaluateExpression(expression);
                    break;
                case 10:      //生成带括号的算式
                    sign1 = creatRandom();
                    sign2 = creatRandom();
                    while (sign2 == 3 && c == 0) c = r.nextInt(N);
                    if (sign1 % 2 == 0)    //假设生成括号的形式只有两种，这是其中一种
                    {
                        while (sign1 == 3 && b == 0) b = r.nextInt(N);
                        expression = "( " + a + returnSign(sign1) + b + " )" + returnSign(sign2) + c;
                    } else {
                        String temp = b + returnSign(sign2) + c;
                        while (evaluateExpression(temp) == "0") {
                            b = r.nextInt(N);
                            c = r.nextInt(N);
                            temp = b + returnSign(sign2) + c;
                        }
                        expression = a + returnSign(sign1) + "( " + b + returnSign(sign2) + c + " )";
                    }
                    System.out.println(expression + " = ?");
                    result = evaluateExpression(expression);
                    break;
            }
            answer = input.next();
            if (answer.compareTo(result) == 0) {
                right++;
                System.out.println("good!");
            } else
                System.out.println("wrong!");
            System.out.println("正确答案：" + result);
        }
        System.out.println("答对" + right + "题，答错" + (num - right) + "题");
        System.out.println("正确率：" + (1.0 * right / num * 100) + "%");
    }

    public static int mingbs(int a1, int b1) {             //求最小公倍数
        int r, aa = a1, bb = b1;
        while (b1 != 0) {
            r = a1 % b1;
            a1 = b1;
            b1 = r;
        }
        int gbs = aa * bb / a1;
        return gbs;
    }

    public static int maxgys(int a1, int b1) {        //求最大公约数
        int r;
        while (b1 != 0) {
            r = a1 % b1;
            a1 = b1;
            b1 = r;
        }
        return a1;
    }

    public static int creatRandom() {       //创造随机数
        Random rd = new Random();
        int s = rd.nextInt(4);
        return s;
    }

    public static String returnSign(int s) {              //利用随机数生成运算符号
        String sign = "";
        switch (s) {
            case 0:
                sign = " + ";
                break;
            case 1:
                sign = " - ";
                break;
            case 2:
                sign = " × ";
                break;
            case 3:
                sign = " ÷ ";
                break;
        }
        return sign;
    }

    public static int calc(int a, int b, int s) {          //计算结果
        int result = 0;
        switch (s) {
            case 0:
                result = a + b;
                break;
            case 1:
                result = a - b;
                break;
            case 2:
                result = a * b;
                break;
            case 3:
                result = a / b;
                break;
        }
        return result;
    }

    public static String evaluateExpression(String expression) {
        Stack<String> operandStack = new Stack<String>();        //存放操作数的栈
        Stack<Character> operatorStack = new Stack<Character>();//存放运算符的栈

        String[] charArr = expression.split(" ");        //将字符串分割成单个字符
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i].trim().equals("")) {        //如果字符串为空，则跳过此次循环
                continue;
            } else if (charArr[i].trim().equals("+") || charArr[i].trim().equals("-")) {
                //如果字符串为"+"或者"-"，则执行栈中已存数据的加减乘除计算
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '×' ||
                                operatorStack.peek() == '÷')) {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.push(charArr[i].charAt(0));//将操作符压入操作符栈中
            } else if (charArr[i].trim().equals("×") || charArr[i].trim().equals("÷")) {
                //如果字符串为"*"或者"/"，则执行栈中已存数据的乘除计算
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '×' ||
                                operatorStack.peek() == '÷')) {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.push(charArr[i].charAt(0));
            } else if (charArr[i].trim().equals("(")) {
                //如果遇到左括号，则将左括号压入操作符栈中
                operatorStack.push('(');
            } else if (charArr[i].trim().equals(")")) {
                //如果遇到右括号，则计算栈中的数据，直到遇到左括号为止
                while (operatorStack.peek() != '(') {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.pop();//将进行过计算的左括号弹出
            } else {
                //如果遇到的是操作数，则将操作数直接压入操作数栈中
                operandStack.push(charArr[i]);
            }
        }
        //对栈中数据进行计算，直到栈为空为止
        while (!operatorStack.isEmpty()) {
            processOneOperator(operandStack, operatorStack);
        }
        //此时操作数栈中的栈顶元素也就是计算结果
        String result = operandStack.pop();
        String[] last = result.split("/");
        int fz = Integer.parseInt(last[0]);
        int fm = Integer.parseInt(last[1]);
        int gys = maxgys(fz, fm);
        fz = fz / gys;
        fm = fm / gys;
        if (check(fz, fm) == false) {
            fz = -fz;
            fm = -fm;
        }
        if (fz == fm) result = "" + 1;
        else if (fm == 1) result = "" + fz;
        else result = fz + "/" + fm;
        return result;
    }

    public static void processOneOperator(Stack<String> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();     //取操作符的栈顶元素
        String op1 = operandStack.pop();    //取操作数的栈顶元素
        String op2 = operandStack.pop();    //取操作数的栈顶元素
        int gbs;
        if (!op1.contains("/"))
            op1 = op1 + "/" + 1;
        if (!op2.contains("/"))
            op2 = op2 + "/" + 1;
        String[] charArr1 = op1.split("/");
        String[] charArr2 = op2.split("/");
        int c = Integer.parseInt(charArr1[0]);
        int d = Integer.parseInt(charArr1[1]);
        int a = Integer.parseInt(charArr2[0]);
        int b = Integer.parseInt(charArr2[1]);
        gbs = mingbs(b, d);
        a = gbs / b * a;
        c = gbs / d * c;
        b = d = gbs;
        if (op == '+') {
            operandStack.push((a + c) + "/" + gbs);
        } else if (op == '-') {
            operandStack.push((a - c) + "/" + gbs);
        } else if (op == '×') {
            operandStack.push((a * c) + "/" + (gbs * gbs));
        } else if (op == '÷') {
            operandStack.push((a * d) + "/" + (b * c));
        }
    }

    public static boolean check(int fz, int fm) {
        if (fm < 0)
            return false;
        else
            return true;
    }
}
