/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetModular;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.impl.DocumentServiceImpl;
import org.kuali.rice.kns.service.impl.KualiConfigurationServiceImpl;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Tests for the {@link BudgetModularTotalDirectCostRule BudgetModularTotalDirectCostRule} class.
 */
public class BudgetModularTotalDirectCostRuleTest {

    private static final List<BudgetVersionOverview> ONE_COMPLETE = new ArrayList<BudgetVersionOverview>();
    private static final List<BudgetVersionOverview> TWO_COMPLETE = new ArrayList<BudgetVersionOverview>();
    private static final List<BudgetVersionOverview> ONE_INCOMPLETE = new ArrayList<BudgetVersionOverview>();
    private static final List<BudgetVersionOverview> TWO_INCOMPLETE = new ArrayList<BudgetVersionOverview>();
    static {
        final BudgetVersionOverview overview1 = new BudgetVersionOverview();
        overview1.setBudgetStatus("1");
        overview1.setDocumentNumber("1234");
        
        final BudgetVersionOverview overview2 = new BudgetVersionOverview();
        overview2.setBudgetStatus("1");
        overview2.setDocumentNumber("1234");
        
        
        final BudgetVersionOverview overview3 = new BudgetVersionOverview();
        overview3.setBudgetStatus("2");
        overview3.setDocumentNumber("1234");

        final BudgetVersionOverview overview4 = new BudgetVersionOverview();
        overview4.setBudgetStatus("2");
        overview4.setDocumentNumber("1234");

        BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE.add(overview1);

        BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE.add(overview1);
        BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE.add(overview2);

        BudgetModularTotalDirectCostRuleTest.ONE_INCOMPLETE.add(overview3);

        BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE.add(overview3);
        BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE.add(overview4);
    }

    private ProposalDevelopmentDocument pdDocument;
    
    //if more methods are required override them to avoid loading all the spring junk
    private KualiConfigurationService configService = new KualiConfigurationService() {
     
       public String getParameterValue(String namespaceCode,
           String parameterDetailTypeCode, String parameterName) {
           if (Constants.PARAMETER_MODULE_BUDGET.equals(namespaceCode)
               && Constants.PARAMETER_COMPONENT_DOCUMENT.equals(parameterDetailTypeCode)
               && Constants.BUDGET_STATUS_COMPLETE_CODE.equals(parameterName)) {
               return "1";
           }
           throw new UnsupportedOperationException("not supported.");
       }
       
       
        public String getPropertyString(String key) {
           if (KeyConstants.WARNING_BUDGET_VERSION_MODULAR_INVALID_TDC.equals(key)) {
               return "At least one Modular Budget contains an invalid Total Direct Cost.";
           }
           throw new UnsupportedOperationException("not supported.");
       }

    public boolean evaluateConstrainedValue(String namespaceCode,
        String detailTypeCode, String parameterName, String constrainedValue) {
        throw new UnsupportedOperationException("not supported.");
    }

    public Properties getAllProperties() {
        throw new UnsupportedOperationException("not supported.");
    }

    public boolean getIndicatorParameter(String namespaceCode,
        String detailTypeCode, String parameterName) {
        throw new UnsupportedOperationException("not supported.");
    }

    public Parameter getParameter(String namespaceCode, String detailTypeCode,
        String parameterName) {
        throw new UnsupportedOperationException("not supported.");
    }

    public List<String> getParameterValues(String namespaceCode,
        String detailTypeCode, String parameterName) {
        throw new UnsupportedOperationException("not supported.");
    }

    public List<Parameter> getParameters(Map<String, String> criteria) {
        throw new UnsupportedOperationException("not supported.");
    }

    public boolean getPropertyAsBoolean(String key) {
        throw new UnsupportedOperationException("not supported.");
    }

    public boolean isProductionEnvironment() {
        throw new UnsupportedOperationException("not supported.");
    }
    
    public Parameter getParameterWithoutExceptions(String namespaceCode, String detailTypeCode, String parameterName) {
        return null;
    }
    };

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setErrorMap(new ErrorMap());
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
        new BudgetModularTotalDirectCostRule(new KualiConfigurationServiceImpl(), null);
    }

    /**
     * Test rule creation with null doc service.
     */
    @Test(expected = NullPointerException.class)
    public void testNullDocu() {
        new BudgetModularTotalDirectCostRule(null, new DocumentServiceImpl());
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
        
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
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
        
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        rule.validateTotalDirectCost(this.pdDocument, true, null);
    }
    
    

    private void testNoErrors(final DocumentService service) {
        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getErrorMap(), GlobalVariables.getErrorMap().isEmpty());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());

        warnings = new HashSet<String>();
        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getErrorMap(), GlobalVariables.getErrorMap().isEmpty());
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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(BudgetDecimal.ZERO);
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.ONE_INCOMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getErrorMap(), GlobalVariables.getErrorMap().isEmpty());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());

        warnings = new HashSet<String>();
        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE);
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getErrorMap(), GlobalVariables.getErrorMap().isEmpty());
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
                doc.setModularBudgetFlag(Boolean.FALSE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(BudgetDecimal.ZERO);
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.setBudgetPeriods(periods);
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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(new BudgetDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.setBudgetPeriods(periods);
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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(new BudgetDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                final BudgetModular modular2 = new BudgetModular();
                modular2.setTotalDirectCost(new BudgetDecimal(.1));
                period2.setBudgetModular(modular2);
                periods.add(period2);

                doc.setBudgetPeriods(periods);
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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
                periods.add(null);
                periods.add(null);

                doc.setBudgetPeriods(periods);
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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                period1.setBudgetModular(null);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                period2.setBudgetModular(null);
                periods.add(period2);

                doc.setBudgetPeriods(periods);
                return doc;
            }
        };
        
        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getErrorMap().size(), 1, GlobalVariables.getErrorMap().size());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));

        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only 2 error3 should have been produced, count "
            + GlobalVariables.getErrorMap().size(), 2, GlobalVariables.getErrorMap().size());
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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(new BudgetDecimal(0));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                final BudgetModular modular2 = new BudgetModular();
                modular2.setTotalDirectCost(BudgetDecimal.ZERO);
                period2.setBudgetModular(modular2);
                periods.add(period2);

                doc.setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getErrorMap().size(), 1, GlobalVariables.getErrorMap().size());

        GlobalVariables.getErrorMap().clear();
        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
        Assert.assertEquals("Only 2 error should have been produced, count "
            + GlobalVariables.getErrorMap().size(), 2, GlobalVariables.getErrorMap().size());

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
                doc.setModularBudgetFlag(Boolean.TRUE);

                final List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();

                final BudgetPeriod period1 = new BudgetPeriod();
                final BudgetModular modular1 = new BudgetModular();
                modular1.setTotalDirectCost(new BudgetDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                final BudgetModular modular2 = new BudgetModular();
                modular2.setTotalDirectCost(BudgetDecimal.ZERO);
                period2.setBudgetModular(modular2);
                periods.add(period2);

                doc.setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getErrorMap(), GlobalVariables.getErrorMap().isEmpty());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));

        
        this.pdDocument.getDevelopmentProposal().setBudgetVersionOverviews(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(this.configService, service);
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getErrorMap(), GlobalVariables.getErrorMap().isEmpty());
        Assert.assertEquals("Only one warning shoudld have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
    }
}
