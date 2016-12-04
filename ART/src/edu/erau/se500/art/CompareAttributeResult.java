package edu.erau.se500.art;

/**
 * CompareAttributeResult class used to store compare and sources fields associated with attributes 
 * from ExtractedClasses object
 */
public class CompareAttributeResult {
	String name;
	boolean isMatched = false;
	MatchResult typeMatch = new MatchResult();
	MatchResult accessMatch = new MatchResult();
	MatchResult staticMatch = new MatchResult();
	MatchResult finalMatch = new MatchResult();
}
