/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.negotiations.rules;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * Validation class to use when validating activities.
 */
public class NegotiationActivityRuleImpl implements NegotiationActivityAddRule {
    
    private static final String START_DATE_PROPERTY = "startDate";
    private static final String END_DATE_PROPERTY = "endDate";

    private final ErrorReporter errorReporter = new ErrorReporter();
    
    /**
     * @see org.kuali.kra.negotiations.rules.NegotiationActivityAddRule#processAddNegotiationActivityRule(org.kuali.kra.negotiations.rules.NegotiationActivityAddRuleEvent)
     */
    public boolean processAddNegotiationActivityRule(NegotiationActivityAddRuleEvent event) {
        boolean result = true;
        
        NegotiationDocument negotiationDoc = (NegotiationDocument) event.getDocument();
        Negotiation negotiation = negotiationDoc.getNegotiation();
        NegotiationActivity newActivity = event.getNewActivity();
        
        GlobalVariables.getMessageMap().addToErrorPath(event.getErrorPathPrefix());
        result &= validateNegotiationActivity(newActivity, negotiation);
        GlobalVariables.getMessageMap().removeFromErrorPath(event.getErrorPathPrefix());
        
        
        return result;
    }
    
    /**
     * 
     * Call this to validate individual activities.
     * @param activity
     * @param negotiation
     * @return
     */
    public boolean validateNegotiationActivity(NegotiationActivity activity, Negotiation negotiation) {
        boolean result = true;
        activity.refreshReferenceObject("activityType");
        if (activity.getActivityType() == null) {
            result = false;
            errorReporter.reportError("activityTypeId", KeyConstants.ERROR_REQUIRED, "Activity Type (Activity Type)");
        }
        activity.refreshReferenceObject("location");
        if (activity.getLocation() == null) {
            result = false;
            errorReporter.reportError("locationId", KeyConstants.ERROR_REQUIRED, "Location (Location)");
        }
        if (activity.getStartDate() == null) {
            result = false;
            errorReporter.reportError(START_DATE_PROPERTY, KeyConstants.ERROR_REQUIRED, "Activity Start Date (Activity Start Date)");
        }
        if (StringUtils.isBlank(activity.getDescription())) {
            result = false;
            errorReporter.reportError("description", KeyConstants.ERROR_REQUIRED, "Activity Description (Activity Description)");
        }
        if (activity.getStartDate() != null && negotiation.getNegotiationStartDate() != null 
                && activity.getStartDate().compareTo(negotiation.getNegotiationStartDate()) < 0) {
            result = false;
            errorReporter.reportError(START_DATE_PROPERTY, KeyConstants.NEGOTIATION_ACTIVITY_START_BEFORE_NEGOTIATION);
        }
        if (activity.getStartDate() != null && activity.getEndDate() != null
                && activity.getStartDate().compareTo(activity.getEndDate()) > 0) {
            result = false;
            errorReporter.reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ACTIVITY_START_BEFORE_END);
        }
        if (activity.getEndDate() != null && negotiation.getNegotiationEndDate() != null
                && activity.getEndDate().compareTo(negotiation.getNegotiationEndDate()) > 0) {
            result = false;
            errorReporter.reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ACTIVITY_END_AFTER_NEGOTIATION);
        }
        if (activity.getFollowupDate() != null) {
            //get today but without any time fields so compare is done strictly on the date.
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            if (activity.getFollowupDate().compareTo(today.getTime()) < 0) {
                result = false;
                errorReporter.reportError("followupDate", KeyConstants.NEGOTIATION_ACTIVITY_FOLLOWUP_BEFORE_TODAY);
            }
        }
        return result;
    }

}
