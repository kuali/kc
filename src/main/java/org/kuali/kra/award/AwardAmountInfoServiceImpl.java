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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class AwardAmountInfoServiceImpl implements AwardAmountInfoService {

    private static final Log LOG = LogFactory.getLog(AwardAmountInfoService.class);

    transient BusinessObjectService businessObjectService;
    transient DocumentService documentService;
    
    /**
     * This method fetches the Award Amount Info object that is to be displayed in UI for Award.  
     * The rules are:
     * 1)the Award Amount Infos awardNumber and versionNumber must match the Award BO
     * 2)The Award Amount Infos timeAndMoneyDocumentNumber must be null or from a T&M document that has been finalized
     * Users don't want this data to apply to Award until the T&M document has been approved.
     * @param award
     * @return
     * @throws WorkflowException 
     * @throws WorkflowException 
     */
    @SuppressWarnings("unchecked")
    public AwardAmountInfo fetchLastAwardAmountInfoForAwardVersionAndFinalizedTandMDocumentNumber(Award award) {
        
        List<AwardAmountInfo> validAwardAmountInfos = new ArrayList<AwardAmountInfo>();

        for(AwardAmountInfo aai : award.getAwardAmountInfos()) {
            //the aai needs to be added if it is created on initialization, or in the case of a root node we add a new one for initial money transaction.
            //if an award has been versioned, the initial transaction will be the first index in list.
            if(aai.getTimeAndMoneyDocumentNumber() == null || (aai.getAwardNumber().endsWith("-00001") && 
                    award.getAwardAmountInfos().indexOf(aai) == 1)) {
                validAwardAmountInfos.add(aai);
            }else {
                Map<String, Object> fieldValues1 = new HashMap<String, Object>();
                fieldValues1.put("documentNumber", aai.getTimeAndMoneyDocumentNumber());
                List<TimeAndMoneyDocument> timeAndMoneyDocuments =
                    (List<TimeAndMoneyDocument>)getBusinessObjectService().findMatching(TimeAndMoneyDocument.class, fieldValues1);
                try {
                TimeAndMoneyDocument timeAndMoneyDocument = 
                    (TimeAndMoneyDocument)getDocumentService().getByDocumentHeaderId(timeAndMoneyDocuments.get(0).getDocumentHeader().getDocumentNumber());
                if(timeAndMoneyDocument.getDocumentHeader().hasWorkflowDocument()) {
                    if(timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
                        validAwardAmountInfos.add(aai);
                    }
                }
                } catch (WorkflowException e) {
                    LOG.error(e.getMessage(), e);
                }
        
            }
        }
    return validAwardAmountInfos.get(validAwardAmountInfos.size() -1);
}
    
    public AwardAmountInfo fetchLastAwardAmountInfoForDocNum(Award award, String docNum){
        List<AwardAmountInfo> validAwardAmountInfos = new ArrayList<AwardAmountInfo>();
        int docNumIntValue = Integer.parseInt(docNum.trim());
        
        for(AwardAmountInfo aai : award.getAwardAmountInfos()) {
            if(aai.getTimeAndMoneyDocumentNumber() == null || (aai.getAwardNumber().endsWith("-00001") &&                   
                    award.getAwardAmountInfos().indexOf(aai) == 1)) {
                validAwardAmountInfos.add(aai);
            }else {
                if(Integer.parseInt(aai.getTimeAndMoneyDocumentNumber().trim()) > docNumIntValue) {
                    break;
                }else{
                    validAwardAmountInfos.add(aai);
                }
            }
        }
            return validAwardAmountInfos.get(validAwardAmountInfos.size() -1);
    }
    
    
    /**
     * 
     * @see org.kuali.kra.award.AwardAmountInfoService#fetchAwardAmountInfoWithHighestTransactionId(java.util.List)
     */

    
public AwardAmountInfo fetchAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos) {
        
        return awardAmountInfos.get(awardAmountInfos.size() -1);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.AwardAmountInfoService#fetchIndexOfAwardAmountInfoWithHighestTransactionId(java.util.List)
     */
    public int fetchIndexOfAwardAmountInfoWithHighestTransactionId(List<AwardAmountInfo> awardAmountInfos) {
       
        return awardAmountInfos.size() - 1;
    }
       

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
}
