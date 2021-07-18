package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * GroupAnagramsLcci
 * https://leetcode-cn.com/problems/group-anagrams-lcci/
 * 面试题 10.02. 变位词组
 * https://leetcode-cn.com/problems/group-anagrams-lcci/solution/jian-li-zi-fu-chuan-suo-yin-by-oshdyr-9wbi/
 *
 * @author tobin
 * @since 2021-07-18
 */
public class GroupAnagramsLcci {

    public static void main(String[] args) {
        GroupAnagramsLcci sol = new GroupAnagramsLcci();

        List<List<String>> res = sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> list : res) {
            for (String str : list) {
                System.out.print(str + ", ");
            }
            System.out.println();
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] indexArr = new int[26];
            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';
                indexArr[idx]++;
            }

            StringBuilder indexSb = new StringBuilder();
            for (int unit : indexArr) {
                indexSb.append(unit);
            }

            String index = indexSb.toString();
            List<String> history;
            if (map.containsKey(index)) {
                history = map.get(index);
            } else {
                history = new LinkedList<>();
            }
            history.add(str);
            map.put(index, history);
        }

        List<List<String>> result = new LinkedList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}

