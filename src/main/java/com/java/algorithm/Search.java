
package com.java.algorithm;

/**
 * 常见的查找算法
 * 
 * @author Belief
 */
public class Search {
    public static void main(String[] args) {
        int[] a = {
                3, 4, 5, 8, 8, 8, 8, 10, 13, 14
        };
        int v = binarySearch(a, 8);
        System.out.println("下标: " + v);
    }

    /**
     * 二分法查找<br>
     * 时间复杂度:O(logn)<br>
     * 基本思想:假设数据是按升序排序的，对于给定值 x，从序列的中间位置开始比较，如果当前位置值等于 x，则查找成功；若 x
     * 小于当前位置值，则在数列的前半段中查找；若 x 大于当前位置值则在数列的后半段中继续查找，直到找到为止
     * 
     * @param a
     * @param x
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        // write your code here
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int middle = (high + low) / 2;
            if (target == nums[middle]) {
                return middle;
            }
            if (target < nums[middle]) {
                high = middle - 1;
            }
            if (target > nums[middle]) {
                low = middle + 1;
            }
        }
        return -1;
    }
}
