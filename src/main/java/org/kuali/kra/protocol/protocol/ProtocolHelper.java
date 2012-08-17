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
package org.kuali.kra.protocol.protocol;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.ProtocolActionType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
// TODO *********commented the code below during IACUC refactoring********* 
// import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.personnel.ProtocolPerson;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.personnel.ProtocolUnit;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.protocol.protocol.location.ProtocolLocation;
import org.kuali.kra.protocol.protocol.participant.ProtocolParticipant;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class ProtocolHelper implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8036126015259703614L;


    private static final String PROTOCOL_CREATED = "Protocol created";
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    private ProtocolForm form;
    
    private String principalInvestigatorId;
    private String principalInvestigatorName;
    private String personId;
    private String rolodexId;
    
    private String lookupUnitNumber;
    private String lookupUnitName;


    private String leadUnitNumber;
    private String leadUnitName;
    private boolean nonEmployeeFlag;
        
    private String referenceId1Label;
    private String referenceId2Label;

    private boolean modifyProtocol = false;
    private boolean billableReadOnly = false;
    private ProtocolLocation newProtocolLocation;
    private String organizationName;
    private ProtocolFundingSource newFundingSource;
    private ProtocolParticipant newProtocolParticipant;
    
    private boolean editProtocolFundingSourceName = false;
    private List<ProtocolFundingSource> deletedProtocolFundingSources;
    
    private boolean modifyGeneralInfo = false;
    private boolean modifyFundingSource = false;
    private boolean modifyReferences = false;
    private boolean modifyOrganizations = false;
    private boolean modifySubjects = false;
    private boolean modifyAreasOfResearch = false;
    private boolean canCreateProposalDevelopment = false;
    private boolean protocolProposalDevelopmentLinkingEnabled = false;
    
    private boolean leadUnitAutoPopulated = false;
    
    private transient ParameterService parameterService;
    private transient KcPersonService personService;
    private transient RolodexService rolodexService;
    private transient ProtocolFundingSourceService protocolFundingSourceService;
    private transient SpecialReviewService specialReviewService;
    private List<ProtocolFundingSource> newProtocolFundingSources;

    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
 
    protected RolodexService getRolodexService() {
        if (this.rolodexService == null) {
            this.rolodexService = KraServiceLocator.getService(RolodexService.class);        
        }
        return this.rolodexService;
    }
    
    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        if (this.protocolFundingSourceService == null) {
            this.protocolFundingSourceService = KraServiceLocator.getService(getProtocolFundingSourceServiceClassHook()); 
        }
        return this.protocolFundingSourceService;
    }
    
    protected abstract Class<? extends ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook();

    
    private SpecialReviewService getSpecialReviewService() {
        if (this.specialReviewService == null) {
            this.specialReviewService = KraServiceLocator.getService(SpecialReviewService.class);
        }
        return this.specialReviewService;
    }

    public boolean isEditProtocolFundingSourceName() {
        return editProtocolFundingSourceName;
    }

    public void setEditProtocolFundingSourceName(boolean editProtocolFundingSourceName) {
        this.editProtocolFundingSourceName = editProtocolFundingSourceName;
    }

    public List<ProtocolFundingSource> getDeletedProtocolFundingSources() {
        return deletedProtocolFundingSources;
    }

    public void setDeletedProtocolFundingSources(List<ProtocolFundingSource> deletedProtocolFundingSources) {
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }

    //Must be set to true, in case if order of method call in prepareview() is altered, functionality of billable will not break. 
    private boolean displayBillable = true;

    public ProtocolHelper(ProtocolForm form) {
        this.form = form;
        setNewProtocolLocation(getNewProtocolLocationInstanceHook());
        setDeletedProtocolFundingSources(new ArrayList<ProtocolFundingSource>());
        setNewFundingSource(getNewProtocolFundingSourceInstanceHook());        
        
// TODO *********commented the code below during IACUC refactoring*********          
//        setNewProtocolParticipant(new ProtocolParticipant());        
        
        setNewProtocolFundingSources(new ArrayList<ProtocolFundingSource>());
    }    
    
    protected abstract ProtocolFundingSource getNewProtocolFundingSourceInstanceHook();

    protected abstract ProtocolLocation getNewProtocolLocationInstanceHook();
    
    
    /**
     * This method prepares view for rendering UI.
     * Note: Order of following methods must not be altered.
     * initializeConfigurationParams() must be before initializePermissions(getProtocol()) due to billable requirement. 
     */
    public void prepareView() {
        prepareRequiredFields();
        syncFundingSources(getProtocol());        
        initializeConfigurationParams();        
        initializePermissions(getProtocol());    
    }
    
    private Protocol getProtocol() {
        ProtocolDocument document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    private void initializeConfigurationParams() {        
        setReferenceId1Label(getParameterValue(getReferenceID1ParameterNameHook()));
        setReferenceId2Label(getParameterValue(getReferenceID2ParameterNameHook()));
        
// TODO *********commented the code below during IACUC refactoring*********         
        boolean flag = (getParameterValue(getBillableParameterHook()).equalsIgnoreCase("Y") ? true : false);
        setDisplayBillable(flag);
    }

    protected abstract String getBillableParameterHook();
    
    protected abstract String getReferenceID1ParameterNameHook();

    protected abstract String getReferenceID2ParameterNameHook();

    /**
     * This method initializes permission related to form.
     * Note: Billable permission is only set if displayBillable is true.
     * Reason: For Institution who does not bill.  
     * @param protocol
     */
    protected void initializePermissions(Protocol protocol) {
         
        initializeModifyProtocolPermission(protocol);

// TODO *********commented the code below during IACUC refactoring*********        
        if(displayBillable) {
            initializeBillablePermission(protocol);   
        }
        
        initializeModifyGeneralInfoPermission(protocol);
        initializeModifyFundingSourcePermission(protocol);
        
        initializeModifyReferencesPermission(protocol);
        initializeModifyOrganizationsPermission(protocol);

// TODO *********commented the code below during IACUC refactoring*********                 
//        initializeModifySubjectsPermission(protocol);
        
        initializeModifyAreasOfResearchPermission(protocol);
        initializeProtocolProposalDevelopmentLinking();
        initializeCreateProposalDevelopmentPermission(protocol);
    }

    private void initializeModifyProtocolPermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolTaskHook(protocol);
        modifyProtocol = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);   
    }

    protected abstract ProtocolTask getNewInstanceModifyProtocolTaskHook(Protocol protocol);

    private void initializeBillablePermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolBillableTaskNewHook(protocol);
        billableReadOnly = !getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    

    public abstract ProtocolTask getNewInstanceModifyProtocolBillableTaskNewHook(Protocol protocol);

    
    private void initializeModifyGeneralInfoPermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolGeneralInfoTaskHook(protocol);
        modifyGeneralInfo = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewInstanceModifyProtocolGeneralInfoTaskHook(Protocol protocol);

    
    
    private void initializeModifyFundingSourcePermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolFundingSourceTaskHook(protocol);
        modifyFundingSource = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewInstanceModifyProtocolFundingSourceTaskHook(Protocol protocol);
    
    
    
    private void initializeModifyReferencesPermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolReferencesTaskHook(protocol);
        modifyReferences = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewInstanceModifyProtocolReferencesTaskHook(Protocol protocol);

    
    
    private void initializeModifyOrganizationsPermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolOrganizationsTaskHook(protocol);
        modifyOrganizations = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewInstanceModifyProtocolOrganizationsTaskHook(Protocol protocol);
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    private void initializeModifySubjectsPermission(Protocol protocol) {
//        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_SUBJECTS, protocol);
//        modifySubjects = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
//    }
    
    private void initializeModifyAreasOfResearchPermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceModifyProtocolResearchAreasTaskHook(protocol);
        modifyAreasOfResearch = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewInstanceModifyProtocolResearchAreasTaskHook(Protocol protocol);
    

    
    private void initializeCreateProposalDevelopmentPermission(Protocol protocol) {
        ProtocolTask task = getNewInstanceCreateProposalDevelopmentTaskHook(protocol);
        canCreateProposalDevelopment = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTask getNewInstanceCreateProposalDevelopmentTaskHook(Protocol protocol);

    private void initializeProtocolProposalDevelopmentLinking()
    {
        protocolProposalDevelopmentLinkingEnabled = getProtocolProposalDevelopmentLinkingHook();
    }

    protected abstract boolean getProtocolProposalDevelopmentLinkingHook();
    
    public boolean isProtocolProposalDevelopmentLinkingEnabled()
    {
        return protocolProposalDevelopmentLinkingEnabled;
    }

    /**
     * This method is to get parameter value
     * @return parameter value
     */
    private String getParameterValue(String parameterName) {
        return getParameterService().getParameterValueAsString(getProtocolDocumentClassHook(), parameterName);        
    }

    // hook
    protected abstract Class<? extends ProtocolDocument> getProtocolDocumentClassHook();

    public void setReferenceId1Label(String referenceId1Label) {
        this.referenceId1Label = referenceId1Label;
    }

    public String getReferenceId1Label() {
        return referenceId1Label;
    }
    
    public void setReferenceId2Label(String referenceId2Label) {
        this.referenceId2Label = referenceId2Label;
    }

    public String getReferenceId2Label() {
        return referenceId2Label;
    }
    
    public boolean getModifyProtocol() {
        return modifyProtocol;
    }
    
    public boolean getBillableReadOnly() {
        return billableReadOnly;
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    private String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public void syncFundingSources(Protocol protocol) {
        if (protocol != null) {
            if (newFundingSource != null) {
                syncFundingSource(newFundingSource);
            }
            for (ProtocolFundingSource source : protocol.getProtocolFundingSources()) {
                syncFundingSource(source);
            }
        }
    }
    
    private void syncFundingSource(ProtocolFundingSource source) {
        ProtocolFundingSource syncedSource = getProtocolFundingSourceService().updateProtocolFundingSource(
                source.getFundingSourceTypeCode(), source.getFundingSourceNumber(), source.getFundingSourceName());
        if (syncedSource != null) {
            source.setProtocol(getProtocol());
            source.setFundingSourceNumber(syncedSource.getFundingSourceNumber());
            source.setFundingSourceName(syncedSource.getFundingSourceName());
            source.setFundingSourceTitle(syncedSource.getFundingSourceTitle());
        }
    }
   
  
    public String getPrincipalInvestigatorId() {
        return principalInvestigatorId;
    }

    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }

    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }

    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(String rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }

    public String getLeadUnitName() {
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }

    public boolean isNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }
        
    public String getLookupUnitName() {
        return lookupUnitName;
    }

    public void setLookupUnitName(String lookupUnitName) {
        this.lookupUnitName = lookupUnitName;
    }

    public String getLookupUnitNumber() {
        return lookupUnitNumber;
    }

    public void setLookupUnitNumber(String lookupUnitNumber) {
        this.lookupUnitNumber = lookupUnitNumber;
    }
 
    
    
    /**
     * This method either populates the protocol form fields from the BO or
     * it propagates form fields from lookup etc. as appropriate for
     * the unsaved Protocol.
     */
    private void prepareRequiredFields() {
        Protocol theProtocol = getProtocol();
        if (theProtocol.getProtocolId() == null) {
            findPrincipalInvestigatorIdFromFields();
            findAndSetLeadUnitFromFields();
        } 
        else {
            resolveRequiredFieldsFromBO();
        }
    }
    
    
    public boolean isLeadUnitAutoPopulated() {
        return leadUnitAutoPopulated;
    }

    
    public void setLeadUnitAutoPopulated(boolean leadUnitAutoPopulated) {
        this.leadUnitAutoPopulated = leadUnitAutoPopulated;
    }
   
    
    
    /**
     * This method is a form helper for protocol. It is needed to push the
     * transient form fields into principal investigator /lead unit.
     * Due to table structure, these are stored as lists in protocol and 
     * protocol investigator respectively, but aren't provided (by design) an explicit
     * "add" element in the required fields panel view like most growing lists
     */
    public void prepareRequiredFieldsForSave() {
        
        if (getProtocol().getProtocolNumber() == null) {
            // TODO getProtocolNumberService() is now an abstract hook method
            getProtocol().setProtocolNumber(getProtocolNumberService().generateProtocolNumber());
        }
        
        findPrincipalInvestigatorIdFromFields();
        findAndSetLeadUnitFromFields();

        // Since we are saving, we will always reset the PI and lead unit to field values
        // TODO getProtocolPersonnelService() is now an abstract hook method
        getProtocolPersonnelService().setPrincipalInvestigator(createPrincipalInvestigator(), getProtocol());
        ProtocolPerson principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(getProtocol().getProtocolPersons());
        getProtocolPersonnelService().setLeadUnit(createLeadUnit(), principalInvestigator, getProtocol());
    }
    
    
    
    /**
     * Creates the initial PROTOCOL_CREATED action for a new protocol.
     */
    public void createInitialProtocolAction() {
        if (getProtocol().getProtocolDocument().getDocumentHeader().getWorkflowDocument().isInitiated()) {
            getProtocol().getProtocolActions().clear();
            ProtocolAction protocolAction = createProtocolCreatedTypeProtocolActionInstanceHook(getProtocol());
            protocolAction.setComments(PROTOCOL_CREATED);
            getProtocol().getProtocolActions().add(protocolAction);
        }
    }
    
    protected abstract ProtocolAction createProtocolCreatedTypeProtocolActionInstanceHook(Protocol protocol);
       

    /**
     * Synchronizes the information between this Protocol's Funding Sources and any Institutional Proposal or Award Special Review entries.
     */
    public void syncSpecialReviewsWithFundingSources() throws WorkflowException {
// TODO *********commented the code below during IACUC refactoring*********         
//        for (ProtocolFundingSource protocolFundingSource : getProtocol().getProtocolFundingSources()) {
//            String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
//            String fundingSourceTypeCode = protocolFundingSource.getFundingSourceTypeCode();
//            String protocolNumber = getProtocol().getProtocolNumber();
//            
//            if (!getSpecialReviewService().isLinkedToSpecialReview(fundingSourceNumber, fundingSourceTypeCode, protocolNumber)) {
//                Date applicationDate = getProtocol().getProtocolSubmission().getSubmissionDate();
//                Date approvalDate = getProtocol().getLastApprovalDate() == null ? getProtocol().getApprovalDate() : getProtocol().getLastApprovalDate();
//                Date expirationDate = getProtocol().getExpirationDate();
//                List<String> exemptionTypeCodes = new ArrayList<String>();
//                for (ProtocolExemptStudiesCheckListItem checkListItem : getProtocol().getProtocolSubmission().getExemptStudiesCheckList()) {
//                    exemptionTypeCodes.add(checkListItem.getExemptStudiesCheckListCode());
//                }
//                getSpecialReviewService().addSpecialReviewForProtocolFundingSource(
//                    fundingSourceNumber, fundingSourceTypeCode, protocolNumber, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
//            }
//        }
//        
//        for (ProtocolFundingSource protocolFundingSource : deletedProtocolFundingSources) {
//            String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
//            String fundingSourceTypeCode = String.valueOf(protocolFundingSource.getFundingSourceTypeCode());
//            String protocolNumber = getProtocol().getProtocolNumber();
//            
//            getSpecialReviewService().deleteSpecialReviewForProtocolFundingSource(fundingSourceNumber, fundingSourceTypeCode, protocolNumber);
//        }
        
        deletedProtocolFundingSources.clear();
    }


    // hook method
    protected abstract ProtocolNumberService getProtocolNumberService();
 
    private KcPersonService getPersonService() {
        if(personService == null) {
            personService = KraServiceLocator.getService(KcPersonService.class);
        }
        return personService;
    }
    
    /**
     * This is used to calculate princiapal investigator ID from fields
     * it's the values set rolodex id or person id depening on 
     * the lookup type
     */
    private void findPrincipalInvestigatorIdFromFields() {
        if (StringUtils.isNotEmpty(getPersonId())) {
            setPrincipalInvestigatorId(getPersonId());
            setNonEmployeeFlag(false);
        } else if (StringUtils.isNotEmpty(getRolodexId())) {
            setPrincipalInvestigatorId(getRolodexId());
            setNonEmployeeFlag(true);
        }
    }

    
    private Unit getPIUnit(String piId) {
        Contactable pi = null;
        if(StringUtils.isNotBlank(piId)) {
            if(!nonEmployeeFlag) {
                pi = getPersonService().getKcPersonByPersonId(getProtocol().getPrincipalInvestigatorId());
            } else {
                pi = getRolodexService().getRolodex(Integer.parseInt(piId));
            }
        }
        return (pi == null? null : pi.getUnit());
    }
    
    private void verifyLeadUnitAutoPopulation() {
        if(StringUtils.isNotEmpty(getProtocol().getPrincipalInvestigatorId()) && StringUtils.isNotEmpty(getProtocol().getLeadUnitNumber())) {
            Unit piUnit = getPIUnit(getProtocol().getPrincipalInvestigatorId()) ;
            if(piUnit != null && !StringUtils.equals(piUnit.getUnitNumber(), getProtocol().getLeadUnitNumber())) {
                setLeadUnitAutoPopulated(false);
            }
        }
    }

    
     /**
      * This is used to calculate lead unit info from fields
      * it's the values set into form or (if unset during lookup)
      * from the lookup values returned for PI's home unit
      */
    private void findAndSetLeadUnitFromFields() {
        getProtocol().setLeadUnitNumber(getLeadUnitNumber());
        setLeadUnitName(getUnitService().getUnitName(getLeadUnitNumber()));
        verifyLeadUnitAutoPopulation();
        
        if ((StringUtils.isEmpty(getLeadUnitName()) 
                && StringUtils.isEmpty(getLeadUnitNumber())) || isLeadUnitAutoPopulated()) {
            if(StringUtils.isNotEmpty(getLookupUnitNumber())) {
                setLeadUnitNumber(getLookupUnitNumber());
                setLeadUnitName(getLookupUnitName()); 
                setLeadUnitAutoPopulated(true);
            }
        }
        setLookupUnitNumber(null);
        setLookupUnitName(null);
    }
    
    
    private ProtocolUnit createLeadUnit() {
        ProtocolUnit ret = null;
        if (StringUtils.isNotEmpty(getLeadUnitNumber()) && StringUtils.isNotEmpty(getLeadUnitName())) {
            ret = createNewProtocolUnitInstanceHook();
            ret.setLeadUnitFlag(true);
            ret.setUnitNumber(getLeadUnitNumber());
            ret.setUnitName(getLeadUnitName());
        }
        return ret;
    }
    
    // hook method
    protected abstract ProtocolUnit createNewProtocolUnitInstanceHook();
    
    
    
    private ProtocolPerson createPrincipalInvestigator() {
        ProtocolPerson pi = null;
        if (!StringUtils.isBlank(getPrincipalInvestigatorId())) {
            pi = createNewProtocolPersonInstanceHook();
            pi.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
            pi.setProtocolNumber(getProtocol().getProtocolNumber());
            pi.setSequenceNumber(0);
            pi.refreshReferenceObject("protocolPersonRole");
            if (isNonEmployeeFlag()) {
                pi.refreshReferenceObject("rolodex");
                pi.setRolodexId(Integer.valueOf(principalInvestigatorId));
            } else {
                pi.setPersonId(principalInvestigatorId);
            }
            pi.setPersonName(getPrincipalInvestigatorName());
        }
        return pi;
    }

    // hook method
    protected abstract ProtocolPerson createNewProtocolPersonInstanceHook();
   
    private void resolveRequiredFieldsFromBO() {
        ProtocolPerson pi = getProtocol().getPrincipalInvestigator();
        if (pi !=null){
            setPrincipalInvestigatorName(pi.getPersonName());
            if (pi.isNonEmployee()) {
                setPrincipalInvestigatorId(pi.getRolodexId().toString());
                setNonEmployeeFlag(pi.isNonEmployee());
            } else {
                setPrincipalInvestigatorId(pi.getPersonId());
            }
            
            if (pi.getLeadUnit()!=null) {
                setLeadUnitNumber(pi.getLeadUnit().getUnitNumber());
                setLeadUnitName(pi.getLeadUnit().getUnitName());
            }
        }
    }
    
    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }

    public ProtocolLocation getNewProtocolLocation() {
        return newProtocolLocation;
    }

    public void setNewProtocolLocation(ProtocolLocation newProtocolLocation) {
        this.newProtocolLocation = newProtocolLocation;
    }
    
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    // hook method
    protected abstract ProtocolPersonnelService getProtocolPersonnelService();
    
    public boolean getDisplayBillable() {
        return displayBillable;
    }

    public void setDisplayBillable(boolean displayBillable) {
        this.displayBillable = displayBillable;
    }

    public ProtocolFundingSource getNewFundingSource() {
        return newFundingSource;
    }

    public void setNewFundingSource(ProtocolFundingSource newFundingSource) {
        this.newFundingSource = newFundingSource;
    }
    
    public ProtocolParticipant getNewProtocolParticipant() {
        return newProtocolParticipant;
    }

    public void setNewProtocolParticipant(ProtocolParticipant newProtocolParticipant) {
        this.newProtocolParticipant = newProtocolParticipant;
    }
    
    public boolean getModifyFundingSource() {
        return modifyFundingSource;
    }

    public boolean getModifyGeneralInfo() {
        return modifyGeneralInfo;
    }

    public boolean getModifyReferences() {
        return modifyReferences;
    }

    public boolean getModifyOrganizations() {
        return modifyOrganizations;
    }

    public boolean getModifySubjects() {
        return modifySubjects;
    }

    public boolean getModifyAreasOfResearch() {
        return modifyAreasOfResearch;
    }

    public boolean isCanCreateProposalDevelopment()    
    {
        return canCreateProposalDevelopment;
    }
    
    public boolean isFundingNumberLookupable() {
        if (newFundingSource != null && StringUtils.isNotBlank(newFundingSource.getFundingSourceTypeCode())) {
            return getProtocolFundingSourceService().isLookupable(newFundingSource.getFundingSourceTypeCode());
        } else {
            return false;
        }
    }
    
    public boolean isSourceNameEditable() {
        if (newFundingSource != null && StringUtils.isNotBlank(newFundingSource.getFundingSourceTypeCode())) {
            return getProtocolFundingSourceService().isEditable(newFundingSource.getFundingSourceTypeCode());
        } else {
            return true;
        }
    }
    
    public boolean isRoleIRBAdmin() {
        Role roleInfo = getRoleService().getRoleByNamespaceCodeAndName(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR);
        List<String> roleIds = new ArrayList<String>();
        roleIds.add(roleInfo.getId());
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualifications =new HashMap<String,String>(qualifiedRoleAttributes);
        return getRoleService().principalHasRole(getUserIdentifier(), roleIds, qualifications);
    }
    
    /**
     * Quick method to get the RoleService
     * @return RoleService reference
     */
    private RoleService getRoleService() {
        return KraServiceLocator.getService(RoleService.class);
    }

    public List<ProtocolFundingSource> getNewProtocolFundingSources() {
        return newProtocolFundingSources;
    }

    public void setNewProtocolFundingSources(List<ProtocolFundingSource> newProtocolFundingSources) {
        this.newProtocolFundingSources = newProtocolFundingSources;
    }    

    public List<ProtocolFundingSource> findNewFundingSources() {
        List<ProtocolFundingSource> fundingSources = new ArrayList<ProtocolFundingSource>();
        for (ProtocolFundingSource fundingSource : getProtocol().getProtocolFundingSources()) {
            if (fundingSource.getProtocolFundingSourceId() == null) {
                fundingSources.add(fundingSource);
            }
            
        }
        return fundingSources;
    }

}
