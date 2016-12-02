package edu.erau.se500.art;

import java.util.ArrayList;
import java.util.List;

public class Compare {

	static List<ExtractedClass> UMLClasses = new ArrayList<ExtractedClass>();
	static List<ExtractedClass> javaClasses = new ArrayList<ExtractedClass>();
	static List<CompareClassResult> results = new ArrayList<CompareClassResult>();

	public static void compare(boolean doForward) {

		if (doForward) doCompare(UMLClasses, javaClasses); //forwards
		else doCompare(javaClasses, UMLClasses); //backwards
		System.out.println("done compare");
	}

	private static void doCompare(List<ExtractedClass> sourceClasses, List<ExtractedClass> compareClasses) {		

		for (ExtractedClass sourceClass : sourceClasses) {
			CompareClassResult thisResult = new CompareClassResult();
			thisResult.name = sourceClass.name;
			for (ExtractedClass compareClass : compareClasses) {

				if (sourceClass.name.toLowerCase().equals(compareClass.name.toLowerCase())) {

					thisResult.isMatched = true;

					thisResult.accessMatch.sourceValue = sourceClass.accessModifier.toString();
					thisResult.accessMatch.compareValue = compareClass.accessModifier.toString();
					if (sourceClass.accessModifier.equals(compareClass.accessModifier)) {
						thisResult.accessMatch.isMatched = true;
					}
					
					thisResult.abstractMatch.sourceValue = Boolean.toString(sourceClass.isAbstract);
					thisResult.abstractMatch.compareValue = Boolean.toString(compareClass.isAbstract);
					if (sourceClass.isAbstract == compareClass.isAbstract) {
						thisResult.abstractMatch.isMatched = true;
					}
					
					thisResult.finalMatch.sourceValue = Boolean.toString(sourceClass.isFinal);
					thisResult.finalMatch.compareValue = Boolean.toString(compareClass.isFinal);
					if (sourceClass.isFinal == compareClass.isFinal) {
						thisResult.finalMatch.isMatched = true;
					}

					thisResult.parentMatch.sourceValue = sourceClass.parentClass;
					thisResult.parentMatch.compareValue = compareClass.parentClass;
					if (sourceClass.parentClass != null && compareClass.parentClass != null) {
						if (sourceClass.parentClass.toLowerCase().equals(compareClass.parentClass.toLowerCase())) {
							thisResult.parentMatch.isMatched = true;
						}
					} else if (sourceClass.parentClass == null && compareClass.parentClass == null) {
						thisResult.parentMatch.isMatched = true;
					}

					for (String sourceInterfaceClass : sourceClass.interfaceClasses) {
						boolean interfaceMatchFound = false;
						for (String compareInterfaceClass : compareClass.interfaceClasses) {

							if (sourceInterfaceClass.toLowerCase().equals(compareInterfaceClass.toLowerCase())) {
								interfaceMatchFound = true;
								thisResult.matchedInterfaces.add(sourceInterfaceClass);
								break;
							}
						}
						if (!interfaceMatchFound) {
							thisResult.unmatchedInterfaces.add(sourceInterfaceClass);
						}
					}

					for (ExtractedAttribute sourceAttribute : sourceClass.attributes) {
						CompareAttributeResult thisAttribute = new CompareAttributeResult();
						thisAttribute.name = sourceAttribute.name;
						for (ExtractedAttribute compareAttribute : compareClass.attributes) {

							if (sourceAttribute.name.toLowerCase().equals(compareAttribute.name.toLowerCase())) {

								thisAttribute.isMatched = true;

								thisAttribute.typeMatch.sourceValue = sourceAttribute.type;
								thisAttribute.typeMatch.compareValue = compareAttribute.type;
								if (sourceAttribute.type.toLowerCase().equals(compareAttribute.type.toLowerCase())) {
									thisAttribute.typeMatch.isMatched = true;
								}

								thisAttribute.accessMatch.sourceValue = sourceAttribute.accessModifier.toString();
								thisAttribute.accessMatch.compareValue = compareAttribute.accessModifier.toString();
								if (sourceAttribute.accessModifier.equals(compareAttribute.accessModifier)) {
									thisAttribute.accessMatch.isMatched = true;		
								}

								thisAttribute.finalMatch.sourceValue = Boolean.toString(sourceAttribute.isFinal);
								thisAttribute.finalMatch.compareValue = Boolean.toString(compareAttribute.isFinal);
								if (sourceAttribute.isFinal == compareAttribute.isFinal) {
									thisAttribute.finalMatch.isMatched = true;
								}

								thisAttribute.staticMatch.sourceValue = Boolean.toString(sourceAttribute.isStatic);
								thisAttribute.staticMatch.compareValue = Boolean.toString(compareAttribute.isStatic);
								if (sourceAttribute.isStatic == compareAttribute.isStatic) {
									thisAttribute.staticMatch.isMatched = true;
								}

								thisResult.attributes.add(thisAttribute);
								break;
							}
						}
					}

					for (ExtractedMethod sourceMethod : sourceClass.methods) {
						CompareMethodResult thisMethod = new CompareMethodResult();
						thisMethod.name = sourceMethod.name;
						for (ExtractedMethod compareMethod : compareClass.methods) {

							System.out.println("COMPARE: "+sourceMethod.name+" "+compareMethod.name);
							
							if (sourceMethod.name.toLowerCase().equals(compareMethod.name.toLowerCase())) {
								

								thisMethod.isMatched = true;

								thisMethod.returnTypeMatch.sourceValue = sourceMethod.type;
								thisMethod.returnTypeMatch.compareValue = compareMethod.type;
								if (sourceMethod.type.toLowerCase().equals(compareMethod.type.toLowerCase())) {
									thisMethod.returnTypeMatch.isMatched = true;
								}

								thisMethod.accessMatch.sourceValue = sourceMethod.accessModifier.toString();
								thisMethod.accessMatch.compareValue = compareMethod.accessModifier.toString();
								if (sourceMethod.accessModifier.equals(compareMethod.accessModifier)) {
									thisMethod.accessMatch.isMatched = true;
								}

								thisMethod.finalMatch.sourceValue = Boolean.toString(sourceMethod.isFinal);
								thisMethod.finalMatch.compareValue = Boolean.toString(compareMethod.isFinal);
								if (sourceMethod.isFinal == compareMethod.isFinal) {
									thisMethod.finalMatch.isMatched = true;
								}

								thisMethod.staticMatch.sourceValue = Boolean.toString(sourceMethod.isStatic);
								thisMethod.staticMatch.compareValue = Boolean.toString(compareMethod.isStatic);
								if (sourceMethod.isStatic == compareMethod.isStatic) {
									thisMethod.staticMatch.isMatched = true;
								}

								thisMethod.abstractMatch.sourceValue = Boolean.toString(sourceMethod.isAbstract);
								thisMethod.abstractMatch.compareValue = Boolean.toString(compareMethod.isAbstract);
								if (sourceMethod.isAbstract == compareMethod.isAbstract) {
									thisMethod.abstractMatch.isMatched = true;
								}

								for (String sourceParameter : sourceMethod.parameters) {
									boolean parameterMatchFound = false;
									for (String compareParameter : compareMethod.parameters) {

										if (sourceParameter.toLowerCase().equals(compareParameter.toLowerCase())) {
											thisMethod.matchedParameters.add(sourceParameter);
											parameterMatchFound = true;
											break;
										}
									}
									if (!parameterMatchFound) {
										thisMethod.unmatchedParameters.add(sourceParameter);
									}
								}
								thisResult.methods.add(thisMethod);
								break;
							}
						}
					}
					break;
				}
			}
			results.add(thisResult);
		}
	}
}