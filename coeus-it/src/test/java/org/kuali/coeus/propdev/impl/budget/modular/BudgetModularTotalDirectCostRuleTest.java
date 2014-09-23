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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalAuditEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.impl.services.ConfigurationServiceImpl;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.impl.DocumentServiceImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
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
