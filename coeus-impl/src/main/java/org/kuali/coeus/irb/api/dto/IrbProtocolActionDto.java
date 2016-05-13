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

package org.kuali.coeus.irb.api.dto;

import com.codiform.moo.annotation.CollectionProperty;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.kuali.coeus.instprop.impl.api.customSerializers.CustomSqlDateSerializer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class IrbProtocolActionDto {

    private String actionCode;
    private String submissionTypeCode;
    private String protocolReviewTypeCode;
    private List<String> exemptStudiesCheckList;
    private List<String> expeditedReviewCheckList;
    private String submissionQualifierTypeCode;
    private String committeeId;
    private String scheduleId;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date approvalDate;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date expirationDate;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date actionDate;

    @JsonProperty(value = "reviewers")
    @CollectionProperty(itemClass=ProtocolReviewerBean.class)
    private List<ProtocolReviewerBean> reviewers;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date initialSubmissionDate;


    public IrbProtocolActionDto() {
        exemptStudiesCheckList = new ArrayList<>();
        expeditedReviewCheckList = new ArrayList<>();
        reviewers = new ArrayList<>();
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
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

    public List getExemptStudiesCheckList() {
        return exemptStudiesCheckList;
    }

    public void setExemptStudiesCheckList(List<String> exemptStudiesCheckList) {
        this.exemptStudiesCheckList = exemptStudiesCheckList;
    }

    public List getExpeditedReviewCheckList() {
        return expeditedReviewCheckList;
    }

    public void setExpeditedReviewCheckList(List<String> expeditedReviewCheckList) {
        this.expeditedReviewCheckList = expeditedReviewCheckList;
    }

    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<ProtocolReviewerBean> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<ProtocolReviewerBean> reviewers) {
        this.reviewers = reviewers;
    }

    public Date getInitialSubmissionDate() {
        return initialSubmissionDate;
    }

    public void setInitialSubmissionDate(Date initialSubmissionDate) {
        this.initialSubmissionDate = initialSubmissionDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
