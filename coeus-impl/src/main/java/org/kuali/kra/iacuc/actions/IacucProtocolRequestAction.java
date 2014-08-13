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
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;

/**
 * This class is an enum which set all the properties for each request action.
 * This is referenced by IacucProtocolActionsAction.requestAction() method
 */
public enum IacucProtocolRequestAction {
    
    /**
     * Request to deactivate a Protocol.
     */
    REQUEST_TO_DEACTIVATE           (IacucProtocolActionType.REQUEST_DEACTIVATE,
                                    TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE,
                                    Constants.IACUC_DEACTIVATE_ACTION_PROPERTY_KEY, 
                                    "iacucProtocolDeactivateRequestBean", 
                                    "Request to Deactivate"), 

    /**
     * Request to lift a hold on a Protocol.
     */
    REQUEST_TO_LIFT_HOLD            (IacucProtocolActionType.REQUEST_LIFT_HOLD,
                                    TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD,
                                    Constants.IACUC_REQUEST_LIFT_HOLD_ACTION_PROPERTY_KEY, 
                                    "iacucProtocolLiftHoldRequestBean", 
                                    "Request to Lift Hold"), 

    /**
     * Request for suspension of a Protocol.
     */
    REQUEST_FOR_SUSPENSION         (IacucProtocolActionType.IACUC_REQUEST_SUSPEND, 
                                    TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION,
                                    Constants.PROTOCOL_SUSPEND_REQUEST_PROPERTY_KEY, 
                                    "iacucProtocolSuspendRequestBean", 
                                    "Request for Suspension"),
                                  
    /**
     * Withdraw a request to deactivate/suspend/lift hold on a Protocol.
     */
    WITHDRAW_SUBMISSION            (IacucProtocolActionType.IACUC_WITHDRAW_SUBMISSION,
                                    TaskName.IACUC_WITHDRAW_SUBMISSION,
                                    Constants.IACUC_WITHDRAW_SUBMISSION_PROPERTY_KEY, 
                                    "iacucProtocolWithdrawSubmission", 
                                    "Withdraw Submission Request");
    
    private final String actionTypeCode;
    private final String taskName;
    private final String errorPath;
    private final String beanName;
    private final String actionName;

    
    private IacucProtocolRequestAction(String actionTypeCode, String taskName, String errorPath, String beanName, String actionName) {
        this.actionTypeCode = actionTypeCode;
        this.taskName = taskName;
        this.errorPath = errorPath;
        this.beanName = beanName;
        this.actionName = actionName;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getActionName() {
        return actionName;
    }
    
    /**
     * Returns the value of this enumeration based on the given taskName.
     * 
     * This can return null, which isn't ideal but is necessary since TaskName is not an enum.
     * @param taskName the name of the task to perform
     * @return the ProtocolRequestAction corresponding to the taskName
     */
    public static IacucProtocolRequestAction valueOfTaskName(String taskName) {
        IacucProtocolRequestAction protocolRequestAction = null;
        
        for (IacucProtocolRequestAction action : values()) {
            if (action.getTaskName().equals(taskName)) {
                protocolRequestAction = action;
            }
        }
        
        return protocolRequestAction;
    }

}