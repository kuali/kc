/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kuali.kra.logging.BufferedLogger;

/**
 * This class provides a method of comparing <code>String</code>s that results is a more
 * natural ordering than the default ordering outlined by the Java Specification.  This 
 * method takes into account the numeric ordering of both initial and internal strings of 
 * digits.  For instance, using the list {"10", "5", "20"} as an example, Java's default 
 * comparison would sort it as {"10", "20", "5"} whereas this comparator will sort it as 
 * {"5", "10", "20"}.  As a second example, consider the list {"file200.txt", 
 * "file1.txt", "file80.txt"}.  The default Java behavior would be to sort the list as 
 * {"file1.txt", "file200.txt", "file80.txt"} while this comparator will sort the list as 
 * {"file1.txt", "file80.txt", "file200.txt"}.<br />
 * <br />
 * Some important things to note:<UL>
 * <LI>This comparator makes no allowances for negative numbers, the '-' will be interpreted
 * as a non-numeric character</LI>
 * <LI>The same is true for the '.' and ',' characters.  Decimals and number groupings are 
 * not supported.</LI>
 * <LI>This comparator uses a <code>long</code> to process each numeric <code>String</code>
 * segment.  As such, the maximum number portion processable by this comparator is 2^63 - 1
 * or 9,223,372,036,854,775,807 (without the commas, of course).</LI>
 * <LI>This comparator is mostly case-insensitive.  The only time case is considered is in
 * the case where two strings are identical in every respect except case.  In that situation
 * the standard Java ordering applies.</LI>
 * <LI>This comparator treats <code>null</code> as the lowest possible <code>String</code> 
 * followed by the empty string (<code>""</code>) followed by all other <code>String</code>s.
 * </UL>
 */
public class NaturalStringComparator implements Serializable, Comparator<String> {
    
    private static final long serialVersionUID = -8005478235094346386L;
    
    private static final NaturalStringComparator theInstance = new NaturalStringComparator();
    public static NaturalStringComparator getInstance() {
        return theInstance;
    }
    
	/**
	 * Compares its two arguments for order. Returns a negative integer, zero, 
	 * or a positive integer as the first argument is less than, equal to, or 
	 * greater than the second.<br/>
	 * <br/>
	 * Uses the more natural ordering of <code>String</code>s defined by this class.
	 * @param string1 the first <code>String</code> to be compared
	 * @param string2 the second <code>String</code> to be compared
	 * @return a negative integer, zero, or a positive integer as the first argument 
	 * is less than, equal to, or greater than the second 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare (String string1, String string2) {
	    BufferedLogger.debug("Comparing ", string1, " to ", string2);
		// compare the references and check for nulls
		if (string1 == string2) return 0;
		if (string1 == null) return -1;
		if (string2 == null) return 1;
		
		// compare the contents and check for empty strings
		if (string1.equalsIgnoreCase(string2)) return string1.compareTo(string2); // if identical otherwise, take case into account
		if (string1.equals("")) return -1;
		if (string2.equals("")) return 1;
		
		// check if one string begins with a digit and the other doesn't
		// if so, the one that begins with a digit is next lowest order
		char c1 = string1.charAt(0);
		char c2 = string2.charAt(0);
		if (Character.isDigit(c1) && !Character.isDigit(c2)) return -1;
		if (Character.isDigit(c2) && !Character.isDigit(c1)) return 1;
		
		// begin the heavy lifting
		String[] chunks1 = splitOnNumerics(string1);
		String[] chunks2 = splitOnNumerics(string2);
		long number1, number2;
		int comparison;
		int retval = chunks1.length - chunks2.length; // shorter is lesser is all else is equal
		for (int i = 0; i < Math.min(chunks1.length, chunks2.length); i++) {
			try {
				// try testing the chunks as numbers first and failover to non-numeric comparison
				number1 = Long.parseLong(chunks1[i]);
				number2 = Long.parseLong(chunks2[i]);
				if (number1 < number2) {
					retval = -1;
					break;
				}
				if (number1 > number2) {
					retval = 1;
					break;
				}
				
				// if still equal, compare as strings to check for the case 
				// of leading zeros, i.e. '1' versus '001'
				comparison = chunks1[i].compareTo(chunks2[i]);
				if (comparison != 0) {
					retval = comparison;
					break;
				}
			}
			catch (NumberFormatException e) {
				// typical case insensitive string comparison
				comparison = chunks1[i].compareToIgnoreCase(chunks2[i]);
				if (comparison != 0) {
					retval = comparison;
					break;
				}
			}
		}
		BufferedLogger.debug(string1, (retval < 0 ? " < " : (retval > 0 ? " > " : " = ")), string2);
		return retval;
	}
	
	/*
	 * Splits a string into an array of strings.  Each element contains either
	 * ALL numeric digits (0-9) or NO numeric characters.
	 */
	private String[] splitOnNumerics(String strToSplit) {
		Pattern pattern = Pattern.compile("(\\D+|\\d+)");
		Matcher matcher = pattern.matcher(strToSplit);
		ArrayList<String> matchList = new ArrayList<String>();
		while (matcher.find()) {
			matchList.add(matcher.group());
		}
		return matchList.toArray(new String[matchList.size()]);
	}
}