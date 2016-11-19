package edu.erau.se500.art;

public class ExtractedAttribute {
	String name;
	String type;
	AccessModifier accessModifier;
	boolean isStatic;
	boolean isFinal;
	
	ExtractedAttribute(String name, String type) {
		this.name = name;
		this.type = type;
		accessModifier = AccessModifier.NONE;
		isStatic = false;
		isFinal = false;
	}
}
