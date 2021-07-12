package leetcode;

/**
 * HIndexII
 * https://leetcode-cn.com/problems/h-index-ii/
 * 275. H 指数 II
 * https://leetcode-cn.com/problems/h-index-ii/solution/jian-dan-dian-ti-jie-jiu-jian-dan-dian-b-icfn/
 *
 * @author tobin
 * @since 2021-07-12
 */
public class HIndexII {

    public static void main(String[] args) {
        HIndexII sol = new HIndexII();
        System.out.println(sol.hIndex(new int[]{0, 1, 3, 5, 6}));
        System.out.println(sol.hIndex(new int[]{100}));
        System.out.println(sol.hIndex(new int[]{0}));
        System.out.println(sol.hIndex(new int[]{11, 15}));
    }

    public int hIndex(int[] citations) {
        for (int h = citations.length; h > 0; h--) {
            int idx = citations.length - h;
            if (citations[idx] >= h) {
                return h;
            }
        }
        return 0;
    }


//    public int hIndex(int[] citations) {
//        int start = 0;
//        int end = citations.length - 1;
//        while (start <= end) {
//            if (end - start < 2) {
//                break;
//            }
//            int mid = (start + end) / 2;
//
//            int h = citations[mid];
//            int h1 = citations.length - mid;
//            if (h > h1) {
//                end = mid;
//            } else {
//                start = mid;
//            }
//        }
//
//        int h_e = citations[end];
//        if (h_e <= (citations.length - end)) {
//            return h_e;
//        }
//        int h_s = citations[start];
//
//        if (h_s <= (citations.length - start)) {
//            return h_s;
//        }
//        return 1;
//    }
}
