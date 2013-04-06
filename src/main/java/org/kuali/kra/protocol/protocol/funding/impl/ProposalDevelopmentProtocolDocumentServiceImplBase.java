/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.protocol.ProtocolNumberServiceBase;
import org.kuali.kra.protocol.protocol.funding.ProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.SpecialReviewHelper;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This service creates Proposal Development Document from ProtocolBase for users authorized to create proposal. This created
 * proposal is then added to ProtocolBase Funding sources. 
 */
public abstract class ProposalDevelopmentProtocolDocumentServiceImplBase<GenericProtocolDocument extends ProtocolDocumentBase> 
    implements ProposalDevelopmentProtocolDocumentService<GenericProtocolDocument> {
    private SystemAuthorizationService systemAuthorizationService;
    private KraAuthorizationService kraAuthorizationService;
    private SequenceAccessorService sequenceAccessorService;

    @SuppressWarnings("unchecked")
    @Override
    public GenericProtocolDocument createProtocolDocument(ProposalDevelopmentForm proposalDevelopmentForm) throws Exception
    {
        GenericProtocolDocument protocolDocument = null;
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal();
         if(isAuthorizedCreateProtocol(proposalDevelopmentForm.getSpecialReviewHelper()))
        {
            DocumentService documentService = KRADServiceLocatorWeb.getDocumentService();
            protocolDocument = (GenericProtocolDocument) getProtocolDocumentNewInstanceHook(documentService);
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
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public void populateDocumentOverview(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument)
    {
        ProposalDevelopmentDocument proposalDocument = developmentProposal.getProposalDocument();
        DocumentHeader proposalDocumentHeader = proposalDocument.getDocumentHeader();
        DocumentHeader protocolDocumentHeader = protocolDocument.getDocumentHeader();
      
        protocolDocumentHeader.setDocumentDescription(getProtocolNameSpaceHook() + " " + proposalDocumentHeader.getDocumentDescription());
        protocolDocumentHeader.setExplanation("Document created from Proposal - "+proposalDocumentHeader.getDocumentNumber());
        protocolDocumentHeader.setOrganizationDocumentNumber(proposalDocumentHeader.getOrganizationDocumentNumber());

    }

    public void populateRequiredFields(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument)
    throws Exception
    {
        ProtocolBase protocol = protocolDocument.getProtocol();
        protocol.setProtocolNumber(getProtocolNumberServiceHook().generateProtocolNumber());
        protocol.setSequenceNumber(0);
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber(getSequenceNumberNameHook());
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
    public void initializeAuthorization(ProtocolDocumentBase protocolDocument) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthorizationService.addRole(userId, getProtocolAggregatorHook(), protocolDocument.getProtocol());
        kraAuthorizationService.addRole(userId, getProtocolApproverHook(), protocolDocument.getProtocol());

        List<Role> roles = systemAuthorizationService.getRoles(getProtocolRoleTypeHook());
        for (Role role : roles) {
            List<KcPerson> persons = kraAuthorizationService.getPersonsInRole(protocolDocument.getProtocol(), role.getName());
            for (KcPerson person : persons) {
                if (!StringUtils.equals(person.getPersonId(), userId)) {
                    kraAuthorizationService.addRole(person.getPersonId(), role.getName(), protocolDocument.getProtocol()); 
                }
            }
        }

    }

    @Override
   public void populateProtocolPerson_Investigator(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument)
    {
        ProtocolPersonBase protocolPerson = getProtocolPersonNewInstanceHook(); 
        ProtocolBase protocol = protocolDocument.getProtocol();
        
        protocolPerson.setPersonId(protocol.getPrincipalInvestigatorId());
        protocolPerson.setPersonName(developmentProposal.getPrincipalInvestigatorName());
        protocolPerson.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);

        ProtocolPersonnelService protocolPersonnelService = getProtocolPersonnelService(); 
        protocolPersonnelService.addProtocolPerson(protocol, protocolPerson);
    
    }
    @Override
    public boolean isAuthorizedCreateProtocol(SpecialReviewHelper specialReviewHelper) {
        SpecialReview<?> specialReview = specialReviewHelper.getNewSpecialReview();
        boolean canCreateProposal=false;
        if ( SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()) )
        {
            canCreateProposal = specialReviewHelper.isCanCreateIrbProtocol();
        }
        else if ( SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode()) )
        {
            canCreateProposal = specialReviewHelper.isCanCreateIacucProtocol();
        }
        return canCreateProposal;
    }

    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolFundingSourceService
     */
    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        return getProtocolFundingSourceServiceHook();
    }


    @Override
    public void populateProtocolFundingSource(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument) {
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
}
