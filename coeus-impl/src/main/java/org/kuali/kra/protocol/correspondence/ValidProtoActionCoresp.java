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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;

public class ValidProtoActionCoresp extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long validProtoActionCorespId;

    private String protocolActionTypeCode;

    private String protoCorrespTypeCode;

    private boolean finalFlag;

    private ProtocolActionTypeBase protocolActionType;
    
    private ProtocolCorrespondenceTypeBase protocolCorrespondenceType;
    
    public ValidProtoActionCoresp() {
    }

    public Long getValidProtoActionCorespId() {
        return validProtoActionCorespId;
    }

    public void setValidProtoActionCorespId(Long validProtoActionCorespId) {
        this.validProtoActionCorespId = validProtoActionCorespId;
    }

    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(String protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public String getProtoCorrespTypeCode() {
        return protoCorrespTypeCode;
    }

    public void setProtoCorrespTypeCode(String protoCorrespTypeCode) {
        this.protoCorrespTypeCode = protoCorrespTypeCode;
    }

    public boolean getFinalFlag() {
        return finalFlag;
    }

    public void setFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
    }

    public ProtocolActionTypeBase getProtocolActionType() {
        return protocolActionType;
    }

    public void setProtocolActionType(ProtocolActionTypeBase protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    public ProtocolCorrespondenceTypeBase getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceTypeBase protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }
}
