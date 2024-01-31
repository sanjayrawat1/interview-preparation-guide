package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P15BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        assertThat(maxProfit(new int[] {})).isEqualTo(0);
        assertThat(maxProfit(new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9})).isEqualTo(9);
        assertThat(maxProfit(new int[] {7, 1, 5, 3, 6, 4})).isEqualTo(5);
        assertThat(maxProfit(new int[] {7, 6, 4, 3, 1})).isEqualTo(0);
        assertThat(maxProfit(new int[] {2, 6, 8, 7, 8, 7, 9, 4, 1, 2, 4, 5, 8})).isEqualTo(7);
        assertThat(maxProfit(new int[] {1, 4, 2})).isEqualTo(3);
    }

    private static int maxProfit(int[] prices) {
        int left = 0;
        int maxProfit = 0;

        for (int right = 1; right < prices.length; right++) {
            int currentProfit = prices[right] - prices[left];
            if (prices[left] < prices[right]) {
                maxProfit = Math.max(maxProfit, currentProfit);
            } else {
                left = right;
            }
        }
        return maxProfit;
    }
}
