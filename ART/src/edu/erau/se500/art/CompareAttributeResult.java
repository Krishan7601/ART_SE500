package edu.erau.se500.art;

public class CompareAttributeResult {
	String name;
	boolean isMatched = false;
	MatchResult typeMatch = new MatchResult();
	MatchResult accessMatch = new MatchResult();
	MatchResult staticMatch = new MatchResult();
	MatchResult finalMatch = new MatchResult();
}
