package com.java.class_object.addmethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AppClassLoader extends ClassLoader {
    private static class SingletonHolder {
        public final static AppClassLoader instance = new AppClassLoader();
    }

    private AppClassLoader() {
    }

    public static AppClassLoader getInstance() {
        return SingletonHolder.instance;
    }

    public Class<?> findClassByBytes(String className, byte[] classBytes) {
        return defineClass(className, classBytes, 0, classBytes.length);
    }

    public Object getCopyObj(Class clazz, Object old) {
        try {
            Object newInstance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = old.getClass().getDeclaredFields();
            for (Field oldField : fields) {
                String fieldName = oldField.getName();
                oldField.setAccessible(true);
                Field newField = newInstance.getClass().getDeclaredField(fieldName);
                newField.setAccessible(true);
                newField.set(newInstance, oldField.get(old));
            }
            return newInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
