package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();

	static List<CompareResults> results = new ArrayList<CompareResults>();

	// parameter counts unnecessary if information stored in ExtractedClasses
	static int parameterCount;
	static int parameterMatch;

	public static void compare(boolean doForward) {
		if (doForward) compareForwards();
		else compareBackwards();
	}

	private static void compareForwards() {		

		for (ExtractedClass umlClass : UMLClasses) {
			CompareResults thisResult = new CompareResults(umlClass.name);
			results.add(thisResult);
			ExtractedClass currentClassUnmatch = umlClass;

			for (ExtractedClass javaClass : javaClasses) {

				if (umlClass.name.equals(javaClass.name)) {

					thisResult.matchFound = true;
					ExtractedClass currentClassMatch = new ExtractedClass(umlClass.name);

					if (umlClass.parentClass.equals(javaClass.parentClass)) {

						thisResult.parentClassMatchFound = true;
						currentClassMatch.parentClass = umlClass.parentClass;
						currentClassUnmatch.parentClass = null;
					}

					for (String umlInterfaceClass : umlClass.interfaceClasses) {
						thisResult.interfaceClassTotal++;
						for (String javaInterfaceClass : javaClass.interfaceClasses) {

							if (umlInterfaceClass.equals(javaInterfaceClass)) {

								thisResult.interfaceClassFound++;
								currentClassMatch.interfaceClasses.add(umlInterfaceClass);
								currentClassMatch.interfaceClasses.remove(umlInterfaceClass);
								break;
							}
						}
					}

					for (ExtractedAttribute umlAttribute : umlClass.attributes) {
						thisResult.attributesTotal++;
						for (ExtractedAttribute javaAttribute : javaClass.attributes) {


							if (umlAttribute.name.toLowerCase().equals(javaAttribute.name.toLowerCase()) && 
									umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {

								thisResult.attributesFound++;
								currentClassMatch.attributes.add(umlAttribute);
								currentClassUnmatch.attributes.remove(umlAttribute);

								if (umlAttribute.accessModifier.equals(javaAttribute.accessModifier)) {

									thisResult.accessModifiersFound++;
									currentClassMatch.attributes.get(currentClassMatch.
											attributes.size()).accessModifier = umlAttribute.accessModifier;
									// remove this access modifier from currentClassUnmatch
								}

								if ((umlAttribute.isAbstract == javaAttribute.isAbstract) && 
										(umlAttribute.isFinal == javaAttribute.isFinal) && 
										(umlAttribute.isStatic == javaAttribute.isStatic)) {

									thisResult.nonAccessModifiersFound++;									
								}
								break;
							}
						}
					}

					for (ExtractedMethod umlMethod : umlClass.methods) {
						parameterCount = 0;
						parameterMatch = 0;
						thisResult.methodsTotal++;
						for (ExtractedMethod javaMethod : javaClass.methods) {

							if (umlMethod.name.toLowerCase().equals(javaMethod.name.toLowerCase()) && 
									umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {

								thisResult.methodsFound++;
								thisResult.methodNames.add(umlMethod.name);
								currentClassMatch.methods.add(umlMethod);
								currentClassUnmatch.methods.remove(umlMethod);

								for (String umlParameter : umlMethod.parameters) {
									parameterCount++;
									for (String javaParameter : javaMethod.parameters) {

										if (umlParameter.equals(javaParameter)) {

											parameterMatch++;
											currentClassMatch.parameters.add(umlParameter);
											currentClassUnmatch.parameters.remove(umlParameter);
											break;
										}
									}
								}
								thisResult.methodParameterTotal.add(parameterCount);
								thisResult.methodParameterFound.add(parameterMatch);
								break;
							}
						}
					}
					thisResult.matchedClasses.add(currentClassMatch);
					thisResult.unmatchedClasses.add(currentClassUnmatch);
					break;
				}
			}
		}
	}

	private static void compareBackwards() {

	}
}

