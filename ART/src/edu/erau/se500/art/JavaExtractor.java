package edu.erau.se500.art;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

/**
 * Pulls the pertinent data out of a java class file and stores them in one of two ArrayLists
 *
 */
public class JavaExtractor {

	/**
	 * A reference to the current ExtractedClass object that the data is getting saved to
	 */
	private static ExtractedClass c;
	
	
	/**
	 * Boolean stating whether the java file was generated from a uml diagram, or if it is instead just source code
	 */
	static boolean fromUML = true;
	
	/**
	 * A reference to the current java file. To be used for error message popups.
	 */
	static String currentFile;

	/** Recursively iterates through all the files and subfolders of a specific folder to call the extract method for each file
	 * @param directory - The directory that is being scanned for files
	 * @throws ParseProblemException - Parser could not parse the file
	 * @throws IOException - Generic file errors
	 */
	static void collectFiles(File directory) throws ParseProblemException, IOException {
		File[] listOfFiles = directory.listFiles();

		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					extractFromFile(listOfFiles[i]);
				} else if (listOfFiles[i].isDirectory()) {
					collectFiles(listOfFiles[i]);
				}
			}
		}
	}

	/** Opens the file to be read by the parser. Generates a tree structure of data. Calls process method to extract 
	 * the class, attributes, and methods out of the file given and saves to an ArrayList, depending on if it was from UML or not.
	 * @param javaFile - the file that the data is getting extracted from
	 * @throws ParseProblemException - Parser could not parse the file
	 * @throws IOException - Generic file errors
	 */
	static void extractFromFile(File javaFile) throws ParseProblemException, IOException {
		String extension = "";
		System.out.println(javaFile.getAbsolutePath());

		int i = javaFile.getName().lastIndexOf('.');
		if (i > 0) {
			extension = javaFile.getName().substring(i+1);
		}
		if (!extension.equalsIgnoreCase("java")) return; //skip files that are not java
		
		currentFile = javaFile.toString(); //used in error message when exception

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

	/** Recursive process to extract data from each node of the data tree. saves the data to an ExtractedClass object
	 * @param node - the current node being processed
	 */
	private static void processNode(Node node) {
		if (node instanceof FieldDeclaration) {
			FieldDeclaration fd = (FieldDeclaration)node;
			NodeList<VariableDeclarator> variables = fd.getVariables();
			for (VariableDeclarator vd : variables) {
				System.out.println("- attribute: "+" type: "+vd.getType()+" name: "+vd.getId().getName()+""+" AccessModifer"+" isPrivate: "+fd.isPrivate()+" isProtected: "+fd.isProtected()+" isPublic: "+fd.isPublic()+" Non-Accessmodifer:"+" isAbstract: "+fd.isAbstract());
				ExtractedAttribute a = new ExtractedAttribute(vd.getId().getName(), vd.getType().toString());
				c.attributes.add(a);
				if(fd.isPublic()){
					a.accessModifier=AccessModifier.PUBLIC;
				}
				else if(fd.isPrivate()){
					a.accessModifier=AccessModifier.PRIVATE;
				}
				else if(fd.isProtected()){
					a.accessModifier=AccessModifier.PROTECTED;
				} 
				if(fd.isStatic()){
					a.isStatic=fd.isStatic();
				}
				if(fd.isFinal()){
					a.isFinal=fd.isFinal();
				}
			}
		}
		else if (node instanceof MethodDeclaration) {
			MethodDeclaration md = (MethodDeclaration)node;
			System.out.println("- method: "+" type: "+md.getType()+" name: "+md.getName()+" AccessModifer"+" isPrivate: "+md.isPrivate()+" isProtected: "+md.isProtected()+" isPublic: "+md.isPublic()+" Non-Accessmodifer:"+" isAbstract: "+md.isAbstract());
			System.out.println(md.getDeclarationAsString());
			
			//convert invalid return type to void
			String returnType;
			if (md.getType().toString().equals("invalid")) returnType = "void";
			else returnType = md.getType().toString();
			
			ExtractedMethod m = new ExtractedMethod(md.getName(), returnType);
			c.methods.add(m);
			if(md.isPublic()){
				m.accessModifier=AccessModifier.PUBLIC;
			}
			else if(md.isPrivate()){
				m.accessModifier=AccessModifier.PRIVATE;
			}
			else if(md.isProtected()){
				m.accessModifier=AccessModifier.PROTECTED;
			}
			if(md.isAbstract()){
				m.isAbstract=md.isAbstract();
			} 
			if(md.isStatic()){
				m.isStatic=md.isStatic();
			}
			if(md.isFinal()){
				m.isFinal=md.isFinal();
			}
			NodeList<Parameter> parameterList = md.getParameters();
			for (Parameter thisParameter : parameterList) {
				m.parameters.add(thisParameter.getElementType().toString()+" "+thisParameter.getName());
				System.out.println(" - PARAMETER - "+thisParameter.getElementType().toString()+" "+thisParameter.getName());
			}
		} else if (node instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration cd = (ClassOrInterfaceDeclaration) node;
			System.out.println("Class: "+cd.getName()+" AccessModifer:"+" isPrivate: "+cd.isPrivate()+" isProtected: "+cd.isProtected()+" isPublic: "+cd.isPublic()+" Non-Accessmodifer:"+" isAbstract: "+cd.isAbstract());
			
			//Access Modifiers
			if(cd.isPublic()){
				c.accessModifier=AccessModifier.PUBLIC;
			}
			else if(cd.isPrivate()){
				c.accessModifier=AccessModifier.PRIVATE;
			}
			else if(cd.isProtected()){
				c.accessModifier=AccessModifier.PROTECTED;
			}
			
			//Non-Access Modifiers
			if(cd.isAbstract()){
				c.isAbstract=cd.isAbstract();
			} 
			if(cd.isStatic()){
				c.isStatic=cd.isStatic();
			}
			if(cd.isFinal()){
				c.isFinal=cd.isFinal();
			}
			
			NodeList<ClassOrInterfaceType> parentList = cd.getExtends();
			for (ClassOrInterfaceType thisClass : parentList) {
				c.parentClass = thisClass.getName(); //SHOULD NOT OCCUR MORE THAN ONCE
				System.out.println(" - EXTENDS - "+thisClass.getName()+" --- "+c.parentClass);
			}
			
			NodeList<ClassOrInterfaceType> interfaceList = cd.getImplements();
			for (ClassOrInterfaceType thisClass : interfaceList) {
				c.interfaceClasses.add(thisClass.getName());
				System.out.println(" - IMPLEMENTS - "+thisClass.getName());
			}
		}
		//RECURSIVE
		for (Node child : node.getChildrenNodes()){
			processNode(child);
		}
	}
}