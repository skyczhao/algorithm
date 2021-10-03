package leetcode;

/**
 * LicenseKeyFormatting
 * https://leetcode-cn.com/problems/license-key-formatting/
 * 482. 密钥格式化
 * https://leetcode-cn.com/problems/license-key-formatting/solution/cong-hou-wang-qian-bian-li-by-oshdyr-dmzj/
 *
 * @author tobin
 * @since 2021-10-04
 */
public class LicenseKeyFormatting {
    public static void main(String[] args) {
        LicenseKeyFormatting sol = new LicenseKeyFormatting();
        System.out.println(sol.licenseKeyFormatting("2-5g-3-J", 2));
    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder resultSB = new StringBuilder();
        String upper_s = s.toUpperCase(); // BUG 1
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = upper_s.charAt(i);
            if (c == '-') {
                continue;
            }

            if (resultSB.length() > 0 && count % k == 0) {
                resultSB.append('-');
                count = 0;
            }
            resultSB.append(c);
            count++;
        }
        return resultSB.reverse().toString();
    }
}
