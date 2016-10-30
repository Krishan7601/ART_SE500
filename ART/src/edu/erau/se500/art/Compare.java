package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();
	
	public static void tempPrint() {
		System.out.println("-------------------------------------------");
		System.out.println(UMLClasses);
		System.out.println("-------------------------------------------");
		System.out.println(javaClasses);
		System.out.println("-------------------------------------------");
	}
}
