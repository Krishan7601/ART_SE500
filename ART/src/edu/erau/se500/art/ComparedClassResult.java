package edu.erau.se500.art;

import java.util.List;

public class ComparedClassResult {
	String name;
	boolean isMatched;
	MatchResult accessMatch;
	MatchResult finalMatch;
	MatchResult abstractMatch;
	MatchResult parentMatch;
	List<String> matchedInterfaces;
	List<String> unmatchedInterfaces;
	
	List<CompareAttributeResult> attributes;
	List<CompareMethodResult> methods;
}
