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
package org.kuali.kra.irb.protocol.funding.impl;

import org.kuali.coeus.common.framework.compliance.core.ComplianceConstants;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.protocol.ProtocolHelper;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.funding.ProtocolProposalDevelopmentDocumentService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelServiceImpl;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * This service creates Proposal Development Document from Protocol for users authorized to create proposal. This created
 * proposal is then added to Protocol Funding sources. 
 */
public class ProtocolProposalDevelopmentDocumentServiceImpl implements ProtocolProposalDevelopmentDocumentService {

    @Override
    public ProposalDevelopmentDocument createProposalDevelopmentDocument(ProtocolForm protocolForm) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = null;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        if(isAuthorizedCreateProposal(protocolForm.getProtocolHelper()))
        {
            DocumentService docService = KRADServiceLocatorWeb.getDocumentService();       
            proposalDevelopmentDocument = (ProposalDevelopmentDocument) docService.getNewDocument(ProposalDevelopmentDocument.class);
            ProposalDevelopmentService proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
            populateDocumentOverview(protocol, proposalDevelopmentDocument);
            populateRequiredFields(protocol, proposalDevelopmentDocument);
            proposalDevelopmentService.initializeUnitOrganizationLocation(proposalDevelopmentDocument);       
            proposalDevelopmentService.initializeProposalSiteNumbers(proposalDevelopmentDocument);
            populateProposalPerson_Investigator(protocol, proposalDevelopmentDocument);
            populateProposalSpecialReview(protocol, proposalDevelopmentDocument);

            docService.saveDocument(proposalDevelopmentDocument);
            initializeAuthorization(proposalDevelopmentDocument);        
        }
        return proposalDevelopmentDocument;
    }
   
    protected void populateDocumentOverview(Protocol protocol, ProposalDevelopmentDocument proposalDocument)
    {
        ProtocolDocument protocolDocument = (ProtocolDocument) protocol.getProtocolDocument();
        DocumentHeader proposalDocumentHeader = proposalDocument.getDocumentHeader();
        DocumentHeader protocolDocumentHeader = protocolDocument.getDocumentHeader();
      
        proposalDocumentHeader.setDocumentDescription("PD - " + protocolDocumentHeader.getDocumentDescription());
        proposalDocumentHeader.setExplanation("Document created from Protocol - "+protocolDocument.getDocumentNumber());
        proposalDocumentHeader.setOrganizationDocumentNumber(protocolDocumentHeader.getOrganizationDocumentNumber());

    }

    protected void populateRequiredFields(Protocol protocol, ProposalDevelopmentDocument proposalDocument)
    throws Exception
    {
        DevelopmentProposal developmentProposal = proposalDocument.getDevelopmentProposal();

        developmentProposal.setTitle(protocol.getTitle());
        developmentProposal.setOwnedByUnit(protocol.getLeadUnit().getUnit());
        developmentProposal.setOwnedByUnitNumber(protocol.getLeadUnitNumber());
        developmentProposal.setRequestedStartDateInitial(new Date(System.currentTimeMillis()));

        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        String projectEndDateParameter = parameterService.getParameterValueAsString(ProtocolDocument.class, ProtocolProposalDevelopmentDocumentService.PROJECT_END_DATE_NUMBER_OF_YEARS);
        int numberOfYears = Integer.parseInt(projectEndDateParameter);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, numberOfYears);
        calendar.add(Calendar.DATE, -1);
        Date projectEndDate = new Date(calendar.getTimeInMillis());
        developmentProposal.setRequestedEndDateInitial(projectEndDate);

        String activityTypeCode = ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.ACTIVITY_TYPE_CODE_RESEARCH_PARM);
        String proposalTypeCode = ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_NEW_PARM);

        developmentProposal.setActivityTypeCode(activityTypeCode);
        developmentProposal.setProposalTypeCode(proposalTypeCode);
                
        // find sponsor from funding source
        List<ProtocolFundingSourceBase> protocolFundingSources = protocol.getProtocolFundingSources();
        ProtocolFundingSource sponsorProtocolFundingSource = null; 
        for(ProtocolFundingSourceBase protocolFundingSource : protocolFundingSources)
        {
            if ( protocolFundingSource.isSponsorFunding() )
            {
                sponsorProtocolFundingSource = (ProtocolFundingSource) protocolFundingSource;
                break;
            }
        }
        if(sponsorProtocolFundingSource != null)
        {
            developmentProposal.setSponsorCode(sponsorProtocolFundingSource.getFundingSourceNumber());
        }

    }

    /**
     * Initialize the Authorizations for a new proposal.  The initiator/creator
     * is assigned the Aggregator role.
     * @param document the proposal development document
     */
    protected void initializeAuthorization(ProposalDevelopmentDocument document) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
        kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, document);
    }


    public void populateProposalPerson_Investigator(Protocol protocol, ProposalDevelopmentDocument proposalDocument) {
        ProposalPerson proposalPerson = new ProposalPerson();

        proposalPerson.setPersonId(protocol.getPrincipalInvestigatorId());
        PersonEditableService personEditableService = KcServiceLocator.getService(PersonEditableService.class);
        personEditableService.populateContactFieldsFromPersonId(proposalPerson);

        proposalPerson.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        
        proposalPerson.setDevelopmentProposal(proposalDocument.getDevelopmentProposal());
        proposalPerson.setProposalPersonNumber(new Integer(1));

        proposalPerson.setOptInUnitStatus(false);
        proposalPerson.setOptInCertificationStatus(false);
        proposalDocument.getDevelopmentProposal().getProposalPersons().add(proposalPerson);

        KeyPersonnelService keyPersonnelService = (KeyPersonnelServiceImpl) KcServiceLocator.getService(KeyPersonnelService.class);
        keyPersonnelService.populateProposalPerson(proposalPerson, proposalDocument);
        keyPersonnelService.assignLeadUnit(proposalPerson, proposalDocument.getDevelopmentProposal().getOwnedByUnitNumber());
    
    }

    protected void populateProposalSpecialReview(Protocol protocol, ProposalDevelopmentDocument proposalDocument)
    {
    if (protocol != null) {
        Integer specialReviewNumber = proposalDocument.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
        
        ProposalSpecialReview specialReview = new ProposalSpecialReview();
        specialReview.setSpecialReviewNumber(specialReviewNumber);
        specialReview.setSpecialReviewTypeCode(SpecialReviewType.HUMAN_SUBJECTS);
        specialReview.setApprovalTypeCode(SpecialReviewApprovalType.PENDING);
        specialReview.setProtocolNumber(protocol.getProtocolNumber());
        specialReview.setDevelopmentProposal(proposalDocument.getDevelopmentProposal());
        
        specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
        specialReview.setComments(ComplianceConstants.NEW_SPECIAL_REVIEW_COMMENT);
        proposalDocument.getDevelopmentProposal().getPropSpecialReviews().add(specialReview);
        }
    }


    protected boolean isAuthorizedCreateProposal(ProtocolHelper protocolHelper) {
        boolean canCreateProposal = protocolHelper.isCanCreateProposalDevelopment();
        return canCreateProposal;
    }


}