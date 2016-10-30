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
	static int methodParameterMatchCount = 0;


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
							attributeIsFinalMatchCount++;
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
								if (umlParameter.equals(javaParameter)) {
									methodParameterMatchCount++;
								}
									
							}
						}
					}
				}
			}
		}
	}

}


