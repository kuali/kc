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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class represents a congressional district. A congressional district consists of a two-letter
 * state code and a district number, although it is represented by a single string.
 */
public class CongressionalDistrict extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 9043098848918407500L;
    
    private String proposalNumber;
    private Integer siteNumber;
    private String congressionalDistrict;
    
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setSiteNumber(Integer siteNumber) {
        this.siteNumber = siteNumber;
    }

    public Integer getSiteNumber() {
        return siteNumber;
    }

    public String getCongressionalDistrict() {
        return congressionalDistrict;
    }
    
    public void setCongressionalDistrict(String stateCode, String districtNumber) {
        setCongressionalDistrict(stateCode + "-" + districtNumber);
    }

    public void setCongressionalDistrict(String congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
    }

    @Override
    protected LinkedHashMap<String, ?> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("siteNumber", this.getSiteNumber());
        hashMap.put("congressionalDistrict", this.getCongressionalDistrict());
        return hashMap;
    }

}
