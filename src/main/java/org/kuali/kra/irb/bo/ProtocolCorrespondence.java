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
package org.kuali.kra.irb.bo;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

@Entity 
@Table(name="PROTOCOL_CORRESPONDENCE")
public class ProtocolCorrespondence extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = 8032222937155468412L;

    @Id 
    @Column(name="ID")
    private Long id; 

    @Column(name="PROTOCOL_NUMBER")
    private String protocolNumber; 

    @Column(name="SEQUENCE_NUMBER")
    private Integer sequenceNumber; 
    
    @Column(name = "ACTION_ID")
    private Integer actionId;
    
    @Column(name = "PROTOCOL_ID")
    private Long protocolId;
    
    @Column(name = "ACTION_ID_FK")
    private Long actionIdFk;
    
    @Column(name = "PROTO_CORRESP_TYPE_CODE")
    private String protoCorrespTypeCode;

    @Column(name="CORRESPONDENCE")
    private byte[] correspondence; 
    
    @Column(name = "FINAL_FLAG")
    private boolean finalFlag;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ID", insertable=false, updatable=false)
    private Protocol protocol;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTO_CORRESP_TYPE_CODE", insertable=false, updatable=false)
    private ProtocolCorrespondenceType protocolCorrespondenceType;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="ACTION_ID_FK", insertable=false, updatable=false)
    private ProtocolAction protocolAction;
    
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
        return protocolCorrespondenceType;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("id", this.getId());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("actionId", getActionId());
        hashMap.put("correspondence", this.getCorrespondence());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("actionIdFk", getActionIdFk());
        hashMap.put("protoCorrespTypeCode", getProtoCorrespTypeCode());
        hashMap.put("finalFlag", getFinalFlag());
        return hashMap;
    }

}