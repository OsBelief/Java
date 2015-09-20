
package com.java.algorithm;

/**
 * 常用排序算法
 * 
 * @author Belief
 */
public class Sort {
    public static void main(String[] args) {
        int[] data = {
                10, 22, 11, 222, 111, 0, 3, 5, 3, 54, 34, 1, 2
        };
        // insertSort(data);
        bubbleSort(data);
        for (int i = 0, len = data.length; i < len; i++) {
            System.out.println(data[i]);
        }
    }

    /**
     * 直接插入排序<br>
     * 基本思想:每次将一个待排序的记录，按其关键字大小插入到前面已经排好序的子序列中的适当位置，直到全部记录插入完成为止
     * 
     * @param data
     */
    public static void insertSort(int[] data) {
        for (int i = 1, len = data.length; i < len; i++) {
            for (int j = i; j > 0 && data[j - 1] > data[j]; j--) {
                int t = data[j - 1];
                data[j - 1] = data[j];
                data[j] = t;
            }
        }
    }

    /**
     * 冒泡排序<br>
     * 基本思想:两两比较,将较大的元素沉到第N-1-i个位置
     * 
     * @param data
     */
    public static void bubbleSort(int[] data) {
        for (int i = 0, len = data.length; i < len; i++) {
            for (int j = 1; j < len - i; j++) {
                if (data[j] < data[j - 1]) {
                    int t = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = t;
                }
            }
        }
    }
}
