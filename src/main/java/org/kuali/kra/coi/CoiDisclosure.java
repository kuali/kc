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
package org.kuali.kra.coi;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.disclosure.CoiDisclEventProject;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.coi.disclosure.DisclosurePerson;
import org.kuali.kra.coi.disclosure.DisclosurePersonUnit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.DateUtils;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class is the main bo class of Coi disclosure
 */
public class CoiDisclosure extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1056040995591476518L;
    private static final String DISPOSITION_PENDING = "3";
    private static final String DISCLOSURE_PENDING = "100";
    public static final String PROPOSAL_DISCL_MODULE_CODE = "11";
    public static final String PROTOCOL_DISCL_MODULE_CODE = "12";
    public static final String AWARD_DISCL_MODULE_CODE = "1";

    private Long coiDisclosureId; 
    private String coiDisclosureNumber; 
    private Integer sequenceNumber; 
    private String personId; 
    private String certificationText; 
    private String certifiedBy; 
    private Date certificationTimestamp; 
    private String disclosureDispositionCode; 
    private String disclosureStatusCode; 
    private Date expirationDate; 
    private String moduleCode; 
    private String reviewStatusCode; 
    private Integer discActiveStatus; 
    private CoiDisclosureDocument coiDisclosureDocument;
    private List<DisclosurePerson> disclosurePersons;
    
    private transient ParameterService parameterService;
    private transient boolean certifiedFlag;

    private static final String DISCLOSURE_CERT_STMT = "COI_CERTIFICATION_STATEMENT";
    private static final String DISCLOSURE_CERT_ACK  = "COI_CERTIFICATION_ACKNOWLEDGEMENT";
    
    private transient String certificationStatement;
    private transient String acknowledgementStatement;

    // dateFormatter here is thread safe.
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

//    private CoiStatus coiStatus; 
//    private CoiDispositionStatus coiDispositionStatus; 
    private List<CoiDisclProject> coiDisclProjects; 
    private List<CoiDiscDetail> coiDiscDetails; 
//    private CoiDocuments coiDocuments; 
//    private CoiNotepad coiNotepad; 
//    private CoiUserRoles coiUserRoles; 

    // help UI purposes
    private transient List<CoiDisclEventProject> coiDisclEventProjects; 

    public CoiDisclosure() { 
//        disclosurePersons = new ArrayList<DisclosurePerson>();
//        DisclosurePerson newDisclosurePerson = new DisclosurePerson();
//        newDisclosurePerson.setCoiDisclosure(this);
//        disclosurePersons.add(newDisclosurePerson);
        this.setSequenceNumber(1);
        initCoiDisclosureNumber();
        getDisclosureReporter();

    } 
    
    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCertificationText() {
        return certificationText;
    }

    public void setCertificationText(String certificationText) {
        this.certificationText = certificationText;
    }

    public String getCertifiedBy() {
        return certifiedBy;
    }

    public void setCertifiedBy(String certifiedBy) {
        this.certifiedBy = certifiedBy;
    }

    public Date getCertificationTimestamp() {
        return certificationTimestamp;
    }

    public String getCertificationTimestampString() {
        if (getCertificationTimestamp() == null) {
            return dateFormatter.format(new Date(Calendar.getInstance().getTimeInMillis()));
        }
        return dateFormatter.format(getCertificationTimestamp());
    }
    
    public void setCertificationTimestamp(Date certificationTimestamp) {
        this.certificationTimestamp = certificationTimestamp;
    }

    public void certifyDisclosure() {
        certifiedFlag = true;
        setCertificationTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
        certificationText = new String(certificationStatement);
//TODO: Might need to save disclosure here...
    }

    public String getDisclosureDispositionCode() {
        return disclosureDispositionCode;
    }

    public void setDisclosureDispositionCode(String disclosureDispositionCode) {
        this.disclosureDispositionCode = disclosureDispositionCode;
    }

    public String getDisclosureStatusCode() {
        return disclosureStatusCode;
    }

    public void setDisclosureStatusCode(String disclosureStatusCode) {
        this.disclosureStatusCode = disclosureStatusCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getReviewStatusCode() {
        return reviewStatusCode;
    }

    public void setReviewStatusCode(String reviewStatusCode) {
        this.reviewStatusCode = reviewStatusCode;
    }

    public Integer getDiscActiveStatus() {
        return discActiveStatus;
    }

    public void setDiscActiveStatus(Integer discActiveStatus) {
        this.discActiveStatus = discActiveStatus;
    }

//    public CoiStatus getCoiStatus() {
//        return coiStatus;
//    }
//
//    public void setCoiStatus(CoiStatus coiStatus) {
//        this.coiStatus = coiStatus;
//    }
//
//    public CoiDispositionStatus getCoiDispositionStatus() {
//        return coiDispositionStatus;
//    }
//
//    public void setCoiDispositionStatus(CoiDispositionStatus coiDispositionStatus) {
//        this.coiDispositionStatus = coiDispositionStatus;
//    }
//
//    public CoiDisclProjects getCoiDisclProjects() {
//        return coiDisclProjects;
//    }
//
//    public void setCoiDisclProjects(CoiDisclProjects coiDisclProjects) {
//        this.coiDisclProjects = coiDisclProjects;
//    }
//
//    public CoiDiscDetails getCoiDiscDetails() {
//        return coiDiscDetails;
//    }
//
//    public void setCoiDiscDetails(CoiDiscDetails coiDiscDetails) {
//        this.coiDiscDetails = coiDiscDetails;
//    }
//
//    public CoiDocuments getCoiDocuments() {
//        return coiDocuments;
//    }
//
//    public void setCoiDocuments(CoiDocuments coiDocuments) {
//        this.coiDocuments = coiDocuments;
//    }
//
//    public CoiNotepad getCoiNotepad() {
//        return coiNotepad;
//    }
//
//    public void setCoiNotepad(CoiNotepad coiNotepad) {
//        this.coiNotepad = coiNotepad;
//    }
//
//    public CoiUserRoles getCoiUserRoles() {
//        return coiUserRoles;
//    }
//
//    public void setCoiUserRoles(CoiUserRoles coiUserRoles) {
//        this.coiUserRoles = coiUserRoles;
//    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("coiDisclosureId", this.getCoiDisclosureId());
        hashMap.put("coiDisclosureNumber", this.getCoiDisclosureNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("certificationText", this.getCertificationText());
        hashMap.put("certifiedBy", this.getCertifiedBy());
        hashMap.put("certificationTimestamp", this.getCertificationTimestamp());
        hashMap.put("disclosureDispositionCode", this.getDisclosureDispositionCode());
        hashMap.put("disclosureStatusCode", this.getDisclosureStatusCode());
        hashMap.put("expirationDate", this.getExpirationDate());
        hashMap.put("moduleCode", this.getModuleCode());
        hashMap.put("reviewStatusCode", this.getReviewStatusCode());
        hashMap.put("discActiveStatus", this.getDiscActiveStatus());
        return hashMap;
    }

    public CoiDisclosureDocument getCoiDisclosureDocument() {
        return coiDisclosureDocument;
    }

    public void setCoiDisclosureDocument(CoiDisclosureDocument coiDisclosureDocument) {
        this.coiDisclosureDocument = coiDisclosureDocument;
    }

    public List<DisclosurePerson> getDisclosurePersons() {
        return disclosurePersons;
    }

    public void setDisclosurePersons(List<DisclosurePerson> disclosurePersons) {
        this.disclosurePersons = disclosurePersons;
    }
    
    public DisclosurePerson getDisclosureReporter() {
        // TODO : what if the list is empty, then need to initialize
        if (CollectionUtils.isEmpty(disclosurePersons)) {
            disclosurePersons = new ArrayList<DisclosurePerson>();
            disclosurePersons.add(getCoiDisclosureService().getDisclosureReporter(GlobalVariables.getUserSession().getPrincipalId(), this.getCoiDisclosureId()));
        }

        return disclosurePersons.get(0);
    }
    
    public void initSelectedUnit() {
        int i = 0;
        for (DisclosurePersonUnit disclosurePersonUnit : disclosurePersons.get(0).getDisclosurePersonUnits()) {
            if (disclosurePersonUnit.isLeadUnitFlag()) {
                disclosurePersons.get(0).setSelectedUnit(i);
                break;
            }
            i++;
        }

    }
    
    private CoiDisclosureService getCoiDisclosureService() {
        return KraServiceLocator.getService(CoiDisclosureService.class);    
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        List<DisclosurePersonUnit> disclosurePersonUnits = new ArrayList<DisclosurePersonUnit>();
        
        for (DisclosurePerson disclosurePerson : getDisclosurePersons()) {
            disclosurePersonUnits.addAll(disclosurePerson.getDisclosurePersonUnits());
        }
        managedLists.add(disclosurePersonUnits);
        managedLists.add(getDisclosurePersons());
        return managedLists;
    }


    public void initRequiredFields() {
        this.setDisclosureDispositionCode(DISPOSITION_PENDING);
        this.setDisclosureStatusCode(DISCLOSURE_PENDING);
        this.setPersonId(this.getDisclosureReporter().getPersonId());
        initCoiDisclosureNumber();
        this.setExpirationDate(new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), 365).getTime()));

    }
    
    public void initCoiDisclosureNumber() {
        // TODO : not sure about disclosurenumber & expirationdate
        if (StringUtils.isBlank(this.getCoiDisclosureNumber())) {
            Long nextNumber = KraServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_COI_DISCL_NUMBER");
            this.setCoiDisclosureNumber(nextNumber.toString());
        }
        
    }
    
    public List<CoiDiscDetail> getCoiDiscDetails() {
        return coiDiscDetails;
    }

    public void setCoiDiscDetails(List<CoiDiscDetail> coiDiscDetails) {
        this.coiDiscDetails = coiDiscDetails;
    }

    public boolean getCertifiedFlag() {
        return certifiedFlag;
    }

    public void setCertifiedFlag(boolean certifiedFlag) {
        this.certifiedFlag = certifiedFlag;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    public String getCertificationStatement() {
        if (certificationStatement == null) {
            certificationStatement = getParameterService().getParameterValue(Constants.MODULE_NAMESPACE_COIDISCLOSURE, 
                       Constants.PARAMETER_COMPONENT_DOCUMENT, DISCLOSURE_CERT_STMT);
        }
        return certificationStatement;
    }

    public String getAcknowledgementStatement() {
        if (acknowledgementStatement == null) {
            acknowledgementStatement = getParameterService().getParameterValue(Constants.MODULE_NAMESPACE_COIDISCLOSURE, 
                       Constants.PARAMETER_COMPONENT_DOCUMENT, DISCLOSURE_CERT_ACK);
        }
        return acknowledgementStatement;
    }

    public List<CoiDisclProject> getCoiDisclProjects() {
        if (coiDisclProjects == null) {
            coiDisclProjects = new ArrayList<CoiDisclProject>();
        }
        return coiDisclProjects;
    }

    public void setCoiDisclProjects(List<CoiDisclProject> coiDisclProjects) {
        this.coiDisclProjects = coiDisclProjects;
    }

    public List<CoiDisclEventProject> getCoiDisclEventProjects() {
        return coiDisclEventProjects;
    }

    public void setCoiDisclEventProjects(List<CoiDisclEventProject> coiDisclEventProjects) {
        this.coiDisclEventProjects = coiDisclEventProjects;
    }

    public boolean isProposalEvent() {
        return StringUtils.equals(PROPOSAL_DISCL_MODULE_CODE, this.getModuleCode());
    }
    
    public boolean isProtocolEvent() {
        return StringUtils.equals(PROTOCOL_DISCL_MODULE_CODE, this.getModuleCode());
    }

    public boolean isAwardEvent() {
        return StringUtils.equals(AWARD_DISCL_MODULE_CODE, this.getModuleCode());
    }

}