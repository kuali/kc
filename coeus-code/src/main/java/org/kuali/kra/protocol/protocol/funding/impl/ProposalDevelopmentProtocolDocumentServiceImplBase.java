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
package org.kuali.kra.protocol.protocol.funding.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.protocol.ProtocolNumberServiceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This service creates Proposal Development Document from ProtocolBase for users authorized to create proposal. This created
 * proposal is then added to ProtocolBase Funding sources. 
 */
public abstract class ProposalDevelopmentProtocolDocumentServiceImplBase<T extends ProtocolDocumentBase>  {
    private SystemAuthorizationService systemAuthorizationService;
    private KcAuthorizationService kraAuthorizationService;
    private KcPersonService kcPersonService;
    private SequenceAccessorService sequenceAccessorService;
    private TaskAuthorizationService taskAuthorizationService;
    private DocumentService documentService;

    @SuppressWarnings("unchecked")
    public T createProtocolDocument(ProposalDevelopmentDocument document) throws Exception
    {
        T protocolDocument = null;
        DevelopmentProposal developmentProposal = document.getDevelopmentProposal();
         if(isAuthorizedCreateProtocol(document))
        {
            DocumentService documentService = getDocumentService();
            protocolDocument = (T) getProtocolDocumentNewInstanceHook(documentService);
            populateDocumentOverview(developmentProposal, protocolDocument);
            populateRequiredFields(developmentProposal, protocolDocument);
            populateProtocolPerson_Investigator(developmentProposal, protocolDocument);
            populateProtocolFundingSource(developmentProposal, protocolDocument);
            documentService.saveDocument(protocolDocument);
            initializeAuthorization(protocolDocument);        
        }
        return protocolDocument;
    }
    
    /**
     * Set the System Authorization Service.
     * @param systemAuthorizationService
     */
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }
    
    /**
     * Set the Kra Authorization Service.
     * @param kralAuthorizationService
     */
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    protected void populateDocumentOverview(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument)
    {
        ProposalDevelopmentDocument proposalDocument = developmentProposal.getProposalDocument();
        DocumentHeader proposalDocumentHeader = proposalDocument.getDocumentHeader();
        DocumentHeader protocolDocumentHeader = protocolDocument.getDocumentHeader();
      
        protocolDocumentHeader.setDocumentDescription(getProtocolNameSpaceHook() + " " + proposalDocumentHeader.getDocumentDescription());
        protocolDocumentHeader.setExplanation("Document created from Proposal - "+proposalDocumentHeader.getDocumentNumber());
        protocolDocumentHeader.setOrganizationDocumentNumber(proposalDocumentHeader.getOrganizationDocumentNumber());

    }

    protected void populateRequiredFields(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument)
    throws Exception
    {
        ProtocolBase protocol = protocolDocument.getProtocol();
        protocol.setProtocolNumber(getProtocolNumberServiceHook().generateProtocolNumber());
        protocol.setSequenceNumber(0);
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber(getSequenceNumberNameHook(), protocol.getClass());
        protocol.setProtocolId(nextProtocolId);

        protocol.setTitle(developmentProposal.getTitle());
        protocol.setLeadUnitNumber(developmentProposal.getOwnedByUnitNumber());
        protocol.setPrincipalInvestigatorId(developmentProposal.getPrincipalInvestigator().getPersonId());
        protocol.setProtocolTypeCode(getProtocolTypeCodeHook());
        // populate protocol specific fields
        populateProtocolSpecificFieldsHook(protocol);

        org.kuali.kra.protocol.actions.ProtocolActionBase protocolAction = getProtocolActionNewInstanceHook(protocolDocument.getProtocol(), null, getProtocolActionProtocolCreatedCodeHook());      
        protocolAction.setComments(getProtocolCreatedHook());
        protocolDocument.getProtocol().getProtocolActions().add(protocolAction);

    }

    /**
     * Initialize the Authorizations for a new proposal.  The initiator/creator
     * is assigned the Aggregator role.
     * @param doc the proposal development document
     */
    protected void initializeAuthorization(ProtocolDocumentBase protocolDocument) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthorizationService.addRole(userId, getProtocolAggregatorHook(), protocolDocument.getProtocol());
        kraAuthorizationService.addRole(userId, getProtocolApproverHook(), protocolDocument.getProtocol());

        List<Role> roles = systemAuthorizationService.getRoles(getProtocolRoleTypeHook());
        for (Role role : roles) {
            List<String> users = kraAuthorizationService.getPrincipalsInRole(protocolDocument.getProtocol(), role.getName());
            List<KcPerson> persons = new ArrayList<KcPerson>();
            for(String uid : users) {
                KcPerson person = kcPersonService.getKcPersonByPersonId(uid);
                if (person != null && person.getActive()) {
                    persons.add(person);
                }
            }

            for (KcPerson person : persons) {
                if (!StringUtils.equals(person.getPersonId(), userId)) {
                    kraAuthorizationService.addRole(person.getPersonId(), role.getName(), protocolDocument.getProtocol()); 
                }
            }
        }

    }

    protected void populateProtocolPerson_Investigator(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument)
    {
        ProtocolPersonBase protocolPerson = getProtocolPersonNewInstanceHook(); 
        ProtocolBase protocol = protocolDocument.getProtocol();
        
        protocolPerson.setPersonId(protocol.getPrincipalInvestigatorId());
        protocolPerson.setPersonName(developmentProposal.getPrincipalInvestigatorName());
        protocolPerson.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);

        ProtocolPersonnelService protocolPersonnelService = getProtocolPersonnelService(); 
        protocolPersonnelService.addProtocolPerson(protocol, protocolPerson);
    
    }

    public boolean isAuthorizedCreateProtocol(ProposalDevelopmentDocument document) {       
        ProposalTask irbTask = new ProposalTask(getCreateProposalTaskNameHook(), document);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), irbTask);
    }
    
    protected abstract String getCreateProposalTaskNameHook();

    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolFundingSourceService
     */
    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        return getProtocolFundingSourceServiceHook();
    }


    protected void populateProtocolFundingSource(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument) {
        ProtocolBase protocol = protocolDocument.getProtocol();

        List<ProtocolFundingSourceBase> protocolFundingSources = protocol.getProtocolFundingSources();
        ProtocolFundingSourceService protocolFundingSourceService = (ProtocolFundingSourceService) getProtocolFundingSourceService(); 
        ProtocolFundingSourceBase protocolFundingSource = protocolFundingSourceService.updateProtocolFundingSource(FundingSourceType.SPONSOR, developmentProposal.getSponsorCode(), developmentProposal.getSponsorName());
        protocolFundingSource.setProtocol(protocolDocument.getProtocol());
        protocolFundingSources.add(protocolFundingSource);
        
    }

    /**
     * Gets the ProtocolBase Personnel Service.
     * @return the ProtocolBase Personnel Service
     */
    protected ProtocolPersonnelService getProtocolPersonnelService() {
        return getProtocolPersonnelServiceHook();
    }
    

    protected abstract ProtocolDocumentBase getProtocolDocumentNewInstanceHook(DocumentService documentService) throws WorkflowException;
    protected abstract String getProtocolActionProtocolCreatedCodeHook();
    protected abstract String getProtocolTypeCodeHook();
    protected abstract void populateProtocolSpecificFieldsHook(ProtocolBase protocol);
    protected abstract ProtocolNumberServiceBase getProtocolNumberServiceHook();
    protected abstract ProtocolActionBase getProtocolActionNewInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String protocolActionTypeCode);
    protected abstract String getProtocolAggregatorHook();
    protected abstract String getProtocolApproverHook();
    protected abstract String getProtocolRoleTypeHook();
    protected abstract String getProtocolNameSpaceHook();
    protected abstract String getSequenceNumberNameHook();
    protected abstract ProtocolPersonBase getProtocolPersonNewInstanceHook();
    protected abstract String getProtocolCreatedHook();
    protected abstract ProtocolPersonnelService getProtocolPersonnelServiceHook();
    protected abstract ProtocolFundingSourceService getProtocolFundingSourceServiceHook() ;

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return taskAuthorizationService;
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
