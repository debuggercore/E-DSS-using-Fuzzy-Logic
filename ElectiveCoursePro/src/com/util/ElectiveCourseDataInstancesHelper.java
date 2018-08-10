package com.util;

import java.io.BufferedReader;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class ElectiveCourseDataInstancesHelper {

	Vector<ElectiveCourseAttribute> attributes;

	public Vector<ElectiveCourseAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Vector<ElectiveCourseAttribute> attributes) {
		this.attributes = attributes;
	}

	Vector data;

	public void setData(Vector data) {
		this.data = data;
	}

	String[][] data_array;

	public String[][] getData_array() {
		return data_array;
	}

	public void setData_array(String[][] data_array) {
		this.data_array = data_array;
	}

	int num_instances;

	public int getNum_instances() {
		return num_instances;
	}

	public void setNum_instances(int num_instances) {
		this.num_instances = num_instances;
	}

	public int num_attributes;

	public int getNum_attributes() {
		return num_attributes;
	}

	public void setNum_attributes(int num_attributes) {
		this.num_attributes = num_attributes;
	}

	int class_index;
	String prob_name;

	ElectiveCourseDataInstancesHelper() {
	}

	// gets information about file
	// stores each line into a vector
	public ElectiveCourseDataInstancesHelper(BufferedReader reader) {
		num_attributes = 0;
		num_instances = 0;
		attributes = new Vector();
		data = new Vector();
		String temp;

		try {
			temp = reader.readLine();

			while (true) {
				if (!reader.ready()) {
					break;
				} else {
					{
						StringTokenizer st = new StringTokenizer(temp, " ");
						if (st.hasMoreTokens()) {
							String temp1 = st.nextToken();
							if (temp1.toUpperCase().compareTo("@RELATION") == 0) {
								prob_name = st.nextToken();

							} else if (temp1.toUpperCase().compareTo(
									"@ATTRIBUTE") == 0) {
								// attributes.add(temp);
								attributes.add(new ElectiveCourseAttribute(temp));
								num_attributes++;
							} else if (temp1.toUpperCase().compareTo("@DATA") == 0) {
								do {
									temp = reader.readLine();
									if (temp != null) {
										data.add(temp.split(",\\s*"));
										num_instances++;
									}
								} while (temp != null);
								break;
							}
						}
					}

					temp = reader.readLine();
				}
			}

			setAttributes(attributes);
			setData(data);
			setData_array(data_array);
			setNum_attributes(num_attributes);
			setNum_instances(num_instances);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// convert data into 2d string array
	public void convert() {
		ListIterator it = data.listIterator();
		data_array = new String[num_instances][num_attributes];
		for (int i = 0; i < num_instances; i++) {
			String[] attrib_vals = (String[]) it.next();
			for(String attr : attrib_vals)
				System.out.println(attr);
			for (int j = 0; j < num_attributes; j++) {
				if (attrib_vals[j].compareTo("?") == 0) {
					data_array[i][j] = null;
				} else {
					data_array[i][j] = attrib_vals[j];
				}
			}
		}

	}

	int numAttributes() {
		return num_attributes;
	}

	int numInstances() {
		return num_instances;
	}

	// retrieves a sinle instance
	String[] instance(int j) {
		return data_array[j];
	}

	/**
	 * Retrieves an attribute's name
	 * 
	 * @param j
	 *            attribute column
	 * @return
	 */
	String attribute(int j) {
		ListIterator it = attributes.listIterator();
		for (int i = 0; i < j; i++) {
			it.next();
		}
		return it.next().toString();
	}

	/**
	 * Get the attribute's name
	 * 
	 * @param j
	 *            attribute column
	 * @return
	 */
	String getAttributeName(int j) {
		return attributes.get(j).name;
	}

	/**
	 * Get range of values for an attribute
	 * 
	 * @param j
	 *            attribute column
	 * @return
	 */
	String[] getAttributeRange(int j) {
		return attributes.get(j).values;
	}

	/**
	 * retrieves the ith value in an instance where i is an attribute column
	 * 
	 * @param instance
	 * @param i
	 *            attribute column
	 * @return
	 */
	String value(String instance, int i) {
		int j = 0;
		StringTokenizer st = new StringTokenizer(instance, ",");
		while (st.hasMoreTokens() && j < i) {
			st.nextToken();
			j++;
		}
		if (st.hasMoreTokens()) {
			return st.nextToken();
		} else {
			return null;
		}

	}

	// value of an instance given attribute column
	String instance_attribute(int i, int j) {
		return data_array[i][j];
	}

	public void setClassIndex(int i) {
		class_index = i;
	}

	int getClassIndex() {
		return class_index;
	}

	// get all values of an attribute

	/**
	 * Get all values of the instances an attribute
	 * 
	 * @param i
	 *            attribute column
	 * @return
	 */
	String[] getAttributeValues(int i) {
		String[] array = new String[num_instances];
		for (int j = 0; j < num_instances; j++) {
			array[j] = data_array[j][i];
		}
		return array;
	}

	// count instances of value in array
	int count_values(String[] array, String value) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (value.compareTo(array[i]) == 0) {
				count++;
			}
		}
		return count;
	}

	// gets data type of an attribute
	// either real or discrete only
	/**
	 * Retrieves the data type of an attribute
	 * 
	 * @param i
	 *            attribute
	 * @return
	 */
	String getAttributeType(int i) {
		return attributes.get(i).type;
	}

	/**
	 * Retrieves all data types of all attributes
	 * 
	 * @return array of attribute data types (Real or Discrete)
	 */
	String[] getAllAttributeTypes() {
		String[] array = new String[num_attributes];
		for (int i = 0; i < num_attributes; i++) {
			array[i] = getAttributeType(i);
		}
		return array;
	}

	Vector getData() {
		return data;
	}

}