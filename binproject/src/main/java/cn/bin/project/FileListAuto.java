package cn.bin.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.bin.commons.FileTools;

public class FileListAuto {
	//文件列表所在目录
	private String fileListPath;
	//svn根目录
	private String svnRootPath;
	//web工程根目录
	private String webappRootPath;
	//class工程根目录
	private String classRootPath;
	//工程标示     需要saveFileName  一一对应
	private String[] workSpace;
	//部署代码的保存路径
	private String[] saveFileName;
	
	//工程顶级文件夹名称
	private String defalutWorkFileRoot;
	
	public FileListAuto() throws UnsupportedEncodingException {
				
		//从properties文件中得到配置的参数
		String propertiesFileName = "src/main/resources/fileList.properties";
		fileListPath = FileTools.getProperty(propertiesFileName, "fileListPath");
		
		workSpace = FileTools.getProperty(propertiesFileName, "workSpace").split("\\|\\|");
		saveFileName = FileTools.getProperty(propertiesFileName, "saveFileName").split("\\|\\|");
		
		defalutWorkFileRoot = FileTools.getProperty(propertiesFileName, "defalutWorkFileRoot");
		
		svnRootPath = defalutWorkFileRoot+workSpace[0]+FileTools.getProperty(propertiesFileName, "svnRootPath");
		webappRootPath = defalutWorkFileRoot+workSpace[0]+FileTools.getProperty(propertiesFileName, "webappRootPath");
		classRootPath = defalutWorkFileRoot+workSpace[0]+FileTools.getProperty(propertiesFileName, "classRootPath");
		
		svnRootPath = new String(svnRootPath.getBytes("ISO-8859-1"),"UTF-8");
	}
	
	public static void main(String[] args) throws IOException {
		new FileListAuto().generateFilesFromFileList();
	}
	public void generateFilesFromFileList() throws IOException{
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String outputRootPath = fileListPath.substring(0,fileListPath.lastIndexOf("/"));
		//File file = null;
		String sourcefilePath = null;
		String sourcefileDir = null;
		String targetFilePath = null;
		String targetFileDir = null;
		String className = null;
		//1读列表文件。(去除无效的)
		//2替换得实际文件路径 (java换为class)
		//3复制相应的文件到文件列表所在目录。（注意内部类的class)
		
		File fileListFile = new File(fileListPath);
		if(fileListFile.isDirectory()){
			return ;
		}
		InputStream in = new FileInputStream(fileListFile);
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		
		while ((sourcefilePath=rd.readLine()) != null) {
			if(StringUtils.isBlank(sourcefilePath)){
				continue;
			}
			String tempSvnRootPath=svnRootPath;
			String tempWebappRootPath=webappRootPath;
			String tempClassRootPath=classRootPath;
			
			String tempOutputRootPath = outputRootPath+saveFileName[0]+df.format(new Date())+"/";
			
			int i=0;
			for(String str:workSpace){
				if(sourcefilePath.indexOf(str)>0){
					tempSvnRootPath = svnRootPath.replace(workSpace[0], str);
					tempWebappRootPath = webappRootPath.replace(workSpace[0], str);
					tempClassRootPath = classRootPath.replace(workSpace[0], str);
					tempOutputRootPath = outputRootPath+saveFileName[i]+df.format(new Date())+"/";
					break;
				}
				i++;
			}
			
			sourcefilePath=sourcefilePath.substring(tempSvnRootPath.length()-1);	
			try {
				System.out.print(sourcefilePath);
				if(sourcefilePath.startsWith("/java/")){
					className= sourcefilePath.substring(sourcefilePath.lastIndexOf("/")+1,sourcefilePath.lastIndexOf("."));
					targetFileDir = tempOutputRootPath+"WEB-INF/classes/"+sourcefilePath.substring("/java/".length(),sourcefilePath.lastIndexOf("/"))+"/";
					sourcefileDir = tempClassRootPath+sourcefilePath.substring("/java/".length(),sourcefilePath.lastIndexOf("/"))+"/";
					for(String fileName:getJavaClassFileList(sourcefileDir,className)){
						System.out.print("___________________");
						FileTools.copyFile(sourcefileDir+fileName,targetFileDir+fileName);
					}
					System.out.println("successed");
				}else if(sourcefilePath.startsWith("/webapp/")){
					targetFilePath = tempOutputRootPath+sourcefilePath.substring("/webapp/".length());
					sourcefilePath = tempWebappRootPath+sourcefilePath.substring("/webapp/".length());
					FileTools.copyFile(sourcefilePath,targetFilePath);
					System.out.println("___________________successed");
				}else {
					System.out.println("=====================failed");
				}
			} catch (Exception e) {
				System.out.println("=====================failed");
				e.printStackTrace();
			}
		}
		System.out.println("......done");
		
	}
	
	private List<String> getJavaClassFileList(String sourcefileDir,String className){
		List<String> result = new ArrayList<String>();
		File filedir = new File(sourcefileDir);
		for(File f : filedir.listFiles()){
			if(f.isFile()){
				if((className+".class").startsWith(f.getName())||f.getName().startsWith(className+"$")){
					result.add(f.getName());
				}
			}
		}
		return result;
	}
}
