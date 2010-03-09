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
package org.kuali.kra.proposaldevelopment.budget.bo;

import org.kuali.kra.budget.core.Budget;

/**
 * This class...
 */
public class ProposalDevelopmentBudgetExt extends Budget {
    private Boolean finalVersionFlag;
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8234453927894053540L;
    /**
     * Gets the finalVersionFlag attribute. 
     * @return Returns the finalVersionFlag.
     */
    public Boolean getFinalVersionFlag() {
        return finalVersionFlag;
    }
    /**
     * Sets the finalVersionFlag attribute value.
     * @param finalVersionFlag The finalVersionFlag to set.
     */
    public void setFinalVersionFlag(Boolean finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

}
