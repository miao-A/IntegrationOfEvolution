package parseUtil;

import java.util.Map;
import java.util.Vector;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.omg.CORBA.Request;

import visitorUtil.ExtensibilityRequestor;



public class ProjectFileUtil {
	private  String pathOfProject;
	private  String pathOfLib;
	private  ASTParser parser;
	private static Vector<InfoOfExtensibility> vec = new Vector<>();

	
	
	public ProjectFileUtil(String pathOfProject){
//		this.pathOfLib = pathOfLib;
		this.pathOfProject = pathOfProject;
	}
	
	public void parser()  {
		// create a AST parser
		parser = ASTParser.newParser(AST.JLS4);
		// enable binding		
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		// set the environment for the AST parsers
		//String libPath = pathOfLib;
		String[] classpathEntries = FilePath.getAllFiles(pathOfProject, ".jar");
		String[] sourcepathEntries = {pathOfProject};
		parser.setEnvironment(classpathEntries, sourcepathEntries, null, true);
		
		// set the compiler option
		Map complierOptions= JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_7, complierOptions);
		parser.setCompilerOptions(complierOptions);
	
	}

	public void getInfoOfExtensibility() {
		// TODO Auto-generated method stub
		
		// obtain requestor
		String[] sourceFilePaths = FilePath.getAllFiles(pathOfProject, ".java");
		System.out.println("fileread over!");
		
		ExtensibilityRequestor requestor = new ExtensibilityRequestor();
		parser.createASTs(sourceFilePaths, null, new String[0], requestor, null);
//		requestor.showInfoOfExitensibily();	
		InfoOfExtensibility info = requestor.showInfoOfExitensibily();
		vec.add(info);

	}
	public void showChart() {
		String[] strings={"",""};
		CreateChartServiceImpl cr = new CreateChartServiceImpl(strings,strings,vec);

		System.out.println("\nEND.");
	}
}
