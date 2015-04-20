package com.java.guara;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class ObjectMethods {
	public static void main(String[] args) {
		// equals避免了NullPointExeception
		boolean b1 = Objects.equal(7, 7);
		boolean b2 = Objects.equal(null, null);
//		boolean b3 = java.util.Objects.equals("a", "a");
		System.out.println(b1);
		System.out.println(b2);
//		System.out.println(b3);
		
		// hashCode
		int h1 = Objects.hashCode("");
		System.out.println(h1);
		
		// toString
		System.out.println(Objects.toStringHelper("MyObjects").add("x", 1).add("y", 2).toString());
		
		// compare/compareTo
		int r = ComparisonChain.start()
		.compare(1, 1)
		.compare(1.1, 1.0)
		.result();
		System.out.println(r);
	}
}
