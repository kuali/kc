/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.protocol.protocol.ProtocolHelperBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolHelper extends ProtocolHelperBase {
    

    private static final long serialVersionUID = 1236584866243874926L;

    private ProtocolParticipant newProtocolParticipant;

    private boolean modifySubjects = false;
    
    public ProtocolHelper(ProtocolForm form) {        
        
        super(form);
        setNewProtocolParticipant(new ProtocolParticipant());
    }    
    
    protected Protocol getProtocol() {
        return (Protocol) super.getProtocol();
    }

    /**
     * This method initializes permission related to form.
     * Note: Billable permission is only set if displayBillable is true.
     * Reason: For Institution who does not bill.  
     * @param protocol
     */
    protected void initializePermissions(ProtocolBase protocol) {
        
        super.initializePermissions(protocol);
        initializeModifySubjectsPermission((Protocol) protocol);
    }
    
    private void initializeModifySubjectsPermission(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_SUBJECTS, protocol);
        modifySubjects = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    /**
     * Synchronizes the information between this Protocol's Funding Sources and any Institutional Proposal or Award Special Review entries.
     */
    public void syncSpecialReviewsWithFundingSources() throws WorkflowException {
        for (ProtocolFundingSourceBase protocolFundingSource : getProtocol().getProtocolFundingSources()) {
            String fundingSourceNumber = ((ProtocolFundingSource) protocolFundingSource).getFundingSourceNumber();
            String fundingSourceTypeCode = ((ProtocolFundingSource) protocolFundingSource).getFundingSourceTypeCode();
            String protocolNumber = getProtocol().getProtocolNumber();
            
            if (!getSpecialReviewService().isLinkedToSpecialReview(fundingSourceNumber, fundingSourceTypeCode, protocolNumber)) {
                Date applicationDate = getProtocol().getProtocolSubmission().getSubmissionDate();
                Date approvalDate = getProtocol().getLastApprovalDate() == null ? getProtocol().getApprovalDate() : getProtocol().getLastApprovalDate();
                Date expirationDate = getProtocol().getExpirationDate();
                List<String> exemptionTypeCodes = new ArrayList<String>();
                for (ProtocolExemptStudiesCheckListItem checkListItem : ( (ProtocolSubmission) getProtocol().getProtocolSubmission()).getExemptStudiesCheckList()) {
                    exemptionTypeCodes.add(checkListItem.getExemptStudiesCheckListCode());
                }
                getSpecialReviewService().addSpecialReviewForProtocolFundingSource(
                    fundingSourceNumber, fundingSourceTypeCode, protocolNumber, applicationDate, approvalDate, expirationDate, exemptionTypeCodes);
            }
        }
        
        for (ProtocolFundingSourceBase protocolFundingSource : getDeletedProtocolFundingSources()) {
            String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
            String fundingSourceTypeCode = String.valueOf(protocolFundingSource.getFundingSourceTypeCode());
            String protocolNumber = getProtocol().getProtocolNumber();
            
            getSpecialReviewService().deleteSpecialReviewForProtocolFundingSource(fundingSourceNumber, fundingSourceTypeCode, protocolNumber);
        }
        
        getDeletedProtocolFundingSources().clear();
    }
    
    protected ProtocolNumberService getProtocolNumberService() {
        return KcServiceLocator.getService(ProtocolNumberService.class);
    }
    
    public ProtocolPersonnelService getProtocolPersonnelService() {
        ProtocolPersonnelService theService = KcServiceLocator.getService(ProtocolPersonnelService.class);
        return theService;
    }
        
    public ProtocolParticipant getNewProtocolParticipant() {
        return newProtocolParticipant;
    }

    public void setNewProtocolParticipant(ProtocolParticipant newProtocolParticipant) {
        this.newProtocolParticipant = newProtocolParticipant;
    }
 
    public boolean getModifySubjects() {
        return modifySubjects;
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
        
    protected boolean getProtocolProposalDevelopmentLinkingHook() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook() {
        return ProtocolFundingSourceService.class;
    }

    @Override
    protected ProtocolFundingSourceBase getNewProtocolFundingSourceInstanceHook() {
        return new ProtocolFundingSource();
    }

    @Override
    protected ProtocolLocationBase getNewProtocolLocationInstanceHook() {
        return new ProtocolLocation();
    }

    @Override
    protected String getBillableParameterHook() {
        return Constants.PARAMETER_MODULE_PROTOCOL_BILLABLE;
    }

    @Override
    protected String getReferenceID1ParameterNameHook() {
        return Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID1;
    }

    @Override
    protected String getReferenceID2ParameterNameHook() {
        return Constants.PARAMETER_MODULE_PROTOCOL_REFERENCEID2;
    }

    @Override
    protected ProtocolTaskBase getNewInstanceModifyProtocolTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL, (Protocol) protocol);
    }

    @Override
    public ProtocolTaskBase getNewInstanceModifyProtocolBillableTaskNewHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_BILLABLE, (Protocol) protocol);
    }

    @Override
    protected ProtocolTaskBase getNewInstanceModifyProtocolGeneralInfoTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_GENERAL_INFO, (Protocol) protocol);
    }

    @Override
    protected ProtocolTaskBase getNewInstanceModifyProtocolFundingSourceTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_FUNDING_SOURCE, (Protocol) protocol);
    }

    @Override
    protected ProtocolTaskBase getNewInstanceModifyProtocolReferencesTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_REFERENCES, (Protocol) protocol);
    }

    @Override
    protected ProtocolTaskBase getNewInstanceModifyProtocolOrganizationsTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_ORGANIZATIONS, (Protocol) protocol);
    }

    @Override
    protected ProtocolTaskBase getNewInstanceModifyProtocolResearchAreasTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL_AREAS_OF_RESEARCH, (Protocol) protocol);
    }

    @Override
    protected ProtocolTaskBase getNewInstanceCreateProposalDevelopmentTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(ProtocolTask.CREATE_PROPOSAL_FOR_IRB_PROTOCOL, (Protocol) protocol);
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentClassHook() {
        return ProtocolDocument.class;
    }

    @Override
    protected ProtocolActionBase createProtocolCreatedTypeProtocolActionInstanceHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol) protocol, null, ProtocolActionType.PROTOCOL_CREATED);
    }

    @Override
    protected ProtocolUnitBase createNewProtocolUnitInstanceHook() {
        return new ProtocolUnit();
    }

    @Override
    protected ProtocolPersonBase createNewProtocolPersonInstanceHook() {
        return new ProtocolPerson();
    }
    
}
