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

		for (ExtractedClass javaClass : javaClasses) {
			CompareClassResult thisResult = new CompareClassResult();
			thisResult.name = javaClass.name;
			for (ExtractedClass umlClass : UMLClasses) {

				if (javaClass.name.toLowerCase().equals(umlClass.name.toLowerCase())) {

					thisResult.isMatched = true;

					thisResult.parentMatch.sourceValue = javaClass.parentClass;
					thisResult.parentMatch.compareValue = umlClass.parentClass;
					if (javaClass.parentClass.toLowerCase().equals(umlClass.parentClass.toLowerCase())) {

						thisResult.parentMatch.isMatched = true;
					}

					for (String javaInterfaceClass : javaClass.interfaceClasses) {
						boolean interfaceMatchFound = false;
						for (String umlInterfaceClass : umlClass.interfaceClasses) {

							if (javaInterfaceClass.toLowerCase().equals(umlInterfaceClass.toLowerCase())) {
								interfaceMatchFound = true;
								thisResult.matchedInterfaces.add(javaInterfaceClass);
								break;
							}
						}
						if (!interfaceMatchFound) {
							thisResult.unmatchedInterfaces.add(javaInterfaceClass);
						}
					}
				}

				for (ExtractedAttribute javaAttribute : javaClass.attributes) {
					CompareAttributeResult thisAttribute = new CompareAttributeResult();
					thisAttribute.name = javaAttribute.name;
					for (ExtractedAttribute umlAttribute : umlClass.attributes) {

						if (javaAttribute.name.toLowerCase().equals(umlAttribute.name.toLowerCase()) && 
								javaAttribute.type.toLowerCase().equals(umlAttribute.type.toLowerCase())) {

							thisAttribute.isMatched = true;

							thisAttribute.typeMatch.sourceValue = javaAttribute.type;
							thisAttribute.typeMatch.compareValue = umlAttribute.type;
							if (javaAttribute.type.toLowerCase().equals(umlAttribute.type.toLowerCase())) {

								thisAttribute.typeMatch.isMatched = true;
							}

							thisAttribute.accessMatch.sourceValue = javaAttribute.accessModifier.toString();
							thisAttribute.accessMatch.compareValue = umlAttribute.accessModifier.toString();
							if (javaAttribute.accessModifier.equals(umlAttribute.accessModifier)) {

								thisAttribute.accessMatch.isMatched = true;		
							}

							thisAttribute.finalMatch.sourceValue = booleanToString(javaAttribute.isFinal);
							thisAttribute.finalMatch.compareValue = booleanToString(umlAttribute.isFinal);
							if (javaAttribute.isFinal == umlAttribute.isFinal) {

								thisAttribute.finalMatch.isMatched = true;
							}

							thisAttribute.staticMatch.sourceValue = booleanToString(javaAttribute.isStatic);
							thisAttribute.staticMatch.compareValue = booleanToString(umlAttribute.isStatic);
							if (javaAttribute.isStatic == umlAttribute.isStatic) {

								thisAttribute.staticMatch.isMatched = true;
							}

							thisResult.attributes.add(thisAttribute);
							break;
						}
					}
				}

				for (ExtractedMethod javaMethod : javaClass.methods) {
					CompareMethodResult thisMethod = new CompareMethodResult();
					thisMethod.name = javaMethod.name;
					for (ExtractedMethod umlMethod : umlClass.methods) {

						if (javaMethod.name.toLowerCase().equals(umlMethod.name.toLowerCase()) && 
								javaMethod.type.toLowerCase().equals(umlMethod.type.toLowerCase())) {

							thisMethod.isMatched = true;

							thisMethod.returnTypeMatch.sourceValue = javaMethod.type;
							thisMethod.returnTypeMatch.compareValue = umlMethod.type;
							if (javaMethod.type.toLowerCase().equals(umlMethod.type.toLowerCase())) {

								thisMethod.returnTypeMatch.isMatched = true;
							}

							thisMethod.accessMatch.sourceValue = javaMethod.accessModifier.toString();
							thisMethod.accessMatch.compareValue = umlMethod.accessModifier.toString();
							if (javaMethod.accessModifier.equals(umlMethod.accessModifier)) {

								thisMethod.accessMatch.isMatched = true;
							}

							thisMethod.finalMatch.sourceValue = booleanToString(javaMethod.isFinal);
							thisMethod.finalMatch.compareValue = booleanToString(umlMethod.isFinal);
							if (javaMethod.isFinal == umlMethod.isFinal) {

								thisMethod.finalMatch.isMatched = true;
							}

							thisMethod.staticMatch.sourceValue = booleanToString(javaMethod.isStatic);
							thisMethod.staticMatch.compareValue = booleanToString(umlMethod.isStatic);
							if (javaMethod.isStatic == umlMethod.isStatic) {

								thisMethod.staticMatch.isMatched = true;
							}

							thisMethod.abstractMatch.sourceValue = booleanToString(javaMethod.isAbstract);
							thisMethod.abstractMatch.compareValue = booleanToString(umlMethod.isAbstract);
							if (javaMethod.isAbstract == umlMethod.isAbstract) {

								thisMethod.abstractMatch.isMatched = true;
							}

							for (String javaParameter : javaMethod.parameters) {
								boolean parameterMatchFound = false;
								for (String umlParameter : umlMethod.parameters) {

									if (javaParameter.toLowerCase().equals(umlParameter.toLowerCase())) {

										thisMethod.matchedParameters.add(javaParameter);
										break;
									}
								}
								if (!parameterMatchFound) {
									thisMethod.unmatchedParameters.add(javaParameter);
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

