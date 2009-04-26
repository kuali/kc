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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.bo.ReportClass;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.service.AwardReportsService;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class tests the methods in AwardReportsServiceImpl service method.
 */
@RunWith(JMock.class)
public class AwardReportsServiceImplTest extends AwardReportsServiceImpl{

    public static final String MOCK_EXPECTED_STRING = "1;test1,2;test2,3;test3,4;test4,5;test5";
    public static final int MOCK_EXPECTED_NUMBER_OF_NEW_AWARD_REPORT_TERM_OBJECTS = 5;
    public static final int MOCK_EXPECTED_NUMBER_OF_NEW_AWARD_REPORT_TERM_RECIPIENT_OBJECTS = 1;
    public static final String DUMMY_REPORT_CLASS_CODE = "1";
    public static final String DUMMY_REPORT_CODE = "1";
    public static final String DUMMY_FREQUNCY_CODE = "1";
    
    AwardReportsService awardReportsService;
    List<KeyLabelPair> keyLabelPairList;
    Map<String, Object> hashMap;
    Award award;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        award = new Award();
        awardReportsService = new AwardReportsServiceImpl();
        keyLabelPairList = new ArrayList<KeyLabelPair>();
        hashMap = new HashMap<String, Object>();
        keyLabelPairList.add(new KeyLabelPair(1, "test1"));
        keyLabelPairList.add(new KeyLabelPair(2, "test2"));
        keyLabelPairList.add(new KeyLabelPair(3, "test3"));
        keyLabelPairList.add(new KeyLabelPair(4, "test4"));
        keyLabelPairList.add(new KeyLabelPair(5, "test5"));
        award.getAwardReportTerms().add(new AwardReportTerm());
    }

    @After
    public void tearDown() throws Exception {
        award = null;
        awardReportsService = null;
        hashMap = null;
    }

    @Test
    public final void testProcessFrequencyBaseCodes() {
        AwardReportsServiceImpl service = new AwardReportsServiceImpl();
        Assert.assertEquals(MOCK_EXPECTED_STRING,
                service.processKeyLabelPairList(keyLabelPairList));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public final void testAddEmptyNewAwardReportTermRecipients() {
        AwardReportsServiceImpl service = new AwardReportsServiceImpl();
        service.addEmptyNewAwardReportTermRecipients(award, hashMap);
        Assert.assertTrue(hashMap.containsKey(Constants.NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        Assert.assertEquals(MOCK_EXPECTED_NUMBER_OF_NEW_AWARD_REPORT_TERM_RECIPIENT_OBJECTS
                , ((List<AwardReportTerm>)hashMap.get(Constants.NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS)).size());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public final void testAddEmptyNewAwardReportTerms() {
        AwardReportsServiceImpl service = new AwardReportsServiceImpl();
        service.addEmptyNewAwardReportTerms(hashMap, keyLabelPairList);
        Assert.assertTrue(hashMap.containsKey(Constants.NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        Assert.assertEquals(MOCK_EXPECTED_NUMBER_OF_NEW_AWARD_REPORT_TERM_OBJECTS
                , ((List<AwardReportTerm>)hashMap.get(Constants.NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS)).size());
    }
    
    @Test
    public final void testAssignReportClassesForPanelHeaderDisplay() {        
        AwardReportsServiceImpl service = new AwardReportsServiceImpl() {
            @Override
            protected ReportClassValuesFinder getReportClassValuesFinder()  {
               return new ReportClassValuesFinder() {
                 @Override
                 public List<KeyLabelPair> getKeyValues() {
                     return keyLabelPairList;
                  }
               };
            }
         };
         
         service.assignReportClassesForPanelHeaderDisplay(hashMap);
         
         Assert.assertTrue(hashMap.containsKey(Constants.REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS));
    }
    
    @Test
    public final void testGetFrequencyCodes(){
        
        AwardReportsServiceImpl service = new AwardReportsServiceImpl() {           
            @Override
            protected FrequencyCodeValuesFinder getFrequencyCodeValuesFinder(String reportClassCode, String reportCode)  {
               return new FrequencyCodeValuesFinder(reportClassCode, reportCode) {
                 @Override
                 public List<KeyLabelPair> getKeyValues() {
                    return keyLabelPairList;
                  }
               };
            }
         };
         Assert.assertEquals(MOCK_EXPECTED_STRING,service.getFrequencyCodes(DUMMY_REPORT_CLASS_CODE, DUMMY_REPORT_CODE));
    }
    
    @Test
    public final void testGetFrequencyBaseCodes(){
        
        AwardReportsServiceImpl service = new AwardReportsServiceImpl() {           
            @Override
            protected FrequencyBaseCodeValuesFinder getFrequencyBaseCodeValuesFinder(String frequencyCode)  {
               return new FrequencyBaseCodeValuesFinder(frequencyCode) {
                 @Override
                 public List<KeyLabelPair> getKeyValues() {
                    return keyLabelPairList;
                  }
               };
            }
         };
         
         Assert.assertEquals(MOCK_EXPECTED_STRING,service.getFrequencyBaseCodes(DUMMY_FREQUNCY_CODE));                 
    }
    
    @Test
    public final void testSetReportClassForPaymentsAndInvoicesSubPanelSuccess(){
        AwardReportsServiceImpl service = new AwardReportsServiceImpl();
        
        final KualiConfigurationService kualiConfigurationService = context.mock(KualiConfigurationService.class);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        
        final Parameter parameter = new Parameter();
        parameter.setParameterName(KeyConstants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES);
        parameter.setParameterValue("6");
        
        context.checking(new Expectations() {{
            one(kualiConfigurationService).getParameter(Constants
                    .PARAMETER_MODULE_AWARD ,Constants.PARAMETER_COMPONENT_DOCUMENT
                    ,KeyConstants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES);will(returnValue(parameter));
        }});
        
        final Map<String, String> primaryKeyField = new HashMap<String, String>();
        final ReportClass reportClass = new ReportClass();
        primaryKeyField.put(REPORT_CLASS_CODE_FIELD,parameter.getParameterValue());
        
        context.checking(new Expectations() {{
            one(businessObjectService).findByPrimaryKey(ReportClass.class, primaryKeyField);will(returnValue(reportClass));
        }});

        service.setKualiConfigurationService(kualiConfigurationService);
        service.setBusinessObjectService(businessObjectService);
        
        service.setReportClassForPaymentsAndInvoicesSubPanel(hashMap);
        
        Assert.assertTrue(hashMap.containsKey(Constants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL));        
    }
}
