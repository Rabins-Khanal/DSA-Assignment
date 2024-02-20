package Questionno2;

import java.util.*;

public class SecretSharing {
    public static void main(String[] args) {
        int totalIndividuals = 5;
        int[][] secretIntervals = {{0, 2}, {1, 3}, {2, 4}};
        //first person 
        int initialPerson = 0;

        // getting list of individuals who know the secret
        List<Integer> peopleWhoKnowSecret = identifyPeopleKnowingSecret(totalIndividuals, secretIntervals, initialPerson);
        System.out.println("People who know the secret: " + peopleWhoKnowSecret);
    }
    // Method to know people who know the secret
    public static List<Integer> identifyPeopleKnowingSecret(int totalIndividuals, int[][] secretIntervals, int initialPerson) {
        boolean[] knowsTheSecret = new boolean[totalIndividuals];
        knowsTheSecret[initialPerson] = true;
        for (int[] interval : secretIntervals) {
            for (int person = interval[0]; person <= interval[1]; person++) {
                if (knowsTheSecret[person]) {
                    // Marking all individuals within the interval as knowing the secret
                    for (int markPerson = interval[0]; markPerson <= interval[1]; markPerson++) {
                        knowsTheSecret[markPerson] = true;
                    }
                    break;
                }
            }
        }

        // individuals who know the secret
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < totalIndividuals; i++) {
            if (knowsTheSecret[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
