package com.java.yaml;

import java.util.List;

/**
 * JavaBean对象
 * @author yicha
 */
public class Person {
	private String name;
	private int age;
	private Person sponse;
	private List<Person> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person getSponse() {
		return sponse;
	}
	public void setSponse(Person sponse) {
		this.sponse = sponse;
	}
	public List<Person> getChildren() {
		return children;
	}
	public void setChildren(List<Person> children) {
		this.children = children;
	}
}
