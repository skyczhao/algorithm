package leetcode;

/**
 * ReversePrefixOfWord
 * https://leetcode-cn.com/problems/reverse-prefix-of-word/
 * 2000. 反转单词前缀
 * https://leetcode-cn.com/problems/reverse-prefix-of-word/solution/bian-li-mo-ni-cao-zuo-by-oshdyr-tx9n/
 *
 * @author tobin
 * @since 2022-02-02
 */
public class ReversePrefixOfWord {
    public static void main(String[] args) {
        ReversePrefixOfWord sol = new ReversePrefixOfWord();
        System.out.println(sol.reversePrefix("abcdefd", 'd'));
        System.out.println(sol.reversePrefix("xyxzxe", 'z'));
        System.out.println(sol.reversePrefix("abcd", 'z'));
    }

    public String reversePrefix(String word, char ch) {
        StringBuilder sb = new StringBuilder();

        int loc = -1;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            sb.append(curr);
            if (curr == ch) {
                loc = i;
                break;
            }
        }
        if (loc < 0) {
            return word;
        }
        StringBuilder nsb = sb.reverse();
        nsb.append(word.substring(loc+1));
        return nsb.toString();
    }
}
