/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.coi;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.coi.disclosure.DisclosurePerson;
import org.kuali.kra.coi.disclosure.DisclosurePersonUnit;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachmentFilter;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.coi.notification.CoiNotification;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * This class is the main bo class of Coi disclosure
 */
public class CoiDisclosure extends KcPersistableBusinessObjectBase implements SequenceOwner<CoiDisclosure>, Permissionable {
    


    private static final long serialVersionUID = 1056040995591476518L;
    
    public static final String MANUAL_DISCL_MODULE_CODE = "14";
    public static final String PROPOSAL_DISCL_MODULE_CODE = "11";
    public static final String INSTITUTIONAL_PROPOSAL_DISCL_MODULE_CODE = "15";
    public static final String PROTOCOL_DISCL_MODULE_CODE = "12";
    public static final String AWARD_DISCL_MODULE_CODE = "1";
    public static final String ANNUAL_DISCL_MODULE_CODE = "13";
    public static final String CERTIFIED = "certified";

    private Long coiDisclosureId; 
    private String coiDisclosureNumber; 
    private Integer sequenceNumber; 
    private String personId; 
    private String certificationText; 
    private String certifiedBy; 
    private Timestamp certificationTimestamp; 
    private String disclosureDispositionCode; 
    private String disclosureStatusCode; 
    private Date expirationDate; 
    private boolean currentDisclosure; 
    private boolean annualUpdate;
    private String eventTypeCode;
    private String moduleItemKey; 
    private Integer discActiveStatus;
    private CoiDisclosureDocument coiDisclosureDocument;
    private List<DisclosurePerson> disclosurePersons;
    private List<CoiDisclosureAttachment> coiDisclosureAttachments;
    private List<CoiDisclosureNotepad> coiDisclosureNotepads;
    private transient ParameterService parameterService;
    private transient boolean certifiedFlag;

    private static final String DISCLOSURE_CERT_STMT = "COI_CERTIFICATION_STATEMENT";
    private static final String DISCLOSURE_CERT_ACK  = "COI_CERTIFICATION_ACKNOWLEDGEMENT";
    private static final String SUBMIT_ACK_THANKYOU = "message.disclosure.submit.thankyou";
    
    private transient String certificationStatement;
    private transient String acknowledgementStatement;
    private static String submitThankyouStatement = null;
    
    // dateFormatter here is thread safe.
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

//    private CoiStatus coiStatus; 
    private CoiDisclosureStatus coiDisclosureStatus; 
    private CoiDispositionStatus coiDispositionStatus; 
    private CoiDisclosureEventType coiDisclosureEventType;
    
    // COI disclosure project 
    private transient String coiDisclProjectId;
    private transient String coiDisclProjectTitle;
    @SkipVersioning
    private List<CoiDisclProject> coiDisclProjects; 
    private List<CoiUserRole> coiUserRoles;

    // help UI purposes
    private transient DateTimeService dateTimeService;
    private CoiDisclosureAttachmentFilter newAttachmentFilter; 
    private KcPersistableBusinessObjectBase eventBo;

    // transient for award header label
    private transient String reporterCreated;
    private transient KcPersonService kcPersonService;
    private transient BusinessObjectService businessObjectService;
    private transient PersistenceService persistenceService;
    
    // must persist generated notifications
    List<CoiNotification> disclosureNotifications;

    
    private String reviewStatusCode; 
    private CoiReviewStatus coiReviewStatus; 
    
    // transient for header label
    private transient String disclosureStatusReviewStatus;
    
    public CoiDisclosure() { 
        super();

        this.setSequenceNumber(1);
        initCoiDisclosureNumber();
        getDisclosureReporter();
        initializeCoiAttachmentFilter();
        coiUserRoles = new ArrayList<CoiUserRole>();
        initializeCoiReviewStatus();

    } 

    protected PersistenceService getPersistenceService() {
        if (persistenceService == null) {
            persistenceService = KcServiceLocator.getService("persistenceServiceOjb");

        }
        return persistenceService;
    }

    public KcPerson getPerson() {
        if (this.personId != null) {
            return this.getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return new KcPerson();
    }
    
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }
    
    public List<CoiDisclosureNotepad> getCoiDisclosureNotepads() {
        
        if (this.coiDisclosureNotepads == null) {
            this.coiDisclosureNotepads = new ArrayList<CoiDisclosureNotepad>();
        }
        Collections.sort(coiDisclosureNotepads, Collections.reverseOrder());
        return this.coiDisclosureNotepads;
    }


    public void setCoiDisclosureNotepads(List<CoiDisclosureNotepad> coiDisclosureNotepads) {
        this.coiDisclosureNotepads = coiDisclosureNotepads;
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

    public Timestamp getCertificationTimestamp() {
        return certificationTimestamp;
    }

    public String getCertificationTimestampString() {
        if (getCertificationTimestamp() == null) {
            return null;
        } else {
            return dateFormatter.format(getCertificationTimestamp());
        }
    }
    
    public void setCertificationTimestamp(Timestamp certificationTimestamp) {
        this.certificationTimestamp = certificationTimestamp;
    }

    public void certifyDisclosure() {
        certifiedFlag = true;
        setCertificationTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        setCertificationText(new String(acknowledgementStatement));
        setCertifiedBy(GlobalVariables.getUserSession().getPrincipalName());
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

//    public String getModuleCode() {
//        return moduleCode;
//    }
//
//    public void setModuleCode(String moduleCode) {
//        this.moduleCode = moduleCode;
//    }

//    public String getReviewStatusCode() {
//        return reviewStatusCode;
//    }
//
//    public void setReviewStatusCode(String reviewStatusCode) {
//        this.reviewStatusCode = reviewStatusCode;
//    }

    public Integer getDiscActiveStatus() {
        return discActiveStatus;
    }

    public void setDiscActiveStatus(Integer discActiveStatus) {
        this.discActiveStatus = discActiveStatus;
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
    
    public String getLeadUnitNumber() {
        for (DisclosurePersonUnit disclosurePersonUnit : disclosurePersons.get(0).getDisclosurePersonUnits()) {
            if (disclosurePersonUnit.isLeadUnitFlag()) {
                return disclosurePersonUnit.getUnitNumber();
            }
        }
        return null;
    }
    
    private CoiDisclosureService getCoiDisclosureService() {
        return KcServiceLocator.getService(CoiDisclosureService.class);
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
        //managedLists.add(getCoiDiscDetails());
        managedLists.add(getCoiUserRoles());
        managedLists.add(getCoiDisclosureAttachments());
        managedLists.add(getCoiDisclosureNotepads());
        List<CoiDiscDetail> details = new ArrayList<CoiDiscDetail>();
        for (CoiDisclProject coiDisclProject : getCoiDisclProjects()) {
            details.addAll(coiDisclProject.getCoiDiscDetails());
        //    managedLists.addAll(coiDisclProject.buildListOfDeletionAwareLists());
        }
        managedLists.add(details);
        managedLists.add(getCoiDisclProjects());
        return managedLists;
    }


    public void initRequiredFields() {
        // is pending equivalent to in progress?
        this.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
        this.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
        this.setReviewStatusCode(CoiReviewStatus.IN_PROGRESS);
        this.setPersonId(this.getDisclosureReporter().getPersonId());
        initCoiDisclosureNumber();
        this.setExpirationDate(new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), 365).getTime()));

    }
    
    public void initCoiDisclosureNumber() {
        // TODO : not sure about disclosurenumber & expirationdate
        if (StringUtils.isBlank(this.getCoiDisclosureNumber())) {
            Long nextNumber = KcServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_COI_DISCL_NUMBER", CoiDisclosure.class);
            setCoiDisclosureNumber(nextNumber.toString());
        }
        
    }
    
    public boolean getCertifiedFlag() {
        return certifiedFlag;
    }

    public void setCertifiedFlag(boolean certifiedFlag) {
        this.certifiedFlag = certifiedFlag;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    
    public String getCertificationStatement() {
        if (certificationStatement == null) {
            certificationStatement = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_COIDISCLOSURE, 
                       Constants.PARAMETER_COMPONENT_DOCUMENT, DISCLOSURE_CERT_STMT);
        }
        return certificationStatement;
    }

    public String getAcknowledgementStatement() {
        if (acknowledgementStatement == null) {
            acknowledgementStatement = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_COIDISCLOSURE, 
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

    
    public String getSubmitThankyouStatement() {
        if (submitThankyouStatement == null) {
            ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();
            submitThankyouStatement = kualiConfiguration.getPropertyValueAsString(SUBMIT_ACK_THANKYOU);
        }
        return submitThankyouStatement;
    }

    public boolean isProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, this.getEventTypeCode());
    }
    
    public boolean isInstitutionalProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, this.getEventTypeCode());
    }
    /*This does not include manual projects*/
    public boolean isNonManualProjectEvent() {
        return (isAwardEvent() || isProposalEvent() || isProtocolEvent() || isInstitutionalProposalEvent());
    }
    
    public boolean isProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IRB_PROTOCOL, this.getEventTypeCode());
    }

    public boolean isIacucProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IACUC_PROTOCOL, this.getEventTypeCode());
    }

    public boolean isAwardEvent() {
        return StringUtils.equals(CoiDisclosureEventType.AWARD, this.getEventTypeCode());
    }

    public boolean isAnnualEvent() {
        return StringUtils.equals(CoiDisclosureEventType.ANNUAL, this.getEventTypeCode());
    }

    public boolean isUpdateEvent() {
        return StringUtils.equals(CoiDisclosureEventType.UPDATE, this.getEventTypeCode());
    }

    public boolean isExcludedFromAnnual() {
        return coiDisclosureEventType.isExcludeFromMasterDisclosure();
    }
    // Good gracious, this is inefficient...
    public boolean isManualEvent() {
        return !StringUtils.equals(CoiDisclosureEventType.ANNUAL, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.AWARD, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.IRB_PROTOCOL, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.IACUC_PROTOCOL, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.NEW, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.UPDATE, this.getEventTypeCode())
            && !StringUtils.equals(CoiDisclosureEventType.OTHER, this.getEventTypeCode());
    }


    /*
     * These are disclosures generated from KC.
     */
    public boolean isSystemEvent() {
        return ( StringUtils.equals(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, this.getEventTypeCode())
            || StringUtils.equals(CoiDisclosureEventType.AWARD, this.getEventTypeCode())
            || StringUtils.equals(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, this.getEventTypeCode())
            || StringUtils.equals(CoiDisclosureEventType.IRB_PROTOCOL, this.getEventTypeCode())
            || StringUtils.equals(CoiDisclosureEventType.IACUC_PROTOCOL, this.getEventTypeCode())
            );
    }

    public String getCompleteMessage() {
        String completeMessage = "Disclosure is complete";
        int completeCount = 0;
        int detailSize = 0;
        if (CollectionUtils.isNotEmpty(this.getCoiDisclProjects())) {
            for (CoiDisclProject coiDisclProject: this.getCoiDisclProjects()) {
                if (CollectionUtils.isNotEmpty(coiDisclProject.getCoiDiscDetails())) {
                    detailSize += coiDisclProject.getCoiDiscDetails().size();
                    for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                        if (coiDiscDetail.getEntityDispositionCode() != null && coiDiscDetail.getEntityDispositionCode() > 0) {
                            completeCount ++;
                        }
                    }
                }
            }
        }
        return completeCount + "/" + detailSize + " Reviews Complete";
    }

    
    protected void updateUserFields(KcPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
    }

    protected DateTimeService getDateTimeService() {
        if(dateTimeService == null) {
            dateTimeService = (DateTimeService) CoreApiServiceLocator.getDateTimeService();
        }
        return dateTimeService;
    }
    /**
     * Adds a attachment to a CoiDisclosure
     * where to add the attachment.
     * @param attachment the attachment
     * @throws IllegalArgumentException if attachment is null or if an unsupported attachment is found
     */
    public <T extends CoiDisclosureAttachment> void addAttachmentsByType(T attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
    
        updateUserFields(attachment);
        attachment.setCoiDisclosureId(coiDisclosureId);
        attachment.setCoiDisclosureNumber(coiDisclosureNumber);
        if (attachment instanceof CoiDisclosureAttachment) {
            // do this in the versioning service if you add one
            assert attachment != null : "the attachment is null";
            if (attachment instanceof CoiDisclosureAttachment) {
                String ATTACHMENT_DRAFTED = "1";
                ((CoiDisclosureAttachment)attachment).setDocumentStatusCode(ATTACHMENT_DRAFTED);
                attachment.setDocumentId(getNextDocumentId(coiDisclosureDocument.getCoiDisclosure().getCoiDisclosureAttachments()));
            }
            coiDisclosureDocument.getCoiDisclosure().addCoiDisclosureAttachment(attachment);
            addAttachment((CoiDisclosureAttachment) attachment);

        } else {
            throw new IllegalArgumentException("unsupported type: " + attachment.getClass().getName());
        }
    }
    
    /*
     * Move to versioning service
     */
    private int getNextDocumentId(List<? extends CoiDisclosureAttachment> attachments) {
        int nextDocumentId = 0;
        for (CoiDisclosureAttachment attachment : attachments) {
            if (attachment.getDocumentId() > nextDocumentId) {
                nextDocumentId = attachment.getDocumentId();
            }
        }
        return ++nextDocumentId;
    }
    
    public void addAttachment(CoiDisclosureAttachment attachment) {
        this.getCoiDisclosureAttachments().add(attachment);
    }
    
    public List<CoiDisclosureAttachment> getCoiDisclosureAttachments() {
        if (this.coiDisclosureAttachments == null) {
            this.coiDisclosureAttachments = new ArrayList<CoiDisclosureAttachment>();
        }

        return coiDisclosureAttachments;
    }    


    public void setCoiDisclosureAttachments(List<CoiDisclosureAttachment> coiDisclosureAttachments) {
        this.coiDisclosureAttachments = coiDisclosureAttachments;
    }
    
    protected void addCoiDisclosureAttachment(CoiDisclosureAttachment coiDisclosureAttachment) {
        CoiDisclosureAttachment.addAttachmentToCollection(coiDisclosureAttachment, this.getCoiDisclosureAttachments());
    }
  
    public void initializeCoiAttachmentFilter() {
        newAttachmentFilter = new CoiDisclosureAttachmentFilter();
        
        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = getParameterService().getParameterValueAsString(CoiDisclosureDocument.class, Constants.PARAMETER_COI_ATTACHMENT_DEFAULT_SORT);
            if (StringUtils.isNotBlank(defaultSortBy)) {
                newAttachmentFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }        
    }
    
    public void initializeCoiReviewStatus() {
        setReviewStatusCode(CoiReviewStatus.IN_PROGRESS);
        getPersistenceService().retrieveReferenceObject(this, "coiReviewStatus");
    }
    
    
    public void setCoiDisclosureAttachmentFilter(CoiDisclosureAttachmentFilter newAttachmentFilter) {
        this.newAttachmentFilter = newAttachmentFilter;      
     }

     public CoiDisclosureAttachmentFilter getCoiDisclosureAttachmentFilter() {
         return newAttachmentFilter;
     }
     
     public List<CoiDisclosureAttachment> getFilteredAttachments() {
         List<CoiDisclosureAttachment> filteredAttachments = new ArrayList<CoiDisclosureAttachment>();
         CoiDisclosureAttachmentFilter attachmentFilter = getCoiDisclosureAttachmentFilter();
         if (attachmentFilter != null && StringUtils.isNotBlank(attachmentFilter.getFilterBy())) {            
             
         } else {
             filteredAttachments = getCoiDisclosureAttachments();
         }
         
         if (attachmentFilter != null && StringUtils.isNotBlank(attachmentFilter.getSortBy())) {
             Collections.sort(filteredAttachments, attachmentFilter.getCoiDisclosureAttachmentComparator()); 
         }   
         
         return filteredAttachments;
     }
     

    public boolean isComplete() {
        // TODO : this is kind of duplicate with getCompleteMessage.
        // may want to merge for better solution
        boolean isComplete = true;
        if (CollectionUtils.isNotEmpty(this.getCoiDisclProjects())) {
            for (CoiDisclProject coiDisclProject: this.getCoiDisclProjects()) {
                if (CollectionUtils.isNotEmpty(coiDisclProject.getCoiDiscDetails())) {
                    for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                        if (coiDiscDetail.getEntityDispositionCode() == null || coiDiscDetail.getEntityDispositionCode() == 0) {
                            isComplete = false;
                            break;
                        }
                        
                    }
                }
                
                if (!isComplete) {
                    break;
                }
            }
        }
        return isComplete;
    }

     
    @Override
    public void setSequenceOwner(CoiDisclosure newlyVersionedOwner) {
        
    }

    @Override
    public CoiDisclosure getSequenceOwner() {
        return this;
    }

    @Override
    public void resetPersistenceState() {
        this.coiDisclosureId = null;
        
    }

    @Override
    public void incrementSequenceNumber() {
        this.sequenceNumber++;         
    }

    @Override
    public Integer getOwnerSequenceNumber() {

        return null;
    }

    @Override
    public String getVersionNameField() {
        return "coiDisclosureNumber";
    }

    @Override
    public String getVersionNameFieldValue() {
        return coiDisclosureNumber;
    }

    public KcPersistableBusinessObjectBase getEventBo() {
        return eventBo;
    }

    public void setEventBo(KcPersistableBusinessObjectBase eventBo) {
        this.eventBo = eventBo;
    }

    @Override
    public String getDocumentNumberForPermission() {
        return coiDisclosureNumber;
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.COI_DISCLOSURE_KEY;
    }

    // permissionable related override
    @Override
    public List<String> getRoleNames() {
        return null;
    }

    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_COIDISCLOSURE;
    }
    

    public String getDocumentUnitNumber() {
        return getLeadUnitNumber();
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.COI_DISCLOSURE_ROLE_TYPE;
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        if (getCoiDisclosureId() != null) {
            qualifiedRoleAttributes.put("coiDisclosureId", getCoiDisclosureId().toString());
        }
    }
    // end permissionable related override

    public String getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public String getModuleItemKey() {
        return moduleItemKey;
    }

    public void setModuleItemKey(String moduleItemKey) {
        this.moduleItemKey = moduleItemKey;
    }

    public boolean isCurrentDisclosure() {
        return currentDisclosure;
    }

    public void setCurrentDisclosure(boolean currentDisclosure) {
        this.currentDisclosure = currentDisclosure;
    }

    public boolean isOpenForNotesAndAttachments() {
        return !isApprovedDisclosure() && !isDisapprovedDisclosure();
    }
    
    public boolean isApprovedDisclosure() {
        return StringUtils.equals(CoiDisclosureStatus.APPROVED, getDisclosureStatusCode());
    }

    /*
     * Checking timestamp instead of status because the status of a submitted 
     * disclosure could be submitted, approved or disapproved.
     */
    public boolean isSubmitted() {
       return ObjectUtils.isNotNull(getCertificationTimestamp());
    }
    
    public boolean isDisapprovedDisclosure() {
        return StringUtils.equals(CoiDisclosureStatus.DISAPPROVED, getDisclosureStatusCode());
    }

    public CoiDispositionStatus getCoiDispositionStatus() {
        if (StringUtils.isNotEmpty(disclosureDispositionCode) && coiDispositionStatus == null) {
            this.refreshReferenceObject("coiDispositionStatus");
        }
        return coiDispositionStatus;
    }

    public void setCoiDispositionStatus(CoiDispositionStatus coiDispositionStatus) {
        this.coiDispositionStatus = coiDispositionStatus;
    }

    public CoiDisclosureStatus getCoiDisclosureStatus() {
        this.refreshReferenceObject("coiDisclosureStatus");
        return coiDisclosureStatus;
    }

    public boolean isUnderReview() {
        CoiDisclosureStatus currentStatus = getCoiDisclosureStatus();
        return StringUtils.equals(CoiDisclosureStatus.ROUTED_FOR_REVIEW, currentStatus.getCoiDisclosureStatusCode());
    }
    
    public boolean isSubmittedForReview() {
        CoiDisclosureStatus currentStatus = getCoiDisclosureStatus();
        return StringUtils.equals(CoiDisclosureStatus.ROUTED_FOR_REVIEW, currentStatus.getCoiDisclosureStatusCode());
    }
    
    public void setCoiDisclosureStatus(CoiDisclosureStatus coiDisclosureStatus) {
        this.coiDisclosureStatus = coiDisclosureStatus;
    }

    public CoiDisclosureEventType getCoiDisclosureEventType() {
        if ((StringUtils.isNotBlank(eventTypeCode) && coiDisclosureEventType == null) || (coiDisclosureEventType != null && !StringUtils.equals(eventTypeCode, coiDisclosureEventType.getEventTypeCode()))) {
            // the typecode are not equal : it may happen when user select a different manualtype, ojb will not refresh automatically
            refreshReferenceObject("coiDisclosureEventType");
        }
        return coiDisclosureEventType;
    }

    public void setCoiDisclosureEventType(CoiDisclosureEventType coiDisclosureEventType) {
        this.coiDisclosureEventType = coiDisclosureEventType;
    }

    public List<CoiUserRole> getCoiUserRoles() {
        return coiUserRoles;
    }

    public void setCoiUserRoles(List<CoiUserRole> coiUserRoles) {
        this.coiUserRoles = coiUserRoles;
    }


    public boolean isAnnualUpdate() {
        return annualUpdate;
    }


    public void setAnnualUpdate(boolean annualUpdate) {
        this.annualUpdate = annualUpdate;
    }

    public String getReporterCreated() {
        return reporterCreated;
    }

    public void setReporterCreated(String reporterCreated) {
        this.reporterCreated = reporterCreated;
    }

    public String getCoiDisclProjectId() {
        return coiDisclProjectId;
    }
    public void setCoiDisclProjectId(String coiDisclProjectId) {
        this.coiDisclProjectId = coiDisclProjectId;
    }
    public String getCoiDisclProjectTitle() {
        return coiDisclProjectTitle;
    }
    public void setCoiDisclProjectTitle(String coiDisclProjectTitle) {
        this.coiDisclProjectTitle = coiDisclProjectTitle;
    }

    public List<CoiNotification> getDisclosureNotifications() {
        if (disclosureNotifications == null) {
            disclosureNotifications = new ArrayList<CoiNotification>();
        }
        return disclosureNotifications;
    }

    public List<CoiNotification> getFilteredDisclosureNotifications() {
        return filterNotifications(getDisclosureNotifications());
    }
    
    public List<CoiNotification> filterNotifications(List<CoiNotification>unfilteredList) {
        String currentUser = GlobalVariables.getUserSession().getPrincipalName().trim();
        if (!(StringUtils.equals(currentUser, getReporterUserName()))) {
            return unfilteredList;
        } else {
            List<CoiNotification>filteredList = new ArrayList<CoiNotification>();
            for (CoiNotification notification: unfilteredList) {
                if (currentUser.equals(notification.getCreateUser())) {
                    filteredList.add(notification);
                } else {
                    for (String recipient: notification.getRecipients().split(",")) {
                        if (currentUser.equals(recipient.trim())) {
                            filteredList.add(notification);
                            break;
                        }
                    }
                }
            }
            return filteredList;
        }
    }
    
    public List<CoiNotification> getNotificationsByDocId() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("documentNumber", getCoiDisclosureDocument().getDocumentNumber());
        return (List<CoiNotification>) getBusinessObjectService().findMatching(CoiNotification.class, fieldValues);
    }

    public List<CoiNotification> getFilteredNotificationsByDocId() {
        return filterNotifications(getNotificationsByDocId());
    }
    
    public void setDisclosureNotifications(List<CoiNotification> disclosureNotifications) {
        this.disclosureNotifications = disclosureNotifications;
    }
 
    public void addNotification(CoiNotification notification) {
        getDisclosureNotifications().add(notification);
    }

    public String getReporterUserName() {
        DisclosurePerson reporter =  getDisclosureReporter();
        return getKcPersonService().getKcPersonByPersonId(reporter.getPersonId()).getUserName();
   }

    public String getReviewStatusCode() {
        return reviewStatusCode;
    }

    public void setReviewStatusCode(String reviewStatusCode) {
        this.reviewStatusCode = reviewStatusCode;
    }

    public CoiReviewStatus getCoiReviewStatus() {
        if (StringUtils.isNotEmpty(reviewStatusCode) && coiReviewStatus == null) {
            this.refreshReferenceObject("coiReviewStatus");
        }
        return coiReviewStatus;
    }

    public void setCoiReviewStatus(CoiReviewStatus coiReviewStatus) {
        this.coiReviewStatus = coiReviewStatus;
    }

    public String getDisclosureStatusReviewStatus() {
        return disclosureStatusReviewStatus;
    }
    
    public boolean isDisclosureSaved() {
        return coiDisclosureId != null;
    }

    /**
     *
     * This is ahelper method to get author person name
     * @return
     */
    public String getAuthorPersonName() {
        KcPerson person = this.getKcPersonService().getKcPersonByUserName(getUpdateUser());
        return ObjectUtils.isNull(person) ? "Person not found" : person.getFullName();
    }

}
