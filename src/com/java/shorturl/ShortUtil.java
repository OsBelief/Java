package com.java.shorturl;

/**
 * 由长链接生成短链接的方法
 * 
 * @author chengzhenhua
 * 
 */
public class ShortUtil {
	private static char Base62Digit(int d) {
		if (d < 26) {
			return (char) ('a' + d);
		} else if (d < 52) {
			return (char) ('A' + d - 26);
		} else if (d < 62) {
			return (char) ('0' + d - 52);
		}
		return 0;
	}

	private static String ToBase62(int n) {
		String res = "";
		while (n != 0) {
			res = Base62Digit(n % 62) + res;
			n /= 62;
		}
		return res;
	}

	private static double RoundFunction(int input) {
		return ((1369 * input + 150889) % 714025) / 714025.0;
	}

	/**
	 * 加解密互逆的Feistel加密
	 * 
	 * @param id
	 * @return
	 */
	private static int PermuteId(int id) {
		int l1 = (id >> 16) & 65535;
		int r1 = id & 65535;
		int l2, r2;
		for (int i = 0; i < 3; i++) {
			l2 = r1;
			r2 = l1 ^ (int) (RoundFunction(r1) * 65535);
			l1 = l2;
			r1 = r2;
		}
		return ((r1 << 16) + l1);
	}
	
	private static String GenerateCode(int id) {

		return ToBase62(PermuteId(id));
	}

	public static void main(String[] args) {
		System.out.println(PermuteId(1));
		System.out.println(PermuteId(PermuteId(1)));
		System.out.println(GenerateCode(1));
	}
}
