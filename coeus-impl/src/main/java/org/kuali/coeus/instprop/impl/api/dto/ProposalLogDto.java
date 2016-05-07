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

package org.kuali.coeus.instprop.impl.api.dto;

import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.Property;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.kuali.coeus.instprop.impl.api.customSerializers.CustomSqlDateSerializer;
import org.kuali.coeus.instprop.impl.api.customSerializers.CustomTimestampSerializer;

import java.sql.Date;
import java.sql.Timestamp;

public class ProposalLogDto {
    private String proposalNumber;
    @Ignore
    private String proposalTypeCode;
    @Property(translate = true, source = "documentDescription")
    private String title;
    private String piId;
    private Integer rolodexId;
    private String piName;
    @Property(translate = true, source = "unitNumber")
    private String leadUnit;
    private String sponsorCode;
    private String sponsorName;
    private String logStatus;
    private String comments;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date deadlineDate;
    private String deadlineTime;
    private String proposalLogTypeCode;
    private Integer fiscalMonth;
    private Integer fiscalYear;
    private String createUser;
    @JsonDeserialize(using = CustomTimestampSerializer.class)
    @Ignore
    private Timestamp createTimestamp;

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPiId() {
        return piId;
    }

    public void setPiId(String piId) {
        this.piId = piId;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    public String getLeadUnit() {
        return leadUnit;
    }

    public void setLeadUnit(String leadUnit) {
        this.leadUnit = leadUnit;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getProposalLogTypeCode() {
        return proposalLogTypeCode;
    }

    public void setProposalLogTypeCode(String proposalLogTypeCode) {
        this.proposalLogTypeCode = proposalLogTypeCode;
    }

    public Integer getFiscalMonth() {
        return fiscalMonth;
    }

    public void setFiscalMonth(Integer fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
