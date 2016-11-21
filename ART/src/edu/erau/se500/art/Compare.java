package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();

	static List<CompareClassResult> results = new ArrayList<CompareClassResult>();

	public static void compare(boolean doForward) {

		if (doForward) compareForwards();
		else compareBackwards();
	}

	private static void compareForwards() {		

		for (ExtractedClass umlClass : UMLClasses) {
			for (ExtractedClass javaClass : javaClasses) {

				if (umlClass.name.toLowerCase().equals(javaClass.name.toLowerCase())) {


					if (umlClass.parentClass.toLowerCase().equals(javaClass.parentClass.toLowerCase())) {

					}

					for (String umlInterfaceClass : umlClass.interfaceClasses) {
						for (String javaInterfaceClass : javaClass.interfaceClasses) {

							if (umlInterfaceClass.toLowerCase().equals(javaInterfaceClass.toLowerCase())) {

								break;
							}
						}
					}

					for (ExtractedAttribute umlAttribute : umlClass.attributes) {
						for (ExtractedAttribute javaAttribute : javaClass.attributes) {

							if (umlAttribute.name.toLowerCase().equals(javaAttribute.name.toLowerCase()) && 
									umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {


								if (umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {

								}

								if (umlAttribute.accessModifier.equals(javaAttribute.accessModifier)) {

								}

								if ((umlAttribute.isFinal == javaAttribute.isFinal) && 
										(umlAttribute.isStatic == javaAttribute.isStatic)) {

								}
								break;
							}
						}
					}

					for (ExtractedMethod umlMethod : umlClass.methods) {

						for (ExtractedMethod javaMethod : javaClass.methods) {

							if (umlMethod.name.toLowerCase().equals(javaMethod.name.toLowerCase()) && 
									umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {


								if (umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {

								}

								if (umlMethod.accessModifier.equals(javaMethod.accessModifier)) {

								}

								if ((umlMethod.isFinal == javaMethod.isFinal) && 
										(umlMethod.isStatic == javaMethod.isStatic) && 
										(umlMethod.isAbstract == javaMethod.isAbstract)) {

								}

								for (String umlParameter : umlMethod.parameters) {
									for (String javaParameter : javaMethod.parameters) {

										if (umlParameter.toLowerCase().equals(javaParameter.toLowerCase())) {

											break;
										}
									}
								}
								break;
							}
						}
					}
					break;
				}
			}
		}
	}

	private static void compareBackwards() {

	}
}

