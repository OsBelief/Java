package com.java.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Java NIO
 *
 * @author Belief
 *
 */
public class FileUtil {
	/**
	 * 按行读文件
	 * @param filepath
	 * @param charset
	 * @return
	 */
	public static List<String> readFileLine(String filepath, String charset) {
		List<String> list = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(filepath);
			isr = new InputStreamReader(fis, charset);
			br = new BufferedReader(isr);
			String s = "";
			while((s = br.readLine()) != null) {
				list.add(s);
			}
			br.close();
			isr.close();
			fis.close();
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 按行写文件
	 * @param filepath
	 * @param charset
	 * @param list
	 */
	public static void writeFileLine(String filepath, String charset, List<String> list) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(filepath);
			osw = new OutputStreamWriter(fos, charset);
			for(String str : list) {
				osw.write(str);
				osw.write("\r\n");
			}
			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 读文件
	 * @param filepath
	 * @param charset
	 * @return
	 */
	public static String readFileIO(String filepath, String charset) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		StringBuffer sb = new StringBuffer();
		char[] chardata = new char[1024];
		try {
			fis = new FileInputStream(filepath);
			isr = new InputStreamReader(fis, charset);
			int len = -1;
			while((len = isr.read(chardata)) != -1) {
				sb.append(chardata, 0, len);
			}
			isr.close();
			fis.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 写文件
	 * @param data
	 * @param filepath
	 * @param charset
	 */
	@SuppressWarnings("resource")
	public static void writeFileNIO(String data, String filepath, String charset) {
		FileChannel fc = null;
		try {
			fc = new FileOutputStream(filepath).getChannel();
			ByteBuffer bb = ByteBuffer.wrap(data.getBytes(charset));
			fc.write(bb);
			fc.force(true);
			fc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("resource")
	public static void writeFileNIO(byte[] data, String filepath, String charset) {
		FileChannel fc = null;
		try {
			fc = new FileOutputStream(filepath).getChannel();
			ByteBuffer bb = ByteBuffer.wrap(data);
			fc.write(bb);
			fc.force(true);
			fc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Java文件复制
	 *
	 * @param oldfile
	 * @param newfile
	 */
	@SuppressWarnings("resource")
	public static void copyFileNIO(String oldfile, String newfile) {
		FileChannel infc = null;
		FileChannel outfc = null;
		ByteBuffer bb = ByteBuffer.allocate(1024);
		try {
			infc = new FileInputStream(oldfile).getChannel();
			outfc = new FileOutputStream(newfile).getChannel();
			int len = infc.read(bb); // 写数据到buffer,FileChannel只能处理ByteBuffer
			while (len != -1) {
				bb.flip(); // 写模式--读模式
				outfc.write(bb);
				bb.clear();
				len = infc.read(bb);
			}
			outfc.force(true);
			infc.close();
			outfc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Java文件复制
	 *
	 * @param oldfile
	 * @param newfile
	 */
	@SuppressWarnings("resource")
	public static void copyFileNIO2(String oldfile, String newfile) {
		FileChannel infc = null;
		FileChannel outfc = null;
		try {
			infc = new FileInputStream(oldfile).getChannel();
			outfc = new FileOutputStream(newfile).getChannel();
			int position = 0;
			long count = infc.size();
			infc.transferTo(position, count, outfc);
//			outfc.transferFrom(infc, position, count);
			outfc.force(true);
			infc.close();
			outfc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * scattering read
	 *
	 * @param filepath
	 * @param charset
	 * @return
	 */
	@SuppressWarnings("resource")
	public static ByteBuffer[] scatterMessage(String filepath, String charset) {
		FileChannel fc = null;
		try {
			fc = new FileInputStream(filepath).getChannel();
			ByteBuffer header = ByteBuffer.allocate(128);
			ByteBuffer body = ByteBuffer.allocate(1024);
			ByteBuffer[] message = { header, body };
			fc.read(message); // Scattering Read
			fc.close();
			return message;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * gather write
	 *
	 * @param header
	 * @param body
	 * @param filepath
	 * @param charset
	 */
	@SuppressWarnings("resource")
	public static void gatherMessage(String header, String body,
			String filepath, String charset) {
		FileChannel fc;
		try {
			fc = new FileOutputStream(filepath).getChannel();
			ByteBuffer headerbuffer = ByteBuffer.wrap(header.getBytes(charset));
			ByteBuffer bodybuffer = ByteBuffer.wrap(body.getBytes(charset));
			ByteBuffer[] message = { headerbuffer, bodybuffer };
			fc.write(message);
			fc.force(true);
			fc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 使用Java NIO2 删除文件及目录
     * @param path
     * @throws InvalidPathException
     * @throws IOException
     */
    public static void delete(String path) throws InvalidPathException, IOException {
        Path rootPath = Paths.get(path);
        if (!Files.exists(rootPath)) {
            return;
        }
        if (Files.isDirectory(rootPath)) {
            try (Stream<Path> walk = Files.walk(rootPath)) {
                walk.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } else {
            Files.delete(rootPath);
        }
    }

    public static void main(String[] args) {

    }
}
