package cn.bin.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ExceptionMain {

	/**
	 * 1.读入文件内容
	 * 2.是否有新增异常，有放入map，并写入文件
	 */
	
	public static void main(String[] args) {
		Map<String,Integer> exceptionMap = new HashMap<String,Integer>();
		Map<String,String> exceptionStrMap = new HashMap<String,String>();
		String resultFilePath = "f:/test/exception/result.txt";
		//for(int i=31;i<=42;i++){
			String text = readFileText("f:/test/exception/exception.log");
			String temp = null;
			String tempkey = null;
			int idx0 = 0;
			int idx = text.indexOf("--", idx0);
			while(idx!=-1){
				temp = text.substring(idx0,idx);
				tempkey = temp.substring(16,60);
				if(exceptionMap.get(tempkey)==null || temp.length()!=exceptionMap.get(tempkey)){
					exceptionMap.put(tempkey,temp.length());
					exceptionStrMap.put(tempkey,temp);
				}
				idx0 = idx+2;
				idx = text.indexOf("--", idx0);
			}
		//}
		for(String str : exceptionStrMap.keySet()){
			writeFileText(resultFilePath, exceptionStrMap.get(str));
			writeFileText(resultFilePath, "--");
		}
		System.out.println("ok");
	}
	
	public static String readFileText(String filePath){
		String result="";
		try {
			FileReader fr = new FileReader(new File(filePath));
			BufferedReader br = new BufferedReader(fr);    //缓冲指定文件的输入
			StringWriter sw = new StringWriter();//创建FileWriter对象，用来写入字符流
			BufferedWriter bw = new BufferedWriter(sw);    //将缓冲对文件的输出
			String myreadline;    //定义一个String类型的变量,用来每次读取一行
			while (br.ready()) {
			    myreadline = br.readLine();//读取一行
			    bw.write(myreadline); //写入文件
			    bw.newLine();
			}
			bw.flush();    //刷新该流的缓冲
			result=sw.toString();
			bw.close();
			br.close();
			sw.close();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String writeFileText(String filePath,String text){
		String result="";
		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath),true);
			fos.write(text.getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
