package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

/**
 * CompareMethodResult class used to store compare and sources fields associated with methods 
 * from ExtractedClasses object
 */
public class CompareMethodResult {
	
	/**
	 * Name of method
	 */
	String name;
	
	/**
	 * Boolean stating if method name is found in UML model and also Java Source Code
	 */
	boolean isMatched = false;
	
	/**
	 * MatchedResult object to store typeMatch details of method's return data type
	 */
	MatchResult returnTypeMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store accessMatch details of method access modifier
	 */
	MatchResult accessMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store staticMatch details if method is or isn't abstract
	 */
	MatchResult abstractMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store staticMatch details if method is or isn't static
	 */
	MatchResult staticMatch = new MatchResult();
	
	/**
	 * MatchedResult object to store finalMatch details if method is or isn't final
	 */
	MatchResult finalMatch = new MatchResult();
	
	/**
	 * list of parameter names that are associated with the method and matched
	 */
	List<String> matchedParameters = new ArrayList<String>();
	
	/**
	 * list of parameter names that are associated with the method and are not matched
	 */
	List<String> unmatchedParameters = new ArrayList<String>();
}
