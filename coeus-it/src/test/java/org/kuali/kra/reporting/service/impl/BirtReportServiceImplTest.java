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
package org.kuali.kra.reporting.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.impl.rpt.BirtParameterBean;
import org.kuali.coeus.common.impl.rpt.cust.CustReportDetails;
import org.kuali.coeus.common.impl.rpt.BirtReportService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.util.List;

import static org.junit.Assert.assertTrue;
public class BirtReportServiceImplTest extends KcIntegrationTestBase {
    
    private BirtReportService birtReportService;
    private String reportId;
    List<BirtParameterBean> parameterList;
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
