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
package org.kuali.kra.irb.correspondence;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.GlobalVariables;

public class BatchCorrespondenceDetailRule {

    private static final String PROPERTY_NAME_NO_OF_DAYS_TILL_NEXT = "newBatchCorrespondenceDetail.noOfDaysTillNext";
    private static final String PROPERTY_NAME_PROTO_CORRESP_TYPE_CODE = "newBatchCorrespondenceDetail.protoCorrespTypeCode";
    private static final String PROPERTY_NAME_DEFAULT_TIME_WINDOW = "batchCorrespondence.defaultTimeWindow";
    private static final String PROPERTY_NAME_FINAL_ACTION_TYPE_CODE = "batchCorrespondence.finalActionTypeCode";
    private static final String PROPERTY_NAME_FINAL_ACTION_CORRESP_TYPE = "batchCorrespondence.finalActionCorrespType";

    public boolean processAddBatchCorrespondenceDetailRules(BatchCorrespondence batchCorrespondence, 
            BatchCorrespondenceDetail newBatchCorrespondenceDetail) {
        boolean rulePassed = true;
        
        rulePassed &= validateNoOfDaysTillNext(batchCorrespondence, newBatchCorrespondenceDetail);
        rulePassed &= validateProtoCorrespTypeCode(newBatchCorrespondenceDetail);

        return rulePassed;
    }

    private boolean validateNoOfDaysTillNext(BatchCorrespondence batchCorrespondence, BatchCorrespondenceDetail newBatchCorrespondenceDetails) {
        boolean isValid = true;
        if (newBatchCorrespondenceDetails.getNoOfDaysTillNext() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_NO_OF_DAYS_TILL_NEXT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_NO_OF_DAYS_TILL_NEXT_NOT_SPECIFIED);
            isValid = false;
        } else if (newBatchCorrespondenceDetails.getNoOfDaysTillNext() < 1) { 
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_NO_OF_DAYS_TILL_NEXT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_NO_OF_DAYS_TILL_NEXT_INVALID, 
                    Integer.toString(batchCorrespondence.getDefaultTimeWindow() - 1));
            isValid = false;
        } else if ((batchCorrespondence.getDefaultTimeWindow() != null) 
                &&(newBatchCorrespondenceDetails.getNoOfDaysTillNext() >= batchCorrespondence.getDefaultTimeWindow())) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_NO_OF_DAYS_TILL_NEXT, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_NO_OF_DAYS_TILL_NEXT_INVALID, 
                    Integer.toString(batchCorrespondence.getDefaultTimeWindow() - 1));
            isValid = false;
        }
        return isValid;
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
        
        rulePassed &= validateDefaultTimeWindow(batchCorrespondence);
        rulePassed &= validateFinalActionTypeCode(batchCorrespondence);
        rulePassed &= validatefinalActionCorrespType(batchCorrespondence);

        return rulePassed;
    }

    private boolean validateDefaultTimeWindow(BatchCorrespondence batchCorrespondence) {
        if (batchCorrespondence.getDefaultTimeWindow() == null) {
            GlobalVariables.getMessageMap().putError(PROPERTY_NAME_DEFAULT_TIME_WINDOW, 
                    KeyConstants.ERROR_BATCH_CORRESPONDENCE_DEFAULT_TIME_WINDOW_NOT_SPECIFIED);
            return false;
        }
        
        for (BatchCorrespondenceDetail batchCorrespondenceDetail : batchCorrespondence.getBatchCorrespondenceDetails()) {
            if (batchCorrespondenceDetail.getNoOfDaysTillNext() >= batchCorrespondence.getDefaultTimeWindow()) {
                GlobalVariables.getMessageMap().putError(PROPERTY_NAME_DEFAULT_TIME_WINDOW, 
                        KeyConstants.ERROR_BATCH_CORRESPONDENCE_DEFAULT_TIME_WINDOW_INVALID);
                return false;
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
