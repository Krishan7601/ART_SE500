package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// attributes.name


public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();

	// Total Counters
	static int classTotalCount = 0;
	static int attributeTotalCount = 0;
	static int methodTotalCount = 0;
	// Match Counter
	static int classNameMatchCount = 0;
	static int attributeNameMatchCount = 0;
	static int attributeTypeMatchCount = 0;
	static int attributeAccessModifierMatchCount = 0;
	static int attributeIsStaticMatchCount = 0;
	static int attributeIsFinalMatchCount = 0;
	static int attributeIsAbstractMatchCount = 0;
	static int methodNameMatchCount = 0;
	static int methodParameterTotalCount = 0;
	static int methodParameterMatchCount = 0;

	// Temporary Results
	static int classNameResults = 0;
	static int attributeNameResults = 0;
	static int attributeTypeResults = 0;
	static int attributeAccessModifierResults = 0;
	static int attributeIsStaticResults = 0;
	static int attributeIsFinalResults = 0;
	static int attributeIsAbstractResults = 0;
	static int methodNameResults = 0;
	static int methodParameterResults = 0;

	public static void tempPrint() {
		System.out.println("-------------------------------------------");
		System.out.println(UMLClasses);
		System.out.println("-------------------------------------------");
		System.out.println(javaClasses);
		System.out.println("-------------------------------------------");
	}

	public static void compare() {
		for (ExtractedClass umlClass : UMLClasses) {
			classTotalCount++;
			for (ExtractedClass javaClass : javaClasses) {
				if (umlClass.name.equals(javaClass.name)) {
					classNameMatchCount++;
				}
				// parent 
				// interface
				for (ExtractedAttribute umlAttribute : umlClass.attributes) {
					attributeTotalCount++;
					for (ExtractedAttribute javaAttribute : javaClass.attributes) {
						if (umlAttribute.name.equals(javaAttribute.name)) {
							attributeNameMatchCount++;
						}
						if (umlAttribute.type.equals(javaAttribute.type)) {
							attributeTypeMatchCount++;
						}
						if (umlAttribute.accessModifier.equals(javaAttribute.accessModifier)) {
							attributeAccessModifierMatchCount++;
						}
						if (umlAttribute.isStatic && javaAttribute.isStatic) {
							attributeIsStaticMatchCount++;
						}
						if (umlAttribute.isFinal && javaAttribute.isFinal) {
							attributeIsFinalMatchCount++;
						}
						if (umlAttribute.isAbstract && javaAttribute.isAbstract) {
							attributeIsAbstractMatchCount++;
						}
					}
				}
				for (ExtractedMethod umlMethod : umlClass.methods) {
					methodTotalCount++;
					for (ExtractedMethod javaMethod : javaClass.methods) {
						if (umlMethod.name.equals(javaMethod.name)) {
							methodNameMatchCount++;
						}
						for (String umlParameter : umlMethod.parameters) {
							for (String javaParameter : javaMethod.parameters) {
								methodParameterTotalCount++;
								if (umlParameter.equals(javaParameter)) {
									methodParameterMatchCount++;
								}

							}
						}
					}
				}
				tempClassResults();
				tempResultsDisplay(umlClass.name);
			}
		}
	}

	private static void tempClassResults() {
		classNameResults = classNameMatchCount/classTotalCount;
		attributeNameResults = attributeNameMatchCount/attributeTotalCount;
		attributeTypeResults = attributeTypeMatchCount/attributeTotalCount;
		attributeAccessModifierResults = attributeAccessModifierMatchCount/attributeTotalCount;
		attributeIsStaticResults = attributeIsStaticMatchCount/attributeTotalCount;
		attributeIsFinalResults = attributeIsFinalMatchCount/attributeTotalCount;
		attributeIsAbstractResults = attributeIsAbstractMatchCount/attributeTotalCount;
		methodNameResults = methodNameMatchCount/methodTotalCount;
		methodParameterResults = methodParameterMatchCount/methodParameterTotalCount;
	}
	
	private static void tempResultsDisplay(String className) {
		System.out.println("\nClass Name: "+className);
		System.out.println("\nClass Name Match: ");
		fractionDisplay(classNameMatchCount,classTotalCount,classNameResults);
		System.out.println("\nAttribute Name Match: ");
		fractionDisplay(attributeNameMatchCount,attributeTotalCount,attributeNameResults);
		System.out.println("\nAttribute Access Modifier Match: ");
		fractionDisplay(attributeAccessModifierMatchCount,attributeTotalCount,attributeAccessModifierResults);
		System.out.println("\nAttribute isStatic Match: ");
		fractionDisplay(attributeIsStaticMatchCount,attributeTotalCount,attributeIsStaticResults);
		System.out.println("\nAttribute isFinal Match: ");
		fractionDisplay(attributeIsFinalMatchCount,attributeTotalCount,attributeIsFinalResults);
		System.out.println("\nAttribute isFinal Match: ");
		fractionDisplay(attributeIsFinalMatchCount,attributeTotalCount,attributeIsFinalResults);
		System.out.println("\nAttribute isAbstrtact Match: ");
		fractionDisplay(attributeIsAbstractMatchCount,attributeTotalCount,attributeIsAbstractResults);
		System.out.println("\nMethod Name Match: ");
		fractionDisplay(methodNameMatchCount,methodTotalCount,methodNameResults);
		System.out.println("\nMethod Parameter Match: ");
		fractionDisplay(methodParameterMatchCount,methodParameterTotalCount,methodParameterResults);
	}
	
	private static void fractionDisplay(int matchCount, int totalCount, int resultDecimal) {
		System.out.println("\t"+matchCount+"/"+totalCount);
		System.out.println("\t :"+resultDecimal*100+"%");
	}
}


