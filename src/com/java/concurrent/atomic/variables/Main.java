package com.java.concurrent.atomic.variables;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子变量<br>
 * 原子变量保证数据一致性的原理:compare-and-swap,简称CAS,比较并替换<br>
 * CAS有3个操作数:内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V和预期值A修改为B，否则什么都不做
 * 
 * @author chengzhenhua
 * 
 */
public class Main {
	public static void main(String[] args) {
		Account account = new Account(1000);
		Company company = new Company(account);
		Bank bank = new Bank(account);
		Thread t1 = new Thread(company);
		Thread t2 = new Thread(bank);
		System.out.println("初始金额: " + account.getBalance());
		t1.start();
		t2.start();
		System.out.println("并发之后的金额: " + account.getBalance());
	}
}

/**
 * 账户
 * 
 * @author chengzhenhua
 * 
 */
class Account {
	private AtomicLong balance;

	public Account(long l) {
		this.balance = new AtomicLong(l);
	}

	public void setBalance(long l) {
		this.balance.set(l);
	}

	public long getBalance() {
		return this.balance.get();
	}

	public void addBalance(long l) {
		this.balance.getAndAdd(l);
	}

	public void substructBalance(long l) {
		this.balance.getAndAdd(-l);
	}
}

/**
 * 模拟公司(增加账户金额)
 * 
 * @author chengzhenhua
 * 
 */
class Company implements Runnable {
	private Account account;

	public Company(Account a) {
		this.account = a;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			this.account.addBalance(1000);
		}
	}

}

/**
 * 模拟银行(减少账户金额)
 * 
 * @author chengzhenhua
 * 
 */
class Bank implements Runnable {
	private Account account;

	public Bank(Account a) {
		this.account = a;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			this.account.substructBalance(1000);
		}
	}
}
