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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.*;
import java.util.Map.Entry;

public class ActivePendingTransactionsServiceImpl implements ActivePendingTransactionsService {
    
    BusinessObjectService businessObjectService;
    AwardAmountInfoService awardAmountInfoService;
    AwardVersionService awardVersionService;
    ParameterService parameterService;

    @SuppressWarnings("unchecked")
    private PersonService personService;
    
    
    @FunctionalInterface
    protected interface GenerateAwardAmountInfoFunction {
    	public AwardAmountInfo apply(PendingTransaction one, AwardAmountInfo two, Map<String, AwardAmountTransaction> three, AwardAmountTransaction four, String five, Award six);
    }
    protected GenerateAwardAmountInfoFunction sourceDownNodeAmountInfoFunc = (a, b, c, d, e, f) -> { return getUpdatedSourceDownNodeAmountInfo(a, b, c, d, e, f); };
    protected GenerateAwardAmountInfoFunction sourceUpNodeAmountInfoFunc = (a, b, c, d, e, f) -> { return getUpdatedSourceUpNodeAmountInfo(a, b, c, d, e, f); };
    protected GenerateAwardAmountInfoFunction updatedDestinationUpNodeAmountInfoFunc = (a, b, c, d, e, f) -> { return getUpdatedDestinationUpNodeAmountInfo(a, b, c, d, e, f); };
    protected GenerateAwardAmountInfoFunction updatedDestinationDownNodeAmountInfoFunc = (a, b, c, d, e, f) -> { return getUpdatedDestinationDownNodeAmountInfo(a, b, c, d, e, f); };
    protected GenerateAwardAmountInfoFunction updatedIntermediateDownNodeAmountInfoFunc = (a, b, c, d, e, f) -> { return getUpdatedIntermediateDownNodeAmountInfo(a, b, c, d, e, f); };
    protected GenerateAwardAmountInfoFunction updatedIntermediateUpNodeAmountInfoFunc = (a, b, c, d, e, f) -> { return getUpdatedIntermediateUpNodeAmountInfo(a, b, c, d, e, f); };

    @Override
    public void approveTransactions(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction) {
        
        Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
        List<Award> awardItems = new ArrayList<Award>();
        List<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
        replaceSessionWithRoutedBy(doc);//replace usersession so update user is logged in user rather than kr.
        List<AwardAmountTransaction> awardAmountTransactions = processTransactions(doc, newAwardAmountTransaction,
                awardAmountTransactionItems, awardItems, transactionDetailItems, false);
        performSave(doc, transactionDetailItems, awardItems, awardAmountTransactions);
    }

    public List<AwardAmountTransaction> processTransactions(TimeAndMoneyDocument doc,AwardAmountTransaction newAwardAmountTransaction
            , Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems, Boolean refreshFlag) {
        
        List<PendingTransaction> updatedPendingTransactions = new ArrayList<PendingTransaction>();
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        updatedPendingTransactions.addAll(doc.getPendingTransactions());
        
        Map<String, AwardHierarchyNode> awardHierarchyNodes = doc.getAwardHierarchyNodes();
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            if (pendingTransaction.getProcessedFlag() == false) {
                AwardHierarchyNode sourceAwardNode = awardHierarchyNodes.get(pendingTransaction.getSourceAwardNumber());
                AwardHierarchyNode destinationAwardNode = awardHierarchyNodes.get(pendingTransaction.getDestinationAwardNumber());            
                AwardHierarchyNode parentNode = new AwardHierarchyNode();
                //if we are just doing a refresh view in T&M, there is no need to change the processed flag.
                if(!refreshFlag){
                    pendingTransaction.setProcessedFlag(true);
                }
                //
                if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                    //working
                    processPendingTransactionWhenSourceIsExternal(doc, newAwardAmountTransaction, updatedPendingTransactions, transactionDetailItems
                            , awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes, destinationAwardNode);   
                    
                    //
                }else if(StringUtils.equalsIgnoreCase(pendingTransaction.getDestinationAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                    processPendingTransactionWhenDestinationIsExternal(doc, newAwardAmountTransaction, updatedPendingTransactions, transactionDetailItems
                            , awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes, sourceAwardNode); 
                    
                    //tests for parent child relationship when pushing money down to children
                }else if(parentChildRelationshipExists(sourceAwardNode.getAwardNumber(), destinationAwardNode.getAwardNumber(), awardHierarchyNodes, parentNode)){                
                    processPendingTransactionWhenParentChildRelationShipExists(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode
                            , transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes,
                            sourceAwardNode, destinationAwardNode); 
                    
                    //tests for child parent relationship when pushing money up to a parent award.
                }else if(childParentRelationshipExists(destinationAwardNode.getAwardNumber(), sourceAwardNode.getAwardNumber(), awardHierarchyNodes, parentNode)){                
                    processPendingTransactionWhenChildParentRelationShipExists(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode
                            , transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes,
                            sourceAwardNode, destinationAwardNode); 
                
                }else{processPendingTransactionWithIndirectRelationship(doc, newAwardAmountTransaction, updatedPendingTransactions, transactionDetailItems, awardAmountTransactionItems
                            , awardItems, pendingTransaction, sourceAwardNode, destinationAwardNode);
                }
                
                updatedPendingTransactions.remove(pendingTransaction);
                pendingTransactionsToBeDeleted.add(pendingTransaction);
            }
        }
        
        
        for(Entry<String,AwardHierarchyNode> awardHierarchyNode : doc.getAwardHierarchyNodes().entrySet()){
            Award award = awardVersionService.getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
            AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
            awardHierarchyNode.getValue().setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
            awardHierarchyNode.getValue().setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
            awardHierarchyNode.getValue().setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
            awardHierarchyNode.getValue().setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
            awardHierarchyNode.getValue().setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect());
            awardHierarchyNode.getValue().setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect());
            awardHierarchyNode.getValue().setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
            awardHierarchyNode.getValue().setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect());
            awardHierarchyNode.getValue().setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect());
            awardHierarchyNode.getValue().setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
            awardHierarchyNode.getValue().setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());        
        }
        
        List<AwardAmountTransaction> awardAmountTransactions = prepareAwardAmountTransactionsListForPersistence(awardAmountTransactionItems);
        return awardAmountTransactions;
    }

    public List<Award> processTransactionsForAddRuleProcessing(TimeAndMoneyDocument doc,AwardAmountTransaction newAwardAmountTransaction
            , Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems) {
        
        List<PendingTransaction> updatedPendingTransactions = new ArrayList<PendingTransaction>();
        List<PendingTransaction> pendingTransactionsToBeDeleted = new ArrayList<PendingTransaction>();
        updatedPendingTransactions.addAll(doc.getPendingTransactions());
        
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            if(pendingTransaction.getProcessedFlag() == false) {
                Map<String, AwardHierarchyNode> awardHierarchyNodes = doc.getAwardHierarchyNodes();
                AwardHierarchyNode sourceAwardNode = awardHierarchyNodes.get(pendingTransaction.getSourceAwardNumber());
                AwardHierarchyNode destinationAwardNode = awardHierarchyNodes.get(pendingTransaction.getDestinationAwardNumber());            
                AwardHierarchyNode parentNode = new AwardHierarchyNode();
                //
                if(StringUtils.equalsIgnoreCase(pendingTransaction.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                    processPendingTransactionWhenSourceIsExternal(doc, newAwardAmountTransaction, updatedPendingTransactions, transactionDetailItems
                            , awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes, destinationAwardNode);   
                    
                    //
                }else if(StringUtils.equalsIgnoreCase(pendingTransaction.getDestinationAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                    processPendingTransactionWhenDestinationIsExternal(doc, newAwardAmountTransaction, updatedPendingTransactions, transactionDetailItems
                            , awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes, sourceAwardNode); 
                    
                    //tests for parent child relationship when pushing money down to children
                }else if(parentChildRelationshipExists(sourceAwardNode.getAwardNumber(), destinationAwardNode.getAwardNumber(), awardHierarchyNodes, parentNode)){                
                    processPendingTransactionWhenParentChildRelationShipExists(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode
                            , transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes,
                            sourceAwardNode, destinationAwardNode); 
                    
                    //tests for child parent relationship when pushing money up to a parent award.
                }else if(childParentRelationshipExists(destinationAwardNode.getAwardNumber(), sourceAwardNode.getAwardNumber(), awardHierarchyNodes, parentNode)){                
                    processPendingTransactionWhenChildParentRelationShipExists(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode
                            , transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes,
                            sourceAwardNode, destinationAwardNode); 
                
                }else{processPendingTransactionWithIndirectRelationship(doc, newAwardAmountTransaction, updatedPendingTransactions, transactionDetailItems, awardAmountTransactionItems
                            , awardItems, pendingTransaction, sourceAwardNode, destinationAwardNode);
                }
                
                updatedPendingTransactions.remove(pendingTransaction);
                pendingTransactionsToBeDeleted.add(pendingTransaction);
            }
        }
        
        return awardItems;
    }

    /*
     * This method prepares a list of AwardAmountTransaction objects for persistence from the map of same objects.
     */
    protected List<AwardAmountTransaction> prepareAwardAmountTransactionsListForPersistence(
            Map<String, AwardAmountTransaction> awardAmountTransactionItems) {
        List<AwardAmountTransaction> awardAmountTransactions = new ArrayList<AwardAmountTransaction>();
        for(Entry<String, AwardAmountTransaction> awardAmountTransaction:awardAmountTransactionItems.entrySet()){
            awardAmountTransactions.add(awardAmountTransaction.getValue());
        }
        return awardAmountTransactions;
    }

    /*
     * This is a helper method for save.
     */
    protected void performSave(TimeAndMoneyDocument doc, List<TransactionDetail> transactionDetailItems, List<Award> awardItems,
            List<AwardAmountTransaction> awardAmountTransactions) {
        businessObjectService.save(transactionDetailItems);
        businessObjectService.save(awardAmountTransactions);
        businessObjectService.save(awardItems);
        businessObjectService.save(doc);
    }

    /*
     * This method processes the pending transaction where there is not a direct path up or down the tree.  The logic will find the common parent
     * and move the money from the source up to this common parent, and then back down to the destination.
     */
    protected void processPendingTransactionWithIndirectRelationship(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction
            , List<PendingTransaction> updatedPendingTransactions, List<TransactionDetail> transactionDetailItems
            , Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, PendingTransaction pendingTransaction
            , AwardHierarchyNode sourceAwardNode, AwardHierarchyNode destinationAwardNode) {
        
        AwardHierarchyNode parentNode = new AwardHierarchyNode();
        //find the common parent of the two nodes in the transaction
        String commonParent = this.findCommonParent(doc.getAwardHierarchyNodes(), sourceAwardNode.getRootAwardNumber(), 
                sourceAwardNode.getAwardNumber(), destinationAwardNode.getAwardNumber());
        //process the transaction by moving the money up the tree from source to common parent.
        processPendingTransactionWhenChildParentRelationShipExistsIndirect(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode
                , transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, doc.getAwardHierarchyNodes(),
                sourceAwardNode, doc.getAwardHierarchyNodes().get(commonParent)); 
        //move money from common parent to destination node.
        processPendingTransactionWhenParentChildRelationShipExistsIndirect(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode
                , transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, doc.getAwardHierarchyNodes(),
                doc.getAwardHierarchyNodes().get(commonParent), destinationAwardNode);
        addTransactionDetails(sourceAwardNode.getAwardNumber(),destinationAwardNode.getAwardNumber()
                ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
    }

    /*
     * This method processes a pending transaction for the case when source is external.
     */
    protected void processPendingTransactionWhenSourceIsExternal(TimeAndMoneyDocument doc,
            AwardAmountTransaction newAwardAmountTransaction, List<PendingTransaction> updatedPendingTransactions,
            List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, Map<String, AwardHierarchyNode> awardHierarchyNodes,
            AwardHierarchyNode destinationAwardNode) {
            //this logic when coming from external source to root Award.
        if(StringUtils.equalsIgnoreCase(pendingTransaction.getDestinationAwardNumber(), destinationAwardNode.getRootAwardNumber())){
            handleTransaction(pendingTransaction, destinationAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
                    , updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedDestinationDownNodeAmountInfoFunc);
            
            addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT,destinationAwardNode.getAwardNumber()
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
            addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT,destinationAwardNode.getAwardNumber()
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
            
        }else{//this logic when moving money from external source to an award other than root award.
            
            createIntermediateTransactionsWhenParentIsExternal(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                    , awardHierarchyNodes.get(destinationAwardNode.getAwardNumber()).getParentAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, true
                    , transactionDetailItems, updatedPendingTransactions, newAwardAmountTransaction);
            
            
            handleTransaction(pendingTransaction, destinationAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
                    , updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedDestinationDownNodeAmountInfoFunc);   
            addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, destinationAwardNode.getAwardNumber()
                    , doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
            
        }
    }
    
   
    
    /*
     * This method processes a pending transaction for the case when source is external.
     */
    protected void processPendingTransactionWhenDestinationIsExternal(TimeAndMoneyDocument doc,
            AwardAmountTransaction newAwardAmountTransaction, List<PendingTransaction> updatedPendingTransactions,
            List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, Map<String, AwardHierarchyNode> awardHierarchyNodes,
            AwardHierarchyNode sourceAwardNode) {
            //this logic when coming from root award to external source.
        if(StringUtils.equalsIgnoreCase(sourceAwardNode.getParentAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
        	handleTransaction(pendingTransaction, sourceAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
                    , updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), sourceUpNodeAmountInfoFunc);
            
            addTransactionDetails(pendingTransaction.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
            addTransactionDetails(pendingTransaction.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
        }else{//this logic when moving money from any award other than root award to external source.
        	handleTransaction(pendingTransaction, sourceAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
                    , updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), sourceUpNodeAmountInfoFunc);
            addTransactionDetails(pendingTransaction.getSourceAwardNumber(),Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
            createIntermediateTransactionsWhenDestinationIsExternal(doc, awardAmountTransactionItems, awardItems, pendingTransaction, sourceAwardNode.getAwardNumber()
                    , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getParentAwardNumber(), Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, true
                    , transactionDetailItems, updatedPendingTransactions, newAwardAmountTransaction);
                               
        }
    }

    /*
     * This method processes a Pending Transaction for the case when either of the source or destination is parent of the other.
     */
    protected void processPendingTransactionWhenParentChildRelationShipExists(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction,
            List<PendingTransaction> updatedPendingTransactions, AwardHierarchyNode parentNode,
            List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, Map<String, AwardHierarchyNode> awardHierarchyNodes,
            AwardHierarchyNode sourceAwardNode, AwardHierarchyNode destinationAwardNode) {
        
    	handleTransaction(pendingTransaction, sourceAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
    			, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), sourceDownNodeAmountInfoFunc);
        
        if(!(destinationAwardNode.getParentAwardNumber().equals(sourceAwardNode.getAwardNumber()))) {
            createIntermediateDownTransactionsWhenParentChildRelationshipExists(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                , awardHierarchyNodes.get(destinationAwardNode.getAwardNumber()).getParentAwardNumber()
                , sourceAwardNode.getAwardNumber(), transactionDetailItems
                , updatedPendingTransactions, newAwardAmountTransaction);
        }
        
        handleTransaction(pendingTransaction, destinationAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedDestinationDownNodeAmountInfoFunc);
        addTransactionDetails(pendingTransaction.getSourceAwardNumber(),destinationAwardNode.getAwardNumber()
                ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
        //if the destination is direct child of parent, we need to report as intermediate transaction.
        if(destinationAwardNode.getParentAwardNumber().equals(sourceAwardNode.getAwardNumber())) {
            addTransactionDetails(pendingTransaction.getSourceAwardNumber(), destinationAwardNode.getAwardNumber()
                    , doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
            }
    }
    
    /*
     * This method processes a Pending Transaction for the case when either of the source or destination is parent of the other.
     */
    protected void processPendingTransactionWhenParentChildRelationShipExistsIndirect(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction,
            List<PendingTransaction> updatedPendingTransactions, AwardHierarchyNode parentNode,
            List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, Map<String, AwardHierarchyNode> awardHierarchyNodes,
            AwardHierarchyNode sourceAwardNode, AwardHierarchyNode destinationAwardNode) {
       
        if(!(destinationAwardNode.getAwardNumber().equals(sourceAwardNode.getAwardNumber()))) {
            createIntermediateDownTransactionsWhenParentChildRelationshipExists(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                    , awardHierarchyNodes.get(destinationAwardNode.getAwardNumber()).getParentAwardNumber()
                    , sourceAwardNode.getAwardNumber(), transactionDetailItems
                    , updatedPendingTransactions, newAwardAmountTransaction);
            
        }
        
        handleTransaction(pendingTransaction, destinationAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedDestinationDownNodeAmountInfoFunc);
    }
    
    /*
     * This method processes a Pending Transaction for the case when either of the source or destination is parent of the other.
     */
    protected void processPendingTransactionWhenChildParentRelationShipExists(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction,
            List<PendingTransaction> updatedPendingTransactions, AwardHierarchyNode parentNode,
            List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, Map<String, AwardHierarchyNode> awardHierarchyNodes,
            AwardHierarchyNode sourceAwardNode, AwardHierarchyNode destinationAwardNode) {
        
    	handleTransaction(pendingTransaction, sourceAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
                , updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), sourceUpNodeAmountInfoFunc);
        
        if(!(sourceAwardNode.getParentAwardNumber().equals(destinationAwardNode.getAwardNumber()))) {
            createIntermediateUpTransactionsWhenParentChildRelationshipExists(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                    , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getParentAwardNumber()
                    , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getAwardNumber(), transactionDetailItems
                    , updatedPendingTransactions, newAwardAmountTransaction);
        }
        
        handleTransaction(pendingTransaction, destinationAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedDestinationUpNodeAmountInfoFunc);
        addTransactionDetails(pendingTransaction.getSourceAwardNumber(), destinationAwardNode.getAwardNumber()
                , doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.PRIMARY);
        if(sourceAwardNode.getParentAwardNumber().equals(destinationAwardNode.getAwardNumber())) {
            addTransactionDetails(pendingTransaction.getSourceAwardNumber(), destinationAwardNode.getAwardNumber()
                    , doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
        }
    }
    
    /*
     * This method processes a Pending Transaction for the case when either of the source or destination is parent of the other.
     */
    protected void processPendingTransactionWhenChildParentRelationShipExistsIndirect(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction,
            List<PendingTransaction> updatedPendingTransactions, AwardHierarchyNode parentNode,
            List<TransactionDetail> transactionDetailItems, Map<String, AwardAmountTransaction> awardAmountTransactionItems,
            List<Award> awardItems, PendingTransaction pendingTransaction, Map<String, AwardHierarchyNode> awardHierarchyNodes,
            AwardHierarchyNode sourceAwardNode, AwardHierarchyNode destinationAwardNode) {
        
    	handleTransaction(pendingTransaction, sourceAwardNode.getAwardNumber(), awardAmountTransactionItems, awardItems
                , updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), sourceUpNodeAmountInfoFunc);
        
        if(!(sourceAwardNode.getParentAwardNumber().equals(destinationAwardNode.getAwardNumber()))) {
            createIntermediateUpTransactionsWhenParentChildRelationshipExists(doc, awardAmountTransactionItems, awardItems, pendingTransaction, destinationAwardNode.getAwardNumber()
                , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getParentAwardNumber()
                , awardHierarchyNodes.get(sourceAwardNode.getAwardNumber()).getAwardNumber(), transactionDetailItems
                , updatedPendingTransactions, newAwardAmountTransaction);
                }
            }

    /*
     * 
     * This method creates the intermediate records of awardAmountInfo and transaction details for case when a parent child relationship exists between 
     * source and destination.
     * 
     * @param doc
     * @param awardAmountTransactionItems
     * @param awardItems
     * @param pendingTransaction
     * @param awardNumber
     * @param parentAwardNumber
     * @param parentOfParentAwardNumber
     * @param direction
     * @param transactionDetailItems
     * @param updatedPendingTransactions
     * @param newAwardAmountTransaction
     */
    protected void createIntermediateDownTransactionsWhenParentChildRelationshipExists(TimeAndMoneyDocument doc, Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems
            , PendingTransaction pendingTransaction, String destinationAwardNumber, String parentAwardNumber, String sourceAwardNumber
            , List<TransactionDetail> transactionDetailItems, List<PendingTransaction> updatedPendingTransactions
            , AwardAmountTransaction newAwardAmountTransaction) {
        List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();

        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, sourceAwardNumber)){                   
            handleTransaction(pendingTransaction, parentAwardNumber, awardAmountTransactionItems, 
                    awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedIntermediateDownNodeAmountInfoFunc);    
            transactionDetailList.add(createTransactionDetail(parentAwardNumber,destinationAwardNumber
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), TransactionDetailType.INTERMEDIATE));
            parentAwardNumber = doc.getAwardHierarchyNodes().get(parentAwardNumber).getParentAwardNumber();       
            destinationAwardNumber = doc.getAwardHierarchyNodes().get(destinationAwardNumber).getParentAwardNumber();                     
        }
        //report last transaction
        addTransactionDetails(parentAwardNumber,destinationAwardNumber
                ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
        Collections.reverse(transactionDetailList);
        transactionDetailItems.addAll(transactionDetailList);
        
    }
    
    /*
     * 
     * This method creates the intermediate records of awardAmountInfo and transaction details for case when a parent child relationship exists between 
     * source and destination.
     */
    protected void createIntermediateUpTransactionsWhenParentChildRelationshipExists(TimeAndMoneyDocument doc, Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems
            , PendingTransaction pendingTransaction, String destinationAwardNumber, String parentOfSourceAwardNumber, String sourceAwardNumber
            , List<TransactionDetail> transactionDetailItems, List<PendingTransaction> updatedPendingTransactions
            , AwardAmountTransaction newAwardAmountTransaction) {
                
        while(!StringUtils.equalsIgnoreCase(destinationAwardNumber, parentOfSourceAwardNumber)){   
            
            handleTransaction(pendingTransaction, parentOfSourceAwardNumber, awardAmountTransactionItems, 
                    awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedIntermediateUpNodeAmountInfoFunc); 
            addTransactionDetails(sourceAwardNumber,parentOfSourceAwardNumber
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
            sourceAwardNumber = doc.getAwardHierarchyNodes().get(sourceAwardNumber).getParentAwardNumber();
            parentOfSourceAwardNumber = doc.getAwardHierarchyNodes().get(parentOfSourceAwardNumber).getParentAwardNumber(); 
        }
        //report last transaction
        addTransactionDetails(sourceAwardNumber,parentOfSourceAwardNumber
                ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
    }
    
    /*
     * 
     * This method creates the intermediate records of awardAmountInfo and transaction detail for the case when source is External.
     */
    protected void createIntermediateTransactionsWhenParentIsExternal(TimeAndMoneyDocument doc, Map<String, AwardAmountTransaction> awardAmountTransactionItems
            , List<Award> awardItems, PendingTransaction pendingTransaction, String destinationAwardNumber, String parentAwardNumber, String defaultExternalAwardNumber
            , boolean direction, List<TransactionDetail> transactionDetailItems, List<PendingTransaction> updatedPendingTransactions
            , AwardAmountTransaction newAwardAmountTransaction) {
        List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){    
            
            handleTransaction(pendingTransaction, parentAwardNumber, awardAmountTransactionItems, awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedIntermediateDownNodeAmountInfoFunc);
            transactionDetailList.add(createTransactionDetail(parentAwardNumber, destinationAwardNumber
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), TransactionDetailType.INTERMEDIATE));
            parentAwardNumber = doc.getAwardHierarchyNodes().get(parentAwardNumber).getParentAwardNumber();
            destinationAwardNumber = doc.getAwardHierarchyNodes().get(destinationAwardNumber).getParentAwardNumber();   
        }
        addTransactionDetails(parentAwardNumber, destinationAwardNumber
                ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
        Collections.reverse(transactionDetailList);
        transactionDetailItems.addAll(transactionDetailList);
        
    }
    
    
    /*
     * 
     * This method creates the intermediate records of awardAmountInfo and transaction detail for the case when source is External.
     * @param doc
     * @param awardAmountTransactionItems
     * @param awardItems
     * @param pendingTransaction
     * @param awardNumber
     * @param parentAwardNumber
     * @param parentOfParentAwardNumber
     * @param direction
     * @param transactionDetailItems
     * @param updatedPendingTransactions
     * @param newAwardAmountTransaction
     */
    protected void createIntermediateTransactionsWhenDestinationIsExternal(TimeAndMoneyDocument doc, Map<String, AwardAmountTransaction> awardAmountTransactionItems
            , List<Award> awardItems, PendingTransaction pendingTransaction, String sourceAwardNumber, String parentOfSourceAwardNumber, String destinationAwardNumber
            , boolean direction, List<TransactionDetail> transactionDetailItems, List<PendingTransaction> updatedPendingTransactions
            , AwardAmountTransaction newAwardAmountTransaction) {               
        while(!StringUtils.equalsIgnoreCase(parentOfSourceAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){    
            handleTransaction(pendingTransaction, parentOfSourceAwardNumber, awardAmountTransactionItems, awardItems, updatedPendingTransactions, newAwardAmountTransaction, doc.getDocumentNumber(), updatedIntermediateUpNodeAmountInfoFunc);
            addTransactionDetails(sourceAwardNumber,parentOfSourceAwardNumber
                    ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
            parentOfSourceAwardNumber = doc.getAwardHierarchyNodes().get(parentOfSourceAwardNumber).getParentAwardNumber();
            sourceAwardNumber = doc.getAwardHierarchyNodes().get(sourceAwardNumber).getParentAwardNumber();
        }
        addTransactionDetails(sourceAwardNumber,parentOfSourceAwardNumber
                ,doc.getAward().getSequenceNumber(), pendingTransaction, doc.getAwardNumber(), doc.getDocumentNumber(), transactionDetailItems, TransactionDetailType.INTERMEDIATE);
    }
    
    /*
     * If there is a parent and child relationship between @awardNumber1 and @awardNumber2. This relationship could be direct or indirect
     * meaning @awardNumber1 could be direct parent or indirect parent of @awardNumber2. Indirect Parent child relationship here means there
     * could be any number of levels between the two. 
     */
    protected boolean parentChildRelationshipExists(String awardNumber1, String awardNumber2, Map<String, AwardHierarchyNode> awardHierarchyNodes, AwardHierarchyNode parentNode) {
        boolean parentChild = Boolean.FALSE;
        String parentAwardNumber = awardHierarchyNodes.get(awardNumber2).getParentAwardNumber();        
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            if(StringUtils.equalsIgnoreCase(parentAwardNumber, awardNumber1)){
                parentChild = Boolean.TRUE;
                break;                
            }
            parentAwardNumber = awardHierarchyNodes.get(parentAwardNumber).getParentAwardNumber();            
        }
        
        if(parentChild){
            parentNode.setAwardNumber(awardHierarchyNodes.get(parentAwardNumber).getAwardNumber());             
        }
        
        return parentChild;
    }
    
    /*
     * If there is a child and parent relationship between @awardNumber1 and @awardNumber2. This relationship could be direct or indirect
     * meaning @awardNumber1 could be direct parent or indirect parent of @awardNumber2. Indirect Parent child relationship here means there
     * could be any number of levels between the two. 
     */
    protected boolean childParentRelationshipExists(String awardNumber1, String awardNumber2, Map<String, AwardHierarchyNode> awardHierarchyNodes, AwardHierarchyNode parentNode) {
        boolean parentChild = Boolean.FALSE;
        String parentAwardNumber = awardHierarchyNodes.get(awardNumber2).getParentAwardNumber();        
        while(!StringUtils.equalsIgnoreCase(parentAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
            if(StringUtils.equalsIgnoreCase(parentAwardNumber, awardNumber1)){
                parentChild = Boolean.TRUE;
                break;                
            }
            parentAwardNumber = awardHierarchyNodes.get(parentAwardNumber).getParentAwardNumber();            
        }
       
        if(parentChild){
            parentNode.setAwardNumber(awardHierarchyNodes.get(parentAwardNumber).getAwardNumber());             
        }
        
        return parentChild;
    }
    
    
    /*
     * This method for creating a new transaction detail.
     */
    protected TransactionDetail createTransactionDetail(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, PendingTransaction pendingTransaction, String currentAwardNumber, String documentNumber, 
             TransactionDetailType transactionDetailtype){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
        transactionDetail.setAnticipatedDirectAmount(pendingTransaction.getAnticipatedDirectAmount());
        transactionDetail.setAnticipatedIndirectAmount(pendingTransaction.getAnticipatedIndirectAmount());
        transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
        transactionDetail.setObligatedDirectAmount(pendingTransaction.getObligatedDirectAmount());
        transactionDetail.setObligatedIndirectAmount(pendingTransaction.getObligatedIndirectAmount());
        transactionDetail.setAwardNumber(currentAwardNumber);
        transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(pendingTransaction.getComments());
        transactionDetail.setTransactionDetailType(transactionDetailtype.toString());
        
        return transactionDetail;
    }
    
    
    /*
     * 
     * This method creates a transactionDetail object and adds it to the list for persistence later.
     */
    protected void addTransactionDetails(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, PendingTransaction pendingTransaction, String currentAwardNumber, String documentNumber, 
            List<TransactionDetail> transactionDetailItems, TransactionDetailType transactionDetailtype){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        transactionDetail.setAnticipatedAmount(pendingTransaction.getAnticipatedAmount());
        transactionDetail.setAnticipatedDirectAmount(pendingTransaction.getAnticipatedDirectAmount());
        transactionDetail.setAnticipatedIndirectAmount(pendingTransaction.getAnticipatedIndirectAmount());
        transactionDetail.setObligatedAmount(pendingTransaction.getObligatedAmount());
        transactionDetail.setObligatedDirectAmount(pendingTransaction.getObligatedDirectAmount());
        transactionDetail.setObligatedIndirectAmount(pendingTransaction.getObligatedIndirectAmount());
        transactionDetail.setAwardNumber(currentAwardNumber);
        transactionDetail.setTransactionId(pendingTransaction.getTransactionId());
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(pendingTransaction.getComments());
        transactionDetail.setTransactionDetailType(transactionDetailtype.toString());
        transactionDetailItems.add(transactionDetail);
    }
    
    protected String findCommonParent(Map<String, AwardHierarchyNode> awardHierarchyNodes, String rootAwardNumber, String sourceAwardNumber, String destinationAwardNumber){
        List<String> listOfParentsOfSource = new ArrayList<String>();
        List<String> listOfParentsOfDestination = new ArrayList<String>();
        String commonParent = null;
        
        buildListOfParents(awardHierarchyNodes, listOfParentsOfSource, sourceAwardNumber, rootAwardNumber);
        buildListOfParents(awardHierarchyNodes, listOfParentsOfDestination, destinationAwardNumber, rootAwardNumber);
        
        for(String an1 : listOfParentsOfSource){
            for(String an2 : listOfParentsOfDestination){
                if(StringUtils.equalsIgnoreCase(an1, an2)){
                    commonParent = an1;
                    break;                    
                }
            }
            if(commonParent!=null){
                break;    
            }
        }
        return commonParent;
        
    }

    protected void buildListOfParents(Map<String, AwardHierarchyNode> awardHierarchyNodes, List<String> listOfParentsOfSource,
             String currentAwardNumber, String rootAwardNumber) {
        String parentAwardNumber;
        while(!StringUtils.equalsIgnoreCase(currentAwardNumber,rootAwardNumber)){
            parentAwardNumber = awardHierarchyNodes.get(currentAwardNumber).getParentAwardNumber();
            listOfParentsOfSource.add(parentAwardNumber);
            currentAwardNumber = parentAwardNumber;
        }        
    }

    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    protected AwardAmountInfo getUpdatedDestinationDownNodeAmountInfo(PendingTransaction pendingTransaction, 
            AwardAmountInfo awardAmountInfo, Map<String, AwardAmountTransaction> awardAmountTransactionItems, 
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award) {
        
        AwardAmountInfo newAwardAmountInfo = getNewAwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        newAwardAmountInfo.setObligatedChange(pendingTransaction.getObligatedAmount());
        newAwardAmountInfo.setAnticipatedChange(pendingTransaction.getAnticipatedAmount());
       //add transaction amounts to the AmountInfo
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate().add(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect().add(pendingTransaction.getObligatedDirectAmount()));
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect().add(pendingTransaction.getObligatedIndirectAmount()));
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount().add(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount().add(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect().add(pendingTransaction.getAnticipatedDirectAmount()));
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect().add(pendingTransaction.getAnticipatedIndirectAmount()));
        newAwardAmountInfo.setObligatedChangeDirect(pendingTransaction.getObligatedDirectAmount());
        newAwardAmountInfo.setObligatedChangeIndirect(pendingTransaction.getObligatedIndirectAmount());
        newAwardAmountInfo.setAnticipatedChangeDirect(pendingTransaction.getAnticipatedDirectAmount());
        newAwardAmountInfo.setAnticipatedChangeIndirect(pendingTransaction.getAnticipatedIndirectAmount());

        //updateAmountFields(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, newAwardAmountInfo);
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }

	AwardAmountInfo getNewAwardAmountInfo() {
		return new AwardAmountInfo();
	}
    
    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    protected AwardAmountInfo getUpdatedDestinationUpNodeAmountInfo(PendingTransaction pendingTransaction, 
            AwardAmountInfo awardAmountInfo, Map<String, AwardAmountTransaction> awardAmountTransactionItems, 
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award) {
        
        AwardAmountInfo newAwardAmountInfo = getNewAwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        newAwardAmountInfo.setObligatedChange(new ScaleTwoDecimal(0));
        newAwardAmountInfo.setAnticipatedChange(new ScaleTwoDecimal(0));
       //add transaction amounts to the AmountInfo
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect());
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect());
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount().add(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect());
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect());
        newAwardAmountInfo.setObligatedChangeDirect(pendingTransaction.getObligatedDirectAmount());
        newAwardAmountInfo.setObligatedChangeIndirect(pendingTransaction.getObligatedIndirectAmount());
        newAwardAmountInfo.setAnticipatedChangeDirect(pendingTransaction.getAnticipatedDirectAmount());
        newAwardAmountInfo.setAnticipatedChangeIndirect(pendingTransaction.getAnticipatedIndirectAmount());
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }
    
    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    protected AwardAmountInfo getUpdatedSourceDownNodeAmountInfo(PendingTransaction pendingTransaction, 
            AwardAmountInfo awardAmountInfo, Map<String, AwardAmountTransaction> awardAmountTransactionItems, 
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award) {
        
        AwardAmountInfo newAwardAmountInfo = getNewAwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        newAwardAmountInfo.setObligatedChange(new ScaleTwoDecimal(0));
        newAwardAmountInfo.setAnticipatedChange(new ScaleTwoDecimal(0));
       //subtract transaction amounts from distributable
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect());
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect());
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect());
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect());
        newAwardAmountInfo.setObligatedChangeDirect(pendingTransaction.getObligatedDirectAmount());
        newAwardAmountInfo.setObligatedChangeIndirect(pendingTransaction.getObligatedIndirectAmount());
        newAwardAmountInfo.setAnticipatedChangeDirect(pendingTransaction.getAnticipatedDirectAmount());
        newAwardAmountInfo.setAnticipatedChangeIndirect(pendingTransaction.getAnticipatedIndirectAmount());

        
        //updateAmountFields(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, newAwardAmountInfo);
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }
    
    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    protected AwardAmountInfo getUpdatedSourceUpNodeAmountInfo(PendingTransaction pendingTransaction, 
            AwardAmountInfo awardAmountInfo, Map<String, AwardAmountTransaction> awardAmountTransactionItems, 
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award) {
        
        AwardAmountInfo newAwardAmountInfo = getNewAwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        newAwardAmountInfo.setObligatedChange(new ScaleTwoDecimal(0).subtract(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setAnticipatedChange(new ScaleTwoDecimal(0).subtract(pendingTransaction.getAnticipatedAmount()));
       //subtract transaction amounts from distributable
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate().subtract(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect().subtract(pendingTransaction.getObligatedDirectAmount()));
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect().subtract(pendingTransaction.getObligatedIndirectAmount()));
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount().subtract(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect().subtract(pendingTransaction.getAnticipatedDirectAmount()));
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect().subtract(pendingTransaction.getAnticipatedIndirectAmount()));
        newAwardAmountInfo.setObligatedChangeDirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getObligatedDirectAmount()));
        newAwardAmountInfo.setObligatedChangeIndirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getObligatedIndirectAmount()));
        newAwardAmountInfo.setAnticipatedChangeDirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getAnticipatedDirectAmount()));
        newAwardAmountInfo.setAnticipatedChangeIndirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getAnticipatedIndirectAmount()));
        
        //updateAmountFields(updateAmounts, addOrSubtract, pendingTransaction, awardAmountInfo, newAwardAmountInfo);
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }
    


    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    protected AwardAmountInfo getUpdatedIntermediateDownNodeAmountInfo(PendingTransaction pendingTransaction, 
            AwardAmountInfo awardAmountInfo, Map<String, AwardAmountTransaction> awardAmountTransactionItems, 
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award) {
        
        AwardAmountInfo newAwardAmountInfo = getNewAwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        newAwardAmountInfo.setObligatedChange(pendingTransaction.getObligatedAmount());
        newAwardAmountInfo.setAnticipatedChange(pendingTransaction.getAnticipatedAmount());
       //add transaction amounts to the AmountInfo
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate().add(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect().add(pendingTransaction.getObligatedDirectAmount()));
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect().add(pendingTransaction.getObligatedIndirectAmount()));
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount().add(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect().add(pendingTransaction.getAnticipatedDirectAmount()));
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect().add(pendingTransaction.getAnticipatedIndirectAmount()));
        newAwardAmountInfo.setObligatedChangeDirect(pendingTransaction.getObligatedDirectAmount());
        newAwardAmountInfo.setObligatedChangeIndirect(pendingTransaction.getObligatedIndirectAmount());
        newAwardAmountInfo.setAnticipatedChangeDirect(pendingTransaction.getAnticipatedDirectAmount());
        newAwardAmountInfo.setAnticipatedChangeIndirect(pendingTransaction.getAnticipatedIndirectAmount());
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }
    
    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    protected AwardAmountInfo getUpdatedIntermediateUpNodeAmountInfo(PendingTransaction pendingTransaction, 
            AwardAmountInfo awardAmountInfo, Map<String, AwardAmountTransaction> awardAmountTransactionItems, 
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award) {
        
        AwardAmountInfo newAwardAmountInfo = getNewAwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(awardAmountInfo.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(award.getSequenceNumber());
        newAwardAmountInfo.setOriginatingAwardVersion(award.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(awardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(awardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(awardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(pendingTransaction.getTransactionId());
        newAwardAmountInfo.setObligatedChange(new ScaleTwoDecimal(0).subtract(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setAnticipatedChange(new ScaleTwoDecimal(0).subtract(pendingTransaction.getAnticipatedAmount()));
       //add transaction amounts to the AmountInfo
        newAwardAmountInfo.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
        newAwardAmountInfo.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate().subtract(pendingTransaction.getObligatedAmount()));
        newAwardAmountInfo.setObligatedTotalDirect(awardAmountInfo.getObligatedTotalDirect().subtract(pendingTransaction.getObligatedDirectAmount()));
        newAwardAmountInfo.setObligatedTotalIndirect(awardAmountInfo.getObligatedTotalIndirect().subtract(pendingTransaction.getObligatedIndirectAmount()));
        newAwardAmountInfo.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
        newAwardAmountInfo.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount().subtract(pendingTransaction.getAnticipatedAmount()));
        newAwardAmountInfo.setAnticipatedTotalDirect(awardAmountInfo.getAnticipatedTotalDirect().subtract(pendingTransaction.getAnticipatedDirectAmount()));
        newAwardAmountInfo.setAnticipatedTotalIndirect(awardAmountInfo.getAnticipatedTotalIndirect().subtract(pendingTransaction.getAnticipatedIndirectAmount()));
        newAwardAmountInfo.setObligatedChangeDirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getObligatedDirectAmount()));
        newAwardAmountInfo.setObligatedChangeIndirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getObligatedIndirectAmount()));
        newAwardAmountInfo.setAnticipatedChangeDirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getAnticipatedDirectAmount()));
        newAwardAmountInfo.setAnticipatedChangeIndirect(new ScaleTwoDecimal(0).subtract(pendingTransaction.getAnticipatedIndirectAmount()));
        
        addAwardAmountTransaction(newAwardAmountInfo.getAwardNumber(), awardAmountTransactionItems, newAwardAmountTransaction, documentNumber);

        return newAwardAmountInfo;
    }
    
    protected void handleTransaction(PendingTransaction pendingTransaction, String awardNumber, 
            Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<PendingTransaction> pendingTransactions,
            AwardAmountTransaction newAwardAmountTransaction, String documentNumber, GenerateAwardAmountInfoFunction amountInfoFunc) {
        
    	if (allowTimeAndMoneyWhenPendingAwardExists()) {
	        Award pendingAward = awardVersionService.getPendingAwardVersion(awardNumber);
	        updateAmountInfo(pendingTransaction, awardAmountTransactionItems, awardItems, newAwardAmountTransaction, documentNumber, pendingAward, amountInfoFunc);
	
	        Award activeAward = awardVersionService.getActiveAwardVersion(awardNumber);
	        updateAmountInfo(pendingTransaction, awardAmountTransactionItems, awardItems, newAwardAmountTransaction, documentNumber, activeAward, amountInfoFunc);
    	} else {
    		Award workingAward = awardVersionService.getWorkingAwardVersion(awardNumber);
    		updateAmountInfo(pendingTransaction, awardAmountTransactionItems, awardItems, newAwardAmountTransaction, documentNumber, workingAward, amountInfoFunc);
    	}
    }
    
    protected boolean allowTimeAndMoneyWhenPendingAwardExists() {
    	return parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM);
    }
    
    private void updateAmountInfo(PendingTransaction pendingTransaction, 
    		Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, 
    		AwardAmountTransaction newAwardAmountTransaction, String documentNumber, Award award, GenerateAwardAmountInfoFunction amountInfoFunc) {
       if (award != null) {
           AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
           award.getAwardAmountInfos().add(amountInfoFunc.apply(pendingTransaction, awardAmountInfo, awardAmountTransactionItems, newAwardAmountTransaction, documentNumber, award));
           awardItems.add(award);
       }
    }

    /*
     * This method validates the AntiDistributableAmount
     * @param pendingTransaction
     * @param awardAmountInfo
     * @param totalPendingAnticipated
     */
    protected void validateAntiDistributableAmount(PendingTransaction pendingTransaction, AwardAmountInfo awardAmountInfo, ScaleTwoDecimal totalPendingAnticipated){
        if(pendingTransaction.getAnticipatedAmount()!=null){
            if(awardAmountInfo.getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount().add(totalPendingAnticipated)).isLessThan(new ScaleTwoDecimal(0))){
                throw new RuntimeException("Insufficient Anticipated Money");
            }
        }
    }
    
    /*
     * This is a helper method to add awardAmountTransaction information.
     * 
     * AwardAmountTransacion table is going to have one entry per document, per affected award.
     * Affected award here means an award that is part of any of the Pending Transactions.
     * 
     * That's why we will maintain a map of AwardAmountTransaction Objects with awardNumber as the key. If the key is present, we won't
     * add a new entry here, otherwise we will. We will persist all awardAmountTransaction objects later.
     */
    protected void addAwardAmountTransaction(String awardNumber, Map<String, AwardAmountTransaction> awardAmountTransactionItems, AwardAmountTransaction newAwardAmountTransaction, String documentNumber) {
        if(!awardAmountTransactionItems.containsKey(awardNumber)){
            AwardAmountTransaction newAwardAmountTransaction1 = new AwardAmountTransaction(); 
            newAwardAmountTransaction1.setAwardNumber(awardNumber);
            newAwardAmountTransaction1.setDocumentNumber(documentNumber);
            newAwardAmountTransaction1.setTransactionTypeCode(newAwardAmountTransaction.getTransactionTypeCode());
            newAwardAmountTransaction1.setComments(newAwardAmountTransaction.getComments());
            newAwardAmountTransaction1.setNoticeDate(newAwardAmountTransaction.getNoticeDate());
            awardAmountTransactionItems.put(awardNumber, newAwardAmountTransaction1);    
        }       
    }
    
    public AwardVersionService getAwardVersionService() {
        awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
        return awardVersionService;
    }

    
    /**
     * Replace the UserSession with one for the user who routed the parent award.
     * @param parentAward
     * @return
     */
    protected UserSession replaceSessionWithRoutedBy(TimeAndMoneyDocument doc) {
        String routedByUserId = doc.getDocumentHeader().getWorkflowDocument().getRoutedByPrincipalId();
        UserSession oldSession = GlobalVariables.getUserSession();

        if (ObjectUtils.isNotNull(routedByUserId)) {
            Person person = getPersonService().getPerson(routedByUserId);
            GlobalVariables.setUserSession(new UserSession(person.getPrincipalName()));
        }
        return oldSession;
    }
    
    @SuppressWarnings("unchecked")
    protected PersonService getPersonService() {
        return personService;
    }

    @SuppressWarnings("unchecked")
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    

    /*
     * 
     * This is a helper method to carry out calculation on various amount fields.
     * 
     * updateAmounts true means the calculations should be done otherwise the same value is returned.
     * addOrSubtract true means the value should be added and false means value should be subtracted.
     * 
     */
    protected ScaleTwoDecimal processAmounts(ScaleTwoDecimal value1, ScaleTwoDecimal value2, boolean addOrSubtract, boolean updateAmounts){
        ScaleTwoDecimal returnValue;
        if(updateAmounts){
            if(addOrSubtract){
                if(value1!=null){
                    returnValue =  value1.add(value2);    
                }else{
                    returnValue = value2;
                }   
            }else{
                if(value1!=null){
                    returnValue =  value1.subtract(value2);    
                }else{
                    returnValue = value2;
                }
            }
        }else{
            returnValue = value1;
        }
        return returnValue;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the awardAmountInfoService attribute. 
     * @return Returns the awardAmountInfoService.
     */
    public AwardAmountInfoService getAwardAmountInfoService() {
        return awardAmountInfoService;
    }
    
    public void setAwardVersionService(AwardVersionService awardVersionService) {
        this.awardVersionService = awardVersionService;
    }

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
