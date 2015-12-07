/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.propdev.impl.attachment.*;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcDataObject;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.budget.hierarchy.ProposalBudgetHierarchyService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.OrderByField;
import org.kuali.rice.core.api.criteria.OrderDirection;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.*;

@Component("proposalHierarchyService")
@Transactional
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {
    
    private static final Log LOG = LogFactory.getLog(ProposalHierarchyServiceImpl.class);
    private static final String DOCUMENT_NEXTVALUES = "documentNextvalues";
    private static final String HIERARCHY_STATUS = "hierarchyStatus";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String CODE = "code";
    private static final String COMPLETE = "C";
    private static final String NARRATIVE_TYPE = "narrativeType";
    private static final String HIERARCHY_PROPOSAL_NUMBER = "hierarchyProposalNumber";
    private static final String NARRATIVE_TYPE_CODE = "narrativeTypeCode";

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
    @Qualifier("proposalPersonBiographyService")
    private ProposalPersonBiographyService proposalPersonBiographyService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;
    @Autowired
    @Qualifier("kcDocumentRejectionService")
    private KcDocumentRejectionService kcDocumentRejectionService;

    @Autowired
    @Qualifier("kradWorkflowDocumentService")
    private WorkflowDocumentService kradWorkflowDocumentService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("pessimisticLockService")
    private PessimisticLockService pessimisticLockService;
    
    @Autowired
    @Qualifier("proposalBudgetHierarchyService")
    private ProposalBudgetHierarchyService proposalBudgetHierarchyService;

    //Setters for dependency injection
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

        copyOpportunity(initialChild, hierarchyDoc.getDevelopmentProposal());

        // add aggregator to the document
        kcAuthorizationService.addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, hierarchyDoc);

        proposalBudgetHierarchyService.initializeBudget(hierarchyDoc.getDevelopmentProposal(), initialChild);
        
        //we are creating the attachments for the first time so do not sync personnel attachments at this time, just copy as is.
        linkChild(hierarchyDoc.getDevelopmentProposal(), initialChild, HierarchyBudgetTypeConstants.SubBudget.code(), false);
        setInitialPi(hierarchyDoc.getDevelopmentProposal(), initialChild);
        copyInitialAttachments(initialChild, hierarchyDoc.getDevelopmentProposal());

        addCreateDetails(newDoc);

        LOG.info(String.format("***Initial Child (#%s) linked to Parent (#%s)", initialChild.getProposalNumber(), hierarchyDoc.getDevelopmentProposal().getProposalNumber()));
        
        finalizeHierarchySync(hierarchyDoc.getDevelopmentProposal());
        
        // return the parent id
        LOG.info(String.format("***Hierarchy creation (#%s) complete", hierarchyDoc.getDevelopmentProposal().getProposalNumber()));
        return hierarchyDoc.getDevelopmentProposal().getProposalNumber();
    }

    private void addCreateDetails(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        proposalDevelopmentDocument.getDevelopmentProposal().setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        proposalDevelopmentDocument.getDevelopmentProposal().setCreateUser(getGlobalVariableService().getUserSession().getLoggedInUserPrincipalName());
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
            documentHeader.setDocumentNumber(workflowDocument.getDocumentId());
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
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if (hierarchyProposal == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_PROPOSAL_DOES_NOT_EXIST, Boolean.TRUE));
            return errors;
        } else {
                if (!hierarchyProposal.isParent()) {
                    errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_PROPOSAL_NOT_HIERARCHY_PARENT,
                                                                Boolean.TRUE, hierarchyProposal.getProposalNumber()));
                }
        }
        if (childProposal.isInHierarchy()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_NOT_HIERARCHY_CHILD, Boolean.TRUE, childProposal.getProposalNumber()));
        }
        errors.addAll(validateChildBudgetPeriods(hierarchyProposal,childProposal,true));
        errors.addAll(validateSponsor(childProposal, hierarchyProposal));
        errors.addAll(validateParent(hierarchyProposal));
        errors.addAll(validateIsParentLocked(hierarchyProposal));
        errors.addAll(validateIsAggregatorOnParent(hierarchyProposal));

        List<ProposalHierarchyErrorWarningDto> sponsorErrors = validateSponsor(childProposal, hierarchyProposal);

        errors.addAll(sponsorErrors);

        return errors;
    }

    public DevelopmentProposal removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        String hierarchyProposalNumber = childProposal.getHierarchyParentProposalNumber();
        DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);

        List<String> childProposalNumbers = proposalHierarchyDao.getHierarchyChildProposalNumbers(hierarchyProposalNumber);
        boolean isLast = childProposalNumbers.size()==1 && StringUtils.equals(childProposalNumbers.get(0), childProposal.getProposalNumber());
     
        childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
        childProposal.setHierarchyParentProposalNumber(null);
        if (StringUtils.equalsIgnoreCase(hierarchyProposal.getHierarchyOriginatingChildProposalNumber(), childProposal.getProposalNumber()) && hierarchyProposal.getPrincipalInvestigator() != null) {
            hierarchyProposal.getPrincipalInvestigator().setHierarchyProposalNumber(null);
        }

        removeChildElements(hierarchyProposal, childProposal.getProposalNumber());
        removeAllChildPersonnelFromParent(hierarchyProposal, childProposal);

        if (isLast) {
            try {
                childProposal = dataObjectService.save(childProposal);
                Document doc = documentService.getByDocumentHeaderId(hierarchyProposal.getProposalDocument().getDocumentNumber());
                documentService.cancelDocument(doc, "Removed last child from Proposal Hierarchy");
                return childProposal;
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
            dataObjectService.save(hierarchyProposal);
            return childProposal;
        }
    }

    protected void removeDeletedPersonnelFromParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (Iterator<ProposalPerson> iterator = hierarchyProposal.getProposalPersons().iterator(); iterator.hasNext();) {
            ProposalPerson person = iterator.next();

            if (StringUtils.equals(person.getHierarchyProposalNumber(), childProposal.getProposalNumber()) &&
                childProposal.getProposalPersons().indexOf(person) == -1 &&
                !personInMultipleProposals(person.getPersonId(), hierarchyProposal)) {
                    //remove person from parent
                    iterator.remove();
                    // remove attachments also
                    getProposalPersonBiographyService().removePersonnelAttachmentForDeletedPerson(hierarchyProposal.getProposalDocument(), person);
            }
        }
    }

    protected void removeAllChildPersonnelFromParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalPerson childPerson : childProposal.getProposalPersons()) {
            if (!personInMultipleProposals(childPerson.getPersonId(), hierarchyProposal)) {
                for (Iterator<ProposalPerson> parentIterator = hierarchyProposal.getProposalPersons().iterator(); parentIterator.hasNext(); ) {
                    ProposalPerson parentPerson = parentIterator.next();
                    if (StringUtils.equals(childPerson.getPersonId(), parentPerson.getPersonId())) {
                        //remove person from parent
                        parentIterator.remove();
                        // remove attachments also
                        getProposalPersonBiographyService().removePersonnelAttachmentForDeletedPerson(hierarchyProposal.getProposalDocument(), parentPerson);
                    }
                }
            }
        }
    }

    public void synchronizeAllChildren(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        prepareHierarchySync(hierarchyProposal);
        synchronizeAll(hierarchyProposal);
        finalizeHierarchySync(hierarchyProposal);
        // because refresh reference does not work, having to retrieve and add to proposal
        // so it displays right.
        reinstateCollections(hierarchyProposal);
    }


    protected void reinstateCollections(DevelopmentProposal proposal) {
        reinstateDegreeInfo(proposal);
    }

    @Override
    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return getProposalHierarchyDao().getDevelopmentProposal(proposalNumber);
    }

    @Override
    public ProposalState getProposalState(String proposalNumber) {
        return getProposalHierarchyDao().getProposalState(proposalNumber);
    }

    protected void synchronizeAll(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        synchronizeAllChildProposals(hierarchyProposal);
    }

    @Override
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());

        prepareHierarchySync(hierarchy);
        synchronizeChildProposal(hierarchy, childProposal, true, false);
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

        synchronizeChildProposal(hierarchyProposal, childProposal, syncPersonnelAttachments, true);
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
            newSite = deepCopy(site);
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

    protected void copyOpportunity(DevelopmentProposal srcProposal, DevelopmentProposal hierarchyProposal) {
        if (srcProposal.getS2sOpportunity() != null) {
            S2sOpportunity opportunity = deepCopy(srcProposal.getS2sOpportunity());
            opportunity.setDevelopmentProposal(hierarchyProposal);
            hierarchyProposal.setS2sOpportunity(opportunity);

            for (S2sOppForms form : opportunity.getS2sOppForms()) {
                form.getS2sOppFormsId().setProposalNumber(hierarchyProposal.getProposalNumber());
            }
        }
    }

    protected <T extends KcDataObject> T deepCopy(T oldObject) {
        return getDataObjectService().copyInstance(oldObject, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
    }

    /**
     * Synchronizes all child proposals to the parent.
     */
    protected boolean synchronizeAllChildProposals(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        boolean changed = false;

        // delete all multiple inst attachments right at the beginning
        deleteAllMultipleInternal(hierarchyProposal);
        final ProposalDevelopmentBudgetExt budget = proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal);
        proposalBudgetHierarchyService.removeMergeableChildBudgetElements(budget);

        finalizeHierarchySync(hierarchyProposal.getProposalDocument());

        for (DevelopmentProposal childProposal : getHierarchyChildren(hierarchyProposal.getProposalNumber())) {
            List<BudgetPeriod> oldBudgetPeriods = getOldBudgetPeriods(budget);
            ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
            childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
            
            removeChildElements(hierarchyProposal, childProposal.getProposalNumber());
            
            synchronizeKeywords(hierarchyProposal, childProposal);
            synchronizeSpecialReviews(hierarchyProposal, childProposal);
            synchronizePersons(hierarchyProposal, childProposal, principalInvestigator);
            synchronizeNarratives(hierarchyProposal, childProposal);
            // we deleted all internal at the beginning so just add now.
            addInternalAttachments(hierarchyProposal, childProposal);
            syncDegreeInfo(hierarchyProposal, childProposal);

            syncAllPersonnelAttachments(hierarchyProposal, childProposal, false);
            proposalBudgetHierarchyService.synchronizeChildBudget(hierarchyProposal, childProposal, oldBudgetPeriods);
            dataObjectService.save(childProposal);
            changed = true;
        }
        return changed;
    }

    protected void deleteAllMultipleInternal(DevelopmentProposal proposal) {
        List<Narrative> freshList = deleteAllMultipleTypeAttachments(proposal.getInstituteAttachments());
        proposal.setInstituteAttachments(freshList);
    }

    protected List<Narrative> deleteAllMultipleTypeAttachments(List<Narrative> attachments) {
        List<Narrative> freshList = new ArrayList<>();
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
     * If a key proposal person appears in multiple child proposals and is removed from the given child
     * proposal, then this also aggregates that key person back to the parent proposal from a different child proposal, making sure that all the key persons
     * in all of the child proposals are represented in the parent proposal.
     */
    protected boolean synchronizeChildProposal(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal,
                                                           boolean syncPersonnelAttachments, boolean isNewChild) throws ProposalHierarchyException {
        List<BudgetPeriod> oldBudgetPeriods = getOldBudgetPeriods(proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal));
        ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
        childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
        
        removeChildElements(hierarchyProposal, childProposal.getProposalNumber());

        synchronizeKeywords(hierarchyProposal, childProposal);
        synchronizeSpecialReviews(hierarchyProposal, childProposal);
        synchronizePersonsAndAggregate(hierarchyProposal, childProposal, principalInvestigator);
        syncDegreeInfo(hierarchyProposal, childProposal);

        proposalBudgetHierarchyService.synchronizeChildBudget(hierarchyProposal, childProposal, oldBudgetPeriods);

        if (syncPersonnelAttachments) {
            synchronizeNarratives(hierarchyProposal, childProposal);
            synchronizeInternalAttachments(hierarchyProposal, childProposal);
            syncAllPersonnelAttachments(hierarchyProposal, childProposal, isNewChild);
        }

        dataObjectService.save(childProposal);

        return true;
    }

    public void reinstateDegreeInfo(DevelopmentProposal proposal) {
        for (ProposalPerson person : proposal.getProposalPersons()) {
            List<ProposalPersonDegree> degrees = getProposalHierarchyDao().getDegreeInformation(proposal.getProposalNumber(), person);
            person.setProposalPersonDegrees(degrees);
        }
    }

    /**
     * Gets the old budget periods before removing them from the parent.
     */
    protected List<BudgetPeriod> getOldBudgetPeriods(Budget oldBudget) {
        List<BudgetPeriod> oldBudgetPeriods = new ArrayList<>();
        oldBudgetPeriods.addAll(oldBudget.getBudgetPeriods());
        return oldBudgetPeriods;
    }

    /**
     * Synchronizes the proposal science keywords from the child proposal to the parent proposal.
     */
    protected void synchronizeKeywords(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (PropScienceKeyword keyword : childProposal.getPropScienceKeywords()) {
            PropScienceKeyword newKeyword = new PropScienceKeyword(hierarchyProposal, getScienceKeyword(keyword.getScienceKeyword().getCode()));
            if (!doesOldKeyWordExist(hierarchyProposal.getPropScienceKeywords(),newKeyword)) {
                newKeyword.setHierarchyProposalNumber(childProposal.getProposalNumber());
                hierarchyProposal.addPropScienceKeyword(newKeyword);
            }

        }
    }

    protected boolean doesOldKeyWordExist(List<PropScienceKeyword> oldKeywords, PropScienceKeyword newKeyword) {
        for (PropScienceKeyword oldKeyWord : oldKeywords) {
           if (oldKeyWord.getScienceKeyword().getCode().equals(newKeyword.getScienceKeyword().getCode()) &&
                   oldKeyWord.getProposalNumber().equals(newKeyword.getProposalNumber())) {
               return true;
           }
        }
        return false;
    }

    protected ScienceKeyword getScienceKeyword(String code) {
        return getDataObjectService().findUnique(ScienceKeyword.class, QueryByCriteria.Builder.forAttribute(CODE, code).build());
    }

    /**
     * Synchronizes the proposal special reviews from the child proposal to the parent proposal.
     */
    protected void synchronizeSpecialReviews(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalSpecialReview review : childProposal.getPropSpecialReviews()) {
            ProposalSpecialReview newReview = deepCopy(review);
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
     */
    protected void synchronizeNarratives(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        // delete everything from parent that has this child proposal number as
        // as hierarchy proposal number and multiple attachment type.
        deleteMultipleTypeAttachments(childProposal, childProposal.getNarratives());

        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getNarratives(), hierarchyProposal.getNarratives());
    }

    protected void synchronizeInternalAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        // delete everything from parent that has this child proposal number as
        // as hierarchy proposal number and multiple attachment type.
        deleteMultipleTypeAttachments(childProposal, childProposal.getInstituteAttachments());

        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getInstituteAttachments());
    }

    protected void addInternalAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getInstituteAttachments());
    }

    protected void syncAttachmentsFromChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<Narrative> childAttachments,
                                            List<Narrative> hierarchyAttachments) {
        for (Narrative narrative : childAttachments) {
            narrative.refreshReferenceObject(NARRATIVE_TYPE);
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
        Narrative newNarrative = deepCopy(narrative);
        newNarrative.setNarrativeTypeCode(narrative.getNarrativeTypeCode());
        newNarrative.setNarrativeType(narrative.getNarrativeType());
        newNarrative.refreshReferenceObject(NARRATIVE_TYPE);
        newNarrative.setHierarchyProposalNumber(childProposal.getProposalNumber());
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);
        newNarrative.setModuleNumber(legacyNarrativeService.getNextModuleNumber(hierarchyProposal.getProposalDocument()));
        newNarrative.setDevelopmentProposal(hierarchyProposal);
        newNarrative.setNarrativeUserRights(null);
        newNarrative.setNarrativeAttachment(deepCopy(narrative.getNarrativeAttachment()));
        //need to null out the file data id so the setData method does not remove the attachment data from the attachment db
        newNarrative.getNarrativeAttachment().setFileDataId(null);
        newNarrative.getNarrativeAttachment().setData(narrative.getNarrativeAttachment().getData());
        hierarchyAttachments.add(newNarrative);
    }

    protected void deleteMultipleTypeAttachments(DevelopmentProposal childProposal, List<Narrative> attachments) {
        for (Narrative narrative : attachments) {
            narrative.refreshReferenceObject(NARRATIVE_TYPE);
            if (narrative.getNarrativeType().isAllowMultiple()) {
                deleteNarrativesFromParent(childProposal, narrative.getNarrativeType());
            }
        }
    }

     protected void deleteNarrativesFromParent(DevelopmentProposal childProposal, NarrativeType type) {
         Map<String, String> param = new HashMap<>();
         param.put(HIERARCHY_PROPOSAL_NUMBER, childProposal.getProposalNumber());
         param.put(NARRATIVE_TYPE_CODE, type.getCode());
         getDataObjectService().deleteMatching(Narrative.class, QueryByCriteria.Builder.andAttributes(param).build());

     }

     protected boolean doesParentHaveNarrativeType(DevelopmentProposal hierarchyProposal, NarrativeType narrativeType) {
        return getLegacyNarrativeService().doesProposalHaveNarrativeType(hierarchyProposal, narrativeType);
     }

    protected void syncAllPersonnelAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, boolean isNewChild) {

        Map<String, Boolean> personInMultipleProp = new HashMap<>();
        List<ProposalPersonBiography> newList = new ArrayList<>();

        /*
         Go thro list of personBios in child, if it is not in multiple proposals, then remove instance from parent so you
         can copy over again.
         */

        for (ProposalPersonBiography srcPropPersonBio : childProposal.getPropPersonBios()) {
            // if the proposal is JUST being linked to a hierarchy and if this proposal has bios for
            // people that exist on the parent, ignore those bios.
            if (!isBioInNewChildDuplicate(isNewChild, hierarchyProposal, srcPropPersonBio)
                    && !personInMultipleProposals(srcPropPersonBio.getPersonId(), hierarchyProposal)) {
                // mark those persons that are not in multiple proposals
                // and remove this persons bio from parent
                // since they will be copied over again later. We need to do this so if a bio is updated at the
                // child it will sync up.
                personInMultipleProp.put(srcPropPersonBio.getPersonId(), false);
                for (Iterator<ProposalPersonBiography> iteratorParent = hierarchyProposal.getPropPersonBios().iterator(); iteratorParent.hasNext(); ) {
                    ProposalPersonBiography parentPropPersonBio = iteratorParent.next();
                    if (StringUtils.equals(srcPropPersonBio.getPersonId(), parentPropPersonBio.getPersonId())) {
                        iteratorParent.remove();
                    }
                }
            }
        }

            // go over the child bios list and if person is not in multiple proposals add it
            for (ProposalPersonBiography srcPropPersonBio : childProposal.getPropPersonBios()) {
                // if the proposal is JUST being linked to a hierarchy and if this proposal has bios for
                // people that exist on the parent, ignore those bios.
                if (!isBioInNewChildDuplicate(isNewChild, hierarchyProposal, srcPropPersonBio)
                    && personInMultipleProp.get(srcPropPersonBio.getPersonId()) != null &&
                    !personInMultipleProp.get(srcPropPersonBio.getPersonId())) {
                        ProposalPersonBiography destPropPersonBio;
                        destPropPersonBio = deepCopy(srcPropPersonBio);
                        destPropPersonBio.setDevelopmentProposal(hierarchyProposal);
                        destPropPersonBio.setProposalNumber(hierarchyProposal.getProposalNumber());
                        destPropPersonBio.setProposalPersonNumber(getProposalPersonNumber(destPropPersonBio.getPersonId(), hierarchyProposal));
                        destPropPersonBio.setVersionNumber(0L);
                        newList.add(destPropPersonBio);
                }
            }
            hierarchyProposal.getPropPersonBios().addAll(newList);
    }

    protected boolean isBioInNewChildDuplicate(boolean isNewChild, DevelopmentProposal hierarchyProposal, ProposalPersonBiography srcPropPersonBio) {
        return isNewChild && isPersonOnParent(hierarchyProposal, srcPropPersonBio.getPersonId(), srcPropPersonBio);
    }

    protected Integer getProposalPersonNumber(String personId, DevelopmentProposal hierarchyProposal) {
        for (ProposalPerson person : hierarchyProposal.getProposalPersons()) {
            if (StringUtils.equalsIgnoreCase(person.getPersonId(), personId)) {
                return person.getProposalPersonNumber();
            }
        }
        return null;
    }

    public boolean personInMultipleProposals(String personId, DevelopmentProposal proposal) {
        if (proposal.isChild()) {
            return getProposalHierarchyDao().personInMultipleChildProposals(personId, proposal.getHierarchyParentProposalNumber());
        } else {
            return getProposalHierarchyDao().personInMultipleChildProposals(personId, proposal.getProposalNumber());
        }
    }

    protected boolean isPersonOnParent(DevelopmentProposal proposal, String id, ProposalPersonBiography srcPropPersonBio) {
        List<ProposalPerson> persons = getProposalHierarchyDao().isPersonOnProposal(proposal.getProposalNumber(), id);
        // if person is on parent, then check if the person has been added by the same proposal linked to
        // the current bio. If latter is true, the srcBio can be added, if not, bio has been added by a different proposal
        // and needs to be maintained at the parent.
        return persons.size() > 0 && !StringUtils.equals(persons.get(0).getHierarchyProposalNumber(), srcPropPersonBio.getDevelopmentProposal().getProposalNumber());
    }

 /**
  * Synchronizes the proposal persons from the child proposal to the parent proposal and then restores any proposal persons that were in the given child
  * proposal (and hence removed from the given parent proposal).
  * <p>
  * This first synchronizes the main proposal persons from the primary child proposal to the parent proposal and then runs the same algorithm on all other
  * children of the parent proposal.
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
     */
    protected void synchronizePersons(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, ProposalPerson principalInvestigator) {
        for (ProposalPerson person : childProposal.getProposalPersons()) {

            int firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            int lastIndex = hierarchyProposal.getProposalPersons().lastIndexOf(person);
            ProposalPerson firstInstance = (firstIndex == -1) ? null : hierarchyProposal.getProposalPersons().get(firstIndex);
            if (firstIndex == -1 || (firstIndex == lastIndex && !rolesAreSimilar(person, firstInstance))) {
                ProposalPerson newPerson;
                newPerson = deepCopy(person);
                newPerson.setCertifiedBy(null);
                newPerson.setCertifiedTime(null);
                newPerson.setCertifiedTimeStamp(null);
                newPerson.setCertifiedPersonName(null);
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
        removeDeletedPersonnelFromParent(hierarchyProposal, childProposal);
    }

    protected void syncDegreeInfo(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalPerson person : childProposal.getProposalPersons()) {
            int firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            syncDegreeInfo(hierarchyProposal, firstIndex, person);
        }
    }

    protected void syncDegreeInfo(DevelopmentProposal hierarchyProposal, int indexOfPersonInParent, ProposalPerson childPerson) {
        ProposalPerson personInParent = hierarchyProposal.getProposalPersons().get(indexOfPersonInParent);
        // if this person is not null and this person is not new then add degrees.
        if(personInParent != null && personInParent.getVersionNumber() != null) {
            if (!personInMultipleProposals(personInParent.getPersonId(), hierarchyProposal)) {
                getProposalHierarchyDao().deleteDegreeInfo(hierarchyProposal.getProposalNumber(), personInParent.getProposalPersonNumber(), personInParent);
                for (ProposalPersonDegree degree : childPerson.getProposalPersonDegrees()) {
                    ProposalPersonDegree newDegree = new ProposalPersonDegree();
                    newDegree.setDegree(degree.getDegree());
                    newDegree.setDegreeCode(degree.getDegreeCode());
                    newDegree.setProposalPerson(personInParent);
                    newDegree.setDegreeType(degree.getDegreeType());
                    newDegree.setFieldOfStudy(degree.getFieldOfStudy());
                    newDegree.setGraduationYear(degree.getGraduationYear());
                    newDegree.setSchool(degree.getSchool());
                    newDegree.setSpecialization(degree.getSpecialization());
                    newDegree.setDegreeSequenceNumber(getDocument(hierarchyProposal).
                                getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
                    dataObjectService.save(newDegree);
                }
            }
        }
    }

    protected ProposalDevelopmentDocument getDocument(DevelopmentProposal proposal) {
        ProposalDevelopmentDocument doc;
        try {
            doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(proposal.getProposalDocument().getDocumentNumber());
        } catch (WorkflowException e) {
            throw new RuntimeException("Cannot find the proposal", e);
        }
        return doc;
    }

    public DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getDevelopmentProposal(hierarchyProposalNumber);
        if (hierarchy == null || !hierarchy.isParent())
            throw new ProposalHierarchyException("Proposal " + hierarchyProposalNumber + " is not a hierarchy");
        return hierarchy;
    }
    
    public boolean isSynchronized(DevelopmentProposal childProposal) {
        Integer hc1 = computeHierarchyHashCode(childProposal);
        Integer hc2 = childProposal.getHierarchyLastSyncHashCode();
        return Objects.equals(hc1, hc2);
    }
    
    protected boolean isBudgetSynchronized(DevelopmentProposal childProposal, ProposalDevelopmentBudgetExt childBudget) throws ProposalHierarchyException {
        if (Objects.equals(childProposal.getLastSyncedBudget(), childBudget)) {
            ObjectUtils.materializeAllSubObjects(childBudget);
            Integer hc1 = proposalBudgetHierarchyService.computeHierarchyHashCode(childBudget);
            Integer hc2 = childBudget.getHierarchyLastSyncHashCode();
            return Objects.equals(hc1, hc2);
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
    
    public List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException {
    	return proposalBudgetHierarchyService.validateChildBudgetPeriods(hierarchyProposal, childProposal, allowEndDateChange);
    }
    
    public void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget) {
    	prepareHierarchySync(hierarchyProposal);
    	proposalBudgetHierarchyService.synchronizeChildBudget(hierarchyProposal, budget);
    	finalizeHierarchySync(hierarchyProposal);
    }
    
    public ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal proposal) {
    	return proposalBudgetHierarchyService.getSyncableBudget(proposal);
    }
    
	public boolean needToExtendProjectDate(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		if (hierarchyProposal != null && !hierarchyProposal.getBudgets().isEmpty()) {
	    	ProposalDevelopmentBudgetExt parentBudget = proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal);
	    	Budget childBudget = getSyncableBudget(childProposal);
	    	if (childBudget != null && parentBudget != null) {
                BudgetPeriod lastParentPeriod = parentBudget.getBudgetPeriods().get(parentBudget.getBudgetPeriods().size() - 1);
                BudgetPeriod lastChildPeriod = childBudget.getBudgetPeriods().get(childBudget.getBudgetPeriods().size() - 1);
                return lastChildPeriod.getStartDate().after(lastParentPeriod.getEndDate());
            }
            return false;
		} else {
			return false;
		}
    }
    
    public boolean needToExtendProjectDate(DevelopmentProposal hierarchyProposal) {
    	List<DevelopmentProposal> proposals = this.getHierarchyProposals(hierarchyProposal);
    	for (DevelopmentProposal proposal : proposals) {
    		if (needToExtendProjectDate(hierarchyProposal, proposal)) {
    			return true;
    		}
    	}
    	return false;
    }

    protected void removeChildElements(DevelopmentProposal parentProposal, String childProposalNumber) {
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
        ProposalDevelopmentBudgetExt parentBudget = proposalBudgetHierarchyService.getHierarchyBudget(parentProposal);
        if (parentBudget != null) {
        	proposalBudgetHierarchyService.removeChildBudgetElements(parentProposal, parentBudget, childProposalNumber);
        }

    }
    
    protected void prepareHierarchySync(DevelopmentProposal hierarchyProposal) {
        prepareHierarchySync(hierarchyProposal.getProposalDocument());
    }
    
    protected void prepareHierarchySync(ProposalDevelopmentDocument pdDoc) {
        pdDoc.refreshReferenceObject(DOCUMENT_NEXTVALUES);
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
        dataObjectService.save(proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal));
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
            destPropPersonBio = deepCopy(srcPropPersonBio);
            destPropPersonBio.setDevelopmentProposal(destProposal);
            destPropPersonBio.setProposalNumber(destProposal.getProposalNumber());
            destPropPersonBio.setProposalPersonNumber(destPerson.getProposalPersonNumber());
            destPropPersonBio.setPersonId(destPerson.getPersonId());
            destPropPersonBio.setRolodexId(destPerson.getRolodexId());

            if (destPropPersonBio.getPersonnelAttachment() != null) {
                destPropPersonBio.getPersonnelAttachment().setData(srcPropPersonBio.getData());
            }
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

    @Override
    public List<DevelopmentProposal> getHierarchyChildren(String parentProposalNumber) {
        List<DevelopmentProposal> children = new ArrayList<>();
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
     * @param principalId the principal we are rejecting the document as.
     * @param appDocStatus the application document status to apply ( does not apply if null )
     * @throws WorkflowException
     */
    protected void rejectProposal( ProposalDevelopmentDocument proposalDoc, String reason, String principalId, String appDocStatus ) throws WorkflowException  {
        kcDocumentRejectionService.reject(proposalDoc.getDocumentHeader().getWorkflowDocument(), reason, principalId, appDocStatus );
    }


    /**
     * Reject an entire proposal hierarchy.
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

    }
    
    
    @Override
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalName, MultipartFile rejectFile )
    throws WorkflowException, ProposalHierarchyException, IOException {
        DevelopmentProposal pbo = getDevelopmentProposal(proposalNumber);
        ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(pbo.getProposalDocument().getDocumentNumber());
        if (!pbo.isInHierarchy()) {
            rejectProposal(pDoc, renderMessage(PROPOSAL_ROUTING_REJECTED_ANNOTATION, reason), principalName, renderMessage(HIERARCHY_REJECTED_APPSTATUS));
        } else if (pbo.isParent()) {
            rejectProposalHierarchy(pDoc, reason, principalName);
        } else {
            //it is a child or in some unknown state, either way we do not support rejecting it.
            throw new UnsupportedOperationException(String.format("Cannot reject proposal %s it is a hierarchy child or ", proposalNumber));
        }

        if (rejectFile != null && rejectFile.getBytes().length > 0) {
            Narrative narrative = new Narrative();
            narrative.setName(rejectFile.getOriginalFilename());
            narrative.setComments(reason);
            try {
                narrative.init(rejectFile);
            } catch (Exception e) {
                throw new RuntimeException("Error Initializing narrative attachment file", e);
            }
            narrative.setNarrativeTypeCode(getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.REJECT_NARRATIVE_TYPE_CODE_PARAM));
            NarrativeStatus status = dataObjectService.findUnique(NarrativeStatus.class, QueryByCriteria.Builder.forAttribute(CODE, COMPLETE).build());
            narrative.setNarrativeStatus(status);
            narrative.setModuleStatusCode(status.getCode());
            narrative.setModuleTitle("Proposal rejection attachment.");
            narrative.setContactName(globalVariableService.getUserSession().getPrincipalName());
            narrative.setPhoneNumber(globalVariableService.getUserSession().getPerson().getPhoneNumber());
            narrative.setEmailAddress(globalVariableService.getUserSession().getPerson().getEmailAddress());
            getLegacyNarrativeService().prepareNarrative(pDoc, narrative);
            pDoc.getDevelopmentProposal().getInstituteAttachments().add(narrative);
            dataObjectService.save(pDoc);
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
    
    protected boolean rolesAreSimilar(ProposalPerson person1, ProposalPerson person2) {
        boolean isInvestigator1 = StringUtils.equals(person1.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person1.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        boolean isInvestigator2 = StringUtils.equals(person2.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person2.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        return isInvestigator1 == isInvestigator2;
    }

    @Override
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) throws ProposalHierarchyException {
        List<HierarchyPersonnelSummary> summaries = new ArrayList<>();
        
        List<String> proposalNumbers = new ArrayList<>();
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber));
        Collections.sort(proposalNumbers);
        
        for (String proposalNumber : proposalNumbers) {
            HierarchyPersonnelSummary summary = new HierarchyPersonnelSummary();
            
            DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
            List<Budget> hierarchyBudgets = new ArrayList<>();
            for (ProposalDevelopmentBudgetExt hierarchyBudget : proposalBudgetHierarchyService.getHierarchyBudgets(childProposal)) {
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
        List<HierarchyProposalSummary> summaries = new ArrayList<>();

        List<String> proposalNumbers = new ArrayList<>();
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
        List<DevelopmentProposal> hierachyProposals = new ArrayList<>();

        List<String> proposalNumbers = new ArrayList<>();
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

        hierachyProposals.addAll(getDataObjectService().findMatching(DevelopmentProposal.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap(PROPOSAL_NUMBER, proposalNumbers))
                .setOrderByFields(OrderByField.Builder.create(HIERARCHY_STATUS, OrderDirection.DESCENDING).build()).build()).getResults());

        return hierachyProposals;
    }

    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId) {
        boolean valid = kcAuthorizationService.hasPermission(principalId, childProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        try {
            valid &= kcAuthorizationService.hasPermission(principalId, getHierarchy(childProposal.getHierarchyParentProposalNumber()).getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        }
        catch (ProposalHierarchyException e) {
            valid = false;
        }
        return valid;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildForRemoval(DevelopmentProposal child) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        try {
            DevelopmentProposal hierarchy = lookupParent(child);
            if (hasCompleteBudget(hierarchy)) {
                errors.add( new ProposalHierarchyErrorWarningDto(ERROR_REMOVE_PARENT_BUDGET_COMPLETE, Boolean.TRUE));
            }
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
    protected ProposalPersonBiographyService getProposalPersonBiographyService() {
        return proposalPersonBiographyService;
    }

    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    protected ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }


    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildForSync (DevelopmentProposal child, DevelopmentProposal hierarchy, boolean allowEndDateChange) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if (child.getPrincipalInvestigator() == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_SYNC_NO_PRINCIPLE_INVESTIGATOR, Boolean.TRUE, child.getProposalNumber()));
        }
        errors.addAll(validateSponsor(child, hierarchy));
        errors.addAll(validateIsParentLocked(hierarchy));
        errors.addAll(validateParent(hierarchy));
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildCandidate(DevelopmentProposal proposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();

        if (proposal.isInHierarchy()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_ALREADY_MEMBER, Boolean.TRUE));
        }
        if (proposal.getBudgets().isEmpty()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NO_BUDGET_VERSION, Boolean.TRUE));
        }
        else {
            if (!hasFinalBudget(proposal)) {
                errors.add(new ProposalHierarchyErrorWarningDto(WARNING_LINK_NO_FINAL_BUDGET, Boolean.FALSE, proposal.getProposalNumber()));
            }
        }
        if (proposal.getPrincipalInvestigator() == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR, Boolean.TRUE));
        }
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child, boolean allowEndDateChange) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();

        if (!StringUtils.equalsIgnoreCase(hierarchy.getSponsorCode(), child.getSponsorCode())) {
            errors.add(new ProposalHierarchyErrorWarningDto(WARNING_LINK_DIFFERENT_SPONSOR, Boolean.FALSE));
        }

        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateParent(DevelopmentProposal proposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if (!proposal.isParent()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NOT_PARENT, Boolean.TRUE));
        }
        if (hasCompleteBudget(proposal)) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_PARENT_BUDGET_COMPLETE, Boolean.TRUE));
        }
        return errors;
    }

    public List<ProposalHierarchyErrorWarningDto> validateSponsor(DevelopmentProposal childProposal, DevelopmentProposal parentProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if(!StringUtils.equals(childProposal.getSponsorCode(), parentProposal.getSponsorCode())) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_DIFFERENT_SPONSORS, Boolean.FALSE));
        }
        return errors;
    }

    protected List<ProposalHierarchyErrorWarningDto> validateIsAggregatorOnParent(DevelopmentProposal parentProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if(!getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), parentProposal.getDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY)) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_NOT_PARENT_AGGREGATOR, Boolean.TRUE, parentProposal.getProposalNumber()));
        }
        return errors;
    }
    public List<ProposalHierarchyErrorWarningDto> validateIsAggregatorOnChild(DevelopmentProposal childProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if(!getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(),childProposal.getDocument(),PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY)) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_NOT_CHILD_AGGREGATOR, Boolean.TRUE, childProposal.getProposalNumber()));
        }
        return errors;
    }

    protected List<ProposalHierarchyErrorWarningDto> validateIsParentLocked(DevelopmentProposal parentProposal){
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
            if (!getPessimisticLockService().getPessimisticLocksForDocument(parentProposal.getDocument().getDocumentNumber()).isEmpty()) {
                errors.add(new ProposalHierarchyErrorWarningDto(ERROR_PARENT_LOCK, Boolean.TRUE, parentProposal.getProposalNumber()));
            }
        return errors;
    }

    private boolean hasFinalBudget(DevelopmentProposal proposal) {
    	return proposal.getFinalBudget() != null;
    }

    private boolean hasCompleteBudget(DevelopmentProposal developmentProposal) {
        boolean retval = false;
        String completeCode = getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_STATUS_COMPLETE_CODE);

        for (ProposalDevelopmentBudgetExt version : developmentProposal.getBudgets()) {
            if (!(version.getBudgetStatus() == null ) && version.getBudgetStatus().equalsIgnoreCase(completeCode)) {
                retval = true;
                break;
            }
        }
        return retval;
    }

	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public void setKradWorkflowDocumentService(
			WorkflowDocumentService kradWorkflowDocumentService) {
		this.kradWorkflowDocumentService = kradWorkflowDocumentService;
	}
	public void setKcDocumentRejectionService(
			KcDocumentRejectionService kcDocumentRejectionService) {
		this.kcDocumentRejectionService = kcDocumentRejectionService;
	}

    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }
	protected ProposalBudgetHierarchyService getProposalBudgetHierarchyService() {
		return proposalBudgetHierarchyService;
	}
	public void setProposalBudgetHierarchyService(
			ProposalBudgetHierarchyService proposalBudgetHierarchyService) {
		this.proposalBudgetHierarchyService = proposalBudgetHierarchyService;
	}

}
