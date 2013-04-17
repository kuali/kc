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
package org.kuali.kra.reporting.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.kuali.kra.reporting.bo.BirtParameterBean;
import org.kuali.kra.reporting.bo.CustReportDetails;


public interface BirtReportService {
    
    /**
    * Fetch input parameters from  template
    * @param reportId
    * @return List of BirtParameterBean instances
    * @throws Exception
    */    
    public ArrayList<BirtParameterBean> getInputParametersFromTemplateFile(String reportId) throws Exception;

    /**
     * Generate ReportDesignFileStream
     * @param reportId
     * @return InputStream
     */
    public InputStream getReportDesignFileStream(String reportId);

    /**
     * Fetch reports for which the user has permission
     * @param 
     * @return List of CustReportDetails instances
     */
    public List<CustReportDetails> getReports();
    
}
