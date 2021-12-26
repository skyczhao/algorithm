package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * OccurrencesAfterBigram
 * https://leetcode-cn.com/problems/occurrences-after-bigram/
 * 1078. Bigram 分词
 * https://leetcode-cn.com/problems/occurrences-after-bigram/solution/mo-ni-pi-pei-by-oshdyr-3298/
 *
 * @author tobin
 * @since 2021-12-26
 */
public class OccurrencesAfterBigram {
    public static void main(String[] args) {
        OccurrencesAfterBigram sol = new OccurrencesAfterBigram();
        String[] res1 = sol.findOcurrences(
                "alice is a good girl she is a good student",
                "a",
                "good");
        for (String str : res1) {
            System.out.println(str);
        }
        System.out.println("===");
    }


    public String[] findOcurrences(String text, String first, String second) {
        if (text == null || text.length() < 1) {
            return null;
        }
        String[] parts = text.split(" ");
        if (parts.length < 1) {
            return null;
        }

        List<String> resList = new LinkedList<>();
        boolean lastIsSecond = false;
        boolean lastIsFirst = false;
        for (String curr : parts) {
            if (lastIsSecond) {
                resList.add(curr);
                lastIsSecond = false;
            }
            if (lastIsFirst) {
                if (curr.equals(second)) {
                    lastIsSecond = true;
                }
                lastIsFirst = false;
            }
            if (curr.equals(first)) {
                lastIsFirst = true;
            }
        }

        String[] resArr = new String[resList.size()];
        int idx = 0;
        for (String res : resList) {
            resArr[idx++] = res;
        }
        return resArr;
    }
}
