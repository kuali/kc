/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.summary;

import java.io.Serializable;

public class FundingSourceSummary implements Serializable {

    private static final long serialVersionUID = -9005092006555888615L;
    
    private String fundingType;
    private String fundingId;
    private String fundingSource;
    private String title;
    
    public String getFundingType() {
        return fundingType;
    }
    public void setFundingType(String fundingType) {
        this.fundingType = fundingType;
    }
    public String getFundingId() {
        return fundingId;
    }
    public void setFundingId(String fundingId) {
        this.fundingId = fundingId;
    }
    public String getFundingSource() {
        return fundingSource;
    }
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
