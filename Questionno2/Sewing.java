package Questionno2;

public class Sewing {
    public static int equalizeDresses(int[] machines) {
        int numMachines = machines.length;
        int totalDresses = 0;

        for (int dress : machines) {
            totalDresses += dress;
        }
        if (totalDresses % numMachines != 0) {
            return -1;
        }

        
        int targetDresses = totalDresses / numMachines;
        int moves = 0;
        for (int i = 0; i < numMachines; i++) {
            int difference = targetDresses - machines[i];
            if (difference > 0) {
                moves += Math.min(difference, machines[i + 1]);
                machines[i + 1] -= Math.min(difference, machines[i + 1]);
            } else if (difference < 0) {
                // Moving excess dresses to adjacent machine if needed
                moves += Math.min(-difference, machines[i - 1]);
                machines[i - 1] -= Math.min(-difference, machines[i - 1]);
            }
        }
        return moves;
    }

    public static void main(String[] args) {
        int[] machines = {1, 0, 5};
        // Calculating minimum moves required to equalize dresses
        int moves = equalizeDresses(machines);
        System.out.println("Minimum moves required to equalize dresses: " + moves);
    }
}
