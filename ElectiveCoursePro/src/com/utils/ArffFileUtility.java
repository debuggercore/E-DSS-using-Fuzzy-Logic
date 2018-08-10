package com.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArffFileUtility {

	public void send(String path, String testData) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			StringBuffer sb = new StringBuffer("@RELATION ELECTIVE\n");
			sb.append("@ATTRIBUTE FIRSTSUBJECT {S,A,B,C,D,E,F,W,X}\n");
			sb.append("@ATTRIBUTE SECONDSUBJECT {S,A,B,C,D,E,F,W,X}\n");			
			sb.append("@ATTRIBUTE class 		{S,A,B,C,D,E,F,W,X}\n");
			sb.append("\n");
			sb.append("@DATA\n");
			sb.append(testData);

			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(sb.toString());

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}	

}
