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
package org.kuali.coeus.common.budget.framework.period;


public enum BudgetSummaryErrorConstants {
        ERROR_PERIOD_START_BEFORE_PROJECT_START("error.periodStartDate.before.projectStartDate", "startDate"),
        ERROR_PERIOD_START_BEFORE_PREVIOUS_END("error.periodStartDate.before.previousEndDate", "startDate"),
        ERROR_NEW_PERIOD_START_BEFORE_PREVIOUS_END("error.newPeriod.projectEnd", "startDate"),
        ERROR_NEW_PERIOD_VALID("error.newPeriod.validDate", "startDate"),
        ERROR_NEW_PERIOD_START_AFTER_PROJECT_END("error.new.PeriodStartDate.after.projectEndDate", "startDate"),
        ERROR_PERIOD_START_REQUIRED("error.periodStartDate.required", "startDate"),
        ERROR_NEW_PERIOD_INVALID("error.new.PeriodStartDate.invalid", "startDate"),
        ERROR_PERIOD_START_AFTER_PERIOD_END("error.periodStart.after.periodEnd", "endDate"),
        ERROR_PERIOD_START_AFTER_PROJECT_END("error.periodStartDate.after.projectEndDate", "startDate"),
        ERROR_PERIOD_END_BEFORE_PROJECT_START("error.periodEnd.before.projectStart", "endDate"),
        ERROR_PERIOD_END_AFTER_PROJECT_END("error.periodEndDate.after.projectEndDate", "endDate"),
        ERROR_PERIOD_END_BEFORE_PREVIOUS_END("error.periodEnd.before.previousEnd", "endDate"),
        ERROR_NEW_PERIOD_PROJECT_END("error.newPeriod.projectEnd", "endDate"),
        ERROR_PERIOD_END_REQUIRED("error.periodEndDate.required", "endDate"),
        ERROR_NEW_PERIOD_END_DATE("error.newPeriod.endDate", "endDate"),
        ERROR_LINE_ITEM_DATE_DOESNOTMATCH("error.lineItem.dateDoesNotmatch", "startDate"),
        ERROR_LINE_ITEM_EXISTS("error.lineItem.exists", "startDate"),
        ERROR_PERIOD_LINE_ITEM_DOESNOT_EXIST("error.periodLineItem.doesNot.exist", "noFocus"),
        ERROR_GENERATE_PERIOD("error.generatePeriod", "noFocus"),
        ERROR_NO_FUTURE_PERIOD_TO_GENERATE("error.noFuturePeriodToGenerate", "noFocus"),
        ERROR_PERIOD_GAPS("error.budgetPeriodGaps", "noFocus");
        private final String errorKey;   
        private final String errorProperty;

        BudgetSummaryErrorConstants(String errorKey, String errorProperty) {
            this.errorKey = errorKey;
            this.errorProperty = errorProperty;
        }

        public String errorKey()   
        { 
            return errorKey; 
        }

        public String errorProperty() 
        { 
            return errorProperty; 
        }

}
