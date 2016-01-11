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
package org.kuali.kra.award.budget;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.krad.bo.PersistableBusinessObjectExtension;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import com.ibm.icu.util.Calendar;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
public class AwardBudgetServiceImplTest {

	protected Mockery context;
    protected AwardBudgetServiceImpl awardBudgetService;
    protected String testAwardNumber = "000000-00000";
    protected Long awardId = 1L;
    protected String proposalNumber = "111";
    protected Long proposalId = 2L;
    protected String devProposalNumber = "59";
    protected String devPropDocNumber = "55555";
    protected Long budgetId = 3L;
    
    @Before
    public void setUp() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }
    
    @Test
    public void testFindBudgetPeriodsFromLinkedProposal() {
        awardBudgetService = new AwardBudgetServiceImpl(){
            protected List findMatching(Class clazz, String key, Object value){
                return mockedFindObjectsWithSingleKey(clazz, key, value);
            }
        };
        
        final DevelopmentProposal temp = new DevelopmentProposal();
        ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
        doc.setDocumentNumber(devPropDocNumber);
        ProposalDevelopmentBudgetExt temp2 = new ProposalDevelopmentBudgetExt();
        temp2.add(new BudgetPeriod());
        temp2.add(new BudgetPeriod());
        temp.setFinalBudget(temp2);
        temp.setProposalDocument(doc);
        
        final DataObjectService doService = context.mock(DataObjectService.class);
        context.checking(new Expectations() {{
            one(doService).find(DevelopmentProposal.class, devProposalNumber);
            will(returnValue(temp));
        }});
        ((AwardBudgetServiceImpl)awardBudgetService).setDataObjectService(doService);
        
        Award tempAward = new Award() {
        	@Override
        	public List<AwardFundingProposal> getAllFundingProposals() {
        		return getTestAwardFundingProposals();
        	}
        };
        tempAward.setAwardId(awardId);
        final AwardService awardService = context.mock(AwardService.class);
        context.checking(new Expectations() {{
        	one(awardService).getActiveOrNewestAward(testAwardNumber);
        	will(returnValue(tempAward));
        }});
        ((AwardBudgetServiceImpl) awardBudgetService).setAwardService(awardService);
        
        List<BudgetPeriod> periods = awardBudgetService.findBudgetPeriodsFromLinkedProposal(testAwardNumber);
        assertTrue(periods.size() == 2);
        assertEquals(proposalNumber, periods.get(0).getInstitutionalProposalNumber());
    }
    
    @Test
    public void testCopyProposalBudgetLineItemsToAwardBudget() {
    	awardBudgetService = new AwardBudgetServiceImpl() {
    		@Override
    		protected void populateCalculatedAmount(AwardBudgetPeriodExt awardBudgetPeriod, AwardBudgetLineItemExt awardBudgetLineItem) {
    			return;
    		}
    	};
    	LocalDateTime awardBudgetStartDate = LocalDateTime.now().minusWeeks(26);
		LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
		final LocalDateTime proposalBudgetStartDate = awardBudgetStartDate;
		final LocalDateTime proposalBudgetEndDate = awardBudgetEndDate;
    	
		AwardBudgetPeriodExt awardBudgetPeriod1 = prepareAwardBudgetPeriod(awardBudgetStartDate, awardBudgetEndDate);
    	
    	BudgetPeriod proposalBudgetPeriod = prepareProposalBudgetPeriod(proposalBudgetStartDate, proposalBudgetEndDate);
    	
    	awardBudgetService.copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod1, proposalBudgetPeriod);
    	assertEquals(1, awardBudgetPeriod1.getBudgetLineItems().size());
    	assertEquals(convertToSqlDate(proposalBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getStartDate());
    	assertEquals(convertToSqlDate(proposalBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getEndDate());
    }
    
    @Test
    public void testCopyProposalBudgetLineItemsToAwardBudgetWithDifferentStartDates() {
    	awardBudgetService = new AwardBudgetServiceImpl() {
    		@Override
    		protected void populateCalculatedAmount(AwardBudgetPeriodExt awardBudgetPeriod, AwardBudgetLineItemExt awardBudgetLineItem) {
    			return;
    		}
    	};
    	LocalDateTime awardBudgetStartDate = LocalDateTime.now().minusWeeks(26);
    	LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
    	final LocalDateTime proposalBudgetStartDate = awardBudgetStartDate.minusWeeks(2);
    	final LocalDateTime proposalBudgetEndDate = awardBudgetEndDate.minusWeeks(2);
    	
    	AwardBudgetPeriodExt awardBudgetPeriod1 = prepareAwardBudgetPeriod(awardBudgetStartDate, awardBudgetEndDate);
    	
    	BudgetPeriod proposalBudgetPeriod = prepareProposalBudgetPeriod(proposalBudgetStartDate, proposalBudgetEndDate);
    	
    	GlobalVariables.getMessageMap().clearErrorMessages();
    	awardBudgetService.copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod1, proposalBudgetPeriod);
    	assertEquals(1, awardBudgetPeriod1.getBudgetLineItems().size());
    	assertEquals(convertToSqlDate(awardBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getStartDate());
    	assertEquals(convertToSqlDate(proposalBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getEndDate());
    	assertEquals(convertToSqlDate(awardBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getStartDate());
    	assertEquals(convertToSqlDate(proposalBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getEndDate());
    	assertEquals(1, GlobalVariables.getMessageMap().getWarningCount());
    }
    
    @Test
    public void testCopyProposalBudgetLineItemsToAwardBudgetWithDifferentEndDates() {
    	awardBudgetService = new AwardBudgetServiceImpl() {
    		@Override
    		protected void populateCalculatedAmount(AwardBudgetPeriodExt awardBudgetPeriod, AwardBudgetLineItemExt awardBudgetLineItem) {
    			return;
    		}
    	};
    	LocalDateTime awardBudgetStartDate = LocalDateTime.now().minusWeeks(26);
    	LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
    	final LocalDateTime proposalBudgetStartDate = awardBudgetStartDate.plusWeeks(2);
    	final LocalDateTime proposalBudgetEndDate = awardBudgetEndDate.plusWeeks(2);
    	
    	AwardBudgetPeriodExt awardBudgetPeriod1 = prepareAwardBudgetPeriod(awardBudgetStartDate, awardBudgetEndDate);
    	
    	BudgetPeriod proposalBudgetPeriod = prepareProposalBudgetPeriod(proposalBudgetStartDate, proposalBudgetEndDate);
    	
    	GlobalVariables.getMessageMap().clearErrorMessages();
    	awardBudgetService.copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod1, proposalBudgetPeriod);
    	assertEquals(1, awardBudgetPeriod1.getBudgetLineItems().size());
    	assertEquals(convertToSqlDate(proposalBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getStartDate());
    	assertEquals(convertToSqlDate(awardBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getEndDate());
    	assertEquals(convertToSqlDate(proposalBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getStartDate());
    	assertEquals(convertToSqlDate(awardBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getEndDate());
    	assertEquals(1, GlobalVariables.getMessageMap().getWarningCount());
    }
    
    @Test
    public void testCopyProposalBudgetLineItemsToAwardBudget_BeforePeriod() {
    	awardBudgetService = new AwardBudgetServiceImpl() {
    		@Override
    		protected void populateCalculatedAmount(AwardBudgetPeriodExt awardBudgetPeriod, AwardBudgetLineItemExt awardBudgetLineItem) {
    			return;
    		}
    	};
    	LocalDateTime awardBudgetStartDate = LocalDateTime.now();
    	LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
    	final LocalDateTime proposalBudgetStartDate = awardBudgetStartDate.minusYears(5);
    	final LocalDateTime proposalBudgetEndDate = awardBudgetEndDate.minusYears(5);
    	
    	AwardBudgetPeriodExt awardBudgetPeriod1 = prepareAwardBudgetPeriod(awardBudgetStartDate, awardBudgetEndDate);
    	
    	BudgetPeriod proposalBudgetPeriod = prepareProposalBudgetPeriod(proposalBudgetStartDate, proposalBudgetEndDate);
    	
    	GlobalVariables.getMessageMap().clearErrorMessages();
    	awardBudgetService.copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod1, proposalBudgetPeriod);
    	assertEquals(1, awardBudgetPeriod1.getBudgetLineItems().size());
    	assertEquals(convertToSqlDate(awardBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getStartDate());
    	assertEquals(convertToSqlDate(awardBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getEndDate());
    	assertEquals(convertToSqlDate(awardBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getStartDate());
    	assertEquals(convertToSqlDate(awardBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getEndDate());
    	assertEquals(1, GlobalVariables.getMessageMap().getWarningCount());
    }
    
    @Test
    public void testCopyProposalBudgetLineItemsToAwardBudget_AfterPeriod() {
    	awardBudgetService = new AwardBudgetServiceImpl() {
    		@Override
    		protected void populateCalculatedAmount(AwardBudgetPeriodExt awardBudgetPeriod, AwardBudgetLineItemExt awardBudgetLineItem) {
    			return;
    		}
    	};
    	LocalDateTime awardBudgetStartDate = LocalDateTime.now();
    	LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
    	final LocalDateTime proposalBudgetStartDate = awardBudgetStartDate.plusYears(5);
    	final LocalDateTime proposalBudgetEndDate = awardBudgetEndDate.plusYears(5);
    	
    	AwardBudgetPeriodExt awardBudgetPeriod1 = prepareAwardBudgetPeriod(awardBudgetStartDate, awardBudgetEndDate);
    	
    	BudgetPeriod proposalBudgetPeriod = prepareProposalBudgetPeriod(proposalBudgetStartDate, proposalBudgetEndDate);
    	
    	GlobalVariables.getMessageMap().clearErrorMessages();
    	awardBudgetService.copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod1, proposalBudgetPeriod);
    	assertEquals(1, awardBudgetPeriod1.getBudgetLineItems().size());
    	assertEquals(convertToSqlDate(awardBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getStartDate());
    	assertEquals(convertToSqlDate(awardBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getEndDate());
    	assertEquals(convertToSqlDate(awardBudgetStartDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getStartDate());
    	assertEquals(convertToSqlDate(awardBudgetEndDate), awardBudgetPeriod1.getBudgetLineItems().get(0).getBudgetPersonnelDetailsList().get(0).getEndDate());
    	assertEquals(1, GlobalVariables.getMessageMap().getWarningCount());
    }

	protected BudgetPeriod prepareProposalBudgetPeriod(
			final LocalDateTime proposalBudgetStartDate,
			final LocalDateTime proposalBudgetEndDate) {
		BudgetCategory category = new BudgetCategory();
    	category.setDescription("Equipment");
    	
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	BudgetPeriod proposalBudgetPeriod = new BudgetPeriod();
    	proposalBudgetPeriod.setBudget(budget);
    	BudgetLineItem lineItem = new BudgetLineItem() {
    		@Override
    		public PersistableBusinessObjectExtension getExtension() {
    			return null;
    		}
    	};
    	lineItem.setBudgetCategory(category);
		lineItem.setStartDate(convertToSqlDate(proposalBudgetStartDate));
		lineItem.setEndDate(convertToSqlDate(proposalBudgetEndDate));
		BudgetPerson person = new BudgetPerson() {
			@Override
    		public PersistableBusinessObjectExtension getExtension() {
				return null;
			}
		};
		person.setNonEmployeeFlag(false);
		person.setPersonId("1");
		budget.getBudgetPersons().add(person);
		BudgetPersonnelDetails personnelLineItem = new BudgetPersonnelDetails() {
			@Override
    		public PersistableBusinessObjectExtension getExtension() {
				return null;
			}
		};
		personnelLineItem.setStartDate(convertToSqlDate(proposalBudgetStartDate));
		personnelLineItem.setEndDate(convertToSqlDate(proposalBudgetEndDate));
		personnelLineItem.setBudgetPerson(person);
		lineItem.getBudgetPersonnelDetailsList().add(personnelLineItem);
    	proposalBudgetPeriod.getBudgetLineItems().add(lineItem);
		return proposalBudgetPeriod;
	}

	protected AwardBudgetPeriodExt prepareAwardBudgetPeriod(
			LocalDateTime awardBudgetStartDate, LocalDateTime awardBudgetEndDate) {
		AwardBudgetDocument awardBudgetDoc = new AwardBudgetDocument();
    	AwardBudgetExt awardBudget = new AwardBudgetExt();
    	awardBudget.setBudgetDocument(awardBudgetDoc);
    	AwardBudgetPeriodExt awardBudgetPeriod1 = new AwardBudgetPeriodExt();
    	awardBudgetPeriod1.setBudget(awardBudget);
    	awardBudget.getBudgetPeriods().add(awardBudgetPeriod1);
    	awardBudgetPeriod1.setStartDate(convertToSqlDate(awardBudgetStartDate));
    	awardBudgetPeriod1.setEndDate(convertToSqlDate(awardBudgetEndDate));
		return awardBudgetPeriod1;
	}

	protected Date convertToSqlDate(LocalDateTime awardBudgetStartDate) {
		return new java.sql.Date(awardBudgetStartDate.toInstant(ZoneOffset.ofHours(0)).toEpochMilli());
	}
    
    protected List<AwardFundingProposal> getTestAwardFundingProposals() {
    	List<AwardFundingProposal> result = new ArrayList<>();
	    AwardFundingProposal temp = new AwardFundingProposal();
	    temp.setActive(true);
	    InstitutionalProposalBoLite proposal = new InstitutionalProposalBoLite();
	    proposal.setProposalNumber(proposalNumber);
	    temp.setProposal(proposal);
	    result.add(temp);
	    temp = new AwardFundingProposal();
	    temp.setActive(false);
	    result.add(temp);
	    return result;
    }
    
    @SuppressWarnings("unchecked")
    protected List mockedFindObjectsWithSingleKey(Class clazz, String key, Object value) {
    	List result = new ArrayList();
    	if (clazz == AwardFundingProposal.class) {
            assertEquals(awardId, value);

        } else if (clazz == InstitutionalProposal.class) {
            assertEquals(proposalNumber, value);
            InstitutionalProposal temp = new InstitutionalProposal() {
            	@Override
            	protected void calculateFiscalMonthAndYearFields() {
            		
            	}
            };
            temp.setProposalId(proposalId);
            temp.setProposalNumber(proposalNumber);
            temp.setSequenceNumber(1);
            result.add(temp);
        } else if (clazz == ProposalAdminDetails.class) {
            assertEquals(proposalId, value);
            ProposalAdminDetails temp = new ProposalAdminDetails();
            temp.setDevProposalNumber(devProposalNumber);
            result.add(temp);
        }
        return result;
    }
}
