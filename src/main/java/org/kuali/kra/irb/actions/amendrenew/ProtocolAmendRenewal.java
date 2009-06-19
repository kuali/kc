/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@Entity 
@Table(name="PROTO_AMEND_RENEWAL")
public class ProtocolAmendRenewal extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1317253368511551232L;
    
    @Id 
    @Column(name="PROTO_AMEND_RENEWAL_ID")
    private Long id; 

    @Column(name="PROTO_AMEND_REN_NUMBER")
    private String protoAmendRenNumber; 

    @Column(name="DATE_CREATED")
    private Date dateCreated; 

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="SUMMARY")
    private String summary; 

    @Column(name="PROTOCOL_ID")
    private Long protocolId; 

    @Column(name="PROTOCOL_NUMBER")
    private String protocolNumber; 

    @Column(name="SEQUENCE_NUMBER")
    private Integer sequenceNumber; 

    private List<ProtocolAmendRenewModule> modules = new ArrayList<ProtocolAmendRenewModule>();
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ID", insertable=false, updatable=false)
    private Protocol protocol;
    
    public ProtocolAmendRenewal() { 

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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("id", this.getId());
        hashMap.put("protoAmendRenNumber", this.getProtoAmendRenNumber());
        hashMap.put("dateCreated", this.getDateCreated());
        hashMap.put("summary", this.getSummary());
        hashMap.put("protocolId", this.getProtocolId());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        return hashMap;
    }
    
}