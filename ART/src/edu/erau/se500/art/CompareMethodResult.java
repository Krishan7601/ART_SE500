package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class CompareMethodResult {
	String name;
	boolean isMatched = false;
	MatchResult returnTypeMatch = new MatchResult();
	MatchResult accessMatch = new MatchResult();
	MatchResult abstractMatch = new MatchResult();
	MatchResult staticMatch = new MatchResult();
	MatchResult finalMatch = new MatchResult();
	List<String> matchedParameters = new ArrayList<String>();
	List<String> unmatchedParameters = new ArrayList<String>();
}
