
package com.java.basic;

/**
 * for循环的执行顺序<br>
 * for(表达式1;表达式2;表达式3){<br>
 * ****循环体;<br>
 * }<br>
 * 表达式1->表达式2->循环体->表达式3->表达式2->循环体->表达式3->表达式2->循环体->表达式3
 * 
 * @author Belief
 */
public class ForLoop {
    private static boolean foo(char c) {
        System.out.println(c);
        return true;
    }

    public static void main(String[] args) {
        int i = 0;
        for (foo('A'); foo('B') && i < 2; foo('C')) {
            i++;
            foo('D');
        }
    }
}
