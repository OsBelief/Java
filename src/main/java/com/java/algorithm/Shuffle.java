
package com.java.algorithm;

import java.util.Random;

/**
 * 生成长度为100,元素的范围是1~100的随机数组(音乐的随机播放)<br>
 * 实际上是洗牌算法的问题
 * 
 * @author Belief
 */
public class Shuffle {
    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0, l = data.length; i < l; i++) {
            data[i] = i + 1;
        }
        shuffle(data);
        for (int i = 0, l = data.length; i < l; i++) {
            System.out.println(data[i]);
        }
    }

    /**
     * 洗牌算法(Fisher–Yates shuffle费雪耶茨随机置换算法)<br>
     * 基本思想:从1到i(1<=i<=n)个数字中依次随机抽取一个数字，并放到一个新序列的尾端（该算法通过互换数字实现），逐渐形成一个新的序列。
     * 计算一下概率:如果某个元素被放入第i个位置，就必须是在前i-1次选取中都没有选到它，并且第 i次恰好选中它，其概率是1/n。
     * 
     * @param musicUrl
     */
    public static void shuffle(int[] data) {
        int shuffle_key;
        int temp;
        Random rand = new Random();
        int length = data.length;
        for (int i = length - 1; i > 0; i--) {
            shuffle_key = rand.nextInt(i + 1);
            temp = data[i];
            data[i] = data[shuffle_key];
            data[shuffle_key] = temp;
        }
    }
}
