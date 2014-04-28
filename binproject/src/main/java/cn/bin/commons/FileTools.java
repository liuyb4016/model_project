package cn.bin.commons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * operate file
 * 
 * @author liuyb
 *
 */
public class FileTools {
	
	/**
	 *  获得 properties 文件的key值
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String getProperty(String filePath,String key){
		 Properties props = new Properties();
	        try {
	         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	         props.load(in);
	         String value = props.getProperty (key);
	            System.out.println(key+value);
	            return value;
	        } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	        }
	}
	
	// 复制文件
    public static void copyFile(String source, String target) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        File sourceFile = null;
        File targetFile = null;
        try {
        	sourceFile = new File(source);
        	targetFile = new File(target);
        	File pfile = targetFile.getParentFile();
        	if(!pfile.isDirectory()){
        		pfile.mkdirs();
        	}
        	if(!targetFile.exists()){
        		targetFile.createNewFile();
        	}
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
