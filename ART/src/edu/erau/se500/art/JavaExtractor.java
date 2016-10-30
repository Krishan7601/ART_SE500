package edu.erau.se500.art;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class JavaExtractor {

	static ExtractedClass c;

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
			

			for (TypeDeclaration<?> td : cu.getTypes()) {
				c =new ExtractedClass(td.getName());
				
				if (fromUML) {
					Compare.UMLClasses.add(c); //save to UML ArrayList
				} else {
					Compare.javaClasses.add(c); //save to Java ArrayList
				}
				
				processNode(td);
			}
		} finally {
			in.close();
		}
	}

	private static void processNode(Node node) {
		if (node instanceof FieldDeclaration) {
			FieldDeclaration fd = (FieldDeclaration)node;
			NodeList<VariableDeclarator> variables = fd.getVariables();
			for (VariableDeclarator vd : variables) {
				System.out.println("- attribute: "+vd.getType()+" "+vd.getId().getName());
				ExtractedAttribute a = new ExtractedAttribute(vd.getId().getName(), vd.getType().toString());
				c.attributes.add(a);
			}
		}
		else if (node instanceof MethodDeclaration) {
			MethodDeclaration md = (MethodDeclaration)node;
			System.out.println("- method: "+md.getType()+" "+md.getName());
			ExtractedMethod m = new ExtractedMethod(md.getName(), md.getType().toString());
			c.methods.add(m);
		} else {
			for (Node child : node.getChildrenNodes()){
				processNode(child);
			}
		}
	}
}