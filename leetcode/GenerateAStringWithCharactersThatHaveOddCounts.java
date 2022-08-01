package leetcode;

/**
 * GenerateAStringWithCharactersThatHaveOddCounts
 * https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts/
 * 1374. 生成每种字符都是奇数个的字符串
 * https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts/solution/suanshu-by-oshdyr-9xp3/
 *
 * @author Tobin
 * @since 2022-08-01
 */
public class GenerateAStringWithCharactersThatHaveOddCounts {

    public static void main(String[] args) {
        GenerateAStringWithCharactersThatHaveOddCounts sol = new GenerateAStringWithCharactersThatHaveOddCounts();
        System.out.println(sol.generateTheString(7));
        System.out.println(sol.generateTheString(4));
        System.out.println(sol.generateTheString(2));
    }

    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            // 偶数, 拆成1+奇数
            for (int i = 0; i < n - 1; i++) {
                sb.append('a');
            }
            sb.append('b');
        } else {
            // 奇数
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
        }
        return sb.toString();
    }
}
