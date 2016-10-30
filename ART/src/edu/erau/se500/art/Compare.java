package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();
	
	static List<CompareResults> results = new ArrayList<CompareResults>();

	public static void compare() {
		for (ExtractedClass umlClass : UMLClasses) {
			
			CompareResults thisResult = new CompareResults(umlClass.name);
			results.add(thisResult);
			
			for (ExtractedClass javaClass : javaClasses) {
				if (umlClass.name.equals(javaClass.name)) {
					thisResult.matchFound = true;
					
					for (ExtractedAttribute umlAttribute : umlClass.attributes) {
						thisResult.attributesTotal++;
						for (ExtractedAttribute javaAttribute : javaClass.attributes) {
							if (umlAttribute.name.toLowerCase().equals(javaAttribute.name.toLowerCase()) && 
									umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {
								thisResult.attributesFound++;
								break;
							}
						}
					}
					
					for (ExtractedAttribute umlMethod : umlClass.methods) {
						thisResult.methodsTotal++;
						for (ExtractedMethod javaMethod : javaClass.methods) {
							if (umlMethod.name.toLowerCase().equals(javaMethod.name.toLowerCase()) && 
									umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {
								thisResult.methodsFound++;
								break;
							}
						}
					}
					
					break;
				}
			}
		}
	}
}


