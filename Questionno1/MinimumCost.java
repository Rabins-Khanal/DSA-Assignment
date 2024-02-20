package Questionno1;
public class MinimumCost {
    public int miniCost(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;

        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k];

        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (j == l)
                        continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][l] + costs[i][j]);
                }
            }
        }
        int resultMiniCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            resultMiniCost = Math.min(resultMiniCost, dp[n - 1][j]);
        }

        return resultMiniCost;
    }

    public static void main(String[] args) {
        MinimumCost finalCost = new MinimumCost();
        int[][] costs = { { 1, 3, 2 }, { 4, 6, 8 }, { 3, 1, 5 } };
        System.out.println("The minimum cost is: " + finalCost.miniCost(costs));

    }
}
