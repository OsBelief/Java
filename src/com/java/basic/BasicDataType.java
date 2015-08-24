package com.java.basic;

/**
 * Java中的基本数据类型都是有符号的<br>
 * 存储无符号类型的方法:使用比要用的无符号类型更大的有符号类型,例如short来处理无符号的字节,使用 long来处理无符号整数<br>
 * 
 * @author chengzhenhua
 * 
 */
public class BasicDataType {
	private static byte[] int2byte(int i) {
		byte[] temp = new byte[4];
		// 从高位到低位
		temp[0] = (byte) (i & 0xFF000000);
		temp[1] = (byte) (i & 0x00FF0000);
		temp[2] = (byte) (i & 0x0000FF00);
		temp[3] = (byte) (i & 0x000000FF);
		return temp;
	}

	public static void main(String[] args) {
		byte[] b1 = int2byte(17);
		for (int j = 0; j < b1.length; j++) {
			System.out.println(b1[j]);
		}
	}
}
