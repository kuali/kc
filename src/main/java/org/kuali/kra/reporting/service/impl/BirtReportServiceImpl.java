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
package org.kuali.kra.reporting.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.reporting.BirtHelper;
import org.kuali.kra.reporting.bo.BirtParameterBean;
import org.kuali.kra.reporting.bo.CustReportDetails;
import org.kuali.kra.reporting.service.BirtReportService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.framework.persistence.jdbc.datasource.XAPoolDataSource;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class BirtReportServiceImpl implements BirtReportService{
    
    private BusinessObjectService businessObjectService;
    private BirtHelper birtHelper;
    private KraAuthorizationService kraAuthorizationService;
    private UnitAuthorizationService unitAuthorizationService;

    @Override
public ArrayList<BirtParameterBean> getInputParametersFromTemplateFile(String reportId) throws Exception {
        
        birtHelper = new BirtHelper();
        ArrayList<BirtParameterBean> parameterList = new ArrayList<BirtParameterBean>();
        InputStream reportDesignInputStream = getReportDesignFileStream(reportId);
        parameterList =  birtHelper.getParameters(reportDesignInputStream);
        return parameterList;
        
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public InputStream getReportDesignFileStream(String reportId){
        
        CustReportDetails custReportDetails;
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("reportId", reportId);
        custReportDetails = (CustReportDetails) businessObjectService.findByPrimaryKey(CustReportDetails.class, primaryKeys);
        InputStream reportDesignInputStream = new ByteArrayInputStream(custReportDetails.getAttachmentContent());
        return reportDesignInputStream;
    }
    
    public List<CustReportDetails> getReportDetails() {

        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String departmentCode = GlobalVariables.getUserSession().getPerson().getPrimaryDepartmentCode();
        List<CustReportDetails> custreportDetailsList = (List<CustReportDetails>) KraServiceLocator.getService(
                BusinessObjectService.class).findAll(CustReportDetails.class);
        List<CustReportDetails> custReportDetails = new ArrayList<CustReportDetails>();
        for (CustReportDetails custReportDetail : custreportDetailsList) {
            boolean hasPermission = getUnitAuthorizationService().hasPermission(principalId, departmentCode,
                    RoleConstants.DEPARTMENT_ROLE_TYPE, custReportDetail.getRightRequired());
            if (hasPermission) {
                custReportDetails.add(custReportDetail);
            }
        }
        return custReportDetails;
    }
    
    @Override
    public IReportRunnable buildDataSource(IReportRunnable iReportRunnable) throws SemanticException, SQLException {
        ElementFactory designFactory = null;
        
        ConfigurationService configurationService = KraServiceLocator.getService(ConfigurationService.class);
        String odaURL = configurationService.getPropertyValueAsString(Constants.ODA_URL);
        String odaUser =  configurationService.getPropertyValueAsString(Constants.ODA_USER);
        String odaPassword =  configurationService.getPropertyValueAsString(Constants.ODA_PASSWORD);
        
        ReportDesignHandle reportDesignHandle = (ReportDesignHandle) iReportRunnable.getDesignHandle();
        reportDesignHandle.getElementFactory();        
        designFactory = reportDesignHandle.getElementFactory();
        OdaDataSourceHandle odaDataSourceHandle = designFactory.newOdaDataSource("Data Source", "org.eclipse.birt.report.data.oda.jdbc");
        XAPoolDataSource xAPoolDataSource =  KraServiceLocator.getService("dataSourceXAPool");
        
        String odaDriverClassName = xAPoolDataSource.getDriverClassName();
        odaDataSourceHandle.setProperty("odaDriverClass", odaDriverClassName);
        odaDataSourceHandle.setProperty("odaURL", odaURL);
        odaDataSourceHandle.setProperty("odaUser", odaUser);
        odaDataSourceHandle.setProperty("odaPassword", odaPassword);
        reportDesignHandle.getDataSources().add(odaDataSourceHandle);
        iReportRunnable.setDesignHandle(reportDesignHandle);
        
        return iReportRunnable;
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
