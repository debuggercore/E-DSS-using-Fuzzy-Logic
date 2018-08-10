package com.model;

import java.io.IOException;

import com.fuzzy.util.FuzzyData;

import junit.framework.TestCase;

public class TestFuzzyData extends TestCase {

	public void testLoadFile() {
		String pathToFile = "D:\\8th Sem\\Project\\ELECTIVECOURSE_WORKSPACE\\ElectiveCoursePro\\src\\dataset\\";
		String completePath = pathToFile+"datamining.arff";
		FuzzyData fuzzyData = new FuzzyData();
		try {
			fuzzyData.loadFile(completePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fuzzyData.fuzzifyInstances();
		System.out.println(fuzzyData.train);
		try {
			fuzzyData.generateFuzzyRules();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void testFuzzifyInstances() {
		fail("Not yet implemented");
	}

	public void testGenerateFuzzyRules() {
		fail("Not yet implemented");
	}

}
