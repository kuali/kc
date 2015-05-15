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
package org.kuali.kra.subaward.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.sql.Date;
import java.util.*;

public class SubAwardServiceImpl implements SubAwardService {

    private static final Log LOG = LogFactory.getLog(SubAwardServiceImpl.class);
    public static final String SUB_AWARD_ID = "subAwardId";
    public static final String SUB_AWARD_SEQUENCE_STATUS = "subAwardSequenceStatus";
    public static final String SUBAWARD_FOLLOW_UP = "Subaward Follow Up";
    public static final String AWARD_AWARD_NUMBER = "award.awardNumber";

    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private VersionHistoryService versionHistoryService;
    private DocumentService documentService;
    private SequenceAccessorService sequenceAccessorService;
    private ParameterService parameterService;

    public SubAwardDocument createNewSubAwardVersion(
    SubAwardDocument subAwardDocument) throws
    VersionException, WorkflowException {

        SubAward newVersion = getVersioningService().
        createNewVersion(subAwardDocument.getSubAward());

        SubAwardDocument newSubAwardDocument =
        (SubAwardDocument) getDocumentService().
        getNewDocument(SubAwardDocument.class);
        newSubAwardDocument.getDocumentHeader().
        setDocumentDescription(subAwardDocument.
        getDocumentHeader().getDocumentDescription());
        newSubAwardDocument.setSubAward(newVersion);
        newVersion.setSubAwardDocument(newSubAwardDocument);
        return newSubAwardDocument;
    }

	@Override
    public void updateSubAwardSequenceStatus(
    SubAward subAward, VersionStatus status) {
        if (status.equals(VersionStatus.ACTIVE)) {
            archiveCurrentActiveSubAward(subAward.getSubAwardId());
        }
        subAward.setSubAwardSequenceStatus(status.toString());
       getBusinessObjectService().save(subAward);
    }

    protected void archiveCurrentActiveSubAward(Long subAwardId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put(SUB_AWARD_ID, Long.toString(subAwardId));
        values.put(SUB_AWARD_SEQUENCE_STATUS, VersionStatus.ACTIVE.name());
        Collection<SubAward> subAwards = getBusinessObjectService().
        findMatching(SubAward.class, values);
        for (SubAward subAward : subAwards) {
            subAward.setSubAwardSequenceStatus(VersionStatus.ARCHIVED.name());
            getBusinessObjectService().save(subAward);
        }
    }

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

    @Override
    public String getNextSubAwardCode() {
        Long nextAwardNumber = sequenceAccessorService.
        getNextAvailableSequenceNumber(Constants.SUBAWARD_SEQUENCE_SUBAWARD_CODE, SubAward.class);

        return nextAwardNumber.toString();
    }

    public void setSequenceAccessorService(
    SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public SubAward getAmountInfo(SubAward subAward) {

        List<SubAwardAmountInfo> subAwardAmountInfoList = subAward.getSubAwardAmountInfoList();
        List<SubAwardAmountReleased> subAwardAmountReleasedList = subAward.getSubAwardAmountReleasedList();
        ScaleTwoDecimal totalObligatedAmount = new ScaleTwoDecimal(0.00);
        ScaleTwoDecimal totalAnticipatedAmount = new ScaleTwoDecimal(0.00);
        ScaleTwoDecimal totalAmountReleased = new ScaleTwoDecimal(0.00);
        if (subAwardAmountInfoList != null && subAwardAmountInfoList.size() > 0) {
            for (SubAwardAmountInfo subAwardAmountInfo: subAwardAmountInfoList) {
                if (subAwardAmountInfo.getObligatedChange() != null) {
                    subAward.setTotalObligatedAmount(totalObligatedAmount.add(subAwardAmountInfo.getObligatedChange()));
                    totalObligatedAmount = subAward.getTotalObligatedAmount();
                }
                if (subAwardAmountInfo.getAnticipatedChange() != null) {
                    subAward.setTotalAnticipatedAmount(totalAnticipatedAmount.add(subAwardAmountInfo.getAnticipatedChange()));
                    totalAnticipatedAmount = subAward.getTotalAnticipatedAmount();
                }
                if (subAwardAmountInfo.getModificationEffectiveDate() != null) {
                    subAward.setModificationEffectiveDate(subAwardAmountInfo.getModificationEffectiveDate());
                }
                if (subAwardAmountInfo.getModificationID() != null) {
                    subAward.setModificationId(subAwardAmountInfo.getModificationID());
                }
                if (subAwardAmountInfo.getPeriodofPerformanceStartDate() != null) {
                    subAward.setPerformanceStartDate(subAwardAmountInfo.getPeriodofPerformanceStartDate());
                }
                if (subAwardAmountInfo.getPeriodofPerformanceEndDate() != null) {
                    subAward.setPerformanceEnddate(subAwardAmountInfo.getPeriodofPerformanceEndDate());
                }
            }
            for (SubAwardAmountReleased subAwardAmountReleased: subAwardAmountReleasedList) {

                if (subAwardAmountReleased.getAmountReleased() != null
                        && !(StringUtils.equals(subAwardAmountReleased.getInvoiceStatus(), DocumentStatus.DISAPPROVED.getCode())
                        || StringUtils.equals(subAwardAmountReleased.getInvoiceStatus(), DocumentStatus.CANCELED.getCode())
                        || StringUtils.equals(subAwardAmountReleased.getInvoiceStatus(), DocumentStatus.RECALLED.getCode()))) {
                    subAward.setTotalAmountReleased(totalAmountReleased.add(subAwardAmountReleased.getAmountReleased()));
                    totalAmountReleased = subAward.getTotalAmountReleased();
                }
            }
            SubAwardAmountInfo amountInfo = subAward.getSubAwardAmountInfoList().get(subAward.getSubAwardAmountInfoList().size()-1);
            amountInfo.setAnticipatedAmount(totalAnticipatedAmount);
            amountInfo.setObligatedAmount(totalObligatedAmount);
        }
        subAward.setTotalObligatedAmount(totalObligatedAmount);
        subAward.setTotalAnticipatedAmount(totalAnticipatedAmount);
        subAward.setTotalAmountReleased(totalAmountReleased);
        subAward.setTotalAvailableAmount(totalObligatedAmount.subtract(totalAmountReleased));

        return subAward;
    }

    public String getFollowupDateDefaultLength() {
        return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD,
                Constants.PARAMETER_COMPONENT_DOCUMENT, SUBAWARD_FOLLOW_UP);
    }

    public Date getCalculatedFollowupDate(Date baseDate) {
        Date retDate =
        new Date(DateUtils.addDays(baseDate, getFollowupDateDefaultLengthInDays()).getTime());
        return retDate;
    }
    
    @Override
    public String getCalculatedFollowupDateForAjaxCall(String baseDate) {
        final String empty = "";
        String[] elements = baseDate.split("/");
        if (elements.length == 3) {
            try {
                int month = Integer.parseInt(elements[0]);
                int day = Integer.parseInt(elements[1]);
                int year = Integer.parseInt(elements[2]);
                if (year < 100) {
                    year = year + 2000;
                }
                Date requestedDate = new Date(year, month-1, day-1);
                Date followUpDate = getCalculatedFollowupDate(requestedDate);
                return (followUpDate.getMonth()+1) + "/" + (followUpDate.getDate()+1) + "/" + followUpDate.getYear();
                
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return empty;
    }

    public int getFollowupDateDefaultLengthInDays() {
        String followupDateRange = getFollowupDateDefaultLength();
        String rangeUnit = followupDateRange.substring(
        followupDateRange.length() - 1, followupDateRange.length());
        int rangeAmount =
        Integer.parseInt(followupDateRange.substring(
        0, followupDateRange.length() - 1));
        int returnAmount = 0;
        if (StringUtils.equalsIgnoreCase(rangeUnit, "D")) {
            returnAmount = rangeAmount;
        } else if (StringUtils.equalsIgnoreCase(rangeUnit, "W")) {
            returnAmount = rangeAmount * 7;
        } else {
            throw new IllegalArgumentException(
            "An invalid range unit was set in the "
            + "'Subaward Follow Up' parameter: " + rangeUnit);
        }
        return returnAmount;
    }
    
    public SubAward getActiveSubAward(Long subAwardId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put(SUB_AWARD_ID, subAwardId);
        List<SubAward> subAwards = (List<SubAward>) getBusinessObjectService().findMatching(SubAward.class, values);
        SubAward subAward = subAwards.get(0);
        getAmountInfo(subAward);
        return subAward;
    }

    @Override
    public List<SubAward> getLinkedSubAwards(Award award) {
        Map<String, Object> values = new HashMap<>();
        values.put(AWARD_AWARD_NUMBER, award.getAwardNumber());
        Collection<SubAwardFundingSource> subAwardFundingSources = businessObjectService.findMatching(SubAwardFundingSource.class, values);
        Set<String> subAwardSet = new TreeSet<String>();
        for(SubAwardFundingSource subAwardFundingSource : subAwardFundingSources){
            subAwardSet.add(subAwardFundingSource.getSubAward().getSubAwardCode());
        }
        List<SubAward> subAwards = new ArrayList<SubAward>();
        for (String subAwardCode : subAwardSet) {
            VersionHistory activeVersion = getVersionHistoryService().findActiveVersion(SubAward.class, subAwardCode);
            if (activeVersion == null) {
                VersionHistory pendingVersion = getVersionHistoryService().findPendingVersion(SubAward.class, subAwardCode);
                if (pendingVersion != null) {
                    subAwards.add((SubAward) pendingVersion.getSequenceOwner());
                }
            } else {
                subAwards.add((SubAward) activeVersion.getSequenceOwner());
            }
        }
        return subAwards;
    }

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected VersioningService getVersioningService() {
        return versioningService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
