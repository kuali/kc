/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.form;

import java.io.Serializable;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardApprovedSubaward;

/**
 * This class...
 */
public class ApprovedSubawardFormHelper implements Serializable {
    
    private AwardForm parent;
    
    private AwardApprovedSubaward newAwardApprovedSubaward;
    
    /**
     * Constructs a ApprovedSubawardFormHelper
     * @param parent
     */
    ApprovedSubawardFormHelper(AwardForm parent) {
        this.parent = parent;
        this.newAwardApprovedSubaward = new AwardApprovedSubaward();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardApprovedSubaward = new AwardApprovedSubaward(); 
    }

    /**
     * Gets the newAwardApprovedSubaward attribute. 
     * @return Returns the newAwardApprovedSubaward.
     */
    public AwardApprovedSubaward getNewAwardApprovedSubaward() {
        return newAwardApprovedSubaward;
    }

    /**
     * Sets the newAwardApprovedSubaward attribute value.
     * @param newAwardApprovedSubaward The newAwardApprovedSubaward to set.
     */
    public void setNewAwardApprovedSubaward(AwardApprovedSubaward newAwardApprovedSubaward) {
        this.newAwardApprovedSubaward = newAwardApprovedSubaward;
    }

    /**
     * This method...
     * @return
     */
    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }
    
    /**
     * This method...
     * @return
     */
    public Object getData() {
        return getNewAwardApprovedSubaward();
    }
}

