/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.infrastructure;

import java.util.Map;

public enum BudgetSummaryErrorConstants {
        ERROR_PERIOD_START_BEFORE_PROJECT_START("error.periodStartDate.before.projectStartDate", "startDate"),
        ERROR_PERIOD_START_BEFORE_PREVIOUS_START("error.periodStartDate.before.previousEndDate", "startDate"),
        ERROR_NEW_PERIOD_VALID("error.newPeriod.validDate", "startDate"),
        ERROR_NEW_PERIOD_START_AFTER_PROJECT_END("error.new.PeriodStartDate.after.projectEndDate", "startDate"),
        ERROR_PERIOD_START_REQUIRED("error.periodStartDate.required", "startDate"),
        ERROR_NEW_PERIOD_INVALID("error.new.PeriodStartDate.invalid", "startDate"),
        ERROR_PERIOD_START_AFTER_PERIOD_END("error.periodStart.after.periodEnd", "endDate"),
        ERROR_PERIOD_START_AFTER_PROJECT_END("error.periodStartDate.after.projectEndDate", "endDate"),
        ERROR_PERIOD_END_BEFORE_PROJECT_START("error.periodEnd.before.projectStart", "endDate"),
        ERROR_PERIOD_END_AFTER_PROJECT_END("error.periodEndDate.after.projectEndDate", "endDate"),
        ERROR_PERIOD_END_BEFORE_PREVIOUS_END("error.periodEnd.before.previousEnd", "endDate"),
        ERROR_NEW_PERIOD_PROJECT_END("error.newPeriod.projectEnd", "endDate"),
        ERROR_PERIOD_END_REQUIRED("error.periodEndDate.required", "endDate"),
        ERROR_NEW_PERIOD_END_DATE("error.newPeriod.endDate", "endDate"),
        ERROR_LINE_ITEM_DATE_DOESNOTMATCH("error.lineItem.dateDoesNotmatch", "startDate"),
        ERROR_LINE_ITEM_EXISTS("error.lineItem.exists", "startDate"),
        ERROR_PERIOD_LINE_ITEM_DOESNOT_EXIST("error.periodLineItem.doesNot.exist", "noFocus"),
        ERROR_GENERATE_PERIOD("error.generatePeriod", "noFocus");

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
