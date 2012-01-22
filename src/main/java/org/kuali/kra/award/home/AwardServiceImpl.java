/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Commawardy License, Version 2.0 (the "License");
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
package org.kuali.kra.award.home;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/** {@inheritDoc} */
public class AwardServiceImpl implements AwardService {
    
    private static final String AWARD_NUMBER = "awardNumber";
    private static final String AWARD_ID = "awardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;
    private BudgetSummaryService budgetSummaryService;
    private ParameterService parameterService;

    /**
     * Note Awards are ordered by sequenceNumber
     * @see org.kuali.kra.award.home.AwardService#findAwardsForAwardNumber(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Award> findAwardsForAwardNumber(String awardNumber) {
        List<Award> results = new ArrayList<Award>(businessObjectService.findMatchingOrderBy(Award.class, 
                                                                ServiceHelper.getInstance().buildCriteriaMap(AWARD_NUMBER, awardNumber),
                                                                SEQUENCE_NUMBER,
                                                                true));
        return results;
    }    
    
    /** {@inheritDoc} */
    public Award getAward(Long awardId) {
        return awardId != null ? (Award) businessObjectService.findByPrimaryKey(Award.class, 
                                        ServiceHelper.getInstance().buildCriteriaMap(AWARD_ID, awardId)) : null;
    }
    
    /** {@inheritDoc} */
    public Award getAward(String awardId) {
        return awardId != null ? (Award) businessObjectService.findByPrimaryKey(Award.class, 
                                        ServiceHelper.getInstance().buildCriteriaMap(AWARD_ID, awardId)) : null;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public AwardDocument createNewAwardVersion(AwardDocument awardDocument) throws VersionException, WorkflowException {
        Award newVersion = getVersioningService().createNewVersion(awardDocument.getAward());
        
        for (AwardAttachment attach : newVersion.getAwardAttachments()) {
            AwardAttachment orignalAttachment = findMatchingAwardAttachment(awardDocument.getAward().getAwardAttachments(), attach.getFileId());
            attach.setUpdateUser(orignalAttachment.getUpdateUser());
            attach.setUpdateTimestamp(orignalAttachment.getUpdateTimestamp());
            attach.setUpdateUserSet(true);
        }
        
        incrementVersionNumberIfCanceledVersionsExist(newVersion);//Canceled versions retain their own version number.
        newVersion.getFundingProposals().clear();
        AwardDocument newAwardDocument = (AwardDocument) getDocumentService().getNewDocument(AwardDocument.class);
        newAwardDocument.getDocumentHeader().setDocumentDescription(awardDocument.getDocumentHeader().getDocumentDescription());
        newAwardDocument.setAward(newVersion);
        newVersion.setAwardDocument(newAwardDocument);
        newVersion.setAwardTransactionTypeCode(0);
        newVersion.getSyncChanges().clear();
        newVersion.getSyncStatuses().clear();
        newVersion.setSyncChild(false);
        newVersion.setAwardAmountInfos(minimizeAwardAmountInfoCollection(newVersion.getAwardAmountInfos()));
        newVersion.getAwardAmountInfos().get(0).setOriginatingAwardVersion(newVersion.getSequenceNumber());
        newVersion.getAwardAmountInfos().get(0).setTimeAndMoneyDocumentNumber(null);
        return newAwardDocument;
    }   
    
    private AwardAttachment findMatchingAwardAttachment(List<AwardAttachment> originalAwardList, Long currentFileId) throws VersionException {
        for (AwardAttachment attach : originalAwardList) {
            if (attach.getFileId().equals(currentFileId)) {
                return attach;
            }
        }
        throw new VersionException("Unable to find matching attachment.");
    }
    
    protected void incrementVersionNumberIfCanceledVersionsExist(Award award) {
        List<VersionHistory> versionHistory = (List<VersionHistory>) businessObjectService.findMatching(VersionHistory.class, getHashMap(award.getAwardNumber()));
        award.setSequenceNumber(versionHistory.size() + 1);
    }
    
    protected Map<String, String> getHashMap(String awardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("sequenceOwnerVersionNameValue", awardNumber);
        return map;
    }
    
    protected List<AwardAmountInfo> minimizeAwardAmountInfoCollection(List<AwardAmountInfo> awardAmountInfos) {
        List<AwardAmountInfo> returnList = new ArrayList<AwardAmountInfo>();
        returnList.add(awardAmountInfos.get(awardAmountInfos.size() - 1));
        return returnList;
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

    protected VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public void updateAwardSequenceStatus(Award award, VersionStatus status) {
        if (status.equals(VersionStatus.ACTIVE)) {
            archiveCurrentActiveAward(award.getAwardNumber());
        }
        award.setAwardSequenceStatus(status.toString());
        if (award.getAwardDocument() != null) {
            businessObjectService.save(award);
        }
    }
    
    protected void archiveCurrentActiveAward(String awardNumber) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("awardNumber", awardNumber);
        values.put("awardSequenceStatus", VersionStatus.ACTIVE.name());
        Collection<Award> awards = businessObjectService.findMatching(Award.class, values);
        for (Award award : awards) {
            award.setAwardSequenceStatus(VersionStatus.ARCHIVED.name());
            businessObjectService.save(award);
        }
    }
}
