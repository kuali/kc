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

public class IacucProtocolFundingSourceNotificationRenderer extends IacucProtocolNotificationRenderer {


    private static final long serialVersionUID = 3119244748963366055L;

    private String fundingType;
    private String action;

    /**
     * Constructs a Funding Source notification renderer.
     * 
     * @param protocol
     * @param fundingType
     * @param action
     */
    public IacucProtocolFundingSourceNotificationRenderer(IacucProtocol protocol, String fundingType, String action) {
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
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{FUNDING_TYPE}", fundingType);
        params.put("{ACTION}", action);
        return params;
    }

}
