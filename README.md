algorithm
=====

[![HitCount](http://hits.dwyl.com/skyczhao/algorithm.svg)](http://hits.dwyl.com/skyczhao/algorithm)

log
-----
Learning! Code more, test more and learn more.  
This folder contains some test codes. Some of them are my test on online judges, and some are just the test for the new algorithm.  
* Date: 05/07/2014 Change to another lab, but still coding. Keep going!  
* Date: 28/09/2014 Repeated course, so sad!  
* Date: 11/12/2014 Change the folder structure  
* Date: 28/09/2020 Add conclusion

Plan
-----

Conclusion
-----
1. Algebra
    - BigNumber: [2. 两数相加](leetcode/add-two-numbers.cpp)
    - Arithmetic: [15. 三数之和](leetcode/3sum.cpp), [18. 四数之和](leetcode/4sum.cpp)
2. Search
    - DFS: [51. N 皇后](leetcode/n-queens.cpp), [52. N皇后 II](leetcode/n-queens-ii.cpp)
3. Sort
    - Merge: [977. 有序数组的平方](leetcode/SquaresOfASortedArray.java)
    - [164. 最大间距](leetcode/MaximumGap.java) 基数排序
    - [321. 拼接最大数](leetcode/CreateMaximumNumber.java) 单调栈结合了归并的思想
3. Dynamic Programming
    - [837. 新21点](leetcode/new_21_game.java)
    - With Tree: [530. 二叉搜索树的最小绝对差](leetcode/MinimumAbsoluteDifferenceInBst.java)
    - [62. 不同路径](leetcode/UniquePaths.java) 可以用组合方法解题
4. Mathematics
    - [134. 加油站](leetcode/GasStation.java) 差分累积计算
    - [204. 计数质数](leetcode/CountPrimes.java)
5. Graph
    - shorest path: [127. 单词接龙](leetcode/WordLadder.java) 或者参考题解的双向BFS
    - DAG: [406. 根据身高重建队列](leetcode/QueueReconstructionByHeight.java)
    - Matrix: [1030. 距离顺序排列矩阵单元格](leetcode/MatrixCellsInDistanceOrder.java) 矩阵遍历
5. Tree
    - Traversal: [94. 中序遍历](leetcode/BinaryTreeInorderTraversal.java), [145. 后序遍历](leetcode/BinaryTreePostorderTraversal.java), [144. 前序遍历](leetcode/BinaryTreePreorderTraversal.java)
    - Maintain: [701. 插入操作](leetcode/BinarySearchTreeInsert.java)
    - Others: [116. 填充下一个右侧节点指针](leetcode/PopulatingNextRightPointersInEachNode.java), [117. 填充下一个右侧节点指针 II](leetcode/PopulatingNextRightPointersInEachNodeII.java), [222. 完全二叉树的节点个数](leetcode/CountCompleteTreeNodes.java) 利用完全二叉树的特征剪枝统计
6. List
    - Cycle: [142. 环形链表 II](leetcode/LinkedListCycleII.java)
    - Swap: [328. 奇偶链表](leetcode/OddEvenLinkedList.java) 指针操作
7. Engineering
    - SQL: 
8. Inspiration
    - [234. 回文链表](leetcode/PalindromeLinkedList.java) 借用递归实现单向链表的'优雅'反向
    - [381. O(1) 时间插入、删除和获取随机元素 - 允许重复](leetcode/IDRO1DuplicatesAllowed.java) 复合数据结构
    - [31. 下一个排列](leetcode/NextPermutation.java) 计算全排列指定值下一个顺序值, 非递归方式规则推导
9. 贪心
    - [861. 翻转矩阵后的得分](leetcode/ScoreAfterFlippingMatrix.java) 理解行转列转的顺序不影响结果, 在此基础进行贪心
    - [738. 单调递增的数字](leetcode/MonotoneIncreasingDigits.java) 结合数值类数据特征进行贪心
10. Others
    - data compress [925. 长按键入](leetcode/LongPressedName.java), simulating [763. 划分字母区间](leetcode/PartitionLabels.java) [659. 分割数组为连续子序列](leetcode/SplitArrayIntoConsecutiveSubsequences.java)
    - array operation [57. 插入区间](leetcode/InsertInterval.java), [941. 有效的山脉数组](leetcode/ValidMountainArray.java)
    - bit/binary [1356. 根据数字二进制下 1 的数目排序](leetcode/SortIntByNumOf1.java) 考虑递推(转移函数)处理