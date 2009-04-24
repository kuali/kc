/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * The AwardApprovedEquipmentRuleImpl
 */
public class AwardApprovedForeignTravelRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardApprovedForeignTravelRule {
    
    private static final String NEW_TRAVEL_BASE = "approvedForeignTravelBean.newApprovedForeignTravel.";
    private static final String TRAVELER_NAME_PROPERTY = NEW_TRAVEL_BASE + "travelerName";
    private static final String DESTINATION_PROPERTY = NEW_TRAVEL_BASE + "destination";
    private static final String START_DATE_PROPERTY = NEW_TRAVEL_BASE + "startDate";
    private static final String END_DATE_PROPERTY = NEW_TRAVEL_BASE + "endDate";
    private static final String AMOUNT_PROPERTY = NEW_TRAVEL_BASE + "amount";
    
    private static final String TRAVELER_NAME_ERROR_PARM = "Traveler Name";
    private static final String DESTINATION_ERROR_PARM = "Destination";
    private static final String START_DATE_ERROR_PARM = "Start Date";
    private static final String END_DATE_ERROR_PARM = "End Date";
    private static final String AMOUNT_ERROR_PARM = "Amount";

    public boolean processAwardApprovedForeignTravelBusinessRules(AwardApprovedForeignTravelRuleEvent event) {
        return processCommonValidations(APPROVED_FOREIGN_TRAVEL_LIST_ERROR_KEY, event);        
    }
    /**
     * 
     * This method processes new AwardApprovedForeignTravel rules
     * 
     * @param event
     * @return
     */
    public boolean processAddAwardApprovedForeignTravelBusinessRules(AddAwardApprovedForeignTravelRuleEvent event) {
        AwardApprovedForeignTravel foreignTravel = event.getForeignTravelForValidation();
        return isAmountValid(event.getErrorPathPrefix(), foreignTravel) & areRequiredFieldsComplete(foreignTravel) & 
                                processCommonValidations(NEW_TRAVEL_BASE, event);        
    }
    
    private boolean processCommonValidations(String errorPath, AwardApprovedForeignTravelRuleEvent event) {
        AwardApprovedForeignTravel foreignTravel = event.getForeignTravelForValidation();
        boolean valid = isEndDateOnOrAfterStartDate(event);
        
        List<AwardApprovedForeignTravel> trips = event.getAward().getApprovedForeignTravelTrips();
        valid &= isUnique(errorPath, trips, foreignTravel);
        
        return valid;
    }
    
    private boolean isEndDateOnOrAfterStartDate(AwardApprovedForeignTravelRuleEvent event) {
        AwardApprovedForeignTravel trip = event.getForeignTravelForValidation();
        boolean valid;
        Date startDate = trip.getStartDate();
        Date endDate = trip.getEndDate();
        if(startDate != null && endDate != null) {
            valid = !endDate.before(startDate);
            if(!valid) {
                reportError(END_DATE_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_TRAVEL_END_DATE_BEFORE_START_DATE, END_DATE_ERROR_PARM);
            }
        } else {
            valid = true;
        }
        return valid;
    }
    /**
     * An equipment item is unique if no other matching items are in the collection
     * To know if this is a new add or an edit of an existing equipment item, we check 
     * the identifier for nullity. If null, this is an add; otherwise, it's an update
     * If an update, then we expect to find one match in the collection (itself). If an add, 
     * we expect to find no matches in the collection 
     * @param equipmentItems
     * @param equipmentItem
     * @return
     */
    boolean isUnique(String errorPath, List<AwardApprovedForeignTravel> foreignTravelTrips, AwardApprovedForeignTravel foreignTravelTrip) {
        boolean duplicateFound = false;
        for(AwardApprovedForeignTravel listItem: foreignTravelTrips) {
            duplicateFound = foreignTravelTrip != listItem && listItem.equals(foreignTravelTrip);
            if(duplicateFound) {
                break;
            }
        }
        
        if(duplicateFound) {
            if(!hasDuplicateErrorBeenReported()) {
                reportError(errorPath, ERROR_AWARD_APPROVED_FOREIGN_TRAVEL_NOT_UNIQUE, (String[]) null);
            }
        }
        return !duplicateFound;
    }

    /**
     * Validate required fields present
     * @param equipmentItem
     * @return
     */
    boolean areRequiredFieldsComplete(AwardApprovedForeignTravel foreignTravelTrip) {
        boolean travelerNameValid = !StringUtils.isEmpty(foreignTravelTrip.getTravelerName());
        if(!travelerNameValid) {
            reportError(TRAVELER_NAME_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, TRAVELER_NAME_ERROR_PARM);
        }
        
        boolean destinationValid = !StringUtils.isEmpty(foreignTravelTrip.getDestination());
        if(!destinationValid) {
            reportError(DESTINATION_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, DESTINATION_ERROR_PARM);
        }
        
        boolean startDateValid = foreignTravelTrip.getStartDate() != null;
        if(!startDateValid) {
            reportError(START_DATE_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, START_DATE_ERROR_PARM);
        }
        
        boolean amountValid = foreignTravelTrip.getAmount() != null;
        if(!amountValid) {
            reportError(AMOUNT_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, AMOUNT_ERROR_PARM);
        }
        
        return travelerNameValid && destinationValid && startDateValid && amountValid;
    }
    
    /**
     * @param errorPath
     * @param equipmentItem
     * @param minimumCapitalization
     * @return
     */
    boolean isAmountValid(String errorPath, AwardApprovedForeignTravel foreignTrip) {
        KualiDecimal amount = foreignTrip.getAmount();
        boolean amountValid = amount != null && amount.doubleValue() >= 0.00;
        if(!amountValid) {
            reportError(AMOUNT_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, AMOUNT_ERROR_PARM);
        }
        return amountValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getErrorMap().containsMessageKey(ERROR_AWARD_APPROVED_FOREIGN_TRAVEL_NOT_UNIQUE);
    }
}