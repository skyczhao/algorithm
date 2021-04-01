package leetcode;

/**
 * ClumsyFactorial
 * https://leetcode-cn.com/problems/clumsy-factorial/
 * 1006. 笨阶乘
 * https://leetcode-cn.com/problems/clumsy-factorial/solution/mo-ni-jie-ti-by-oshdyr-bxtf/
 *
 * @since 2021-04-01
 */
public class ClumsyFactorial {
    public static void main(String[] args) {
        ClumsyFactorial sol = new ClumsyFactorial();
        System.out.println(sol.clumsy(10));
        System.out.println(sol.clumsy(1000));
        System.out.println(sol.clumsy(100));
        System.out.println(sol.clumsy(1));
        System.out.println(sol.clumsy(2));
        System.out.println(sol.clumsy(3));
        System.out.println(sol.clumsy(4));
        System.out.println(sol.clumsy(5));
        System.out.println(sol.clumsy(6));
        System.out.println(sol.clumsy(7));
        System.out.println(sol.clumsy(8));
        System.out.println(sol.clumsy(9));
    }

    public int clumsy(int N) {

        int result = -1;
        for (int next = N; next > 0; next -= 4) {
            int curr = next;

            if (next - 1 > 0) {
                curr *= (next - 1);
            } else {
                if (result < 0) {
                    result = curr;
                } else {
                    result = result - curr;
                }
                break;
            }

            if (next - 2 > 0) {
                curr /= (next - 2);
            } else {
                if (result < 0) {
                    result = curr;
                } else {
                    result = result - curr;
                }
                break;
            }

            if (next - 3 > 0) {
                curr += (next - 3);
            } else {
                if (result < 0) {
                    result = curr;
                } else {
                    result = result - curr;
                }
                break;
            }

            if (result < 0) {
                result = curr;
            } else {
                result = result - curr + 2 * (next - 3);
            }
        }

        return result;
    }
}
