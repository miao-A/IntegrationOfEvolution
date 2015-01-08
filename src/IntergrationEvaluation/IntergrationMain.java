package IntergrationEvaluation;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

import parseUtil.ProjectFileUtil;

import org.jfree.chart.*;

import parseUtil.CreateChartServiceImpl;
public class IntergrationMain {
	
	
	public static void main(String[] args) throws IOException {
		
		String [] arrayOfProjects = {"D:/ProjectEOfHW/heritrix0.2.0","D:/ProjectEOfHW/heritrix0.4.0",
				"D:/ProjectEOfHW/heritrix0.6.0","D:/ProjectEOfHW/heritrix0.8.0",
				"D:/ProjectEOfHW/heritrix1.0.0","D:/ProjectEOfHW/heritrix1.10.0",
				"D:/ProjectEOfHW/heritrix1.12.0","D:/ProjectEOfHW/heritrix1.14.4",
				"D:/ProjectEOfHW/heritrix-3.0.0","D:/ProjectEOfHW/heritrix-3.1.0"};
		
		//String [] arrayOfProjects = {"D:/ProjectEOfHW/eclipseCode/eclipse-3.0/plugins"};	
		String pathOfProject;

		
		for (int i = 0; i < arrayOfProjects.length; i++) {
			pathOfProject = arrayOfProjects[i];
			File file = new File(pathOfProject);
			if (!file.isDirectory()) {
				System.out.println("Path of Project not EXIST:\t"+pathOfProject);
				continue;
			}
			
			System.out.println("Project:\t"+pathOfProject);
			ProjectFileUtil projectFileUtil = new ProjectFileUtil(pathOfProject);
			projectFileUtil.parser();
			projectFileUtil.getInfoOfExtensibility();
			if (i==arrayOfProjects.length-1) {
				projectFileUtil.showChart();
			}

			System.out.println("end!");
		}

	}

}
