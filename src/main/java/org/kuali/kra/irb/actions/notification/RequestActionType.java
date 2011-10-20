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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.notification.IRBNotificationContext;


public enum RequestActionType {
    REQUEST_TO_CLOSE(RequestToCloseEvent.class, "105"), 
    REQUEST_TO_SUSPENSION(RequestToSuspensionEvent.class, "106"), 
    CLOSE_ENROLLMENT(CloseEnrollmentEvent.class, "108"), 
    OPEN_ENROLLMENT(OpenEnrollmentEvent.class, "115"), 
    DATA_ANALYSIS(DataAnalysisEvent.class, "114"); 


    
    private final Class<? extends IRBNotificationContext> eventClass;
    private final String actionTypeCode;

    RequestActionType(Class<? extends IRBNotificationContext> eventClass, String actionTypeCode) {
        this.eventClass = eventClass;
        this.actionTypeCode = actionTypeCode;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public Class<? extends IRBNotificationContext> getEventClass() {
        return eventClass;
    }

    public static RequestActionType  getRequestActionType(String actionTypeCode) {
        for (RequestActionType requestActionType : values()) {
            if(StringUtils.equals(requestActionType.getActionTypeCode(), actionTypeCode)) {
                return requestActionType;
            }
        }
        return null;
        
    }

}
