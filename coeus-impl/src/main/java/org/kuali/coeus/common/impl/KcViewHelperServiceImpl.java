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
package org.kuali.coeus.common.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.KRADUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class KcViewHelperServiceImpl extends ViewHelperServiceImpl {
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    public List<DataValidationItem> populateDataValidation() {
        List<DataValidationItem> dataValidationItems = new ArrayList<DataValidationItem>();
        for (Map.Entry<String,AuditCluster> entry : getGlobalVariableService().getAuditErrorMap().entrySet()) {
            AuditCluster auditCluster = entry.getValue();
            List<AuditError> auditErrors = auditCluster.getAuditErrorList();
            String areaName = StringUtils.substringBefore(auditCluster.getLabel(),".");
            String sectionName = StringUtils.substringAfter(auditCluster.getLabel(),".");
            for (AuditError auditError : auditErrors) {
            	DataValidationItem dataValidationItem = new DataValidationItem();
                String pageId = StringUtils.substringBefore(auditError.getLink(),".");
                String sectionId = StringUtils.substringAfter(auditError.getLink(),".");
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setErrorKey(auditError.getMessageKey());
                errorMessage.setMessageParameters(auditError.getParams());

                dataValidationItem.setArea(areaName);
                dataValidationItem.setSection(sectionName);
                dataValidationItem.setDescription(KRADUtils.getMessageText(errorMessage, false));
                dataValidationItem.setSeverity(auditCluster.getCategory());
                dataValidationItem.setNavigateToPageId(pageId);
                dataValidationItem.setNavigateToSectionId(sectionId);

                dataValidationItems.add(dataValidationItem);
            }
        }
        return dataValidationItems;
    }

    public String getErrorCssClass(String severity) {
        if (severity.endsWith(Constants.AUDIT_ERRORS)) {
            return "label-danger";
        } else if (severity.equals(Constants.AUDIT_WARNINGS)) {
            return "label-warning";
        }
        return "label-info";
    }
   
	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

}
