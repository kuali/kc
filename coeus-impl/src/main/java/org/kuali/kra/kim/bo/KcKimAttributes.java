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
package org.kuali.kra.kim.bo;

import org.kuali.coeus.common.framework.unit.Unit;
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
    public static final String SUBAWARD = "subAward";
    public static final String SUB_QUALIFIER = "subQualifier";
    public static final String DOCUMENT_NUMBER = "documentNumber";
    public static final String CLASS_NAME = "className";
    
    protected String proposal;
    protected String protocol;
    protected String committee;
    protected String award;
    protected String timeandmoney;
    protected String unitNumber;
    protected Boolean subunits;
    protected String negotiation;
    protected String subAward;
    protected String sectionName;
    protected String documentAction;
    protected String className;
    
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
    
    public Boolean getSubunits() {
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

    public String getSubAward() {
        return subAward;
    }

    public void setSubAward(String subAward) {
        this.subAward = subAward;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
