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
package org.kuali.coeus.award.dto;

import java.sql.Date;

public class AwardCloseoutDto {


    private Long awardCloseoutId;

    private Date finalSubmissionDate;

    private Date dueDate;

    private String closeoutReportCode;

    private String closeoutReportName;

    private boolean multiple;

    public Long getAwardCloseoutId() {
        return awardCloseoutId;
    }

    public void setAwardCloseoutId(Long awardCloseoutId) {
        this.awardCloseoutId = awardCloseoutId;
    }

    public Date getFinalSubmissionDate() {
        return finalSubmissionDate;
    }

    public void setFinalSubmissionDate(Date finalSubmissionDate) {
        this.finalSubmissionDate = finalSubmissionDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCloseoutReportCode() {
        return closeoutReportCode;
    }

    public void setCloseoutReportCode(String closeoutReportCode) {
        this.closeoutReportCode = closeoutReportCode;
    }

    public String getCloseoutReportName() {
        return closeoutReportName;
    }

    public void setCloseoutReportName(String closeoutReportName) {
        this.closeoutReportName = closeoutReportName;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }
}
