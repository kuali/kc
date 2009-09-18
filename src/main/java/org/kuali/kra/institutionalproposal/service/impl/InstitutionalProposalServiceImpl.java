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
package org.kuali.kra.institutionalproposal.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.exception.InstitutionalProposalCreationException;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InstitutionalProposalServiceImpl implements InstitutionalProposalService {
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private VersioningService versioningService;
    
    /* Public API methods */
    
    public String createInstitutionalProposal(DevelopmentProposal developmentProposal) {
        GlobalVariables.getUserSession().setBackdoorUser("quickstart"); // Should be KC system user
        
        try {
            
            InstitutionalProposalDocument institutionalProposalDocument = mergeProposals(new InstitutionalProposal(), developmentProposal);
            documentService.blanketApproveDocument(institutionalProposalDocument, 
                        "Autoenerated Institutional Proposal from Development Proposal " + developmentProposal.getProposalNumber(), 
                        new ArrayList<Object>());
            
            return institutionalProposalDocument.getInstitutionalProposal().getProposalNumber();
            
        } catch (WorkflowException ex) {
            throw new InstitutionalProposalCreationException("Caught workflow exception creating new Institutional Proposal", ex);
        } finally {
            resetUserSession();
        }
    }
    
    public String createPendingInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal) {
        GlobalVariables.getUserSession().setBackdoorUser("quickstart"); // Should be KC system user
        
        try {
            
            InstitutionalProposalDocument newInstitutionalProposalDocument = versionProposal(proposalNumber, developmentProposal);
            newInstitutionalProposalDocument.getInstitutionalProposal().setProposalSequenceStatus(VersionStatus.PENDING.toString());
            documentService.saveDocument(newInstitutionalProposalDocument, InstitutionalProposalDocument.class);
            
            return newInstitutionalProposalDocument.getInstitutionalProposal().getSequenceNumber().toString();
            
        } catch (WorkflowException we) {
            throw new InstitutionalProposalCreationException("Caught workflow exception creating new Institutional Proposal", we);
        } catch (VersionException ve) {
            throw new InstitutionalProposalCreationException("Caught version exception creating new Institutional Proposal", ve);
        }
        finally {
            resetUserSession();
        }
    }
    
    public String createActiveInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal) {
        GlobalVariables.getUserSession().setBackdoorUser("quickstart"); // Should be KC system user
        
        try {
            
            InstitutionalProposalDocument newInstitutionalProposalDocument = versionProposal(proposalNumber, developmentProposal);
            newInstitutionalProposalDocument.getInstitutionalProposal().setProposalSequenceStatus(VersionStatus.ACTIVE.toString());
            documentService.blanketApproveDocument(newInstitutionalProposalDocument, 
                    "Autogenerated Institutional Proposal from Development Proposal " + developmentProposal.getProposalNumber(), 
                    new ArrayList<Object>());
            
            return newInstitutionalProposalDocument.getInstitutionalProposal().getSequenceNumber().toString();
            
        } catch (WorkflowException we) {
            throw new InstitutionalProposalCreationException("Caught workflow exception creating new Institutional Proposal", we);
        } catch (VersionException ve) {
            throw new InstitutionalProposalCreationException("Caught version exception creating new Institutional Proposal", ve);
        }
        finally {
            resetUserSession();
        }
    }
    
    /* Local helper methods */
    
    @SuppressWarnings("unchecked")
    protected InstitutionalProposal getInstitutionalProposal(String proposalNumber) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put(InstitutionalProposal.PROPOSAL_NUMBER_PROPERTY_STRING, proposalNumber);
        criteria.put(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING, VersionStatus.ACTIVE.toString());
        Collection results = businessObjectService.findMatching(InstitutionalProposal.class, criteria);
        if (results.isEmpty()) {
            return null;
        }
        
        return (InstitutionalProposal) results.toArray()[0];
    }
    
    private InstitutionalProposalDocument versionProposal(String proposalNumber, DevelopmentProposal developmentProposal)
        throws VersionException, WorkflowException {
        
        InstitutionalProposal currentVersion = getInstitutionalProposal(proposalNumber);
        if (currentVersion == null) {
            // Throw checked exception
        }
        InstitutionalProposal newVersion = versioningService.createNewVersion(currentVersion);
        InstitutionalProposalDocument newInstitutionalProposalDocument = mergeProposals(newVersion, developmentProposal);
        return newInstitutionalProposalDocument;
    }
    
    private InstitutionalProposalDocument mergeProposals(InstitutionalProposal institutionalProposal, DevelopmentProposal developmentProposal)
        throws WorkflowException {
        
        //Budget budget = developmentProposal.get
        
        InstitutionalProposalDocument institutionalProposalDocument = 
            (InstitutionalProposalDocument) documentService.getNewDocument(InstitutionalProposalDocument.class);
        
        institutionalProposalDocument.getDocumentHeader().setDocumentDescription(
                "Generated by Dev Proposal" + developmentProposal.getProposalNumber());
        
        // Base fields
        institutionalProposal.setProposalTypeCode(Integer.parseInt(developmentProposal.getProposalTypeCode()));
        institutionalProposal.setActivityTypeCode(developmentProposal.getActivityTypeCode());
        institutionalProposal.setStatusCode(1);  // TODO app constant
        institutionalProposal.setSponsorCode(developmentProposal.getSponsorCode());
        institutionalProposal.setTitle(developmentProposal.getTitle());
        institutionalProposal.setSubcontractFlag(developmentProposal.getSubcontracts());
        institutionalProposal.setRequestedStartDateInitial(developmentProposal.getRequestedStartDateInitial());
        institutionalProposal.setRequestedEndDateInitial(developmentProposal.getRequestedEndDateInitial());
        institutionalProposal.setDeadlineDate(developmentProposal.getDeadlineDate());
        if (developmentProposal.getRolodex() != null) {
            institutionalProposal.setRolodexId(developmentProposal.getRolodex().getRolodexId());
        }
        institutionalProposal.setNoticeOfOpportunityCode(developmentProposal.getNoticeOfOpportunityCode());
        institutionalProposal.setNumberOfCopies(developmentProposal.getNumberOfCopies());
        //institutionalProposal.setDeadlineType(developmentProposal.getDeadlineType());
        institutionalProposal.setMailBy(developmentProposal.getMailBy());
        institutionalProposal.setMailType(developmentProposal.getMailType());
        institutionalProposal.setMailAccountNumber(developmentProposal.getMailAccountNumber());
        institutionalProposal.setMailDescription(developmentProposal.getMailDescription());
        institutionalProposal.setPrimeSponsorCode(developmentProposal.getPrimeSponsorCode());
        institutionalProposal.setCurrentAwardNumber(developmentProposal.getCurrentAwardNumber());
        institutionalProposal.setCfdaNumber(developmentProposal.getCfdaNumber());
        institutionalProposal.setNewDescription(developmentProposal.getNewDescription());
        
        /* Special Reviews. Module hard coupling - revisit this. */
        for (ProposalSpecialReview dpSpecialReview: developmentProposal.getPropSpecialReviews()) {
            InstitutionalProposalSpecialReview ipSpecialReview = new InstitutionalProposalSpecialReview();
            ipSpecialReview.setApplicationDate(dpSpecialReview.getApplicationDate());
            ipSpecialReview.setApprovalDate(dpSpecialReview.getApprovalDate());
            ipSpecialReview.setApprovalTypeCode(dpSpecialReview.getApprovalTypeCode());
            ipSpecialReview.setComments(dpSpecialReview.getComments());
            //ipSpecialReview.setExemptionTypeCodes(dpSpecialReview.get);
            //ipSpecialReview.setExemptionTypes(dpSpecialReview.getExemptionTypes());
            ipSpecialReview.setExpirationDate(dpSpecialReview.getExpirationDate());
            ipSpecialReview.setProtocolNumber(dpSpecialReview.getProtocolNumber());
            ipSpecialReview.setSpecialReview(dpSpecialReview.getSpecialReview());
            ipSpecialReview.setSpecialReviewApprovalType(dpSpecialReview.getSpecialReviewApprovalType());
            ipSpecialReview.setSpecialReviewCode(dpSpecialReview.getSpecialReviewCode());
//            for(InstitutionalProposalSpecialReviewExemption ipExempt: dpSpecialReview.gete) {
//                ipSpecialReview.addSpecialReviewExemption(ipExempt.getExemptionTypeCode());
//            }
            ipSpecialReview.setSpecialReviewNumber(dpSpecialReview.getSpecialReviewNumber());
            ipSpecialReview.setValidSpecialReviewApproval(dpSpecialReview.getValidSpecialReviewApproval());
            institutionalProposal.addSpecialReview(ipSpecialReview);
        }
        
        for (PropScienceKeyword dpKeyword: developmentProposal.getPropScienceKeywords()) {
            institutionalProposal.addKeyword(dpKeyword.getScienceKeyword());
        }
        
        // Cost Shares (from Budget)
        // Unrecovered F and As (from Budget)
        
        institutionalProposalDocument.setInstitutionalProposal(institutionalProposal);
        
        return institutionalProposalDocument;
    }
    
    private void resetUserSession() {
        GlobalVariables.getUserSession().clearBackdoorUser();
    }
    
    /* Service getters and setters */
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    
}
