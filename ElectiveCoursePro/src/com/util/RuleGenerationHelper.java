package com.util;


import java.util.LinkedHashSet;


public class RuleGenerationHelper {

    LinkedHashSet<Condition> conditions;
    String label;

    public RuleGenerationHelper() {
        conditions = new LinkedHashSet<Condition>();
    }

    void addCondition(Condition c) {
        conditions.add(c);
    }    

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RuleGenerationHelper) {        
            boolean result=conditions.containsAll(((RuleGenerationHelper) obj).conditions)&&label.equals(((RuleGenerationHelper)obj).label);
            return result;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer rule=new StringBuffer();
	boolean first=true;
	rule.append("IF ");
        for(Condition c:conditions){
	    if(!first){
		rule.append(" AND ");
	    }else {
		first=false;
	    }
            rule.append(c);
        }
        rule.append(" THEN "+label);
        return rule.toString();
    }    
}
