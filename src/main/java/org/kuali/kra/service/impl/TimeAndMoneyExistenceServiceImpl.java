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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.TimeAndMoneyExistenceService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class TimeAndMoneyExistenceServiceImpl implements TimeAndMoneyExistenceService {

    private KraWorkflowService kraWorkflowService;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    
    public boolean validateTimeAndMoneyRule(Award award, Map<String, AwardHierarchy> awardHierarchyNodes) throws WorkflowException {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        Boolean timeAndMoneyDocumentDoesNotExist = Boolean.TRUE;
        String rootAwardNumber = awardHierarchyNodes.get(award.getAwardNumber()).getRootAwardNumber();
        fieldValues.put("rootAwardNumber", rootAwardNumber);

        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);

        List<TimeAndMoneyDocument> timeAndMoneyDocuments = (List<TimeAndMoneyDocument>)businessObjectService.findMatching(TimeAndMoneyDocument.class, fieldValues);
        TimeAndMoneyDocument timeAndMoneyDocument = null;

        for(TimeAndMoneyDocument t : timeAndMoneyDocuments){
            timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(t.getDocumentNumber());
            timeAndMoneyDocument.setAwardNumber(award.getAwardNumber());
            timeAndMoneyDocument.setAward(award);
            if(!kraWorkflowService.isInWorkflow(timeAndMoneyDocument)){
                timeAndMoneyDocumentDoesNotExist = Boolean.FALSE;
                break;
            }
        }
        return timeAndMoneyDocumentDoesNotExist;
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
    public KraWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    /**
     * Sets the kraWorkflowService attribute value.
     * @param kraWorkflowService The kraWorkflowService to set.
     */
    public void setKraWorkflowService(KraWorkflowService kraWorkflowService) {
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
