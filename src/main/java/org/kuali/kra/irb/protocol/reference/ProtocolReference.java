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
package org.kuali.kra.irb.protocol.reference;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;

/**
 * 
 * This class...
 */
public class ProtocolReference extends ProtocolAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7610203950849323256L;

    private Long protocolReferenceId;

    private Integer protocolReferenceNumber;

    private Integer protocolReferenceTypeCode;

    private String referenceKey;

    private Date applicationDate;

    private Date approvalDate;

    private String comments;

    private ProtocolReferenceType protocolReferenceType;

    /**
	 * 
	 * Constructs a ProtocolReference.java.
	 */
    public ProtocolReference() {
    }

    /**
	 * 
	 * Constructs a ProtocolReference.java.
	 * @param bean
	 * @param protocol
	 * @param protocolReferenceType
	 * @throws ParseException
	 */
    public ProtocolReference(ProtocolReferenceBean bean, Protocol protocol, ProtocolReferenceType protocolReferenceType) throws ParseException {
        this.protocolReferenceType = protocolReferenceType;
        this.protocolReferenceTypeCode = protocolReferenceType.getProtocolReferenceTypeCode();
        this.setProtocol(protocol);
        this.setProtocolId(protocol.getProtocolId());
        this.setProtocolNumber(protocol.getProtocolNumber());
        this.referenceKey = bean.getReferenceKey();
        this.comments = bean.getComments();
        this.setApplicationDate(convertStringToDate(bean.getApplicationDate()));
        this.setApprovalDate(convertStringToDate(bean.getApprovalDate()));
    }

    private Date convertStringToDate(String stringDate) throws ParseException {
        if (!StringUtils.isBlank(stringDate)) {
            Date date = new Date(DateFormat.getDateInstance(DateFormat.SHORT).parse(stringDate).getTime());
            return date;
        } else {
            return null;
        }
    }

    public void setProtocolReferenceId(Long protocolReferenceId) {
        this.protocolReferenceId = protocolReferenceId;
    }

    public Long getProtocolReferenceId() {
        return protocolReferenceId;
    }

    public Integer getProtocolReferenceNumber() {
        return protocolReferenceNumber;
    }

    public void setProtocolReferenceNumber(Integer protocolReferenceNumber) {
        this.protocolReferenceNumber = protocolReferenceNumber;
    }

    public Integer getProtocolReferenceTypeCode() {
        return protocolReferenceTypeCode;
    }

    public void setProtocolReferenceTypeCode(Integer protocolReferenceTypeCode) {
        this.protocolReferenceTypeCode = protocolReferenceTypeCode;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ProtocolReferenceType getProtocolReferenceType() {
        return protocolReferenceType;
    }

    public void setProtocolReferenceType(ProtocolReferenceType protocolReferenceType) {
        this.protocolReferenceType = protocolReferenceType;
    }

    /**
	 * {@inheritDoc}
	 * sets the protocol reference number to null.
	 */
    @Override
    public void postInitHook(Protocol aProtocol) {
        this.setProtocolReferenceNumber(null);
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setProtocolReferenceId(null);
    }
}
