/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
