package com.java.extension;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * 一致性hash算法
 * @author Belief
 * @param <T>
 */
public class ConsistentHash<T> {
	private static MessageDigest md;
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	private int numberOfReplicas;	//虚拟节点的个数
	private SortedMap<Integer, T> circle = new TreeMap<Integer, T>();
	public ConsistentHash(int numerOfReplicas, Collection<T> nodes) {
		this.numberOfReplicas = numerOfReplicas;
		for(T node : nodes) {
			add(node);
		}
	}
	public void add(T node) {
		for(int i = 0; i < numberOfReplicas; ++i) {
			circle.put(this.hash(node.toString() + i), node);
		}
	}
	public void remove(T node) {
		for(int i = 0; i < numberOfReplicas; ++i) {
			circle.remove(this.hash(node.toString() + i));
		}
	}
	public T get(Object key) {
		if(circle.isEmpty()) {
			return null;
		}
		int hash = this.hash(key.toString());
		if(!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}
	//哈希算法
	public int hash(String node) {
		md.reset();
		md.update(node.getBytes());
		byte[] buffer = md.digest();
		return Integer.parseInt(buffer.toString());
	}
}
