package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

/**
 * constructs object to store all data being extracted from a class found in source 
 * classes or compare classes
 */
public class ExtractedClass extends ExtractedAttribute {
	
	/**
	 * stores name of class's parent class
	 */
	String parentClass;
	
	/**
	 * stores boolean, true if class is abstract
	 */
	boolean isAbstract;

	/**
	 * list of names of interface classes that this class implements
	 */
	List<String> interfaceClasses = new ArrayList<String>();

	/**
	 * list of ExtractedAttribute objects for each attribute found in this class
	 */
	List<ExtractedAttribute> attributes = new ArrayList<ExtractedAttribute>();
	
	/**
	 * list of ExtractedMethod objects for each method found in this class
	 */
	List<ExtractedMethod> methods = new ArrayList<ExtractedMethod>();
	
	/** Constructor
	 * @param name - name of class
	 */
	ExtractedClass(String name) {
		super(name, null);
		parentClass = null;
		isAbstract = false;
		interfaceClasses = new ArrayList<String>();
		attributes = new ArrayList<ExtractedAttribute>();
		methods = new ArrayList<ExtractedMethod>();
	}
}