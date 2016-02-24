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
package org.kuali.kra.award.commitments;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.coeus.common.framework.costshare.CostShareFunctions;
import org.kuali.coeus.common.framework.costshare.CostShareService;

import java.io.Serializable;

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

    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }

    public Object getData() {
        return getNewAwardCostShare();
    }
    
    @Override
    public String getProjectPeriodLabel() {
        String label = KcServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }
}
