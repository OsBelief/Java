package com.java.extension;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * 一致性哈希
 * @author Belief
 * @param <T>
 */
public class ConsistentHash<T> {
	private static MessageDigest md;	//md5哈希算法
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private int numberOfReplicas;	//虚拟节点个数
	private SortedMap<Integer, T> circle = new TreeMap<Integer, T>();
	
	public ConsistentHash(int numerOfReplicas, Collection<T> nodes) {
		this.numberOfReplicas = numerOfReplicas;
		for(T node : nodes) {
			add(node);
		}
	}
	/**                                                                                     
	 * 添加节点
	 * @param node
	 */
	public void add(T node) {
		for(int i = 0; i < numberOfReplicas; ++i) {
			circle.put(this.hash(node.toString() + i), node);
		}
	}
	/**
	 * 删除节点
	 * @param node
	 */
	public void remove(T node) {
		int i = 0;
		//删除所有的虚拟节点
		while(circle.remove(this.hash(node.toString() + i)) != null) {
			++i;
		}
	}
	/**
	 * 为对象获得最近的一个顺时针节点
	 * @param key
	 * @return
	 */
	public T get(Object key) {
		if(circle.isEmpty()) {
			return null;
		}
		int hash = this.hash(key.toString());
		if(!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			//由于是circle,则没有比Object的hash大的,则循环到第一个
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		//正好命中
		return circle.get(hash);
	}
	/**
	 * 哈希算法
	 * @param node
	 * @return
	 */
	public Integer hash(String node) {
		md.reset();
		md.update(node.getBytes());
		byte[] temp = md.digest();
		//截取中间32位转换为整数
		int reslut = (temp[6] & 0xff) | ((temp[7] << 8) & 0xff00)
				| ((temp[8] << 24) >>> 8) | (temp[9] << 24); 
		return Integer.valueOf(Math.abs(reslut)); 
	}
	public int getNumberOfReplicas() {
		return numberOfReplicas;
	}
	public void setNumberOfReplicas(int numberOfReplicas) {
		this.numberOfReplicas = numberOfReplicas;
	}
}
