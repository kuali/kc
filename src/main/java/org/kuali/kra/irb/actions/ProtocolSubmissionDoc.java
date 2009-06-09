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
package org.kuali.kra.irb.actions;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

@SuppressWarnings("serial")
public class ProtocolSubmissionDoc extends KraPersistableBusinessObjectBase {
    
    @Id 
    @Column(name = "SUBMISSION_DOC_ID")
    private Long submissionDocId;
    
    @Column(name="PROTOCOL_NUMBER")
    private String protocolNumber; 

    @Column(name="SEQUENCE_NUMBER")
    private Integer sequenceNumber;
    
    @Column(name = "SUBMISSION_NUMBER")
    private Integer submissionNumber;
    
    @Column(name = "DOCUMENT_ID")
    private Integer documentId;
    
    @Column(name = "PROTOCOL_ID")
    private Long protocolId;
    
    @Column(name = "SUBMISSION_ID_FK")
    private Long submissionIdFk;
    
    @Column(name = "FILE_NAME")
    private String fileName;
    
    @Column(name = "DOCUMENT")
    private byte[] document;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ID", insertable=false, updatable=false)
    private Protocol protocol;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_ID_FK", insertable=false, updatable=false)
    private ProtocolSubmission protocolSubmission;    
    
    public ProtocolSubmissionDoc() {
        
    }

    public Long getSubmissionDocId() {
        return submissionDocId;
    }

    public void setSubmissionDocId(Long submissionDocId) {
        this.submissionDocId = submissionDocId;
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

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("submissionDocId", getSubmissionDocId());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("submissionIdFk", getSubmissionIdFk());
        hashMap.put("documentId", getDocumentId());
        hashMap.put("fileName", getFileName());
        hashMap.put("document", getDocument());
        return hashMap;
    }
}
