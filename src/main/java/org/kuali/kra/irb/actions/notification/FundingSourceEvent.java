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
 * This class supports a notification for protocol funding sources.
 */
public class FundingSourceEvent extends IRBNotificationContext {
    public static final String FUNDING_SOURCE = "904";
    private String fundingType;
    private String action;
  

    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getActionTypeCode()
     */
    @Override
    public String getActionTypeCode() {
        return FUNDING_SOURCE;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContextBase#getContextName()
     */
    @Override
    public String getContextName() {
        return "FundingSource";
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public String replaceContextVariables(String text) {
        KcNotificationRenderingService renderer = getNotificationRenderingService();
        Map<String, String> params = renderer.getReplacementParameters();
        params.put("{FUNDING_TYPE}", getFundingType());
        params.put("{ACTION}", getAction());
        
        return renderer.render(text, params);
    }

    public String getFundingType() {
        return fundingType;
    }

    public void setFundingType(String fundingType) {
        this.fundingType = fundingType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
