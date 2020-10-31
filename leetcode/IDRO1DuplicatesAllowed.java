package leetcode;

import java.util.*;

/**
 * IDRO1DuplicatesAllowed
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 *
 * @since 2020-10-31
 */
public class IDRO1DuplicatesAllowed {

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        System.out.println(collection.insert(0));
        System.out.println(collection.remove(0));
        System.out.println(collection.insert(-1));
        System.out.println(collection.remove(0));
        System.out.println(collection.getRandom());
        System.out.println(collection.getRandom());
        
//        System.out.println(collection.insert(1));
//        System.out.println(collection.remove(1));
//        System.out.println(collection.insert(1));
//        System.out.println(collection.remove(1));
//        System.out.println(collection.getRandom());
//        System.out.println(collection.getRandom());

//        // 向集合中插入 1 。返回 true 表示集合不包含 1 。
//        System.out.println(collection.insert(1));
//
//        // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
//        System.out.println(collection.insert(1));
//
//        // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
//        System.out.println(collection.insert(2));
//
//        // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
//        System.out.println(collection.getRandom());
//
//        // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
//        System.out.println(collection.remove(1));
//
//        // getRandom 应有相同概率返回 1 和 2 。
//        System.out.println(collection.getRandom());

    }

    static class RandomizedCollection {

        Map<Integer, Set<Integer>> container;
        List<Integer> values;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            this.container = new HashMap<>();
            this.values = new ArrayList<>(); // use array instead of linked list
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            values.add(val);
            int index = values.size() - 1;

            if (container.containsKey(val)) {
                container.get(val).add(index);
                return false;
            }

            Set<Integer> idxs = new HashSet<>();
            idxs.add(index);
            container.put(val, idxs);
            return true;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (values.isEmpty() ||
                    container.isEmpty() ||
                    !container.containsKey(val)) {
                return false;
            }

            // KEY: remove from last to begin, don't change other value order
            int lastIdx = values.size() - 1;
            int lastVal = values.get(lastIdx);
            if (lastVal == val) {
                // remove idx directly
                container.get(lastVal).remove(lastIdx);
            } else {
                // pick exist index
                Set<Integer> idxs = container.get(val);
                int targetIdx = 0;
                for (Integer idx : idxs) {
                    targetIdx = idx;
                    break;
                }

                // swap idx between val and lastValue
                container.get(val).remove(targetIdx);
                container.get(lastVal).remove(lastIdx);
                container.get(lastVal).add(targetIdx);
                values.set(targetIdx, lastVal);
            }
            values.remove(lastIdx);

            // KEY: remove empty values immediately
            if (container.get(val).isEmpty()) {
                container.remove(val);
            }
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            if (values.isEmpty()) { // bug1
                return -1;
            }
            int idx = (int) Math.floor(Math.random() * values.size());
            return values.get(idx);
        }
    }
}
