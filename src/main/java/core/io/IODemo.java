package core.io;

import java.io.File;
import java.io.RandomAccessFile;

public class IODemo {
	
	public static void randomAccessFileDemo() throws Exception{
		File file = new File("iodemo.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		//指针位置
		System.out.println(raf.getFilePointer());

	}
}
