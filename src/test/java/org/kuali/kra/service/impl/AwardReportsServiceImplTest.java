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
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.service.AwardReportsService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class tests the methods in AwardReportsServiceImpl service method.
 */
@RunWith(JMock.class)
public class AwardReportsServiceImplTest extends AwardReportsServiceImpl{

    private static final String P_AND_I_PARAM = "6";
    public static final String MOCK_EXPECTED_STRING = "1;test1,2;test2,3;test3,4;test4,5;test5";
    public static final int MOCK_EXPECTED_NUMBER_OF_NEW_AWARD_REPORT_TERM_OBJECTS = 5;
    public static final int MOCK_EXPECTED_NUMBER_OF_NEW_AWARD_REPORT_TERM_RECIPIENT_OBJECTS = 1;
    public static final String DUMMY_REPORT_CLASS_CODE = "1";
    public static final String DUMMY_REPORT_CODE = "1";
    public static final String DUMMY_FREQUNCY_CODE = "1";
    
    AwardReportsService awardReportsService;
    List<KeyValue> KeyValueList;
    Map<String, Object> hashMap;
    Award award;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        award = new Award();
        awardReportsService = new AwardReportsServiceImpl();
        KeyValueList = new ArrayList<KeyValue>();
        hashMap = new HashMap<String, Object>();
        KeyValueList.add(new ConcreteKeyValue("1", "test1"));
        KeyValueList.add(new ConcreteKeyValue("2", "test2"));
        KeyValueList.add(new ConcreteKeyValue("3", "test3"));
        KeyValueList.add(new ConcreteKeyValue("4", "test4"));
        KeyValueList.add(new ConcreteKeyValue("5", "test5"));
        award.getAwardReportTermItems().add(new AwardReportTerm());
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
                service.processKeyValueList(KeyValueList));
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
        service.addEmptyNewAwardReportTerms(hashMap, KeyValueList);
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
                 public List<KeyValue> getKeyValues() {
                     return KeyValueList;
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
                 public List<KeyValue> getKeyValues() {
                    return KeyValueList;
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
                 public List<KeyValue> getKeyValues() {
                    return KeyValueList;
                  }
               };
            }
         };
         
         Assert.assertEquals(MOCK_EXPECTED_STRING,service.getFrequencyBaseCodes(DUMMY_FREQUNCY_CODE));                 
    }
    
    @Test
    public final void testSetReportClassForPaymentsAndInvoicesSubPanelSuccess(){
        AwardReportsServiceImpl service = new AwardReportsServiceImpl();
        
        final ParameterService parameterService = context.mock(ParameterService.class);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
              
        context.checking(new Expectations() {{
            one(parameterService).getParameterValueAsString(AwardDocument.class
                    ,KeyConstants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES);will(returnValue(P_AND_I_PARAM));
        }});
        
        final Map<String, String> primaryKeyField = new HashMap<String, String>();
        final ReportClass reportClass = new ReportClass();
        primaryKeyField.put(REPORT_CLASS_CODE_FIELD, P_AND_I_PARAM);
        
        context.checking(new Expectations() {{
            one(businessObjectService).findByPrimaryKey(ReportClass.class, primaryKeyField);will(returnValue(reportClass));
        }});

        service.setParameterService(parameterService);
        service.setBusinessObjectService(businessObjectService);
        
        service.setReportClassForPaymentsAndInvoicesSubPanel(hashMap);
        
        Assert.assertTrue(hashMap.containsKey(Constants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL));        
    }
}
