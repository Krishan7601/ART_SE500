package edu.erau.se500.art;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.tools.JavaFileObject;
/*
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.api.JavacTool;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;	
*/
public class JavaParser_OLD<D, R> {

	static void collectFiles(File projectDirectory){
		//for each file found in directory
			//loadFile(thisFile);
	}
	
	static void parseFile(File javaFile) {
		
		
	}
}
/*
	// you can choose .java whatever you like to test
	 private static final String path = "/Users/Sola/git/art/ART/src/edu/erau/se500/art/SelectView.java";
			 //"/Users/Sola/Documents/workspace/语法编辑器/src/语法编辑器/User.java";

	    private JavacFileManager fileManager;
	    private JavacTool javacTool;

	    public JavaParser() {
	        Context context = new Context();
	        fileManager = new JavacFileManager(context, true, Charset.defaultCharset());
	        javacTool = new JavacTool();
	    }

	    public void JavaParserFiles() {
	        Iterable<? extends JavaFileObject> files = fileManager.getJavaFileObjects(path);
	        JavacTask compilationTask = javacTool.getTask(null, fileManager, null, null, null, files);
	        JavacTask javacTask = (JavacTask) compilationTask;
	        try {
	            Iterable<? extends CompilationUnitTree> result = javacTask.parse();
	            for (CompilationUnitTree tree : result) {
	                tree.accept((TreeVisitor<R, D>) new SourceVisitor(), null);

	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    static class SourceVisitor extends TreeScanner<Void, Void> {

	        private String currentPackageName = null;

	        @Override
	        public Void visitCompilationUnit(CompilationUnitTree node, Void aVoid) {        
	            return super.visitCompilationUnit(node, aVoid);
	        }
	       
	        //==================================Obtain Infor. ================================
		// I just use print to show the result the save data part need orthers' help
	        @Override
	        public Void visitClass(ClassTree node, Void aVoid) {
	            formatPtrln("class name: %s", node.getSimpleName());
	            for (Tree member : node.getMembers()) {
	            	if (member instanceof MethodTree){
	            		MethodTree method=(MethodTree)member;
	            		formatPtrln("Method Name:%s",method.getName());
	            	}
	                if (member instanceof VariableTree) {
	                    VariableTree variable = (VariableTree) member;
	                    List<? extends AnnotationTree> annotations = (List<? extends AnnotationTree>) variable.getModifiers().getAnnotations();
	                    if (annotations.size() > 0) {
	                        formatPtrln("Attribute: %s, annotaion: %s", variable.getName(), annotations.get(0).getAnnotationType());
	                    } else {
	                        formatPtrln("Attribute: %s", variable.getName(),variable.getKind(),variable.getType());
	                    }                
	                }
	            }
	            return super.visitClass(node, aVoid);
	         }
	        @Override
	        public Void visitVariable(VariableTree node, Void aVoid) {
	            formatPtrln("Attribute name: %s, type: %s, kind: %s, package: %s", 
	                    node.getName(), node.getType(), node.getKind(), currentPackageName);
	            return null;
	        }
	    }

	    public static void formatPtrln(String format, Object... args) {
	        System.out.println(String.format(format, args));
	    }

	    public static void main(String[] args) {

	        new JavaParser().JavaParserFiles();
	    }
}
*/
