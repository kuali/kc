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

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;

/**
 * 
 * This class manages the attributes needed to maintain and protocol correspondence.
 */
public class ProtocolCorrespondence extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 8032222937155468412L;

    private Long id;

    private String protocolNumber;

    private Integer sequenceNumber;

    private Integer actionId;

    private Long protocolId;

    private Long actionIdFk;

    private String protoCorrespTypeCode;

    private byte[] correspondence;

    private boolean finalFlag;

    private Protocol protocol;

    private ProtocolCorrespondenceType protocolCorrespondenceType;

    private ProtocolAction protocolAction;
    
    private boolean regenerateFlag;
    private Timestamp createTimestamp; 
    private String createUser;


    // hooks to view/save correspondence
    private ProtocolNotificationRequestBean notificationRequestBean;
    private String forwardName;
    private boolean holdingPage;

    /**
     * 
     * Constructs a ProtocolCorrespondence.java.
     */
    public ProtocolCorrespondence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getActionIdFk() {
        return actionIdFk;
    }

    public void setActionIdFk(Long actionIdFk) {
        this.actionIdFk = actionIdFk;
    }

    public String getProtoCorrespTypeCode() {
        return protoCorrespTypeCode;
    }

    public void setProtoCorrespTypeCode(String protoCorrespTypeCode) {
        this.protoCorrespTypeCode = protoCorrespTypeCode;
    }

    public byte[] getCorrespondence() {
        return correspondence;
    }

    public void setCorrespondence(byte[] correspondence) {
        this.correspondence = correspondence;
    }

    public void setFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
    }

    public boolean getFinalFlag() {
        return finalFlag;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ProtocolAction getProtocolAction() {
        return protocolAction;
    }

    public void setProtocolAction(ProtocolAction protocolAction) {
        this.protocolAction = protocolAction;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceType protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public ProtocolCorrespondenceType getProtocolCorrespondenceType() {
        if (protocolCorrespondenceType == null && StringUtils.isNotBlank(protoCorrespTypeCode)) {
            this.refreshReferenceObject("protocolCorrespondenceType");
        }
        return protocolCorrespondenceType;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public ProtocolNotificationRequestBean getNotificationRequestBean() {
        return notificationRequestBean;
    }

    public void setNotificationRequestBean(ProtocolNotificationRequestBean notificationRequestBean) {
        this.notificationRequestBean = notificationRequestBean;
    }

    public boolean isHoldingPage() {
        return holdingPage;
    }

    public void setHoldingPage(boolean holdingPage) {
        this.holdingPage = holdingPage;
    }

    public boolean isRegenerateFlag() {
        return regenerateFlag;
    }

    public void setRegenerateFlag(boolean regenerateFlag) {
        this.regenerateFlag = regenerateFlag;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
