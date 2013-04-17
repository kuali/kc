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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.reporting.BirtHelper;
import org.kuali.kra.reporting.bo.BirtParameterBean;
import org.kuali.kra.reporting.bo.CustReportDetails;
import org.kuali.kra.reporting.service.BirtReportService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class BirtReportServiceImpl implements BirtReportService{
    
    private BusinessObjectService businessObjectService;
    private BirtHelper birtHelper;
    private KraAuthorizationService kraAuthorizationService;
    private UnitAuthorizationService unitAuthorizationService;
    
    public static final String PERMISSION_NAME = "RUN GLOBAL REPORTS";
    public static final String DATA_SOURCE = "org.eclipse.birt.report.data.oda.jdbc";

    /**
     * Fetch input parameters from  template
     * @param reportId
     * @return List of BirtParameterBean instances
     * @throws Exception
     */ 
    public ArrayList<BirtParameterBean> getInputParametersFromTemplateFile(String reportId) throws Exception {
        
        birtHelper = new BirtHelper();
        ArrayList<BirtParameterBean> parameterList = new ArrayList<BirtParameterBean>();
        InputStream reportDesignInputStream = getReportDesignFileStream(reportId);
        parameterList =  birtHelper.getParameters(reportDesignInputStream);
        return parameterList;
    }
    
    /**
     * Generate ReportDesignFileStream
     * @param reportId
     * @return InputStream     
     */
    public InputStream getReportDesignFileStream(String reportId){
        
        CustReportDetails custReportDetails;
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("reportId", reportId);
        custReportDetails = (CustReportDetails) businessObjectService.findByPrimaryKey(CustReportDetails.class, primaryKeys);
        InputStream reportDesignInputStream = new ByteArrayInputStream(custReportDetails.getAttachmentContent());
        return reportDesignInputStream;
    }
    
    /**
     * Fetch reports for which the user has permission
     * @param 
     * @return List of CustReportDetails instances
     */
    public List<CustReportDetails> getReports() {

        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String departmentCode = GlobalVariables.getUserSession().getPerson().getPrimaryDepartmentCode();
        List<CustReportDetails> custReportDetailsList = (List<CustReportDetails>) KraServiceLocator.getService(
                BusinessObjectService.class).findAll(CustReportDetails.class);
        List<CustReportDetails> custReportDetails = new ArrayList<CustReportDetails>();
        for (CustReportDetails custReportDetail : custReportDetailsList) {
            if(custReportDetail.getPermissionName() != null) {
                if(custReportDetail.getPermissionName().equalsIgnoreCase(PERMISSION_NAME)) {
                    boolean hasPermission = getUnitAuthorizationService().hasPermission(principalId, departmentCode,
                            RoleConstants.DEPARTMENT_ROLE_TYPE, custReportDetail.getPermissionName());
                    if (hasPermission) {
                        custReportDetails.add(custReportDetail);
                    }
                }
            }
        }
        return custReportDetails;
    }
  
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return KraServiceLocator.getService(UnitAuthorizationService.class);
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }
}
