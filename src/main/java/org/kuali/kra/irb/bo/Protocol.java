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
package org.kuali.kra.irb.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.service.ProtocolLocationService;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.service.UnitService;
import org.springframework.util.StringUtils;

/**
 * 
 * This class is Protocol Business Object.
 */
public class Protocol extends KraPersistableBusinessObjectBase{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1461551957662921433L;
    private Long protocolId; 
    private String protocolNumber; 
    private Integer sequenceNumber; 
    private String protocolTypeCode; 
    private String protocolStatusCode; 
    private String title; 
    private String description; 
    private Date applicationDate; 
    private Date approvalDate; 
    private Date expirationDate; 
    private Date lastApprovalDate; 
    private String fdaApplicationNumber; 
    private String referenceNumber1; 
    private String referenceNumber2; 
    private Boolean billable; 
    private String specialReviewIndicator; 
    private String vulnerableSubjectIndicator; 
    private String keyStudyPersonIndicator; 
    private String fundingSourceIndicator; 
    private String correspondentIndicator; 
    private String referenceIndicator; 
    private String relatedProjectsIndicator; 
    private ProtocolDocument protocolDocument;
    
    private ProtocolStatus protocolStatus; 
    private ProtocolType protocolType; 

    private List<ProtocolRiskLevel> protocolRiskLevels;
    private List<ProtocolParticipant> protocolParticipants;
    
    private List<ProtocolResearchArea> protocolResearchAreas;
    
    private List<ProtocolReference> protocolReferences;
    private List<ProtocolLocation> protocolLocations;
    
    //Is transient, used for lookup select option in UI by KNS 
    private String newDescription;
    
    private boolean nonEmployeeFlag;

    private List<ProtocolInvestigator> protocolInvestigators; 
    private String principalInvestigatorId;
    private String principalInvestigatorName;
    private String personId;
    private String rolodexId;
    private String leadUnitNumber;
    private String leadUnitName;
    
    private String lookupUnitNumber;
    
    private List<ProtocolPerson> protocolPersons; 
    /**
     * 
     * Constructs an Protocol BO.
     */
    public Protocol() {
        super();
        billable = false;
        protocolRiskLevels = new ArrayList<ProtocolRiskLevel>();
        protocolParticipants = new TypedArrayList(ProtocolParticipant.class);
        protocolResearchAreas = new ArrayList<ProtocolResearchArea>();// new TypedArrayList(ProtocolResearchAreas.class);
        protocolReferences = new ArrayList<ProtocolReference>(); //ArrayList<ProtocolReference>();
        newDescription = getDefaultNewDescription();
        protocolInvestigators = new ArrayList<ProtocolInvestigator>();
        protocolStatus = new ProtocolStatus();
        protocolStatusCode = protocolStatus.getProtocolStatusCode();
        protocolLocations = new ArrayList<ProtocolLocation>(); //ArrayList<ProtocolLocation>();
        protocolPersons = new ArrayList<ProtocolPerson>(); //ArrayList<ProtocolPerson>();
        initializeProtocolLocation();
        // set statuscode default
        setProtocolStatusCode(Constants.DEFAULT_PROTOCOL_STATUS_CODE);
        this.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getProtocolTypeCode() {
        return protocolTypeCode;
    }

    public void setProtocolTypeCode(String protocolTypeCode) {
        this.protocolTypeCode = protocolTypeCode;
    }

    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(Date lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    public String getFdaApplicationNumber() {
        return fdaApplicationNumber;
    }

    public void setFdaApplicationNumber(String fdaApplicationNumber) {
        this.fdaApplicationNumber = fdaApplicationNumber;
    }

    public String getReferenceNumber1() {
        return referenceNumber1;
    }

    public void setReferenceNumber1(String referenceNumber1) {
        this.referenceNumber1 = referenceNumber1;
    }

    public String getReferenceNumber2() {
        return referenceNumber2;
    }

    public void setReferenceNumber2(String referenceNumber2) {
        this.referenceNumber2 = referenceNumber2;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }

    public String getVulnerableSubjectIndicator() {
        return vulnerableSubjectIndicator;
    }

    public void setVulnerableSubjectIndicator(String vulnerableSubjectIndicator) {
        this.vulnerableSubjectIndicator = vulnerableSubjectIndicator;
    }

    public String getKeyStudyPersonIndicator() {
        return keyStudyPersonIndicator;
    }

    public void setKeyStudyPersonIndicator(String keyStudyPersonIndicator) {
        this.keyStudyPersonIndicator = keyStudyPersonIndicator;
    }

    public String getFundingSourceIndicator() {
        return fundingSourceIndicator;
    }

    public void setFundingSourceIndicator(String fundingSourceIndicator) {
        this.fundingSourceIndicator = fundingSourceIndicator;
    }

    public String getCorrespondentIndicator() {
        return correspondentIndicator;
    }

    public void setCorrespondentIndicator(String correspondentIndicator) {
        this.correspondentIndicator = correspondentIndicator;
    }

    public String getReferenceIndicator() {
        return referenceIndicator;
    }

    public void setReferenceIndicator(String referenceIndicator) {
        this.referenceIndicator = referenceIndicator;
    }

    public String getRelatedProjectsIndicator() {
        return relatedProjectsIndicator;
    }

    public void setRelatedProjectsIndicator(String relatedProjectsIndicator) {
        this.relatedProjectsIndicator = relatedProjectsIndicator;
    }

    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();        
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("protocolTypeCode", getProtocolTypeCode());
        hashMap.put("protocolStatusCode", getProtocolStatusCode());
        hashMap.put("title", getTitle());
        hashMap.put("description", getDescription());
        hashMap.put("applicationDate", getApplicationDate());
        hashMap.put("approvalDate", getApprovalDate());
        hashMap.put("expirationDate", getExpirationDate());
        hashMap.put("lastApprovalDate", getLastApprovalDate());
        hashMap.put("fdaApplicationNumber", getFdaApplicationNumber());
        hashMap.put("referenceNumber1", getReferenceNumber1());
        hashMap.put("referenceNumber2", getReferenceNumber2());
        hashMap.put("isBillable", isBillable());
        hashMap.put("specialReviewIndicator", getSpecialReviewIndicator());
        hashMap.put("vulnerableSubjectIndicator", getVulnerableSubjectIndicator());
        hashMap.put("keyStudyPersonIndicator", getKeyStudyPersonIndicator());
        hashMap.put("fundingSourceIndicator", getFundingSourceIndicator());
        hashMap.put("correspondentIndicator", getCorrespondentIndicator());
        hashMap.put("referenceIndicator", getReferenceIndicator());
        hashMap.put("relatedProjectsIndicator", getRelatedProjectsIndicator());
        return hashMap;
    }

    public ProtocolStatus getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(ProtocolStatus protocolStatus) {
        this.protocolStatus = protocolStatus;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public List<ProtocolRiskLevel> getProtocolRiskLevels() {
        return protocolRiskLevels;
    }

    public void setProtocolRiskLevels(List<ProtocolRiskLevel> protocolRiskLevels) {
        this.protocolRiskLevels = protocolRiskLevels;
    }
    
    public List<ProtocolParticipant> getProtocolParticipants() {
        return protocolParticipants;
    }

    public void setProtocolParticipants(List<ProtocolParticipant> protocolParticipants) {
        this.protocolParticipants = protocolParticipants;
    }
    
    /**
     * Gets index i from the protocol participant list.
     * 
     * @param index
     * @return protocol participant at index i
     */
    public ProtocolParticipant getProtocolParticipant(int index) {
        return getProtocolParticipants().get(index);
    }

    public String getNewDescription() {
        return newDescription;
    }
    
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getDefaultNewDescription() {
        return "(select)";
    }
    public void setProtocolResearchAreas(List<ProtocolResearchArea> protocolResearchAreas) {
        this.protocolResearchAreas = protocolResearchAreas;
    }

    public List<ProtocolResearchArea> getProtocolResearchAreas() {
        return protocolResearchAreas;
    }
    
    public void addProtocolResearchAreas(ProtocolResearchArea protocolResearchArea) {
        getProtocolResearchAreas().add(protocolResearchArea);
    }

    public ProtocolResearchArea getProtocolResearchAreas(int index) {
        while (getProtocolResearchAreas().size() <= index) {
            getProtocolResearchAreas().add(new ProtocolResearchArea());
        }
        return (ProtocolResearchArea) getProtocolResearchAreas().get(index);
    }

    public void setProtocolReferences(List<ProtocolReference> protocolReferences) {
        this.protocolReferences = protocolReferences;
    }

    public List<ProtocolReference> getProtocolReferences() {
        return protocolReferences;
    }

    public ProtocolDocument getProtocolDocument() {
        return protocolDocument;
    }

    public void setProtocolDocument(ProtocolDocument protocolDocument) {
        this.protocolDocument = protocolDocument;
    }

    public void setProtocolLocations(List<ProtocolLocation> protocolLocations) {
        this.protocolLocations = protocolLocations;
    }

    public List<ProtocolLocation> getProtocolLocations() {
        return protocolLocations;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(this.protocolResearchAreas);
        managedLists.add(this.protocolReferences);
        managedLists.add(this.protocolInvestigators);
        managedLists.add(getProtocolLocations());
        return managedLists;

    }

    public List<ProtocolInvestigator> getProtocolInvestigators() {
        return protocolInvestigators;
    }

    public void setProtocolInvestigators(List<ProtocolInvestigator> protocolInvestigators) {
        this.protocolInvestigators = protocolInvestigators;
    }

    /**
     * This method is to find Principal Investigator from ProtocolPerson list
     * @return ProtocolPerson
     */
    public ProtocolPerson getPrincipalInvestigator() {
        ProtocolPerson principalInvestigator = null;
        for ( ProtocolPerson investigator : getProtocolPersons() ) {
            if (investigator.getProtocolPersonRoleId().equalsIgnoreCase(Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                principalInvestigator = investigator;
            }
        }
        return principalInvestigator;
    }
    
    public ProtocolInvestigator getPI() {
        ProtocolInvestigator principalInvestigator = null;
        for ( ProtocolInvestigator investigator : getProtocolInvestigators() ) {
            if (investigator.getPrincipalInvestigatorFlag()) {
                principalInvestigator = investigator;
            }
        }
        return principalInvestigator;
    }


    public ProtocolUnit getLeadUnit() {
        ProtocolUnit leadUnit = null;
        if (getPrincipalInvestigator() != null) {
            for ( ProtocolUnit unit : getPrincipalInvestigator().getProtocolUnits() ) {
                if (unit.getLeadUnitFlag()) {
                    leadUnit = unit;
                }
            }
        }
        return leadUnit;
    }    

    public String getLeadUnitName() {
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }
    
    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;

        refreshLeadUnitName(leadUnitNumber);
        updateLeadUnitInPI(leadUnitNumber);    
    }
    
    private void refreshLeadUnitName(String leadUnitNumber) {        
        setLeadUnitName(KraServiceLocator.getService(UnitService.class).getUnitName(leadUnitNumber));
    }
    
    private void updateLeadUnitInPI(String leadUnitNumber)  {
        if (StringUtils.hasText(leadUnitNumber)) {
            //ProtocolInvestigator principal = getPrincipalInvestigator();
            ProtocolPerson principal = getPrincipalInvestigator();
            if (principal != null) {
                if (principal.getLeadUnit() != null) {
                    principal.getLeadUnit().setUnitNumber(leadUnitNumber); 
                    principal.getLeadUnit().setPersonId(getPrincipalInvestigator().getPersonId());
                } else {
                    ProtocolUnit leadUnit = new ProtocolUnit();
                    leadUnit.setUnitNumber(leadUnitNumber);
                    leadUnit.setPersonId(getPrincipalInvestigator().getPersonId());
                    leadUnit.refreshReferenceObject("unit");
                    leadUnit.setLeadUnitFlag(true); 
                    getPrincipalInvestigator().getProtocolUnits().add(leadUnit);
                    
                }
            }
        }
    }



    public String getPrincipalInvestigatorId() {       
        return principalInvestigatorId;
    }

    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
        updatePrincipalInvestigator(principalInvestigatorId);
    }
    
    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }

    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    public boolean isNonEmployeeFlag() {
 /*       //refresh nonEmpFrom PI
        if (getPrincipalInvestigator() != null) {
            this.nonEmployeeFlag = getPrincipalInvestigator().getNonEmployeeFlag();
        }*/
        return this.nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
      //  boolean changed = this.nonEmployeeFlag != nonEmployeeFlag;
        this.nonEmployeeFlag = nonEmployeeFlag;
        
/*        // If this flag changes, we have to update the PI name as we're dealing w/ rolodex versus person
        if (changed) {
            setPrincipalInvestigatorId(this.principalInvestigatorId);
        }   */     
    }
   
    
    private void updatePrincipalInvestigator(String principalInvestigatorId) {
        //ProtocolInvestigator pi = getPrincipalInvestigator();
        ProtocolPerson pi = getPrincipalInvestigator();
        if (pi !=null ) {
            pi.setPersonId(principalInvestigatorId);
            pi.setPersonNameFromId(principalInvestigatorId, nonEmployeeFlag);
            updateLeadUnitInPI(getLeadUnitNumber());
            //pi.setNonEmployeeFlag(this.nonEmployeeFlag);

        } else {
            if (principalInvestigatorId != null) {
                //pi = new ProtocolInvestigator();
                pi = new ProtocolPerson();
                pi.setPersonId(principalInvestigatorId);
                pi.setPersonNameFromId(principalInvestigatorId, nonEmployeeFlag);
                pi.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                pi.setProtocolNumber("0");
                pi.setSequenceNumber(0);
                pi.refreshReferenceObject("protocolPersonRole");
                if(nonEmployeeFlag) {
                    pi.refreshReferenceObject("rolodex");
                }else {
                    pi.refreshReferenceObject("person");
                }
                //pi.setPrincipalInvestigatorFlag(true);
                updateLeadUnitInPI(getLeadUnitNumber());
                //pi.setNonEmployeeFlag(this.nonEmployeeFlag);
                //protocolInvestigators.add(pi);
                getProtocolPersons().add(pi);
            }
        }
        setPrincipalInvestigatorName( pi.getPersonName());

    }
    
    public void resolvePrincipalInvestigator() {
        //ProtocolInvestigator principal = getPrincipalInvestigator();
        ProtocolPerson principal = getPrincipalInvestigator();
        if (principal != null ) {
            //this.nonEmployeeFlag = principal.getNonEmployeeFlag();
            this.nonEmployeeFlag = principal.getPersonId() == null ? true : false;
            setPrincipalInvestigatorId(principal.getPersonId());
            setPrincipalInvestigatorName(principal.getPersonName());

            if (principal.getLeadUnit() != null) {
                setLeadUnitNumber(principal.getLeadUnit().getUnitNumber()); 
            }
        }
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
        if (StringUtils.hasText(personId)) {
            setNonEmployeeFlag(false);
            setRolodexId(null);
            setPrincipalInvestigatorId(personId);
        }
    }

    public String getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(String rolodexId) {
        this.rolodexId = rolodexId;
        if (StringUtils.hasText(rolodexId)) {
            setNonEmployeeFlag(true);
            setPersonId(null);
            setPrincipalInvestigatorId(rolodexId);

        }
    }

    public String getLookupUnitNumber() {
        return lookupUnitNumber;
    }

    public void setLookupUnitNumber(String lookupUnitNumber) {
        this.lookupUnitNumber = lookupUnitNumber;
        if (lookupUnitNumber != null && !StringUtils.hasText(leadUnitNumber)) {
            leadUnitNumber = lookupUnitNumber;
        }
    }

    /**
     * This method is to get protocol location service
     * @return ProtocolLocationService
     */
    private ProtocolLocationService getProtocolLocationService() {
        ProtocolLocationService protocolLocationService = (ProtocolLocationService)KraServiceLocator.getService("protocolLocationService");
        return protocolLocationService;
    }

    /*
     * Initialize protocol location.
     * Add default organization.
     */
    private void initializeProtocolLocation() {
        getProtocolLocationService().addDefaultProtocolLocation(this);
    }
    
    public List<ProtocolPerson> getProtocolPersons() {
        return protocolPersons;
    }

    public void setProtocolPersons(List<ProtocolPerson> protocolPersons) {
        this.protocolPersons = protocolPersons;
    }

    /**
     * Gets index i from the protocol person list.
     * 
     * @param index
     * @return protocol person at index i
     */
    public ProtocolPerson getProtocolPerson(int index) {
        return getProtocolPersons().get(index);
    }

}
