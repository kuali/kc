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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class ValidProtoSubRevType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 3396505214377892706L;

    private Long validProtoSubRevTypeId;

    private String submissionTypeCode;

    private String protocolReviewTypeCode;

    private ProtocolReviewTypeBase protocolReviewType;

    private ProtocolSubmissionTypeBase submissionType;

    public ValidProtoSubRevType() {
    }

    public Long getValidProtoSubRevTypeId() {
        return validProtoSubRevTypeId;
    }

    public void setValidProtoSubRevTypeId(Long validProtoSubRevTypeId) {
        this.validProtoSubRevTypeId = validProtoSubRevTypeId;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }

    public ProtocolReviewTypeBase getProtocolReviewType() {
        return protocolReviewType;
    }

    public void setProtocolReviewType(ProtocolReviewTypeBase protocolReviewType) {
        this.protocolReviewType = protocolReviewType;
    }

    public ProtocolSubmissionTypeBase getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(ProtocolSubmissionTypeBase submissionType) {
        this.submissionType = submissionType;
    }
}
