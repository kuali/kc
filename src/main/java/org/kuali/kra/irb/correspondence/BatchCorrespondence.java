/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.actions.ProtocolActionType;

public class BatchCorrespondence extends KraPersistableBusinessObjectBase {

    public static final String SEND_CORRESPONDENCE_BEFORE_EVENT = "BEFORE";

    public static final String SEND_CORRESPONDENCE_AFTER_EVENT = "AFTER";

    private static final long serialVersionUID = 1L;

    private String batchCorrespondenceTypeCode;

    private String description;

    private String daysToEventUiText;

    private String sendCorrespondence;

    private Integer finalActionDay;

    private String finalActionTypeCode;

    private String finalActionCorrespType;

    private List<BatchCorrespondenceDetail> batchCorrespondenceDetails;

    private ProtocolCorrespondenceType protocolCorrespondenceType;

    private ProtocolActionType protocolActionType;

    public BatchCorrespondence() {
        setBatchCorrespondenceDetails(new ArrayList<BatchCorrespondenceDetail>());
    }

    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDaysToEventUiText() {
        return daysToEventUiText;
    }

    public void setDaysToEventUiText(String daysToEventUiText) {
        this.daysToEventUiText = daysToEventUiText;
    }

    public String getSendCorrespondence() {
        return sendCorrespondence;
    }

    public void setSendCorrespondence(String sendCorrespondence) {
        this.sendCorrespondence = sendCorrespondence;
    }

    public Integer getFinalActionDay() {
        return finalActionDay;
    }

    public void setFinalActionDay(Integer finalActionDay) {
        this.finalActionDay = finalActionDay;
    }

    public String getFinalActionTypeCode() {
        if (finalActionTypeCode == null) {
            return "";
        } else {
            return finalActionTypeCode;
        }
    }

    public void setFinalActionTypeCode(String finalActionTypeCode) {
        if (StringUtils.isBlank(finalActionTypeCode)) {
            this.finalActionTypeCode = null;
        } else {
            this.finalActionTypeCode = finalActionTypeCode;
        }
    }

    public String getFinalActionCorrespType() {
        if (finalActionCorrespType == null) {
            return "";
        } else {
            return finalActionCorrespType;
        }
    }

    public void setFinalActionCorrespType(String finalActionCorrespType) {
        if (StringUtils.isBlank(finalActionCorrespType)) {
            this.finalActionCorrespType = null;
        } else {
            this.finalActionCorrespType = finalActionCorrespType;
        }
    }

    /**
     * 
     * This method returns the batch correspondence details in the proper order depending if
     * correspondences occur before or after the event.
     * @return Sorted list of BatchCorrespondenceDetail
     */
    public List<BatchCorrespondenceDetail> getBatchCorrespondenceDetails() {
        List<BatchCorrespondenceDetail> result = this.batchCorrespondenceDetails;
        Collections.sort(result);
        if (StringUtils.equals(getSendCorrespondence(), SEND_CORRESPONDENCE_BEFORE_EVENT)) {
            Collections.reverse(result);
        }
        return result;
    }

    public void setBatchCorrespondenceDetails(List<BatchCorrespondenceDetail> batchCorrespondenceDetails) {
        this.batchCorrespondenceDetails = batchCorrespondenceDetails;
    }

    public ProtocolCorrespondenceType getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceType protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public ProtocolActionType getProtocolActionType() {
        return protocolActionType;
    }

    public void setProtocolActionType(ProtocolActionType protocolActionType) {
        this.protocolActionType = protocolActionType;
    }
}
