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
