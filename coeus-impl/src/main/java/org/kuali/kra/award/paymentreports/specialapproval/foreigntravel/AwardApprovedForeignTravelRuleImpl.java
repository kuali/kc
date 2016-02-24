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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Date;
import java.util.List;

/**
 * The AwardApprovedEquipmentRuleImpl
 */
public class AwardApprovedForeignTravelRuleImpl extends KcTransactionalDocumentRuleBase
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
        ScaleTwoDecimal amount = foreignTrip.getAmount();
        boolean amountValid = amount != null && amount.doubleValue() >= 0.00;
        if(!amountValid) {
            reportError(AMOUNT_PROPERTY, ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, AMOUNT_ERROR_PARM);
        }
        return amountValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getMessageMap().containsMessageKey(ERROR_AWARD_APPROVED_FOREIGN_TRAVEL_NOT_UNIQUE);
    }
}
