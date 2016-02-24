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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
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

    private String documentNumber;

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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

public String toString() {
    return "Proposal: sponsorCode = " + sponsorCode
    + ", proposalNumber = " + proposalNumber
    + ", documentNumber = " + documentNumber 
    + ", title = " + title 
    + ", unit = " + ownedByUnitNumber
    + ", type = " + proposalTypeCode
    + ", deadline = " + deadlineDate;
}
}
