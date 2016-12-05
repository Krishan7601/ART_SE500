package edu.erau.se500.art;

/**
 * MatchResult object used for ResultsView to show details of each compared field
 */
public class MatchResult {
	/**
	 * Boolean isMatched used to state whether field is found in both UML Model and Java Source Code
	 */
	boolean isMatched;
	/**
	 * sourceValue stores the associated field's value from source ExtractedClass
	 */
	String sourceValue;
	/**
	 * compareValue stores the associated field's value from compared ExtractedClass
	 */
	String compareValue;
}
