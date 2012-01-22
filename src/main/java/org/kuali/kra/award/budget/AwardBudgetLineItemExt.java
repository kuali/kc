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
package org.kuali.kra.award.budget;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;

/**
 * This class...
 */
public class AwardBudgetLineItemExt extends BudgetLineItem {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6909566795823678487L;
    private BudgetDecimal obligatedAmount;
    /**
     * 
     * This method is to create new BudgetpersonnelDetails object
     * @return
     */
    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new AwardBudgetPersonnelDetailsExt();
    }
    /**
     * 
     * @see org.kuali.kra.budget.nonpersonnel.BudgetLineItem#getNewBudgetLineItemCalculatedAmount()
     */
    public AbstractBudgetCalculatedAmount getNewBudgetLineItemCalculatedAmount() {
        return new AwardBudgetLineItemCalculatedAmountExt();
    }

    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public BudgetDecimal getObligatedAmount() {
        return obligatedAmount==null?BudgetDecimal.ZERO:obligatedAmount;
    }
    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(BudgetDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
    
}
