package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

/**
 * constructs object that stores details of methods extracted from source class 
 * or compare class. Extends ExtractedAttribute to hold all same fields as ExtractedAttibute.
 */
public class ExtractedMethod extends ExtractedAttribute {
	
	/**
	 * list of names of parameters associated with this method
	 */
	List<String> parameters;
	
	/**
	 * boolean, true if attribute is abstract, false if not
	 */
	boolean isAbstract;
	
	
	/** Constructor
	 * @param name - name of the method
	 * @param type - return type of the method
	 */
	ExtractedMethod(String name, String type) {
		super(name, type);
		parameters = new ArrayList<String>();
		isAbstract = false;
	}
}
