package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

/**
 * CompareMethodResult class used to store compare and sources fields associated with methods 
 * from ExtractedClasses object
 */
public class CompareMethodResult {
	/**
	 * name is Name of method
	 */
	String name;
	/**
	 * isMatched is Boolean stating if method name is found in UML model and also Java Source Code
	 */
	boolean isMatched = false;
	/**
	 * returnTypeMatch is MatchedResult object to store typeMatch details of method's return data type
	 */
	MatchResult returnTypeMatch = new MatchResult();
	/**
	 * accessMatch is MatchedResult object to store accessMatch details of method access modifier
	 */
	MatchResult accessMatch = new MatchResult();
	/**
	 * abstractMatch is MatchedResult object to store staticMatch details if method is or isn't abstract
	 */
	MatchResult abstractMatch = new MatchResult();
	/**
	 * staticMatch is MatchedResult object to store staticMatch details if method is or isn't static
	 */
	MatchResult staticMatch = new MatchResult();
	/**
	 * finalMatch is MatchedResult object to store finalMatch details if method is or isn't final
	 */
	MatchResult finalMatch = new MatchResult();
	/**
	 * matchedParameter is list of parameter names that are associated with the method and matched
	 */
	List<String> matchedParameters = new ArrayList<String>();
	/**
	 * unmatchedParameter is list of parameter names that are associated with the method and are not matched
	 */
	List<String> unmatchedParameters = new ArrayList<String>();
}
