/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
