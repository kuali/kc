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
package org.kuali.kra.timeandmoney.service.impl;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.HashMap;
import java.util.Map;

public class ActivePendingTransactionServiceImplTest {
    
    public static final ScaleTwoDecimal TEN_THOUSAND = new ScaleTwoDecimal(10000);
    public static final ScaleTwoDecimal TWENTY_THOUSAND = new ScaleTwoDecimal(20000);
    public static final ScaleTwoDecimal ZERO = new ScaleTwoDecimal(0);
    
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
        Assert.assertTrue(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardNumber1, awardNumber2, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
             
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardNumber1, awardNumber3, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardNumber1, awardNumber4, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber1, parent.getAwardNumber());
     
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardNumber2, awardNumber3, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardNumber2, awardNumber4, awardHierarchyNodes, parent));
        Assert.assertEquals(awardNumber2, parent.getAwardNumber());
     
                
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

    private void addAwardHierarchyNode(String rootAwardNumber, String parentAwardNumber, String awardNumber) {
        awardHierarchyNode = new AwardHierarchyNode();
        awardHierarchyNode.setAwardNumber(awardNumber);
        awardHierarchyNode.setParentAwardNumber(parentAwardNumber);
        awardHierarchyNode.setRootAwardNumber(rootAwardNumber);        
        awardHierarchyNodes.put(awardNumber, awardHierarchyNode);
    }
}
