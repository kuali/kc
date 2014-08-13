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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;

/**
 * Creates a BudgetCommonService instance.
 */
public final class BudgetCommonServiceFactory {
    
    private BudgetCommonServiceFactory() {
        throw new UnsupportedOperationException("do not instantiate");
    }
    
    /**
     * Creates an instance of BudgetCommonService by looking at the classname.
     * @return
     */
    public static BudgetCommonService createInstance(BudgetParent parentBudget) {
        if (parentBudget.getClass().equals(Award.class)) {
            return KcServiceLocator.getService(AwardBudgetService.class);
        } else {
            return KcServiceLocator.getService(ProposalBudgetService.class);
        }
    }

}