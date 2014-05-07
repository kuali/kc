/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
