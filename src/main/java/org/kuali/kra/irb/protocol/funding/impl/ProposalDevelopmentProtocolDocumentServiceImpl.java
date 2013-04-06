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
package org.kuali.kra.irb.protocol.funding.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.irb.personnel.ProtocolPersonnelServiceImpl;
import org.kuali.kra.irb.protocol.ProtocolNumberService;
import org.kuali.kra.irb.protocol.funding.ProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.SpecialReviewHelper;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This service creates Proposal Development Document from Protocol for users authorized to create proposal. This created
 * proposal is then added to Protocol Funding sources. 
 */
public class ProposalDevelopmentProtocolDocumentServiceImpl implements ProposalDevelopmentProtocolDocumentService {
    private DocumentService documentService;
    private SystemAuthorizationService systemAuthorizationService;
    private KraAuthorizationService kraAuthorizationService;
    private ProtocolNumberService protocolNumberService;
    private SequenceAccessorService sequenceAccessorService;


    @Override
    public ProtocolDocument createProtocolDocument(ProposalDevelopmentForm proposalDevelopmentForm) throws Exception
    {
        ProtocolDocument protocolDocument = null;
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal();
         if(isAuthorizedCreateProtocol(proposalDevelopmentForm.getSpecialReviewHelper()))
        {
            protocolDocument = (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
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
     * Set the Document Service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
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
    
    /**
     * Set the Protocol Number Service
     * @param protocolNumberService the Protocol Number Service
     */
    public void setProtocolNumberService(ProtocolNumberService protocolNumberService) {
        this.protocolNumberService = protocolNumberService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public void populateDocumentOverview(DevelopmentProposal developmentProposal, ProtocolDocument protocolDocument)
    {
        ProposalDevelopmentDocument proposalDocument = developmentProposal.getProposalDocument();
        DocumentHeader proposalDocumentHeader = proposalDocument.getDocumentHeader();
        DocumentHeader protocolDocumentHeader = protocolDocument.getDocumentHeader();
      
        protocolDocumentHeader.setDocumentDescription("IRB - " + proposalDocumentHeader.getDocumentDescription());
        protocolDocumentHeader.setExplanation("Document created from Proposal - "+proposalDocumentHeader.getDocumentNumber());
        protocolDocumentHeader.setOrganizationDocumentNumber(proposalDocumentHeader.getOrganizationDocumentNumber());

    }

    public void populateRequiredFields(DevelopmentProposal developmentProposal, ProtocolDocument protocolDocument)
    throws Exception
    {
        Protocol protocol = protocolDocument.getProtocol();

        protocol.setProtocolNumber(protocolNumberService.generateProtocolNumber());
        protocol.setSequenceNumber(0);
        Long nextProtocolId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
        protocol.setProtocolId(nextProtocolId);

        protocol.setTitle(developmentProposal.getTitle());
        protocol.setLeadUnitNumber(developmentProposal.getOwnedByUnitNumber());
        protocol.setPrincipalInvestigatorId(developmentProposal.getPrincipalInvestigator().getPersonId());

        org.kuali.kra.irb.actions.ProtocolAction protocolAction = 
                new org.kuali.kra.irb.actions.ProtocolAction(protocol, null, ProtocolActionType.PROTOCOL_CREATED);
          protocolAction.setComments(PROTOCOL_CREATED);
          protocol.getProtocolActions().add(protocolAction);

    }

    /**
     * Initialize the Authorizations for a new proposal.  The initiator/creator
     * is assigned the Aggregator role.
     * @param doc the proposal development document
     */
    public void initializeAuthorization(ProtocolDocument protocolDocument) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        kraAuthorizationService.addRole(userId, RoleConstants.PROTOCOL_AGGREGATOR, protocolDocument.getProtocol());
        kraAuthorizationService.addRole(userId, RoleConstants.PROTOCOL_APPROVER, protocolDocument.getProtocol());

        List<Role> roles = systemAuthorizationService.getRoles(RoleConstants.PROTOCOL_ROLE_TYPE);
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
   public void populateProtocolPerson_Investigator(DevelopmentProposal developmentProposal, ProtocolDocument protocolDocument)
    {
        ProtocolPerson protocolPerson = new ProtocolPerson();
        Protocol protocol = protocolDocument.getProtocol();
        
        protocolPerson.setPersonId(protocol.getPrincipalInvestigatorId());
        protocolPerson.setPersonName(developmentProposal.getPrincipalInvestigatorName());
        protocolPerson.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);

        ProtocolPersonnelServiceImpl protocolPersonnelService = (ProtocolPersonnelServiceImpl) KraServiceLocator.getService(ProtocolPersonnelService.class);
        protocolPersonnelService.addProtocolPerson(protocol, protocolPerson);
    
    }

    @Override
    public boolean isAuthorizedCreateProtocol(SpecialReviewHelper specialReviewHelper) {
        boolean canCreateProposal = specialReviewHelper.isCanCreateIrbProtocol();
        return canCreateProposal;
    }

    /**
     * This method is to get protocol location service
     * 
     * @return ProtocolFundingSourceService
     */
    private ProtocolFundingSourceService getProtocolFundingSourceService() {
        return (ProtocolFundingSourceService) KraServiceLocator.getService(ProtocolFundingSourceService.class);
    }


    @Override
    public void populateProtocolFundingSource(DevelopmentProposal developmentProposal, ProtocolDocument protocolDocument) {
        Protocol protocol = protocolDocument.getProtocol();

        List<ProtocolFundingSourceBase> protocolFundingSources = protocol.getProtocolFundingSources();
        ProtocolFundingSourceServiceImpl protocolFundingSourceServiceImpl = (ProtocolFundingSourceServiceImpl) getProtocolFundingSourceService(); 
        ProtocolFundingSource protocolFundingSource = (ProtocolFundingSource) protocolFundingSourceServiceImpl.updateProtocolFundingSource(FundingSourceType.SPONSOR, developmentProposal.getSponsorCode(), developmentProposal.getSponsorName());
        protocolFundingSource.setProtocol(protocolDocument.getProtocol());
        protocolFundingSources.add(protocolFundingSource);
        
    }
}