package edu.erau.se500.art;

/**
 * CompareAttributeResult class used to store compare and sources fields associated with attributes 
 * from ExtractedClasses object
 */
public class CompareAttributeResult {
	/**
	 * name is Name of attribute
	 */
	String name;
	/**
	 * isMatched is Boolean stating if attribute name is found in UML model and also Java Source Code
	 */
	boolean isMatched = false;
	/**
	 * type Match is MatchedResult object to store typeMatch details of method's return data type
	 */
	MatchResult typeMatch = new MatchResult();
	/**
	 * accessMatch is MatchedResult object to store accessMatch details of attribute's access modifier
	 */
	MatchResult accessMatch = new MatchResult();
	/**
	 * staticMatch is MatchedResult object to store staticMatch details if attribute is or isn't static
	 */
	MatchResult staticMatch = new MatchResult();
	/**
	 * finalMatch is MatchedResult object to store finalMatch details if attribute is or isn't final
	 */
	MatchResult finalMatch = new MatchResult();
}
