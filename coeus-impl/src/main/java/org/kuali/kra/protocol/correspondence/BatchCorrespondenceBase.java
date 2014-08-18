/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BatchCorrespondenceBase extends KcPersistableBusinessObjectBase {

    // TODO normally these kind of public static vars would be pushed down to the IACUC/IRB subclasses, 
    // but it seems that they are going to have the same value in both IRB and IACUC so letting them stay here in the superclass. 
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

    private List<BatchCorrespondenceDetailBase> batchCorrespondenceDetails;

    private ProtocolCorrespondenceTypeBase protocolCorrespondenceType;

    private ProtocolActionTypeBase protocolActionType;

    public BatchCorrespondenceBase() {
        setBatchCorrespondenceDetails(new ArrayList<BatchCorrespondenceDetailBase>());
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
     * @return Sorted list of BatchCorrespondenceDetailBase
     */
    public List<BatchCorrespondenceDetailBase> getBatchCorrespondenceDetails() {
        List<BatchCorrespondenceDetailBase> result = this.batchCorrespondenceDetails;
        Collections.sort(result);
        if (StringUtils.equals(getSendCorrespondence(), SEND_CORRESPONDENCE_BEFORE_EVENT)) {
            Collections.reverse(result);
        }
        return result;
    }

    public void setBatchCorrespondenceDetails(List<BatchCorrespondenceDetailBase> batchCorrespondenceDetails) {
        this.batchCorrespondenceDetails = batchCorrespondenceDetails;
    }

    public ProtocolCorrespondenceTypeBase getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceTypeBase protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public ProtocolActionTypeBase getProtocolActionType() {
        return protocolActionType;
    }

    public void setProtocolActionType(ProtocolActionTypeBase protocolActionType) {
        this.protocolActionType = protocolActionType;
    }
}
