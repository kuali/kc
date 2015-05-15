/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;


/**
 * 
 * This class will be used by both Time &amp; Money and Awards to help validate various date fields and to ensure both apply the same logic.
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
