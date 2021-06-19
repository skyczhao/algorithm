package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MaximumLengthOfAConcatenatedStringWithUniqueCharacters
 * https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 * 1239. 串联字符串的最大长度
 * https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/solution/bfsjie-ti-by-oshdyr-4pcc/
 *
 * @author tobin
 * @since 2021-06-19
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    public static void main(String[] args) {
        MaximumLengthOfAConcatenatedStringWithUniqueCharacters sol = new MaximumLengthOfAConcatenatedStringWithUniqueCharacters();

        System.out.println(sol.maxLength(Arrays.asList("un", "iq", "ue")));
        System.out.println(sol.maxLength(Arrays.asList("cha", "r", "act", "ers")));
        System.out.println(sol.maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz")));
    }

    public int maxLength(List<String> arr) {
        int maxCount = 0;

        Map<Integer, Integer> graphCounts = new HashMap<>();
        // parse each str
        for (String str : arr) {
            boolean flag = false;
            // count chars
            int[] charCounts = new int[26];
            for (int i = 0; i < str.length(); i++) {
                int cIdx = str.charAt(i) - 'a';
                charCounts[cIdx]++;
                if (charCounts[cIdx] > 1) {
                    flag = true;
                    break;
                }
            }
            // skip str had duplicate chars
            if (flag) {
                continue;
            }

            int gCnt = str.length();
            if (gCnt > maxCount) {
                maxCount = gCnt;
            }

            // to graph idx;
            int gIdx = toGraphIdx(charCounts);

            Map<Integer, Integer> nextGraphCounts = new HashMap<>();
            if (!graphCounts.containsKey(gIdx)) {
                nextGraphCounts.put(gIdx, gCnt);
            }
            for (Map.Entry<Integer, Integer> entry : graphCounts.entrySet()) {
                int key = entry.getKey();
                int keyCount = entry.getValue();
                if ((key & gIdx) == 0) {
                    int nextCount = keyCount + gCnt;
                    nextGraphCounts.put(key | gIdx, nextCount);
                    if (nextCount > maxCount) {
                        maxCount = nextCount;
                    }
                }
            }

            graphCounts.putAll(nextGraphCounts);
//            System.out.println();
        }

        return maxCount;
    }

    private int toGraphIdx(int[] charCounts) {
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res = (res << 1) + charCounts[i];
        }
        return res;
    }
}
