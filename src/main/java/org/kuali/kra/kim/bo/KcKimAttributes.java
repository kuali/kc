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
package org.kuali.kra.kim.bo;

import org.kuali.kra.bo.Unit;
import org.kuali.rice.kim.bo.impl.KimAttributes;

public class KcKimAttributes extends KimAttributes {

    public static final String PROPOSAL = "proposal";
    public static final String PROTOCOL = "protocol";
    public static final String COMMITTEE = "committee";
    public static final String COMMITTEESCHEDULE = "committeeSchedule";
    public static final String AWARD = "award";
    public static final String TIMEANDMONEY = "timeandmoney";
    public static final String UNIT_NUMBER = "unitNumber";
    public static final String ORGANIZATION_ID = "organizationId";
    public static final String SUBUNITS = "subunits";
    public static final String NEGOTIATION = "negotiation";
    public static final String SUB_QUALIFIER = "subQualifier";
    
    protected String proposal;
    protected String protocol;
    protected String committee;
    protected String award;
    protected String timeandmoney;
    protected String unitNumber;
    protected Boolean subunits;
    protected String negotiation;
    protected String sectionName;
    protected String documentAction;
    
    protected Unit unit;
    
    public String getProposal() {
        return proposal;
    }
    
    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    public String getCommittee() {
        return committee;
    }
    
    public void setCommittee(String committee) {
        this.committee = committee;
    }
    
    public String getAward() {
        return award;
    }
    
    public void setAward(String award) {
        this.award = award;
    }
    
    public String getTimeandmoney() {
        return timeandmoney;
    }
    
    public void setTimeandmoney(String timeandmoney) {
        this.timeandmoney = timeandmoney;
    }
    
    public String getUnitNumber() {
        return unitNumber;
    }
    
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    
    public Boolean isSubunits() {
        return subunits;
    }
    
    public void setSubunits(Boolean subunits) {
        this.subunits = subunits;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDocumentAction() {
        return documentAction;
    }

    public void setDocumentAction(String documentAction) {
        this.documentAction = documentAction;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }
    
}
