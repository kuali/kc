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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.infrastructure.AwardRoleConstants;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

public class AwardServiceImpl implements AwardService {

    private static final String AWARD_NUMBER = "awardNumber";
    private static final String AWARD_ID = "awardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    public static final String SEQUENCE_OWNER_VERSION_NAME_VALUE = "sequenceOwnerVersionNameValue";
    public static final String AWARD_SEQUENCE_STATUS = "awardSequenceStatus";
    public static final String DOCUMENT_NUMBER = "documentNumber";

    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;
    private AwardDao awardDao;
    private SponsorHierarchyService sponsorHierarchyService;
    private AwardNumberService awardNumberService;

    @Autowired
    @Qualifier("awardVersionService")
    private AwardVersionService awardVersionService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    public List<Award> findAwardsForAwardNumber(String awardNumber) {
        return new ArrayList<>(businessObjectService.findMatchingOrderBy(Award.class,
                Collections.singletonMap(AWARD_NUMBER, awardNumber),
                                                                SEQUENCE_NUMBER,
                                                                true));
    }

    public void createInitialAwardUsers(Award award) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
        SystemAuthorizationService systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        if (!systemAuthorizationService.hasRole(userId, award.getNamespace(), AwardRoleConstants.AWARD_MODIFIER.getAwardRole())) {
            kraAuthService.addDocumentLevelRole(userId, AwardRoleConstants.AWARD_MODIFIER.getAwardRole(), award);
        }
    }


    protected void setTotalsOnAward(final Award award) {
        final AwardAmountInfo aai = award.getLastAwardAmountInfo();
        if (aai == null) {
            return;
        }

        final ScaleTwoDecimal obligatedDirectTotal     = aai.getObligatedTotalDirect() != null ? aai.getObligatedTotalDirect() : new ScaleTwoDecimal(0);
        final ScaleTwoDecimal obligatedIndirectTotal   = aai.getObligatedTotalIndirect() != null ? aai.getObligatedTotalIndirect() : new ScaleTwoDecimal(0);
        final ScaleTwoDecimal anticipatedDirectTotal   = aai.getAnticipatedTotalDirect() != null ? aai.getAnticipatedTotalDirect() : new ScaleTwoDecimal(0);
        final ScaleTwoDecimal anticipatedIndirectTotal = aai.getAnticipatedTotalIndirect() != null ? aai.getAnticipatedTotalIndirect() : new ScaleTwoDecimal(0);

        aai.setAmountObligatedToDate(obligatedDirectTotal.add(obligatedIndirectTotal));
        aai.setAnticipatedTotalAmount(anticipatedDirectTotal.add(anticipatedIndirectTotal));
    }

    protected Award getActiveAwardVersion(Award award) {
        return awardVersionService.getActiveAwardVersion(award.getAwardNumber());
    }

    public boolean isDirectIndirectViewEnabled() {
        boolean returnValue = false;
        String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")) {
            returnValue = true;
        }
        return returnValue;
    }

    protected AwardAmountInfo getPreviousAwardAmountInfo(Award award) {
        int awardAmountInfosSize = award.getAwardAmountInfos().size();
        if (awardAmountInfosSize > 1) {
            int previousAwardAmountInfoIndex = awardAmountInfosSize - 2;
            return award.getAwardAmountInfos().get(previousAwardAmountInfoIndex);
        } else {
            Award oldAward = getActiveAwardVersion(award);
            return oldAward != null ? oldAward.getLastAwardAmountInfo() : null;
        }
    }

    public void updateCurrentAwardAmountInfo(Award award) {
        if (award.getAwardNumber().endsWith(Award.DEFAULT_AWARD_NUMBER)
                || award.getAwardNumber().endsWith(AwardConstants.ROOT_AWARD_SUFFIX)) {
            AwardAmountInfo currentAwardAmountInfo = award.getLastAwardAmountInfo();
            AwardAmountInfo previousAwardAmountInfo = getPreviousAwardAmountInfo(award);

            if (isDirectIndirectViewEnabled()) {
                setTotalsOnAward(award);
            }

            if (previousAwardAmountInfo != null) {
                currentAwardAmountInfo.setObligatedChange(currentAwardAmountInfo.getAmountObligatedToDate().subtract(previousAwardAmountInfo.getAmountObligatedToDate()));
                currentAwardAmountInfo.setObligatedChangeDirect(currentAwardAmountInfo.getObligatedTotalDirect().subtract(previousAwardAmountInfo.getObligatedTotalDirect()));
                currentAwardAmountInfo.setObligatedChangeIndirect(currentAwardAmountInfo.getObligatedTotalIndirect().subtract(previousAwardAmountInfo.getObligatedTotalIndirect()));
                currentAwardAmountInfo.setAnticipatedChange(currentAwardAmountInfo.getAnticipatedTotalAmount().subtract(previousAwardAmountInfo.getAnticipatedTotalAmount()));
                currentAwardAmountInfo.setAnticipatedChangeDirect(currentAwardAmountInfo.getAnticipatedTotalDirect().subtract(previousAwardAmountInfo.getAnticipatedTotalDirect()));
                currentAwardAmountInfo.setAnticipatedChangeIndirect(currentAwardAmountInfo.getAnticipatedTotalIndirect().subtract(previousAwardAmountInfo.getAnticipatedTotalIndirect()));
                currentAwardAmountInfo.setObliDistributableAmount(previousAwardAmountInfo.getObliDistributableAmount().add(currentAwardAmountInfo.getObligatedChange()));
                currentAwardAmountInfo.setAntDistributableAmount(previousAwardAmountInfo.getAntDistributableAmount().add(currentAwardAmountInfo.getAnticipatedChange()));
            } else {
                currentAwardAmountInfo.setObligatedChange(currentAwardAmountInfo.getAmountObligatedToDate());
                currentAwardAmountInfo.setObligatedChangeDirect(currentAwardAmountInfo.getObligatedTotalDirect());
                currentAwardAmountInfo.setObligatedChangeIndirect(currentAwardAmountInfo.getObligatedTotalIndirect());
                currentAwardAmountInfo.setAnticipatedChange(currentAwardAmountInfo.getAnticipatedTotalAmount());
                currentAwardAmountInfo.setAnticipatedChangeDirect(currentAwardAmountInfo.getAnticipatedTotalDirect());
                currentAwardAmountInfo.setAnticipatedChangeIndirect(currentAwardAmountInfo.getAnticipatedTotalIndirect());
                currentAwardAmountInfo.setObliDistributableAmount(currentAwardAmountInfo.getAmountObligatedToDate());
                currentAwardAmountInfo.setAntDistributableAmount(currentAwardAmountInfo.getAnticipatedTotalAmount());
            }
        }
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
        newVersion.setCurrentVersionBudgets(new ArrayList<>());
        AwardDocument newAwardDocument = generateAndPopulateAwardDocument(awardDocument, newVersion);

        return newAwardDocument;
    }

    public void checkAwardNumber(Award award) {
        if (Award.DEFAULT_AWARD_NUMBER.equals(award.getAwardNumber())) {
            AwardNumberService awardNumberService = getAwardNumberService();
            String awardNumber = awardNumberService.getNextAwardNumber();
            award.setAwardNumber(awardNumber);
        }
        if (Award.DEFAULT_AWARD_NUMBER.equals(award.getAwardAmountInfos().get(0).getAwardNumber())) {
            award.getAwardAmountInfos().get(0).setAwardNumber(award.getAwardNumber());
        }
        award.getAwardApprovedSubawards().stream()
                .filter(approvedSubaward -> Award.DEFAULT_AWARD_NUMBER.equals(approvedSubaward.getAwardNumber()))
                .forEach(approvedSubaward -> approvedSubaward.setAwardNumber(award.getAwardNumber()));

        for(AwardComment comment : award.getAwardComments()) {
            comment.setAward(award);
        }
        for(AwardCustomData customData : award.getAwardCustomDataList()) {
            customData.setAward(award);
        }
    }

    public AwardDocument generateAndPopulateAwardDocument(AwardDocument oldAwardDocument, Award newVersion) throws WorkflowException {
        fixAttachments(oldAwardDocument, newVersion);

        incrementVersionNumberIfCanceledVersionsExist(newVersion);//Canceled versions retain their own version number.
        newVersion.getFundingProposals().clear();
        AwardDocument newAwardDocument = (AwardDocument) getDocumentService().getNewDocument(AwardDocument.class);
        newAwardDocument.getDocumentHeader().setDocumentDescription(oldAwardDocument.getDocumentHeader().getDocumentDescription());
        newAwardDocument.setAward(newVersion);
        newAwardDocument.setDocumentNextvalues(oldAwardDocument.getDocumentNextvalues());
        newAwardDocument.getDocumentNextvalues().forEach(nextValue ->  nextValue.setDocumentKey(newAwardDocument.getDocumentNumber()));
        newVersion.setAwardDocument(newAwardDocument);
        newVersion.setAwardTransactionTypeCode(0);
        newVersion.getSyncChanges().clear();
        newVersion.getSyncStatuses().clear();
        newVersion.setSyncChild(false);
        newVersion.setAwardAmountInfos(minimizeAwardAmountInfoCollection(newVersion.getAwardAmountInfos()));
        newVersion.getAwardAmountInfos().get(0).setOriginatingAwardVersion(newVersion.getSequenceNumber());
        newVersion.getAwardAmountInfos().get(0).setTimeAndMoneyDocumentNumber(null);
        newVersion.getAwardAmountInfos().get(0).setSequenceNumber(newVersion.getSequenceNumber());

        synchNewCustomAttributes(newVersion, oldAwardDocument.getAward());
        return newAwardDocument;
    }

    public void fixAttachments(AwardDocument awardDocument, Award newVersion) {
        for (AwardAttachment attach : newVersion.getAwardAttachments()) {
            AwardAttachment orignalAttachment = findMatchingAwardAttachment(awardDocument.getAward().getAwardAttachments(), attach.getFileId());
            attach.setUpdateUser(orignalAttachment.getUpdateUser());
            attach.setUpdateTimestamp(orignalAttachment.getUpdateTimestamp());
            attach.setUpdateUserSet(true);
        }
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
        map.put(SEQUENCE_OWNER_VERSION_NAME_VALUE, awardNumber);
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
        values.put(AWARD_NUMBER, awardNumber);
        values.put(AWARD_SEQUENCE_STATUS, VersionStatus.ACTIVE.name());
        Collection<Award> awards = businessObjectService.findMatching(Award.class, values);
        for (Award award : awards) {
            award.setAwardSequenceStatus(VersionStatus.ARCHIVED.name());
            award.setAllowUpdateFieldsToBeReset(false);
            businessObjectService.save(award);
        }
    }

    public String getRootAwardNumber(String awardNumber) {
        return awardNumber.substring(0, 6) + "-00001";
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
        values.put(DOCUMENT_NUMBER, docNumber);
        List<Award> awards = (List<Award>) businessObjectService.findMatching(Award.class, values);
        return awards.get(0);
    }

    @Override
    public String getAwardNumber(Long awardId) {
        return awardDao.getAwardNumber(awardId);
    }

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

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public AwardNumberService getAwardNumberService() {
        return awardNumberService;
    }

    public void setAwardNumberService(AwardNumberService awardNumberService) {
        this.awardNumberService = awardNumberService;
    }

    public void setAwardVersionService(AwardVersionService awardVersionService) {
        this.awardVersionService = awardVersionService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
