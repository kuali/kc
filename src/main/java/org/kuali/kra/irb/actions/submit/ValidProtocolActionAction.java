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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * This class represents the action follow up mapping as it exists in coeus.
 * A follow up action is mapped to an action via this bo.  When the user takes the action
 * protocolActionTypeCode they will be prompted to complete each of the actions associated with it 
 * via this object.  The properties protocolActionTypeCode, actionNumber, and followUpActionCode form
 * a unique key on the underlying table.
 * 
 */
public class ValidProtocolActionAction extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long validProtocolActionActionId;

    private String protocolActionTypeCode;

    private String motionTypeCode;

    private int actionNumber;

    private String followupActionCode;

    private boolean userPromptFlag;

    private String userPrompt;

    private ProtocolActionType protocolActionType;

    private CommitteeDecisionMotionType committeeDecisionMotionType;

    private ProtocolActionType followupProtocolActionType;

    /**
     * Gets the motionTypeCode attribute. 
     * @return Returns the motionTypeCode.
     */
    public String getMotionTypeCode() {
        return motionTypeCode;
    }

    /**
     * Sets the motionTypeCode attribute value.
     * @param motionTypeCode The motionTypeCode to set.
     */
    public void setMotionTypeCode(String motionTypeCode) {
        this.motionTypeCode = motionTypeCode;
    }

    /**
     * Gets the protocolActionType attribute. 
     * @return Returns the protocolActionType.
     */
    public ProtocolActionType getProtocolActionType() {
        return protocolActionType;
    }

    /**
     * Sets the protocolActionType attribute value.
     * @param protocolActionType The protocolActionType to set.
     */
    public void setProtocolActionType(ProtocolActionType protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    /**
     * Gets the committeeDecisionMotionType attribute. 
     * @return Returns the committeeDecisionMotionType.
     */
    public CommitteeDecisionMotionType getCommitteeDecisionMotionType() {
        return committeeDecisionMotionType;
    }

    /**
     * Sets the committeeDecisionMotionType attribute value.
     * @param committeeDecisionMotionType The committeeDecisionMotionType to set.
     */
    public void setCommitteeDecisionMotionType(CommitteeDecisionMotionType committeeDecisionMotionType) {
        this.committeeDecisionMotionType = committeeDecisionMotionType;
    }

    /**
     * Gets the followupProtocolActionType attribute. 
     * @return Returns the followupProtocolActionType.
     */
    public ProtocolActionType getFollowupProtocolActionType() {
        return followupProtocolActionType;
    }

    /**
     * Sets the followupProtocolActionType attribute value.
     * @param followupProtocolActionType The followupProtocolActionType to set.
     */
    public void setFollowupProtocolActionType(ProtocolActionType followupProtocolActionType) {
        this.followupProtocolActionType = followupProtocolActionType;
    }

    public ValidProtocolActionAction() {
    }

    /**
     * Gets the validProtocolActionActionId attribute. 
     * @return Returns the validProtocolActionActionId.
     */
    public Long getValidProtocolActionActionId() {
        return validProtocolActionActionId;
    }

    /**
     * Sets the validProtocolActionActionId attribute value.
     * @param validProtocolActionActionId The validProtocolActionActionId to set.
     */
    public void setValidProtocolActionActionId(Long validProtocolActionActionId) {
        this.validProtocolActionActionId = validProtocolActionActionId;
    }

    /**
     * Gets the protocolActionTypeCode attribute. 
     * @return Returns the protocolActionTypeCode.
     */
    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    /**
     * Sets the protocolActionTypeCode attribute value.
     * @param protocolActionTypeCode The protocolActionTypeCode to set.
     */
    public void setProtocolActionTypeCode(String protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    /**
     * Gets the actionNumber attribute. 
     * @return Returns the actionNumber.
     */
    public int getActionNumber() {
        return actionNumber;
    }

    /**
     * Sets the actionNumber attribute value.
     * @param actionNumber The actionNumber to set.
     */
    public void setActionNumber(int actionNumber) {
        this.actionNumber = actionNumber;
    }

    /**
     * Gets the followupActionCode attribute. 
     * @return Returns the followupActionCode.
     */
    public String getFollowupActionCode() {
        return followupActionCode;
    }

    /**
     * Sets the followupActionCode attribute value.
     * @param followupActionCode The followupActionCode to set.
     */
    public void setFollowupActionCode(String followupActionCode) {
        this.followupActionCode = followupActionCode;
    }

    /**
     * Gets the promptUserFlag attribute. 
     * @return Returns the promptUserFlag.
     */
    public boolean getUserPromptFlag() {
        return userPromptFlag;
    }

    /**
     * Sets the promptUserFlag attribute value.
     * @param promptUserFlag The promptUserFlag to set.
     */
    public void setUserPromptFlag(boolean promptUserFlag) {
        this.userPromptFlag = promptUserFlag;
    }

    /**
     * Gets the userPrompt attribute. 
     * @return Returns the userPrompt.
     */
    public String getUserPrompt() {
        return userPrompt;
    }

    /**
     * Sets the userPrompt attribute value.
     * @param userPrompt The userPrompt to set.
     */
    public void setUserPrompt(String userPrompt) {
        this.userPrompt = userPrompt;
    }
}
