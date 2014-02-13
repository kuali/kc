/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.List;

/**
 * 
 * This class is to be used as a read only implementation of ProposalDevelopmentDocument since
 * transactional documents can't set lookupDefinition in the DD framework.
 * 
 */
public class LookupableDevelopmentProposal extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Sponsor sponsor;

    private String sponsorCode;

    private String proposalNumber;

    private String title;

    private String ownedByUnitNumber;

    private Unit ownedByUnit;

    private String proposalTypeCode;

    private ProposalType proposalType;

    private Date deadlineDate;

    private List<ProposalPerson> proposalPersons;

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getSponsorName() {
        if (getSponsor() != null) {
            return getSponsor().getSponsorName();
        }
        return null;
    }

    public Sponsor getSponsor() {
        if (sponsor == null && !StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject("sponsor");
        }
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnedByUnitNumber() {
        return ownedByUnitNumber;
    }

    public void setOwnedByUnitNumber(String ownedByUnit) {
        this.ownedByUnitNumber = ownedByUnit;
    }

    public Unit getOwnedByUnit() {
        return ownedByUnit;
    }

    public void setOwnedByUnit(Unit ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    public String getProposalTypeDescription() {
        if (getProposalType() == null && getProposalTypeCode() != null) {
            refreshReferenceObject("proposalType");
        }
        if (getProposalType() != null) {
            return getProposalType().getDescription();
        }
        return null;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getOwnedByUnitName() {
        Unit unit = getOwnedByUnit();
        return unit != null ? unit.getUnitName() : null;
    }

    public ProposalType getProposalType() {
        if (proposalType == null && proposalTypeCode != null) {
            proposalType = KcServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(ProposalType.class, proposalTypeCode);
        }
        return proposalType;
    }

    public String getInvestigator() {
        ProposalPerson principalInvestigator = null;
        for (ProposalPerson person : proposalPersons) {
            if (StringUtils.equals(person.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                principalInvestigator = person;
                break;
            }
        }
        return principalInvestigator == null ? "" : principalInvestigator.getFullName();
    }

    public List<ProposalPerson> getProposalPersons() {
        return this.proposalPersons;
    }

}
