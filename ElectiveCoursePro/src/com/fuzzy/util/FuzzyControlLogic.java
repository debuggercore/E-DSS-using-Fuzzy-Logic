package com.fuzzy.util;

import java.util.ArrayList;

public class FuzzyControlLogic {
	
	public String buildFuzzyLogicData(String sub1, String sub2, String sub3, ArrayList<String> rules){

		String str="";
		str+= "FUNCTION_BLOCK"+sub1+"\n";
		String STR1= varinput(sub2,sub3);
		String STR2= varoutput(sub1);
		String STR3= createfuzzify1(sub2);
		String STR4= createfuzzify2(sub3);
		String STR5= defuzzify(sub1);
		String STR6= ruleblock(rules);

		str+=STR1;
		str+=STR2;
		str+=STR3;
		str+=STR4;
		str+=STR5;
		str+=STR6;
		String str1="";
		str1+="END_FUNCTION_BLOCK";
		str+=str1;
		
		return str;
	}

	public String varinput(String name1,String name2)
	{
	     String str="";
	     str+= "VAR_INPUT"+"\n"+name1+": REAL;"+"\n"+name2+": REAL;"+"\n"+"END_VAR\n";
	     return str;
	}

	public String varoutput(String name)
	{
	     String str="";
	     str+= "VAR_OUTPUT"+"\n"+name+": REAL;"+"\n"+"END_VAR\n";
	     return str;
	}


	public String createfuzzify1(String name){
		String str="";
		str+="FUZZIFY "+name+"\n";
		str+="TERM verylow := trian 0 3.0 6.0;"+"\n";
	        str+="TERM low := trian 5.0 6.0 7.0;"+"\n";
	        str+="TERM medium := trian 6.0 7.0 8.0;"+"\n";
	        str+="TERM high := trian 7.0 8.0 9.0;"+"\n";
	        str+="TERM veryhigh := trian 8.0 9.0 10.0;"+"\n";
	        str+="END_FUZZIFY\n";

		return str;
	}

	public String createfuzzify2(String name){
		String str="";
		str+="FUZZIFY "+name+"\n";
		str+="TERM verylow := trian 0 3.0 6.0;"+"\n";
	        str+="TERM low := trian 5.0 6.0 7.0;"+"\n";
	        str+="TERM medium := trian 6.0 7.0 8.0;"+"\n";
	        str+="TERM high := trian 7.0 8.0 9.0;"+"\n";
	        str+="TERM veryhigh := trian 8.0 9.0 10.0;"+"\n";
	        str+="END_FUZZIFY\n";

		return str;
	}

	public String defuzzify(String name){
		String str="";
		str+="DEFUZZIFY "+name+"\n";
		str+="TERM verylow := trian 0 3.0 6.0;"+"\n";
	        str+="TERM low := trian 5.0 6.0 7.0;"+"\n";
	        str+="TERM medium := trian 6.0 7.0 8.0;"+"\n";
	        str+="TERM high := trian 7.0 8.0 9.0;"+"\n";
	        str+="TERM veryhigh := trian 8.0 9.0 10.0;"+"\n";
	        str+="METHOD : COG;"+"\n"+"DEFAULT := 0;"+"\n";
	        str+="END_DEFUZZIFY\n";

		return str;
	}

	public String ruleblock(ArrayList<String> rules){
	        String str="";
	        str+="RULEBLOCK No1"+"\n"+"AND : MIN;"+"\n"+"ACT :MIN;"+"\n"+"ACCU : MAX;";
	        int ruleNo = 1;
	        for(String eachRule : rules){
	        	str+="RULE "+ruleNo+"\t:\t"+eachRule+";\n";
	        	ruleNo++;
	        }
	        str+="END_RULEBLOCK\n";
	        return str;
	}
}
