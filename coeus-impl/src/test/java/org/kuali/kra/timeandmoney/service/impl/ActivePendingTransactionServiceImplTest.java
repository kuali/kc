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


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.AwardAmountInfoServiceImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ActivePendingTransactionServiceImplTest {
    
    public static final ScaleTwoDecimal TEN_THOUSAND = new ScaleTwoDecimal(10000);
    public static final ScaleTwoDecimal TWENTY_THOUSAND = new ScaleTwoDecimal(20000);
    public static final ScaleTwoDecimal ZERO = new ScaleTwoDecimal(0);
    
    private final String rootAwardNumber = "000021-00001";
    private final String awardChild1 = "000021-00002";
    private final String awardChild2 = "000021-00003";
    private final String awardGrandChild1Of1 = "000021-00004";
    private final String awardGrandChild2Of1 = "000021-00005";
    private final String awardGrandChild1Of2 = "000021-00006";
    private final String awardGrandChild2Of2 = "000021-00007";
    
    ActivePendingTransactionsServiceImpl activePendingTransactionsServiceImpl;
    Map<String, AwardHierarchyNode> awardHierarchyNodes;
    AwardHierarchyNode awardHierarchyNode;
    PendingTransaction pt;
    
	Answer<Award> emptyAwardAnswer;

    @Before
    public void setUp() throws Exception {
        activePendingTransactionsServiceImpl = new ActivePendingTransactionsServiceImpl() {
    		@Override
    		AwardAmountInfo getNewAwardAmountInfo() {
    			return getAwardAmountInfoForTesting();
    		}
    	};
        awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
        awardHierarchyNode = new AwardHierarchyNode();
        pt = new PendingTransaction();
        buildDefaultAwardHierarchy();
        
        emptyAwardAnswer = new Answer<Award>() {
    		public Award answer(InvocationOnMock invocation) {
    			return getEmptyAward(invocation.getArgument(0));
    		}
    	};
    }

	public void buildDefaultAwardHierarchy() {
		addAwardHierarchyNode(rootAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, rootAwardNumber);        
        addAwardHierarchyNode(rootAwardNumber, rootAwardNumber, awardChild1);        
        addAwardHierarchyNode(rootAwardNumber, rootAwardNumber, awardChild2);        
        addAwardHierarchyNode(rootAwardNumber, awardChild1, awardGrandChild1Of1);
        addAwardHierarchyNode(rootAwardNumber, awardChild1, awardGrandChild2Of1);
        addAwardHierarchyNode(rootAwardNumber, awardChild2, awardGrandChild1Of2);
        addAwardHierarchyNode(rootAwardNumber, awardChild2, awardGrandChild2Of2);
	}

    @After
    public void tearDown() throws Exception {
        activePendingTransactionsServiceImpl = null;
        awardHierarchyNodes = null;
        awardHierarchyNode = null;
        pt = null;
    }
    
    @Test
    public void testProcessTransactionsFromParentToChild() {
    	
    	final String sourceAwardNumber = rootAwardNumber;
    	final String destinationAwardNumber = awardChild1;
    	
    	
    	final Award sourceAward = createSourceAward(sourceAwardNumber, TEN_THOUSAND);
    	final Award destinationAward = createDestinationAward(destinationAwardNumber, TEN_THOUSAND);
    	
    	AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	AwardAmountInfo childAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);
    	
    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, sourceAward);
		PendingTransaction trans1 = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
    	final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
    	
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newRootAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	assertEquals(rootAwardAmountInfo.getObliDistributableAmount().subtract(trans1.getObligatedAmount()), newRootAwardAmountInfo.getObliDistributableAmount());
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	assertEquals(childAwardAmountInfo.getObliDistributableAmount().add(trans1.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(2, newAwardTransactions.size());
    	assertEquals(2, transactionDetailItems.size());
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(newRootAwardAmountInfo.getAwardNumber()));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(newRootAwardAmountInfo.getAwardNumber()).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(newRootAwardAmountInfo.getAwardNumber()).getComments());
    	assertTrue(awardAmountTransactionItems.containsKey(destinationAward.getAwardNumber()));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(destinationAward.getAwardNumber()).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(destinationAward.getAwardNumber()).getComments());
    	
    	
    }
    
    @Test
    public void testProcessTransactionsWhenSourceIsExternal() {
    	final String sourceAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
    	final String destinationAwardNumber = awardChild1;
    	
    	
    	final Award rootAward = createSourceAward(rootAwardNumber, TEN_THOUSAND);
    	final Award destinationAward = createDestinationAward(destinationAwardNumber, TEN_THOUSAND);
    	
    	AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	AwardAmountInfo childAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);

    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, rootAward);
		PendingTransaction pendingTransaction = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
    	
		final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newRootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	assertEquals(rootAwardAmountInfo.getObliDistributableAmount(), newRootAwardAmountInfo.getObliDistributableAmount());
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	assertEquals(childAwardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(2, newAwardTransactions.size());
    	assertEquals(3, transactionDetailItems.size());
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(2, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(rootAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(rootAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(rootAwardNumber).getComments());
    	assertTrue(awardAmountTransactionItems.containsKey(destinationAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(destinationAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(destinationAwardNumber).getComments());
    	
    }
    
    @Test
    public void testProcessTransactionsWhenSourceIsExternalAndDestinationIsRoot() {
    	final String sourceAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
    	final String destinationAwardNumber = rootAwardNumber;
    	final Award rootAward = createSourceAward(rootAwardNumber, TEN_THOUSAND);    	
    	AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	when(mockedAwardVersionService.getPendingAwardVersion(destinationAwardNumber)).thenReturn(null);
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(destinationAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(destinationAwardNumber)).thenReturn(rootAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);
    	
    	
    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, rootAward);
		PendingTransaction pendingTransaction = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
    	
		final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	
    	assertEquals(rootAwardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(1, newAwardTransactions.size());
    	assertEquals(2, transactionDetailItems.size());
    	assertEquals(1,  getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(rootAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(rootAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(rootAwardNumber).getComments());
    	
    }
    
    @Test
    public void testProcessTransactionsFromChildToParent() {
    	
    	final String sourceAwardNumber = awardChild1;
    	final String destinationAwardNumber = rootAwardNumber;
    	
    	final Award sourceAward = createSourceAward(sourceAwardNumber, TEN_THOUSAND);
    	final Award destinationAward = createDestinationAward(destinationAwardNumber, TEN_THOUSAND);
    	
    	AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	AwardAmountInfo childAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);    
    		
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);
    	
    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, sourceAward);
		PendingTransaction trans1 = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
    	
		final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newRootAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	assertEquals(rootAwardAmountInfo.getObliDistributableAmount().subtract(trans1.getObligatedAmount()), newRootAwardAmountInfo.getObliDistributableAmount());
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	assertEquals(childAwardAmountInfo.getObliDistributableAmount().add(trans1.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(2, newAwardTransactions.size());
    	assertEquals(2, transactionDetailItems.size());
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(rootAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(rootAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(rootAwardNumber).getComments());
    	assertTrue(awardAmountTransactionItems.containsKey(sourceAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(sourceAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(sourceAwardNumber).getComments());
    	
    }
    
    @Test
    public void testProcessTransactionsWhenDestinationIsExternal() {
    	final String sourceAwardNumber = awardChild1;
    	final String destinationAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
    	
    	final Award rootAward = createSourceAward(rootAwardNumber, TEN_THOUSAND);
    	final Award sourceAward = createDestinationAward(sourceAwardNumber, TEN_THOUSAND);
    	
    	AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	AwardAmountInfo childAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);    	
    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);
    	
    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, rootAward);
		PendingTransaction pendingTransaction = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
		final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newRootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	assertEquals(rootAwardAmountInfo.getObliDistributableAmount(), newRootAwardAmountInfo.getObliDistributableAmount());
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	assertEquals(childAwardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(2, newAwardTransactions.size());
    	assertEquals(3, transactionDetailItems.size());
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(2, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(rootAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(rootAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(rootAwardNumber).getComments());
    	assertTrue(awardAmountTransactionItems.containsKey(sourceAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(sourceAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(sourceAwardNumber).getComments());
    	
    }
    
    @Test
    public void testProcessTransactionsWhenDestinationIsExternalAndSourceIsRoot() {
    	final String sourceAwardNumber = rootAwardNumber;
    	final String destinationAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
    	
    	
    	final Award rootAward = createSourceAward(rootAwardNumber, TEN_THOUSAND);
    	final Award sourceAward = rootAward;
    	
    	AwardAmountInfo childAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(sourceAwardNumber)).thenReturn(sourceAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);
    	
    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, rootAward);
		PendingTransaction pendingTransaction = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
		final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	assertEquals(childAwardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(1, newAwardTransactions.size());
    	assertEquals(2, transactionDetailItems.size());
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(rootAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(rootAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(rootAwardNumber).getComments());
    	
    }
    
    int getTransactionDetailTypeCount(ArrayList<TransactionDetail> transactionDetailItems, TransactionDetailType type) {
    	int count = 0;
    	for (TransactionDetail transactionDetailItem : transactionDetailItems) {
    		if (type.toString().equals(transactionDetailItem.getTransactionDetailType())) {
    			count++;
    		}
    	}
    	return count;
    }

	TimeAndMoneyDocument buildTimeAndMoneyDocument(final String sourceAwardNumber, final String destinationAwardNumber, final Award sourceAward) {
		TimeAndMoneyDocument timeAndMoneyDoc = new TimeAndMoneyDocument();
    	timeAndMoneyDoc.setAwardHierarchyNodes(awardHierarchyNodes);
    	timeAndMoneyDoc.setAward(sourceAward);
    	PendingTransaction pendingTransaction = new PendingTransaction();
    	pendingTransaction.setSourceAwardNumber(sourceAwardNumber);
    	pendingTransaction.setDestinationAwardNumber(destinationAwardNumber);
    	pendingTransaction.setObligatedAmount(TEN_THOUSAND);
    	pendingTransaction.setAnticipatedAmount(TEN_THOUSAND);
		timeAndMoneyDoc.getPendingTransactions().add(pendingTransaction);
		return timeAndMoneyDoc;
	}

	Award createDestinationAward(final String destinationAwardNumber, ScaleTwoDecimal amountToDate) {
		final Award childAward1 = new Award();
		childAward1.setAwardNumber(destinationAwardNumber);
    	childAward1.setSequenceNumber(1);
    	AwardAmountInfo childAwardAmountInfo = getAwardAmountInfoForTesting();
    	childAwardAmountInfo.setObliDistributableAmount(amountToDate);
    	childAwardAmountInfo.setAmountObligatedToDate(amountToDate);
    	childAwardAmountInfo.setAnticipatedTotalAmount(amountToDate);
    	childAwardAmountInfo.setAmountObligatedToDate(amountToDate);
    	childAwardAmountInfo.setAntDistributableAmount(amountToDate);
    	childAwardAmountInfo.setTransactionId(0L);
    	childAwardAmountInfo.setAwardNumber(destinationAwardNumber);
    	childAward1.getAwardAmountInfos().add(childAwardAmountInfo);
		return childAward1;
	}

	Award createSourceAward(final String sourceAwardNumber, ScaleTwoDecimal amountToDate) {
		final Award rootAward = createDestinationAward(sourceAwardNumber, amountToDate);
		return rootAward;
	}
    
    @Test
    public void testProcessTransactionsWithIndirectRelationship() {
    	final Award sourceAward = createSourceAward(awardGrandChild1Of1, TEN_THOUSAND);    	
    	final Award destinationAward = createSourceAward(awardGrandChild1Of2, TEN_THOUSAND);
    	final Award intermediateUpAward = createSourceAward(awardChild1, ZERO);    	
    	final Award intermediateDownAward = createSourceAward(awardChild2, ZERO);
    	final Award rootAward = createSourceAward(rootAwardNumber, ZERO);
    	
    	final AwardAmountInfo sourceAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	final AwardAmountInfo destinationAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	final AwardAmountInfo intermediateUpAwardAmountInfo = getMostRecentAwardAmountInfo(intermediateUpAward);
    	final AwardAmountInfo intermediateDownAwardAmountInfo = getMostRecentAwardAmountInfo(intermediateDownAward);
    	final AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getPendingAwardVersion(anyString())).thenReturn(null);
    	
    	when(mockedAwardVersionService.getActiveAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getActiveAwardVersion(awardGrandChild1Of1)).thenReturn(sourceAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(awardGrandChild1Of2)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(awardChild1)).thenReturn(intermediateUpAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(awardChild2)).thenReturn(intermediateDownAward);
    	when(mockedAwardVersionService.getActiveAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	
		when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(awardGrandChild1Of1)).thenReturn(sourceAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(awardGrandChild1Of2)).thenReturn(destinationAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(awardChild1)).thenReturn(intermediateUpAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(awardChild2)).thenReturn(intermediateDownAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(true);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);

    	TimeAndMoneyDocument timeAndMoneyDoc = new TimeAndMoneyDocument();
    	timeAndMoneyDoc.setAwardHierarchyNodes(awardHierarchyNodes);
    	timeAndMoneyDoc.setAward(sourceAward);
    	
    	PendingTransaction pendingTransaction = new PendingTransaction();
    	pendingTransaction.setSourceAwardNumber(awardGrandChild1Of1);
    	pendingTransaction.setDestinationAwardNumber(awardGrandChild1Of2);
    	pendingTransaction.setObligatedAmount(TEN_THOUSAND);
    	pendingTransaction.setAnticipatedAmount(TEN_THOUSAND);
		timeAndMoneyDoc.getPendingTransactions().add(pendingTransaction);
		
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, new AwardAmountTransaction(), new HashMap<String, AwardAmountTransaction>(), 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newSourceAwardAmountInfo = getMostRecentAwardAmountInfo(sourceAward);
    	assertEquals(sourceAwardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()), newSourceAwardAmountInfo.getObliDistributableAmount());
    	AwardAmountInfo newDesintationAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	assertEquals(destinationAwardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()), newDesintationAwardAmountInfo.getObliDistributableAmount());
    	
    	assertEquals(pendingTransaction.getObligatedAmount().negated(), getMostRecentAwardAmountInfo(intermediateUpAward).getObligatedChange());
    	assertEquals(intermediateUpAwardAmountInfo.getObliDistributableAmount(), getMostRecentAwardAmountInfo(intermediateUpAward).getObliDistributableAmount());
    	assertEquals(intermediateUpAwardAmountInfo.getAmountObligatedToDate().subtract(pendingTransaction.getObligatedAmount()), getMostRecentAwardAmountInfo(intermediateUpAward).getAmountObligatedToDate());
    	
    	assertEquals(pendingTransaction.getObligatedAmount(), getMostRecentAwardAmountInfo(intermediateDownAward).getObligatedChange());
    	assertEquals(intermediateDownAwardAmountInfo.getObliDistributableAmount(), getMostRecentAwardAmountInfo(intermediateDownAward).getObliDistributableAmount());
    	assertEquals(intermediateDownAwardAmountInfo.getAmountObligatedToDate().add(pendingTransaction.getObligatedAmount()), getMostRecentAwardAmountInfo(intermediateDownAward).getAmountObligatedToDate());

    	assertEquals(4, newAwardTransactions.size());
    	assertEquals(5, transactionDetailItems.size());
    	assertTransactionDetailsContainsOnePrimaryTransaction(transactionDetailItems);
    }
    
    @Test
    public void testProcessTransactionsWhenSourceIsExternal_NoPendingAward() {
    	final String sourceAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
    	final String destinationAwardNumber = awardChild1;
    	
    	final Award rootAward = createSourceAward(rootAwardNumber, TEN_THOUSAND);
    	final Award destinationAward = createDestinationAward(destinationAwardNumber, TEN_THOUSAND);
    	
    	AwardAmountInfo rootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	AwardAmountInfo childAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	
    	AwardVersionService mockedAwardVersionService = mock(AwardVersionService.class);
    	when(mockedAwardVersionService.getWorkingAwardVersion(anyString())).thenAnswer(emptyAwardAnswer);
    	when(mockedAwardVersionService.getWorkingAwardVersion(rootAwardNumber)).thenReturn(rootAward);
    	when(mockedAwardVersionService.getWorkingAwardVersion(destinationAwardNumber)).thenReturn(destinationAward);
    	
    	AwardAmountInfoService awardAmountInfoService = mock(AwardAmountInfoServiceImpl.class);
    	when(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(anyList())).thenCallRealMethod();
    	
    	ParameterService parameterService = mock(ParameterService.class);
    	when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM))
    		.thenReturn(false);
    	activePendingTransactionsServiceImpl.setParameterService(parameterService);
    	
    	activePendingTransactionsServiceImpl.setAwardVersionService(mockedAwardVersionService);
    	activePendingTransactionsServiceImpl.setAwardAmountInfoService(awardAmountInfoService);

    	TimeAndMoneyDocument timeAndMoneyDoc = buildTimeAndMoneyDocument(sourceAwardNumber, destinationAwardNumber, rootAward);
		PendingTransaction pendingTransaction = timeAndMoneyDoc.getPendingTransactions().get(0);
    	ArrayList<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
    	
		final HashMap<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
		final AwardAmountTransaction newAwardAmountTransaction = new AwardAmountTransaction();
		newAwardAmountTransaction.setTransactionTypeCode(1);
		newAwardAmountTransaction.setComments("Test Comment");
		List<AwardAmountTransaction> newAwardTransactions = activePendingTransactionsServiceImpl.processTransactions(timeAndMoneyDoc, newAwardAmountTransaction, awardAmountTransactionItems, 
    			new ArrayList<Award>(), transactionDetailItems, Boolean.TRUE);
		
    	AwardAmountInfo newRootAwardAmountInfo = getMostRecentAwardAmountInfo(rootAward);
    	assertEquals(rootAwardAmountInfo.getObliDistributableAmount(), newRootAwardAmountInfo.getObliDistributableAmount());
    	AwardAmountInfo newChildAwardAmountInfo = getMostRecentAwardAmountInfo(destinationAward);
    	assertEquals(childAwardAmountInfo.getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()), newChildAwardAmountInfo.getObliDistributableAmount());
    	assertEquals(2, newAwardTransactions.size());
    	assertEquals(3, transactionDetailItems.size());
    	assertEquals(1, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.PRIMARY));
    	assertEquals(2, getTransactionDetailTypeCount(transactionDetailItems, TransactionDetailType.INTERMEDIATE));
    	assertTrue(awardAmountTransactionItems.containsKey(rootAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(rootAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(rootAwardNumber).getComments());
    	assertTrue(awardAmountTransactionItems.containsKey(destinationAwardNumber));
    	assertEquals(newAwardAmountTransaction.getTransactionTypeCode(), awardAmountTransactionItems.get(destinationAwardNumber).getTransactionTypeCode());
    	assertEquals(newAwardAmountTransaction.getComments(), awardAmountTransactionItems.get(destinationAwardNumber).getComments());
    	
    }

    
    private void assertTransactionDetailsContainsOnePrimaryTransaction(List<TransactionDetail> transactionDetails) {
    	for (TransactionDetail detail : transactionDetails) {
    		if (StringUtils.equals(TransactionDetailType.PRIMARY.toString(), detail.getTransactionDetailType())) {
    			return;
    		}
    	}
    	fail("No primary transaction found in transaction details");
    }
    
    @Test
    public void testIndirectParentChildRelationshipExists(){        
        
        addAwardHierarchyNode(rootAwardNumber, Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, rootAwardNumber);
        
        addAwardHierarchyNode(rootAwardNumber, rootAwardNumber, awardChild1);
        
        addAwardHierarchyNode(rootAwardNumber, rootAwardNumber, awardChild2);
        
        addAwardHierarchyNode(rootAwardNumber, awardChild1, awardGrandChild1Of1);
        
        AwardHierarchyNode parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(rootAwardNumber, awardChild1, awardHierarchyNodes, parent));
        Assert.assertEquals(rootAwardNumber, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(rootAwardNumber, awardChild2, awardHierarchyNodes, parent));
        Assert.assertEquals(rootAwardNumber, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();        
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(rootAwardNumber, awardGrandChild1Of1, awardHierarchyNodes, parent));
        Assert.assertEquals(rootAwardNumber, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardChild1, awardChild2, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertTrue(activePendingTransactionsServiceImpl.parentChildRelationshipExists(awardChild1, awardGrandChild1Of1, awardHierarchyNodes, parent));
        Assert.assertEquals(awardChild1, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardChild1, rootAwardNumber, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardChild2, rootAwardNumber, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardGrandChild1Of1, rootAwardNumber, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardChild2, awardChild1, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
        
        parent = new AwardHierarchyNode();
        Assert.assertFalse(activePendingTransactionsServiceImpl.childParentRelationshipExists(awardGrandChild1Of1, awardChild1, awardHierarchyNodes, parent));
        Assert.assertEquals(null, parent.getAwardNumber());
     
                
    }
    
    @Test
    public void testFindCommonParent(){        
        Assert.assertEquals(rootAwardNumber, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardChild1, awardChild2));
        Assert.assertEquals(rootAwardNumber, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardChild1, awardGrandChild1Of1));
        Assert.assertEquals(rootAwardNumber, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardGrandChild1Of1, awardGrandChild1Of2));
        Assert.assertEquals(awardChild1, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardGrandChild1Of1, awardGrandChild2Of1));
        Assert.assertEquals(awardChild2, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardGrandChild1Of2, awardGrandChild2Of2));
        Assert.assertEquals(rootAwardNumber, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardChild1, awardGrandChild2Of2));
        Assert.assertEquals(rootAwardNumber, activePendingTransactionsServiceImpl.findCommonParent(awardHierarchyNodes, rootAwardNumber, awardChild2, awardGrandChild1Of1));
    }

    private void addAwardHierarchyNode(String rootAwardNumber, String parentAwardNumber, String awardNumber) {
        awardHierarchyNode = new AwardHierarchyNode();
        awardHierarchyNode.setAwardNumber(awardNumber);
        awardHierarchyNode.setParentAwardNumber(parentAwardNumber);
        awardHierarchyNode.setRootAwardNumber(rootAwardNumber);        
        awardHierarchyNodes.put(awardNumber, awardHierarchyNode);
    }

	private AwardAmountInfo getMostRecentAwardAmountInfo(Award award) {
		return award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1);
	}

	Award getEmptyAward(String awardNumber) {
		Award throwAwayAward = new Award();
		throwAwayAward.setAwardNumber(awardNumber);
		throwAwayAward.getAwardAmountInfos().add(new AwardAmountInfo());
		throwAwayAward.getAwardAmountInfos().get(0).setAwardNumber(awardNumber);
		return throwAwayAward;
	}

	AwardAmountInfo getAwardAmountInfoForTesting() {
		return new AwardAmountInfo() {
			@Override
		    protected void changeUpdateElements(Object newObject, Object oldObject) {
		    	
		    }
		};
	}
}
