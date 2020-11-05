package leetcode;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * WordLadder
 * https://leetcode-cn.com/problems/word-ladder/
 * 127. 单词接龙
 *
 * @since 2020-11-05
 */
public class WordLadder {
    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};

        WordLadder sol = new WordLadder();
        System.out.println(sol.ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() < 1) {
            return 0;
        }

        Set<Integer>[] nextWords = new Set[wordList.size() + 1];
        for (int idx = 0; idx < wordList.size() + 1; idx++) {
            nextWords[idx] = new HashSet<>();
        }

        int endIdx = buildGraph(beginWord, wordList, endWord, nextWords);
        if (endIdx < 0) {
            return 0;
        }

        return dijkstra(nextWords.length - 1, endIdx, nextWords);
    }

    private int dijkstra(int beginIdx, int endIdx, Set<Integer>[] nextWords) {
        int[] shortest = new int[nextWords.length];
        for (int idx = 0; idx < nextWords.length; idx++) {
            shortest[idx] = Integer.MAX_VALUE;
        }

        Queue<Integer> nodeQueue = new LinkedTransferQueue<>();
        nodeQueue.add(beginIdx);
        shortest[beginIdx] = 1;

        while (!nodeQueue.isEmpty()) {
            int currIdx = nodeQueue.poll();
            if (currIdx == endIdx) {
                continue;
            }

            int nextLength = shortest[currIdx] + 1;
            for (Integer nextIdx : nextWords[currIdx]) {
                if (shortest[nextIdx] > nextLength) {
                    shortest[nextIdx] = nextLength;
                    nodeQueue.add(nextIdx);
                }
            }
        }

        // 例外情况:
        // "hot"
        // "dog"
        // ["hot","dog"]
        return shortest[endIdx] == Integer.MAX_VALUE ? 0 : shortest[endIdx];
    }

    private int buildGraph(String beginWord, List<String> wordList, String endWord, Set<Integer>[] nextWords) {

        int endIdx = -1;
        for (int startIdx = 0; startIdx < wordList.size(); startIdx++) {
            String start = wordList.get(startIdx);
            if (start.equals(endWord)) {
                endIdx = startIdx;
            }
            for (int nextIdx = startIdx + 1; nextIdx < wordList.size(); nextIdx++) {
                String next = wordList.get(nextIdx);
                if (isNeighbor(start, next)) {
                    nextWords[startIdx].add(nextIdx);
                    nextWords[nextIdx].add(startIdx);
                }
            }
        }

        int startIdx = wordList.size();
        String start = beginWord;
        for (int nextIdx = 0; nextIdx < wordList.size(); nextIdx++) {
            String next = wordList.get(nextIdx);
            if (isNeighbor(start, next)) {
                nextWords[startIdx].add(nextIdx);
                nextWords[nextIdx].add(startIdx);
            }
        }

        return endIdx;
    }

    private boolean isNeighbor(String start, String end) {
        if (start == null || end == null || start.length() != end.length()) {
            return false;
        }

        int diffCount = 0;
        for (int idx = 0; idx < start.length(); idx++) {
            if (start.charAt(idx) != end.charAt(idx)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
