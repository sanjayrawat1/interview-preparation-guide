package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

/**
 * @author Sanjay Singh Rawat
 */
public class P0SlidingWindowTemplate {

    public int fn(int[] arr) {
        int left = 0;
        int ans = 0;
        int curr = 0;

        boolean WINDOW_CONDITION_BROKEN = true;

        for (int right = 0; right < arr.length; right++) {
            // do logic here to add arr[right] to curr
            while (WINDOW_CONDITION_BROKEN) {
                // remove arr[left] from curr
                left++;
            }

            // update ans
        }

        return ans;
    }
}
