/*
 * Copyright 2005-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// *******************************************************************
//	Imported from some UofArizona apps 1/12/2005.  Note that there 
// are still a number of Arizona specific comments and code in this 
// script.  It will need to be cleaned up before import into Kuali.
// *******************************************************************

//	This function uses some functions from formchek.js, so
//  if you import this function, be sure to import
//  formchek.js as well.

function isValidDate(s) {
    if (isEmpty(s))
       if (isValidDate.arguments.length == 1) return defaultEmptyOK;
       else return (isValidDate.arguments[1] == true);

	var regex = "\\d(\\d)?[-/\\\\]?\\d(\\d)?[-/\\\\]?\\d\\d\(\\d\\d)?";
	var delim = "";
	var stringlength = s.length;
	var datearray;
	var daypart,monthpart,yearpart;


	// check that the string matches the basic format
	if (!s.match(regex)) {
		return false;
	}

	// find the delimiter
	if (s.indexOf("-") != -1) {
		delim = "-";
	} else if (s.indexOf("/") != -1) {
		delim = "/";
	} else if (s.indexOf("\\") != -1) {
		delim = "\\";
	} else if (s.indexOf(".") != -1) {
		delim = ".";
	}

	// if there is no delimiter, (i.e. a date like
	// 100104 (Oct 1, 2004)) we will are going to assume that it
	// is a FRS style date.  FRS dates are either in the form MMDDYY
	// or MMDDYYYY.  So we check that s is a date given this assumption.
	if (delim == "") {
		if (stringlength == 6) {
			monthpart = s.substring(0,2);
			daypart   = s.substring(2,4);
			yearpart  = s.substring(4,7);
			if (isDate(yearpart,monthpart,daypart)) {
				return true;
			} else {
				return false;
			}
		} else if (stringlength == 8) {
			monthpart = s.substring(0,2);
			daypart   = s.substring(2,4);
			yearpart  = s.substring(4,8);

			if (isDate(yearpart,monthpart,daypart)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	} else if (delim != "") {

		// split the string into its parts
		datearray = s.split(delim);

		// check that all the parts taken together make a valid date.
		// For this function the proper order of the parts is month, day, year
		if (isDate(datearray[2],datearray[0],datearray[1])) {
			return true;
		} else {
			return false;
		}
	}
}

// This is a function to return true if the string entered is a postive
// number that can be used as currency.  Integers will work, as well as
// decimal fractions.
function isPositiveCurrency(s) {
    if (isEmpty(s))
       if (isPositiveCurrency.arguments.length == 1) return defaultEmptyOK;
       else return (isPositiveCurrency.arguments[1] == true);


	if ((isFloat(s)) && (s > 0)) {
		return true;
	} else {
		return false;
	}
}

// This function returns true is the string is in the right format for a
// FRS account number.  Right now all we're checking for is that the
// string is a 6 digit numeric; but more checking theoretically could be
// added later.
function isAccount(s) {
    if (isEmpty(s))
       if (isAccount.arguments.length == 1) return defaultEmptyOK;
       else return (isAccount.arguments[1] == true);

	var regex = "\\d\\d\\d\\d\\d\\d\\d";

	if (s.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// This function returns true is the string is in the right format for a
// FRS object code.  Right now all we're checking for is that the
// string is a 4 digit numeric; but more checking theoretically could be
// added later.
function isObjCode(s) {
    if (isEmpty(s))
       if (isObjCode.arguments.length == 1) return defaultEmptyOK;
       else return (isObjCode.arguments[1] == true);

	var regex = "\\d\\d\\d\\d";

	if (s.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// This fuction returns true if the string has an "F" (or "f") in
// the first postion, and 6 numeric digits after it.
function isDDFNumber(s) {
    if (isEmpty(s))
       if (isDDFNumber.arguments.length == 1) return defaultEmptyOK;
       else return (isDDFNumber.arguments[1] == true);

	var regex = "[Ff]\\d\\d\\d\\d\\d\\d";

	if (s.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// This function takes dates in MM/DD/YY(YY) format (hyphens, whacks and periods
// are also ok) or MMDDYY(YY) (FRS format) and returns a javascript date object.
function dateConvert(s) {
	var monthpart,yearpart,daypart,datearray,convertedDate;
	var stringlength = s.length;
	var delim="";

	// find the delimiter
	if (s.indexOf("-") != -1) {
		delim = "-";
	} else if (s.indexOf("/") != -1) {
		delim = "/";
	} else if (s.indexOf("\\") != -1) {
		delim = "\\";
	} else if (s.indexOf(".") != -1) {
		delim = ".";
	}

	// we need to capture the case of no delimiters
	if (delim == "") {
		if (stringlength == 6) {
			monthpart = s.substring(0,2);
			daypart   = s.substring(2,4);
			yearpart  = s.substring(4,7);

			if (yearpart <= 70) {
				yearpart = "20" + yearpart;
			} else {
				yearpart = "19" + yearpart;
			}
		} else if (stringlength == 6) {
			monthpart = s.substring(0,2);
			daypart   = s.substring(2,4);
			yearpart  = s.substring(4,7);
		}

		convertedDate = new Date(yearpart,monthpart,daypart);
		return convertedDate;
	}

	// split the string into its parts
	datearray = s.split(delim);

	convertedDate = new Date(datearray[2],datearray[0],datearray[1]);
	return convertedDate;
}


// This function tests whether the dates given are in the correct order.
// While this is designed primarily for the trip.asp page, it can be used
// for any page with a pair of date fields that need to be in a particular
// order.
function datesInCorrectOrder(first,second) {
	var firstdate  = dateConvert(first);
	var seconddate = dateConvert(second);

	if (firstdate.getTime() <= seconddate.getTime()) {
		return true;
	} else {
		return false;
	}
}

// This function forces the user to confirm that they want to delete something
function confirmDelete() {
	if (confirm("Are you sure that you want to remove this?")) {
		return true;
	} else {
		return false;
	}
}

