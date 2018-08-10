package com.fuzzy.util;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import wekaframework.classfiers.custom.J48Custom;


import com.util.C45;


public class FuzzyData {
	public Instances train;	
	 
	public void loadFile(String completePath) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(completePath));
		train = new Instances(reader);
		System.out.println(train);
		reader.close();
		// setting class attribute
		train.setClassIndex(train.numAttributes() - 1);
		
	}
	
	public void fuzzifyInstances(){
		/*
		 * HIGH		S	A
		 * MEDIUM	B	C	D
		 * LOW		E	F
		 */
		String VERYHIGH = " S A B ";
		String HIGH = " C B A ";
		String MEDIUM = " B C D ";
		String LOW = " E D C ";
		String VERYLOW = " F E D ";
		
		int instanceSize = train.numInstances();
		for(int i=0; i<instanceSize; i++){
			Instance instance = train.instance(i);
			//System.out.println(instance);
			int attrSize = instance.numAttributes();
			//System.out.println(attrSize);
			//System.out.println(instance.stringValue(0));
			for(int j=0; j<attrSize; j++){
				
				//Attribute attr = instance.attribute(j);
				//System.out.println("BEFORE "+attr.name()+" "+attr.value(i));
				String attrVal = instance.stringValue(j);
				if(VERYHIGH.contains(attrVal) && HIGH.contains(attrVal)){
					//System.out.println("shigh");
					
					instance.setValue(j, new Random().nextInt()%2==0 ? "VERYHIGH":"HIGH");
				}
				else if(VERYHIGH.contains(attrVal)){
					instance.setValue(j, "VERYHIGH");
				}
				else if(HIGH.contains(attrVal) && MEDIUM.contains(attrVal)){
					instance.setValue(j, new Random().nextInt()%2==0 ? "HIGH":"MEDIUM");
				}
				else if(HIGH.contains(attrVal)){
					instance.setValue(j, "HIGH");
				}
				else if(MEDIUM.contains(attrVal) && LOW.contains(attrVal)){
					instance.setValue(j, new Random().nextInt()%2==0 ? "MEDIUM":"LOW");
				}
				else if(MEDIUM.contains(attrVal)){
					instance.setValue(j, "MEDIUM");
				}
				else if(LOW.contains(attrVal) && VERYLOW.contains(attrVal)){
					instance.setValue(j, new Random().nextInt()%2==0 ? "LOW":"VERYLOW");
				}
				else if(LOW.contains(attrVal)){
					instance.setValue(j, "LOW");
				}
				else if(VERYLOW.contains(attrVal)){
					instance.setValue(j, "VERYLOW");
				}
				//System.out.println("AFTER "+attr.name()+" "+attr.value(0));
			}
			//System.out.println(instance.toString());
		}
	}
	
	public ArrayList<String> generateFuzzyRules() throws Exception{
		ArrayList<String> rules = new ArrayList<String>();
		Remove rm = new Remove();
		rm.setAttributeIndices("1"); // remove 1st attribute
		// classifier
		J48 j48 = new J48();
		
		j48.setUnpruned(true); // using an unpruned J48
		// meta-classifier
		/*FilteredClassifier fc = new FilteredClassifier();
		fc.setFilter(rm);
		fc.setClassifier(j48);
		// train and make predictions
		fc.buildClassifier(train);
		*/
		j48.buildClassifier(train);
		// display classifier
	     final javax.swing.JFrame jf = 
	       new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
	     jf.setSize(1000,1000);
	     jf.getContentPane().setLayout(new BorderLayout());
	     TreeVisualizer tv = new TreeVisualizer(null,
	         j48.graph(),
	         new PlaceNode2());
	     jf.getContentPane().add(tv, BorderLayout.CENTER);
	     jf.addWindowListener(new java.awt.event.WindowAdapter() {
	       public void windowClosing(java.awt.event.WindowEvent e) {
	         jf.dispose();
	       }
	     });
	     jf.pack();
	     jf.setVisible(true);
	     tv.fitToScreen();
		/*String x =j48.toString(); 
        String x2 =j48.toSource(x); 
		System.out.println(x2);*/
	    System.out.println(j48.graph());
		return rules;
	}
}
