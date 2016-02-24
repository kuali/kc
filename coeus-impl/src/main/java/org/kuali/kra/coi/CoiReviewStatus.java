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
