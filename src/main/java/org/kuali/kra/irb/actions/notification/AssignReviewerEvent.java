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
package org.kuali.kra.irb.actions.notification;

import java.util.Map;

import org.kuali.kra.common.notification.service.KcNotificationRenderingService;
import org.kuali.kra.irb.notification.IRBNotificationContext;


/**
 * 
 * This class is the event for Assign reviewer notification.
 */
public class AssignReviewerEvent extends IRBNotificationContext {
    public static final String ASSIGN_REVIEWER = "901";
    
    private String actionTaken;

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return ASSIGN_REVIEWER;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getContextName()
     */
    @Override
    public String getContextName() {
        return "AssignReviewerEvent";
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public String replaceContextVariables(String text) {
        KcNotificationRenderingService renderer = getNotificationRenderingService();
        Map<String, String> params = renderer.getReplacementParameters();
        params.put("{ACTION_TAKEN}", getActionTaken());
        
        return renderer.render(text, params);
    }    

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }


}
