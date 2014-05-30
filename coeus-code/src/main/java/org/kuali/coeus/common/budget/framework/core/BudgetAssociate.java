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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class is for associating common Budget properties to the extended Budget children BOs
 */
public abstract class BudgetAssociate extends KcPersistableBusinessObjectBase implements IdentifiableBudget {


    private static final long serialVersionUID = 3219654486879418421L;

    private Long budgetId;

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
}
