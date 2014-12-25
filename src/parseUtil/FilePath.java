package parseUtil;

import java.io.File;
import java.util.ArrayList;

public class FilePath {
	private static void readFiles(File file, String suffix, ArrayList<String> filesList) {
		if (file != null) {
			if (file.isDirectory()) {
				File f[] = file.listFiles();
				if (f != null) {
					for (int i = 0; i < f.length; i++) {
						readFiles(f[i], suffix, filesList);
					}
				}
			} else if(file.getName().endsWith(suffix)){
				filesList.add(file.toString());
			}
		}
	}
	
	public static String[] getAllFiles(String projectPath, String suffix) {
		ArrayList<String> filesList = new ArrayList<String>();                                  
		File file = new File(projectPath);
		if (file.isDirectory() == true) {
			readFiles(file, suffix, filesList);
		}
		
		int size=filesList.size();  
        String[] array = (String[])filesList.toArray(new String[size]);  
		return array;
	}
}
