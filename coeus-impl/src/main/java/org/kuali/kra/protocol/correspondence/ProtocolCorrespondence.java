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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;

import java.sql.Timestamp;

/**
 * 
 * This class manages the attributes needed to maintain and protocol correspondence.
 */
public class ProtocolCorrespondence extends ProtocolAssociateBase {

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

    private ProtocolBase protocol;

    private ProtocolCorrespondenceTypeBase protocolCorrespondenceType;

    private ProtocolActionBase protocolAction;
    
    private boolean regenerateFlag;
    private Timestamp createTimestamp; 
    private String createUser;
    private Timestamp finalFlagTimestamp; 


    // hooks to view/save correspondence
    private ProtocolNotificationRequestBeanBase notificationRequestBean;
    private String forwardName;
    private boolean holdingPage;


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

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    public ProtocolActionBase getProtocolAction() {
        return protocolAction;
    }

    public void setProtocolAction(ProtocolActionBase protocolAction) {
        this.protocolAction = protocolAction;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceTypeBase protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public ProtocolCorrespondenceTypeBase getProtocolCorrespondenceType() {
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

    public ProtocolNotificationRequestBeanBase getNotificationRequestBean() {
        return notificationRequestBean;
    }

    public void setNotificationRequestBean(ProtocolNotificationRequestBeanBase notificationRequestBean) {
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

    public Timestamp getFinalFlagTimestamp() {
        return finalFlagTimestamp;
    }

    public void setFinalFlagTimestamp(Timestamp finalFlagTimestamp) {
        this.finalFlagTimestamp = finalFlagTimestamp;
    }

    @Override
    public void resetPersistenceState() {
    	id = null;
    }
    
}
