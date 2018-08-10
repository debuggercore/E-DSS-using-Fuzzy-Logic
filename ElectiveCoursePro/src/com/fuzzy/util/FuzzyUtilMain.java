package com.fuzzy.util;

import com.model.ElectivePredictObj;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class FuzzyUtilMain {

	private static ElectivePredictObj electivePredictObj;

	private static OutputValue outputvalue;

	public static void main(String[] args) throws Exception {
		double tip1 = predictElectiveFuzzy();

		if (outputvalue == null) {
			outputvalue = new OutputValue();
		}

		outputvalue.setValue1(tip1);
		
		double value = tip1;

		outputvalue.setValue(value);

		System.out.println(value);

	}

	/*private static double processJavaComputerOrganization() {
		// Load from 'FCL' file
		String fileName = electivePredictObj.getFilePath2();
		FIS fis = FIS.load(fileName, true);
		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return -1;
		}

		// Show
		fis.chart();

		// Set inputs
		/*fis.setVariable(electivePredictObj.getSubject3Name().toLowerCase(),
				new Double(electivePredictObj.getSubject3()));
		fis.setVariable(electivePredictObj.getSubject4Name().toLowerCase(),
				new Double(electivePredictObj.getSubject4()));*/

		// Evaluate
		/*
		fis.evaluate();

		double tip = fis.getVariable(
				electivePredictObj.getElectiveName().toLowerCase()).getValue();

		System.out.println("The Tip Details are");
		System.out.println(tip);

		// Show output variable's chart
		fis.getVariable(electivePredictObj.getElectiveName().toLowerCase())
				.chartDefuzzifier(true);

		// Print ruleSet
		System.out.println(fis);

		return tip;
	}*/

	private static double predictElectiveFuzzy() {
		// Load from 'FCL' file
		String fileName = electivePredictObj.getFilePath1();
		System.out.println(fileName);
		FIS fis = FIS.load(fileName, true);
		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return -1;
		}

		// Show
		fis.chart();

		// Set inputs
		fis.setVariable(electivePredictObj.getSubject1Name().toLowerCase(),
				new Double(electivePredictObj.getSubject1()));
		fis.setVariable(electivePredictObj.getSubject2Name().toLowerCase(),
				new Double(electivePredictObj.getSubject2()));

		// Evaluate
		fis.evaluate();

		double tip = fis.getVariable(
				electivePredictObj.getElectiveName().toLowerCase()).getValue();

		System.out.println("The Tip Details are");
		System.out.println(tip);

		// Show output variable's chart
		fis.getVariable(electivePredictObj.getElectiveName().toLowerCase())
				.chartDefuzzifier(true);

		// Print ruleSet
		//System.out.println(fis);
		
		// Show each rule (and degree of support)
	    /*for( Rule r : fis.getFunctionBlock(electivePredictObj.getElectiveNamePart1()).getFuzzyRuleBlock("No1").getRules() )
	      System.out.println(r);*/

		return tip;
	}

	public ElectivePredictObj getElectivePredictObj() {
		return electivePredictObj;
	}

	public static void setElectivePredictObj(
			ElectivePredictObj electivePredictObj) {
		FuzzyUtilMain.electivePredictObj = electivePredictObj;
	}

	public static OutputValue getOutputvalue() {
		return outputvalue;
	}

	public static void setOutputvalue(OutputValue outputvalue) {
		FuzzyUtilMain.outputvalue = outputvalue;
	}
}