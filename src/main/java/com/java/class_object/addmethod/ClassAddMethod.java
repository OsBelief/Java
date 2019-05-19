package com.java.class_object.addmethod;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Javassist是一个字节码读写类库
 * 利用Javassist为类添加方法和属性
 * https://www.javassist.org/tutorial/tutorial.html
 */
public class ClassAddMethod {
    public static void main(String[] args) {
        addMethodReflect();
        System.out.println("---------------分割线---------------");
        getMethodOrGetDeclaredMethod();
    }

    /**
     * 利用Javassist动态增加方法
     */
    private static void addMethodReflect() {
        try {
            String className = "com.java.class_object.addmethod.UserInfo";

            UserInfo userInfo = new UserInfo();
            userInfo.setName("Chengzhenhua");
            userInfo.setId("1");
            System.out.println("before: " + userInfo.toString());

            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get(className);

            CtMethod testMethod = CtMethod.make("public String test(){ return \"test is called! \" + toString(); }", ctClass);
            ctClass.addMethod(testMethod);

            CtMethod fooMethod = CtMethod.make("private void foo(String foo){ System.out.println(\"foo method: \"+ foo); }", ctClass);
            ctClass.addMethod(fooMethod);

            CtField testField = CtField.make("private int age;", ctClass);
            ctClass.addField(testField);

            AppClassLoader appClassLoader = AppClassLoader.getInstance();
            Class<?> clazz = appClassLoader.findClassByBytes(className, ctClass.toBytecode());
            Object newObj = appClassLoader.getCopyObj(clazz, userInfo);
            System.out.println("after: " + newObj.toString());

            String result = (String) newObj.getClass().getMethod("test").invoke(newObj);  // getDeclaredMethod
            System.out.println("增加方法test: " + result);

            Method method = newObj.getClass().getDeclaredMethod("foo", String.class); // getMethod
            method.setAccessible(true); // 不进行Java语言访问权限检查
            method.invoke(newObj, "Hello World!");

            Field ageField = newObj.getClass().getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(newObj, 28);
            System.out.println("增加属性age: " + ageField.get(newObj));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * getMethod和getDeclaredMethod方法的区别
     * getDeclaredMethod获取的是类自身声明的所有方法, 包含public、protected和private方法, 和重写父类、接口实现的方法
     * getMethod获取的是类的所有共有方法, 这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法
     * <p>
     * getField和getDeclaredField的区别, 与此一致
     */
    private static void getMethodOrGetDeclaredMethod() {
        Class<?> clazz = UserInfo.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("getMethod: method=" + method.getName());
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("getDeclaredMethod=" + declaredMethod.getName());
        }
    }
}
