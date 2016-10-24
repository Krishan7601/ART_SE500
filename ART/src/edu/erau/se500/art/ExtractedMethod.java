package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class ExtractedMethod extends ExtractedAttribute {
	//TODO: Should we change the return type to something more powerful than a string?
	String returnType;
	List<String> parameters;
	
	ExtractedMethod(String name, String returnType) {
		super(name);
		this.returnType = returnType;
		parameters = new ArrayList<String>();
	}
}
