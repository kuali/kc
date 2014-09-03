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
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
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

    private static final List<ProposalDevelopmentBudgetExt> ONE_COMPLETE = new ArrayList<>();
    private static final List<ProposalDevelopmentBudgetExt> TWO_COMPLETE = new ArrayList<>();
    private static final List<ProposalDevelopmentBudgetExt> ONE_INCOMPLETE = new ArrayList<>();
    private static final List<ProposalDevelopmentBudgetExt> TWO_INCOMPLETE = new ArrayList<>();
    static {
        ProposalDevelopmentBudgetExt docver1 = new ProposalDevelopmentBudgetExt();
        docver1.setBudgetStatus("1");
        docver1.setDocumentNumber("1234");
        
        ProposalDevelopmentBudgetExt docver2 = new ProposalDevelopmentBudgetExt();
        docver2.setBudgetStatus("1");
        docver2.setDocumentNumber("1234");
        
        
        ProposalDevelopmentBudgetExt docver3 = new ProposalDevelopmentBudgetExt();
        docver3.setBudgetStatus("2");
        docver3.setDocumentNumber("1234");

        ProposalDevelopmentBudgetExt docver4 = new ProposalDevelopmentBudgetExt();
        docver4.setBudgetStatus("2");
        docver4.setDocumentNumber("1234");

        BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE.add(docver1);

        BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE.add(docver2);
        BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE.add(docver2);

        BudgetModularTotalDirectCostRuleTest.ONE_INCOMPLETE.add(docver3);

        BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE.add(docver3);
        BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE.add(docver4);
    }

    private ProposalDevelopmentDocument pdDocument;
    
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
    }
    
    private String getWarning(final BudgetModularTotalDirectCostRule rule) {
        
        try {
            final Field warning = BudgetModularTotalDirectCostRule.class.getDeclaredField("tdcWarning");
            return AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    try {
                        warning.setAccessible(true);
                        return (String) warning.get(rule);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test rule creation with null config service.
     */
    @Test(expected = NullPointerException.class)
    public void testNullConfigService() {
        new BudgetModularTotalDirectCostRule(new ConfigurationServiceImpl(), null, null);
    }

    /**
     * Test rule creation with null doc service.
     */
    @Test(expected = NullPointerException.class)
    public void testNullDocu() {
        new BudgetModularTotalDirectCostRule(null, new DocumentServiceImpl(), null);
    }
    
    
    /**
     * Test rule creation with null document.
     */
    @Test(expected = NullPointerException.class)
    public void testNullDocument() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                return new BudgetDocument();
            }
        };
        
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        rule.validateTotalDirectCost(null, true, new HashSet<String>());
    }

    /**
     * Test rule creation with null warnings container.
     */
    @Test(expected = NullPointerException.class)
    public void testNullWarnings() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                return new BudgetDocument();
            }
        };
        
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        rule.validateTotalDirectCost(this.pdDocument, true, null);
    }
    
    

    private void testNoErrors(final DocumentService service) {
    	this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());

        warnings = new HashSet<String>();
        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());
    }
    
    /**
     * Tests a document that has tdc errors does not error because
     * it is not completed.
     */
    @Test
    public void testNotCompleteWithErrors() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(ScaleTwoDecimal.ZERO);
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());

        warnings = new HashSet<String>();
        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());
    }

    /**
     * Tests a document that has tdc errors does not error because
     * it is not modular.
     */
    @Test
    public void testNotModularWithErrors() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.FALSE);
                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(new ScaleTwoDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        testNoErrors(service);
    }

    /**
     * Tests the no errors are produced from a single positive tdc.
     */
    @Test
    public void testOnePeriodPositve() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(new ScaleTwoDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        testNoErrors(service);
    }

    /**
     * Tests the no errors are produced from multiple positive tdc.
     */
    @Test
    public void testMultPeriodPositive() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

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

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        testNoErrors(service);
    }

    /**
     * Tests that no errors are produced with null periods.
     */
    @Test
    public void testNullPeriods() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
                periods.add(null);
                periods.add(null);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        testNoErrors(service);
    }

    /**
     * 
     * Tests that no errors are produced with null modular.
     */
    @Test
    public void testNullModular() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                period1.setBudgetModular(null);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                period2.setBudgetModular(null);
                periods.add(period2);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };
        
        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 1, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));

        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only 2 error3 should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 2, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
    }

    /**
     * Tests that errors and warnings are produced when all tdc's are 0.
     */
    @Test
    public void testTdcErrors() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

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

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 1, GlobalVariables.getMessageMap().getErrorMessages().size());

        GlobalVariables.getMessageMap().clearErrorMessages();
        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
        Assert.assertEquals("Only 2 error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 2, GlobalVariables.getMessageMap().getErrorMessages().size());

    }

    /**
     * Tests that a warning is produced when at least one tdc is 0.
     */
    @Test
    public void testTdcWarning() {
        final DocumentService service = new DocumentServiceImpl() {
            @Override
            public BudgetDocument getByDocumentHeaderId(final String documentHeaderId) {
                final BudgetDocument doc = new BudgetDocument();
                doc.getBudget().setModularBudgetFlag(Boolean.TRUE);

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

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));

        this.pdDocument.getDevelopmentProposal().setBudgets(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(CoreApiServiceLocator.getKualiConfigurationService(), service,
                CoreFrameworkServiceLocator.getParameterService());
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertEquals("Only one warning shoudld have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
    }
}
