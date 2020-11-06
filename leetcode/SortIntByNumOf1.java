package leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * SortIntByNumOf1
 * https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/
 * 1356. 根据数字二进制下 1 的数目排序
 * 1. 位运算数1
 * 2. 加速: 类似动态规划, 数字A的1的数量, 由 A>>1的1的数量 加上 A的第一位是否1 组成
 *
 * @since 2020-11-06
 */
public class SortIntByNumOf1 {

    public static void main(String[] args) {
        SortIntByNumOf1 sol = new SortIntByNumOf1();

        int[] res = sol.sortByBits(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        for (int v : res) {
            System.out.print(v + ", ");
        }
        System.out.println();
    }


    class Tuple<K, V> {
        public K key;
        public V value;

        public Tuple(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int[] sortByBits(int[] arr) {
        List<Tuple<Integer, Integer>> list = new LinkedList<>();
        for (int key : arr) {
            list.add(new Tuple(key, getNumOf1(key)));
        }

        Collections.sort(list, new Comparator<Tuple<Integer, Integer>>() {
            @Override
            public int compare(Tuple<Integer, Integer> left, Tuple<Integer, Integer> right) {
                if (left.value != right.value) {
                    return left.value - right.value;
                }
                return left.key - right.key;
            }
        });

        int[] result = new int[arr.length];
        for (int idx = 0; idx < arr.length; idx++) {
            result[idx] = list.get(idx).key;
        }
        return result;
    }

    private int getNumOf1(int number) {
        int num = number;
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }
}
