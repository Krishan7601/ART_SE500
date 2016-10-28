package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class ExtractedMethod extends ExtractedAttribute {
	//TODO: Should we change the return type to something more powerful than a string?
	List<String> parameters;
	
	ExtractedMethod(String name, String type) {
		super(name, type);
		parameters = new ArrayList<String>();
	}
}
