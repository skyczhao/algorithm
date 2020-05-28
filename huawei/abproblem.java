package huawei;

import java.util.Scanner;

/**
 */
public class abproblem {
    /**
     * 考察多次循环输入
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
