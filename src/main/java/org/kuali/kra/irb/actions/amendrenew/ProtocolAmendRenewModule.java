/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@Entity
@Table(name="PROTOCOL_MODULES")
@SuppressWarnings("serial")
public class ProtocolAmendRenewModule extends KraPersistableBusinessObjectBase {

    @Column(name = "PROTO_AMEND_RENEW_MODULE_ID")
    private Long protocolAmendRenewModuleId;
    
    @Column(name = "PROTO_AMEND_RENEW_MODULE_NUMBER")
    private String protocolAmendRenewModuleNumber;
    
    @Column(name = "PROTOCOL_ID")
    private Long protocolId;
    
    @Column(name = "PROTOCOL_NUMBER")
    private String protocolNumber;
    
    @Column(name = "PROTOCOL_MODULE_CODE")
    private String protocolModuleCode;
    
    private Protocol protocol;
    
    private ProtocolModule protocolModule;
    
    public ProtocolAmendRenewModule() {
        
    }

    public Long getProtocolAmendRenewModuleId() {
        return protocolAmendRenewModuleId;
    }

    public void setProtocolAmendRenewModuleId(Long protocolAmendRenewModuleId) {
        this.protocolAmendRenewModuleId = protocolAmendRenewModuleId;
    }

    public String getProtocolAmendRenewModuleNumber() {
        return protocolAmendRenewModuleNumber;
    }

    public void setProtocolAmendRenewModuleNumber(String protocolAmendRenewModuleNumber) {
        this.protocolAmendRenewModuleNumber = protocolAmendRenewModuleNumber;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getProtocolModuleCode() {
        return protocolModuleCode;
    }

    public void setProtocolModuleCode(String protocolModuleCode) {
        this.protocolModuleCode = protocolModuleCode;
    }
    
    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    public ProtocolModule getProtocolModule() {
        return protocolModule;
    }

    public void setProtocolModule(ProtocolModule protocolModule) {
        this.protocolModule = protocolModule;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("protocolAmendRenewModuleId", this.getProtocolAmendRenewModuleId());
        map.put("protocolAmendRenewModuleNumber", this.getProtocolAmendRenewModuleNumber());
        map.put("protocolId", this.getProtocolId());
        map.put("protocolNumber", this.getProtocolNumber());
        map.put("protocolModuleCode", getProtocolModuleCode());
        return map;
    }
}
