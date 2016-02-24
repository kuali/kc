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
package org.kuali.kra.negotiations.bo;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class handles the attributes needed for an unassociated negotiation.
 */
public class NegotiationUnassociatedDetail extends KcPersistableBusinessObjectBase implements Negotiable {
    

    private static final long serialVersionUID = 989159429578390915L;
    private Long negotiationUnassociatedDetailId;
    private Long negotiationId;
    private String title;
    private String piPersonId;
    private String piRolodexId;
    private String piName;
    private String leadUnitNumber;
    private String sponsorCode;
    private String primeSponsorCode;
    private String sponsorAwardNumber;
    private String contactAdminPersonId;
    private String subAwardOrganizationId;
    
    private transient String negotiableProposalTypeCode;
    
    private Negotiation negotiation;
    private Unit leadUnit;
    private Sponsor sponsor;
    private Sponsor primeSponsor;
    private Organization subAwardOrganization;
    private Rolodex pINonEmployee;

    //transient
    private String piEmployeeUserName;
    private String contactAdminUserName;

    private transient KcPersonService kcPersonService;
    

    public NegotiationUnassociatedDetail() {
        super();
    }
    
    
    protected void prePersist() {
        super.prePersist();
        setPiName();
    }
    
    protected void preUpdate() {
        super.preUpdate();
        setPiName();
    }
    
    
    protected void setPiName() {
        if (getPIEmployee() != null) {
            setPiName(getPIEmployee().getFullName());
        } else if (getPINonEmployee() != null) {
            setPiName(this.getPINonEmployee().getFullName());
        }        
    }
    

    public Long getNegotiationUnassociatedDetailId() {
        return negotiationUnassociatedDetailId;
    }



    public void setNegotiationUnassociatedDetailId(Long negotiationUnassociatedDetailId) {
        this.negotiationUnassociatedDetailId = negotiationUnassociatedDetailId;
    }



    public Long getNegotiationId() {
        return negotiationId;
    }



    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }



    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public String getPiPersonId() {
        return piPersonId;
    }



    public void setPiPersonId(String piPersonId) {
        this.piPersonId = piPersonId;
    }



    public String getPiRolodexId() {
        return piRolodexId;
    }



    public void setPiRolodexId(String piRolodexId) {
        this.piRolodexId = piRolodexId;
    }



    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }



    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }



    public String getSponsorCode() {
        return sponsorCode;
    }



    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }



    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }



    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }



    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }



    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }



    public String getContactAdminPersonId() {
        return contactAdminPersonId;
    }



    public void setContactAdminPersonId(String contactAdminPersonId) {
        this.contactAdminPersonId = contactAdminPersonId;
    }



    public String getSubAwardOrganizationId() {
        return subAwardOrganizationId;
    }



    public void setSubAwardOrganizationId(String subAwardOrganizationId) {
        this.subAwardOrganizationId = subAwardOrganizationId;
    }



    public Negotiation getNegotiation() {
        return negotiation;
    }



    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }



    public Unit getLeadUnit() {
        return leadUnit;
    }



    public void setLeadUnit(Unit leadUnit) {
        this.leadUnit = leadUnit;
    }



    public Sponsor getSponsor() {
        return sponsor;
    }



    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }



    public Sponsor getPrimeSponsor() {
        return primeSponsor;
    }



    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
    }



    public Organization getSubAwardOrganization() {
        return subAwardOrganization;
    }



    public void setSubAwardOrganization(Organization subAwardOrganization) {
        this.subAwardOrganization = subAwardOrganization;
    }

    public KcPerson getPIEmployee() {
        if (this.getPiPersonId() == null) {
            return null;
        }
        else {
            return getKcPersonService().getKcPersonByPersonId(this.getPiPersonId());
        }
    }
    
    public Rolodex getPINonEmployee() {
        return pINonEmployee;
    }

    public void setPINonEmployee(Rolodex pINonEmployee) {
        this.pINonEmployee = pINonEmployee;
    }

    public KcPerson getContactAdmin() {
        if (this.getContactAdminPersonId() == null) {
            return null;
        }
        else {
            return getKcPersonService().getKcPersonByPersonId(this.getContactAdminPersonId());
        }
    }
    
    @Override
    public String getLeadUnitName() {
        String name = getLeadUnit() == null ? EMPTY_STRING : getLeadUnit().getUnitName();
        return name;
    }

    @Override
    public String getPiName() {
        return piName;
    }
    
    public void setPiName(String piName) {
        this.piName = piName;
    }


    @Override
    public String getPiEmployeeName() {
        String name = getPIEmployee() == null ? EMPTY_STRING : getPIEmployee().getFullName();
        return name;
    }



    @Override
    public String getPiNonEmployeeName() {
        String name = getPINonEmployee() == null ? EMPTY_STRING : getPINonEmployee().getFullName();
        return name;
    }



    @Override
    public String getAdminPersonName() {
        String name = getContactAdmin() == null ? EMPTY_STRING : getContactAdmin().getFullName();
        return name;
    }



    @Override
    public String getSponsorName() {
        String name = getSponsor() == null ? EMPTY_STRING : getSponsor().getSponsorName();
        return name;
    }



    @Override
    public String getPrimeSponsorName() {
        String name = getPrimeSponsor() == null ? EMPTY_STRING : getPrimeSponsor().getSponsorName();
        return name;
    }



    @Override
    public String getSubAwardOrganizationName() {
        String name = getSubAwardOrganization() == null ? EMPTY_STRING : getSubAwardOrganization().getOrganizationName();
        return name;
    }
    
    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        List<NegotiationPersonDTO> kcPeople = new ArrayList<NegotiationPersonDTO>();
        if (this.getPIEmployee() != null) {
            kcPeople.add(new NegotiationPersonDTO(this.getPIEmployee(), Constants.PRINCIPAL_INVESTIGATOR_ROLE));
        }
        return kcPeople;
    }



    public String getNegotiableProposalTypeCode() {
        return negotiableProposalTypeCode;
    }



    public void setNegotiableProposalTypeCode(String negotiableProposalTypeCode) {
        this.negotiableProposalTypeCode = negotiableProposalTypeCode;
    }



    @Override
    public String getAssociatedDocumentId() {
        if (getNegotiationUnassociatedDetailId() != null) {
            return getNegotiationUnassociatedDetailId().toString();
        } else {
            return EMPTY_STRING;
        }
    }


    @Override
    public ProposalType getNegotiableProposalType() {
        return null;
    }

    public String getPiEmployeeUserName() {
        KcPerson pi = getPIEmployee();
        if (pi == null) {
            return piEmployeeUserName;
        }
        else {
            return pi.getUserName();
        }
    }


    public void setPiEmployeeUserName(String piEmployeeUserName) {
        this.piEmployeeUserName = piEmployeeUserName;
        KcPerson pi = null;
        try {
            pi = getKcPersonService().getKcPersonByUserName(piEmployeeUserName);
        }
        catch (IllegalArgumentException e) {
            // invalid username, will be caught by validation routines
        }
        if (pi != null) {
            setPiPersonId(pi.getPersonId());
        }
        else {
            setPiPersonId(null);
        }
    }


    public String getContactAdminUserName() {
        KcPerson admin = getContactAdmin();
        if (admin == null) {
            return contactAdminUserName;
        } else {
            return admin.getUserName();
        }
    }


    public void setContactAdminUserName(String adminUserName) {
        this.contactAdminUserName = adminUserName;
        KcPerson admin = null;
        try {
            admin = getKcPersonService().getKcPersonByUserName(adminUserName);
        }
        catch (IllegalArgumentException e) {
            // invalid username, will be caught by validation routines
        }
        if (admin != null) {
            setContactAdminPersonId(admin.getPersonId());
        }
        else {
            setContactAdminPersonId(null);
        }
    }

    @Override
    public String getSubAwardRequisitionerName() {
        return EMPTY_STRING;
    }


    @Override
    public String getSubAwardRequisitionerUnitNumber() {
        return EMPTY_STRING;
    }


    @Override
    public String getSubAwardRequisitionerUnitName() {
        return EMPTY_STRING;
    }


    @Override
    public String getSubAwardRequisitionerId() {
        return EMPTY_STRING;
    }

    /**
     * Looks up and returns the KcPersonService.
     * @return the person service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
