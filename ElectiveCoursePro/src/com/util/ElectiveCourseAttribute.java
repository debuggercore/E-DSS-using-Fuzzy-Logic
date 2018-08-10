package com.util;



import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ElectiveCourseAttribute {

    public String name;
    String type;
    String[] values;

    public ElectiveCourseAttribute() {
    }

    public ElectiveCourseAttribute(String arffAttrib) {
        String temp = arffAttrib.toUpperCase();
        name=arffAttrib.substring(11).split("\\s+")[0];        
        if (temp.contains("NUMERIC") || temp.contains("REAL")) {
            type = "REAL";
        } else {
            type="DISCRETE";
            Pattern p = Pattern.compile("\\{(.*)\\}");
            Matcher m = p.matcher(arffAttrib);
            m.find();
            values=m.group(1).split(",\\s*");
        }
    }
}