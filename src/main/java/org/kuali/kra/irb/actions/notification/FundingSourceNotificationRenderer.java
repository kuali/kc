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

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;

/**
 * Renders additional fields for the Funding Source notification.
 */
public class FundingSourceNotificationRenderer extends IRBNotificationRenderer {

    private static final long serialVersionUID = 7708247234043655433L;
    
    private String fundingType;
    private String action;

    /**
     * Constructs a Funding Source notification renderer.
     * 
     * @param protocol
     * @param fundingType
     * @param action
     */
    public FundingSourceNotificationRenderer(Protocol protocol, String fundingType, String action) {
        super(protocol);
        
        this.fundingType = fundingType;
        this.action = action;
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
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{FUNDING_TYPE}", fundingType);
        params.put("{ACTION}", action);
        return params;
    }

}