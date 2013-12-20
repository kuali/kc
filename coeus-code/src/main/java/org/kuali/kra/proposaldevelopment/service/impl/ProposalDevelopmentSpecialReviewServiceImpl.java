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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.service.impl.SpecialReviewServiceImpl;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolProposalDevelopmentProtocolDocumentServiceImpl;
import org.kuali.kra.iacuc.specialreview.IacucProtocolSpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.funding.ProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentSpecialReviewService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KualiRuleService;

public class ProposalDevelopmentSpecialReviewServiceImpl implements ProposalDevelopmentSpecialReviewService {
    
    private static final String PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER = "irb.protocol.development.proposal.linking.enabled";
    private static final String IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER = "iacuc.protocol.proposal.development.linking.enabled";
    
    private ProposalDevelopmentProtocolDocumentService proposalDevelopmentProtocolDocumentService;
    private IacucProtocolProposalDevelopmentProtocolDocumentService iacucProtocolProposalDevelopmentProtocolDocumentService;
    private ProtocolSpecialReviewService protocolSpecialReviewService;
    private IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService;
    
    private ParameterService parameterService;

    @Override
    public boolean createProtocol(ProposalSpecialReview specialReview, ProposalDevelopmentDocument document) throws Exception {
        if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
            if (isIrbLinkingEnabled()) {
                ProposalDevelopmentProtocolDocumentService service = getProposalDevelopmentProtocolDocumentService(); 
                ProtocolDocument protocolDocument = service.createProtocolDocument(document);
                if (protocolDocument != null )
                {
                    specialReview.setSpecialReviewTypeCode(SpecialReviewType.HUMAN_SUBJECTS);
                    Integer specialReviewNumber = document.getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER);
                    specialReview.setSpecialReviewNumber(specialReviewNumber);
                    specialReview.setApprovalTypeCode(SpecialReviewApprovalType.PENDING);
                    specialReview.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
                    specialReview.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
                    specialReview.setProtocolStatus(protocolDocument.getProtocol().getProtocolStatus().getDescription());
                    specialReview.setComments(SpecialReviewServiceImpl.NEW_SPECIAL_REVIEW_COMMENT);

                    prepareProtocolLinkViewFields(specialReview);
                    KualiRuleService ruleService = KraServiceLocator.getService(KualiRuleService.class);
                    if (ruleService.applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(document, specialReview, 
                            document.getDevelopmentProposal().getPropSpecialReviews(), isIrbLinkingEnabled()))) {
                        document.getDevelopmentProposal().getPropSpecialReviews().add(specialReview);
                        return true;
                    }
                }
            }
        } else if (SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode())) {
            if (isIacucLinkingEnabled()) {
                IacucProtocolProposalDevelopmentProtocolDocumentService service = getIacucProtocolProposalDevelopmentProtocolDocumentService(); 
                ProtocolDocumentBase protocolDocument = service.createProtocolDocument(document);
                if (protocolDocument != null) {
                    specialReview.setSpecialReviewTypeCode(SpecialReviewType.ANIMAL_USAGE);
                    Integer specialReviewNumber = document.getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER);
                    specialReview.setSpecialReviewNumber(specialReviewNumber);
                    specialReview.setApprovalTypeCode(SpecialReviewApprovalType.PENDING);
                    specialReview.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
                    specialReview.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
                    specialReview.setProtocolStatus(protocolDocument.getProtocol().getProtocolStatus().getDescription());
                    specialReview.setComments(SpecialReviewServiceImpl.NEW_SPECIAL_REVIEW_COMMENT);
        
                    prepareProtocolLinkViewFields(specialReview);
                    KualiRuleService ruleService = KraServiceLocator.getService(KualiRuleService.class);
                    if (ruleService.applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(document, specialReview, 
                            document.getDevelopmentProposal().getPropSpecialReviews(), isIacucLinkingEnabled()))) {
                        document.getDevelopmentProposal().getPropSpecialReviews().add(specialReview);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Prepares the linked fields on the Special Review that are pulled directly from the Protocol and not from the local object.
     * @param specialReview the Special Review to update
     */
    public void prepareProtocolLinkViewFields(ProposalSpecialReview specialReview) {
        if (isIrbLinkingEnabled() || isIacucLinkingEnabled()) {
            if (specialReview != null && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                ProtocolSpecialReviewService protocolSpecialReviewService = getProtocolSpecialReviewService();
                protocolSpecialReviewService.populateSpecialReview(specialReview);
            }
            else if (specialReview != null && SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode())) {
                IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService = getIacucProtocolSpecialReviewService();
                iacucProtocolSpecialReviewService.populateSpecialReview(specialReview);
            }

        }
    }

    @Override
    public boolean isIrbLinkingEnabled() {
        return getParameterService().getParameterValueAsBoolean(ProtocolDocument.class, PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
    }

    @Override
    public boolean isIacucLinkingEnabled() {
        return getParameterService().getParameterValueAsBoolean(IacucProtocolDocument.class, IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER);
    }

    @Override
    public boolean canCreateIrbProtocol(ProposalDevelopmentDocument document) {
        return getProposalDevelopmentProtocolDocumentService().isAuthorizedCreateProtocol(document); 
    }
    
    public boolean canCreateIacucProtocol(ProposalDevelopmentDocument document) {
        return getIacucProtocolProposalDevelopmentProtocolDocumentService().isAuthorizedCreateProtocol(document);
    }

    protected ProposalDevelopmentProtocolDocumentService getProposalDevelopmentProtocolDocumentService() {
        return proposalDevelopmentProtocolDocumentService;
    }

    public void setProposalDevelopmentProtocolDocumentService(
            ProposalDevelopmentProtocolDocumentService proposalDevelopmentProtocolDocumentService) {
        this.proposalDevelopmentProtocolDocumentService = proposalDevelopmentProtocolDocumentService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public IacucProtocolProposalDevelopmentProtocolDocumentService getIacucProtocolProposalDevelopmentProtocolDocumentService() {
        return iacucProtocolProposalDevelopmentProtocolDocumentService;
    }

    public void setIacucProtocolProposalDevelopmentProtocolDocumentService(
            IacucProtocolProposalDevelopmentProtocolDocumentService iacucProtocolProposalDevelopmentProtocolDocumentService) {
        this.iacucProtocolProposalDevelopmentProtocolDocumentService = iacucProtocolProposalDevelopmentProtocolDocumentService;
    }

    protected ProtocolSpecialReviewService getProtocolSpecialReviewService() {
        return protocolSpecialReviewService;
    }

    public void setProtocolSpecialReviewService(ProtocolSpecialReviewService protocolSpecialReviewService) {
        this.protocolSpecialReviewService = protocolSpecialReviewService;
    }

    protected IacucProtocolSpecialReviewService getIacucProtocolSpecialReviewService() {
        return iacucProtocolSpecialReviewService;
    }

    public void setIacucProtocolSpecialReviewService(IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService) {
        this.iacucProtocolSpecialReviewService = iacucProtocolSpecialReviewService;
    }

}
