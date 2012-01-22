/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.amendrenew;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@SuppressWarnings("serial")
public class ProtocolAmendRenewModule extends KraPersistableBusinessObjectBase implements SequenceAssociate<Protocol> {

    private Long protocolAmendRenewModuleId;

    private String protocolAmendRenewalNumber;

    private Long protocolAmendRenewalId;

    private String protocolNumber;

    private String protocolModuleTypeCode;

    private ProtocolAmendRenewal protocolAmendRenewal;

    private ProtocolModule protocolModule;

    public ProtocolAmendRenewModule() {
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

    public ProtocolAmendRenewal getProtocolAmendRenewal() {
        return protocolAmendRenewal;
    }

    public void setProtocolAmendRenewal(ProtocolAmendRenewal protocolAmendRenewal) {
        this.protocolAmendRenewal = protocolAmendRenewal;
    }

    public ProtocolModule getProtocolModule() {
        return protocolModule;
    }

    public void setProtocolModule(ProtocolModule protocolModule) {
        this.protocolModule = protocolModule;
    }

    public Protocol getSequenceOwner() {
        return protocolAmendRenewal != null ? protocolAmendRenewal.getProtocol() : null;
    }

    public void setSequenceOwner(Protocol newlyVersionedOwner) {
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
