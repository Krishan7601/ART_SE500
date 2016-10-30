package edu.erau.se500.art;

public class CompareResults {

	String className;
	boolean matchFound = false;
	
	int attributesTotal = 0;
	int attributesFound = 0;
	
	int methodsTotal = 0;
	int methodsFound = 0;
	
	CompareResults(String className) {
		this.className = className;
	}
}
