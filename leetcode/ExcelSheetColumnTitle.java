package leetcode;

/**
 * ExcelSheetColumnTitle
 * https://leetcode-cn.com/problems/excel-sheet-column-title/
 * 168. Excel表列名称
 * https://leetcode-cn.com/problems/excel-sheet-column-title/solution/lei-26jin-zhi-by-oshdyr-6v4s/
 *
 * @author tobin
 * @since 2021-06-29
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        ExcelSheetColumnTitle sol = new ExcelSheetColumnTitle();

        System.out.println(sol.convertToTitle(87));
        System.out.println(sol.convertToTitle(28));
        System.out.println(sol.convertToTitle(27));
        System.out.println(sol.convertToTitle(26));
        System.out.println(sol.convertToTitle(52));
        System.out.println(sol.convertToTitle(52));
        System.out.println(sol.convertToTitle(676));
        System.out.println(sol.convertToTitle(702));
        System.out.println(sol.convertToTitle(17576));
        System.out.println(sol.convertToTitle(17577));
        System.out.println(sol.convertToTitle(17603));
        System.out.println(sol.convertToTitle(18253)); // 26*26*26 + 26*26 + 1
        System.out.println(sol.convertToTitle(18278)); // 26*26*26 + 26*26 + 26
        System.out.println(sol.convertToTitle(18279)); // 26*26*26 + 26*26 + 26 + 1
    }

    private static final int BASE = 26;

    public String convertToTitle(int columnNumber) {
        int next = columnNumber;
        StringBuilder sb = new StringBuilder();
        while (next > 0) {
            next = next - 1;
            int left = next % BASE;
            next = next / BASE;
            sb.append((char) ('A' + left));
        }

        return sb.reverse().toString();
    }
}
