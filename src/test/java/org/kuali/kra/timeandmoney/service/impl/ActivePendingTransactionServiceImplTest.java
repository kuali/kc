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
package org.kuali.kra.timeandmoney.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kns.util.KualiDecimal;

public class ActivePendingTransactionServiceImplTest {
    
    public static final KualiDecimal TEN_THOUSAND = new KualiDecimal(10000);
    public static final KualiDecimal TWENTY_THOUSAND = new KualiDecimal(20000);
    public static final KualiDecimal ZERO = new KualiDecimal(0);
    
    public static final String awardNumber1 = "000021-00001";
    public static final String awardNumber2 = "000021-00002";
    public static final String awardNumber3 = "000021-00003";
    public static final String awardNumber4 = "000021-00004";
    public static final String awardNumber5 = "000021-00005";
    public static final String awardNumber6 = "000021-00006";
    public static final String awardNumber7 = "000021-00007";
    
    ActivePendingTransactionsServiceImpl activePendingTransactionsServiceImpl;
    Map<String, AwardHierarchyNode> awardHierarchyNodes;
    AwardHierarchyNode awardHierarchyNode;
    PendingTransaction pt;

    @Before
    public void setUp() throws Exception {
        activePendingTransactionsServiceImpl = new ActivePendingTransactionsServiceImpl();
        awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
        awardHierarchyNode = new AwardHierarchyNode();
        pt = new PendingTransaction();
    }

    @After
    public void tearDown() throws Exception {
        activePendingTransactionsServiceImpl = null;
        awardHierarchyNodes = null;
        awardHierarchyNode = null;
        pt = null;
    }
    
    @Test
    public void testIndirectParentChildRelationshipExists(){        
        
        addAwardHierarchyNode(awardNumber1, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, awardNumber1);
        
        addAwardHierarchyNode(awardNumber1, awardNumber1, awardNumber2);
        
        addAwardHierarchyNode(awardNumber1, awardNumber1, awardNumber3);
        
        addAwardHierarchyNode(awardNumber1, awardNumber2, awardNumber4);
        
        AwardHierarchyNode parent = new AwardHierarchyNode();        
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber1, awardNumber2, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber1, awardNumber3, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber1, awardNumber4, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber2, awardNumber3, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber2, awardNumber4, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber2, parent.getAwardNumber());
     
        
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber2, awardNumber1, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
             
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber3, awardNumber1, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber4, awardNumber1, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber3, awardNumber2, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardNumber4, awardNumber2, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber2, parent.getAwardNumber());
     
                
    }
    
    //@Test
    public void testProcessPendingTransaction(){
        //activePendingTransactionsServiceImpl.processPendingTransaction(doc, newAwardAmountTransaction, updatedPendingTransactions, parentNode, transactionDetailItems, awardAmountTransactionItems, awardItems, pendingTransaction, awardHierarchyNodes, sourceAwardNode, destinationAwardNode);
    }
    
    @Test
    public void testUpdateAmountFields(){        
        AwardAmountInfo aai = new AwardAmountInfo();        
        aai.setAmountObligatedToDate(TEN_THOUSAND);
        aai.setAntDistributableAmount(TEN_THOUSAND);
        aai.setObliDistributableAmount(TEN_THOUSAND);
        aai.setAnticipatedTotalAmount(TEN_THOUSAND);
        
        AwardAmountInfo nai = new AwardAmountInfo();        
        nai.setAmountObligatedToDate(TEN_THOUSAND);
        nai.setAntDistributableAmount(TEN_THOUSAND);
        nai.setObliDistributableAmount(TEN_THOUSAND);
        nai.setAnticipatedTotalAmount(TEN_THOUSAND);
        
        pt.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
        pt.setDestinationAwardNumber(awardNumber1);
        pt.setObligatedAmount(TEN_THOUSAND);
        pt.setAnticipatedAmount(TEN_THOUSAND);
        activePendingTransactionsServiceImpl.updateAmountFields(false, false, pt, aai, nai);
        Assert.assertEquals(TEN_THOUSAND, nai.getAntDistributableAmount());
        Assert.assertEquals(TWENTY_THOUSAND, nai.getAnticipatedTotalAmount());
        Assert.assertEquals(TWENTY_THOUSAND, nai.getAmountObligatedToDate());
        Assert.assertEquals(TEN_THOUSAND, nai.getObliDistributableAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getObligatedChange());
        Assert.assertEquals(TEN_THOUSAND, nai.getAnticipatedChange());
        
        pt.setSourceAwardNumber(awardNumber1);
        pt.setDestinationAwardNumber(awardNumber3);
        pt.setObligatedAmount(TEN_THOUSAND);
        pt.setAnticipatedAmount(TEN_THOUSAND);
        activePendingTransactionsServiceImpl.updateAmountFields(false, false, pt, aai, nai);
        Assert.assertEquals(TEN_THOUSAND, nai.getAntDistributableAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getAnticipatedTotalAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getAmountObligatedToDate());
        Assert.assertEquals(TEN_THOUSAND, nai.getObliDistributableAmount());
        Assert.assertEquals(ZERO, nai.getObligatedChange());
        Assert.assertEquals(ZERO, nai.getAnticipatedChange());       
        
        pt.setSourceAwardNumber(awardNumber1);
        pt.setDestinationAwardNumber(awardNumber3);
        pt.setObligatedAmount(TEN_THOUSAND);
        pt.setAnticipatedAmount(TEN_THOUSAND);
        activePendingTransactionsServiceImpl.updateAmountFields(true, true, pt, aai, nai);
        Assert.assertEquals(TWENTY_THOUSAND, nai.getAntDistributableAmount());
        Assert.assertEquals(TWENTY_THOUSAND, nai.getAnticipatedTotalAmount());
        Assert.assertEquals(TWENTY_THOUSAND, nai.getAmountObligatedToDate());
        Assert.assertEquals(TWENTY_THOUSAND, nai.getObliDistributableAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getObligatedChange());
        Assert.assertEquals(TEN_THOUSAND, nai.getAnticipatedChange());
        
        pt.setSourceAwardNumber(awardNumber1);
        pt.setDestinationAwardNumber(awardNumber3);
        pt.setObligatedAmount(TEN_THOUSAND);
        pt.setAnticipatedAmount(TEN_THOUSAND);
        activePendingTransactionsServiceImpl.updateAmountFields(true, false, pt, aai, nai);
        Assert.assertEquals(ZERO, nai.getAntDistributableAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getAnticipatedTotalAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getAmountObligatedToDate());
        Assert.assertEquals(ZERO, nai.getObliDistributableAmount());
        Assert.assertEquals(TEN_THOUSAND, nai.getObligatedChange());
        Assert.assertEquals(TEN_THOUSAND, nai.getAnticipatedChange());       
        
    }
    
    @Test
    public void testFetchAwardAmountInfoWithHighestTransactionId(){
        List<AwardAmountInfo> awardAmountInfos = new ArrayList<AwardAmountInfo>();
        addAwardAmountInfos(awardAmountInfos, 1);
        addAwardAmountInfos(awardAmountInfos, 4);
        addAwardAmountInfos(awardAmountInfos, 3);
        addAwardAmountInfos(awardAmountInfos, 115);
        addAwardAmountInfos(awardAmountInfos, 32);
        addAwardAmountInfos(awardAmountInfos, 13);
        Assert.assertEquals(new Long(115), activePendingTransactionsServiceImpl.fetchAwardAmountInfoWithHighestTransactionId(awardAmountInfos).getTransactionId());
    }
    
    public void testValidateAntiDistributableAmount(){
        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        pt.setSourceAwardNumber(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT);
        pt.setDestinationAwardNumber(awardNumber1);
        pt.setObligatedAmount(TEN_THOUSAND);
        pt.setAnticipatedAmount(TEN_THOUSAND);
        KualiDecimal totalPendingAnticipated = TEN_THOUSAND;
        //awardAmountInfo.setaward
        activePendingTransactionsServiceImpl.validateAntiDistributableAmount(pt, awardAmountInfo, totalPendingAnticipated);
    }
    
    @Test
    public void testFindCommonParent(){
        addAwardHierarchyNode(awardNumber1, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, awardNumber1);        
        addAwardHierarchyNode(awardNumber1, awardNumber1, awardNumber2);        
        addAwardHierarchyNode(awardNumber1, awardNumber1, awardNumber3);        
        addAwardHierarchyNode(awardNumber1, awardNumber2, awardNumber4);
        addAwardHierarchyNode(awardNumber1, awardNumber2, awardNumber5);
        addAwardHierarchyNode(awardNumber1, awardNumber3, awardNumber6);
        addAwardHierarchyNode(awardNumber1, awardNumber3, awardNumber7);
        
        Assert.assertEquals(awardNumber1, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber2, awardNumber3));
        Assert.assertEquals(awardNumber1, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber2, awardNumber4));
        Assert.assertEquals(awardNumber1, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber4, awardNumber6));
        Assert.assertEquals(awardNumber2, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber4, awardNumber5));
        Assert.assertEquals(awardNumber3, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber6, awardNumber7));
        Assert.assertEquals(awardNumber1, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber2, awardNumber7));
        Assert.assertEquals(awardNumber1, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, awardNumber1, awardNumber3, awardNumber4));
    }

    /**
     * This method...
     * @param awardAmountInfos
     */
    private void addAwardAmountInfos(List<AwardAmountInfo> awardAmountInfos, int transactionId) {
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setTransactionId(new Long(transactionId));
        awardAmountInfos.add(newAwardAmountInfo);
    }

    /**
     * This method...
     * @param rootAwardNumber
     * @param parentAwardNumber
     * @param awardNumber
     */
    private void addAwardHierarchyNode(String rootAwardNumber, String parentAwardNumber, String awardNumber) {
        awardHierarchyNode = new AwardHierarchyNode();
        awardHierarchyNode.setAwardNumber(awardNumber);
        awardHierarchyNode.setParentAwardNumber(parentAwardNumber);
        awardHierarchyNode.setRootAwardNumber(rootAwardNumber);        
        awardHierarchyNodes.put(awardNumber, awardHierarchyNode);
    }
}
