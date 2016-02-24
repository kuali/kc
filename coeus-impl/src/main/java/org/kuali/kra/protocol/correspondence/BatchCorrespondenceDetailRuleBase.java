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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class BatchCorrespondenceDetailRuleBase {
    
    private static final String SEND_CORRESPONDENCE_AFTER_EVENT ="AFTER";

    private static final String PROPERTY_NAME_DAYS_TO_EVENT = "newBatchCorrespondenceDetail.daysToEvent";
    private static final String PROPERTY_NAME_PROTO_CORRESP_TYPE_CODE = "newBatchCorrespondenceDetail.protoCorrespTypeCode";
    private static final String PROPERTY_NAME_FINAL_ACTION_DAY = "batchCorrespondence.finalActionDay";
    private static final String PROPERTY_NAME_FINAL_ACTION_TYPE_CODE = "batchCorrespondence.finalActionTypeCode";
    private static final String PROPERTY_NAME_FINAL_ACTION_CORRESP_TYPE = "batchCorrespondence.finalActionCorrespType";

    public boolean processAddBatchCorrespondenceDetailRules(BatchCorrespondenceBase batchCorrespondence, 
            BatchCorrespondenceDetailBase newBatchCorrespondenceDetail) {
        boolean rulePassed = true;
        
        rulePassed &= validateDaysToEvent(batchCorrespondence, newBatchCorrespondenceDetail);
        rulePassed &= validateProtoCorrespTypeCode(newBatchCorrespondenceDetail);

        return rulePassed;
    }

    private boolean validateDaysToEvent(BatchCorrespondenceBase batchCorrespondence, BatchCorrespondenceDetailBase newBatchCorrespondenceDetails) {
        boolean isValid = true;
        if (newBatchCorrespondenceDetails.getDaysToEvent() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_DAYS_TO_EVENT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_NOT_SPECIFIED);
            isValid = false;
        } else if (newBatchCorrespondenceDetails.getDaysToEvent() < 0) { 
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_DAYS_TO_EVENT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_NEGATIVE);
            isValid = false;
        } else if (StringUtils.equals(batchCorrespondence.getSendCorrespondence(), SEND_CORRESPONDENCE_AFTER_EVENT)  
                && (newBatchCorrespondenceDetails.getDaysToEvent() >= batchCorrespondence.getFinalActionDay())) { 
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_DAYS_TO_EVENT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_INVALID, 
                    Integer.toString(batchCorrespondence.getFinalActionDay() - 1));
            isValid = false;
        } else if (isDuplicate(batchCorrespondence, newBatchCorrespondenceDetails)) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_DAYS_TO_EVENT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_DUPLICATE);
            isValid = false;
        }
        return isValid;
    }

    /**
     * 
     * This method checks if the daysToEvents exists already.
     * @param batchCorrespondence
     * @param newBatchCorrespondenceDetails
     * @return
     */
    private boolean isDuplicate(BatchCorrespondenceBase batchCorrespondence, BatchCorrespondenceDetailBase newBatchCorrespondenceDetails) {
        if (batchCorrespondence.getFinalActionDay().equals(newBatchCorrespondenceDetails.getDaysToEvent())) {
            return true;
        }
        
        for (BatchCorrespondenceDetailBase batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
            if (batchCorrespondenceDetail.getDaysToEvent().equals(newBatchCorrespondenceDetails.getDaysToEvent())) {
                return true;
            }
        }
        
        return false;
    }

    private boolean validateProtoCorrespTypeCode(BatchCorrespondenceDetailBase newBatchCorrespondenceDetails) {
        if (StringUtils.isBlank(newBatchCorrespondenceDetails.getProtoCorrespTypeCode())) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_PROTO_CORRESP_TYPE_CODE, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

    public boolean processSaveBatchCorrespondenceDetailRules(BatchCorrespondenceBase batchCorrespondence) {
        boolean rulePassed = true;
        
        rulePassed &= validateFinalActionDay(batchCorrespondence);
        rulePassed &= validateFinalActionTypeCode(batchCorrespondence);
        rulePassed &= validatefinalActionCorrespType(batchCorrespondence);

        return rulePassed;
    }

    private boolean validateFinalActionDay(BatchCorrespondenceBase batchCorrespondence) {
        if (batchCorrespondence.getFinalActionDay() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_DAY, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_NOT_SPECIFIED);
            return false;
        }
        
        if (StringUtils.equals(batchCorrespondence.getSendCorrespondence(), SEND_CORRESPONDENCE_AFTER_EVENT)) {
            for (BatchCorrespondenceDetailBase batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
                if (batchCorrespondenceDetail.getDaysToEvent() >= batchCorrespondence.getFinalActionDay()) {
                    GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_DAY, 
                            KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_INVALID);
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean validateFinalActionTypeCode(BatchCorrespondenceBase batchCorrespondence) {
        if (batchCorrespondence.getFinalActionTypeCode() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_TYPE_CODE, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_TYPE_CODE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

    private boolean validatefinalActionCorrespType(BatchCorrespondenceBase batchCorrespondence) {
        if (batchCorrespondence.getFinalActionCorrespType() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_CORRESP_TYPE, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_CORRESP_TYPE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

}
