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
package org.kuali.coeus.common.impl;

import java.util.*;

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

        Collections.sort(dataValidationItems, (o1, o2) -> o1.getArea().compareTo(o2.getArea()));
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
