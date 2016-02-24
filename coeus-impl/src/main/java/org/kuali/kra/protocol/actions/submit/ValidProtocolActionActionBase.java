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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;

/**
 * This class represents the action follow up mapping as it exists in coeus.
 * A follow up action is mapped to an action via this bo.  When the user takes the action
 * protocolActionTypeCode they will be prompted to complete each of the actions associated with it 
 * via this object.  The properties protocolActionTypeCode, actionNumber, and followUpActionCode form
 * a unique key on the underlying table.
 * 
 */
public abstract class ValidProtocolActionActionBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long validProtocolActionActionId;

    private String protocolActionTypeCode;

    private String motionTypeCode;

    private int actionNumber;

    private String followupActionCode;

    private boolean userPromptFlag;

    private String userPrompt;

    private ProtocolActionTypeBase protocolActionType;

    private CommitteeDecisionMotionType committeeDecisionMotionType;

    private ProtocolActionTypeBase followupProtocolActionType;

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
    public ProtocolActionTypeBase getProtocolActionType() {
        return protocolActionType;
    }

    /**
     * Sets the protocolActionType attribute value.
     * @param protocolActionType The protocolActionType to set.
     */
    public void setProtocolActionType(ProtocolActionTypeBase protocolActionType) {
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
    public ProtocolActionTypeBase getFollowupProtocolActionType() {
        return followupProtocolActionType;
    }

    /**
     * Sets the followupProtocolActionType attribute value.
     * @param followupProtocolActionType The followupProtocolActionType to set.
     */
    public void setFollowupProtocolActionType(ProtocolActionTypeBase followupProtocolActionType) {
        this.followupProtocolActionType = followupProtocolActionType;
    }

    public ValidProtocolActionActionBase() {
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
