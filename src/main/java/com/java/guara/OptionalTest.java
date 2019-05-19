package com.java.guara;

import com.google.common.base.Optional;

/**
 * Optional类的应用 T类型要么不存在(引用缺失),要么非空(引用存在) 赋予null语义,将null和空区分开
 * 
 * @author yicha
 * 
 */
public class OptionalTest {
	public static void main(String[] args) {
		// Optional<Integer> possible = Optional.of(5);
		Optional<Integer> possible = Optional.absent();
		System.out.println(possible.isPresent());
		System.out.println(possible.or(6));
	}
}
