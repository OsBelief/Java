package com.java.guara;

import static com.google.common.base.Preconditions.*;
/**
 * 让方法调用的条件检查更简单
 * @author yicha
 *
 */
public class PreconditionsTest {
	public static void main(String[] args) {
		checkArgument(false, "出错了");
	}
}
