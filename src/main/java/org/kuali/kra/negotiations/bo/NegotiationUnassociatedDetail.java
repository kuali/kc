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
package org.kuali.kra.negotiations.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.service.RolodexService;

/**
 * 
 * This class handles the attributes needed for an unassociated negotiation.
 */
public class NegotiationUnassociatedDetail extends KraPersistableBusinessObjectBase implements Negotiable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
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
    
    //transient
    private String piEmployeeUserName;
    private String contactAdminUserName;
    
    /**
     * 
     * Constructs a NegotiationUnassociatedDetail.java.
     */
    public NegotiationUnassociatedDetail() {
        super();
    }
    
    
    public void beforeInsert(PersistenceBroker persistenceBroker) {
        super.beforeInsert(persistenceBroker);
        setPiName();
    }
    
    public void beforeUpdate(PersistenceBroker persistenceBroker) {
        super.beforeUpdate(persistenceBroker);
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



    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("NegotiationUnassociatedDetailId", this.getNegotiationUnassociatedDetailId());
        return map;
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
        if (this.getPiRolodexId() == null) {
            return null;
        } else {
            try {
                return getRolodexService().getRolodex(Integer.parseInt(this.getPiRolodexId()));
            } catch (Exception e) {
                return null;
            }
        }
    }
    
    protected RolodexService getRolodexService() {
            return KraServiceLocator.getService(RolodexService.class);        
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
}