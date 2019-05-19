
package com.java.basic;

import java.io.IOException;

/**
 * final、finally、finalize的区别<br>
 * final修饰数据是常量,必须初始化(定义时、代码块或构造方法时静态和非静态的区别),默认值不属于初始化;final修饰方法不能被子类重写;
 * final修饰类不能被继承<br>
 * finally只能用在try/catch语句中,表示发生异常后最终总是被执行,即使return、continue、
 * break也不能影响finally语句块的执行<br>
 * finalize用于垃圾回收器在决定回收对象时,会首先调用它的finallize()方法,在该方法中可以释放系统资源,如关闭输入输出流等。
 * 
 * @author Belief
 */
public final class FinalFinallyFinalize {
    private final String s; // 不初始化编译会报错

    private FinalFinallyFinalize() {
        s = "";
    }

    public static void main(String[] args) {
        testFinally();
        System.out.println("----------------------------------------");
        testReturn();
        System.out.println("----------------------------------------");
        FinalFinallyFinalize f = new FinalFinallyFinalize();
        f = null;
        System.gc();
    }

    private static void testFinally() {
        try {
            new IOException();
            System.out.println("程序抛出了异常");
        } catch (Exception e) {
            System.out.println("程序捕获了异常");
        } finally {
            System.out.println("finally类最终会执行");
        }
    }

    /**
     * return表示退出当前方法,并将值返回<br>
     * 编译器在编译return new IOException()时会分为两个步骤,new
     * IOException()在finally之前执行，return在finally之后执行,但catch语句不会被执行
     * 
     * @return
     */
    private static ReturnException testReturn() {
        try {
            return new ReturnException();
        } catch (Exception e) {
            System.out.println("程序捕获了异常");
        } finally {
            System.out.println("finally类最终会执行");
        }
        return null;
    }

    private static class ReturnException {
        public ReturnException() {
            System.out.println("执行了return语句");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行finalize");
    }
}
