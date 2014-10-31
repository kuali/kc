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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.propdev.impl.attachment.*;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatus;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcDataObject;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardAttachment;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardFiles;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevBudgetSubAwardService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.OrderByField;
import org.kuali.rice.core.api.criteria.OrderDirection;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kns.service.SessionDocumentService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.sql.Date;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.*;

@Component("proposalHierarchyService")
@Transactional
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {
    
    private static final Log LOG = LogFactory.getLog(ProposalHierarchyServiceImpl.class);

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;
    @Autowired
    @Qualifier("proposalHierarchyDao")
    private ProposalHierarchyDao proposalHierarchyDao;
    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService legacyNarrativeService;
    @Autowired
    @Qualifier("proposalBudgetService")
    private ProposalBudgetService budgetService;
    @Autowired
    @Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;
    @Autowired
    @Qualifier("proposalPersonBiographyService")
    private ProposalPersonBiographyService proposalPersonBiographyService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;
    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;
    @Autowired
    @Qualifier("kcDocumentRejectionService")
    private KcDocumentRejectionService kcDocumentRejectionService;
    @Autowired
    @Qualifier("knsSessionDocumentService")
    private SessionDocumentService knsSessionDocumentService;
    @Autowired
    @Qualifier("kradWorkflowDocumentService")
    private WorkflowDocumentService kradWorkflowDocumentService;
    @Autowired
    @Qualifier("keyPersonnelService")
    private KeyPersonnelService keyPersonnelService;
    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;
    @Autowired
    @Qualifier("budgetPersonnelBudgetService")
    private BudgetPersonnelBudgetService budgetPersonnelBudgetService;
    @Autowired
    @Qualifier("propDevBudgetSubAwardService")
    private PropDevBudgetSubAwardService propDevBudgetSubAwardService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    //Setters for dependency injection
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }
    public void setProposalHierarchyDao(ProposalHierarchyDao proposalHierarchyDao) {
        this.proposalHierarchyDao = proposalHierarchyDao;
    }
    public void setLegacyNarrativeService(LegacyNarrativeService narrativeService) {
        this.legacyNarrativeService = narrativeService;
    }
    public void setBudgetService(ProposalBudgetService budgetService) {
        this.budgetService = budgetService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public String createHierarchy(DevelopmentProposal initialChild, String userId) {
        LOG.info(String.format("***Create Hierarchy using Proposal #%s", initialChild.getProposalNumber()));

        ProposalDevelopmentDocument newDoc = assembleDoc();

        // copy the initial information to the new parent proposal
        DevelopmentProposal hierarchy = newDoc.getDevelopmentProposal();
        copyInitialData(hierarchy, initialChild);

        // set hierarchy status
        setHierarchyStatus(initialChild.getProposalDocument(), newDoc);

        ProposalDevelopmentDocument hierarchyDoc = saveDocument(newDoc);

        // add aggregator to the document
        kcAuthorizationService.addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, hierarchyDoc);

        budgetService.addBudgetVersion(hierarchyDoc, "Hierarchy Budget", null);

        initializeBudget(hierarchyDoc.getDevelopmentProposal(), initialChild);
        
        //we are creating the attachments for the first time so do not sync personnel attachments at this time, just copy as is.
        linkChild(hierarchyDoc.getDevelopmentProposal(), initialChild, HierarchyBudgetTypeConstants.SubBudget.code(), false);
        setInitialPi(hierarchyDoc.getDevelopmentProposal(), initialChild);
        copyInitialAttachments(initialChild, hierarchyDoc.getDevelopmentProposal());

        LOG.info(String.format("***Initial Child (#%s) linked to Parent (#%s)", initialChild.getProposalNumber(), hierarchyDoc.getDevelopmentProposal().getProposalNumber()));
        
        finalizeHierarchySync(hierarchyDoc.getDevelopmentProposal());
        
        // return the parent id
        LOG.info(String.format("***Hierarchy creation (#%s) complete", hierarchyDoc.getDevelopmentProposal().getProposalNumber()));
        return hierarchyDoc.getDevelopmentProposal().getProposalNumber();
    }

    protected ProposalDevelopmentDocument saveDocument(ProposalDevelopmentDocument newDoc) {
        ProposalDevelopmentDocument hierarchyDoc;
        // persist the document and add a budget
        try {
            hierarchyDoc = (ProposalDevelopmentDocument) documentService.saveDocument(newDoc);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }
        return hierarchyDoc;
    }

    protected void setHierarchyStatus(ProposalDevelopmentDocument childDocument, ProposalDevelopmentDocument newDoc) {
        DevelopmentProposal hierarchy = newDoc.getDevelopmentProposal();
        hierarchy.setHierarchyStatus(HierarchyStatusConstants.Parent.code());
        String docDescription = childDocument.getDocumentHeader()
                .getDocumentDescription();
        newDoc.getDocumentHeader().setDocumentDescription(docDescription);
        newDoc.setDevelopmentProposal(hierarchy);
        hierarchy.setProposalDocument(newDoc);
    }

    protected ProposalDevelopmentDocument assembleDoc() {
        ProposalDevelopmentDocument newDoc;
        // manually assembling a new PDDoc here because the DocumentService will deny initiator permission without context
        // since a person with MAINTAIN_PROPOSAL_HIERARCHY permission is allowed to initiate IF they are creating a parent
        // we circumvent the initiator step altogether.
        try {
            WorkflowDocument workflowDocument = kradWorkflowDocumentService.createWorkflowDocument(PROPOSAL_DEVELOPMENT_DOCUMENT_TYPE, globalVariableService.getUserSession().getPerson());
            DocumentHeader documentHeader = new DocumentHeader();
            documentHeader.setWorkflowDocument(workflowDocument);
            documentHeader.setDocumentNumber(workflowDocument.getDocumentId().toString());
            newDoc = new ProposalDevelopmentDocument();
            newDoc.setDocumentHeader(documentHeader);
            newDoc.setDocumentNumber(documentHeader.getDocumentNumber());
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error creating new document: " + x);
        }
        return newDoc;
    }

    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode) throws ProposalHierarchyException {
            prepareHierarchySync(hierarchyProposal);
            linkChild(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode, true);
            finalizeHierarchySync(hierarchyProposal);
    }

    public List<ProposalHierarchyErrorWarningDto> validateLinkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
        if (hierarchyProposal == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_PROPOSAL_DOES_NOT_EXIST, Boolean.TRUE, new String[0]));
        } else {
                if (!hierarchyProposal.isParent()) {
                    errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_PROPOSAL_NOT_HIERARCHY_PARENT,
                                                                Boolean.TRUE, hierarchyProposal.getProposalNumber()));
                }
        }
        if (childProposal.isInHierarchy()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_NOT_HIERARCHY_CHILD, Boolean.TRUE, childProposal.getProposalNumber()));
        }
        return errors;
    }

    public void removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        String hierarchyProposalNumber = childProposal.getHierarchyParentProposalNumber();
        DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);

        List<String> childProposalNumbers = proposalHierarchyDao.getHierarchyChildProposalNumbers(hierarchyProposalNumber);
        boolean isLast = childProposalNumbers.size()==1 && StringUtils.equals(childProposalNumbers.get(0), childProposal.getProposalNumber());
     
        childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
        childProposal.setHierarchyParentProposalNumber(null);
        if (StringUtils.equalsIgnoreCase(hierarchyProposal.getHierarchyOriginatingChildProposalNumber(), childProposal.getProposalNumber())) {
            hierarchyProposal.getPrincipalInvestigator().setHierarchyProposalNumber(null);
        }
        removeChildElements(hierarchyProposal, childProposal.getProposalNumber());

        if (isLast) {
            try {
                dataObjectService.save(childProposal);
                Document doc = documentService.getByDocumentHeaderId(hierarchyProposal.getProposalDocument().getDocumentNumber());
                documentService.cancelDocument(doc, "Removed last child from Proposal Hierarchy");
            }
            catch (WorkflowException e) {
                throw new ProposalHierarchyException("Error cancelling empty parent proposal");
            }
        }
        else {
            //need to find out what the lowest number is
            //so we can make it the new primary child for budget syncs.
            String lowestProposalNumber = "";
            for( String proposalNumber : childProposalNumbers ) {
                if ( !StringUtils.equals(proposalNumber, childProposal.getProposalNumber() )) {
                    lowestProposalNumber = proposalNumber;
                    break;
                }
            }
            hierarchyProposal.setHierarchyOriginatingChildProposalNumber(lowestProposalNumber);
            dataObjectService.save(childProposal);
            synchronizeAllChildren(hierarchyProposal);
        }
    }

    @Override
    public void synchronizeAllChildren(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        prepareHierarchySync(pdDoc);
        synchronizeAll(pdDoc.getDevelopmentProposal());
    }

    protected void synchronizeAllChildren(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        prepareHierarchySync(hierarchyProposal);
        synchronizeAll(hierarchyProposal);
    }

    protected void synchronizeAll(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        synchronizeAllChildProposals(hierarchyProposal);
    }

    @Override
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());

        prepareHierarchySync(hierarchy);
        synchronizeChildProposal(hierarchy, childProposal, true);
        finalizeHierarchySync(hierarchy);
    }
    
    
    public void synchronizeChildProposalBudget(ProposalDevelopmentBudgetExt budget, DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());
        finalizeHierarchySync(hierarchy);
    }
    
    @Override
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        return getHierarchy(childProposal.getHierarchyParentProposalNumber());
    }

    protected void linkChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, String hierarchyBudgetTypeCode, boolean syncPersonnelAttachments)
            throws ProposalHierarchyException {
        // set child to child status
        childProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        childProposal.setHierarchyParentProposalNumber(hierarchyProposal.getProposalNumber());
        childProposal.setHierarchyBudgetType(hierarchyBudgetTypeCode);
        // call synchronize
        synchronizeChildProposal(hierarchyProposal, childProposal, syncPersonnelAttachments);
    }

    protected void copyInitialData(DevelopmentProposal hierarchyProposal, DevelopmentProposal srcProposal)
            throws ProposalHierarchyException {
        // Required fields for saving document
        hierarchyProposal.setHierarchyOriginatingChildProposalNumber(srcProposal.getProposalNumber());
        hierarchyProposal.setSponsor(srcProposal.getSponsor());
        hierarchyProposal.setSponsorCode(srcProposal.getSponsorCode());
        hierarchyProposal.setProposalTypeCode(srcProposal.getProposalTypeCode());
        hierarchyProposal.setRequestedStartDateInitial(srcProposal.getRequestedStartDateInitial());
        hierarchyProposal.setRequestedEndDateInitial(srcProposal.getRequestedEndDateInitial());
        hierarchyProposal.setOwnedByUnit(srcProposal.getOwnedByUnit());
        hierarchyProposal.setOwnedByUnitNumber(srcProposal.getOwnedByUnitNumber());
        hierarchyProposal.setActivityType(srcProposal.getActivityType());
        hierarchyProposal.setActivityTypeCode(srcProposal.getActivityTypeCode());
        hierarchyProposal.setTitle(srcProposal.getTitle());

        // Sponsor & program information
        hierarchyProposal.setDeadlineDate(srcProposal.getDeadlineDate());
        hierarchyProposal.setDeadlineTime(srcProposal.getDeadlineTime());
        hierarchyProposal.setDeadlineType(srcProposal.getDeadlineType());
        hierarchyProposal.setAnticipatedAwardTypeCode(srcProposal.getAnticipatedAwardTypeCode());
        hierarchyProposal.setNoticeOfOpportunityCode(srcProposal.getNoticeOfOpportunityCode());
        hierarchyProposal.setCfdaNumber(srcProposal.getCfdaNumber());
        hierarchyProposal.setPrimeSponsorCode(srcProposal.getPrimeSponsorCode());
        hierarchyProposal.setNsfCode(srcProposal.getNsfCode());
        hierarchyProposal.setSponsorProposalNumber(srcProposal.getSponsorProposalNumber());
        hierarchyProposal.setAgencyDivisionCode(srcProposal.getAgencyDivisionCode());
        hierarchyProposal.setAgencyProgramCode(srcProposal.getAgencyProgramCode());
        hierarchyProposal.setSubcontracts(srcProposal.getSubcontracts());
        hierarchyProposal.setProgramAnnouncementNumber(srcProposal.getProgramAnnouncementNumber());
        hierarchyProposal.setProgramAnnouncementTitle(srcProposal.getProgramAnnouncementTitle());

        // Organization/location
        ProposalSite newSite;
        hierarchyProposal.getProposalSites().clear();
    
          for (ProposalSite site : srcProposal.getProposalSites()) {
            newSite = (ProposalSite)deepCopy(site);
            newSite.setDevelopmentProposal(null);
            for (CongressionalDistrict cd : newSite.getCongressionalDistricts()) {
                cd.setProposalSite(newSite);
            }
            hierarchyProposal.addProposalSite(newSite);
        }
            
        // Delivery info
        hierarchyProposal.setMailBy(srcProposal.getMailBy());
        hierarchyProposal.setMailType(srcProposal.getMailType());
        hierarchyProposal.setMailAccountNumber(srcProposal.getMailAccountNumber());
        hierarchyProposal.setNumberOfCopies(srcProposal.getNumberOfCopies());
        hierarchyProposal.setMailingAddressId(srcProposal.getMailingAddressId());
        hierarchyProposal.setMailDescription(srcProposal.getMailDescription());
    }

    protected KcDataObject deepCopy(KcDataObject oldObject) {
        return getDataObjectService().copyInstance(oldObject, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
    }

    /**
     * Synchronizes all child proposals to the parent.
     * @param hierarchyProposal
     * @return
     * @throws ProposalHierarchyException
     */
    protected boolean synchronizeAllChildProposals(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        boolean changed = false;

        // delete all multiple inst attachments right at the beginning
        deleteAllMultipleInternal(hierarchyProposal);

        finalizeHierarchySync(hierarchyProposal.getProposalDocument());

        for (DevelopmentProposal childProposal : getHierarchyChildren(hierarchyProposal.getProposalNumber())) {
            List<PropScienceKeyword> oldKeywords = getOldKeywords(hierarchyProposal, childProposal);
            ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
            childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
            
            removeChildElements(hierarchyProposal, childProposal.getProposalNumber());
            
            synchronizeKeywords(hierarchyProposal, childProposal, oldKeywords);
            synchronizeSpecialReviews(hierarchyProposal, childProposal);
            synchronizePersons(hierarchyProposal, childProposal, principalInvestigator);
            synchronizeNarratives(hierarchyProposal, childProposal);
            // we deleted all internal at the beginning so just add now.
            addInternalAttachments(hierarchyProposal, childProposal);
            syncAllPersonnelAttachments(hierarchyProposal, childProposal);
            synchronizeChildBudget(hierarchyProposal, childProposal);
            dataObjectService.save(childProposal);
            dataObjectService.save(hierarchyProposal);
            changed = true;
        }
        
        return changed;
    }

    protected void deleteAllMultipleInternal(DevelopmentProposal proposal) {
        List<Narrative> freshList = deleteAllMultipleTypeAttachments(proposal.getInstituteAttachments(), proposal);
        proposal.setInstituteAttachments(freshList);
    }

    protected List<Narrative> deleteAllMultipleTypeAttachments(List<Narrative> attachments, DevelopmentProposal proposal) {
        List<Narrative> freshList = new ArrayList<Narrative>();
        for (Narrative narrative : attachments) {
            if (narrative.getNarrativeType().isAllowMultiple()) {
                narrative.setDevelopmentProposal(null);
            } else {
                freshList.add(narrative);
            }
        }
        return freshList;
    }

    /**
     * Synchronizes the given child proposal to the parent.  
     * <p>
     * If a key protocol person appears in multiple child proposals and is removed from the given child 
     * proposal, then this also aggregates that key person back to the parent proposal from a different child proposal, making sure that all the key persons
     * in all of the child proposals are represented in the parent proposal.
     *  
     * @param hierarchyProposal
     * @param childProposal
     * @return
     * @throws ProposalHierarchyException
     */
    protected boolean synchronizeChildProposal(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, boolean syncPersonnelAttachments) throws ProposalHierarchyException {
        
        List<PropScienceKeyword> oldKeywords = getOldKeywords(hierarchyProposal, childProposal);
        ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
        childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
        
        removeChildElements(hierarchyProposal, childProposal.getProposalNumber());
        
        synchronizeKeywords(hierarchyProposal, childProposal, oldKeywords);
        synchronizeSpecialReviews(hierarchyProposal, childProposal);
        synchronizePersonsAndAggregate(hierarchyProposal, childProposal, principalInvestigator);

        if (syncPersonnelAttachments) {
            synchronizeNarratives(hierarchyProposal, childProposal);
            synchronizeInternalAttachments(hierarchyProposal, childProposal);
            syncAllPersonnelAttachments(hierarchyProposal, childProposal);
        }

        synchronizeChildBudget(hierarchyProposal, childProposal);
        dataObjectService.save(childProposal);
        
        return true;
    }
    
    /**
     * Gets the old proposal science keywords before removing them from the parent.
     * @param hierarchyProposal
     * @param childProposal
     * @return
     */
    protected List<PropScienceKeyword> getOldKeywords(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        List<PropScienceKeyword> oldKeywords = new ArrayList<PropScienceKeyword>();
        for (PropScienceKeyword keyword : hierarchyProposal.getPropScienceKeywords()) {
            if (StringUtils.equals(childProposal.getProposalNumber(), keyword.getHierarchyProposalNumber())) {
                oldKeywords.add(keyword);
            }
        }
        return oldKeywords;
    }
    
    /**
     * Synchronizes the proposal science keywords from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     * @param oldKeywords
     */
    protected void synchronizeKeywords(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<PropScienceKeyword> oldKeywords) {
        for (PropScienceKeyword keyword : childProposal.getPropScienceKeywords()) {
            PropScienceKeyword newKeyword = new PropScienceKeyword(hierarchyProposal, keyword.getScienceKeyword());
            int index = oldKeywords.indexOf(newKeyword);
            if (index > -1) {
                newKeyword = oldKeywords.get(index);
            }
            newKeyword.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.addPropScienceKeyword(newKeyword);
        }
    }

    /**
     * Synchronizes the proposal special reviews from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     */
    protected void synchronizeSpecialReviews(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalSpecialReview review : childProposal.getPropSpecialReviews()) {
            ProposalSpecialReview newReview = (ProposalSpecialReview) deepCopy(review);
            newReview.setDevelopmentProposal(hierarchyProposal);
            newReview.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.getPropSpecialReviews().add(newReview);
        }
    }
    
    /**
     * Synchronizes the proposal narratives from the child proposal to the parent proposal based on some rules.
     * 1. If the attachment is single type, then will get created in the parent if one does not already exist there and then subsequent changes must be
     * made on the parent and will not allow sync to child.
     * 2. Multiple Attachment Type: If you have many  attachments for the attachment type multiple,
     * then they all sync up and use the hierarchy proposal number to figure out how to sync.
     * These are never changed at the parent, though you could add one at the parent ( this will not have a hierarchy proposal number) that never
     * gets synced
     * 3. Any attachment can be added at the parent and then requires all updates on the parent. Child proposals not populated with this attachment.
     * @param hierarchyProposal
     * @param childProposal
     */
    protected void synchronizeNarratives(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        // delete everything from parent that has this child proposal number as
        // as hierarchy proposal number and multiple attachment type.
        deleteMultipleTypeAttachments(hierarchyProposal, childProposal, childProposal.getNarratives());

        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getNarratives(), hierarchyProposal.getNarratives());
    }

    protected void synchronizeInternalAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        // delete everything from parent that has this child proposal number as
        // as hierarchy proposal number and multiple attachment type.
        deleteMultipleTypeAttachments(hierarchyProposal, childProposal, childProposal.getInstituteAttachments());

        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getInstituteAttachments());
    }

    protected void addInternalAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getInstituteAttachments());
    }

    protected void addNarratives(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getNarratives());
    }

    protected void syncAttachmentsFromChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<Narrative> childAttachments,
                                            List<Narrative> hierarchyAttachments) {
        for (Narrative narrative : childAttachments) {
            narrative.refreshReferenceObject("narrativeType");
            // if single type narrative
            if (!narrative.getNarrativeType().isAllowMultiple() && !doesParentHaveNarrativeType(hierarchyProposal, narrative.getNarrativeType())) {
                addNarrativeToParent(hierarchyProposal, childProposal, narrative, hierarchyAttachments);
                // not adding user rights here since it is not needed.
            }

            // if attachment type allows multiple
            if (narrative.getNarrativeType().isAllowMultiple()) {
                addNarrativeToParent(hierarchyProposal, childProposal, narrative, hierarchyAttachments);
            }
        }
    }

        protected void addNarrativeToParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, Narrative narrative,
                                            List<Narrative> hierarchyAttachments) {
        Narrative newNarrative = (Narrative) deepCopy(narrative);
        newNarrative.setNarrativeTypeCode(narrative.getNarrativeTypeCode());
        newNarrative.refreshReferenceObject("narrativeType");
        newNarrative.setHierarchyProposalNumber(childProposal.getProposalNumber());
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);
        newNarrative.setModuleNumber(legacyNarrativeService.getNextModuleNumber(hierarchyProposal.getProposalDocument()));
        newNarrative.setDevelopmentProposal(hierarchyProposal);
        newNarrative.setNarrativeUserRights(null);
        hierarchyAttachments.add(newNarrative);
    }

    protected void deleteMultipleTypeAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<Narrative> attachments) {
        for (Narrative narrative : attachments) {
            narrative.refreshReferenceObject("narrativeType");
            if (narrative.getNarrativeType().isAllowMultiple()) {
                deleteNarrativesFromParent(hierarchyProposal, childProposal, narrative.getNarrativeType());
            }
        }
    }

     protected void deleteNarrativesFromParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, NarrativeType type) {
         Map<String, String> param = new HashMap<String, String>();
         param.put("hierarchyProposalNumber", childProposal.getProposalNumber());
         param.put("narrativeTypeCode", type.getCode());
         getDataObjectService().deleteMatching(Narrative.class, QueryByCriteria.Builder.andAttributes(param).build());

     }

     protected boolean doesParentHaveNarrativeType(DevelopmentProposal hierarchyProposal, NarrativeType narrativeType) {
        return getLegacyNarrativeService().doesProposalHaveNarrativeType(hierarchyProposal, narrativeType);
     }

    protected void syncAllPersonnelAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        Map<String, Boolean> personInMultipleProp = new HashMap<String, Boolean>();
        List<ProposalPersonBiography> newList = new ArrayList<ProposalPersonBiography>();

        /*
         Go thro list of personBios in child, if it is not in multiple proposals, then remove instance from parent so you
         can copy over again.
         */
        for (Iterator<ProposalPersonBiography> iteratorChild = childProposal.getPropPersonBios().iterator(); iteratorChild.hasNext();) {
            ProposalPersonBiography srcPropPersonBio = iteratorChild.next();
            Integer hierarchyProposalPersonNumber = personNotPresentInMultipleProposals(srcPropPersonBio, childProposal, hierarchyProposal);
            if (hierarchyProposalPersonNumber == null) {
                // mark those persons that are not in multiple proposals since they will be copied over later
                personInMultipleProp.put(srcPropPersonBio.getPersonId(), false);
                for (Iterator<ProposalPersonBiography> iteratorParent = hierarchyProposal.getPropPersonBios().iterator(); iteratorParent.hasNext();) {
                    ProposalPersonBiography parentPropPersonBio = iteratorParent.next();
                    if (StringUtils.equals(srcPropPersonBio.getPersonId(), parentPropPersonBio.getPersonId())) {
                        iteratorParent.remove();
                    }
                }
            }
        }

        // go over the child bios list and if person is not in multiple proposals, add that bio
        for (ProposalPersonBiography srcPropPersonBio : childProposal.getPropPersonBios()) {
            if (personInMultipleProp.get(srcPropPersonBio.getPersonId()) != null &&
                    !personInMultipleProp.get(srcPropPersonBio.getPersonId())) {
                ProposalPersonBiography destPropPersonBio;
                destPropPersonBio = (ProposalPersonBiography) deepCopy(srcPropPersonBio);
                destPropPersonBio.setDevelopmentProposal(hierarchyProposal);
                destPropPersonBio.setProposalNumber(hierarchyProposal.getProposalNumber());
                destPropPersonBio.setProposalPersonNumber(getProposalPersonNumber(destPropPersonBio.getPersonId(), hierarchyProposal));
                destPropPersonBio.setVersionNumber(0L);
                newList.add(destPropPersonBio);

            }
        }
        hierarchyProposal.getPropPersonBios().addAll(newList);
    }

    protected Integer getProposalPersonNumber(String personId, DevelopmentProposal hierarchyProposal) {
        for (ProposalPerson person : hierarchyProposal.getProposalPersons()) {
            if (StringUtils.equalsIgnoreCase(person.getPersonId(), personId)) {
                return person.getProposalPersonNumber();
            }
        }
        return null;
    }

    protected Integer personNotPresentInMultipleProposals(ProposalPersonBiography srcPropPersonBio, DevelopmentProposal childProposal, DevelopmentProposal hierarchyProposal) {
        // if person not on parent, add
        // if it is on lead, then check if it is on any other children. If not, merge, if so, stop.
        Integer hierarchyProposalPersonNumber = isPersonOnProposal(hierarchyProposal, srcPropPersonBio.getPersonId());
        if (hierarchyProposalPersonNumber == null) {
                return null;
            } else {
                // check other proposals
                List<DevelopmentProposal> children = getAllChildren(hierarchyProposal.getProposalNumber());
                for (DevelopmentProposal proposal : children) {
                    if(!StringUtils.equals(proposal.getProposalNumber(), childProposal.getProposalNumber())) {
                         Integer proposalPersonNumber = isPersonOnProposal(proposal, srcPropPersonBio.getPersonId());
                         if (proposalPersonNumber != null) {
                             return proposalPersonNumber;
                        }

                    }
                }
            }
        return null;
    }

    protected List<DevelopmentProposal> getAllChildren(String hierarchyProposalNumber) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("hierarchyProposalNumber", hierarchyProposalNumber);
            return (List<DevelopmentProposal>) dataObjectService.findMatching(DevelopmentProposal.class, QueryByCriteria.Builder.forAttribute("hierarchyParentProposalNumber", hierarchyProposalNumber).build()).getResults();
    }

    protected Integer isPersonOnProposal(DevelopmentProposal proposal, String id) {
        for (ProposalPerson proposalPerson : proposal.getProposalPersons()) {
               if(StringUtils.equals(id, proposalPerson.getPersonId())) {
                return proposalPerson.getProposalPersonNumber();
            }
        }
        return null;
    }
 /**
  * Synchronizes the proposal persons from the child proposal to the parent proposal and then restores any proposal persons that were in the given child
  * proposal (and hence removed from the given parent proposal).
  * <p>
  * This first synchronizes the main proposal persons from the primary child proposal to the parent proposal and then runs the same algorithm on all other
  * children of the parent proposal.
  * @param hierarchyProposal
  * @param primaryChildProposal
  * @param principalInvestigator
  */
    protected void synchronizePersonsAndAggregate(DevelopmentProposal hierarchyProposal, DevelopmentProposal primaryChildProposal, 
            ProposalPerson principalInvestigator) {
        
        synchronizePersons(hierarchyProposal, primaryChildProposal, principalInvestigator);
        for (DevelopmentProposal childProposal : getHierarchyChildren(hierarchyProposal.getProposalNumber())) {
            if (!StringUtils.equals(primaryChildProposal.getProposalNumber(), childProposal.getProposalNumber())) {
                synchronizePersons(hierarchyProposal, childProposal, principalInvestigator);
            }
        }
    }

    /**
     * Synchronizes the proposal persons from the child proposal to the parent proposal.
     * @param hierarchyProposal
     * @param childProposal
     * @param principalInvestigator
     */
    protected void synchronizePersons(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, ProposalPerson principalInvestigator) {
        for (ProposalPerson person : childProposal.getProposalPersons()) {
            int firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            int lastIndex = hierarchyProposal.getProposalPersons().lastIndexOf(person);
            ProposalPerson firstInstance = (firstIndex == -1) ? null : hierarchyProposal.getProposalPersons().get(firstIndex);
            if (firstIndex == -1 || (firstIndex == lastIndex && !rolesAreSimilar(person, firstInstance))) {
                ProposalPerson newPerson;
                newPerson = (ProposalPerson) deepCopy(person);
                newPerson.setDevelopmentProposal(hierarchyProposal);
                newPerson.getProposalPersonYnqs().clear();
                for (ProposalPersonUnit unit : newPerson.getUnits()) {
                    for(ProposalUnitCreditSplit creditSplit : unit.getCreditSplits()) {
                        creditSplit.setCredit(new ScaleTwoDecimal(0));
                    }
                }

                for (ProposalPersonCreditSplit creditSplit : newPerson.getCreditSplits()) {
                    creditSplit.setCredit(new ScaleTwoDecimal(0));
                }
                newPerson.setProposalPersonNumber(null);
                newPerson.setVersionNumber(null);
                newPerson.setHierarchyProposalNumber(childProposal.getProposalNumber());
            
                if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                    newPerson.setProposalPersonRoleId(Constants.CO_INVESTIGATOR_ROLE);
                }
                if (newPerson.equals(principalInvestigator) && (firstIndex == -1 || !firstInstance.isInvestigator())) {
                    newPerson.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                }
                
                hierarchyProposal.addProposalPerson(newPerson);
            }
        }
    }
    
    protected void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal)
            throws ProposalHierarchyException {
    	String hierarchyBudgetTypeCode = childProposal.getHierarchyBudgetType();
    	String childProposalNumber = childProposal.getProposalNumber();
    	ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(hierarchyProposal);
    	ProposalDevelopmentBudgetExt childBudget = getSyncableBudget(childProposal);
        boolean isOriginatingChildBudget = StringUtils.equals(childProposal.getProposalNumber(), hierarchyProposal.getHierarchyOriginatingChildProposalNumber());    	
        try {
            if( isOriginatingChildBudget ) {
                parentBudget.setUrRateClassCode( childBudget.getUrRateClassCode() );
                parentBudget.setOhRateClassCode( childBudget.getOhRateClassCode() );
            }
            
            BudgetPerson newPerson;
            Map<Integer, BudgetPerson> personMap = new HashMap<Integer, BudgetPerson>();
            for (BudgetPerson person : childBudget.getBudgetPersons()) {
                newPerson = (BudgetPerson) deepCopy(person);
                newPerson.setPersonSequenceNumber(parentBudget.getNextValue(
                        Constants.PERSON_SEQUENCE_NUMBER));
                newPerson.setBudgetId(parentBudget.getBudgetId());
                newPerson.setHierarchyProposalNumber(childProposalNumber);
                newPerson.setHierarchyProposal(childProposal);
                newPerson.setVersionNumber(null);
                parentBudget.addBudgetPerson(newPerson);
                personMap.put(person.getPersonSequenceNumber(), newPerson);
            }
            
            BudgetSubAwards newSubAwards;
            for (BudgetSubAwards childSubAwards : childBudget.getBudgetSubAwards()) {
            	for (BudgetSubAwardAttachment origAttachment : childSubAwards.getBudgetSubAwardAttachments()) {
            		origAttachment.getData();
            	}
                newSubAwards = (BudgetSubAwards) deepCopy(childSubAwards);
                newSubAwards.setBudgetId(parentBudget.getBudgetId());
                newSubAwards.setBudgetVersionNumber(parentBudget.getBudgetVersionNumber());
                newSubAwards.setSubAwardNumber(parentBudget.getNextValue("subAwardNumber") != null ? parentBudget.getNextValue("subAwardNumber") : 1);
                newSubAwards.setVersionNumber(null);
                newSubAwards.setHierarchyProposalNumber(childProposalNumber);
                for (BudgetSubAwardAttachment attachment : newSubAwards.getBudgetSubAwardAttachments()) {
                	attachment.setBudgetSubAward(newSubAwards);
                    attachment.setId(null);
                    attachment.setVersionNumber(null);
                }
                for (BudgetSubAwardFiles files : newSubAwards.getBudgetSubAwardFiles()) {
                	files.setBudgetSubAward(newSubAwards);
                    files.setVersionNumber(null);
                }
                parentBudget.getBudgetSubAwards().add(newSubAwards);
            }
            
            int parentStartPeriod = getCorrespondingParentPeriod(parentBudget, childBudget);
            if (parentStartPeriod == -1) {
                throw new ProposalHierarchyException("Cannot find a parent budget period that corresponds to the child period.");
            }

            List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
            List<BudgetPeriod> childPeriods = childBudget.getBudgetPeriods();
            BudgetPeriod parentPeriod, childPeriod;
            Long budgetId = parentBudget.getBudgetId();
            Long budgetPeriodId;
            Integer budgetPeriod;
            BudgetCostShare newCostShare;
            for (BudgetCostShare costShare : childBudget.getBudgetCostShares()) {
                if (StringUtils.isNotEmpty(costShare.getSourceAccount())) {
                    newCostShare = (BudgetCostShare) deepCopy(costShare);
                    newCostShare.setBudgetId(budgetId);
                    newCostShare.setDocumentComponentId(parentBudget.getNextValue(newCostShare.getDocumentComponentIdKey()));
                    newCostShare.setObjectId(null);
                    newCostShare.setVersionNumber(null);
                    newCostShare.setHierarchyProposalNumber(childProposalNumber);
                    newCostShare.setHiddenInHierarchy(true);
                    parentBudget.add(newCostShare);
                }
            }
            
            BudgetUnrecoveredFandA newUnrecoveredFandA;
            for (BudgetUnrecoveredFandA unrecoveredFandA : childBudget.getBudgetUnrecoveredFandAs()) {
                if (StringUtils.isNotEmpty(unrecoveredFandA.getSourceAccount())) {
                    newUnrecoveredFandA = (BudgetUnrecoveredFandA) deepCopy(unrecoveredFandA);
                    newUnrecoveredFandA.setBudgetId(budgetId);
                    newUnrecoveredFandA.setDocumentComponentId(parentBudget.getNextValue(newUnrecoveredFandA.getDocumentComponentIdKey()));
                    newUnrecoveredFandA.setObjectId(null);
                    newUnrecoveredFandA.setVersionNumber(null);
                    newUnrecoveredFandA.setHierarchyProposalNumber(childProposalNumber);
                    newUnrecoveredFandA.setHiddenInHierarchy(true);
                    parentBudget.add(newUnrecoveredFandA);
                }
            }

            for (int i = 0, j = parentStartPeriod; i < childPeriods.size(); i++, j++) {
                childPeriod = childPeriods.get(i);
                parentPeriod = findOrCreateMatchingPeriod(childPeriod, parentBudget);                
                budgetPeriodId = parentPeriod.getBudgetPeriodId();
                budgetPeriod = parentPeriod.getBudgetPeriod();
                BudgetLineItem parentLineItem;
                Integer lineItemNumber;

                if (StringUtils.equals(hierarchyBudgetTypeCode, HierarchyBudgetTypeConstants.SubBudget.code())) {
                    for (BudgetLineItem childLineItem : childPeriod.getBudgetLineItems()) {
                    	if (childLineItem.getSubAwardNumber() != null) {
                    		continue;
                    	}
                        parentLineItem = (BudgetLineItem) deepCopy(childLineItem);
                        lineItemNumber = parentBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setHierarchyProposal(childProposal);
                        
                        parentLineItem.setBudgetLineItemId(null);
                        parentLineItem.setBudgetPeriodId(parentPeriod.getBudgetPeriodId());
                        parentLineItem.setBudgetPeriod(parentPeriod.getBudgetPeriod());
                        parentLineItem.setBudgetPeriodBO(parentPeriod);
                        parentLineItem.setBudget(parentBudget);
                        parentLineItem.setBudgetId(parentBudget.getBudgetId());
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setVersionNumber(null);
                        parentLineItem.setObjectId(null);
                        
                        for (BudgetLineItemCalculatedAmount calAmt : parentLineItem.getBudgetLineItemCalculatedAmounts()) {
                        	calAmt.setBudgetLineItem(parentLineItem);
                            calAmt.setBudgetLineItemCalculatedAmountId(null);
                            calAmt.setBudgetLineItemId(null);
                            calAmt.setBudgetId(budgetId);

                            calAmt.setBudgetPeriod(budgetPeriod);
                            calAmt.setLineItemNumber(lineItemNumber);
                            calAmt.setVersionNumber(null);
                            calAmt.setObjectId(null);
                        }
                        BudgetPerson budgetPerson;
                        for (BudgetPersonnelDetails details : parentLineItem.getBudgetPersonnelDetailsList()) {
                            budgetPerson = personMap.get(details.getPersonSequenceNumber());
                            details.setBudgetLineItem(parentLineItem);
                            details.setBudgetPerson(budgetPerson);
                            details.setJobCode(budgetPerson.getJobCode());
                            details.setPersonId(budgetPerson.getPersonRolodexTbnId());
                            details.setPersonSequenceNumber(budgetPerson.getPersonSequenceNumber());
                            details.setPersonNumber(parentBudget.getNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));

                            details.setBudgetPersonnelLineItemId(null);
                            details.setBudgetId(budgetId);
                            details.setBudgetPeriod(budgetPeriod);
                            details.setLineItemNumber(lineItemNumber);
                            details.setVersionNumber(null);
                            details.setObjectId(null);
                            
                            for (BudgetPersonnelCalculatedAmount calAmt : details.getBudgetPersonnelCalculatedAmounts()) {
                                calAmt.setBudgetPersonnelCalculatedAmountId(null);
                                calAmt.setBudgetPersonnelLineItem(details);
                                calAmt.setBudgetId(budgetId);

                                calAmt.setBudgetPeriod(budgetPeriod);
                                calAmt.setLineItemNumber(lineItemNumber);
                                calAmt.setVersionNumber(null);
                                calAmt.setObjectId(null);
                            }
                        }
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                }
                else { // subproject budget
                    Map<String, String> primaryKeys;
                    CostElement costElement;
                    String directCostElement = parameterService.getParameterValueAsString(Budget.class, PARAMETER_NAME_DIRECT_COST_ELEMENT);
                    String indirectCostElement = parameterService.getParameterValueAsString(Budget.class, PARAMETER_NAME_INDIRECT_COST_ELEMENT);
                    
                    if (childPeriod.getTotalIndirectCost().isNonZero()) {
                        costElement = (CostElement)dataObjectService.findUnique(CostElement.class, QueryByCriteria.Builder.forAttribute("costElement", indirectCostElement).build());
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setHierarchyProposal(childProposal);
                        parentLineItem.setBudget(parentBudget);
                        parentLineItem.setBudgetId(parentBudget.getBudgetId());
                        parentLineItem.setBudgetPeriodId(parentPeriod.getBudgetPeriodId());
                        parentLineItem.setBudgetPeriod(parentPeriod.getBudgetPeriod());
                        parentLineItem.setBudgetPeriodBO(parentPeriod);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setLineItemSequence(lineItemNumber);
                        parentLineItem.setLineItemCost(childPeriod.getTotalIndirectCost());
                        parentLineItem.setIndirectCost(childPeriod.getTotalIndirectCost());
                        parentLineItem.setCostElementBO(costElement);
                        parentLineItem.setCostElement(costElement.getCostElement());
                        parentLineItem.setBudgetCategoryCode(costElement.getBudgetCategoryCode());
                        parentLineItem.setOnOffCampusFlag(costElement.getOnOffCampusFlag());
                        parentLineItem.setApplyInRateFlag(true);
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                    if (childPeriod.getTotalDirectCost().isNonZero()) {
                        costElement = (CostElement)dataObjectService.findUnique(CostElement.class, QueryByCriteria.Builder.forAttribute("costElement", directCostElement).build());
                        parentLineItem = parentBudget.getNewBudgetLineItem();
                        parentLineItem.setLineItemDescription(childProposalNumber);
                        parentLineItem.setStartDate(parentPeriod.getStartDate());
                        parentLineItem.setEndDate(parentPeriod.getEndDate());
                        parentLineItem.setHierarchyProposalNumber(childProposalNumber);
                        parentLineItem.setHierarchyProposal(childProposal);
                        parentLineItem.setBudget(parentBudget);
                        parentLineItem.setBudgetId(parentBudget.getBudgetId());
                        parentLineItem.setBudgetPeriodId(parentPeriod.getBudgetPeriodId());
                        parentLineItem.setBudgetPeriod(parentPeriod.getBudgetPeriod());
                        parentLineItem.setBudgetPeriodBO(parentPeriod);
                        parentLineItem.setBudgetPeriod(budgetPeriod);
                        parentLineItem.setVersionNumber(null);
                        lineItemNumber = parentBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);
                        parentLineItem.setLineItemNumber(lineItemNumber);
                        parentLineItem.setLineItemSequence(lineItemNumber);
                        parentLineItem.setLineItemCost(childPeriod.getTotalDirectCost());
                        parentLineItem.setDirectCost(childPeriod.getTotalDirectCost());
                        parentLineItem.setCostElementBO(costElement);
                        parentLineItem.setCostElement(costElement.getCostElement());
                        parentLineItem.setBudgetCategoryCode(costElement.getBudgetCategoryCode());
                        parentLineItem.setOnOffCampusFlag(costElement.getOnOffCampusFlag());
                        parentLineItem.setApplyInRateFlag(true);
                        parentPeriod.getBudgetLineItems().add(parentLineItem);
                    }
                }
            }
            parentBudget.setStartDate(parentBudget.getBudgetPeriod(0).getStartDate());
            parentBudget.setEndDate(parentBudget.getBudgetPeriod(parentBudget.getBudgetPeriods().size()-1).getEndDate());
        }
        catch (Exception e) {
            LOG.error("Problem copying line items to parent", e);
            throw new ProposalHierarchyException("Problem copying line items to parent", e);
        }

        budgetSummaryService.calculateBudget(parentBudget);
        dataObjectService.save(parentBudget);
        childProposal.setLastSyncedBudget(childBudget);
    }
    
    protected BudgetPeriod findOrCreateMatchingPeriod(BudgetPeriod childPeriod, Budget parentBudget) {
        int priorPeriodIndex = 0;
        int index = 0;
        for (BudgetPeriod period : parentBudget.getBudgetPeriods()) {
            if (period.getStartDate().equals(childPeriod.getStartDate()) && period.getEndDate().equals(childPeriod.getEndDate())) {
                return period;
            } else if (period.getEndDate().before(childPeriod.getStartDate())) {
                priorPeriodIndex = index;
            }
            index++;
        }
        //otherwise add it after most recent budget period which should be valid based on previous validations.
        BudgetPeriod result = new BudgetPeriod();
        result.setStartDate(childPeriod.getStartDate());
        result.setEndDate(childPeriod.getEndDate());
        result.setBudgetPeriod(parentBudget.getBudgetPeriods().get(priorPeriodIndex).getBudgetPeriod()+1);
        result.setBudget(parentBudget);

        getBudgetSummaryService().addBudgetPeriod(parentBudget, result);
        return result;
    }

    protected DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getDevelopmentProposal(hierarchyProposalNumber);
        if (hierarchy == null || !hierarchy.isParent())
            throw new ProposalHierarchyException("Proposal " + hierarchyProposalNumber + " is not a hierarchy");
        return hierarchy;
    }

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal) (dataObjectService.findUnique(DevelopmentProposal.class, QueryByCriteria.Builder.forAttribute("proposalNumber", proposalNumber).build()));
    }
    
    public boolean isSynchronized(DevelopmentProposal childProposal) {
        Integer hc1 = computeHierarchyHashCode(childProposal);
        Integer hc2 = childProposal.getHierarchyLastSyncHashCode();
        return org.apache.commons.lang3.ObjectUtils.equals(hc1, hc2);
    }
    
    protected boolean isBudgetSynchronized(DevelopmentProposal childProposal, ProposalDevelopmentBudgetExt childBudget) throws ProposalHierarchyException {
        if (Objects.equals(childProposal.getLastSyncedBudget(), childBudget)) {
            ObjectUtils.materializeAllSubObjects(childBudget);
            Integer hc1 = computeHierarchyHashCode(childBudget);
            Integer hc2 = childBudget.getHierarchyLastSyncHashCode();
            return org.apache.commons.lang3.ObjectUtils.equals(hc1, hc2);
        } else {
            return false;
        }
    }
    
    
    protected void setInitialPi(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        ProposalPerson pi = child.getPrincipalInvestigator();
        if (pi != null) {
            int index = hierarchy.getProposalPersons().indexOf(pi);
            if (index > -1) {
                hierarchy.getProposalPerson(index).setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
            }
        }
    }
    
    protected List<ProposalDevelopmentBudgetExt> getHierarchyBudgets(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        List<ProposalDevelopmentBudgetExt> hierarchyBudgets = new ArrayList<>();
        for (ProposalDevelopmentBudgetExt budgetVersion : hierarchyProposal.getProposalDocument().getDevelopmentProposal().getBudgets()) {
            hierarchyBudgets.add(budgetVersion);
        }
    
        return hierarchyBudgets;
    }
    
    protected ProposalDevelopmentBudgetExt getHierarchyBudget(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
    	if (!hierarchyProposal.getBudgets().isEmpty()) {
    		return hierarchyProposal.getBudgets().get(0);
    	} else {
    		return null;
    	}
    }
 
    public ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException {
    	if (childProposal.getFinalBudget() == null) {
    		return childProposal.getLatestBudget();
    	} else {
    		return childProposal.getFinalBudget();
    	}
    }
    
    protected void initializeBudget (DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) throws ProposalHierarchyException {
    	ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(hierarchyProposal);
        Budget childBudget = getSyncableBudget(childProposal);
        BudgetPeriod parentPeriod, childPeriod;
        for (int i=0; i < childBudget.getBudgetPeriods().size(); i++) {
            parentPeriod = parentBudget.getBudgetPeriod(i);
            childPeriod = childBudget.getBudgetPeriod(i);
            parentPeriod.setStartDate(childPeriod.getStartDate());
            parentPeriod.setEndDate(childPeriod.getEndDate());
            parentPeriod.setBudgetPeriod(childPeriod.getBudgetPeriod());
        }
        
        parentBudget.setCostSharingAmount(new ScaleTwoDecimal(0));
        parentBudget.setTotalCost(new ScaleTwoDecimal(0));
        parentBudget.setTotalDirectCost(new ScaleTwoDecimal(0));
        parentBudget.setTotalIndirectCost(new ScaleTwoDecimal(0));
        parentBudget.setUnderrecoveryAmount(new ScaleTwoDecimal(0));
        
        parentBudget.setOhRateClassCode(childBudget.getOhRateClassCode());
        parentBudget.setOhRateTypeCode(childBudget.getOhRateTypeCode());
        parentBudget.setUrRateClassCode(childBudget.getUrRateClassCode());
        dataObjectService.save(parentBudget);
    }
    
    public ProposalHierarchyErrorWarningDto validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException {
    	ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(hierarchyProposal);
    	Budget childBudget = getSyncableBudget(childProposal);

        ProposalHierarchyErrorWarningDto retval = null;
        // check that child budget starts on one of the budget period starts
        int correspondingStart = getCorrespondingParentPeriod(parentBudget, childBudget);
        if (correspondingStart == -1) {
            retval = new ProposalHierarchyErrorWarningDto(ERROR_BUDGET_START_DATE_INCONSISTENT, Boolean.TRUE, childProposal.getProposalNumber());
        }
        // check that child budget periods map to parent periods
        else {
            List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
            List<BudgetPeriod> childPeriods = childBudget.getBudgetPeriods();
            BudgetPeriod parentPeriod, childPeriod;
            int i;
            int j;
            for (i = correspondingStart, j = 0; i < parentPeriods.size() && j < childPeriods.size(); i++, j++) {
                parentPeriod = parentPeriods.get(i);
                childPeriod = childPeriods.get(j);
                if (!parentPeriod.getStartDate().equals(childPeriod.getStartDate())
                        || !parentPeriod.getEndDate().equals(childPeriod.getEndDate())) {
                    retval = new ProposalHierarchyErrorWarningDto(ERROR_BUDGET_PERIOD_DURATION_INCONSISTENT, Boolean.TRUE, childProposal.getProposalNumber());
                    break;
                }
            }
            if (retval == null 
                    && !allowEndDateChange 
                    && (j < childPeriods.size() 
                            || childProposal.getRequestedEndDateInitial().after(hierarchyProposal.getRequestedEndDateInitial()))) {
                retval = new ProposalHierarchyErrorWarningDto(QUESTION_EXTEND_PROJECT_DATE_CONFIRM, Boolean.TRUE, childProposal.getProposalNumber());
            }
        }
        
        return retval;
    }
    
    protected int getCorrespondingParentPeriod(Budget parentBudget, Budget childBudget) {
        int correspondingStart = -1;
 
        // using start date of first period as start date and end date of last period
        // as end because budget start and end are not particularly reliable
        Date childStart = childBudget.getBudgetPeriod(0).getStartDate();
        Date parentStart = parentBudget.getBudgetPeriod(0).getStartDate();
        Date parentEnd = parentBudget.getBudgetPeriod(parentBudget.getBudgetPeriods().size()-1).getEndDate();
        // check that child budget starts somewhere during parent budget
        if (childStart.compareTo(parentStart) >= 0
                && childStart.compareTo(parentEnd) < 0) {
            // check that child budget starts on one of the budget period starts
            List<BudgetPeriod> parentPeriods = parentBudget.getBudgetPeriods();
            for (int i=0; i<parentPeriods.size(); i++) {
                if (childStart.equals(parentPeriods.get(i).getStartDate())) {
                    correspondingStart = i;
                    break;
                }
            }
        }
        return correspondingStart;
    }
    
    protected void removeChildElements(DevelopmentProposal parentProposal, String childProposalNumber) {
        List<PropScienceKeyword> keywords = parentProposal.getPropScienceKeywords();
        for (int i=keywords.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, keywords.get(i).getHierarchyProposalNumber())) {
                keywords.remove(i);
            }
        }

        List<ProposalSpecialReview> reviews = parentProposal.getPropSpecialReviews();
        for (int i=reviews.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, reviews.get(i).getHierarchyProposalNumber())) {
                reviews.remove(i);
            }
        }

        List<Narrative> narratives = parentProposal.getNarratives();
        for (int i=narratives.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, narratives.get(i).getHierarchyProposalNumber())) {
                dataObjectService.delete(narratives.remove(i));
            }
        }
        ProposalDevelopmentBudgetExt parentBudget = getHierarchyBudget(parentProposal);
        if (parentBudget != null) {
        	removeChildBudgetElements(parentProposal, parentBudget, childProposalNumber);
        }
    }
    
    protected void removeChildBudgetElements(DevelopmentProposal parentProposal, ProposalDevelopmentBudgetExt parentBudget, String childProposalNumber) {
        QueryByCriteria query = QueryByCriteria.Builder.fromPredicates(PredicateFactory.equal("hierarchyProposalNumber", childProposalNumber), PredicateFactory.equal("budgetId", parentBudget.getBudgetId()));
        dataObjectService.deleteMatching(BudgetProjectIncome.class, query);
        dataObjectService.deleteMatching(BudgetCostShare.class, query);
        dataObjectService.deleteMatching(BudgetUnrecoveredFandA.class, query);
        
        for (Iterator<BudgetSubAwards> iter = parentBudget.getBudgetSubAwards().iterator(); iter.hasNext();) {
        	BudgetSubAwards subAward = iter.next();
        	if (StringUtils.equals(childProposalNumber, subAward.getHierarchyProposalNumber())) {
        		dataObjectService.delete(subAward);
        		parentBudget.removeBudgetLineItems(subAward.getBudgetLineItems());
        		iter.remove();
        	}
        }

        for (Iterator<BudgetPerson> iter = parentBudget.getBudgetPersons().iterator(); iter.hasNext(); ) {
        	BudgetPerson person = iter.next();
        	if (StringUtils.equals(childProposalNumber, person.getHierarchyProposalNumber())) {
        		dataObjectService.delete(person);
        		iter.remove();
        	}
        }
        List<BudgetPeriod> periodsToDelete = new ArrayList<>();
        boolean deletePeriods = true;
        for (int i = parentBudget.getBudgetPeriods().size()-1; i >= 0; i--) {
        	BudgetPeriod period = parentBudget.getBudgetPeriod(i);
	        for (Iterator<BudgetLineItem> lineItemIter = period.getBudgetLineItems().iterator(); lineItemIter.hasNext();) {
	        	BudgetLineItem lineItem = lineItemIter.next();
	        	if (StringUtils.equals(childProposalNumber, lineItem.getHierarchyProposalNumber())) {
	        		dataObjectService.delete(lineItem);
	        		lineItemIter.remove();
	        		parentBudget.getBudgetLineItems().remove(lineItem);
	        	}
	        }
	        if (deletePeriods) {
	        	if (parentBudget.getBudgetPeriods().get(i).getBudgetLineItems().size() == 0 && i > 0) {
	        		periodsToDelete.add(parentBudget.getBudgetPeriods().get(i));
	        	} else {
	        		deletePeriods = true;
	        	}
	        }
        }
        for (BudgetPeriod period : periodsToDelete) {
        	dataObjectService.delete(period);
        	parentBudget.getBudgetPeriods().remove(period);
        }

        parentBudget.setEndDate(parentBudget.getBudgetPeriods().get(parentBudget.getBudgetPeriods().size()-1).getEndDate());       
    }
    
    protected void prepareHierarchySync(DevelopmentProposal hierarchyProposal) {
        prepareHierarchySync(hierarchyProposal.getProposalDocument());
    }
    
    protected void prepareHierarchySync(ProposalDevelopmentDocument pdDoc) {
        pdDoc.refreshReferenceObject("documentNextvalues");
    }
    
    protected void finalizeHierarchySync(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        try {
            documentService.saveDocument(pdDoc);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }
    
    protected DevelopmentProposal finalizeHierarchySync(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        DevelopmentProposal savedParentProposal = dataObjectService.save(hierarchyProposal);
        return savedParentProposal;
    }

    /*
    This is the first copy from lead child to parent, so copy EVERY NARRATIVE over. Narrative includes institute attachments.
     */
    protected void copyInitialAttachments(DevelopmentProposal srcProposal, DevelopmentProposal destProposal) {

        ProposalPersonBiography destPropPersonBio;
        ProposalPerson srcPerson = null;
        ProposalPerson destPerson = null;
        for (ProposalPersonBiography srcPropPersonBio : srcProposal.getPropPersonBios()) {
            for (ProposalPerson person : srcProposal.getProposalPersons()) {
                if (person.getProposalPersonNumber().equals(srcPropPersonBio.getProposalPersonNumber())) {
                    srcPerson = person;
                    break;
                }
            }
            for (ProposalPerson person : destProposal.getProposalPersons()) {
                if (person.equals(srcPerson)) {
                    destPerson = person;
                    break;
                }
            }
            destPropPersonBio = (ProposalPersonBiography)deepCopy(srcPropPersonBio);
            destPropPersonBio.setDevelopmentProposal(destProposal);
            destPropPersonBio.setProposalNumber(destProposal.getProposalNumber());
            destPropPersonBio.setProposalPersonNumber(destPerson.getProposalPersonNumber());
            destPropPersonBio.setPersonId(destPerson.getPersonId());
            destPropPersonBio.setRolodexId(destPerson.getRolodexId());
            destProposal.getPropPersonBios().add(destPropPersonBio);
        }

        for (Narrative srcNarrative : srcProposal.getNarratives()) {
            addNarrativeToParent(destProposal, srcProposal, srcNarrative, destProposal.getNarratives());
        }

        for(Narrative narrative : destProposal.getNarratives()) {
            narrative.setNarrativeUserRights(null);
        }

        for (Narrative attInternal : srcProposal.getInstituteAttachments()) {
            addNarrativeToParent(destProposal, srcProposal, attInternal, destProposal.getInstituteAttachments());
        }
    }

    protected String getHierarchyChildRouteStatus( String oldStatus, String newStatus) {
        
        LOG.info( String.format( "Route status change %s:%s",oldStatus,newStatus));
        
        String retCd = null;
        if( StringUtils.equals(newStatus,KewApiConstants.ROUTE_HEADER_ENROUTE_CD) 
                && ( StringUtils.equals( oldStatus, KewApiConstants.ROUTE_HEADER_INITIATED_CD) 
                || StringUtils.equals(oldStatus, KewApiConstants.ROUTE_HEADER_SAVED_CD)  
                || StringUtils.equals(KewApiConstants.ROUTE_HEADER_ENROUTE_CD, oldStatus)) ) { 
                retCd = renderMessage( HIERARCHY_CHILD_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(newStatus, KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
                retCd = renderMessage( HIERARCHY_CHILD_FINAL_APPSTATUS );
        } else if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
                retCd = renderMessage( HIERARCHY_CHILD_DISAPPROVE_APPSTATUS );
        } else if( StringUtils.equals( newStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD ) ) {
               retCd = renderMessage( HIERARCHY_CHILD_CANCEL_APPSTATUS );
        } else {
            LOG.warn(String.format("Do not know how to calculate hierarchy child status for %s to %s",oldStatus,newStatus) );
        }
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format("Route status for children:%s",retCd ));
        return retCd;
    }

    
    /**
     * Creates a hash of the data pertinent to a hierarchy for comparison during hierarchy syncing. 
     */
    protected int computeHierarchyHashCode(DevelopmentProposal proposal) {
        int prime = 31;
        int result = 1;
        for (ProposalPerson person : proposal.getProposalPersons()) {
            result = prime * result + person.hashCode();
        }
        for (Narrative narrative : proposal.getNarratives()) {
            result = prime * result + narrative.hierarchyHashCode();
        }
        for (PropScienceKeyword keyword : proposal.getPropScienceKeywords()) {
            result = prime * result + keyword.getScienceKeyword().getCode().hashCode();
        }
        for (ProposalSpecialReview review : proposal.getPropSpecialReviews()) {
            result = prime * result + review.hierarchyHashCode();
        }
        return result;
    }
    
    /**
     * Creates a hash of the data pertinent to a hierarchy for comparison during hierarchy syncing. 
     */
    protected int computeHierarchyHashCode(Budget budget) {
        int prime = 31;
        int result = 1;
        budgetCalculationService.calculateBudget(budget);
        budgetCalculationService.calculateBudgetSummaryTotals(budget);
        result = prime * result + budget.getBudgetSummaryTotals().hashCode();
        return result;
    }    

    protected List<ProposalDevelopmentDocument> getChildProposalDevelopmentDocuments(String parentProposalNumber) throws ProposalHierarchyException {
       
        List<ProposalDevelopmentDocument> outList = new ArrayList<ProposalDevelopmentDocument>();
        for( DevelopmentProposal child : getHierarchyChildren(parentProposalNumber)) {
            try {
                outList.add( (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId( child.getProposalDocument().getDocumentNumber() ) );
            }
            catch (WorkflowException e) {
                LOG.error( String.format( "Could not find document for child proposal number %s", parentProposalNumber, child.getProposalNumber() ), e);
                throw new ProposalHierarchyException( String.format( "Could not find document for child proposal number %s", parentProposalNumber, child.getProposalNumber() ), e );
            }
            
        }
        
        return outList;
        
    }

    @Override
    public List<DevelopmentProposal> getHierarchyChildren(String parentProposalNumber) {
        List<DevelopmentProposal> children = new ArrayList<DevelopmentProposal>();
        for( String childProposalNumber : proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber)) {
            children.add(getDevelopmentProposal(childProposalNumber));
        }
        return children;
    }
    
    @Override
    public WorkflowDocument getParentWorkflowDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
            return getParentDocument( child ).getDocumentHeader().getWorkflowDocument();
    }

    
    @Override
    public ProposalDevelopmentDocument getParentDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
        try {
            DevelopmentProposal parentProposal = getHierarchy(child.getDevelopmentProposal().getHierarchyParentProposalNumber());
            String parentDocumentNumber = parentProposal.getProposalDocument().getDocumentNumber();
            return (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(parentDocumentNumber);
        } catch (WorkflowException e) {
            throw new ProposalHierarchyException( String.format("Could not lookup hierarchy workflow status for child:%s",child.getDocumentHeader().getDocumentNumber()),e);
        }
    }
    
    
    /**
     * Reject a proposal by sending it to the first node ( as named by PROPOSALDEVELOPMENTDOCUMENT_KEW_INITIAL_NODE_NAME )
     * @param proposalDoc The ProposalDevelopmentDocument that should be rejected.
     * @param appDocStatus the application status to set in the workflow document.
     * @param principalId the principal we are rejecting the document as.
     * @param appDocStatus the application document status to apply ( does not apply if null )
     * @throws WorkflowException
     */
    protected void rejectProposal( ProposalDevelopmentDocument proposalDoc, String reason, String principalId, String appDocStatus ) throws WorkflowException  {
        kcDocumentRejectionService.reject(proposalDoc.getDocumentHeader().getWorkflowDocument(), reason, principalId, appDocStatus );
    }
    
    
    /**
     * Reject an entire proposal hierarchy.  This works by first rejecting each child, and then rejecting the parent.
     * @param hierarchyParent The hierarchy to reject
     * @param reason the reason to be applied to the annotation field.  The reason will be pre-pended with static text indicating if it was a child or the parent.
     * @param principalId the id of the principal that is rejecting the document.
     * @throws ProposalHierarchyException If hierarchyParent is not a hierarchy, or there was a problem rejecting one of the documents.
     */
    protected void rejectProposalHierarchy(ProposalDevelopmentDocument hierarchyParent, String reason, String principalId ) throws ProposalHierarchyException {
        
      //1. reject the parent.
        try {
            rejectProposal( hierarchyParent, renderMessage( PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason ), principalId, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException( String.format( "WorkflowException encountered rejecting proposal hierarchy parent %s", hierarchyParent.getDevelopmentProposal().getProposalNumber() ),e);
        }
        
        //2. Try to reject all of the children.
        for( ProposalDevelopmentDocument child : getChildProposalDevelopmentDocuments(hierarchyParent.getDevelopmentProposal().getProposalNumber())) {
            try {
                rejectProposal( child, renderMessage( HIERARCHY_ROUTING_PARENT_REJECTED_ANNOTATION, reason ), identityService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER ).getPrincipalId(), renderMessage( HIERARCHY_CHILD_REJECTED_APPSTATUS ) );
            } catch (WorkflowException e) {
                throw new ProposalHierarchyException( String.format( "WorkflowException encountered rejecting child document %s", child.getDevelopmentProposal().getProposalNumber()), e );
            }
        }
     
    }
    
    
    @Override
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalName, MultipartFile rejectFile )
    throws WorkflowException, ProposalHierarchyException, IOException {
        DevelopmentProposal pbo = getDevelopmentProposal(proposalNumber);
        ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(pbo.getProposalDocument().getDocumentNumber());
        if( !pbo.isInHierarchy() ) {
            rejectProposal( pDoc, renderMessage( PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason ), principalName, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
        } else if ( pbo.isParent() ) {
            rejectProposalHierarchy( pDoc, reason, principalName );
        } else {
            //it is a child or in some unknown state, either way we do not support rejecting it.
            throw new UnsupportedOperationException( String.format( "Cannot reject proposal %s it is a hierarchy child or ", proposalNumber ));
        }
        
        if (rejectFile != null && rejectFile.getBytes().length > 0) {
            Narrative narrative = new Narrative();
            narrative.setName(rejectFile.getOriginalFilename());
            narrative.setComments(reason);
            try {
                narrative.init(rejectFile);
            } catch (Exception e) {
               throw new RuntimeException("Error Initializing narrative attachment file",e);
            }
            narrative.setNarrativeTypeCode(getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.REJECT_NARRATIVE_TYPE_CODE_PARAM));
            NarrativeStatus status = (NarrativeStatus) dataObjectService.findUnique(NarrativeStatus.class, QueryByCriteria.Builder.forAttribute("code", "C").build());
            narrative.setNarrativeStatus(status);
            narrative.setModuleStatusCode(status.getCode());
            narrative.setModuleTitle("Proposal rejection attachment.");
            narrative.setContactName(globalVariableService.getUserSession().getPrincipalName());
            narrative.setPhoneNumber(globalVariableService.getUserSession().getPerson().getPhoneNumber());
            narrative.setEmailAddress(globalVariableService.getUserSession().getPerson().getEmailAddress());
            getLegacyNarrativeService().prepareNarrative(pDoc,narrative);
            pDoc.getDevelopmentProposal().getInstituteAttachments().add(narrative);
            dataObjectService.save(pDoc);
        }
        
    }
    
    
    /**
     * Based on the hierarchy, and route status change of the parent, calculate what route action should be taken on the children.
     * @param hierarchy the heirarchy being routed
     * @param dto the route status change information.
     * @return The route action to take on the children.
     */
    protected String calculateChildRouteStatus( ProposalDevelopmentDocument hierarchy, DocumentRouteStatusChange dto ) {
        
        String parentOldStatus = dto.getOldRouteStatus();
        String parentNewStatus = dto.getNewRouteStatus();
        String newChildStatusTarget = "";

        if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_INITIATED_CD)) {
            // nothing to do here.
        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_SAVED_CD)) {
            // previous status was saved
            newChildStatusTarget = parentNewStatus;
            if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_ENROUTE_CD)) {
                // nothing to do
            }
            else if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
                // nothing to do.
            }
            else {
                throw new UnsupportedOperationException(String.format(
                        "Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,
                        parentNewStatus));
            }
        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_ENROUTE_CD)) {
            // we are moving from enroute to some other state.

            if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_CANCEL_CD)
                    || StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                newChildStatusTarget = parentNewStatus;
            }
            else if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_PROCESSED_CD)) {
                // nothing to do here, wait for the document to go final.
            } else if( StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_ENROUTE_CD)) {
                //special case, document has been rejected and being approved again to simulate entry into workflow.
                //this value will trigger an approve.
                newChildStatusTarget = KewApiConstants.ROUTE_HEADER_ENROUTE_CD;
            }
            else {
                throw new UnsupportedOperationException(String.format("Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
            }

        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_PROCESSED_CD)) {
            // nothing to do here.
        }
        else if (StringUtils.equals(parentOldStatus, KewApiConstants.ROUTE_HEADER_PROCESSED_CD)) {
            if (StringUtils.equals(parentNewStatus, KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
                newChildStatusTarget = parentNewStatus;
            }
            else {
                throw new UnsupportedOperationException(String.format("Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
            }
        }
        else {
            throw new UnsupportedOperationException(String.format(
                    "Do not know how to handle children of hierarchy for route status chnage from %s to %s", parentOldStatus,parentNewStatus));
        }
        return newChildStatusTarget;
    }

    @Override
    public void routeHierarchyChildren(ProposalDevelopmentDocument proposalDevelopmentDocument, DocumentRouteStatusChange dto ) throws ProposalHierarchyException {
        
        String childStatusTarget = calculateChildRouteStatus(proposalDevelopmentDocument, dto );
        WorkflowDocument workdoc;
        ProposalDevelopmentDocument child = null;
        try {
            LOG.info(  IdentityService.class );
            for (ProposalDevelopmentDocument c : getChildProposalDevelopmentDocuments(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber() )) {
                child = c;
                if (!StringUtils.equals("", childStatusTarget)) {

                    if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_ENROUTE_CD, childStatusTarget)) {
                        //The user currently must initially route the child documents in order for them to hold in the system users action list.

                        workdoc = WorkflowDocumentFactory.loadDocument(child.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId(), child.getDocumentHeader().getWorkflowDocument().getDocumentId() );
                        workdoc.setApplicationDocumentStatus(getHierarchyChildRouteStatus( dto.getOldRouteStatus(), dto.getNewRouteStatus() ));
                        if( !workdoc.isEnroute() ) {
                            workdoc.route(renderMessage( HIERARCHY_ROUTING_PARENT_SUBMITTED_ANNOTATION ));

                        } else {
                            //this means the status change is actually in the form of an approve action on a document that was moved back to the initial node.
                            //we need to do an approval.
                            workdoc.approve(renderMessage( HIERARCHY_ROUTING_PARENT_RESUBMITTED_ANNOTATION ));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_ENROUTE_APPSTATUS ) );
                        }

                    } else {
                        workdoc = WorkflowDocumentFactory.loadDocument( identityService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER ).getPrincipalId(),child.getDocumentHeader().getWorkflowDocument().getDocumentId() );
                        workdoc.setApplicationDocumentStatus(getHierarchyChildRouteStatus( dto.getOldRouteStatus(), dto.getNewRouteStatus() ));

                        if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_CANCEL_CD,childStatusTarget)) {
                            workdoc.cancel(renderMessage( HIERARCHY_ROUTING_PARENT_CANCELLED_ANNOTATION));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_CANCEL_APPSTATUS  ));

                        } else if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD, childStatusTarget)) {
                            workdoc.approve(renderMessage( HIERARCHY_ROUTING_PARENT_APPROVED_ANNOTATION ));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_FINAL_APPSTATUS ));

                        } else if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD, childStatusTarget)) {
                            workdoc.disapprove(renderMessage( HIERARCHY_ROUTING_PARENT_DISAPPROVED_ANNOTATION ));
                            workdoc.setApplicationDocumentStatus(renderMessage( HIERARCHY_CHILD_DISAPPROVE_APPSTATUS ));
                        } else {
                            throw new UnsupportedOperationException(String.format("Do not know how to handle new child status of %s", childStatusTarget));
                        }

                    }
                }
            }
        } catch ( Exception we ) {
            throw new ProposalHierarchyException( String.format( "Exception encountrered while attempting to route child proposal %s ( document #%s ) of proposal hierarchy %s ( document #%s )", child.getDevelopmentProposal().getProposalNumber(), child.getDocumentNumber(), proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), proposalDevelopmentDocument.getDocumentNumber() ), we);
        }
        
    }
    
    public void calculateAndSetProposalAppDocStatus( ProposalDevelopmentDocument doc, DocumentRouteStatusChange dto  ) throws ProposalHierarchyException {
        String principalId = globalVariableService.getUserSession().getPrincipalId();
        if( StringUtils.equals( dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_ENROUTE_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            updateAppDocStatus( doc, principalId, HIERARCHY_CANCEL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_DISAPPROVE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_FINAL_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_FINAL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_PROCESSED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_PROCESSED_APPSTATUS ) ;
        } 
    }
    
    protected void updateAppDocStatus( ProposalDevelopmentDocument doc, String principalId, String newStatus ) throws ProposalHierarchyException {
        try {
            WorkflowDocument wdoc = WorkflowDocumentFactory.loadDocument(principalId, doc.getDocumentHeader().getWorkflowDocument().getDocumentId() );
            wdoc.setApplicationDocumentStatus(renderMessage( newStatus ));
        }
        catch (Exception e) {
            throw new ProposalHierarchyException( String.format( "Exception encountrered while attempting to update App Doc Status of proposal %s ( document #%s )", doc.getDevelopmentProposal().getProposalNumber(), doc.getDocumentNumber() ), e);
        }
    }
    
    
    
    public boolean allChildBudgetsAreComplete(String parentProposalNumber) {
        boolean retval = true;
        String completeCode = parameterService.getParameterValueAsString(Budget.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        for (ProposalBudgetStatus status : proposalHierarchyDao.getHierarchyChildProposalBudgetStatuses(parentProposalNumber)) {
            if (!StringUtils.equalsIgnoreCase(completeCode, status.getBudgetStatusCode())) {
                retval = false;
                break;
            }
        }
        return retval;
    }
    
    protected boolean rolesAreSimilar(ProposalPerson person1, ProposalPerson person2) {
        boolean isInvestigator1 = StringUtils.equals(person1.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person1.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        boolean isInvestigator2 = StringUtils.equals(person2.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person2.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        return isInvestigator1 == isInvestigator2;
    }

    @Override
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) throws ProposalHierarchyException {
        List<HierarchyPersonnelSummary> summaries = new ArrayList<HierarchyPersonnelSummary>();
        
        List<String> proposalNumbers = new ArrayList<String>();
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber));
        Collections.sort(proposalNumbers);
        
        for (String proposalNumber : proposalNumbers) {
            HierarchyPersonnelSummary summary = new HierarchyPersonnelSummary();
            
            DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
            List<Budget> hierarchyBudgets = new ArrayList<Budget>();
            for (ProposalDevelopmentBudgetExt hierarchyBudget : getHierarchyBudgets(childProposal)) {
                hierarchyBudgets.add(hierarchyBudget);
            }
            Collections.sort(hierarchyBudgets);
            
            summary.setProposalNumber(proposalNumber);
            summary.setHierarchyBudgets(hierarchyBudgets);
            summaries.add(summary);
        }
        
        return summaries;
    }
    
    @Override
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal proposal = getDevelopmentProposal(proposalNumber);
        List<HierarchyProposalSummary> summaries = new ArrayList<HierarchyProposalSummary>();

        List<String> proposalNumbers = new ArrayList<String>();
        if (proposal.isParent()) {
            proposalNumbers.add(proposalNumber);
        }
        else if (proposal.isChild()) {
            proposalNumbers.add(proposal.getHierarchyParentProposalNumber());
        }
        else {
            throw new ProposalHierarchyException("Proposal " + proposalNumber + " is not a member of a hierarchy.");            
        }
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(proposalNumbers.get(0)));

        HierarchyProposalSummary summary;
        for (String number : proposalNumbers) {
            summary = new HierarchyProposalSummary();
            summary.setProposalNumber(number);
            if (!StringUtils.equals(number, proposalNumbers.get(0))) {
                summary = getProposalSummary(number);
            }
            summaries.add(summary);
        }
        return summaries;
    }
    
    public HierarchyProposalSummary getProposalSummary(String proposalNumber) throws ProposalHierarchyException {
        HierarchyProposalSummary summary = new HierarchyProposalSummary();
        summary.setProposalNumber(proposalNumber);
        DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
        summary.setSynced(isSynchronized(childProposal));
        ProposalDevelopmentBudgetExt budget = getSyncableBudget(childProposal);
        summary.setBudgetSynced(isBudgetSynchronized(childProposal, budget));
        return summary;
    }

    @Override
    public List<DevelopmentProposal> getHierarchyProposals(DevelopmentProposal proposal) {
        List<DevelopmentProposal> hierachyProposals = new ArrayList<DevelopmentProposal>();

        List<String> proposalNumbers = new ArrayList<String>();
        if (proposal.isParent()) {
            proposalNumbers.add(proposal.getProposalNumber());
        }
        else if (proposal.isChild()) {
            proposalNumbers.add(proposal.getHierarchyParentProposalNumber());
        }
        else {
            return hierachyProposals;
        }
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(proposalNumbers.get(0)));

        hierachyProposals.addAll(getDataObjectService().findMatching(DevelopmentProposal.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("proposalNumber", proposalNumbers))
                .setOrderByFields(OrderByField.Builder.create("hierarchyStatus", OrderDirection.DESCENDING).build()).build()).getResults());

        return hierachyProposals;
    }

    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId) {
        boolean valid = true;
        valid &= kcAuthorizationService.hasPermission(principalId, childProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        try {
            valid &= kcAuthorizationService.hasPermission(principalId, getHierarchy(childProposal.getHierarchyParentProposalNumber()).getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        }
        catch (ProposalHierarchyException e) {
            valid = false;
        }
        return valid;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildForRemoval(DevelopmentProposal child) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
        try {
            DevelopmentProposal hierarchy = lookupParent(child);
        }
        catch (ProposalHierarchyException e) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_UNEXPECTED, Boolean.TRUE, e.getMessage()));
        }
        return errors;
    }

    protected String renderMessage( String key, String... params ) {
       String msg = kualiConfigurationService.getPropertyValueAsString(key);
       for (int i = 0; i < params.length; i++) {
           msg = replace(msg, "{" + i + "}", params[i]);
       }
       return msg;
       
    }
    protected KcDocumentRejectionService getKcDocumentRejectionService() {
        return kcDocumentRejectionService;
    }
    protected DocumentService getDocumentService() {
        return documentService;
    }
    protected KcAuthorizationService getKcAuthorizationService() {
        return kcAuthorizationService;
    }
    protected ProposalHierarchyDao getProposalHierarchyDao() {
        return proposalHierarchyDao;
    }
    protected LegacyNarrativeService getLegacyNarrativeService() {
        return legacyNarrativeService;
    }
    protected BudgetService getBudgetService() {
        return budgetService;
    }
    protected ProposalPersonBiographyService getProposalPersonBiographyService() {
        return proposalPersonBiographyService;
    }
    protected ParameterService getParameterService() {
        return parameterService;
    }
    protected IdentityService getIdentityManagementService() {
        return identityService;
    }
    protected ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    protected BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }
    protected BudgetCalculationService getBudgetCalculationService(){return budgetCalculationService;}
    protected BudgetPersonnelBudgetService getBudgetPersonnelBudgetService () {return budgetPersonnelBudgetService;}

    public IdentityService getIdentityService() {
        return identityService;
    }


    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildForSync (DevelopmentProposal child, DevelopmentProposal hierarchy, boolean allowEndDateChange) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
        boolean valid = true;
        if (child.getPrincipalInvestigator() == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_SYNC_NO_PRINCIPLE_INVESTIGATOR, Boolean.TRUE, child.getProposalNumber()));
        }
        try {
            // add budget validation here.

        }
        catch (ProposalHierarchyException e) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_UNEXPECTED, Boolean.TRUE, e.getMessage()));
        }
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildCandidate(DevelopmentProposal proposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();

        if (proposal.isInHierarchy()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_ALREADY_MEMBER, Boolean.TRUE, new String[0]));
        }
        if (proposal.getBudgets().isEmpty()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NO_BUDGET_VERSION, Boolean.TRUE, new String[0]));
        }
        else {
            if (!hasFinalBudget(proposal)) {
                errors.add(new ProposalHierarchyErrorWarningDto(WARNING_LINK_NO_FINAL_BUDGET, Boolean.FALSE, new String[]{proposal.getProposalNumber()}));
            }
        }
        if (proposal.getPrincipalInvestigator() == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR, Boolean.TRUE, new String[0]));
        }
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child, boolean allowEndDateChange) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
        boolean valid = true;
        if (!StringUtils.equalsIgnoreCase(hierarchy.getSponsorCode(), child.getSponsorCode())) {
            errors.add(new ProposalHierarchyErrorWarningDto(WARNING_LINK_DIFFERENT_SPONSOR, Boolean.FALSE, new String[0]));
        }
        try {
            // add budget validation here
        }
        catch (ProposalHierarchyException e) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_UNEXPECTED, Boolean.TRUE, e.getMessage()));
        }
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateParent(DevelopmentProposal proposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
        if (!proposal.isParent()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NOT_PARENT, Boolean.TRUE, new String[0]));
        }
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateSponsor(DevelopmentProposal childProposal, DevelopmentProposal parentProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<ProposalHierarchyErrorWarningDto>();
        if(!StringUtils.equals(childProposal.getSponsorCode(),parentProposal.getSponsorCode())) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_DIFFERENT_SPONSORS, Boolean.TRUE, new String[0]));
        }
        return errors;
    }

    private boolean hasFinalBudget(DevelopmentProposal proposal) {
    	return proposal.getFinalBudget() != null;
    }
	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

    public KeyPersonnelService getKeyPersonnelService() {
        return keyPersonnelService;
    }
	protected PropDevBudgetSubAwardService getPropDevBudgetSubAwardService() {
		return propDevBudgetSubAwardService;
	}
	public void setPropDevBudgetSubAwardService(
			PropDevBudgetSubAwardService propDevBudgetSubAwardService) {
		this.propDevBudgetSubAwardService = propDevBudgetSubAwardService;
	}

}
