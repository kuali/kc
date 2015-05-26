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
package org.kuali.kra.award.awardhierarchy;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.core.proxy.ProxyHelper;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepad;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

@Transactional
public class AwardHierarchyServiceImpl implements AwardHierarchyService {
    private static final Log LOG = LogFactory.getLog(AwardHierarchyServiceImpl.class);

    private static final String DOCUMENT_DESCRIPTION_FIELD_NAME = "documentDescription";
    
    AwardNumberService awardNumberService;
    LegacyDataAdapter legacyDataAdapter;
    DocumentService documentService;
    VersioningService versioningService;
    VersionHistoryService versionHistoryService; 
    AwardAmountInfoService awardAmountInfoService;
    ParameterService parameterService;
    private AwardService awardService;
    AwardVersionService awardVersionService;
    GlobalVariableService globalVariableService;

    /**
     * 
     * @param targetNode
     * @return
     */
    public AwardHierarchy copyAwardAndAllDescendantsAsNewHierarchy(AwardHierarchy targetNode) {
        String newRootAwardNumber = awardNumberService.getNextAwardNumber();
        AwardHierarchy newRootNode = createBasicHierarchy(newRootAwardNumber);
        Award newRootAward = copyAward(targetNode.getAward(), newRootAwardNumber);
        newRootNode.setAward(newRootAward);
        finalizeAward(newRootAward);
        for(AwardHierarchy childNode: targetNode.getChildren()) {
            copyNodeRecursively(childNode, newRootNode, newRootNode);
        }
        return newRootNode;
    }

    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode){
        return copyAwardAndDescendantsAsChildOfAnotherNode(sourceNode, targetParentNode);
    }

    public AwardHierarchy copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return copyAwardAndDescendantsAsChildOfAnotherNode(sourceNode, targetParentNode);
    }

    public AwardHierarchy copyAwardAsChildOfAnAwardInAnotherHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return copyAwardAsChildOfAnotherNode(sourceNode, targetParentNode);
    }

    public AwardHierarchy copyAwardAsChildOfAnAwardInCurrentHierarchy(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        return copyAwardAsChildOfAnotherNode(sourceNode, targetParentNode);
    }

    @Override
    public AwardHierarchy copyAwardAsNewHierarchy(AwardHierarchy targetNode) {
        String nextAwardNumber = awardNumberService.getNextAwardNumber();
        Award newAward = copyAward(targetNode.getAward(), nextAwardNumber);
        // Nulling out all dates and amounts from new award since it is copied as new from hierarchy and
        // should not contain any old dates.
        newAward.setAwardEffectiveDate(null);
        int indexOfLatestAwardVersion = newAward.getAwardAmountInfos().size() -1;
        newAward.getAwardAmountInfos().get(indexOfLatestAwardVersion).setFinalExpirationDate(null);
        newAward.getAwardAmountInfos().get(indexOfLatestAwardVersion).setObligationExpirationDate(null);
        newAward.getAwardAmountInfos().get(indexOfLatestAwardVersion).setCurrentFundEffectiveDate(null);
        newAward.setAwardDirectFandADistributions(new ArrayList<AwardDirectFandADistribution>());

        AwardHierarchy newNode = createBasicHierarchy(nextAwardNumber);
        newNode.setAward(newAward);
        return newNode;
    }

    @Override
    public AwardHierarchy createBasicHierarchy(String awardNumber){
        return new AwardHierarchy(awardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, awardNumber, awardNumber);
    }

    public AwardHierarchy createNewAwardBasedOnAnotherAwardInHierarchy(AwardHierarchy nodeToCopyFrom, AwardHierarchy targetParentNode) {
        return copyAwardAsChildOfAnotherNode(nodeToCopyFrom, targetParentNode);
    }
    
    public void copyAwardAmountDateInfo(Award source, Award copy) {
        List<AwardAmountInfo> awardAmountInfoList = new ArrayList<AwardAmountInfo>();
        AwardAmountInfo initialInfo = new AwardAmountInfo();
        AwardAmountInfo awardAmount = source.getLastAwardAmountInfo();
        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        awardAmountInfo.setFinalExpirationDate(awardAmount.getFinalExpirationDate());
        awardAmountInfo.setCurrentFundEffectiveDate(awardAmount.getCurrentFundEffectiveDate());
        awardAmountInfo.setObligationExpirationDate(awardAmount.getObligationExpirationDate());
        initialInfo.setFinalExpirationDate(awardAmount.getFinalExpirationDate());
        initialInfo.setCurrentFundEffectiveDate(awardAmount.getCurrentFundEffectiveDate());
        initialInfo.setObligationExpirationDate(awardAmount.getObligationExpirationDate());
        awardAmountInfo.setAward(copy);
        initialInfo.setAward(copy);
        if(awardAmount.getOriginatingAwardVersion() != null) {
           awardAmountInfo.setOriginatingAwardVersion(1); 
        }
        awardAmountInfoList.add(initialInfo);
        awardAmountInfoList.add(awardAmountInfo);
            
        
        copy.setAwardAmountInfos(awardAmountInfoList);
    }
    /**
     * @param targetNode
     * @return
     */
    public AwardHierarchy createNewAwardBasedOnParent(AwardHierarchy targetNode) {
        String nextAwardNumber = targetNode.generateNextAwardNumberInSequence();
        Award newAward = copyAward(targetNode.getAward(), nextAwardNumber);
        AwardHierarchy newNode = new AwardHierarchy(targetNode.getRoot(), targetNode, nextAwardNumber, targetNode.getAward().getAwardNumber());
        newNode.setAward(newAward);
        targetNode.getChildren().add(newNode);
        return newNode;
    }
    
    /**
     * When we create a new child award that is not a copy of the parent, we still need copy dates from parent to child.
     * @param source
     * @param copy
     */
    public void copyAwardAmountDateInfoToNewChild(Award source, Award copy) {
        AwardAmountInfo parentAai = source.getAwardAmountInfos().get(source.getAwardAmountInfos().size() - 1);

        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        awardAmountInfo.setFinalExpirationDate(parentAai.getFinalExpirationDate());
        awardAmountInfo.setCurrentFundEffectiveDate(parentAai.getCurrentFundEffectiveDate());
        awardAmountInfo.setObligationExpirationDate(parentAai.getObligationExpirationDate());
        awardAmountInfo.setAward(copy);
        
        copy.setAwardAmountInfos(new ArrayList<AwardAmountInfo>());
        copy.getAwardAmountInfos().add(awardAmountInfo);
    }

    public AwardHierarchy createNewChildAward(AwardHierarchy targetNode) {
        //copy dates when child is not a copy of parent.
        Award newAward = new Award();
        Award copyDateAward = targetNode.getAward();
        
        newAward.setAwardNumber(targetNode.generateNextAwardNumberInSequence());
        AwardHierarchy newNode = new AwardHierarchy(targetNode.getRoot(), targetNode, newAward.getAwardNumber(), newAward.getAwardNumber());
        //copyAwardAmountDateInfo(targetNode.getAward(), newAward);  
        copyAwardAmountDateInfoToNewChild(copyDateAward, newAward);
        newNode.setAward(newAward);
        targetNode.getChildren().add(newNode);
        return newNode;
    }

    @Override
    public AwardHierarchy loadAwardHierarchy(String awardNumber) {
        return awardNumber == null || awardNumber.equals(Award.DEFAULT_AWARD_NUMBER) ? null : loadAwardHierarchyBranch(awardNumber);
    }

    @Override
    public AwardHierarchy loadAwardHierarchyBranch(String awardNumber) {
        Map<String, AwardHierarchy> hierarchy = getAwardHierarchy(awardNumber, new ArrayList<String>());
        return hierarchy.get(awardNumber);
    }
    
    public Map<String, AwardHierarchy> getAwardHierarchy(AwardHierarchy anyNode, List<String> order) {
        Map<String, AwardHierarchy> result = new HashMap<String, AwardHierarchy>();
        if (anyNode == null) {
            return result;
        }
        
        Map<String, Object> values = new HashMap<String, Object>();
        //find all hierarchy BOs for the root award number. If the anyNode was got is the root, the award number
        //will be 'DEFAULT_AWARD_NUMBER' and therefore we will use the award number, otherwise, the root award number
        String rootAwardNumber = StringUtils.equals(Award.DEFAULT_AWARD_NUMBER, anyNode.getRootAwardNumber()) ? anyNode.getAwardNumber() : anyNode.getRootAwardNumber(); 
        values.put("rootAwardNumber", rootAwardNumber);
        values.put("active", true);
        List<AwardHierarchy> hierarchyList = (List<AwardHierarchy>) legacyDataAdapter.findMatchingOrderBy(AwardHierarchy.class, values, "awardNumber", true);

        if (!hierarchyList.isEmpty()) {
            for (AwardHierarchy hierarchy : hierarchyList) {
                result.put(hierarchy.getAwardNumber(), hierarchy);
                //clear children in case this was already called and cached BOs were returned from OJB.
                hierarchy.getChildren().clear();
            }
            AwardHierarchy rootNode = result.get(rootAwardNumber);
            for (AwardHierarchy hierarchy : result.values()) {
                hierarchy.setRoot(rootNode);
                AwardHierarchy parent = result.get(hierarchy.getParentAwardNumber());
                if (parent != null) {
                    parent.getChildren().add(hierarchy);
                    hierarchy.setParent(parent);
                }
            }
            for (AwardHierarchy hierarchy : result.values()) {
                Collections.sort(hierarchy.getChildren(), new Comparator<AwardHierarchy>() {
                    public int compare(AwardHierarchy arg0, AwardHierarchy arg1) {
                        return arg0.getAwardNumber().compareTo(arg1.getAwardNumber());
                    } });
            }
            Queue<AwardHierarchy> queue = new LinkedList<AwardHierarchy>();
            queue.add(rootNode);
            while (!queue.isEmpty()) {
                AwardHierarchy node = queue.poll();
                order.add(node.getAwardNumber());
                queue.addAll(node.getChildren());
            }
        }
        return result;
    }

    @Override
    public Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber, List<String> order) {
        return getAwardHierarchy(loadSingleAwardHierarchyNode(awardNumber), order);
    }

    @Override
    public AwardHierarchy loadFullHierarchyFromAnyNode(String awardNumber) {
        List<String> order = new ArrayList<String>();
        Map<String, AwardHierarchy> hierarchy = getAwardHierarchy(awardNumber, order);
        if (!order.isEmpty()) {
            return hierarchy.get(order.get(0));
        } else {
            return null;
        }
    }

    @Override
    public AwardDocument loadPlaceholderDocument() {
        DocumentHeader header = findPlaceholderDocumentHeader();
        try {
            return header != null ? (AwardDocument) documentService.getByDocumentHeaderId(header.getDocumentNumber()) : createPlaceholderDocument();
        } catch(WorkflowException e) {
            throw uncheckedException(e);
        }
    }

    @Override
    public void persistAwardHierarchy(AwardHierarchy node) {
        if(node.isNew()) {            // only save new nodes; no updates or deletes
            legacyDataAdapter.save(node);
        }
    }

    /**
     * @param rootNodes
     */
    public void persistAwardHierarchies(Collection<AwardHierarchy> rootNodes) {
        if(rootNodes == null || rootNodes.size() == 0) {
            return;
        }
        for(AwardHierarchy rootNode : rootNodes) {
            persistAwardHierarchy(rootNode, RECURS_HIERARCHY);
        }
    }

    @Override
    public void persistAwardHierarchy(AwardHierarchy branchNode, boolean recurse) {
        AwardDocument placeholderDocument = loadPlaceholderDocument();
        if(placeholderDocument != null) { // should only be null in unit test because we can't new AwardDocument
            int startingAwardCount = placeholderDocument.getAwardList().size();
            if(branchNode.hasChildren() && recurse) {
                List<AwardHierarchy> nodes = branchNode.getFlattenedListOfNodesInHierarchy();
                for(AwardHierarchy node: nodes) {
                    saveNodeWithAward(node, placeholderDocument);
                }
            } else {
                saveNodeWithAward(branchNode, placeholderDocument);
            }
            if(placeholderDocument.getAwardList().size() > startingAwardCount) {
                savePlaceholderDocument(placeholderDocument);
            }
        }
    }

    /**
     * @param awardNumberService
     */
    public void setAwardNumberService(AwardNumberService awardNumberService) {
        this.awardNumberService = awardNumberService;
    }

    /**
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * @param versioningService
     */
    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    
    /**
     * This method copies an Award to a new Award, sequence #1, with newAwardNumber
     * @param award
     * @param nextAwardNumber
     * @return
     */
    Award copyAward(Award award, String nextAwardNumber) {
        Award newAward;
        try {
            String originalAwardNumber = award.getAwardNumber();
            Integer originalSequenceNumber = award.getSequenceNumber();
            ObjectCopyUtils.prepareObjectForDeepCopy(award);
            AwardDocument document = award.getAwardDocument();
            award.setAwardDocument(null);
            newAward = useOriginalAwardAsTemplateForCopy(award, nextAwardNumber);
            restoreOriginalAwardPropertiesAfterCopy(award, originalAwardNumber, originalSequenceNumber);
            award.setAwardDocument(document);
            copyAwardAmountDateInfo(award, newAward);
            award.setBudgets(new ArrayList<>());

            List<AwardSpecialReview> awardSpecialReviews = new ArrayList<AwardSpecialReview>();
            newAward.setSpecialReviews(awardSpecialReviews);
            clearFilteredAttributes(newAward);
            getAwardService().synchNewCustomAttributes(newAward, award);
            
        } catch(Exception e) { 
            throw uncheckedException(e);
        }
        return newAward;
    }
   
    protected void clearFilteredAttributes(Award newAward) {
        // setting all financial information to null so copied award can spawn its own
        newAward.setAccountNumber(null);
        newAward.setFinancialAccountCreationDate(null);
        newAward.setFinancialAccountDocumentNumber(null);
        newAward.setFinancialChartOfAccountsCode(null);
        newAward.setNoticeDate(null);
        //simply clear the funding proposals since we haven't saved and they haven't been added to the associated proposal.
        newAward.getFundingProposals().clear();
        newAward.setAwardApprovedSubawards(new ArrayList<AwardApprovedSubaward>());
        newAward.setApprovedEquipmentItems(new ArrayList<AwardApprovedEquipment>());
        newAward.setApprovedForeignTravelTrips(new ArrayList<AwardApprovedForeignTravel>());
        newAward.setAwardNotepads(new ArrayList<AwardNotepad>());
        
        try {
            String defaultTxnTypeStr = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.DEFAULT_TXN_TYPE_COPIED_AWARD);
            if(StringUtils.isNotEmpty(defaultTxnTypeStr)) {
                newAward.setAwardTransactionTypeCode(Integer.parseInt(defaultTxnTypeStr));
            }
        }
        catch (Exception e) {
            //do Nothing
        }
        newAward.setAwardCloseoutItems(new ArrayList<AwardCloseout>());
        
        for(AwardComment comment: newAward.getAwardComments()) {
            if(StringUtils.equals(Constants.CURRENT_ACTION_COMMENT_TYPE_CODE, comment.getCommentType().getCommentTypeCode())) {
                comment.setComments(Constants.DEF_CURRENT_ACTION_COMMENT_COPIED_AWARD);
            }
        }
        
        newAward.getAwardAttachments().clear();
        
        newAward.getSyncChanges().clear();
        newAward.getSyncStatuses().clear();
        newAward.getAwardBudgetLimits().clear();
        
        /**
         * per KRACOEUS-5448 portions of the payment and invoices sub panel items should not be copied.
         */
        List<AwardReportTerm> newTerms = new ArrayList<AwardReportTerm>();
        String paymentReportClassCode = getPaymentAndInvoicesReportClass().getReportClassCode();
        for (AwardReportTerm term : newAward.getAwardReportTermItems()) {
            if (!StringUtils.equals(paymentReportClassCode, term.getReportClassCode())) {
                newTerms.add(term);
            }
        }
        newAward.setAwardReportTermItems(newTerms);
        newAward.getPaymentScheduleItems().clear();
    } 
    
    protected ReportClass getPaymentAndInvoicesReportClass() {
        Map param = new HashMap();
        param.put("DESCRIPTION", "Payment/Invoice");
        ReportClass reportClass = (ReportClass) legacyDataAdapter.findMatching(ReportClass.class, param).iterator().next();
        return reportClass;
    }

    AwardHierarchy copyAwardAsChildOfAnotherNode(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        String newAwardNumber = targetParentNode.generateNextAwardNumberInSequence();
        AwardHierarchy newLeafNode = new AwardHierarchy(targetParentNode.getRoot(), targetParentNode, newAwardNumber, sourceNode.getOriginatingAwardNumber());
        Award newLeafAward = copyAward(sourceNode.getAward(), newAwardNumber);
        newLeafNode.setAward(newLeafAward);
        targetParentNode.getChildren().add(newLeafNode);
        return newLeafNode;
    }

    protected AwardHierarchy getCopyOfSourceNode(AwardHierarchy sourceNode) {
         AwardHierarchy newSource = sourceNode.clone();
         return newSource;
    }
    
    AwardHierarchy copyAwardAndDescendantsAsChildOfAnotherNode(AwardHierarchy sourceNode, AwardHierarchy targetParentNode) {
        String newAwardNumber = targetParentNode.generateNextAwardNumberInSequence();
        AwardHierarchy newSource = getCopyOfSourceNode(sourceNode);
        List<AwardHierarchy> sourceChildren = (List<AwardHierarchy>) Collections.unmodifiableList(newSource.getChildren());  
        AwardHierarchy newBranchNode = new AwardHierarchy(targetParentNode.getRoot(), targetParentNode, newAwardNumber, sourceNode.getOriginatingAwardNumber());
        Award newBranchAward = copyAward(sourceNode.getAward(), newAwardNumber);
        finalizeAward(newBranchAward);
        targetParentNode.getChildren().add(newBranchNode);
        newBranchNode.setAward(newBranchAward);
        for(AwardHierarchy childNode: sourceChildren) {
            copyNodeRecursively(childNode, newBranchNode, targetParentNode.getRoot());
        }  
        return newBranchNode;  
    }

    protected void finalizeAward(Award newAward) {
        versionHistoryService.updateVersionHistory(newAward, VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
        awardService.updateAwardSequenceStatus(newAward, VersionStatus.ACTIVE);
    }
    
    void copyNodeRecursively(AwardHierarchy sourceNode, AwardHierarchy newParentNode, AwardHierarchy newRootNode) {
        String nextAwardNumberInHierarchy = newParentNode.generateNextAwardNumberInSequence();
        List<AwardHierarchy> sourceChildren = (List<AwardHierarchy>) Collections.unmodifiableList(sourceNode.getChildren());  
        AwardHierarchy newNode = new AwardHierarchy(newRootNode, newParentNode, nextAwardNumberInHierarchy,
                                                    sourceNode.getOriginatingAwardNumber());
        Award newAward = copyAward(sourceNode.getAward(), nextAwardNumberInHierarchy);
        newNode.setAward(newAward);
        newParentNode.getChildren().add(newNode);
        finalizeAward(newAward);
        for(AwardHierarchy childNode: sourceChildren) {
            copyNodeRecursively(childNode, newNode, newRootNode);
        }
    }

    AwardDocument createPlaceholderDocument() throws WorkflowException {
        return getGlobalVariableService().doInNewGlobalVariables(new UserSession("admin"), new Callable<AwardDocument>() {
			@Override
			public AwardDocument call() throws Exception {
				AwardDocument document = null;
		        document = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
		        document.getDocumentHeader().setDocumentDescription(AwardDocument.PLACEHOLDER_DOC_DESCRIPTION);
		        document.getAwardList().clear();
		        documentService.saveDocument(document);
		        documentService.blanketApproveDocument(document, "Placeholder being routed to final", Collections.<AdHocRouteRecipient> emptyList());
		        LOG.info("Created Placeholder Document #" + document.getDocumentNumber());
		        return document;
			}
        });
    }

    /**
     * This method finds the placeholder document header
     * @return
     */
    DocumentHeader findPlaceholderDocumentHeader() {
        @SuppressWarnings("unchecked")
        Collection c = legacyDataAdapter.findMatching(DocumentHeader.class, getDocumentDescriptionCriteriaMap());
        return !c.isEmpty() ? (DocumentHeader) c.iterator().next() : null;
    }

    AwardHierarchy loadSingleAwardHierarchyNode(String awardNumber) {
        return (AwardHierarchy) legacyDataAdapter.findByPrimaryKey(AwardHierarchy.class, getAwardHierarchyCriteriaMap(awardNumber));
    }

    Map<String, Object> getDocumentDescriptionCriteriaMap() {
        return Collections.<String, Object>singletonMap(DOCUMENT_DESCRIPTION_FIELD_NAME, AwardDocument.PLACEHOLDER_DOC_DESCRIPTION);
    }

    protected void addNewAwardToPlaceholderDocument(AwardDocument doc, AwardHierarchy node) {
        Award award = node.getAward();
        if (award.isNew()) {
            doc.getAwardList().add(award);
        }
    }

    protected Map<String, Object> getAwardHierarchyCriteriaMap(String awardNumber) {
        return CollectionUtils.zipMap(new String[]{AwardHierarchy.UNIQUE_IDENTIFIER_FIELD, "active"}, new Object[]{awardNumber, Boolean.TRUE});
    }

    protected void restoreOriginalAwardPropertiesAfterCopy(Award award, String originalAwardNumber, Integer originalSequenceNumber) {
        award.setAwardNumber(originalAwardNumber);
        award.setSequenceNumber(originalSequenceNumber);
    }

    protected void saveNodeWithAward(AwardHierarchy node, AwardDocument doc) {
        if(node.isNew()) {
            persistAwardHierarchy(node);
            addNewAwardToPlaceholderDocument(doc, node);
        }
    }

    protected void savePlaceholderDocument(AwardDocument doc) {
        try {
            documentService.saveDocument(doc);
        } catch (WorkflowException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected RuntimeException uncheckedException(Exception e) {
        return new RuntimeException(e.getMessage(), e);
    }

    protected Award useOriginalAwardAsTemplateForCopy(Award award, String nextAwardNumber) throws VersionException {
        award.setAwardNumber(nextAwardNumber);
        award.setSequenceNumber(0);
        return versioningService.createNewVersion(award);
    }

    public void populateAwardHierarchyNodes(Map<String, AwardHierarchy> awardHierarchyItems, Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber) {
        AwardHierarchyNode awardHierarchyNode;
        String tmpAwardNumber = null;
        
        for(Entry<String, AwardHierarchy> awardHierarchy:awardHierarchyItems.entrySet()){
            tmpAwardNumber = awardHierarchy.getValue().getAwardNumber();
            if (awardHierarchyNodes.get(tmpAwardNumber) != null) {
                continue;
            }
            awardHierarchyNode = createAwardHierarchyNode(awardHierarchy.getValue(), currentAwardNumber, currentSequenceNumber);
            awardHierarchyNodes.put(awardHierarchyNode.getAwardNumber(), awardHierarchyNode);
        }  
    }
    
    public AwardHierarchyNode createAwardHierarchyNode(AwardHierarchy awardHierarchy, String currentAwardNumber, String currentSequenceNumber) {
        String awardNumber = awardHierarchy.getAwardNumber();
        AwardHierarchyNode awardHierarchyNode = new AwardHierarchyNode();
        awardHierarchyNode.setAwardNumber(awardNumber);
        awardHierarchyNode.setParentAwardNumber(awardHierarchy.getParentAwardNumber());
        awardHierarchyNode.setRootAwardNumber(awardHierarchy.getRootAwardNumber());

        VersionHistory pendingVersionHistory = versionHistoryService.findPendingVersion(Award.class, awardNumber);
        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersion(Award.class, awardNumber);


        Award award = awardVersionService.getWorkingAwardVersion(awardNumber);
        //KRACOEUS-5543: If an award is copied to another hierarchy, it does not retain it's own Award Document.  In this case there will not be a version history, so
        //we need to grab the last version from the database.  
       if(award == null) {
           award = getAwardFromDatabase(awardNumber);
       }

        AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());            
        
        awardHierarchyNode.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        awardHierarchyNode.setLeadUnitName(award.getUnitName());
        awardHierarchyNode.setPrincipalInvestigatorName(award.getPrincipalInvestigatorName());
        awardHierarchyNode.setAccountNumber(award.getAccountNumber());
        awardHierarchyNode.setAwardStatusCode(award.getStatusCode());
        awardHierarchyNode.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
        awardHierarchyNode.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
        awardHierarchyNode.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect());
        awardHierarchyNode.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect());
        awardHierarchyNode.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
        awardHierarchyNode.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect());
        awardHierarchyNode.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect());
        awardHierarchyNode.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
        awardHierarchyNode.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        awardHierarchyNode.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        awardHierarchyNode.setProjectStartDate(award.getAwardEffectiveDate());
        awardHierarchyNode.setTitle(award.getTitle());
        awardHierarchyNode.setAwardId(award.getAwardId());
        awardHierarchyNode.setAwardDocumentNumber(award.getAwardDocument().getDocumentNumber());
        awardHierarchyNode.setHasChildren(!awardHierarchy.getChildren().isEmpty());
        
        //if there is not a pending version and there is an active version then the award document is final.
        awardHierarchyNode.setAwardDocumentFinalStatus(pendingVersionHistory == null && activeVersionHistory != null);
        return awardHierarchyNode;

    }
    
    private Award getAwardFromDatabase(String awardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", awardNumber);
        List<Award> awardList = ((List<Award>)legacyDataAdapter.findMatchingOrderBy(Award.class, map, "sequenceNumber", true));
        Award returnVal = awardList.get(awardList.size()-1);
        return returnVal;
    }

    public void populateAwardHierarchyNodesForTandMDoc(Map<String, AwardHierarchy> awardHierarchyItems, Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber, TimeAndMoneyDocument timeAndMoneyDocument) {
        AwardHierarchyNode awardHierarchyNode;
        String tmpAwardNumber = null;
        
        for(Entry<String, AwardHierarchy> awardHierarchy:awardHierarchyItems.entrySet()){
            tmpAwardNumber = awardHierarchy.getValue().getAwardNumber();
            awardHierarchyNode = new AwardHierarchyNode();
            awardHierarchyNode.setAwardNumber(tmpAwardNumber);
            awardHierarchyNode.setParentAwardNumber(awardHierarchy.getValue().getParentAwardNumber());
            awardHierarchyNode.setRootAwardNumber(awardHierarchy.getValue().getRootAwardNumber());
            
            VersionHistory pendingVersionHistory = versionHistoryService.findPendingVersion(Award.class, tmpAwardNumber);
            VersionHistory activeVersionHistory = versionHistoryService.findActiveVersion(Award.class, tmpAwardNumber);
            Award award = awardVersionService.getWorkingAwardVersion(tmpAwardNumber);

            AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchLastAwardAmountInfoForDocNum(award, timeAndMoneyDocument.getDocumentNumber());            
            
            awardHierarchyNode.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
            awardHierarchyNode.setLeadUnitName(award.getUnitName());
            awardHierarchyNode.setPrincipalInvestigatorName(award.getPrincipalInvestigatorName());
            awardHierarchyNode.setAccountNumber(award.getAccountNumber());
            awardHierarchyNode.setAwardStatusCode(award.getStatusCode());
            awardHierarchyNode.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
            awardHierarchyNode.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
            // we need to add in the pending transactions that are a result of changing totals in single-node view
            ScaleTwoDecimal obligatedTotal = awardAmountInfo.getAmountObligatedToDate();
            ScaleTwoDecimal obligatedTotalDirect = awardAmountInfo.getObligatedTotalDirect();
            ScaleTwoDecimal obligatedTotalIndirect = awardAmountInfo.getObligatedTotalIndirect();
            ScaleTwoDecimal anticipatedTotalAmount = awardAmountInfo.getAnticipatedTotalAmount();
            ScaleTwoDecimal anticipatedTotalDirect = awardAmountInfo.getAnticipatedTotalDirect();
            ScaleTwoDecimal anticipatedTotalIndirect = awardAmountInfo.getAnticipatedTotalIndirect();
            for (PendingTransaction pendingTransaction: timeAndMoneyDocument.getPendingTransactions()){
                if (pendingTransaction.getProcessedFlag() == false && pendingTransaction.isSingleNodeTransaction()) {
                    obligatedTotal = obligatedTotal.add(pendingTransaction.getObligatedAmount());
                    obligatedTotalDirect = obligatedTotalDirect.add(pendingTransaction.getObligatedDirectAmount());
                    obligatedTotalIndirect = obligatedTotalIndirect.add(pendingTransaction.getObligatedIndirectAmount());
                    anticipatedTotalAmount = anticipatedTotalAmount.add(pendingTransaction.getAnticipatedAmount());
                    anticipatedTotalDirect = anticipatedTotalDirect.add(pendingTransaction.getAnticipatedDirectAmount());
                    anticipatedTotalIndirect = anticipatedTotalIndirect.add(pendingTransaction.getAnticipatedIndirectAmount());
                }
            }
            awardHierarchyNode.setAmountObligatedToDate(obligatedTotal);
            awardHierarchyNode.setObligatedTotalDirect(obligatedTotalDirect);
            awardHierarchyNode.setObligatedTotalIndirect(obligatedTotalIndirect);
            awardHierarchyNode.setAnticipatedTotalAmount(anticipatedTotalAmount);
            awardHierarchyNode.setAnticipatedTotalDirect(anticipatedTotalDirect);
            awardHierarchyNode.setAnticipatedTotalIndirect(anticipatedTotalIndirect);
            awardHierarchyNode.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
            awardHierarchyNode.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
            awardHierarchyNode.setProjectStartDate(award.getAwardEffectiveDate());
            awardHierarchyNode.setTitle(award.getTitle());
            awardHierarchyNode.setAwardId(award.getAwardId());

            //if there is not a pending version and there is an active version then the award document is final.
            awardHierarchyNode.setAwardDocumentFinalStatus(pendingVersionHistory == null && activeVersionHistory != null);
            awardHierarchyNodes.put(awardHierarchyNode.getAwardNumber(), awardHierarchyNode);
        }  
    }
    
    public AwardVersionService getAwardVersionService() {
        awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
        return awardVersionService;
    }
    
    public void createNodeMapsOnFormForSummaryPanel(Map<String, AwardHierarchyNode> awardHierarchyNodes, Map<String, String> previousNodeMap, Map<String, String> nextNodeMap) {
        List <AwardHierarchy> sortedList = new ArrayList<AwardHierarchy>();
        AwardHierarchy rootNode = loadFullHierarchyFromAnyNode(getRootNode(awardHierarchyNodes).getAwardNumber());
        //AwardHierarchy rootNode = (AwardHierarchy) getRootNode(awardHierarchyNodes);
        sortedList.add(rootNode);
        //create sorted list with a depth first search through hierarchy tree adding node to sorted list as we visit each node.
        //add all first level children and tear through children recursively creating top down sorted list.
        for(AwardHierarchy ah : rootNode.getChildren()) {
            sortedList.add(ah);
            addChildrenToSortedList(ah, sortedList);
        }
        nextNodeMap.clear();
        previousNodeMap.clear();
        previousNodeMap.put(rootNode.getAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
        addSubNodesToPreviousNodeMap(sortedList, previousNodeMap);
        addNodesToNextNodeMap(sortedList, nextNodeMap);
    }
    
    private void addChildrenToSortedList(AwardHierarchy ah, List <AwardHierarchy> sortedList) {
        if(ah.hasChildren()) {
            for(AwardHierarchy awardHierarchy : ah.getChildren()) {
                sortedList.add(awardHierarchy);
                addChildrenToSortedList(awardHierarchy, sortedList);
            }
        }
    }
    
    private void addSubNodesToPreviousNodeMap(List <AwardHierarchy> sortedList, Map<String, String> previousNodeMap) {
        String previousNodeNumber = null;
        String nextNodeNumber = null;
        int index = 0;
        while(index < sortedList.size() - 1) {
            previousNodeNumber = sortedList.get(index).getAwardNumber();
            nextNodeNumber = sortedList.get(index + 1).getAwardNumber();
            previousNodeMap.put(nextNodeNumber, previousNodeNumber);
            index++;
        }
    }
    
    private void addNodesToNextNodeMap(List <AwardHierarchy> sortedList, Map<String, String> nextNodeMap) {
        String previousNodeNumber = null;
        String nextNodeNumber = null;
        int index = 0;
        while(index < sortedList.size() - 1) {
            previousNodeNumber = sortedList.get(index).getAwardNumber();
            nextNodeNumber = sortedList.get(index + 1).getAwardNumber();
            nextNodeMap.put(previousNodeNumber, nextNodeNumber);
            index++;
        }
        nextNodeMap.put(sortedList.get(index).getAwardNumber(), Constants.LAST_NODE_NEXT_VALUE);
        
    }
    
    
    private AwardHierarchyNode getRootNode(Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        AwardHierarchyNode returnValue = null;
        for(String awardNumber : awardHierarchyNodes.keySet()) {
            if(awardNumber.endsWith("-00001")) {
                returnValue = awardHierarchyNodes.get(awardNumber);
            }
        }
        return returnValue;
    }
    
    /**
     * Gets the awardAmountInfoService attribute. 
     * @return Returns the awardAmountInfoService.
     */
    public AwardAmountInfoService getAwardAmountInfoService() {
        return awardAmountInfoService;
    }

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    public AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public void setAwardVersionService(AwardVersionService awardVersionService) {
        this.awardVersionService = awardVersionService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

    public static class ObjectCopyUtils {

        public static final int MAX_DEPTH_FOR_PROXY_MATERILIZATION = 3;

        public static void prepareObjectForDeepCopy(PersistableBusinessObject bo) {
            try {
                materializeAllProxies(bo);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        private static void materializeAllProxies(PersistableBusinessObject bo) throws Exception {
            ObjectUtils.materializeSubObjectsToDepth(bo, MAX_DEPTH_FOR_PROXY_MATERILIZATION);
            ObjectCopyUtils.materializeUpdateableCollections(bo);
        }

        public static void materializeUpdateableCollections(Object bo) throws FormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            ObjectCopyUtils.materializeUpdateableCollections(bo, MAX_DEPTH_FOR_PROXY_MATERILIZATION);
        }

        public static void materializeUpdateableCollections(Object bo, int depth) throws FormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            if (depth == 0 || ObjectUtils.isNull(bo)) {
                return;
            }

            if (depth < 0 || depth > MAX_DEPTH_FOR_PROXY_MATERILIZATION) {
                throw new IllegalArgumentException("The depth passed in was out of bounds.  Only values between 0 and 3, inclusively, are allowed.");
            }

            PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bo.getClass());
            for (int i = 0; i < propertyDescriptors.length; i++) {
                if (KNSServiceLocator.getPersistenceStructureService().hasCollection(bo.getClass(), propertyDescriptors[i].getName()) && KNSServiceLocator.getPersistenceStructureService().isCollectionUpdatable(bo.getClass(), propertyDescriptors[i].getName())) {
                    Collection<PersistableBusinessObject> updateableCollection = (Collection<PersistableBusinessObject>) ObjectUtils.getPropertyValue(bo, propertyDescriptors[i].getName());
                    if ((updateableCollection != null) && ProxyHelper.isCollectionProxy(updateableCollection)) {
                        ObjectUtils.materializeObjects(updateableCollection);
                        for(PersistableBusinessObject elementBo : updateableCollection) {
                            materializeUpdateableCollections(elementBo, depth-1);
                        }
                    }
                }
            }
        }
    }

	GlobalVariableService getGlobalVariableService() {
		if (globalVariableService == null) {
			globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
		}
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
}
