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
package org.kuali.kra.protocol.actions.amendrenew;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.ProtocolBase;

@SuppressWarnings("serial")
public abstract class ProtocolAmendRenewModuleBase extends KcPersistableBusinessObjectBase implements SequenceAssociate<ProtocolBase> {

    private Long protocolAmendRenewModuleId;

    private String protocolAmendRenewalNumber;

    private Long protocolAmendRenewalId;

    private String protocolNumber;

    private String protocolModuleTypeCode;

    private ProtocolAmendRenewalBase protocolAmendRenewal;

    private ProtocolModuleBase protocolModule;

    public ProtocolAmendRenewModuleBase() {
    }

    public Long getProtocolAmendRenewModuleId() {
        return protocolAmendRenewModuleId;
    }

    public void setProtocolAmendRenewModuleId(Long protocolAmendRenewModuleId) {
        this.protocolAmendRenewModuleId = protocolAmendRenewModuleId;
    }

    public String getProtocolAmendRenewalNumber() {
        return protocolAmendRenewalNumber;
    }

    public void setProtocolAmendRenewalNumber(String protocolAmendRenewalNumber) {
        this.protocolAmendRenewalNumber = protocolAmendRenewalNumber;
    }

    public Long getProtocolAmendRenewalId() {
        return protocolAmendRenewalId;
    }

    public void setProtocolAmendRenewalId(Long protocolAmendRenewalId) {
        this.protocolAmendRenewalId = protocolAmendRenewalId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getProtocolModuleTypeCode() {
        return protocolModuleTypeCode;
    }

    public void setProtocolModuleTypeCode(String protocolModuleTypeCode) {
        this.protocolModuleTypeCode = protocolModuleTypeCode;
    }

    public ProtocolAmendRenewalBase getProtocolAmendRenewal() {
        return protocolAmendRenewal;
    }

    public void setProtocolAmendRenewal(ProtocolAmendRenewalBase protocolAmendRenewal) {
        this.protocolAmendRenewal = protocolAmendRenewal;
    }

    public ProtocolModuleBase getProtocolModule() {
        return protocolModule;
    }

    public void setProtocolModule(ProtocolModuleBase protocolModule) {
        this.protocolModule = protocolModule;
    }

    public ProtocolBase getSequenceOwner() {
        return protocolAmendRenewal != null ? protocolAmendRenewal.getProtocol() : null;
    }

    public void setSequenceOwner(ProtocolBase newlyVersionedOwner) {
        if (protocolAmendRenewal != null) {
            protocolAmendRenewal.setProtocol(newlyVersionedOwner);
        }
    }

    public void resetPersistenceState() {
        protocolAmendRenewModuleId = null;
    }

    public Integer getSequenceNumber() {
        return protocolAmendRenewal != null ? protocolAmendRenewal.getSequenceNumber() : 0;
    }
}
