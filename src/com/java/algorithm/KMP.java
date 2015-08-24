package com.java.algorithm;

/**
 * KMP字符串匹配算法
 * 
 * @author chengzhenhua
 * 
 */
public class KMP {
	/**
	 * 获得部分匹配值,即"前缀"和"后缀"的最长的共有元素的长度 "A"的前缀和后缀都为空集，共有元素的长度为0;<br>
	 * "AB"的前缀为[A]，后缀为[B]，共有元素的长度为0;<br>
	 * "ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0;<br>
	 * "ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0;<br>
	 * "ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1;<br>
	 * "ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB,
	 * B]，共有元素为"AB"，长度为2;<br>
	 * "ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD,
	 * ABD, BD, D]，共有元素的长度为0。<br>
	 * 
	 * @param N
	 * @return
	 */
	public static int[] getNext(String N) {
		int n = N.length();
		int[] next = new int[n];
		next[0] = -1;
		int j = 1;
		int i = -1;
		for (; j < n; j++) {
			char p = N.charAt(i + 1);
			char q = N.charAt(j);
			while (p != q && i >= 0) {
				i = next[i];
			}
			if (p == q) {
				i++; // 情况1、2
			}
			next[j] = i; // 情况3
		}
		return next;
	}

	public static void main(String[] args) {
	}
}
