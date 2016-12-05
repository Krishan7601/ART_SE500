package edu.erau.se500.art;

/**
 * used for ResultsView to show details of each compared field
 */
public class MatchResult {
	
	/**
	 * state whether field is found in both UML Model and Java Source Code
	 */
	boolean isMatched;
	
	/**
	 * associated field's value from source ExtractedClass
	 */
	String sourceValue;
	
	/**
	 * associated field's value from compared ExtractedClass
	 */
	String compareValue;
}
