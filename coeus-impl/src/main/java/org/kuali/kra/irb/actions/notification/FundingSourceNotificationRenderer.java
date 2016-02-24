/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.notification;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;

import java.util.Map;

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
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{FUNDING_TYPE}", fundingType);
        params.put("{ACTION}", action);
        return params;
    }

}
