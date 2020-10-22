package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * PartitionLabels
 * https://leetcode-cn.com/problems/partition-labels/
 *
 * @since 2020-10-22
 */
public class PartitionLabels {

    public static void main(String[] args) {
        PartitionLabels sol = new PartitionLabels();

        List<Integer> res = sol.partitionLabels("ababcbacadefegdehijhklij");
        for (Integer length : res) {
            System.out.println(length);
        }
    }

    public List<Integer> partitionLabels(String S) {
        // store each char's last index
        Map<Character, Integer> charEndIdx = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            charEndIdx.put(S.charAt(i), i);
        }

        List<Integer> subStrLength = new LinkedList<>();
        // try to spilt from first char to end char
        for (int startIdx = 0; startIdx < S.length(); ) {
            // start: detect substring
            // firstly, the start char decide the end char
            int endIdx = charEndIdx.get(S.charAt(startIdx));
            for (int i = startIdx; i <= endIdx; i++) {
                // recursively, the inner char affect the substring end index (or length)
                int currEndIdx = charEndIdx.get(S.charAt(i));
                if (currEndIdx > endIdx) {
                    endIdx = currEndIdx;
                }
            }
            // end: detect substring

            // store current substring length
            subStrLength.add(endIdx - startIdx + 1);

            // for next substring
            startIdx = endIdx + 1;
        }
        return subStrLength;
    }
}
