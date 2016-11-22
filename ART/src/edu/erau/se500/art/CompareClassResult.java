package edu.erau.se500.art;

import java.util.List;

public class CompareClassResult {
	String name;
	boolean isMatched = false;
	MatchResult accessMatch;
	MatchResult finalMatch;
	MatchResult abstractMatch;
	MatchResult parentMatch;
	List<String> matchedInterfaces;
	List<String> unmatchedInterfaces;
	
	List<CompareAttributeResult> attributes;
	List<CompareMethodResult> methods;
}
