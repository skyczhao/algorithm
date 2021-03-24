package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * FlattenNestedListIterator
 * https://leetcode-cn.com/problems/flatten-nested-list-iterator/
 * 341. 扁平化嵌套列表迭代器
 * https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/di-gui-jie-ti-by-oshdyr-phng/
 * 附: 参考使用DFS
 *
 * @author tobin
 * @since 2021-03-23
 */
public class FlattenNestedListIterator {
    public static void main(String[] args) {
        List<NestedInteger> values = new ArrayList<>();

        NestedInteger six = new NestedInteger();
        six.intValue = 6;
        NestedInteger four = new NestedInteger();
        four.intValue = 4;
        NestedInteger one = new NestedInteger();
        one.intValue = 1;


        NestedInteger list_six = new NestedInteger();
        list_six.listValue = new ArrayList<>();
        list_six.listValue.add(six);

        NestedInteger list_four_six = new NestedInteger();
        list_four_six.listValue = new ArrayList<>();
        list_four_six.listValue.add(four);
        list_four_six.listValue.add(list_six);

        NestedInteger list_one_four_six = new NestedInteger();
        list_one_four_six.listValue = new ArrayList<>();
        list_one_four_six.listValue.add(one);
        list_one_four_six.listValue.add(list_four_six);

        NestedIterator iterator = new NestedIterator(list_one_four_six.listValue);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {

    public Integer intValue = null;
    public List<NestedInteger> listValue = null;

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return intValue != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return intValue;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return listValue;
    }
}

//[[1,1],2,[1,1]]
//[1,[4,[6]]]
//[[]]
//[[],[3]]
class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> nestedList;
    private int idx;
    private NestedIterator iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.idx = 0;
    }

    @Override
    public Integer next() {
        if (this.iterator != null) {
            if (this.iterator.hasNext()) {
                return this.iterator.next();
            } else {
                this.iterator = null;
                this.idx++;
                if (this.idx >= this.nestedList.size()) {
                    return null;
                }
            }
        }

        if (this.nestedList.get(this.idx).isInteger()) {
            Integer value = this.nestedList.get(this.idx).getInteger();
            this.idx++;
            return value;
        }
        this.iterator = new NestedIterator(this.nestedList.get(this.idx).getList());
        return this.iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (this.idx >= nestedList.size()) {
            return false;
        }
        if (this.nestedList.get(this.idx).isInteger()) {
            return true;
        }
        if (this.iterator != null) {
            if (this.iterator.hasNext()) {
                return true;
            } else {
                this.iterator = null;
                this.idx++;
                return this.hasNext();
            }
        }

        this.iterator = new NestedIterator(this.nestedList.get(this.idx).getList());
        if (this.iterator.hasNext()) {
            return true;
        } else {
            this.iterator = null;
            this.idx++;
            return this.hasNext();
        }
    }
}
