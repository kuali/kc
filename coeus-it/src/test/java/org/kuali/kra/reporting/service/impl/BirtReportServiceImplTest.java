/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.reporting.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.reporting.bo.BirtParameterBean;
import org.kuali.kra.reporting.bo.CustReportDetails;
import org.kuali.kra.reporting.service.BirtReportService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
public class BirtReportServiceImplTest extends KcIntegrationTestBase {
    
    private BirtReportService birtReportService;
    private String reportId;
    ArrayList<BirtParameterBean> parameterList;
    List<CustReportDetails> custReportDetails;
    
    @Before
    public void setUp() throws Exception {
        birtReportService = KcServiceLocator.getService(BirtReportService.class);
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testGetReports() {
        custReportDetails =  (List<CustReportDetails>) KNSServiceLocator.getBusinessObjectService().findAll(CustReportDetails.class);
        assertTrue(custReportDetails.size() > 0);
    }
    
    @Test
    public void testGetInputParametersFromTemplateFile() throws Exception{ 
        List<CustReportDetails> custReportDetailsList = (List<CustReportDetails>) KNSServiceLocator.getBusinessObjectService().findAll(CustReportDetails.class);
        reportId = custReportDetailsList.get(0).getReportId().toString();
        parameterList = birtReportService.getInputParametersFromTemplateFile(reportId);
        assertTrue(parameterList.size() > 0);
    }
}
