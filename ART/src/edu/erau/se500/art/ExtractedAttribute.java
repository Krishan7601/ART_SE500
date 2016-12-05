package edu.erau.se500.art;

/**
 * ExtractedAttribute class constructs object that stores details of attribues extracted from source class 
 * or compare class
 */
public class ExtractedAttribute {
	/**
	 * name stores name of attribute
	 */
	String name;
	/**
	 * type stores data type of attribute
	 */
	String type;
	/**
	 * accessModifier stores access modifier of attribute
	 */
	AccessModifier accessModifier;
	/**
	 * isStatic stores boolean, true if attribute is static 
	 */
	boolean isStatic;
	/**
	 * isFinal stores boolean, true if attribute is final 
	 */
	boolean isFinal;
	
	ExtractedAttribute(String name, String type) {
		this.name = name;
		this.type = type;
		accessModifier = AccessModifier.DEFAULT;
		isStatic = false;
		isFinal = false;
	}
}
