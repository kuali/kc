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
package org.kuali.kra.timeandmoney.service.impl;

import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyExistenceService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeAndMoneyExistenceServiceImpl implements TimeAndMoneyExistenceService {

    private KcWorkflowService kraWorkflowService;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;

    public static final String ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST = "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST";

    public boolean validateTimeAndMoneyRule(Award award, String rootAwardNumber) throws WorkflowException {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("rootAwardNumber", rootAwardNumber);
        fieldValues.put("documentStatus", VersionStatus.PENDING.toString());

        BusinessObjectService businessObjectService =  KcServiceLocator.getService(BusinessObjectService.class);

        List<TimeAndMoneyDocument> timeAndMoneyDocuments = (List<TimeAndMoneyDocument>)businessObjectService.findMatching(TimeAndMoneyDocument.class, fieldValues);

        return timeAndMoneyDocuments == null || timeAndMoneyDocuments.isEmpty();
    }
    
    public void addAwardVersionErrorMessage() {
        GlobalVariables.getMessageMap().addToErrorPath("document");
        GlobalVariables.getMessageMap().addToErrorPath("award");
        GlobalVariables.getMessageMap().putError("version", KeyConstants.ERROR_AWARD_CANNOT_BE_VERSIONED);
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        GlobalVariables.getMessageMap().removeFromErrorPath("award");
    }

    /**
     * Gets the kraWorkflowService attribute. 
     * @return Returns the kraWorkflowService.
     */
    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    /**
     * Sets the kraWorkflowService attribute value.
     * @param kraWorkflowService The kraWorkflowService to set.
     */
    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }

    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
