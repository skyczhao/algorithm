package leetcode;

import java.util.*;

/**
 * NumberOfValidWordsForEachPuzzle
 * https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 1178. 猜字谜
 * https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle/solution/er-jin-zhi-ya-suo-er-jin-zhi-qiu-zi-ji-f-1a4p/
 *
 * @since 2021-02-26
 */
public class NumberOfValidWordsForEachPuzzle {
    public static void main(String[] args) {
        NumberOfValidWordsForEachPuzzle sol = new NumberOfValidWordsForEachPuzzle();
        // List<Integer> res = sol.findNumOfValidWords(new String[]{"apple", "pleas", "please"},
        //         new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"});
        List<Integer> res = sol.findNumOfValidWords(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"});
//                new String[]{"ac", "ca"});
        for (Integer i : res) {
            System.out.println(i);
        }
    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        // 二进制表达, 压缩存储
        Map<Integer, Integer> wordIdxCount = new HashMap<>();
        for (String word : words) {
            int wordIdx = 0;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                wordIdx = wordIdx | (1 << idx);
            }
            int size = 0;
            int tmp = wordIdx;
            while (tmp > 0) {
                size += (tmp & 1);
                tmp >>= 1;
            }
            if (size <= 7) { // 因为谜面只有7位, 谜底超过7个不同的字符肯定是不合适的
                wordIdxCount.put(wordIdx, wordIdxCount.getOrDefault(wordIdx, 0) + 1);
            }
        }

        // 枚举判断, 取代遍历比较
        List<Integer> result = new ArrayList<>(puzzles.length);
        for (String puzzle : puzzles) {
            int firstIdx = 1 << (puzzle.charAt(0) - 'a');
            int puzzleIdx = 0;
            for (int i = 0; i < puzzle.length(); i++) {
                puzzleIdx = puzzleIdx | (1 << (puzzle.charAt(i) - 'a'));
            }

            Set<Integer> puzzleSubsets = new HashSet<>();
            int tmp = puzzleIdx;
            while (tmp > 0) {
                puzzleSubsets.add(tmp | firstIdx); // 确保首位为1
                tmp = (tmp - 1) & puzzleIdx;
            }

            int count = 0;
            for (Integer puzzleSubIdx : puzzleSubsets) {
                count += wordIdxCount.getOrDefault(puzzleSubIdx, 0);
            }
            result.add(count);
        }
        return result;
    }

    public List<Integer> findNumOfValidWords_tle2(String[] words, String[] puzzles) {

        Map<Integer, Integer> wordIdxCount = new HashMap<>();
        for (String word : words) {
            int wordIdx = 0;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                wordIdx = wordIdx | (1 << idx);
            }
            int size = 0;
            int tmp = wordIdx;
            while (tmp > 0) {
                size += (tmp & 1);
                tmp >>= 1;
            }
            if (size <= 7) { // 因为谜面只有7位, 谜底超过7个不同的字符肯定是不合适的
                wordIdxCount.put(wordIdx, wordIdxCount.getOrDefault(wordIdx, 0) + 1);
            }
        }

        List<Integer> result = new ArrayList<>(puzzles.length);
        for (String puzzle : puzzles) {
            int firstIdx = 1 << (puzzle.charAt(0) - 'a');
            int puzzleIdx = 0;
            for (int i = 0; i < puzzle.length(); i++) {
                puzzleIdx = puzzleIdx | (1 << (puzzle.charAt(i) - 'a'));
            }

            int count = 0;
            for (Integer wordIdx : wordIdxCount.keySet()) {
                if ((wordIdx & firstIdx) < 1) {
                    continue;
                }

                int xor = puzzleIdx ^ wordIdx;
                int not = ~puzzleIdx;
                if ((xor & not) > 0) {
                    continue;
                }

                count += wordIdxCount.get(wordIdx);
            }
            result.add(count);
        }
        return result;
    }

    // TLE
    public List<Integer> findNumOfValidWords_tle(String[] words, String[] puzzles) {

        List<Set<Character>> wordSets = new ArrayList<>(words.length);
        for (String word : words) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < word.length(); i++) {
                set.add(word.charAt(i));
            }
            wordSets.add(set);
        }

        List<Integer> result = new ArrayList<>(puzzles.length);
        for (String puzzle : puzzles) {
            boolean[] arr = new boolean[26];
            for (int i = 0; i < puzzle.length(); i++) {
                arr[puzzle.charAt(i) - 'a'] = true;
            }

            int count = 0;
            for (int i = 0; i < words.length; i++) {
                Set<Character> set = wordSets.get(i);

                if (!set.contains(puzzle.charAt(0))) {
                    continue;
                }
                boolean flag = true;
                for (Character c : set) {
                    if (!arr[c - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    count++;
                }
            }

            result.add(count);
        }
        return result;
    }
}
