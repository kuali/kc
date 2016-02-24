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
package org.kuali.kra.protocol.summary;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class FundingSourceSummary implements Serializable {

    private static final long serialVersionUID = -9005092006555888615L;
    
    private String fundingSourceType;
    private String fundingSource;
    private String fundingSourceNumber;
    private String fundingSourceName;
    private String fundingSourceTitle;
    
    private boolean fundingSourceTypeChanged;
    private boolean fundingSourceChanged;
    private boolean fundingSourceNumberChanged;
    private boolean fundingSourceNameChanged;
    private boolean fundingSourceTitleChanged;
    
    public String getFundingSourceType() {
        return fundingSourceType;
    }
    
    public void setFundingSourceType(String fundingSourceType) {
        this.fundingSourceType = fundingSourceType;
    }
    
    public String getFundingSource() {
        return fundingSource;
    }
    
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }
    
    public String getFundingSourceNumber() {
        return fundingSourceNumber;
    }
    
    public void setFundingSourceNumber(String fundingSourceNumber) {
        this.fundingSourceNumber = fundingSourceNumber;
    }
    
    public String getFundingSourceName() {
        return fundingSourceName;
    }
    
    public void setFundingSourceName(String fundingSourceName) {
        this.fundingSourceName = fundingSourceName;
    }
    
    public String getFundingSourceTitle() {
        return fundingSourceTitle;
    }
    
    public void setFundingSourceTitle(String fundingSourceTitle) {
        this.fundingSourceTitle = fundingSourceTitle;
    }

    public boolean isFundingSourceTypeChanged() {
        return fundingSourceTypeChanged;
    }

    public boolean isFundingSourceChanged() {
        return fundingSourceChanged;
    }
    
    public boolean isFundingSourceNumberChanged() {
        return fundingSourceNumberChanged;
    }

    public boolean isFundingSourceNameChanged() {
        return fundingSourceNameChanged;
    }

    public boolean isFundingSourceTitleChanged() {
        return fundingSourceTitleChanged;
    }

    public void compare(ProtocolSummary other) {
        FundingSourceSummary otherFundingSource = other.findFundingSource(fundingSourceType, fundingSource);
        if (otherFundingSource == null) {
            fundingSourceTypeChanged = true;
            fundingSourceChanged = true;
            fundingSourceNumberChanged = true;
            fundingSourceNameChanged = true;
            fundingSourceTitleChanged = true;
        } else {
            fundingSourceTypeChanged = !StringUtils.equals(fundingSourceType, otherFundingSource.getFundingSourceType());
            fundingSourceChanged = !StringUtils.equals(fundingSource, otherFundingSource.getFundingSource());
            fundingSourceNumberChanged = !StringUtils.equals(fundingSourceNumber, otherFundingSource.getFundingSourceNumber());
            fundingSourceNameChanged = !StringUtils.equals(fundingSourceName, otherFundingSource.getFundingSourceName());
            fundingSourceTitleChanged = !StringUtils.equals(fundingSourceTitle, otherFundingSource.getFundingSourceTitle());
        }
    }
}
