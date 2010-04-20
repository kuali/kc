/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

public class AwardAmountInfoServiceImpl implements AwardAmountInfoService {

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
     */
    @SuppressWarnings("unchecked")
    public AwardAmountInfo fetchLastAwardAmountInfoForAwardVersionAndFinalizedTandMDocumentNumber(Award award) throws WorkflowException {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("awardNumber", award.getAwardNumber());
        fieldValues.put("sequenceNumber", award.getSequenceNumber());
        List<AwardAmountInfo> awardAmountInfos = 
            (List<AwardAmountInfo>)getBusinessObjectService().findMatchingOrderBy(AwardAmountInfo.class, fieldValues, "awardAmountInfoId", true);
        List<AwardAmountInfo> validAwardAmountInfos = new ArrayList<AwardAmountInfo>();
        //add this check for initial award creation.....the Award Amount Info object has not been persisted yet.
        if(awardAmountInfos.size() == 0) {
            return award.getAwardAmountInfos().get(0);
        }else {
            for(AwardAmountInfo aai : awardAmountInfos) {
                if(aai.getTimeAndMoneyDocumentNumber() == null) {
                    validAwardAmountInfos.add(aai);
                }else {
                    Map<String, Object> fieldValues1 = new HashMap<String, Object>();
                    fieldValues1.put("documentNumber", aai.getTimeAndMoneyDocumentNumber());
                    List<TimeAndMoneyDocument> timeAndMoneyDocuments =
                        (List<TimeAndMoneyDocument>)getBusinessObjectService().findMatching(TimeAndMoneyDocument.class, fieldValues1);
                    TimeAndMoneyDocument timeAndMoneyDocument = 
                        (TimeAndMoneyDocument)getDocumentService().getByDocumentHeaderId(timeAndMoneyDocuments.get(0).getDocumentHeader().getDocumentNumber());
                    if(timeAndMoneyDocument.getDocumentHeader().hasWorkflowDocument()) {
                        if(timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().stateIsFinal()) {
                            validAwardAmountInfos.add(aai);
                        }
                    }
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
