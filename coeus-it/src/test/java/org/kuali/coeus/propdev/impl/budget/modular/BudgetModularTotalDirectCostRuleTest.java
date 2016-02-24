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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalAuditEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.*;

/**
 * Tests for the {@link org.kuali.coeus.propdev.impl.budget.modular.BudgetModularTotalDirectCostRule BudgetModularTotalDirectCostRule} class.
 */
public class BudgetModularTotalDirectCostRuleTest extends KcIntegrationTestBase {

    private ProposalDevelopmentDocument pdDocument;
    private BudgetModularTotalDirectCostRule rule;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setMessageMap(new MessageMap());
        this.pdDocument = new ProposalDevelopmentDocument();

        this.pdDocument.getDocumentHeader().setDocumentDescription(this.getClass().getName());
        this.pdDocument.getDevelopmentProposal().setSponsorCode("005889");
        this.pdDocument.getDevelopmentProposal().setTitle("Project title");
        this.pdDocument.getDevelopmentProposal().setRequestedStartDateInitial(new Date(Calendar.getInstance().getTime().getTime()));
        this.pdDocument.getDevelopmentProposal().setRequestedEndDateInitial(new Date(Calendar.getInstance().getTime().getTime()));
        this.pdDocument.getDevelopmentProposal().setActivityTypeCode("1");
        this.pdDocument.getDevelopmentProposal().setProposalTypeCode("1");
        this.pdDocument.getDevelopmentProposal().setOwnedByUnitNumber("000001");
        rule = new BudgetModularTotalDirectCostRule();
        rule.setParamService(CoreFrameworkServiceLocator.getParameterService());
    }
    
    

    private void testNoErrors(ProposalDevelopmentBudgetExt budget) {
    	GlobalVariables.getMessageMap().clearErrorMessages();
    	GlobalVariables.getMessageMap().getWarningMessages().clear();
    	this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoWarnings());

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoWarnings());
    }
    
    /**
     * Tests a document that has tdc errors does not error because
     * it is not completed.
     */
    @Test
    public void testNotCompleteWithErrors() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("2");

        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

        final BudgetPeriod period1 = new BudgetPeriod();
        final BudgetModular modular1 = new BudgetModular();
        modular1.setTotalDirectCost(ScaleTwoDecimal.ZERO);
        period1.setBudgetModular(modular1);
        periods.add(period1);
        budget.setBudgetPeriods(periods);

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoWarnings());

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoWarnings());
    }

    /**
     * Tests a document that has tdc errors does not error because
     * it is not modular.
     */
    @Test
    public void testNotModularWithErrors() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.FALSE);
    	budget.setBudgetStatus("1");
        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

        final BudgetPeriod period1 = new BudgetPeriod();
        final BudgetModular modular1 = new BudgetModular();
        modular1.setTotalDirectCost(new ScaleTwoDecimal(1));
        period1.setBudgetModular(modular1);
        periods.add(period1);

        budget.setBudgetPeriods(periods);

        testNoErrors(budget);
    }

    /**
     * Tests the no errors are produced from a single positive tdc.
     */
    @Test
    public void testOnePeriodPositve() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("1");

	    final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
	
	    final BudgetPeriod period1 = new BudgetPeriod();
	    final BudgetModular modular1 = new BudgetModular();
	    modular1.setTotalDirectCost(new ScaleTwoDecimal(1));
	    period1.setBudgetModular(modular1);
	    periods.add(period1);
	
	    budget.setBudgetPeriods(periods);

        testNoErrors(budget);
    }

    /**
     * Tests the no errors are produced from multiple positive tdc.
     */
    @Test
    public void testMultPeriodPositive() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("1");

        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

        final BudgetPeriod period1 = new BudgetPeriod();
        final BudgetModular modular1 = new BudgetModular();
        modular1.setTotalDirectCost(new ScaleTwoDecimal(1));
        period1.setBudgetModular(modular1);
        periods.add(period1);

        final BudgetPeriod period2 = new BudgetPeriod();
        final BudgetModular modular2 = new BudgetModular();
        modular2.setTotalDirectCost(new ScaleTwoDecimal(.1));
        period2.setBudgetModular(modular2);
        periods.add(period2);

        budget.setBudgetPeriods(periods);

        testNoErrors(budget);
    }

    /**
     * Tests that no errors are produced with null periods.
     */
    @Test
    public void testNullPeriods() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("1");

        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        periods.add(null);
        periods.add(null);

        budget.setBudgetPeriods(periods);

        testNoErrors(budget);
    }

    /**
     * 
     * Tests that no errors are produced with null modular.
     */
    @Test
    public void testNullModular() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("1");

        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

        final BudgetPeriod period1 = new BudgetPeriod();
        period1.setBudgetModular(null);
        periods.add(period1);

        final BudgetPeriod period2 = new BudgetPeriod();
        period2.setBudgetModular(null);
        periods.add(period2);

        budget.setBudgetPeriods(periods);
        
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 1, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertEquals("Only one warning should have been produced, count "
            + GlobalVariables.getMessageMap(), 1, GlobalVariables.getMessageMap().getWarningMessages().size());

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertEquals("Only 2 error3 should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 2, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertEquals("Only 2 warning should have been produced, count "
            + GlobalVariables.getMessageMap(), 2, GlobalVariables.getMessageMap().getWarningMessages().size());
    }

    /**
     * Tests that errors and warnings are produced when all tdc's are 0.
     */
    @Test
    public void testTdcErrors() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("1");

        final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

        final BudgetPeriod period1 = new BudgetPeriod();
        final BudgetModular modular1 = new BudgetModular();
        modular1.setTotalDirectCost(new ScaleTwoDecimal(0));
        period1.setBudgetModular(modular1);
        periods.add(period1);

        final BudgetPeriod period2 = new BudgetPeriod();
        final BudgetModular modular2 = new BudgetModular();
        modular2.setTotalDirectCost(ScaleTwoDecimal.ZERO);
        period2.setBudgetModular(modular2);
        periods.add(period2);

        budget.setBudgetPeriods(periods);

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertEquals("Only one warning should have been produced, count "
            + GlobalVariables.getMessageMap(), 1, GlobalVariables.getMessageMap().getWarningMessages().size());
        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 1, GlobalVariables.getMessageMap().getErrorMessages().size());

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertEquals("Only 2 warning should have been produced, count "
            + GlobalVariables.getMessageMap(), 2, GlobalVariables.getMessageMap().getWarningMessages().size());
        Assert.assertEquals("Only 2 error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 2, GlobalVariables.getMessageMap().getErrorMessages().size());

    }

    /**
     * Tests that a warning is produced when at least one tdc is 0.
     */
    @Test
    public void testTdcWarning() {
    	ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
    	budget.setModularBudgetFlag(Boolean.TRUE);
    	budget.setBudgetStatus("1");
	
	    final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
	
	    final BudgetPeriod period1 = new BudgetPeriod();
	    final BudgetModular modular1 = new BudgetModular();
	    modular1.setTotalDirectCost(new ScaleTwoDecimal(1));
	    period1.setBudgetModular(modular1);
	    periods.add(period1);
	
	    final BudgetPeriod period2 = new BudgetPeriod();
	    final BudgetModular modular2 = new BudgetModular();
	    modular2.setTotalDirectCost(ScaleTwoDecimal.ZERO);
	    period2.setBudgetModular(modular2);
	    periods.add(period2);
	
	    budget.setBudgetPeriods(periods);

	    GlobalVariables.getMessageMap().clearErrorMessages();
	    GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertEquals("Only one warning should have been produced, count "
            + GlobalVariables.getMessageMap(), 1, GlobalVariables.getMessageMap().getWarningMessages().size());

        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        this.pdDocument.getDevelopmentProposal().getBudgets().add(budget);
        rule.validateTotalDirectCost(new ProposalAuditEvent(pdDocument));
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertEquals("Only 2 warning shoudld have been produced, count "
            + GlobalVariables.getMessageMap(), 2, GlobalVariables.getMessageMap().getWarningMessages().size());
    }
}
