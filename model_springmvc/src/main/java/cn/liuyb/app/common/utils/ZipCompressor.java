 
package cn.liuyb.app.common.utils;  
  
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
  
public class ZipCompressor {  
    static final int BUFFER = 8192;  
  
    private ZipOutputStream out;
    public ZipCompressor(ZipOutputStream out) {
    	this.out= out;
    }

    public void compress(File file) {
        try {  
            String basedir = "";  
            compress(file, out, basedir);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    public void compress(String srcPathName) {  
        File file = new File(srcPathName);  
        if (!file.exists())  
            throw new RuntimeException(srcPathName + "不存在！");  
        try {  
            String basedir = "";  
            compress(file, out, basedir);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    private void compress(File file, ZipOutputStream out, String basedir) {  
        /* 判断是目录还是文件 */  
        if (file.isDirectory()) {  
            //System.out.println("压缩：" + basedir + file.getName());  
            this.compressDirectory(file, out, basedir);  
        } else {  
            //System.out.println("压缩：" + basedir + file.getName());  
            this.compressFile(file, out, basedir);  
        }  
    }  
  
    /** 压缩一个目录 */  
    private void compressDirectory(File dir, ZipOutputStream out, String basedir) {  
        if (!dir.exists())  
            return;  
  
        File[] files = dir.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            /* 递归 */  
            compress(files[i], out, basedir + dir.getName() + "/");  
        }  
    }  
  
    /** 压缩一个文件 */  
    private void compressFile(File file, ZipOutputStream out, String basedir) {  
        if (!file.exists()) {  
            return;  
        }  
        try {  
            BufferedInputStream bis = new BufferedInputStream(  
                    new FileInputStream(file));  
            ZipEntry entry = new ZipEntry(basedir + file.getName());  
            out.putNextEntry(entry);  
            int count;  
            byte data[] = new byte[BUFFER];  
            while ((count = bis.read(data, 0, BUFFER)) != -1) {  
                out.write(data, 0, count);  
            }  
            bis.close();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    public static void main(String[] args) throws FileNotFoundException {  
        ZipCompressor zc = new ZipCompressor(new ZipOutputStream(new FileOutputStream(new File("D:\\zip.zip"))));  
        zc.compress("D:\\test\\t\\");  
    }  
}  
