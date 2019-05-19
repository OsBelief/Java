package com.java.yaml;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.ho.yaml.Yaml;
import org.ho.yaml.YamlDecoder;
import org.ho.yaml.YamlEncoder;

/**
 * YAMl文件的结构:Structure通过空格来展示、Sequence里的项用"-"来代表、Map里的键值对用":"分隔.
 * @author yicha
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
	    
//	    LearningYAML.writeYAML(john);
	    LearningYAML.writeYAMLByStream(john);
	    
//	    Person p = LearningYAML.readYAML();
	    Person p = LearningYAML.readYAMLByStream();
	    if(p != null) {
	    	System.out.println("name:  " + p.getName());
		    System.out.println("age:  " + p.getAge());
		    System.out.println("sponse's name:  " + p.getSponse().getName());
		    System.out.println("sponse's age:  " + p.getSponse().getAge());
		    System.out.println("first child's name:  " + p.getChildren().get(0).getName());
		    System.out.println("first child's age:  " + p.getChildren().get(0).getAge());
		    System.out.println("second child's name:  " + p.getChildren().get(1).getName());
		    System.out.println("second child's age:  " + p.getChildren().get(1).getAge());
	    }
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
	/**
	 * 将数据写入到YAML文件中
	 * @param p
	 */
	public static void writeYAML(Person p) {
		File dumpFile = new File("file/John.yaml");
		try {
			Yaml.dump(p, dumpFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 以流的形式将数据写入到YAML文件中
	 * @param p
	 */
	public static void writeYAMLByStream(Person p) {
		File dumpFile = new File("file/John.yaml");
		try {
			YamlEncoder yEncoder = new YamlEncoder(new FileOutputStream(dumpFile));
			yEncoder.writeObject(p);
			yEncoder.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * 从YAML中读取数据
	 * @return
	 */
	public static Person readYAML(){
		File dumpFile = new File("file/John.yaml");
		Person p = null;
		try {
			p = Yaml.loadType(dumpFile, Person.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}
	/**
	 * 以流的形式,从YAML中读取数据
	 * @return
	 */
	public static Person readYAMLByStream(){
		File dumpFile = new File("file/John.yaml");
		Person p = null;
		try {
			YamlDecoder yDecoder = new YamlDecoder(new FileInputStream(dumpFile));
			p = (Person) yDecoder.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
		}
		return p;
	}
}
