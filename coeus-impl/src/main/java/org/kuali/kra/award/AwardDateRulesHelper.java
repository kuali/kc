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
package org.kuali.kra.award;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;


/**
 * 
 * This class will be used by both Time & Money and Awards to help validate various date fields and to ensure both apply the same logic. 
 */
public class AwardDateRulesHelper {
    
    public static final String OBLIGATION_START_DATE = "Obligation Start Date";
    public static final String OBLIGATION_END_DATE = "Obligation End Date";
    public static final String PROJECT_START_DATE = "Project Start Date";
    public static final String PROJECT_END_DATE = "Project End Date";
    
    public static boolean validateObligationStartBeforeObligationEnd(MessageMap errorMap, Date obligationStartDate, Date obligationEndDate, String fieldName, String awardID) {
        boolean success = true;
        if (isDateOneAfterDateTwo(obligationStartDate, obligationEndDate)) {
            success = false;
            errorMap.putError(fieldName, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, new String[] {OBLIGATION_START_DATE, OBLIGATION_END_DATE, awardID});
        }
        return success;
    }
    
    public static boolean validateObligationStartBeforeProjectEnd(MessageMap errorMap, Date obligationStartDate, Date projectEndDate, String fieldName, String awardID) {
        boolean success = true;
        if (isDateOneAfterDateTwo(obligationStartDate, projectEndDate)) {
            success = false;
            errorMap.putError(fieldName, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, new String[] {OBLIGATION_START_DATE, PROJECT_END_DATE, awardID});
        }
        return success;
    }
    
    public static boolean validateObligationEndBeforeProjectEnd(MessageMap errorMap, Date obligationEndDate, Date projectEndDate, String fieldName, String awardID) {
        boolean success = true;
        if (isDateOneAfterDateTwo(obligationEndDate, projectEndDate)) {
            success = false;
            errorMap.putError(fieldName, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, new String[] {OBLIGATION_END_DATE, PROJECT_END_DATE, awardID});
        }
        return success;
    }
    
    public static boolean validateProjectStartBeforeObligationStart(MessageMap errorMap, Date projectStartDate, Date obligationStartDate, String fieldName, String awardID) {
        boolean success = true;
        if (isDateOneAfterDateTwo(projectStartDate, obligationStartDate)) {
            success = false;
            errorMap.putError(fieldName, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, new String[] {PROJECT_START_DATE, OBLIGATION_START_DATE, awardID});
        }
        return success;
    }
    
    public static boolean validateProjectStartBeforeObligationEnd(MessageMap errorMap, Date projectStartDate, Date obligationEndDate, String fieldName, String awardID) {
        boolean success = true;
        if (isDateOneAfterDateTwo(projectStartDate, obligationEndDate)) {
            success = false;
            errorMap.putError(fieldName, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, new String[] {PROJECT_START_DATE, OBLIGATION_END_DATE, awardID});
        }
        return success;
    }
    
    public static boolean validateProjectStartBeforeProjectEnd(MessageMap errorMap, Date projectStartDate, Date projectEndDate, String fieldName, String awardID) {
        boolean success = true;
        if (isDateOneAfterDateTwo(projectStartDate, projectEndDate)) {
            success = false;
            errorMap.putError(fieldName, KeyConstants.ERROR_START_DATE_ON_OR_BEFORE, new String[] {PROJECT_START_DATE, PROJECT_END_DATE, awardID});
        }
        return success;
    }
    
    public static boolean isDateOneAfterDateTwo(Date dateOne, Date dateTwo) {
        boolean valid = false;
        if (dateOne != null && dateTwo != null && dateOne.after(dateTwo)) {
           valid = true;
        }
        return valid;
    }
}
