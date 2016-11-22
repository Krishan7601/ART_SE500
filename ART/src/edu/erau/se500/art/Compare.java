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
			CompareClassResult thisResult = new CompareClassResult();
			thisResult.name = umlClass.name;
			for (ExtractedClass javaClass : javaClasses) {

				if (umlClass.name.toLowerCase().equals(javaClass.name.toLowerCase())) {

					thisResult.isMatched = true;

					thisResult.parentMatch.sourceValue = umlClass.parentClass;
					thisResult.parentMatch.compareValue = javaClass.parentClass;
					if (umlClass.parentClass.toLowerCase().equals(javaClass.parentClass.toLowerCase())) {

						thisResult.parentMatch.isMatched = true;
					}

					for (String umlInterfaceClass : umlClass.interfaceClasses) {
						boolean interfaceMatchFound = false;
						for (String javaInterfaceClass : javaClass.interfaceClasses) {

							if (umlInterfaceClass.toLowerCase().equals(javaInterfaceClass.toLowerCase())) {
								interfaceMatchFound = true;
								thisResult.matchedInterfaces.add(umlInterfaceClass);
								break;
							}
						}
						if (!interfaceMatchFound) {
							thisResult.unmatchedInterfaces.add(umlInterfaceClass);
						}
					}
				}

				for (ExtractedAttribute umlAttribute : umlClass.attributes) {
					CompareAttributeResult thisAttribute = new CompareAttributeResult();
					thisAttribute.name = umlAttribute.name;
					for (ExtractedAttribute javaAttribute : javaClass.attributes) {

						if (umlAttribute.name.toLowerCase().equals(javaAttribute.name.toLowerCase()) && 
								umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {

							thisAttribute.isMatched = true;

							thisAttribute.typeMatch.sourceValue = umlAttribute.type;
							thisAttribute.typeMatch.compareValue = javaAttribute.type;
							if (umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {

								thisAttribute.typeMatch.isMatched = true;
							}

							thisAttribute.accessMatch.sourceValue = umlAttribute.accessModifier.toString();
							thisAttribute.accessMatch.compareValue = javaAttribute.accessModifier.toString();
							if (umlAttribute.accessModifier.equals(javaAttribute.accessModifier)) {

								thisAttribute.accessMatch.isMatched = true;		
							}

							thisAttribute.finalMatch.sourceValue = booleanToString(umlAttribute.isFinal);
							thisAttribute.finalMatch.compareValue = booleanToString(javaAttribute.isFinal);
							if (umlAttribute.isFinal == javaAttribute.isFinal) {

								thisAttribute.finalMatch.isMatched = true;
							}

							thisAttribute.staticMatch.sourceValue = booleanToString(umlAttribute.isStatic);
							thisAttribute.staticMatch.compareValue = booleanToString(javaAttribute.isStatic);
							if (umlAttribute.isStatic == javaAttribute.isStatic) {

								thisAttribute.staticMatch.isMatched = true;
							}

							thisResult.attributes.add(thisAttribute);
							break;
						}
					}
				}

				for (ExtractedMethod umlMethod : umlClass.methods) {
					CompareMethodResult thisMethod = new CompareMethodResult();
					thisMethod.name = umlMethod.name;
					for (ExtractedMethod javaMethod : javaClass.methods) {

						if (umlMethod.name.toLowerCase().equals(javaMethod.name.toLowerCase()) && 
								umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {

							thisMethod.isMatched = true;

							thisMethod.returnTypeMatch.sourceValue = umlMethod.type;
							thisMethod.returnTypeMatch.compareValue = javaMethod.type;
							if (umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {

								thisMethod.returnTypeMatch.isMatched = true;
							}

							thisMethod.accessMatch.sourceValue = umlMethod.accessModifier.toString();
							thisMethod.accessMatch.compareValue = javaMethod.accessModifier.toString();
							if (umlMethod.accessModifier.equals(javaMethod.accessModifier)) {

								thisMethod.accessMatch.isMatched = true;
							}

							thisMethod.finalMatch.sourceValue = booleanToString(umlMethod.isFinal);
							thisMethod.finalMatch.compareValue = booleanToString(javaMethod.isFinal);
							if (umlMethod.isFinal == javaMethod.isFinal) {

								thisMethod.finalMatch.isMatched = true;
							}

							thisMethod.staticMatch.sourceValue = booleanToString(umlMethod.isStatic);
							thisMethod.staticMatch.compareValue = booleanToString(javaMethod.isStatic);
							if (umlMethod.isStatic == javaMethod.isStatic) {

								thisMethod.staticMatch.isMatched = true;
							}

							thisMethod.abstractMatch.sourceValue = booleanToString(umlMethod.isAbstract);
							thisMethod.abstractMatch.compareValue = booleanToString(javaMethod.isAbstract);
							if (umlMethod.isAbstract == javaMethod.isAbstract) {

								thisMethod.abstractMatch.isMatched = true;
							}

							for (String umlParameter : umlMethod.parameters) {
								boolean parameterMatchFound = false;
								for (String javaParameter : javaMethod.parameters) {

									if (umlParameter.toLowerCase().equals(javaParameter.toLowerCase())) {

										thisMethod.matchedParameters.add(umlParameter);
										break;
									}
								}
								if (!parameterMatchFound) {
									thisMethod.unmatchedParameters.add(umlParameter);
								}
							}
						}
						thisResult.methods.add(thisMethod);
						break;
					}
				}
			}
			results.add(thisResult);
			break;
		}
	}

	private static void compareBackwards() {

	}

	private static String booleanToString(boolean booleanValue) {
		String booleanString = null;

		if (booleanValue) {
			booleanString = "true";
		}
		else if (!booleanValue) {
			booleanString = "false";
		}

		return booleanString;
	}
}

