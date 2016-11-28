package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class CompareClassResult {
	String name;
	boolean isMatched = false;
	MatchResult accessMatch = new MatchResult();
	MatchResult finalMatch = new MatchResult();
	MatchResult abstractMatch = new MatchResult();
	MatchResult parentMatch = new MatchResult();
	List<String> matchedInterfaces = new ArrayList<String>();
	List<String> unmatchedInterfaces = new ArrayList<String>();
	
	List<CompareAttributeResult> attributes = new ArrayList<CompareAttributeResult>();
	List<CompareMethodResult> methods = new ArrayList<CompareMethodResult>();
}
