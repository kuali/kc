/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.common.specialreview.web.struts.form;

import java.io.Serializable;

import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Defines the base class of all Special Review Helpers.
 * @param <T> Special Review
 */
public abstract class SpecialReviewHelperBase<T extends SpecialReview<? extends SpecialReviewExemption>> implements Serializable {

    private static final long serialVersionUID = 5881897247177996736L;

    private T newSpecialReview;

    private boolean canModifySpecialReview;
    
    public T getNewSpecialReview() {
        return newSpecialReview;
    }
    
    public void setNewSpecialReview(T newSpecialReview) {
        this.newSpecialReview = newSpecialReview;
    }
    
    public boolean getCanModifySpecialReview() {
        return canModifySpecialReview;
    }
    
    /**
     * Prepares the user view by initializing the permissions.
     */
    public void prepareView() {
        initializePermissions();
    }
    
    /**
     * Initialize the permissions for viewing/editing the Special Review web page.
     */
    private void initializePermissions() {
        canModifySpecialReview = hasModifySpecialReviewPermission(getUserIdentifier());
    }
    
    /**
     * Can the current user modify Special Review.
     * @param principalId the Id of the user for which to check permissions
     * @return true if the current user can modify Special Review, false otherwise
     */
    protected abstract boolean hasModifySpecialReviewPermission(String principalId);
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
}