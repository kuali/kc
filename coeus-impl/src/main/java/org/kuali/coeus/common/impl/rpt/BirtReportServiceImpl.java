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
package org.kuali.coeus.common.impl.rpt;

import org.kuali.coeus.common.impl.rpt.cust.CustReportDetails;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("birtReportService")
public class BirtReportServiceImpl implements BirtReportService{

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    private BirtHelper birtHelper;

    /**
     * Fetch input parameters from  template.
     */ 
    public List<BirtParameterBean> getInputParametersFromTemplateFile(String reportId) throws Exception {
        
        birtHelper = new BirtHelper();
        InputStream reportDesignInputStream = getReportDesignFileStream(reportId);
        return birtHelper.getParameters(reportDesignInputStream);
    }
    
    /**
     * Generate ReportDesignFileStream.
     */
    public InputStream getReportDesignFileStream(String reportId){
        
        CustReportDetails custReportDetails;
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("reportId", reportId);
        custReportDetails = businessObjectService.findByPrimaryKey(CustReportDetails.class, primaryKeys);
        return new ByteArrayInputStream(custReportDetails.getAttachmentContent());
    }
    
    /**
     * Fetch reports for which the user has permission.
     */
    public List<CustReportDetails> getReports() {

        String principalId = globalVariableService.getUserSession().getPrincipalId();
        String departmentCode = globalVariableService.getUserSession().getPerson().getPrimaryDepartmentCode();
        List<CustReportDetails> custReportDetailsList = (List<CustReportDetails>) getBusinessObjectService().findAll(CustReportDetails.class);
        List<CustReportDetails> custReportDetails = new ArrayList<>();
        for (CustReportDetails custReportDetail : custReportDetailsList) {
            if(custReportDetail.getPermissionName() != null) {
                if(custReportDetail.getPermissionName().equalsIgnoreCase(PermissionConstants.RUN_GLOBAL_REPORTS)) {
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

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return unitAuthorizationService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
