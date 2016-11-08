package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class ExtractedClass extends ExtractedMethod {
	String parentClass;

	List<String> interfaceClasses = new ArrayList<String>();

	List<ExtractedAttribute> attributes = new ArrayList<ExtractedAttribute>();
	List<ExtractedMethod> methods = new ArrayList<ExtractedMethod>();
	
	ExtractedClass(String name) {
		super(name, null);
		parentClass = null;
		interfaceClasses = new ArrayList<String>();
		attributes = new ArrayList<ExtractedAttribute>();
		methods = new ArrayList<ExtractedMethod>();
	}
}