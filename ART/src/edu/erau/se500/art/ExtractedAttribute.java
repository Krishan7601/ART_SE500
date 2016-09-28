package edu.erau.se500.art;

public class ExtractedAttribute {
	String name;
	AccessModifier accessModifier;
	boolean isStatic;
	boolean isFinal;
	boolean isAbstract;
	
	ExtractedAttribute(String name) {
		this.name = name;
		accessModifier = AccessModifier.NONE;
		isStatic = false;
		isFinal = false;
		isAbstract = false;
	}
}
