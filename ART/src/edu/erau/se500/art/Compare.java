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

				if (umlClass.name.toLowerCase().equals(javaClass.name.toLowerCase())) {

					thisResult.matchFound = true;
					ExtractedClass currentClassMatch = new ExtractedClass(umlClass.name);

					if (umlClass.parentClass.toLowerCase().equals(javaClass.parentClass.toLowerCase())) {

						thisResult.parentClassMatchFound = true;
						currentClassMatch.parentClass = umlClass.parentClass;
						currentClassUnmatch.parentClass = null;
					}

					for (String umlInterfaceClass : umlClass.interfaceClasses) {
						thisResult.interfaceClassTotal++;
						for (String javaInterfaceClass : javaClass.interfaceClasses) {

							if (umlInterfaceClass.toLowerCase().equals(javaInterfaceClass.toLowerCase())) {

								thisResult.interfaceClassFound++;
								currentClassMatch.interfaceClasses.add(umlInterfaceClass);
								currentClassUnmatch.interfaceClasses.remove(umlInterfaceClass);
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

								if (umlAttribute.type.toLowerCase().equals(javaAttribute.type.toLowerCase())) {

									thisResult.attributeTypesFound++;
									currentClassMatch.attributes.get(currentClassMatch.
											attributes.size()).type = umlAttribute.type;
									// remove this type from currentClassUnmatch

								}

								if (umlAttribute.accessModifier.equals(javaAttribute.accessModifier)) {

									thisResult.accessModifiersFound++;
									currentClassMatch.attributes.get(currentClassMatch.
											attributes.size()).accessModifier = umlAttribute.accessModifier;
									// remove this access modifier from currentClassUnmatch
								}

								if ((umlAttribute.isFinal == javaAttribute.isFinal) && 
										(umlAttribute.isStatic == javaAttribute.isStatic)) {

									thisResult.nonAccessModifiersFound++;	
									currentClassMatch.attributes.get(currentClassMatch.
											attributes.size()).isFinal = umlAttribute.isFinal;
									currentClassMatch.attributes.get(currentClassMatch.
											attributes.size()).isStatic = umlAttribute.isStatic;
									// remove these non-access modifiers from currentClassUnmatch

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

								if (umlMethod.type.toLowerCase().equals(javaMethod.type.toLowerCase())) {

									currentClassMatch.methods.get(currentClassMatch.
											methods.size()).type = umlMethod.type;
									// remove this type from currentClassUnmatch

								}

								if (umlMethod.isAbstract == javaMethod.isAbstract) {
									currentClassMatch.methods.get(currentClassMatch.
											methods.size()).isAbstract = umlMethod.isAbstract;
								}

								for (String umlParameter : umlMethod.parameters) {
									parameterCount++;
									for (String javaParameter : javaMethod.parameters) {

										if (umlParameter.toLowerCase().equals(javaParameter.toLowerCase())) {

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

