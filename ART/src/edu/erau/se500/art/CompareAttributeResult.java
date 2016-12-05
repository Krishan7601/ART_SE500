package edu.erau.se500.art;

/**
 * CompareAttributeResult class used to store compare and sources fields associated with attributes 
 * from ExtractedClasses object
 */
public class CompareAttributeResult {
	
	/**
	 * Name of attribute
	 */
	String name;
	
	/**
	 * Boolean stating if attribute name is found in UML model and also Java Source Code
	 */
	boolean isMatched = false;
	
	/**
	 * MatchedResult object to store typeMatch details of method's return data type
	 */
	MatchResult typeMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store accessMatch details of attribute's access modifier
	 */
	MatchResult accessMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store staticMatch details if attribute is or isn't static
	 */
	MatchResult staticMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store finalMatch details if attribute is or isn't final
	 */
	MatchResult finalMatch = new MatchResult();
}
