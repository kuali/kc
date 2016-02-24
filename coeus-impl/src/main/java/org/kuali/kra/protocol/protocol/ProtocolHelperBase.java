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
package org.kuali.kra.protocol.protocol;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ProtocolHelperBase implements Serializable {
    

    private static final long serialVersionUID = 8036126015259703614L;


    private static final String PROTOCOL_CREATED = "Protocol created";
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    private ProtocolFormBase form;
    
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
    private ProtocolLocationBase newProtocolLocation;
    private String organizationName;
    private ProtocolFundingSourceBase newFundingSource;
    
    private boolean editProtocolFundingSourceName = false;
    private List<ProtocolFundingSourceBase> deletedProtocolFundingSources;
    
    private boolean modifyGeneralInfo = false;
    private boolean modifyFundingSource = false;
    private boolean modifyReferences = false;
    private boolean modifyOrganizations = false;
    
    private boolean modifyAreasOfResearch = false;
    private boolean canCreateProposalDevelopment = false;
    private boolean protocolProposalDevelopmentLinkingEnabled = false;
    
    private boolean leadUnitAutoPopulated = false;
    
    private transient ParameterService parameterService;
    private transient KcPersonService personService;
    private transient RolodexService rolodexService;
    private transient ProtocolFundingSourceService protocolFundingSourceService;
    private transient SpecialReviewService specialReviewService;
    private List<ProtocolFundingSourceBase> newProtocolFundingSources;

    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
 
    protected RolodexService getRolodexService() {
        if (this.rolodexService == null) {
            this.rolodexService = KcServiceLocator.getService(RolodexService.class);
        }
        return this.rolodexService;
    }
    
    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        if (this.protocolFundingSourceService == null) {
            this.protocolFundingSourceService = KcServiceLocator.getService(getProtocolFundingSourceServiceClassHook());
        }
        return this.protocolFundingSourceService;
    }
    
    protected abstract Class<? extends ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook();

    
    protected SpecialReviewService getSpecialReviewService() {
        if (this.specialReviewService == null) {
            this.specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
        }
        return this.specialReviewService;
    }

    public boolean isEditProtocolFundingSourceName() {
        return editProtocolFundingSourceName;
    }

    public void setEditProtocolFundingSourceName(boolean editProtocolFundingSourceName) {
        this.editProtocolFundingSourceName = editProtocolFundingSourceName;
    }

    public List<ProtocolFundingSourceBase> getDeletedProtocolFundingSources() {
        return deletedProtocolFundingSources;
    }

    public void setDeletedProtocolFundingSources(List<ProtocolFundingSourceBase> deletedProtocolFundingSources) {
        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
    }

    //Must be set to true, in case if order of method call in prepareview() is altered, functionality of billable will not break. 
    private boolean displayBillable = true;

    public ProtocolHelperBase(ProtocolFormBase form) {
        this.form = form;
        setNewProtocolLocation(getNewProtocolLocationInstanceHook());
        setDeletedProtocolFundingSources(new ArrayList<ProtocolFundingSourceBase>());
        setNewFundingSource(getNewProtocolFundingSourceInstanceHook());                
        setNewProtocolFundingSources(new ArrayList<ProtocolFundingSourceBase>());
    }    
    
    protected abstract ProtocolFundingSourceBase getNewProtocolFundingSourceInstanceHook();

    protected abstract ProtocolLocationBase getNewProtocolLocationInstanceHook();
    
    
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
    
    protected ProtocolBase getProtocol() {
        ProtocolDocumentBase document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocumentBase in ProtocolFormBase");
        }
        return document.getProtocol();
    }
    
    private void initializeConfigurationParams() {        
        setReferenceId1Label(getParameterValue(getReferenceID1ParameterNameHook()));
        setReferenceId2Label(getParameterValue(getReferenceID2ParameterNameHook()));         
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
    protected void initializePermissions(ProtocolBase protocol) {
         
        initializeModifyProtocolPermission(protocol);   
        if(displayBillable) {
            initializeBillablePermission(protocol);   
        }        
        initializeModifyGeneralInfoPermission(protocol);
        initializeModifyFundingSourcePermission(protocol);        
        initializeModifyReferencesPermission(protocol);
        initializeModifyOrganizationsPermission(protocol);        
        initializeModifyAreasOfResearchPermission(protocol);
        initializeProtocolProposalDevelopmentLinking();
        initializeCreateProposalDevelopmentPermission(protocol);
    }

    private void initializeModifyProtocolPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolTaskHook(protocol);
        modifyProtocol = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);   
    }

    protected abstract ProtocolTaskBase getNewInstanceModifyProtocolTaskHook(ProtocolBase protocol);

    private void initializeBillablePermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolBillableTaskNewHook(protocol);
        billableReadOnly = !getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    

    public abstract ProtocolTaskBase getNewInstanceModifyProtocolBillableTaskNewHook(ProtocolBase protocol);

    
    private void initializeModifyGeneralInfoPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolGeneralInfoTaskHook(protocol);
        modifyGeneralInfo = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewInstanceModifyProtocolGeneralInfoTaskHook(ProtocolBase protocol);

    
    
    private void initializeModifyFundingSourcePermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolFundingSourceTaskHook(protocol);
        modifyFundingSource = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewInstanceModifyProtocolFundingSourceTaskHook(ProtocolBase protocol);
    
    
    
    private void initializeModifyReferencesPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolReferencesTaskHook(protocol);
        modifyReferences = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewInstanceModifyProtocolReferencesTaskHook(ProtocolBase protocol);

    
    
    private void initializeModifyOrganizationsPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolOrganizationsTaskHook(protocol);
        modifyOrganizations = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewInstanceModifyProtocolOrganizationsTaskHook(ProtocolBase protocol);
    
    private void initializeModifyAreasOfResearchPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceModifyProtocolResearchAreasTaskHook(protocol);
        modifyAreasOfResearch = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewInstanceModifyProtocolResearchAreasTaskHook(ProtocolBase protocol);
    

    
    private void initializeCreateProposalDevelopmentPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = getNewInstanceCreateProposalDevelopmentTaskHook(protocol);
        canCreateProposalDevelopment = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected abstract ProtocolTaskBase getNewInstanceCreateProposalDevelopmentTaskHook(ProtocolBase protocol);

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
    protected abstract Class<? extends ProtocolDocumentBase> getProtocolDocumentClassHook();

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

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public void syncFundingSources(ProtocolBase protocol) {
        if (protocol != null) {
            if (newFundingSource != null) {
                syncFundingSource(newFundingSource);
            }
            for (ProtocolFundingSourceBase source : protocol.getProtocolFundingSources()) {
                syncFundingSource(source);
            }
        }
    }
    
    private void syncFundingSource(ProtocolFundingSourceBase source) {
        ProtocolFundingSourceBase syncedSource = getProtocolFundingSourceService().updateProtocolFundingSource(
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
     * the unsaved ProtocolBase.
     */
    private void prepareRequiredFields() {
        ProtocolBase theProtocol = getProtocol();
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
        ProtocolPersonBase principalInvestigator = getProtocolPersonnelService().getPrincipalInvestigator(getProtocol().getProtocolPersons());
        getProtocolPersonnelService().setLeadUnit(createLeadUnit(), principalInvestigator, getProtocol());
    }
    
    
    
    /**
     * Creates the initial PROTOCOL_CREATED action for a new protocol.
     */
    public void createInitialProtocolAction() {
        if (getProtocol().getProtocolDocument().getDocumentHeader().getWorkflowDocument().isInitiated()) {
            getProtocol().getProtocolActions().clear();
            ProtocolActionBase protocolAction = createProtocolCreatedTypeProtocolActionInstanceHook(getProtocol());
            protocolAction.setComments(PROTOCOL_CREATED);
            getProtocol().getProtocolActions().add(protocolAction);
        }
    }
    
    protected abstract ProtocolActionBase createProtocolCreatedTypeProtocolActionInstanceHook(ProtocolBase protocol);
       
    
    
    public abstract void syncSpecialReviewsWithFundingSources() throws WorkflowException;
    
    protected abstract ProtocolNumberServiceBase getProtocolNumberService();
 
    private KcPersonService getPersonService() {
        if(personService == null) {
            personService = KcServiceLocator.getService(KcPersonService.class);
        }
        return personService;
    }
    
    /**
     * This is used to calculate princiapal investigator ID from fields
     * it's the values set rolodex id or person id depening on 
     * the lookup type
     */
    private void findPrincipalInvestigatorIdFromFields() {
        if (StringUtils.isNotEmpty(getRolodexId())) {
            setPrincipalInvestigatorId(getRolodexId());
            setNonEmployeeFlag(true);
        } else if (StringUtils.isNotEmpty(getPersonId())) {
            setPrincipalInvestigatorId(getPersonId());
            setNonEmployeeFlag(false);
        }
    }

    
    private String getPIUnit(String piId) {
        if(StringUtils.isNotBlank(piId)) {
            if(!nonEmployeeFlag) {
                KcPerson pi = getPersonService().getKcPersonByPersonId(getPrincipalInvestigatorId());
                return pi == null || pi.getUnit() == null ? null : pi.getUnit().getUnitNumber();
            } else {
                if (StringUtils.isNotBlank(rolodexId)) {
                	RolodexContract pi = getRolodexService().getRolodex(Integer.parseInt(rolodexId));
                    return pi == null? null : pi.getOwnedByUnit();
                }
            }
        }
        return null;
    }
    
    private void verifyLeadUnitAutoPopulation() {
        if(StringUtils.isNotEmpty(getProtocol().getPrincipalInvestigatorId()) && StringUtils.isNotEmpty(getProtocol().getLeadUnitNumber())) {
            String piUnit = getPIUnit(getProtocol().getPrincipalInvestigatorId()) ;
            if(piUnit != null && !StringUtils.equals(piUnit, getProtocol().getLeadUnitNumber())) {
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
    
    
    private ProtocolUnitBase createLeadUnit() {
        ProtocolUnitBase ret = null;
        if (StringUtils.isNotEmpty(getLeadUnitNumber()) && StringUtils.isNotEmpty(getLeadUnitName())) {
            ret = createNewProtocolUnitInstanceHook();
            ret.setLeadUnitFlag(true);
            ret.setUnitNumber(getLeadUnitNumber());
            ret.setUnitName(getLeadUnitName());
        }
        return ret;
    }
    
    // hook method
    protected abstract ProtocolUnitBase createNewProtocolUnitInstanceHook();
    
    
    
    private ProtocolPersonBase createPrincipalInvestigator() {
        ProtocolPersonBase pi = null;
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
    protected abstract ProtocolPersonBase createNewProtocolPersonInstanceHook();
   
    private void resolveRequiredFieldsFromBO() {
        ProtocolPersonBase pi = getProtocol().getPrincipalInvestigator();
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
        return KcServiceLocator.getService(UnitService.class);
    }

    public ProtocolLocationBase getNewProtocolLocation() {
        return newProtocolLocation;
    }

    public void setNewProtocolLocation(ProtocolLocationBase newProtocolLocation) {
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

    public ProtocolFundingSourceBase getNewFundingSource() {
        return newFundingSource;
    }

    public void setNewFundingSource(ProtocolFundingSourceBase newFundingSource) {
        this.newFundingSource = newFundingSource;
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
    
    /**
     * Quick method to get the RoleService
     * @return RoleService reference
     */
    protected RoleService getRoleService() {
        return KcServiceLocator.getService(RoleService.class);
    }

    public List<ProtocolFundingSourceBase> getNewProtocolFundingSources() {
        return newProtocolFundingSources;
    }

    public void setNewProtocolFundingSources(List<ProtocolFundingSourceBase> newProtocolFundingSources) {
        this.newProtocolFundingSources = newProtocolFundingSources;
    }    

    public List<ProtocolFundingSourceBase> findNewFundingSources() {
        List<ProtocolFundingSourceBase> fundingSources = new ArrayList<ProtocolFundingSourceBase>();
        for (ProtocolFundingSourceBase fundingSource : getProtocol().getProtocolFundingSources()) {
            if (fundingSource.getProtocolFundingSourceId() == null) {
                fundingSources.add(fundingSource);
            }
            
        }
        return fundingSources;
    }

}
