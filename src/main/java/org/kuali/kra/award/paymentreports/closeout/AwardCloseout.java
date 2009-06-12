/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.closeout;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.award.AwardAssociate;

/**
 * 
 * This class represents the AwardCloseout business object.
 */
public class AwardCloseout extends AwardAssociate { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
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
     * Constructs a AwardCloseout.java.
     */
    public AwardCloseout() { 

    } 
    
    /**
     * 
     * This method...
     * @return
     */
    public Long getAwardCloseoutId() {
        return awardCloseoutId;
    }

    /**
     * 
     * This method...
     * @param awardCloseoutId
     */
    public void setAwardCloseoutId(Long awardCloseoutId) {
        this.awardCloseoutId = awardCloseoutId;
    }    
    
    /**
     * 
     * This method...
     * @return
     */
    public Date getFinalSubmissionDate() {
        return finalSubmissionDate;
    }

    /**
     * 
     * This method...
     * @param finalSubmissionDate
     */
    public void setFinalSubmissionDate(Date finalSubmissionDate) {
        this.finalSubmissionDate = finalSubmissionDate;
    }    

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("awardCloseoutId", this.getAwardCloseoutId());         
        hashMap.put("finalSubmissionDate", this.getFinalSubmissionDate());
        hashMap.put("closeoutReportCode", this.getCloseoutReportCode());
        hashMap.put("closeoutReportName", this.getCloseoutReportName());
        hashMap.put("dueDate", this.getDueDate());
        hashMap.put("multiple", this.isMultiple());
        return hashMap;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getCloseoutReportCode() {
        return closeoutReportCode;
    }

    /**
     * 
     * This method...
     * @param closeoutReportCode
     */
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

    /**
     * @see java.lang.Object#hashCode()
     */
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
    
    /**
     * 
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState(){
        awardCloseoutId = null;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof AwardCloseout))
            return false;
        final AwardCloseout other = (AwardCloseout) obj;
        if (closeoutReportCode == null) {
            if (other.closeoutReportCode != null)
                return false;
        }
        else if (!closeoutReportCode.equals(other.closeoutReportCode))
            return false;
        if (dueDate == null) {
            if (other.dueDate != null)
                return false;
        }
        else if (!dueDate.equals(other.dueDate))
            return false;
        if (finalSubmissionDate == null) {
            if (other.finalSubmissionDate != null)
                return false;
        }
        else if (!finalSubmissionDate.equals(other.finalSubmissionDate))
            return false;
        if (multiple != other.multiple)
            return false;
        return true;
    }
    
    
}