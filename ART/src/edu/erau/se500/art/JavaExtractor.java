package edu.erau.se500.art;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class JavaExtractor {

	static ExtractedClass extrClass;

	static void collectFiles(File projectDirectory){
		//for each file found in directory
		//loadFile(thisFile);
	}

	static void extractFromFile(File javaFile) throws IOException {

		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(javaFile);

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		NodeList<TypeDeclaration<?>> td = cu.getTypes();

		for (TypeDeclaration<?> t : td) {
			System.out.println(t.getName());
			extrClass =new ExtractedClass(t.getName(), "returntype");
		}

		ExtractedClass cl =new ExtractedClass("classname","returntype");
		cl.attributes=new ArrayList<ExtractedAttribute>();
		cl.methods=new ArrayList<ExtractedMethod>();
		//cl.attributes.add(cl);


		// visit and print the methods names
		//      new ClassVisitor().visit(cu, null);
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