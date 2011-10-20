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

import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.notification.IRBNotificationContext;

/**
 * 
 * This class is for withdraw notification event
 */
public class WithdrawEvent extends IRBNotificationContext {

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return ProtocolActionType.WITHDRAWN;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getContextName()
     */
    @Override
    public String getContextName() {
        return "WithdrawEvent";
    }   

}
