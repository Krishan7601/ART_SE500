package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

/**
 * ExtractedMethod class constructs object that stores details of methods extracted from source class 
 * or compare class. Extends ExtractedAttribute to hold all same fields as ExtractedAttibute.
 */
public class ExtractedMethod extends ExtractedAttribute {
	/**
	 * parameters stores list of names of parameters associated with this method
	 */
	List<String> parameters;
	/**
	 * isAbstract stores boolean, true if attribute is abstract 
	 */
	boolean isAbstract;
	
	ExtractedMethod(String name, String type) {
		super(name, type);
		parameters = new ArrayList<String>();
		isAbstract = false;
	}
}
