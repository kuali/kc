/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.*;

public class AwardServiceImpl implements AwardService {
    
    private static final String AWARD_NUMBER = "awardNumber";
    private static final String AWARD_ID = "awardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;
    private AwardDao awardDao;

    /**
     * Note Awards are ordered by sequenceNumber
     * @see org.kuali.kra.award.home.AwardService#findAwardsForAwardNumber(java.lang.String)
     */
    public List<Award> findAwardsForAwardNumber(String awardNumber) {
        return new ArrayList<Award>(businessObjectService.findMatchingOrderBy(Award.class,
                Collections.singletonMap(AWARD_NUMBER, awardNumber),
                                                                SEQUENCE_NUMBER,
                                                                true));
    }    
    
    @Override
    public Award getAward(Long awardId) {
        return awardId != null ? businessObjectService.findByPrimaryKey(Award.class,
                                        Collections.singletonMap(AWARD_ID, awardId)) : null;
    }
    
    @Override
    public Award getAward(String awardId) {
        return awardId != null ? businessObjectService.findByPrimaryKey(Award.class,
                Collections.singletonMap(AWARD_ID, awardId)) : null;
    }

    @Override
    public AwardDocument createNewAwardVersion(AwardDocument awardDocument) throws VersionException, WorkflowException {
        Award newVersion = getVersioningService().createNewVersion(awardDocument.getAward());
        newVersion.setCurrentVersionBudgets(new ArrayList<AwardBudgetExt>());
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
        newVersion.getAwardAmountInfos().get(0).setSequenceNumber(newVersion.getSequenceNumber());
        
        synchNewCustomAttributes(newVersion, awardDocument.getAward());
        
        return newAwardDocument;
    }   
    
    @Override
    public void synchNewCustomAttributes(Award newAward, Award oldAward) {
        Set<Integer> availableCustomAttributes = new HashSet<>();
        for(AwardCustomData awardCustomData : newAward.getAwardCustomDataList()) {
            availableCustomAttributes.add(awardCustomData.getCustomAttributeId().intValue());
        }
        
        if(oldAward.getAwardDocument() != null) {
            Map<String, CustomAttributeDocument> customAttributeDocuments = oldAward.getAwardDocument().getCustomAttributeDocuments();
            for (Map.Entry<String, CustomAttributeDocument> entry : customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = entry.getValue();
                if(!availableCustomAttributes.contains(customAttributeDocument.getId().intValue())) {
                    AwardCustomData awardCustomData = new AwardCustomData();
                    awardCustomData.setCustomAttributeId(customAttributeDocument.getId());
                    awardCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
                    awardCustomData.setValue(customAttributeDocument.getCustomAttribute().getDefaultValue());
                    awardCustomData.setAward(newAward);
                    newAward.getAwardCustomDataList().add(awardCustomData);
                }
            }
            newAward.getAwardCustomDataList().removeAll(getInactiveCustomDataList(newAward.getAwardCustomDataList(), customAttributeDocuments));
        }
    }
    
    private List<AwardCustomData> getInactiveCustomDataList(List<AwardCustomData> awardCustomDataList, Map<String, CustomAttributeDocument> customAttributeDocuments) {
        List<AwardCustomData> inactiveCustomDataList = new ArrayList<>();
        for(AwardCustomData awardCustomData : awardCustomDataList) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(awardCustomData.getCustomAttributeId().toString());
            if(customAttributeDocument == null || !customAttributeDocument.isActive()) {
                inactiveCustomDataList.add(awardCustomData);
            }
        }
        return inactiveCustomDataList;
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
        Map<String, String> map = new HashMap<>();
        map.put("sequenceOwnerVersionNameValue", awardNumber);
        return map;
    }
    
    protected List<AwardAmountInfo> minimizeAwardAmountInfoCollection(List<AwardAmountInfo> awardAmountInfos) {
        List<AwardAmountInfo> returnList = new ArrayList<>();
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
        Map<String, Object> values = new HashMap<>();
        values.put("awardNumber", awardNumber);
        values.put("awardSequenceStatus", VersionStatus.ACTIVE.name());
        Collection<Award> awards = businessObjectService.findMatching(Award.class, values);
        for (Award award : awards) {
            award.setAwardSequenceStatus(VersionStatus.ARCHIVED.name());
            award.setAllowUpdateTimestampToBeReset(false);
            businessObjectService.save(award);
        }
    }

    @Override
    public Award getActiveOrNewestAward(String awardNumber) {
        List<VersionHistory> versions = getVersionHistoryService().loadVersionHistory(Award.class, awardNumber);
        VersionHistory newest = null;
        for (VersionHistory version: versions) {
            if (version.getStatus() == VersionStatus.ACTIVE) {
                newest = version;
            } else if (newest == null || (version.getStatus() != VersionStatus.CANCELED && version.getSequenceOwnerSequenceNumber() > newest.getSequenceOwnerSequenceNumber())) {
                newest = version;
            }  
        }
        if (newest != null) {
            return (Award) newest.getSequenceOwner();
        } else {
            return null;
        }
        
    }

    @Override
    public Award getAwardAssociatedWithDocument(String docNumber) {
        Map<String, Object> values = new HashMap<>();
        values.put("documentNumber", docNumber);
        List<Award> awards = (List<Award>) businessObjectService.findMatching(Award.class, values);
        return awards.get(0);
    }

    @Override
    public String getAwardNumber(Long awardId) {
        return awardDao.getAwardNumber(awardId);
    }

    /**
     * Defer to the DAO
     * @param fieldValues a Map of criteria to find matching awards for
     * @return a limited result Collection of matching Awards
     */
    @Override
    public Collection<Award> retrieveAwardsByCriteria(Map<String, Object> fieldValues) {
        return getAwardDao().retrieveAwardsByCriteria(fieldValues);
    }

    public AwardDao getAwardDao() {
        return awardDao;
    }

    public void setAwardDao(AwardDao awardDao) {
        this.awardDao = awardDao;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
