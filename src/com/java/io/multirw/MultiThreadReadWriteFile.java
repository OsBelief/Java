package com.java.io.multirw;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;

/**
 * 利用独占锁和共享锁的特性, 实现多线程同时读写文件
 * FileLock是进程锁, 这个示例中的实现方式有问题
 * 这个问题我觉得最终可以参考下Android中SharedPreferencesImpl的实现, 那个是线程安全的
 * TODO 怎么样解决写入的顺序问题
 */
public class MultiThreadReadWriteFile {
    public static void main(String[] args) {
        // 创建测试文件
        File testFile = new File("/Users/tianzhihen/Desktop", "MultiThreadReadWriteFile.txt");
        if (testFile.exists()) {
            boolean result = testFile.delete();
            System.out.println("测试文件创建结果:" + result);
        }
        try {
            testFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("测试文件:" + testFile.getAbsolutePath());
        // 模拟多线程写
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                String content = "test-" + finalI + "\n";
                System.out.println(Thread.currentThread().getName() + "写入: " + content);
                writeString(testFile.getAbsolutePath(), content);
            }).start();
        }
        // 模拟多线程读
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "读取: ");
                readString(testFile.getAbsolutePath());
            }).start();
        }
    }

    private static void writeString(String filePath, String content) {
        File file = new File(filePath);
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rws");
            fileChannel = randomAccessFile.getChannel();
            while (true) {
                try {
                    fileLock = fileChannel.tryLock();  // 尝试获得独占锁, 若文件在被其他线程使用, 则阻塞等待
                    if (fileLock != null) {
                        System.out.println(Thread.currentThread().getName() + ", 获得独占锁, 写入内容: " + content);
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("有其他线程获得独占锁, 当前线程休眠500毫秒");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            System.out.println("已写入的字符数=" + fileChannel.size());
            fileChannel.position(fileChannel.size());   // 将文件定位到结尾

            ByteBuffer bb = ByteBuffer.wrap(content.getBytes("UTF-8"));
            fileChannel.write(bb);
            fileChannel.force(true);

            fileLock.release();
            fileChannel.close();
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readString(String filePath) {
        File file = new File(filePath);
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rws");
            fileChannel = randomAccessFile.getChannel();
            while (true) {
                try {
                    fileLock = fileChannel.tryLock(0L, Long.MAX_VALUE, true);  // 尝试获得共享锁, 若文件在被其他线程使用, 则阻塞等待
                    if (fileLock != null) {
                        System.out.println(Thread.currentThread().getName() + ", 获得共享锁");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("有其他线程获得共享锁, 当前线程休眠500毫秒");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            BufferedReader br = new BufferedReader(Channels.newReader(fileChannel, "UTF-8"));
            br.lines().forEach((string) -> System.out.println(Thread.currentThread().getName() + ", 读取数据=" + string));

            fileLock.release();
            fileChannel.close();
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
