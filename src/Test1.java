import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Test1 {
    public static void main(String[] arge) {
        System.out.println("欢迎使用，答题时输入“#”退出程序");
        System.out.println("请输入答题数：");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int right = 0, fz, fm, gys, gbs;
        String answer = "";
        String result = "";
        String[] record = new String[100];
        record[0] = "";
        Random r = new Random();
        out:
        for (int i = 0; i < num; i++) {
            int s = r.nextInt(9);
            int a = r.nextInt(10);
            int b = r.nextInt(9) + 1;
            int c = r.nextInt(10);
            int d = r.nextInt(9) + 1;
            if (s >= 4) {
                while (a == b || a > b) {
                    a = r.nextInt(10);
                    b = r.nextInt(9) + 1;
                }
                while (c == d || c > d) {
                    c = r.nextInt(10);
                    d = r.nextInt(9) + 1;
                }
            }
            String now = "" + a + b;     //避免生成相同算式
            String now1 = "" + b + a;
            for (int j = 0; j < i; j++) {
                while (record[j].equals(now) || record[j].equals(now1)) {
                    a = r.nextInt(10);
                    b = r.nextInt(9) + 1;
                    while (a == b || a > b) {
                        a = r.nextInt(10);
                        b = r.nextInt(9) + 1;
                    }
                    now = "" + a + b;
                    now1 = "" + b + a;
                    j = 0;
                }
            }
            record[i] = now;
            switch (s) {
                case 0:
                    System.out.println(a + "+" + b + "=?");
                    result = "" + (a + b);
                    break;
                case 1:
                    System.out.println(a + "-" + b + "=?");
                    result = "" + (a - b);
                    break;
                case 2:
                    System.out.println(a + "*" + b + "=?");
                    result = "" + (a * b);
                    break;
                case 3:
                    System.out.print(a + "/" + b + "=?");
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
                    System.out.println(a + "/" + b + " + " + c + "/" + d + "=?");
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
                    System.out.print(a + "/" + b + " - " + c + "/" + d + "=?");
                    System.out.println("（若是负数，则把负号写在最前面）");
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
                    if (fz == fm) result = "" + 1;
                    else if (fm == 1) result = "" + fz;
                    else {
                        if (fm < 0) {
                            fz = -fz;
                            fm = -fm;
                        }
                        result = fz + "/" + fm;
                    }
                    break;
                case 6:
                    System.out.println(a + "/" + b + " * " + c + "/" + d + "=?");
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
                    while (c == 0) c = r.nextInt(10);
                    System.out.println(a + "/" + b + " / " + c + "/" + d + "=?");
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
                case 8:
                    System.out.print(a + "/" + b + " + " + c + "=?");
                    System.out.println("（若是分数，请化为a/b的形式）");
                    d = 1;
                    gbs = mingbs(b, d);
                    a = gbs / b * a;
                    c = gbs / d * c;
                    fz = a + c;
                    fm = gbs;
                    gys = maxgys(fz, fm);
                    fz = fz / gys;
                    fm = fm / gys;
                    if (fz == fm) result = "" + 1;
                    else if (fm == 1) result = "" + fz;
                    else result = fz + "/" + fm;
                    break;
            }
            answer = input.next();
            if (answer.compareTo("#") == 0) break;
            while (!isNumeric(answer)) {             //判断输入是否合法
                System.out.println("输入不合法！请重新输入");
                answer = input.next();
                if (answer.compareTo("#") == 0) break out;
            }
            while (answer.contains("/")) {           //判断分母是否为0
                int fm0 = answer.indexOf("/");
                String fm00 = answer.substring(fm0 + 1);
                if (fm00.compareTo("0") == 0) {
                    System.out.println("输入错误! 请重新输入");
                    answer = input.next();
                    if (answer.compareTo("#") == 0) break out;
                    while (!isNumeric(answer)) {             //判断输入是否合法
                        System.out.println("输入不合法！请重新输入");
                        answer = input.next();
                        if (answer.compareTo("#") == 0) break out;
                    }
                } else break;
            }
            if (answer.compareTo(result) == 0) {
                right++;
                System.out.println("good!");
            } else
                System.out.println("wrong!");
        }
        System.out.println("答对" + right + "题，答错" + (num - right) + "题");
        System.out.println("正确率：" + (1.0 * right / num * 100) + "%");
    }

    public static int mingbs(int a1, int b1) {                //求最小公倍数
        int r, aa = a1, bb = b1;
        while (b1 != 0) {
            r = a1 % b1;
            a1 = b1;
            b1 = r;
        }
        int gbs = aa * bb / a1;
        return gbs;
    }

    public static int maxgys(int a1, int b1) {           //求最大公因数
        int r;
        while (b1 != 0) {
            r = a1 % b1;
            a1 = b1;
            b1 = r;
        }
        return a1;
    }

    public static boolean isNumeric(String str) {         //判断输入字符是否合法
        Pattern pattern = Pattern.compile("^[0-9]*$");
        if (str.contains("/") && str.contains("-")) {
            str = str.replaceFirst("/", "");
            str = str.replaceFirst("-", "");
            return pattern.matcher(str).matches();
        } else if (str.contains("/")) {
            str = str.replaceFirst("/", "");
            return pattern.matcher(str).matches();
        } else if (str.contains("-")) {
            str = str.replaceFirst("-", "");
            return pattern.matcher(str).matches();
        } else {
            return pattern.matcher(str).matches();
        }
    }
}
