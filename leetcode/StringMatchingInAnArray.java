package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * StringMatchingInAnArray
 * https://leetcode.cn/problems/string-matching-in-an-array/
 * 1408. 数组中的字符串匹配
 * https://leetcode.cn/problems/string-matching-in-an-array/solution/bao-li-sou-suo-pan-duan-by-oshdyr-f81q/
 *
 * @author tobin
 * @since 2022-08-06
 */
public class StringMatchingInAnArray {

    public static void main(String[] args) {
        StringMatchingInAnArray sol = new StringMatchingInAnArray();
        List<String> res = sol.stringMatching(new String[]{"mass", "as", "hero", "superhero"});
        for (String str : res) {
            System.out.println(str);
        }
    }

    public List<String> stringMatching(String[] words) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    if (words[j].contains(words[i])) {
                        result.add(words[i]);
                        break;
                    }
                }
            }
        }

        return result;
    }
}
