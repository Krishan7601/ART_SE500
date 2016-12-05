package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;


/**
 * CompareClassResult stores the results of the comparison for each class 
 */
public class CompareClassResult {
	/**
	 * stores name of class
	 */
	String name;
	
	/**
	 * stores whether class name is found in both UML Model and Java Source Code
	 */
	boolean isMatched = false;
	
	/**
	 * MatchResult object that stores the access modifier of class found in UML and Java Source Code
	 */
	MatchResult accessMatch = new MatchResult();
	
	/**
	 * MatchResult object that stores isFinal of class found in UML and Java Source Code
	 */
	MatchResult finalMatch = new MatchResult();
	
	/**
	 * MatchResult that stores isAbstract of class found in UML and Java Source Code
	 */
	MatchResult abstractMatch = new MatchResult();
	
	/**
	 * MatchResult object and stores whether class parent is found in both UML Model and Java Source Code
	 */
	MatchResult parentMatch = new MatchResult();
	
	/**
	 * list of matched interface names associated with class
	 */
	List<String> matchedInterfaces = new ArrayList<String>();
	
	/**
	 * list of interfaces names found in source class but not in compare class
	 */
	List<String> unmatchedInterfaces = new ArrayList<String>();
	
	/**
	 * list of CompareAttributeResult objects which store whether attribute is matched, source value and compare value
	 */
	List<CompareAttributeResult> attributes = new ArrayList<CompareAttributeResult>();
	
	/**
	 * list of CompareMethodResult objects which store whether method is matched, source value and compare value
	 */
	List<CompareMethodResult> methods = new ArrayList<CompareMethodResult>();
}
