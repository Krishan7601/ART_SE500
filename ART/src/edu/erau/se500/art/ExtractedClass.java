package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class ExtractedClass extends ExtractedMethod {
	ExtractedClass parentClass;
	ExtractedClass interfaceClass;
	List<ExtractedAttribute> attributes;
	List<ExtractedMethod> methods;
	
	ExtractedClass(String name, String returnType) {
		super(name, returnType);
		parentClass = null;
		interfaceClass = null;
		attributes = new ArrayList<ExtractedAttribute>();
		methods = new ArrayList<ExtractedMethod>();
	}
}
