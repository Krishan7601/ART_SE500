package edu.erau.se500.art;

import java.util.ArrayList;

public class CompareResults {

	String className;	
	boolean matchFound = false;
	
	boolean parentClassMatchFound = false;
	
	int interfaceClassTotal = 0;
	int interfaceClassFound = 0;
	
	int attributesTotal = 0;
	int attributesFound = 0;
	
	int accessModifiersFound = 0;
	
	int nonAccessModifiersFound = 0;
	
	int methodsTotal = 0;
	int methodsFound = 0;
	
	ArrayList<ExtractedAttribute> matchedAttribute;
	
	ArrayList<ExtractedMethod> matchedMethods;
	ArrayList<ExtractedMethod> unmatchedMethods;

	ArrayList<String> methodParameters;
	ArrayList<Integer> methodParametersTotal;
	
	CompareResults(String className) {
		this.className = className;
	}
}
