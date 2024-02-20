package Questionno1;

import java.util.PriorityQueue;

public class EngineBuilding {
    // Method to calculate the minimum time required to build all engines
    public static int minBuildTime(int[] engines, int splitCost) {
        // a priority queue to hold the engines
        PriorityQueue<Integer> engineQueue = new PriorityQueue<>();

        // Adding engine build times to the priority queue
        for (int engine : engines) {
            engineQueue.offer(engine);
        }

        // Combining engines until there's only one left
        while (engineQueue.size() > 1) {
            int fastestEngine = engineQueue.poll();
            int secondFastestEngine = engineQueue.poll();
            engineQueue.offer(secondFastestEngine + splitCost);
        }
        return engineQueue.poll();
    }

    public static void main(String[] args) {
        int[] engines = {1,2,3};
        int splitTime = 1;
        // minimum time to build all engines
        int totalTime = minBuildTime(engines, splitTime);
        System.out.println("Minimum time to build all engines: " + totalTime);
    }
}

