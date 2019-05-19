package com.java.basic;

import java.io.*;

/**
 * 在处理必须关闭的资源时, 始终要优先考虑用try-with-resources, 而不是用try-finally
 */
public class TryWithResources {
    public static void main(String[] args) {
    }

    private static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    private static String firstLineOfFile2(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dst)) {
            byte[] buffer = new byte[1024];
            int n;
            while ((n = in.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        }
    }
}
