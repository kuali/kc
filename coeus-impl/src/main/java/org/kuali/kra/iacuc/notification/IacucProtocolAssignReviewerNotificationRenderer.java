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
package org.kuali.kra.iacuc.notification;

import org.kuali.kra.iacuc.IacucProtocol;

import java.util.Map;

/**
 * Renders additional fields for the Assign Reviewer notification.
 */
public class IacucProtocolAssignReviewerNotificationRenderer extends IacucProtocolNotificationRenderer {

    private static final long serialVersionUID = 9066343992573219667L;

    private String actionTaken;
    
    /**
     * Constructs an Assign Reviewer notification renderer.
     * 
     * @param protocol
     * @param actionTaken
     */
    public IacucProtocolAssignReviewerNotificationRenderer(IacucProtocol protocol, String actionTaken) {
        super(protocol);
        
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{ACTION_TAKEN}", actionTaken);
        return params;
    }    

}
