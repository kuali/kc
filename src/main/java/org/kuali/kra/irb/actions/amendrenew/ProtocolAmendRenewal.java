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
package org.kuali.kra.irb.actions.amendrenew;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

public class ProtocolAmendRenewal extends KraPersistableBusinessObjectBase implements SequenceAssociate<Protocol> {

    private static final long serialVersionUID = 1317253368511551232L;

    private Long id;

    private String protoAmendRenNumber;

    private Date dateCreated;

    private String summary;

    private Long protocolId;

    private String protocolNumber;

    private Integer sequenceNumber;

    private List<ProtocolAmendRenewModule> modules;

    private Protocol protocol;

    public ProtocolAmendRenewal() {
        modules = new ArrayList<ProtocolAmendRenewModule>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtoAmendRenNumber() {
        return protoAmendRenNumber;
    }

    public void setProtoAmendRenNumber(String protoAmendRenNumber) {
        this.protoAmendRenNumber = protoAmendRenNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long long1) {
        this.protocolId = long1;
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

    public List<ProtocolAmendRenewModule> getModules() {
        return modules;
    }

    public void setModules(List<ProtocolAmendRenewModule> modules) {
        this.modules = modules;
    }

    public void addModule(ProtocolAmendRenewModule module) {
        modules.add(module);
    }

    public void removeModule(String protocolModuleTypeCode) {
        for (ProtocolAmendRenewModule module : modules) {
            if (StringUtils.equals(protocolModuleTypeCode, module.getProtocolModuleTypeCode())) {
                modules.remove(module);
            }
        }
    }

    /**
     * This method checks to see if the protocol amendment or renewal amends a specific module.
     * @param protocolModuleTypeCode
     * @return true if the module is being amended, false otherwise.
     */
    public boolean hasModule(String protocolModuleTypeCode) {
        for (ProtocolAmendRenewModule module : getModules()) {
            if (StringUtils.equals(protocolModuleTypeCode, module.getProtocolModuleTypeCode())) {
                return true;
            }
        }
        return false;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /** {@inheritDoc} */
    public Protocol getSequenceOwner() {
        return this.getProtocol();
    }

    /** {@inheritDoc} */
    public void setSequenceOwner(Protocol newlyVersionedOwner) {
        this.setProtocol(newlyVersionedOwner);
    }

    public void resetPersistenceState() {
        this.setId(null);
    }
}
