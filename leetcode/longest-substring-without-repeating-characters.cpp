
#include <iostream>

using namespace std;

class Solution
{
  public:
    int lengthOfLongestSubstring(string s)
    {
        int *has = new int[100];

        int size = s.length();
        int maxLength = 0;
        for (int i = 0; i < size; i++)
        {
            memset(has, 0, 100 * sizeof(int));
            int length = 0;
            // 起始点找错
            for (int j = i; j < size; j++)
            {
                int idx = s[j] - 'a';
                if (has[idx] == 1)
                {
                    break;
                }

                has[idx] = 1;
                length++;
            }
            if (length > maxLength)
            {
                maxLength = length;
            }
        }

        return maxLength;
    }
};

int main()
{

    cout << (new Solution())->lengthOfLongestSubstring("abcabcbb") << endl;
    cout << (new Solution())->lengthOfLongestSubstring("pwwkew") << endl;

    return 0;
}


// 最佳：
// 滑动窗口是数组/字符串问题中常用的抽象概念。 
// 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)[i,j)（左闭，右开）。
// 而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。
// 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。 
// 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。如果我们对所有的 ii 这样做，就可以得到答案。

// public class Solution {
//     public int lengthOfLongestSubstring(String s) {
//         int n = s.length();
//         Set<Character> set = new HashSet<>();
//         int ans = 0, i = 0, j = 0;
//         while (i < n && j < n) {
//             // 白板上推理一下: try to extend the range [i, j]
//             if (!set.contains(s.charAt(j))){
//                 set.add(s.charAt(j++));
//                 ans = Math.max(ans, j - i);
//             }
//             else {
//                 set.remove(s.charAt(i++));
//             }
//         }
//         return ans;
//     }
// }