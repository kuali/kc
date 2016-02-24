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
package org.kuali.kra.protocol.protocol.funding.impl;

import org.kuali.coeus.common.framework.compliance.core.ComplianceConstants;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelServiceImpl;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.protocol.ProtocolHelperBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolProposalDevelopmentDocumentService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * This service creates Proposal Development Document from ProtocolBase for users authorized to create proposal. This created
 * proposal is then added to ProtocolBase Funding sources. 
 */
public abstract class ProtocolProposalDevelopmentDocumentServiceImplBase implements ProtocolProposalDevelopmentDocumentService {

    @Override
    public ProposalDevelopmentDocument createProposalDevelopmentDocument(ProtocolFormBase protocolForm) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = null;
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
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
   

    protected void populateDocumentOverview(ProtocolBase protocol, ProposalDevelopmentDocument proposalDocument)
    {
        ProtocolDocumentBase protocolDocument = protocol.getProtocolDocument();
        DocumentHeader proposalDocumentHeader = proposalDocument.getDocumentHeader();
        DocumentHeader protocolDocumentHeader = protocolDocument.getDocumentHeader();
      
        proposalDocumentHeader.setDocumentDescription("PD - " + protocolDocumentHeader.getDocumentDescription());
        proposalDocumentHeader.setExplanation("Document created from ProtocolBase - "+protocolDocument.getDocumentNumber());
        proposalDocumentHeader.setOrganizationDocumentNumber(protocolDocumentHeader.getOrganizationDocumentNumber());

    }


    protected void populateRequiredFields(ProtocolBase protocol, ProposalDevelopmentDocument proposalDocument)
    throws Exception
    {
        DevelopmentProposal developmentProposal = proposalDocument.getDevelopmentProposal();

        developmentProposal.setTitle(protocol.getTitle());
        developmentProposal.setOwnedByUnit(protocol.getLeadUnit().getUnit());
        developmentProposal.setOwnedByUnitNumber(protocol.getLeadUnitNumber());
        developmentProposal.setRequestedStartDateInitial(new Date(System.currentTimeMillis()));

        String projectEndDateParameter = getProjectEndDateNumberOfYearsHook();
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
        ProtocolFundingSourceBase sponsorProtocolFundingSource = null; 
        for(ProtocolFundingSourceBase protocolFundingSource : protocolFundingSources)
        {
            if ( protocolFundingSource.isSponsorFunding() )
            {
                sponsorProtocolFundingSource = protocolFundingSource;
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
        kraAuthService.addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, document);
    }


    protected void populateProposalPerson_Investigator(ProtocolBase protocol, ProposalDevelopmentDocument proposalDocument) {
        ProposalPerson proposalPerson = new ProposalPerson();

        proposalPerson.setPersonId(protocol.getPrincipalInvestigatorId());
        PersonEditableService personEditableService = KcServiceLocator.getService(PersonEditableService.class);
        personEditableService.populateContactFieldsFromPersonId(proposalPerson);

        proposalPerson.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        
        proposalPerson.setDevelopmentProposal(proposalDocument.getDevelopmentProposal());
        proposalPerson.setProposalPersonNumber(new Integer(1));

        proposalPerson.setOptInUnitStatus(true);
        proposalPerson.setOptInCertificationStatus(true);
        proposalDocument.getDevelopmentProposal().getProposalPersons().add(proposalPerson);

        KeyPersonnelService keyPersonnelService = (KeyPersonnelServiceImpl) KcServiceLocator.getService(KeyPersonnelService.class);
        keyPersonnelService.populateProposalPerson(proposalPerson, proposalDocument);
        keyPersonnelService.assignLeadUnit(proposalPerson, proposalDocument.getDevelopmentProposal().getOwnedByUnitNumber());
    
    }


    protected void populateProposalSpecialReview(ProtocolBase protocol, ProposalDevelopmentDocument proposalDocument)
    {
    if (protocol != null) {
        Integer specialReviewNumber = proposalDocument.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER);
        
        ProposalSpecialReview specialReview = new ProposalSpecialReview();
        specialReview.setSpecialReviewNumber(specialReviewNumber);
        specialReview.setSpecialReviewTypeCode(getSpecialReviewTypeHook());
        specialReview.setApprovalTypeCode(SpecialReviewApprovalType.PENDING);
        specialReview.setProtocolNumber(protocol.getProtocolNumber());
        specialReview.setDevelopmentProposal(proposalDocument.getDevelopmentProposal());
        
        specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
        specialReview.setComments(ComplianceConstants.NEW_SPECIAL_REVIEW_COMMENT);
        proposalDocument.getDevelopmentProposal().getPropSpecialReviews().add(specialReview);
        }
    }


    protected boolean isAuthorizedCreateProposal(ProtocolHelperBase protocolHelper) {

        boolean canCreateProposal = protocolHelper.isCanCreateProposalDevelopment();
        return canCreateProposal;
    }


    protected abstract String getProjectEndDateNumberOfYearsHook();
    protected abstract String getSpecialReviewTypeHook();

}
