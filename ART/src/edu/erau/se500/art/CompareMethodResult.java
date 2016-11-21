package edu.erau.se500.art;

import java.util.List;

public class CompareMethodResult {
	String name;
	boolean isMatched;
	MatchResult returnTypeMatch;
	MatchResult accessMatch;
	MatchResult abstractMatch;
	MatchResult staticMatch;
	MatchResult finalMatch;
	List<String> matchedParameters;
	List<String> unmatchedParameters;
}
