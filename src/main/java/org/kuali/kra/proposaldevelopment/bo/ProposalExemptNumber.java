/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProposalExemptNumber extends KraPersistableBusinessObjectBase {
    private String proposalNumber;
    private Integer specialReviewNumber;
    private String exemptionTypeCode;
    private ExemptionType exemptionType;
    public String getProposalNumber() {
        return proposalNumber;
    }
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    public Integer getSpecialReviewNumber() {
        return specialReviewNumber;
    }
    public void setSpecialReviewNumber(Integer specialReviewNumber) {
        this.specialReviewNumber = specialReviewNumber;
    }
    public String getExemptionTypeCode() {
        return exemptionTypeCode;
    }
    public void setExemptionTypeCode(String exemptionTypeCode) {
        this.exemptionTypeCode = exemptionTypeCode;
    }
    public ExemptionType getExemptionType() {
        return exemptionType;
    }
    public void setExemptionType(ExemptionType exemptionType) {
        this.exemptionType = exemptionType;
    }
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("specialReviewNumber", getSpecialReviewNumber());
        hashMap.put("exemptionTypeCode", getExemptionTypeCode());
        return hashMap;
    }

}
