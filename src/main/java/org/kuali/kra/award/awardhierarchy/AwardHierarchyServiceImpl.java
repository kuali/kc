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
package org.kuali.kra.award.awardhierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepad;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.ObjectCopyUtils;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AwardHierarchyServiceImpl implements AwardHierarchyService {
    private static final Log LOG = LogFactory.getLog(AwardHierarchyServiceImpl.class);

    private static final String DOCUMENT_DESCRIPTION_FIELD_NAME = "documentDescription";
    
    AwardNumberService awardNumberService;
    BusinessObjectService businessObjectService;
    DocumentService documentService;
    VersioningService versioningService;
    VersionHistoryService versionHistoryService; 
    AwardAmountInfoService awardAmountInfoService;
    ActivePendingTransactionsService activePendingTransactionsService;
    ParameterService parameterService;
    private AwardService awardService;

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

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#copyAwardAsNewHierarchy(AwardHierarchy)
     */
    public AwardHierarchy copyAwardAsNewHierarchy(AwardHierarchy targetNode) {
        String nextAwardNumber = awardNumberService.getNextAwardNumber();
        Award newAward = copyAward(targetNode.getAward(), nextAwardNumber);
        AwardHierarchy newNode = createBasicHierarchy(nextAwardNumber);
        newNode.setAward(newAward);
        return newNode;
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#createBasicHierarchy(java.lang.String)
     */
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

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#loadAwardHierarchy(java.lang.String)
     */
    public AwardHierarchy loadAwardHierarchy(String awardNumber) {
        return awardNumber == null || awardNumber.equals(Award.DEFAULT_AWARD_NUMBER) ? null : loadAwardHierarchyBranch(awardNumber);
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#loadAwardHierarchy(java.lang.String)
     */
    public AwardHierarchy loadAwardHierarchyBranch(String awardNumber) {
        return loadAwardHierarchyBranch(loadSingleAwardHierarchyNode(awardNumber));
    }

     /**/
    public Map<String, AwardHierarchy> getAwardHierarchy(AwardHierarchy rootNode, List<String> order) {
        Map<String, Collection<AwardHierarchy>> mapOfChildren = new HashMap<String, Collection<AwardHierarchy>>();
        Map<String, AwardHierarchy> awardHierarchies = createAwardHierarchyAndPrepareCollectionForSort(rootNode, mapOfChildren);

        String parentAwardNumber = rootNode.getAwardNumber();
        order.add(parentAwardNumber);
        if(awardHierarchies.size() > 1){
            createSortOrder(order, awardHierarchies, mapOfChildren, parentAwardNumber, null, null);
        }

        return awardHierarchies;
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#getAwardHierarchy(java.lang.String, java.util.List)
     */
    public Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber, List<String> order) {
        return getAwardHierarchy(getAwardHierarchyRootNode(awardNumber), order);
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#loadFullHierarchyFromAnyNode(java.lang.String)
     */
    public AwardHierarchy loadFullHierarchyFromAnyNode(String awardNumber) {
        AwardHierarchy rootNode = null;
        if(!Award.DEFAULT_AWARD_NUMBER.equals(awardNumber)) {
            AwardHierarchy someNode = loadSingleAwardHierarchyNode(awardNumber);
            if(someNode != null) {
                rootNode = someNode.isRootNode() ? someNode : loadSingleAwardHierarchyNode(someNode.getRootAwardNumber());
                rootNode.setRoot(rootNode);
                rootNode.setParent(null);
                rootNode = loadAwardHierarchyBranch(rootNode);
            }
        }
        return rootNode;
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#loadPlaceholderDocument()
     */
    public AwardDocument loadPlaceholderDocument() {
        DocumentHeader header = findPlaceholderDocumentHeader();
        try {
            return header != null ? (AwardDocument) documentService.getByDocumentHeaderId(header.getDocumentNumber()) : createPlaceholderDocument();
        } catch(WorkflowException e) {
            throw uncheckedException(e);
        }
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#persistAwardHierarchy(org.kuali.kra.award.awardhierarchy.AwardHierarchy)
     */
    public void persistAwardHierarchy(AwardHierarchy node) {
        if(node.isNew()) {            // only save new nodes; no updates or deletes
            businessObjectService.save(node);
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

    /**
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#persistAwardHierarchy(org.kuali.kra.award.awardhierarchy.AwardHierarchy, boolean)
     */
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
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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

    protected AwardHierarchy getRootNode(String rootAwardNumber){
        return loadAwardHierarchy(rootAwardNumber);
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

            List<AwardSpecialReview> awardSpecialReviews = new ArrayList<AwardSpecialReview>();
            newAward.setSpecialReviews(awardSpecialReviews);
            clearFilteredAttributes(newAward);
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
        int sourceFundingProposalsCount = newAward.getFundingProposals().size();
        for(int i=0; i < sourceFundingProposalsCount; i++) {
            newAward.removeFundingProposal(i);
        }
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
        targetParentNode.getChildren().add(newBranchNode);
        newBranchNode.setAward(newBranchAward);
        for(AwardHierarchy childNode: sourceChildren) {
            copyNodeRecursively(childNode, newBranchNode, targetParentNode.getRoot());
        }  
        return newBranchNode;  
    }

    protected void finalizeAward(Award newAward) {
        versionHistoryService.createVersionHistory(newAward, VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
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
        AwardDocument document;
        document = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        if(document != null) {  // will be null in unit test, but not otherwise. Rice should allow us to create a new Document() (or new form) in a unit test
            document.getDocumentHeader().setDocumentDescription(AwardDocument.PLACEHOLDER_DOC_DESCRIPTION);
            document.getAwardList().clear();
            documentService.saveDocument(document);
            LOG.info("Created Placeholder Document #" + document.getDocumentNumber());
        }
        return document;
    }

    /**
     * This method finds the placeholder document header
     * @return
     */
    DocumentHeader findPlaceholderDocumentHeader() {
        @SuppressWarnings("unchecked")
        Collection c = businessObjectService.findMatching(DocumentHeader.class, getDocumentDescriptionCriteriaMap());
        return c.size() == 1 ? (DocumentHeader) c.iterator().next() : null;
    }

    AwardHierarchy loadAwardHierarchyBranch(AwardHierarchy branchNode) {
        if(branchNode != null) {
            recurseTree(branchNode);
        }
        return branchNode;
    }

    AwardHierarchy loadSingleAwardHierarchyNode(String awardNumber) {
        return (AwardHierarchy) businessObjectService.findByPrimaryKey(AwardHierarchy.class, getAwardHierarchyCriteriaMap(awardNumber));
    }

    Map<String, Object> getDocumentDescriptionCriteriaMap() {
        return ServiceHelper.getInstance().buildCriteriaMap(DOCUMENT_DESCRIPTION_FIELD_NAME, AwardDocument.PLACEHOLDER_DOC_DESCRIPTION);
    }

    /**
     * This method recurses the AwardHierarchy tree
     * @param branchNode
     */
    @SuppressWarnings("unchecked")
    void recurseTree(AwardHierarchy branchNode) {
        Map<String, Object> criteria = ServiceHelper.getInstance().buildCriteriaMap(new String[]{"parentAwardNumber", "active"}, new Object[]{branchNode.getAwardNumber(), Boolean.TRUE});
        Collection c = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, criteria, AwardHierarchy.UNIQUE_IDENTIFIER_FIELD, true);
        branchNode.setChildren(new ArrayList<AwardHierarchy>(c));
        if(branchNode.hasChildren()) {
            for(AwardHierarchy childNode: branchNode.getChildren()) {
                childNode.setParent(branchNode);
                childNode.setRoot(branchNode.getRoot());
                recurseTree(childNode);
            }
        }
    }

    protected void addNewAwardToPlaceholderDocument(AwardDocument doc, AwardHierarchy node) {
        Award award = node.getAward();
        if (award.isNew()) {
            doc.getAwardList().add(award);
        }
    }

    /*
     * This method constructs the entire award hierarchy based on the root node.
     *
     * This method also creates a map of children - where the key is parent award number and value is a list of all of its children.
     * This map will be used to sort award hierarchy nodes in correct parent-child order.
     *
     * Both awardHierarchy and mapOfChildren are being updated in same for loop so its not possible to have two separate methods for them.
     */
    @SuppressWarnings("unchecked")
    protected Map<String, AwardHierarchy> createAwardHierarchyAndPrepareCollectionForSort(AwardHierarchy awardHierarchyRootNode,
                                                                                        Map<String, Collection<AwardHierarchy>> mapOfChildren) {
        Map<String, AwardHierarchy> hierarchyMap = new HashMap<String, AwardHierarchy>();
        createAwardHierarchyMap(hierarchyMap, awardHierarchyRootNode, mapOfChildren);
        return hierarchyMap;
    }

    // recursively walk hierarchy tree, populating maps
    protected void createAwardHierarchyMap(Map<String, AwardHierarchy> hierarchyMap, AwardHierarchy node, Map<String, Collection<AwardHierarchy>> mapOfChildren) {
        if(node != null) {
            hierarchyMap.put(node.getAwardNumber(), node);
            // there is a pernicious side-effect in createSortOrder that causes child collection to be cleared, so store a copy of the children collection
            mapOfChildren.put(node.getAwardNumber(), new ArrayList<AwardHierarchy>(node.getChildren()));
            for(AwardHierarchy childNode: node.getChildren()) {
                createAwardHierarchyMap(hierarchyMap, childNode, mapOfChildren);
            }
        }
    }

    protected AwardHierarchy getAwardHierarchyRootNode(String someNodeAwardNumberInHierarchy) {
        Collection c = businessObjectService.findMatching(AwardHierarchy.class, getAwardHierarchyCriteriaMap(someNodeAwardNumberInHierarchy));
        AwardHierarchy someNodeInHierarchy = null;
        if(c.size() == 1) {
            someNodeInHierarchy = (AwardHierarchy) c.iterator().next();
        }
        if(someNodeInHierarchy == null) {
            throw new MissingHierarchyException(someNodeAwardNumberInHierarchy);
        }
        return getRootNode(someNodeInHierarchy.getRootAwardNumber());
    }

    /*
     * This method updates the @listForAwardHierarchySort as per the correct parent-child order. This list will be used to display the award hierarchy nodes
     * in correct sort order.
     * The order is going to be root followed by all its children followed by all of their children until there are no children.
     */
    protected void createSortOrder(List<String> listForAwardHierarchySort, Map<String, AwardHierarchy> awardHierarchies,
                                 Map<String, Collection<AwardHierarchy>> mapOfChildren, String parentAwardNumber,
                                 Collection<AwardHierarchy> ahCollection, AwardHierarchy ah1) {

        while(!StringUtils.equalsIgnoreCase(parentAwardNumber,Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            if(mapOfChildren.get(parentAwardNumber).size()!=0){
                ahCollection = mapOfChildren.get(parentAwardNumber);
                ah1 = ahCollection.iterator().next();
                parentAwardNumber = ah1.getAwardNumber();
                listForAwardHierarchySort.add(parentAwardNumber);
            }else if(ahCollection!=null && ahCollection.size() ==0){
                ah1 = awardHierarchies.get(awardHierarchies.get(parentAwardNumber).getAwardNumber());
                if(ah1!=null){
                    parentAwardNumber = ah1.getParentAwardNumber();
                    if(!StringUtils.equalsIgnoreCase(parentAwardNumber,Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                        mapOfChildren.get(parentAwardNumber).remove(ah1);
                    }
                }else{
                    parentAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
                }
            }
            else if(ah1!=null){
                parentAwardNumber = ah1.getParentAwardNumber();
                ahCollection.remove(ah1);
            }
        }

    }

    protected Map<String, Object> getAwardHierarchyCriteriaMap(String awardNumber) {
        return ServiceHelper.getInstance().buildCriteriaMap(new String[]{AwardHierarchy.UNIQUE_IDENTIFIER_FIELD, "active"}, new Object[]{awardNumber, Boolean.TRUE});
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
    
    /**
     * 
     * @throws WorkflowException 
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#populateAwardHierarchyNodes(java.util.Map, java.util.Map)
     */
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
        Award award = null;
        if (pendingVersionHistory != null) {
            award = (Award) pendingVersionHistory.getSequenceOwner();
        } else if (activeVersionHistory != null) {
            award = (Award) activeVersionHistory.getSequenceOwner();
        }
        //KRACOEUS-4879 - The above is the same as the commented out section below as long as we prefer the pending version in 
        //the getWorkingAwardVersion call.
        //Award award = activePendingTransactionsService.getWorkingAwardVersion(awardNumber); 
        //if a current award was passed in, make sure to use the current pending version if it exists
        //so any changes are reflected in the hierarchy.
        /*if(StringUtils.isNotEmpty(currentAwardNumber) && StringUtils.isNotEmpty(currentSequenceNumber) && StringUtils.equals(awardNumber, currentAwardNumber)) {
            pendingVersionHistory = versionHistoryService.findPendingVersion(Award.class, currentAwardNumber, currentSequenceNumber);
            if (pendingVersionHistory != null) {
                award = (Award) pendingVersionHistory.getSequenceOwner();
            }
        }*/

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
        
        /*
        String documentNumber = award.getAwardDocument().getDocumentNumber();
        boolean awardDocumentFinalStatus = false;
        try {
            Document awardDocument = documentService.getByDocumentHeaderId(documentNumber);
            awardDocumentFinalStatus = (awardDocument != null) ? awardDocument.getDocumentHeader().getWorkflowDocument().isFinal() : false;
        } catch (WorkflowException e) {
            throw uncheckedException(e);
        }
        
        awardHierarchyNode.setAwardDocumentNumber(documentNumber);
        if (awardDocumentFinalStatus && pendingVersionHistory != null && award != null && !pendingVersionHistory.getSequenceOwnerSequenceNumber().equals(award.getSequenceNumber())) {
            awardDocumentFinalStatus = false;
        }
        awardHierarchyNode.setAwardDocumentFinalStatus(awardDocumentFinalStatus);*/
        //if there is not a pending version and there is an active version then the award document is final.
        awardHierarchyNode.setAwardDocumentFinalStatus(pendingVersionHistory == null && activeVersionHistory != null);
        return awardHierarchyNode;

    }
    
    /**
     * 
     * @throws WorkflowException 
     * @see org.kuali.kra.award.awardhierarchy.AwardHierarchyService#populateAwardHierarchyNodes(java.util.Map, java.util.Map)
     */
    public void populateAwardHierarchyNodesForTandMDoc(Map<String, AwardHierarchy> awardHierarchyItems, Map<String, AwardHierarchyNode> awardHierarchyNodes, String currentAwardNumber, String currentSequenceNumber, String docNum) {
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
            Award award = null;
            if (pendingVersionHistory != null) {
                award = (Award) pendingVersionHistory.getSequenceOwner();
            } else if (activeVersionHistory != null) {
                award = (Award) activeVersionHistory.getSequenceOwner();
            }
            //KRACOEUS-4879 - The above is the same as the commented out section below as long as we prefer the pending version in 
            
            /*//Award award = null;
            VersionHistory pendingVersionHistory = null;
            //For the current award, we should always show only the pending version if it exists
//            if(StringUtils.isNotEmpty(currentAwardNumber) && StringUtils.isNotEmpty(currentSequenceNumber) && StringUtils.equals(tmpAwardNumber, currentAwardNumber)) {
                pendingVersionHistory = versionHistoryService.findPendingVersion(Award.class, currentAwardNumber, currentSequenceNumber);
//                if(pendingVersionHistory != null) {
//                    award = (Award) pendingVersionHistory.getSequenceOwner();
//                }
//            }
            Award award = activePendingTransactionsService.getWorkingAwardVersion(tmpAwardNumber);
//            if(award == null) {
//                award = activeAward;  
//            }  */

            AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchLastAwardAmountInfoForDocNum(award, docNum);            
            
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
            //awardHierarchyNode.setCurrentFundEffectiveDate(award.getAwardEffectiveDate());
            awardHierarchyNode.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
            awardHierarchyNode.setProjectStartDate(award.getAwardEffectiveDate());
            awardHierarchyNode.setTitle(award.getTitle());
            awardHierarchyNode.setAwardId(award.getAwardId());
            
            /*String documentNumber = award.getAwardDocument().getDocumentNumber();
            boolean awardDocumentFinalStatus = false;
            try {
                Document awardDocument = documentService.getByDocumentHeaderId(documentNumber);
                awardDocumentFinalStatus = (awardDocument != null) ? awardDocument.getDocumentHeader().getWorkflowDocument().isFinal() : false;
            } catch(WorkflowException e) {
                throw uncheckedException(e);
            }
            
            awardHierarchyNode.setAwardDocumentNumber(documentNumber);
            if(!awardDocumentFinalStatus && pendingVersionHistory != null && award != null && !pendingVersionHistory.getSequenceOwnerSequenceNumber().equals(award.getSequenceNumber())) {
                awardDocumentFinalStatus = true;   
            }
            awardHierarchyNode.setAwardDocumentFinalStatus(new Boolean(awardDocumentFinalStatus));*/
            //if there is not a pending version and there is an active version then the award document is final.
            awardHierarchyNode.setAwardDocumentFinalStatus(pendingVersionHistory == null && activeVersionHistory != null);
            awardHierarchyNodes.put(awardHierarchyNode.getAwardNumber(), awardHierarchyNode);
        }  
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

    /**
     * Gets the activePendingTransactionsService attribute. 
     * @return Returns the activePendingTransactionsService.
     */
    public ActivePendingTransactionsService getActivePendingTransactionsService() {
        return activePendingTransactionsService;
    }
 
    /**
     * Sets the activePendingTransactionsService attribute value.
     * @param activePendingTransactionsService The activePendingTransactionsService to set.
     */
    public void setActivePendingTransactionsService(ActivePendingTransactionsService activePendingTransactionsService) {
        this.activePendingTransactionsService = activePendingTransactionsService;
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

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
}
