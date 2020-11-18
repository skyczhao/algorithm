package leetcode;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * GasStation
 * https://leetcode-cn.com/problems/gas-station/
 * 134. 加油站
 *
 * @since 2020-11-18
 */
public class GasStation {
    public static void main(String[] args) {
        GasStation sol = new GasStation();

        System.out.println(sol.canCompleteCircuit(
                new int[]{1, 2, 3, 4, 5},
                new int[]{3, 4, 5, 1, 2}
        ));
        System.out.println(sol.canCompleteCircuit(
                new int[]{2, 3, 4,},
                new int[]{3, 4, 3}
        ));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length < 1 ||
                cost == null || cost.length < 1 ||
                gas.length != cost.length) {
            return -1;
        }

        int size = gas.length;
        int[] diffs = new int[size];
        for (int idx = 0; idx < size; idx++) {
            diffs[idx] = gas[idx] - cost[idx];
        }
        for (int startIdx = 0; startIdx < size; startIdx++) {
            if (diffs[startIdx] < 0) {
                continue;
            }

            int sum = diffs[startIdx];
            int nextIdx = startIdx;
            while (sum >= 0) {
                nextIdx = (nextIdx + 1) % size;
                sum += diffs[nextIdx];
                if (nextIdx == startIdx) {
                    break;
                }
            }
            if (sum >= 0) {
                return nextIdx;
            }
        }

        return -1;
    }

    class CarState {
        public int gas = 0;
        public int gasStation = 0;
        public int moveTimes = 0;

        public CarState(int gas, int gasStation, int moveTimes) {
            this.gas = gas;
            this.gasStation = gasStation;
            this.moveTimes = moveTimes;
        }
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (gas == null || gas.length < 1 ||
                cost == null || cost.length < 1 ||
                gas.length != cost.length) {
            return -1;
        }

        Queue<CarState> queue = new LinkedTransferQueue<>();
        int size = gas.length;
        for (int idx = 0; idx < size; idx++) {
            queue.add(new CarState(gas[idx], idx, 0));
        }

        while (!queue.isEmpty()) {
            CarState curr = queue.poll();
            if (curr.gas < cost[curr.gasStation]) {
                continue;
            }
            int nextGasStation = (curr.gasStation + 1) % size;
            if (curr.moveTimes + 1 == size) {
                return nextGasStation;
            }

            queue.add(new CarState(curr.gas - cost[curr.gasStation] + gas[nextGasStation],
                    nextGasStation,
                    curr.moveTimes + 1));
        }
        return -1;
    }
}
