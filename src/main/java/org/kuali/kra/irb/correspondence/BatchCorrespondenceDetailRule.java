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
package org.kuali.kra.irb.correspondence;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class BatchCorrespondenceDetailRule {
    
    private static final String SEND_CORRESPONDENCE_AFTER_EVENT ="AFTER";

    private static final String PROPERTY_NAME_DAYS_TO_EVENT = "newBatchCorrespondenceDetail.daysToEvent";
    private static final String PROPERTY_NAME_PROTO_CORRESP_TYPE_CODE = "newBatchCorrespondenceDetail.protoCorrespTypeCode";
    private static final String PROPERTY_NAME_FINAL_ACTION_DAY = "batchCorrespondence.finalActionDay";
    private static final String PROPERTY_NAME_FINAL_ACTION_TYPE_CODE = "batchCorrespondence.finalActionTypeCode";
    private static final String PROPERTY_NAME_FINAL_ACTION_CORRESP_TYPE = "batchCorrespondence.finalActionCorrespType";

    public boolean processAddBatchCorrespondenceDetailRules(BatchCorrespondence batchCorrespondence, 
            BatchCorrespondenceDetail newBatchCorrespondenceDetail) {
        boolean rulePassed = true;
        
        rulePassed &= validateDaysToEvent(batchCorrespondence, newBatchCorrespondenceDetail);
        rulePassed &= validateProtoCorrespTypeCode(newBatchCorrespondenceDetail);

        return rulePassed;
    }

    private boolean validateDaysToEvent(BatchCorrespondence batchCorrespondence, BatchCorrespondenceDetail newBatchCorrespondenceDetails) {
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
    private boolean isDuplicate(BatchCorrespondence batchCorrespondence, BatchCorrespondenceDetail newBatchCorrespondenceDetails) {
        if (batchCorrespondence.getFinalActionDay().equals(newBatchCorrespondenceDetails.getDaysToEvent())) {
            return true;
        }
        
        for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
            if (batchCorrespondenceDetail.getDaysToEvent().equals(newBatchCorrespondenceDetails.getDaysToEvent())) {
                return true;
            }
        }
        
        return false;
    }

    private boolean validateProtoCorrespTypeCode(BatchCorrespondenceDetail newBatchCorrespondenceDetails) {
        if (StringUtils.isBlank(newBatchCorrespondenceDetails.getProtoCorrespTypeCode())) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_PROTO_CORRESP_TYPE_CODE, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

    public boolean processSaveBatchCorrespondenceDetailRules(BatchCorrespondence batchCorrespondence) {
        boolean rulePassed = true;
        
        rulePassed &= validateFinalActionDay(batchCorrespondence);
        rulePassed &= validateFinalActionTypeCode(batchCorrespondence);
        rulePassed &= validatefinalActionCorrespType(batchCorrespondence);

        return rulePassed;
    }

    private boolean validateFinalActionDay(BatchCorrespondence batchCorrespondence) {
        if (batchCorrespondence.getFinalActionDay() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_DAY, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_NOT_SPECIFIED);
            return false;
        }
        
        if (StringUtils.equals(batchCorrespondence.getSendCorrespondence(), SEND_CORRESPONDENCE_AFTER_EVENT)) {
            for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
                if (batchCorrespondenceDetail.getDaysToEvent() >= batchCorrespondence.getFinalActionDay()) {
                    GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_DAY, 
                            KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_INVALID);
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean validateFinalActionTypeCode(BatchCorrespondence batchCorrespondence) {
        if (batchCorrespondence.getFinalActionTypeCode() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_TYPE_CODE, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_TYPE_CODE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

    private boolean validatefinalActionCorrespType(BatchCorrespondence batchCorrespondence) {
        if (batchCorrespondence.getFinalActionCorrespType() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_FINAL_ACTION_CORRESP_TYPE, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_CORRESP_TYPE_NOT_SPECIFIED);
            return false;
        } else {
            return true;
        }
    }

}
