package Utils;
import java.io.File;


public class FileUtils {

	public static void main(String[] args){
		FileUtils.moveFiles();
	}
	
	public static void moveFiles(){
		String dir = "E:\\视频教程\\张孝祥Java高新技术";
        File file = new File(dir);
        File[] files = file.listFiles();
        
        for(int i=0; i<files.length; i++) {
            if(files[i].isDirectory()){
            	String name = files[i].getName();
            	File[] childFiles = files[i].listFiles();
            	for(int j=0;j<childFiles.length;j++){
            		File childFile = childFiles[j];
            		String oldName = childFile.getName();
            		if(oldName.endsWith(".avi")){
            			childFile.renameTo(new File(dir +"\\" + name+".avi"));
            		}
            	}
            }else if(files[i].isFile()){
//            	String name = files[i].getName();
//            	String path = files[i].getParent();
            	String path = files[i].getPath();
            	path = path.replaceFirst("希赛软件设计师视频教程之", "");
            	System.out.println(path);
            	files[i].renameTo(new File(path));
            }
        }
	}
	public static void reName(){
		
	}
}
