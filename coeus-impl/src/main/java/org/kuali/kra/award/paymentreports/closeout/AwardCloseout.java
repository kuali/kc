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
package org.kuali.kra.award.paymentreports.closeout;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.AwardAssociate;

import java.sql.Date;

/**
 * 
 * This class represents the AwardCloseout business object.
 */
public class AwardCloseout extends AwardAssociate {


    private static final long serialVersionUID = 5392480855349965272L;

    private Long awardCloseoutId;

    private Date finalSubmissionDate;

    private Date dueDate;

    private String closeoutReportCode;

    private String closeoutReportName;

    private boolean multiple;

    private CloseoutReportType closeoutReportType;

    /**
     * 
    <c:choose>
        <c:when test="${KualiForm.awardCloseoutBean.closeoutReportTypeUserDefined == KualiForm.document.awardList[0].awardCloseoutItems[status.index].closeoutReportCode }" >
            <kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}" />
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${KualiForm.document.awardList[0].awardCloseoutItems[status.index].multiple}" >
                    <c:out value="MULTIPLE" />
                </c:when>
                <c:otherwise>
                    <kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}" readOnly="true" />
                </c:otherwise>
            </c:choose>
         </c:otherwise>
    </c:choose>
     */

    public AwardCloseout() {
    }

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

    public String getCloseoutReportCode() {
        return closeoutReportCode;
    }

    public void setCloseoutReportCode(String closeoutReportCode) {
        this.closeoutReportCode = closeoutReportCode;
    }

    /**
     * Gets the closeoutReportType attribute. 
     * @return Returns the closeoutReportType.
     */
    public CloseoutReportType getCloseoutReportType() {
        return closeoutReportType;
    }

    /**
     * Sets the closeoutReportType attribute value.
     * @param closeoutReportType The closeoutReportType to set.
     */
    public void setCloseoutReportType(CloseoutReportType closeoutReportType) {
        this.closeoutReportType = closeoutReportType;
    }

    /**
     * Gets the dueDate attribute. 
     * @return Returns the dueDate.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the dueDate attribute value.
     * @param dueDate The dueDate to set.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the closeoutReportName attribute. 
     * @return Returns the closeoutReportName.
     */
    public String getCloseoutReportName() {
        return closeoutReportName;
    }

    /**
     * Sets the closeoutReportName attribute value.
     * @param closeoutReportName The closeoutReportName to set.
     */
    public void setCloseoutReportName(String closeoutReportName) {
        this.closeoutReportName = closeoutReportName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((closeoutReportCode == null) ? 0 : closeoutReportCode.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = prime * result + ((finalSubmissionDate == null) ? 0 : finalSubmissionDate.hashCode());
        result = prime * result + (multiple ? 1231 : 1237);
        return result;
    }

    /**
     * Gets the multiple attribute. 
     * @return Returns the multiple.
     */
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * Sets the multiple attribute value.
     * @param multiple The multiple to set.
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public void resetPersistenceState() {
        awardCloseoutId = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof AwardCloseout)) return false;
        final AwardCloseout other = (AwardCloseout) obj;
        if (closeoutReportCode == null) {
            if (other.closeoutReportCode != null) return false;
        } else if (!closeoutReportCode.equals(other.closeoutReportCode)) return false;
        if (dueDate == null) {
            if (other.dueDate != null) return false;
        } else if (!dueDate.equals(other.dueDate)) return false;
        if (finalSubmissionDate == null) {
            if (other.finalSubmissionDate != null) return false;
        } else if (!finalSubmissionDate.equals(other.finalSubmissionDate)) return false;
        if (multiple != other.multiple) return false;
        return true;
    }

    /**
     * 
     * This method returns true if the closeout report code equals "UD".
     * @return
     */
    public boolean isUserDefinedReport() {
        boolean retVal = StringUtils.equalsIgnoreCase("UD", this.getCloseoutReportCode());
        return retVal;
    }
}
