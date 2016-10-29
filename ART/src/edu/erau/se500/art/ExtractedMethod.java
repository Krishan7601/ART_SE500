package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class ExtractedMethod extends ExtractedAttribute {
	List<String> parameters;
	
	ExtractedMethod(String name, String type) {
		super(name, type);
		parameters = new ArrayList<String>();
	}
}
