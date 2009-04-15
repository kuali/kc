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

import org.kuali.kra.award.bo.AwardDirectFandADistribution;
import org.kuali.kra.award.document.AwardDocument;

/**
 * This class supports the AwardForm class
 */
public class DirectFandADistributionFormHelper {

    private AwardForm parent;
    private AwardDirectFandADistribution newAwardDirectFandADistribution;
    
    /**
     * Constructs a DirectFandADistributionFormHelper
     * @param parent
     */
    public DirectFandADistributionFormHelper(AwardForm parent) {
        this.parent = parent;
        setNewAwardDirectFandADistribution (new AwardDirectFandADistribution());
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardDirectFandADistribution = new AwardDirectFandADistribution(); 
    }
    
    /**
     * Gets the parent attribute. 
     * @return Returns the parent.
     */
    public AwardForm getParent() {
        return parent;
    }
    
    /**
     * Sets the parent attribute value.
     * @param parent The parent to set.
     */
    public void setParent(AwardForm parent) {
        this.parent = parent;
    }
    
    
    /**
     * This method returns the AwardDocument.
     * @return
     */
    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }

    /**
     * Gets the newAwardDirectFandADistribution attribute. 
     * @return Returns the newAwardDirectFandADistribution.
     */
    public AwardDirectFandADistribution getNewAwardDirectFandADistribution() {
        return newAwardDirectFandADistribution;
    }

    /**
     * Sets the newAwardDirectFandADistribution attribute value.
     * @param newAwardDirectFandADistribution The newAwardDirectFandADistribution to set.
     */
    public void setNewAwardDirectFandADistribution(AwardDirectFandADistribution newAwardDirectFandADistribution) {
        this.newAwardDirectFandADistribution = newAwardDirectFandADistribution;
    }
    
    
}
