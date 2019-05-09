
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

    /**
     * 冒泡排序的优化
     * @param array
     */
    public static void bubbleSort2(int array[]) {
        int tmp = 0;
        boolean isSorted = true;
        int lastExchangeIndex = 0;    //记录最后一次交换的位置
        int sortBorder = array.length - 1;//无序数列的边界，每次比较只需要比到这里为止
        for (int i = 0; i < array.length; i++) {        //有序标记，每一轮的初始是true
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    lastExchangeIndex = j;//把无序数列的边界更新为最后一次交换元素的位置
                }
            }
            if (isSorted) {
                break;
            }
            sortBorder = lastExchangeIndex;
        }
    }

    /**
     * 直接插入排序<br>
     * 基本思想:直接插入排序是将无序区的第一个元素直接插入到有序区以形成一个更大的有序区，
     * 而直接选择排序是从无序区选一个最小的元素直接放到有序区的最后。
     * 
     * @param data
     */
    public static void selectSort(int[] data) {
        int i, j, n = data.length, minIndex;
        for (i = 0; i < n; i++) {
            minIndex = i; // 找最小元素的位置
            for (j = i + 1; j < n; j++) {
                if (data[minIndex] > data[j]) {
                    minIndex = j;
                }
            }
            int t = data[i];// 将这个元素放到无序区的开头
            data[i] = data[minIndex];
            data[minIndex] = t;
        }
    }
}
