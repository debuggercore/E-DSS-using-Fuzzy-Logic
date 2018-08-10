package com.fuzzy.util;

import java.io.IOException;

import junit.framework.TestCase;

public class TestFuzzyData extends TestCase {

	public void testLoadFile() {
		String pathToFile = "D:\\8th Sem\\Project\\ELECTIVECOURSE_WORKSPACE\\";
		String completePath = pathToFile+"cns.arff";
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

}

