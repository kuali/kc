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
package org.kuali.kra.subaward.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.KualiDecimal;

public class SubAwardServiceImpl implements SubAwardService{


    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private DocumentService documentService;
    private SequenceAccessorService sequenceAccessorService;
    
    public SubAwardDocument createNewSubAwardVersion(SubAwardDocument subAwardDocument) throws VersionException, WorkflowException {

        SubAward newVersion = getVersioningService().createNewVersion(subAwardDocument.getSubAward());

        SubAwardDocument newSubAwardDocument = (SubAwardDocument) getDocumentService().getNewDocument(SubAwardDocument.class);
        newSubAwardDocument.getDocumentHeader().setDocumentDescription(subAwardDocument.getDocumentHeader().getDocumentDescription());
        newSubAwardDocument.setSubAward(newVersion);
        newVersion.setSubAwardDocument(newSubAwardDocument);
        return newSubAwardDocument;
    }

    protected void incrementVersionNumberIfCanceledVersionsExist(SubAward subAward) {
        List<VersionHistory> versionHistory = (List<VersionHistory>) getBusinessObjectService().findMatching(VersionHistory.class, getHashMap(Integer.toString(subAward.getSubAwardId())));
        subAward.setSequenceNumber(versionHistory.size() + 1);
    }
    protected Map<String, String> getHashMap(String subAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("sequenceOwnerVersionNameValue", subAwardNumber);
        return map;
    }
    protected VersioningService getVersioningService() {
        return versioningService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    @Override
    public void updateSubAwardSequenceStatus(SubAward subAward, VersionStatus status) {
        if (status.equals(VersionStatus.ACTIVE)) {
            archiveCurrentActiveSubAward(subAward.getSubAwardId());
        }
        subAward.setSubAwardSequenceStatus(status.toString());
        getBusinessObjectService().save(subAward);   
    }
    protected void archiveCurrentActiveSubAward(Integer subAwardId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("subAwardId", Integer.toString(subAwardId));
        values.put("subAwardSequenceStatus", VersionStatus.ACTIVE.name());
        Collection<SubAward> subAwards = getBusinessObjectService().findMatching(SubAward.class, values);
        for (SubAward subAward : subAwards) {
            subAward.setSubAwardSequenceStatus(VersionStatus.ARCHIVED.name());
            getBusinessObjectService().save(subAward);
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    /** {@inheritDoc} */
    public String getNextSubAwardCode() {
        Long nextAwardNumber = sequenceAccessorService.getNextAvailableSequenceNumber(Constants.SUBAWARD_SEQUENCE_SUBAWARD_CODE);
        
        return nextAwardNumber.toString();
    }
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    public SubAward getAmountInfo(SubAward subAward){

        List<SubAwardAmountInfo> subAwardAmountInfoList = subAward.getSubAwardAmountInfoList();  
        List<SubAwardAmountReleased> subAwardAmountReleasedList = subAward.getSubAwardAmountReleasedList();
        KualiDecimal totalOblicatedAmount = new KualiDecimal(0.00); ;
        KualiDecimal totalAnticipatedAmount = new KualiDecimal(0.00) ;
        KualiDecimal totalAmountReleased = new KualiDecimal(0.00) ;
        if(subAwardAmountInfoList!=null){
            for(SubAwardAmountInfo subAwardAmountInfo : subAwardAmountInfoList){
                if(subAwardAmountInfo.getObligatedChange()!=null){
                   
                    subAward.setTotalObligatedAmount(totalOblicatedAmount.add(subAwardAmountInfo.getObligatedChange()));
                    totalOblicatedAmount = subAward.getTotalObligatedAmount();
                }
                if(subAwardAmountInfo.getAnticipatedChange()!=null){
                    subAward.setTotalAnticipatedAmount(totalAnticipatedAmount.add(subAwardAmountInfo.getAnticipatedChange()));
                    totalAnticipatedAmount = subAward.getTotalAnticipatedAmount();
                }
            }
            for(SubAwardAmountReleased subAwardAmountReleased:subAwardAmountReleasedList){
                
                if(subAwardAmountReleased.getAmountReleased()!=null){
                    subAward.setTotalAmountReleased(totalAmountReleased.add(subAwardAmountReleased.getAmountReleased()));
                    totalAmountReleased = subAward.getTotalAmountReleased();
                }
            }
        }
        subAward.setTotalObligatedAmount(totalOblicatedAmount);
        subAward.setTotalAnticipatedAmount(totalAnticipatedAmount);
        subAward.setTotalAmountReleased(totalAmountReleased);
        subAward.setTotalAvailableAmount(totalOblicatedAmount.subtract(totalAmountReleased));
        return subAward;
    }
}
