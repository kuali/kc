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
package org.kuali.kra.award.printing;

/**
 * This class represents different types of reports for award Printing
 */
public enum AwardPrintType {

	AWARD_DELTA_REPORT("awardDeltaReport"), AWARD_NOTICE_REPORT(
			"awardNoticeReport"), AWARD_TEMPLATE("awardTemplate"), AWARD_BUDGET_HIERARCHY(
			"awardBudgetHierarchy"), AWARD_BUDGET_HISTORY_TRANSACTION(
			"awardBudgetHistoryTransaction"),MONEY_AND_END_DATES_HISTORY("moneyAndEndDatesHistory");
	private final String awardPrintType;

	AwardPrintType(String awardPrintType) {
		this.awardPrintType = awardPrintType;
	}

	/**
	 * This method will return the report name,Which ever called.
	 * 
	 * @return report name
	 */
	public String getAwardPrintType() {
		return awardPrintType;
	}
}
