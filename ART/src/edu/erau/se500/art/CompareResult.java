package edu.erau.se500.art;

import java.util.ArrayList;

public class CompareResult {

	String className;	
	boolean matchFound = false;

	boolean parentClassMatchFound = false;

	ArrayList<String> matchedInterfaces;
	ArrayList<String> unmatchedInterfaces;
	int interfaceClassTotal = 0;
	int interfaceClassFound = 0;

	int attributesTotal = 0;
	int attributesFound = 0;
	int attributeTypesFound = 0;
	int attributeAccessModifiersFound = 0;
	int attributeNonAccessModifiersFound = 0;

	ArrayList<String> methodNames;
	int methodsTotal = 0;
	int methodsFound = 0;
	int methodTypesFound = 0;
	int methodAccessModifiersFound = 0;
	int methodNonAccessModifiersFound = 0;
	
	ArrayList<String> matchedParameters;
	ArrayList<String> unmatchedParameters;
	ArrayList<Integer> methodParameterTotal;
	ArrayList<Integer> methodParameterFound;
	

	CompareResult(String className) {
		this.className = className;
	}
}
