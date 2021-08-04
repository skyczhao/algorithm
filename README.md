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
1. C++/C，在没有java现有数据结构/模板方法/语法糖协助下，能不能更快地使用比较简单的语言本身的支持来实现解题
2. 挑战时间、空间最佳，而不仅仅是通过
3. 图是一直想深入理解的，实际问题怎么转为图的方式解决；

Conclusion
-----
1. Algebra
    - BigNumber: [2. 两数相加](leetcode/add-two-numbers.cpp), [989. 数组形式的整数加法](leetcode/Add2ArrayFormOfInt.java)
    - Arithmetic: [15. 三数之和](leetcode/3sum.cpp), [18. 四数之和](leetcode/4sum.cpp)
    - [1006. 笨阶乘](leetcode/ClumsyFactorial.java), [633. 平方数之和](leetcode/SumOfSquareNumbers.java) 首尾指针, [1734. 解码异或后的排列](leetcode/DecodeXoredPermutation.java)
    - [12. 整数转罗马数字](leetcode/IntegerToRoman.java), [461. 汉明距离](leetcode/HammingDistance.java), [477. 汉明距离总和](leetcode/TotalHammingDistance.java)
2. Search / Match
    - DFS: [51. N 皇后](leetcode/n-queens.cpp), [52. N皇后 II](leetcode/n-queens-ii.cpp)
    - BFS: [1239. 串联字符串的最大长度](leetcode/MaximumLengthOfAConcatenatedStringWithUniqueCharacters.java), [752. 打开转盘锁](leetcode/OpenTheLock.java), [743. 网络延迟时间](leetcode/NetworkDelayTime.java)
    - [1178. 猜字谜](leetcode/NumberOfValidWordsForEachPuzzle.java) 二进制,压缩,优化小集交大集的搜索
    - [74. 搜索二维矩阵](leetcode/SearchS2dMatrix.java)
    - [81. 搜索旋转排序数组 II](leetcode/SearchInRotatedSortedArrayII.java) 条件式分治搜索, [153. 寻找旋转排序数组中的最小值](leetcode/FindMinimumInRotatedSortedArray.java)
    - [28. 实现 strStr()](leetcode/ImplementStrstr.java) KMP字符串匹配算法, [1011. 在 D 天内送达包裹的能力](leetcode/CapacityToShipPackagesWithinDDays.java) 二分搜索的适用场景, [275. H 指数 II](leetcode/HIndexII.java)
    - [403. 青蛙过河](leetcode/FrogJump.java) 记忆化搜索
3. Sort
    - Merge: [977. 有序数组的平方](leetcode/SquaresOfASortedArray.java)
    - [164. 最大间距](leetcode/MaximumGap.java) 基数排序
    - [321. 拼接最大数](leetcode/CreateMaximumNumber.java) 单调栈结合了归并的思想
    - [1046. 最后一块石头的重量](leetcode/LastStoneWeight.java) 插入(排序), 注意插入尾部情形
3. Dynamic Programming
    - [837. 新21点](leetcode/new_21_game.java), [746. 使用最小花费爬楼梯](leetcode/MinCostClimbingStairs.java), [338. 比特位计数](leetcode/CountingBits.java), [879. 盈利计划](leetcode/ProfitableSchemes.java), [518. 零钱兑换 II](leetcode/CoinChange2.java), [877. 石子游戏](leetcode/StoneGame.java)
    - With Tree: [530. 二叉搜索树的最小绝对差](leetcode/MinimumAbsoluteDifferenceInBst.java)
    - [62. 不同路径](leetcode/UniquePaths.java) 可以用组合方法解题
    - dijkstra: [1631. 最小体力消耗路径](leetcode/PathWithMinimumEffort.java)
    - [354. 俄罗斯套娃信封问题](leetcode/RussianDollEnvelopes.java), [115. 不同的子序列](leetcode/DistinctSubsequences.java)
    - [面试题 17.21. 直方图的水量](leetcode/VolumeOfHistogramLcci.java), [213. 打家劫舍 II](leetcode/HouseRobberII.java), [91. 解码方法](leetcode/DecodeWays.java), [1269. 停在原地的方案数](leetcode/NumberOfWaysToStayInTheSamePlaceAfterSomeSteps.java)
4. Mathematics
    - [134. 加油站](leetcode/GasStation.java) 差分累积计算
    - [204. 计数质数](leetcode/CountPrimes.java), [368. 最大整除子集](leetcode/LargestDivisibleSubset.java), [342. 4的幂](leetcode/PowerOfFour.java)
    - [930. 和相同的二元子数组](leetcode/BinarySubarraysWithSum.java), [168. Excel表列名称](leetcode/ExcelSheetColumnTitle.java)
    - [剑指 Offer 52](leetcode/LiangGeLianBiaoDeLcof.java) 转为两个链表和的追及问题
5. Graph
    - shorest path: [127. 单词接龙](leetcode/WordLadder.java) 或者参考题解的双向BFS
    - DAG: [406. 根据身高重建队列](leetcode/QueueReconstructionByHeight.java)
    - Matrix: [1030. 距离顺序排列矩阵单元格](leetcode/MatrixCellsInDistanceOrder.java) 矩阵遍历
5. Tree
    - Traversal: [94. 中序遍历](leetcode/BinaryTreeInorderTraversal.java), [145. 后序遍历](leetcode/BinaryTreePostorderTraversal.java), [144. 前序遍历](leetcode/BinaryTreePreorderTraversal.java), [103. 锯齿形层序遍历](leetcode/BinaryTreeZigzagLevelOrderTraversal.java), [341. 扁平化嵌套列表迭代器](leetcode/FlattenNestedListIterator.java), [872. 叶子相似的树](leetcode/LeafSimilarTrees.java), [993. 二叉树的堂兄弟节点](leetcode/CousinsInBinaryTree.java)
    - Maintain: [701. 插入操作](leetcode/BinarySearchTreeInsert.java)
    - 并查集: [947. 移除最多的同行或同列石头](leetcode/MostStonesRemoved.java) 二维构造一维并查集, [1579. 保证图可完全遍历](leetcode/RemoveMaxNumberOfEdges.java), [778. 水位上升的泳池中游泳](leetcode/SwimInRisingWater.java)
    - Others: [116. 填充下一个右侧节点指针](leetcode/PopulatingNextRightPointersInEachNode.java), [117. 填充下一个右侧节点指针 II](leetcode/PopulatingNextRightPointersInEachNodeII.java), [222. 完全二叉树的节点个数](leetcode/CountCompleteTreeNodes.java) 利用完全二叉树的特征剪枝统计
    - [783. 二叉搜索树节点最小距离](leetcode/MinimumDistanceBetweenBstNodes.java), [208. 实现 Trie (前缀树)](leetcode/ImplementTriePrefixTree.java), [938. 二叉搜索树的范围和](leetcode/RangeSumOfBst.java)
    - [897. 递增顺序搜索树](leetcode/IncreasingOrderSearchTree.java), [1104. 二叉树寻路](leetcode/PathInZigzagLabelledBinaryTree.java) Z型遍历
6. List
    - Cycle: [142. 环形链表 II](leetcode/LinkedListCycleII.java)
    - Swap: [328. 奇偶链表](leetcode/OddEvenLinkedList.java) 指针操作, [92. 反转链表 II](leetcode/ReverseLinkedListII.java)
    - [724. 寻找数组的中心索引](leetcode/FindPivotIndex.java) 数组等值分割, [82. 删除排序链表中的重复元素 II](leetcode/RemoveDuplicatesFromSortedListII.java), [80. 删除有序数组中的重复项 II](leetcode/RemoveDuplicatesFromSortedArrayII.java)
    - [480. 滑动窗口中位数](leetcode/SlidingWindowMedian.java) 滑动窗口结合数据结构, [1004. 最大连续1的个数 III](leetcode/MaxConsecutiveOnesIII.java), [456. 132 模式](leetcode/Pattern132.java), [220. 存在重复元素 III](leetcode/ContainsDuplicateIII.java), [611. 有效三角形的个数](leetcode/ValidTriangleNumber.java)
    - [1310. 子数组异或查询](leetcode/XorQueriesOfASubarray.java), [1190. 反转每对括号间的子串](leetcode/ReverseSubstringsBetweenEachPairOfParentheses.java)
    - [138. 复制带随机指针的链表](leetcode/CopyListWithRandomPointer.java) 栅栏创建避免索引, [1743. 从相邻元素对还原数组](leetcode/RestoreTheArrayFromAdjacentPairs.java)
    - [581. 最短无序连续子数组](leetcode/ShortestUnsortedContinuousSubarray.java)
7. stack & queue
    - [232. 用栈实现队列](leetcode/ImplementQueueUsingStacks.java), [726. 原子的数量](leetcode/NumberOfAtoms.java)
    - [224. 基本计算器](leetcode/BasicCalculator.java) 栈操作算式
8. Engineering
    - SQL: 
9. Inspiration
    - [234. 回文链表](leetcode/PalindromeLinkedList.java) 借用递归实现单向链表的'优雅'反向
    - [381. O(1) 时间插入、删除和获取随机元素 - 允许重复](leetcode/IDRO1DuplicatesAllowed.java) 复合数据结构
    - [31. 下一个排列](leetcode/NextPermutation.java) 计算全排列指定值下一个顺序值, 非递归方式规则推导
    - [135. 分发糖果](leetcode/Candy.java) 巧妙分治
    - [995. K 连续位的最小翻转次数](leetcode/MinimumNumberKConsecutiveBitFlips.java)
    - [面试题 17.10. 主要元素](leetcode/FindMajorityElementLcci.java) 二次遍历
10. 贪心
    - [861. 翻转矩阵后的得分](leetcode/ScoreAfterFlippingMatrix.java) 理解行转列转的顺序不影响结果, 在此基础进行贪心
    - [738. 单调递增的数字](leetcode/MonotoneIncreasingDigits.java) 结合数值类数据特征进行贪心
    - [455. 分发饼干](leetcode/AssignCookies.java) 贪心+剪枝, [1838. 最高频元素的频数](leetcode/FrequencyOfTheMostFrequentElement.java)
    - [330. 按要求补齐数组](leetcode/PatchingArray.java) 基于数学的贪心, 难题
11. Others
    - data compress [925. 长按键入](leetcode/LongPressedName.java), simulating [763. 划分字母区间](leetcode/PartitionLabels.java) [659. 分割数组为连续子序列](leetcode/SplitArrayIntoConsecutiveSubsequences.java)
    - array operation [57. 插入区间](leetcode/InsertInterval.java), [941. 有效的山脉数组](leetcode/ValidMountainArray.java)
    - bit/binary [1356. 根据数字二进制下 1 的数目排序](leetcode/SortIntByNumOf1.java) 考虑递推(转移函数)处理
    - [54. 螺旋矩阵](leetcode/SpiralMatrix.java), [59. 螺旋矩阵 II](leetcode/SpiralMatrixII.java)
12. Map
    - [面试题 10.02. 变位词组](leetcode/GroupAnagramsLcci.java)