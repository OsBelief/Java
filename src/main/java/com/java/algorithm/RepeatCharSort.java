
package com.java.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串中将连续出现的字符按出现次数从大到小的顺序排序,例如aaabbbbcca则输出bbbb,aaa,cc,a
 * 
 * @author Belief
 */
public class RepeatCharSort {
    public static void main(String[] args) {
        String s = "aaabbbbcccaab";
        List<String> result = new ArrayList<String>();
        for (int i = 0, j = i + 1, l = s.length(); i < l; i = j) {
            char temp = s.charAt(i);
            for (; j < l; j++) {
                if (temp != s.charAt(j)) {
                    result.add(s.substring(i, j));
                    break;
                }
            }
            // 边界
            if (j == l) {
                result.add(s.substring(i, j));
            }
        }
        // 直接插入排序
        int len = result.size();
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && result.get(j - 1).length() < result.get(j).length(); j--) {
                String t = result.get(j);
                result.set(j, result.get(j - 1));
                result.set(j - 1, t);
            }
        }
        System.out.println(result);
    }
}
