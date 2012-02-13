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
package org.kuali.kra.award.commitments;

import java.io.Serializable;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.costshare.CostShareFunctions;
import org.kuali.kra.costshare.CostShareService;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This class supports the AwardForm class
 */
public class CostShareFormHelper implements Serializable, CostShareFunctions { 
    private AwardForm parent;
    
    private AwardCostShare newAwardCostShare;
    
    /**
     * Constructs a CostShareFormHelper
     * @param parent
     */
    public CostShareFormHelper(AwardForm parent) {
        this.parent = parent;
        init();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardCostShare = new AwardCostShare(); 
    }

    /**
     * Gets the newAwardCostShare attribute. 
     * @return Returns the newAwardCostShare.
     */
    public AwardCostShare getNewAwardCostShare() {
        return newAwardCostShare;
    }

    /**
     * Sets the newAwardAwardCostShare attribute value.
     * @param newAwardAwardCostShare The newAwardAwardCostShare to set.
     */
    public void setNewAwardCostShare(AwardCostShare newAwardCostShare) {
        this.newAwardCostShare = newAwardCostShare;
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
        return getNewAwardCostShare();
    }
    
    /**
     * 
     * @see org.kuali.kra.costshare.CostShareFunctions#getProjectPeriodLabel()
     */
    public String getProjectPeriodLabel() {
        String label = KraServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }
}
