package com.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java NIO
 * @author Belief
 *
 */
public class FileUtil {
	@SuppressWarnings("resource")
	public static void copyFileNIO(String oldfile, String newfile) {
		FileChannel infc = null;
		FileChannel outfc = null;
		ByteBuffer bb = ByteBuffer.allocate(1024);
		try {
			infc = new FileInputStream(oldfile).getChannel();
			outfc = new FileOutputStream(newfile).getChannel();
			int len = infc.read(bb);	//写数据到buffer,FileChannel只能处理ByteBuffer
			while(len != -1) {
				bb.flip();	//写模式--读模式
				outfc.write(bb);
				bb.clear();
				len = infc.read(bb);
			}
			infc.close();
			outfc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	}
}
