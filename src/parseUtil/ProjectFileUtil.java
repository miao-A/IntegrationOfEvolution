package parseUtil;

import java.util.Map;

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

		// obtain sourceFilePaths
		String[] sourceFilePaths  =FilePath.getAllFiles(pathOfProject, ".java");
//		System.out.println("fileread over!");
	
	}

	public void getInfoOfExtensibility() {
		// TODO Auto-generated method stub
		
		// obtain requestor
		ExtensibilityRequestor requestor = new ExtensibilityRequestor();
		parser.createASTs(FilePath.getAllFiles(pathOfProject, ".java"), null, new String[0], requestor, null);
		requestor.showInfoOfExitensibily();		
	}
}
