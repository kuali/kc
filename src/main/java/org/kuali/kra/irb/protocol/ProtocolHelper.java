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
package org.kuali.kra.irb.protocol;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolHelper implements Serializable {
    
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
    
    private boolean editProtocolFundingSourceName = false;

    
    public boolean isEditProtocolFundingSourceName() {
        return editProtocolFundingSourceName;
    }

    public void setEditProtocolFundingSourceName(boolean editProtocolFundingSourceName) {
        this.editProtocolFundingSourceName = editProtocolFundingSourceName;
    }

    //Must be set to true, in case if order of method call in prepareview() is altered, functionality of billable will not break. 
    private boolean displayBillable = true;

    public ProtocolHelper(ProtocolForm form) {
        this.form = form;
        setNewProtocolLocation(new ProtocolLocation());
    }    
    
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
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    private void initializeConfigurationParams() {        
        setReferenceId1Label(getParameterValue(Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID1));
        setReferenceId2Label(getParameterValue(Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID2));
        boolean flag = (getParameterValue(Constants.PARAMETER_MODULE_PROTOCOL_BILLABLE).equalsIgnoreCase("Y") ? true : false);
        setDisplayBillable(flag);
    }

    /**
     * This method initializes permission related to form.
     * Note: Billable permission is only set if displayBillable is true.
     * Reason: For Institution who does not bill.  
     * @param protocol
     */
    private void initializePermissions(Protocol protocol) {
        initializeModifyProtocolPermission(protocol);
        if(displayBillable)
            initializeBillablePermission(protocol);   
    }

    private void initializeModifyProtocolPermission(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        modifyProtocol = getTaskAuthorizationService().isAuthorized(getUsername(), task);   
    }

    private void initializeBillablePermission(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_BILLABLE, protocol);
        billableReadOnly = !getTaskAuthorizationService().isAuthorized(getUsername(), task);
    }

    /**
     * This method is to get parameter value
     * @return parameter value
     */
    private String getParameterValue(String parameterName) {
        KualiConfigurationService configService = getService(KualiConfigurationService.class);
        return (configService.getParameter(Constants.PARAMETER_MODULE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, parameterName)).getParameterValue();        
    }

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

    private String getUsername() {
         UniversalUser user = new UniversalUser (GlobalVariables.getUserSession().getPerson());
         return user.getPersonUserIdentifier();
    }

    public void syncFundingSources(Protocol protocol) {
        if (protocol != null) {
            if (newFundingSource != null) {
                
                if (newFundingSource.getFundingSourceType() != null
                        && newFundingSource.getFundingSourceType().getFundingSourceTypeCode() != null
                        && StringUtils.isNotEmpty(newFundingSource.getFundingSource()) ) {

                    ProtocolFundingSource syncedSource = getProtocolFundingSourceService().calculateProtocolFundingSource(
                            newFundingSource.getFundingSourceType().getFundingSourceTypeCode().toString(),
                            newFundingSource.getFundingSource(), newFundingSource.getFundingSourceName(),
                            newFundingSource.getFundingSourceTitle());
                    newFundingSource.setFundingSourceName(syncedSource.getFundingSourceName());
                    newFundingSource.setFundingSourceTitle(syncedSource.getFundingSourceTitle());
                }
            }
            for (ProtocolFundingSource source : protocol.getProtocolFundingSources()) {
                if (source.getFundingSourceType() != null && source.getFundingSourceType().getFundingSourceTypeCode() != null
                        && source.getFundingSource() != null) {

                    ProtocolFundingSource syncedSource = getProtocolFundingSourceService().calculateProtocolFundingSource(
                            source.getFundingSourceType().getFundingSourceTypeCode().toString(), source.getFundingSource(),
                            source.getFundingSourceName(), source.getFundingSourceTitle());
                    source.setFundingSourceName(syncedSource.getFundingSourceName());
                    source.setFundingSourceTitle(syncedSource.getFundingSourceTitle());
                }
            }
        }
    }
    
    public ProtocolFundingSourceService getProtocolFundingSourceService() {
        ProtocolFundingSourceService theService = 
            (ProtocolFundingSourceService) KraServiceLocator.getService("protocolFundingSourceService");
        return theService;
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
        } else {
            resolveRequiredFieldsFromBO();
        }
    }
    
    /**
     * This method is a form helper for protocol. It is needed to push the
     * transient form fields into principal investigator /lead unit.
     * Due to table structure, these are stored as lists in protocol and 
     * protocol investigator respectively, but aren't provided (by design) an explicit
     * "add" element in the required fields panel view like most growing lists
     */
    public void prepareRequiredFieldsForSave() {

        findPrincipalInvestigatorIdFromFields();
        findAndSetLeadUnitFromFields();

        // since we are saving, we will always clear the PI and reset from field values
        /* check if PI is set otherwise it will throw exception */
        if(!StringUtils.isBlank(getPrincipalInvestigatorId())) {
            getProtocolPersonnelService().updatePrincipalInvestigator(getProtocol(),createPrincipalInvestigator());
        }
        getProtocol().setLeadUnitForValidation(createLeadUnit());

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
    
     /**
      * This is used to calculate lead unit info from fields
      * it's the values set into form or (if unset during lookup)
      * from the lookup values returned for PI's home unit
      */
    private void findAndSetLeadUnitFromFields() {

        getProtocol().setLeadUnitNumber(getLeadUnitNumber());
        setLeadUnitName(getUnitService().getUnitName(getLeadUnitNumber()));
        if (StringUtils.isEmpty(getLeadUnitName()) 
                && StringUtils.isEmpty(getLeadUnitNumber()) ) {
            setLeadUnitNumber(getLookupUnitNumber());
            setLeadUnitName(getLookupUnitName());
        }
        setLookupUnitNumber(null);
        setLookupUnitName(null);
    }

    private ProtocolUnit createLeadUnit() {
        ProtocolUnit ret = null;
        if (StringUtils.isNotEmpty(getLeadUnitNumber()) && StringUtils.isNotEmpty(getLeadUnitName())) {
            ret = new ProtocolUnit();
            ret.setLeadUnitFlag(true);
            ret.setUnitNumber(getLeadUnitNumber());
            ret.setUnitName(getLeadUnitName());
        }
        return ret;
    }
    
    private ProtocolPerson createPrincipalInvestigator() {
        ProtocolPerson pi = null;

        pi = new ProtocolPerson();
        pi.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        pi.setProtocolNumber("0");
        pi.setSequenceNumber(0);
        pi.refreshReferenceObject("protocolPersonRole");
        if (isNonEmployeeFlag()) {
            pi.refreshReferenceObject("rolodex");
            pi.setRolodexId(Integer.valueOf(principalInvestigatorId));
        } else {
            pi.refreshReferenceObject("person");
            pi.setPersonId(principalInvestigatorId);
        }
        pi.setPersonName(getPrincipalInvestigatorName());

        ProtocolUnit unit = createLeadUnit();
        if (unit != null) {
            unit.setPersonId(pi.getPersonId());
            unit.refreshReferenceObject("unit");
            pi.getProtocolUnits().add(unit);
        }

        return pi;
    }
    
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

    public ProtocolPersonnelService getProtocolPersonnelService() {
        ProtocolPersonnelService theService = 
            (ProtocolPersonnelService) KraServiceLocator.getService(ProtocolPersonnelService.class);
        return theService;
    }
    
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
}
