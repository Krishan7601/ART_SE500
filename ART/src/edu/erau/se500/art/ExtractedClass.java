package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

/**
 * ExtractedClass constructs object to store all data being extracted from a class found in source 
 * classes or compare classes
 */
public class ExtractedClass extends ExtractedAttribute {
	/**
	 * parentClass stores name of class's parent class
	 */
	String parentClass;
	/**
	 * isAbstract stores boolean, true if class is abstract
	 */
	boolean isAbstract;

	/**
	 * interfaceClasses is list of names of interface classes that this class implements
	 */
	List<String> interfaceClasses = new ArrayList<String>();

	/**
	 * attributes stores list of ExtractedAttribute objects for each attribute found in this class
	 */
	List<ExtractedAttribute> attributes = new ArrayList<ExtractedAttribute>();
	/**
	 * methods stores list of ExtractedMethod objects for each method found in this class
	 */
	List<ExtractedMethod> methods = new ArrayList<ExtractedMethod>();
	
	ExtractedClass(String name) {
		super(name, null);
		parentClass = null;
		isAbstract = false;
		interfaceClasses = new ArrayList<String>();
		attributes = new ArrayList<ExtractedAttribute>();
		methods = new ArrayList<ExtractedMethod>();
	}
}