package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// attributes.name


public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();
	
	static List<ExtractedAttribute> UMLAttributes = new ArrayList<ExtractedAttribute>();
	static List<ExtractedAttribute> javaAttributes = new ArrayList<ExtractedAttribute>();
	
	static List<ExtractedMethod> UMLMethods = new ArrayList<ExtractedMethod>();
	static List<ExtractedMethod> javaMethods = new ArrayList<ExtractedMethod>();

	// Initial Check for Exact Matches - redundant? 
	static Set<ExtractedClass> UMLClassSet = new HashSet<ExtractedClass>(UMLClasses);
	static List<ExtractedClass> exactMatchList = new ArrayList<ExtractedClass>();

	// ExtractedClass name
	static List<String> UMLClassNames = null;
	static List<String> javaClassNames = null;
	static Set<String> UMLClassNamesSet = new HashSet<String>(UMLClassNames);
	static List<String> classNameMatchList = new ArrayList<String>();
	
	// ExtractedClass parent
	static List<ExtractedClass> UMLClassParent = null;
	static List<ExtractedClass> javaClassParent = null;
	static Set<ExtractedClass> UMLClassParentSet = new HashSet<ExtractedClass>(UMLClassParent);
	static List<ExtractedClass> classParentMatchList = new ArrayList<ExtractedClass>();

	// ExtractedClass interface
	static List<ExtractedClass> UMLClassInterface = null;
	static List<ExtractedClass> javaClassInterface = null;
	static Set<ExtractedClass> UMLClassInterfaceSet = new HashSet<ExtractedClass>(UMLClassInterface);
	static List<ExtractedClass> classInterfaceMatchList = new ArrayList<ExtractedClass>();
	
	// ExtractedClass attributes.name
	static List<String> UMLAttributeNames = null;
	static List<String> javaAttributeNames = null;
	static Set<String> UMLAttributeNamesSet = new HashSet<String>(UMLAttributeNames);
	static List<String> attributeNameMatchList = new ArrayList<String>();
	
	// ExtractedClass attributes.type
	// ExtractedClass attributes.accessModifier
	// ExtractedClass attributes.isStatic
	// ExtractedClass attributes.isFinal
	// ExtractedClass attributes.isAbstract

	// ExtractedClass methods.name
	static List<String> UMLMethodNames = null;
	static List<String> javaMethodNames = null;
	static Set<String> UMLMethodNamesSet = new HashSet<String>(UMLMethodNames);
	static List<String> methodNameMatchList = new ArrayList<String>();
	// ExtractedClass methods.parameters
	

	public static void tempPrint() {
		System.out.println("-------------------------------------------");
		System.out.println(UMLClasses);
		System.out.println("-------------------------------------------");
		System.out.println(javaClasses);
		System.out.println("-------------------------------------------");
	}

	public static void populateLists() {
		// UML Lists
		for (ExtractedClass compUMLClass : UMLClasses) {
			UMLClassNames.add(compUMLClass.name);
			UMLClassParent.add(compUMLClass.parentClass);
			UMLClassInterface.add(compUMLClass.interfaceClass);

			for (ExtractedAttribute compUMLAttribute : compUMLClass.attributes) {
				UMLAttributeNames.add(compUMLAttribute.name);
			}
			for (ExtractedMethod compUMLMethod : compUMLClass.methods) {
				javaMethodNames.add(compUMLMethod.name);
			}

		}
		
		// Java Lists
		for(ExtractedClass compJavaClass : javaClasses) {
			javaClassNames.add(compJavaClass.name);
			javaClassParent.add(compJavaClass.parentClass);
			javaClassInterface.add(compJavaClass.interfaceClass);
			
			for (ExtractedAttribute compJavaAttribute : compJavaClass.attributes) {
				javaAttributeNames.add(compJavaAttribute.name);
			}	
			for (ExtractedMethod compJavaMethod : compJavaClass.methods) {
				javaMethodNames.add(compJavaMethod.name);
			}
		}
	}
	
	public static void exactClassMatches() {
		exactMatchList.addAll(UMLClasses);
		exactMatchList.addAll(javaClasses);
		exactMatchList.retainAll(UMLClassNamesSet);
	}

	public static void compareClassNames() {
		classNameMatchList.addAll(UMLClassNames);
		classNameMatchList.addAll(javaClassNames);
		classNameMatchList.retainAll(UMLClassNamesSet);
	}
	
	public static void compareParentClasses() {
		classParentMatchList.addAll(UMLClassParent);
		classParentMatchList.addAll(javaClassParent);
		classParentMatchList.retainAll(UMLClassNamesSet);
	}	
	
	public static void compareInterfaceClasses() {
		classInterfaceMatchList.addAll(UMLClassInterface);
		classInterfaceMatchList.addAll(javaClassInterface);
		classInterfaceMatchList.retainAll(UMLClassInterfaceSet);
	}
	
	public static void compareAttributeName() {
		attributeNameMatchList.addAll(UMLAttributeNames);
		attributeNameMatchList.addAll(javaAttributeNames);
		attributeNameMatchList.retainAll(UMLAttributeNamesSet);
	}
}

