package com.java.yaml;

import java.util.ArrayList;
import java.util.List;

/**
 * YAMl文件的结构:Structure通过空格来展示、Sequence里的项用"-"来代表、Map里的键值对用":"分隔.
 * @author yicha
 *
 */
public class LearningYAML {
	public static void main(String[] args) {
		Person sponse = new Person();
	    sponse.setName("Jane Smith");
	    sponse.setAge(25);
	    List<Person> children = new ArrayList<Person>();
	    Person c1 = new Person();
	    Person c2 = new Person();
	    c1.setName("Jimmy Smith");
	    c1.setAge(15);
	    c2.setName("Jenny Smith");
	    c2.setAge(12);
	    children.add(c1);
	    children.add(c2);
	    
	    Person john = LearningYAML.getJavaBean("John Smith", 37, sponse, children);
	}
	/**
	 * 获得JavaBean对象
	 * @param name
	 * @param age
	 * @param p
	 * @param children
	 * @return
	 */
	public static Person getJavaBean(String name, int age, Person p, List<Person> children) {
		Person john = new Person();
		john.setName(name);
		john.setAge(age);
		john.setSponse(p);
		john.setChildren(children);
		return john;
	}
}
