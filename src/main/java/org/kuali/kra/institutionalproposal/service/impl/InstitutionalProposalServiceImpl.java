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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandA;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnitCreditSplit;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.exception.InstitutionalProposalCreationException;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InstitutionalProposalServiceImpl implements InstitutionalProposalService {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(InstitutionalProposalServiceImpl.class);
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private VersioningService versioningService;
    private InstitutionalProposalVersioningService institutionalProposalVersioningService;
    private SequenceAccessorService sequenceAccessorService;
    
    /* Public API methods */
    
    public String createInstitutionalProposal(DevelopmentProposal developmentProposal, Budget budget) {
        GlobalVariables.getUserSession().setBackdoorUser("quickstart"); // Should be KC system user
        
        try {
            
            InstitutionalProposalDocument institutionalProposalDocument = mergeProposals(new InstitutionalProposal(), developmentProposal, budget);
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
    
    public String createPendingInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget) {
        GlobalVariables.getUserSession().setBackdoorUser("quickstart"); // Should be KC system user
        
        try {
            
            InstitutionalProposalDocument newInstitutionalProposalDocument = versionProposal(proposalNumber, developmentProposal, budget);
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
    
    public String createActiveInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget) {
        GlobalVariables.getUserSession().setBackdoorUser("quickstart"); // Should be KC system user
        
        try {
            
            InstitutionalProposalDocument newInstitutionalProposalDocument = versionProposal(proposalNumber, developmentProposal, budget);
            documentService.blanketApproveDocument(newInstitutionalProposalDocument, 
                    "Autogenerated Institutional Proposal from Development Proposal " + developmentProposal.getProposalNumber(), 
                    new ArrayList<Object>());
            institutionalProposalVersioningService.updateInstitutionalProposalVersionStatus(
                    newInstitutionalProposalDocument.getInstitutionalProposal(), VersionStatus.ACTIVE);
            
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
    
    private InstitutionalProposalDocument versionProposal(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget)
        throws VersionException, WorkflowException {
        
        InstitutionalProposal currentVersion = getInstitutionalProposal(proposalNumber);
        ObjectUtils.materializeObjects(currentVersion.getInstitutionalProposalScienceKeywords());
        if (currentVersion == null) {
            // Throw checked exception
        }
        InstitutionalProposal newVersion = versioningService.createNewVersion(currentVersion);
        InstitutionalProposalDocument newInstitutionalProposalDocument = mergeProposals(newVersion, developmentProposal, budget);
        return newInstitutionalProposalDocument;
    }
    
    private InstitutionalProposalDocument mergeProposals(InstitutionalProposal institutionalProposal, DevelopmentProposal developmentProposal, Budget budget)
        throws WorkflowException {
        
        InstitutionalProposalDocument institutionalProposalDocument = 
            (InstitutionalProposalDocument) documentService.getNewDocument(InstitutionalProposalDocument.class);
        
        institutionalProposalDocument.getDocumentHeader().setDocumentDescription(
                "Generated by Dev Proposal" + developmentProposal.getProposalNumber());
        
        //set proposal number on new Institutional Proposal so that it will be propagated to all created child BO's before initial save.
        Long nextProposalNumber = sequenceAccessorService.getNextAvailableSequenceNumber(Constants.INSTITUTIONAL_PROPSAL_PROPSAL_NUMBER_SEQUENCE);
        DecimalFormat formatter = new DecimalFormat("00000000");
        String nextProposalNumberAsString = formatter.format(nextProposalNumber);
        institutionalProposal.setProposalNumber(nextProposalNumberAsString);

        // Base fields from Dev proposal
        institutionalProposal.setProposalTypeCode(Integer.parseInt(developmentProposal.getProposalTypeCode()));
        institutionalProposal.setActivityTypeCode(developmentProposal.getActivityTypeCode());
        institutionalProposal.setStatusCode(1);  // TODO app constant
        institutionalProposal.setSponsorCode(developmentProposal.getSponsorCode());
        institutionalProposal.setTitle(developmentProposal.getTitle());
        institutionalProposal.setSubcontractFlag(developmentProposal.getSubcontracts());
        institutionalProposal.setRequestedStartDateInitial(developmentProposal.getRequestedStartDateInitial());
        institutionalProposal.setRequestedEndDateInitial(developmentProposal.getRequestedEndDateInitial());
        institutionalProposal.setDeadlineDate(developmentProposal.getDeadlineDate());
        institutionalProposal.setNoticeOfOpportunityCode(developmentProposal.getNoticeOfOpportunityCode());
        institutionalProposal.setNumberOfCopies(developmentProposal.getNumberOfCopies());
        institutionalProposal.setDeadlineType(developmentProposal.getDeadlineType());
        institutionalProposal.setMailBy(developmentProposal.getMailBy());
        institutionalProposal.setMailType(developmentProposal.getMailType());
        institutionalProposal.setMailAccountNumber(developmentProposal.getMailAccountNumber());
        institutionalProposal.setMailDescription(developmentProposal.getMailDescription());
        institutionalProposal.setPrimeSponsorCode(developmentProposal.getPrimeSponsorCode());
        institutionalProposal.setCurrentAwardNumber(developmentProposal.getCurrentAwardNumber());
        institutionalProposal.setCfdaNumber(developmentProposal.getCfdaNumber());
        institutionalProposal.setNewDescription(developmentProposal.getNewDescription());
        if (developmentProposal.getRolodex() != null) {
            institutionalProposal.setRolodexId(developmentProposal.getRolodex().getRolodexId());
        }
        
        // Contacts
        for (ProposalPerson pdPerson : developmentProposal.getProposalPersons()) {
            InstitutionalProposalPerson ipPerson = new InstitutionalProposalPerson();
            if (ObjectUtils.isNotNull(pdPerson.getPersonId())) {
                ipPerson.setPersonId(pdPerson.getPersonId());
            }
            if (ObjectUtils.isNotNull(pdPerson.getRolodexId())) {
                ipPerson.setRolodexId(pdPerson.getRolodexId());
            }
            ipPerson.setAutoIncrementSet(pdPerson.isAutoIncrementSet());
            ipPerson.setContactRoleCode(pdPerson.getRole().getRoleCode());
            for (ProposalPersonCreditSplit pdPersonCreditSplit : pdPerson.getCreditSplits()) {
                InstitutionalProposalPersonCreditSplit ipPersonCreditSplit = new InstitutionalProposalPersonCreditSplit();
                ipPersonCreditSplit.setAutoIncrementSet(pdPersonCreditSplit.isAutoIncrementSet());
                ipPersonCreditSplit.setCredit(pdPersonCreditSplit.getCredit());
                ipPersonCreditSplit.setInvCreditTypeCode(pdPersonCreditSplit.getInvCreditTypeCode());
                ipPersonCreditSplit.setNewCollectionRecord(pdPersonCreditSplit.isNewCollectionRecord());
                ipPerson.add(ipPersonCreditSplit);
            }
            ipPerson.setEmailAddress(pdPerson.getEmailAddress());
            ipPerson.setFaculty(pdPerson.getFacultyFlag());
            ipPerson.setFullName(pdPerson.getFullName());
            ipPerson.setKeyPersonRole(pdPerson.getProjectRole());
            ipPerson.setNewCollectionRecord(pdPerson.isNewCollectionRecord());
            ipPerson.setPerson(pdPerson.getPerson());
            ipPerson.setPhoneNumber(pdPerson.getPhoneNumber());
            ipPerson.setRoleCode(pdPerson.getRole().getRoleCode());
            ipPerson.setTotalEffort(pdPerson.getPercentageEffort());
            // ipPerson.setSummerEffort(pdPerson.getPercentageEffort());
            // ipPerson.setAcademicYearEffort(pdPerson.getPercentageEffort());
            // ipPerson.setCalendarYearEffort(pdPerson.getPercentageEffort());
            // ipPerson.setUnitName(pdPerson.getUnit().getUnitName());
            for (ProposalPersonUnit pdPersonUnit : pdPerson.getUnits()) {
                InstitutionalProposalPersonUnit ipPersonUnit = new InstitutionalProposalPersonUnit();
                ipPersonUnit.setAutoIncrementSet(pdPersonUnit.isAutoIncrementSet());
                ipPersonUnit.setLeadUnit(pdPersonUnit.isLeadUnit());
                ipPersonUnit.setNewCollectionRecord(pdPersonUnit.isNewCollectionRecord());
                ipPersonUnit.setUnitNumber(pdPersonUnit.getUnitNumber());
                for (ProposalUnitCreditSplit pdPersonCreditSplit : pdPersonUnit.getCreditSplits()) {
                    InstitutionalProposalPersonUnitCreditSplit ipPersonUnitCreditSplit = new InstitutionalProposalPersonUnitCreditSplit();
                    ipPersonUnitCreditSplit.setAutoIncrementSet(pdPersonCreditSplit.isAutoIncrementSet());
                    ipPersonUnitCreditSplit.setCredit(pdPersonCreditSplit.getCredit());
                    ipPersonUnitCreditSplit.setInvCreditTypeCode(pdPersonCreditSplit.getInvCreditTypeCode());
                    ipPersonUnitCreditSplit.setNewCollectionRecord(pdPersonCreditSplit.isNewCollectionRecord());
                    ipPersonUnit.add(ipPersonUnitCreditSplit);
                }
                ipPerson.add(ipPersonUnit);
            }
            institutionalProposal.add(ipPerson);
        }
        
        /* Special Reviews. Module hard coupling - revisit this. */
        for (ProposalSpecialReview dpSpecialReview : developmentProposal.getPropSpecialReviews()) {
            InstitutionalProposalSpecialReview ipSpecialReview = new InstitutionalProposalSpecialReview();
            ipSpecialReview.setApplicationDate(dpSpecialReview.getApplicationDate());
            ipSpecialReview.setApprovalDate(dpSpecialReview.getApprovalDate());
            ipSpecialReview.setApprovalTypeCode(dpSpecialReview.getApprovalTypeCode());
            ipSpecialReview.setComments(dpSpecialReview.getComments());
            ipSpecialReview.setExpirationDate(dpSpecialReview.getExpirationDate());
            ipSpecialReview.setProtocolNumber(dpSpecialReview.getProtocolNumber());
            ipSpecialReview.setSpecialReview(dpSpecialReview.getSpecialReview());
            ipSpecialReview.setSpecialReviewApprovalType(dpSpecialReview.getSpecialReviewApprovalType());
            ipSpecialReview.setSpecialReviewCode(dpSpecialReview.getSpecialReviewCode());
            ipSpecialReview.setSpecialReviewNumber(dpSpecialReview.getSpecialReviewNumber());
            ipSpecialReview.setValidSpecialReviewApproval(dpSpecialReview.getValidSpecialReviewApproval());
            // for(InstitutionalProposalSpecialReviewExemption ipExempt: dpSpecialReview.gete) {
            //     ipSpecialReview.addSpecialReviewExemption(ipExempt.getExemptionTypeCode());
            // }
            // ipSpecialReview.setExemptionTypeCodes(dpSpecialReview.get);
            // ipSpecialReview.setExemptionTypes(dpSpecialReview.getExemptionTypes());
            institutionalProposal.addSpecialReview(ipSpecialReview);
        }
        
        // Keywords
        for (PropScienceKeyword dpKeyword : developmentProposal.getPropScienceKeywords()) {
            institutionalProposal.addKeyword(dpKeyword.getScienceKeyword());
        }
        
        if (budget != null) {
        
            // Base fields from Budget
            institutionalProposal.setRequestedStartDateTotal(budget.getSummaryPeriodStartDate());
            institutionalProposal.setRequestedEndDateTotal(budget.getSummaryPeriodEndDate());
            institutionalProposal.setTotalDirectCostInitial(new KualiDecimal(budget.getBudgetPeriod(0).getTotalDirectCost().bigDecimalValue()));
            institutionalProposal.setTotalIndirectCostInitial(new KualiDecimal(budget.getBudgetPeriod(0).getTotalIndirectCost().bigDecimalValue()));
            institutionalProposal.setTotalDirectCostTotal(new KualiDecimal(budget.getTotalDirectCost().bigDecimalValue()));
            institutionalProposal.setTotalIndirectCostTotal(new KualiDecimal(budget.getTotalIndirectCost().bigDecimalValue()));
            
            // Cost Shares (from Budget)
            for (BudgetCostShare budgetCostShare : budget.getBudgetCostShares()) {
                InstitutionalProposalCostShare ipCostShare = new InstitutionalProposalCostShare();
                ipCostShare.setAmount(new KualiDecimal(budgetCostShare.getShareAmount().bigDecimalValue()));
                ipCostShare.setCostSharePercentage(new KualiDecimal(budgetCostShare.getSharePercentage().bigDecimalValue()));
                ipCostShare.setFiscalYear(budgetCostShare.getFiscalYear().toString());
                ipCostShare.setSourceAccount(budgetCostShare.getSourceAccount());
                institutionalProposal.add(ipCostShare);
            }
            
            // Unrecovered F and As (from Budget)
            for (BudgetUnrecoveredFandA budgetUfa : budget.getBudgetUnrecoveredFandAs()) {
                InstitutionalProposalUnrecoveredFandA ipUfa = new InstitutionalProposalUnrecoveredFandA();
                ipUfa.setApplicableIndirectcostRate(new KualiDecimal(budgetUfa.getApplicableRate().bigDecimalValue()));
                ipUfa.setFiscalYear(budgetUfa.getFiscalYear().toString());
                ipUfa.setOnCampusFlag(Boolean.parseBoolean(budgetUfa.getOnCampusFlag()));
                ipUfa.setSourceAccount(budgetUfa.getSourceAccount());
                institutionalProposal.add(ipUfa);
            }
        }
        
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

    public void setInstitutionalProposalVersioningService(InstitutionalProposalVersioningService institutionalProposalVersioningService) {
        this.institutionalProposalVersioningService = institutionalProposalVersioningService;
    }
    
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
}
