/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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

package org.kuali.kra.subaward.reporting.printing;

/**
 * This class represents different types of reports for Negotiation Activity
 * Printing
 */
public enum SubAwardPrintType {
	SUB_AWARD_SF_294_PRINT_TYPE("SF294"),
	SUB_AWARD_SF_295_PRINT_TYPE("SF295"),
	SUB_AWARD_FDP_TEMPLATE("fdpAgreement"),
	SUB_AWARD_FDP_MODIFICATION("fdpModification");
	

	private final String subAwardPrintType;

	SubAwardPrintType(String subAwardPrintType) {
		this.subAwardPrintType = subAwardPrintType;
	}

	public String getSubAwardPrintType() {
		return subAwardPrintType;
	}
}
