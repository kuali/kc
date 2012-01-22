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
package org.kuali.kra.proposaldevelopment.rules;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.impl.ConfigurationServiceImpl;
import org.kuali.rice.krad.service.impl.DocumentServiceImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * Tests for the {@link BudgetModularTotalDirectCostRule BudgetModularTotalDirectCostRule} class.
 */
public class BudgetModularTotalDirectCostRuleTest {

    private static final List<BudgetDocumentVersion> ONE_COMPLETE = new ArrayList<BudgetDocumentVersion>();
    private static final List<BudgetDocumentVersion> TWO_COMPLETE = new ArrayList<BudgetDocumentVersion>();
    private static final List<BudgetDocumentVersion> ONE_INCOMPLETE = new ArrayList<BudgetDocumentVersion>();
    private static final List<BudgetDocumentVersion> TWO_INCOMPLETE = new ArrayList<BudgetDocumentVersion>();
    static {
        BudgetDocumentVersion docver1 = new BudgetDocumentVersion();
        final BudgetVersionOverview overview1 = docver1.getBudgetVersionOverview();
        overview1.setBudgetStatus("1");
        overview1.setDocumentNumber("1234");
        
        BudgetDocumentVersion docver2 = new BudgetDocumentVersion();
        final BudgetVersionOverview overview2 = docver2.getBudgetVersionOverview();
        overview2.setBudgetStatus("1");
        overview2.setDocumentNumber("1234");
        
        
        BudgetDocumentVersion docver3 = new BudgetDocumentVersion();
        final BudgetVersionOverview overview3 = docver3.getBudgetVersionOverview();
        overview3.setBudgetStatus("2");
        overview3.setDocumentNumber("1234");

        BudgetDocumentVersion docver4 = new BudgetDocumentVersion();
        final BudgetVersionOverview overview4 = docver4.getBudgetVersionOverview();
        overview4.setBudgetStatus("2");
        overview4.setDocumentNumber("1234");

        BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE.add(docver1);

        BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE.add(docver2);
        BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE.add(docver2);

        BudgetModularTotalDirectCostRuleTest.ONE_INCOMPLETE.add(docver3);

        BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE.add(docver3);
        BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE.add(docver4);
    }

    private ProposalDevelopmentDocument pdDocument;
    
    private ParameterService paramService = new ParameterService() {

        public void clearCache() {
            throw new UnsupportedOperationException("not supported.");
            
        }

        public String getDetailType(Class<? extends Object> documentOrStepClass) {
            throw new UnsupportedOperationException("not supported.");
        }

        public boolean getIndicatorParameter(Class<? extends Object> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        public boolean getIndicatorParameter(String namespaceCode, String detailTypeCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        public String getNamespace(Class<? extends Object> documentOrStepClass) {
            throw new UnsupportedOperationException("not supported.");
        }

        public String getParameterValue(Class<? extends Object> componentClass, String parameterName) {
            if (BudgetDocument.class.equals(componentClass) && Constants.BUDGET_STATUS_COMPLETE_CODE.equals(parameterName)) {
                return "1";
            }
            throw new UnsupportedOperationException("not supported.");
        }

        public String getParameterValue(Class<? extends Object> componentClass, String parameterName, String constrainingValue) {
            throw new UnsupportedOperationException("not supported.");
        }

        public String getParameterValue(String namespaceCode,
                String parameterDetailTypeCode, String parameterName) {
                if (Constants.MODULE_NAMESPACE_BUDGET.equals(namespaceCode)
                    && ParameterConstants.DOCUMENT_COMPONENT.equals(parameterDetailTypeCode)
                    && Constants.BUDGET_STATUS_COMPLETE_CODE.equals(parameterName)) {
                    return "1";
                }
                throw new UnsupportedOperationException("not supported.");
        }

        public List<String> getParameterValues(Class<? extends Object> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        public List<String> getParameterValues(Class<? extends Object> componentClass, String parameterName,
                String constrainingValue) {
            throw new UnsupportedOperationException("not supported.");
        }

        public List<String> getParameterValues(String namespaceCode, String detailTypeCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        public Parameter retrieveParameter(String namespaceCode, String detailTypeCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        public List<Parameter> retrieveParametersGivenLookupCriteria(Map<String, String> fieldValues) {
            throw new UnsupportedOperationException("not supported.");
        }

        public void setParameterForTesting(Class<? extends Object> componentClass, String parameterName, String parameterText) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Parameter createParameter(Parameter parameter) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Parameter getParameter(Class<?> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Parameter getParameter(String namespaceCode, String componentCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName, Boolean defaultValue) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName,
                Boolean defaultValue) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getParameterValueAsString(Class<?> componentClass, String parameterName, String defaultValue) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getParameterValueAsString(Class<?> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName,
                String defaultValue) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Collection<String> getParameterValuesAsString(Class<?> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Collection<String> getParameterValuesAsString(String namespaceCode, String componentCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getSubParameterValueAsString(Class<?> componentClass, String parameterName, String subParameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getSubParameterValueAsString(String namespaceCode, String componentCode, String parameterName,
                String subParameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Collection<String> getSubParameterValuesAsString(Class<?> componentClass, String parameterName,
                String subParameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Collection<String> getSubParameterValuesAsString(String namespaceCode, String componentCode, String parameterName,
                String subParameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Boolean parameterExists(Class<?> componentClass, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Boolean parameterExists(String namespaceCode, String componentCode, String parameterName) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public Parameter updateParameter(Parameter parameter) {
            throw new UnsupportedOperationException("not supported.");
        }
    };
    
    //if more methods are required override them to avoid loading all the spring junk
    private ConfigurationService configService = new ConfigurationService() {
        @Override
        public Map<String, String> getAllProperties() {
             throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public boolean getPropertyValueAsBoolean(String key) {
            throw new UnsupportedOperationException("not supported.");
        }

        @Override
        public String getPropertyValueAsString(String key) {
            throw new UnsupportedOperationException("not supported.");
        }

    };

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
        
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
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
        
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        rule.validateTotalDirectCost(this.pdDocument, true, null);
    }
    
    

    private void testNoErrors(final DocumentService service) {
        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());

        warnings = new HashSet<String>();
        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
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
                modular1.setTotalDirectCost(BudgetDecimal.ZERO);
                period1.setBudgetModular(modular1);
                periods.add(period1);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.ONE_INCOMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertTrue("The validation should not have produced any warnings " + warnings, warnings.isEmpty());

        warnings = new HashSet<String>();
        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.TWO_INCOMPLETE);
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
                modular1.setTotalDirectCost(new BudgetDecimal(1));
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
                modular1.setTotalDirectCost(new BudgetDecimal(1));
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
                modular1.setTotalDirectCost(new BudgetDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                final BudgetModular modular2 = new BudgetModular();
                modular2.setTotalDirectCost(new BudgetDecimal(.1));
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
        
        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 1, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));

        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
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
                modular1.setTotalDirectCost(new BudgetDecimal(0));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                final BudgetModular modular2 = new BudgetModular();
                modular2.setTotalDirectCost(BudgetDecimal.ZERO);
                period2.setBudgetModular(modular2);
                periods.add(period2);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);

        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
        Assert.assertEquals("Only one error should have been produced, count "
            + GlobalVariables.getMessageMap().getErrorMessages().size(), 1, GlobalVariables.getMessageMap().getErrorMessages().size());

        GlobalVariables.getMessageMap().clearErrorMessages();
        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.TWO_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
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
                modular1.setTotalDirectCost(new BudgetDecimal(1));
                period1.setBudgetModular(modular1);
                periods.add(period1);

                final BudgetPeriod period2 = new BudgetPeriod();
                final BudgetModular modular2 = new BudgetModular();
                modular2.setTotalDirectCost(BudgetDecimal.ZERO);
                period2.setBudgetModular(modular2);
                periods.add(period2);

                doc.getBudget().setBudgetPeriods(periods);
                return doc;
            }
        };

        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        BudgetModularTotalDirectCostRule rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        Set<String> warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertEquals("Only one warning should have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));

        
        this.pdDocument.setBudgetDocumentVersions(BudgetModularTotalDirectCostRuleTest.ONE_COMPLETE);
        rule = new BudgetModularTotalDirectCostRule(this.configService, service, this.paramService);
        warnings = new HashSet<String>();
        rule.validateTotalDirectCost(this.pdDocument, true, warnings);
        Assert.assertTrue("The validation should not have produced any errors " + GlobalVariables.getMessageMap(), GlobalVariables.getMessageMap().hasNoErrors());
        Assert.assertEquals("Only one warning shoudld have been produced, count "
            + warnings.size(), 1, warnings.size());
        Assert.assertTrue("The warning was incorrect, warning: "
            + warnings, warnings.contains(getWarning(rule)));
    }
}
