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
package org.kuali.coeus.propdev.impl.s2s.schedule;


import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;

/**
 * 
 * This class is a wrapper around {@link org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission} business object. IT helps in storing some additional information related
 * to scheduling that is in addition to the opportunity submission information that is available in the business object.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionData {
    private char acType;
    private S2sAppSubmission s2sAppSubmission;
    private String sortId;


    /**
     * Getter for BO {@link S2sAppSubmission}
     * 
     * @return {@link S2sAppSubmission)
     */
    public S2sAppSubmission getS2sAppSubmission() {
        return s2sAppSubmission;
    }

    /**
     * Setter for BO {@link S2sAppSubmission}
     * 
     * @param {@link S2sAppSubmission}
     */
    public void setS2sAppSubmission(S2sAppSubmission appSubmission) {
        s2sAppSubmission = appSubmission;
    }


    /**
     * Getter for property acType.
     * 
     * @return Value of property acType.
     */
    public char getAcType() {
        return acType;
    }

    /**
     * Setter for property acType.
     * 
     * @param acType New value of property acType.
     */
    public void setAcType(char acType) {
        this.acType = acType;
    }

    /**
     * Getter for property sortId.
     * 
     * @return Value of property sortId.
     */
    public java.lang.String getSortId() {
        return sortId;
    }

    /**
     * Setter for property sortId.
     * 
     * @param sortId New value of property sortId.
     */
    public void setSortId(java.lang.String sortId) {
        this.sortId = sortId;
    }
}
