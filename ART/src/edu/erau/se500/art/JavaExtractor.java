package edu.erau.se500.art;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class JavaExtractor {

	static ExtractedClass extrClass;

	static void collectFiles(File directory, boolean fromUML) throws IOException {
		File[] listOfFiles = directory.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        extractFromFile(listOfFiles[i], fromUML);
	      } else if (listOfFiles[i].isDirectory()) {
	        collectFiles(listOfFiles[i], fromUML);
	      }
	    }
	}

	static void extractFromFile(File javaFile, boolean fromUML) throws IOException {
		String extension = "";
		System.out.println(javaFile.getAbsolutePath());
		
		int i = javaFile.getName().lastIndexOf('.');
		if (i > 0) {
		    extension = javaFile.getName().substring(i+1);
		}
		if (!extension.equalsIgnoreCase("java")) return; //skip files that are not java

		FileInputStream in = new FileInputStream(javaFile);

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		TypeDeclaration<?> td = cu.getTypes().get(0);
		extrClass =new ExtractedClass(td.getName(), "returntype");

		//new ClassVisitor().visit(cu, null);
		new MethodVisitor().visit(cu, null);
		new VariableVisitor().visit(cu, null);

	}

	private static class ClassVisitor extends VoidVisitorAdapter {

		@Override
		public void visit(ClassOrInterfaceType n, Object arg) {

			//    ExtractedClass cl =new ExtractedClass(n.getName(),"returntype");
			//
			//             cl.methods.add(cl);
			super.visit(n, arg);
		}
	}
	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes. 
	 */
	private static class MethodVisitor extends VoidVisitorAdapter {

		@Override
		public void visit(MethodDeclaration n, Object arg) {
			//System.out.println("Method No."+j+" name: "+n.getName());
			super.visit(n, arg);

			ExtractedMethod m = new ExtractedMethod(n.getName(),"return");
			System.out.println(m.name);
			extrClass.methods.add(m);
		}
	}

	private static class VariableVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(VariableDeclarator n, Object arg) {
			//System.out.println("Attribute No."+i+" name: "+n.getId().getName()+", type "+n.getType());

			ExtractedAttribute m = new ExtractedAttribute(n.getId().getName(), n.getType().toStringWithoutComments());
			System.out.println(m.name);

			super.visit(n, arg);
		}
	}
}