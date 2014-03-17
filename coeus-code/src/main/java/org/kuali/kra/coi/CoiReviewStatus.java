/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class CoiReviewStatus extends KcPersistableBusinessObjectBase {
    


    private static final long serialVersionUID = -7843513213298067208L;
    private String reviewStatusCode; 
    private String description; 
    private boolean statusUpdatedOnlyByAction;
    
    public static final String IN_PROGRESS = "1";
    public static final String SUBMITTED_FOR_REVIEW = "2";
    public static final String ASSIGNED_TO_REVIEWER = "3";
    public static final String ASSIGNED_REVIEW_COMPLETE = "4";
    public static final String REVIEW_COMPLETE = "5";
    
    public CoiReviewStatus() { 

    } 
    
    public String getReviewStatusCode() {
        return reviewStatusCode;
    }

    public void setReviewStatusCode(String reviewStatusCode) {
        this.reviewStatusCode = reviewStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatusUpdatedOnlyByAction() {
        return statusUpdatedOnlyByAction;
    }

    public void setStatusUpdatedOnlyByAction(boolean statusUpdatedOnlyByAction) {
        this.statusUpdatedOnlyByAction = statusUpdatedOnlyByAction;
    }

}