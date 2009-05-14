/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.irb.protocol.ProtocolFundingSource;

public class FundingSourceType extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private Integer fundingSourceTypeCode;
    private String description;
    private boolean fundingSourceTypeFlag;


    public FundingSourceType() {

    }

    public Integer getFundingSourceTypeCode() {
        return fundingSourceTypeCode;
    }

    public void setFundingSourceTypeCode(Integer fundingSourceTypeCode) {
        this.fundingSourceTypeCode = fundingSourceTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getFundingSourceTypeFlag() {
        return fundingSourceTypeFlag;
    }

    public void setFundingSourceTypeFlag(boolean fundingSourceTypeFlag) {
        this.fundingSourceTypeFlag = fundingSourceTypeFlag;
    }


    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("fundingSourceTypeCode", getFundingSourceTypeCode());
        hashMap.put("description", getDescription());
        hashMap.put("fundingSourceTypeFlag", getFundingSourceTypeFlag());
        return hashMap;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean isEqual=true;
        if (obj==null || !(obj instanceof FundingSourceType)) {
             return false;
        }
        
        FundingSourceType fundingSourceType = (FundingSourceType) obj;
        if (!fundingSourceType.getDescription().equalsIgnoreCase(getDescription())) {
            isEqual=false;
        } else if (fundingSourceType.getFundingSourceTypeCode().intValue() != getFundingSourceTypeCode().intValue()) {
            isEqual=false;
        } else if (fundingSourceType.getFundingSourceTypeFlag() != getFundingSourceTypeFlag()) {
            isEqual=false;
        }
        return isEqual;
    }
        
    @Override
    public int hashCode() {
          final int PRIME = 31;
          int result = 1;
          result = PRIME * result + ((this.getDescription() == null) ? 0 : this.getDescription().hashCode());
          result = PRIME * result + ((this.getFundingSourceTypeCode() == null) ? 0 : this.getFundingSourceTypeCode().hashCode());
          return result;
    }

}