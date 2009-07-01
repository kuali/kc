/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.polling;

import org.kuali.kra.s2s.bo.S2sAppSubmission;

/**
 * 
 * This class is a wrapper around {@link S2sAppSubmission} business object. IT helps in storing some additional information related
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
     * @param {@link S2sAppSubmission)
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
