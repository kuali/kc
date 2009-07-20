/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import org.kuali.kra.budget.bo.BudgetProposalLaRate;
/**
 * 
 * This class represents INSTITUTE_LA_RATE record
 */
public class InstituteLaRate extends AbstractInstituteRate {

private static final long serialVersionUID = 6467972635670502396L;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected BudgetProposalLaRate createBudgetRate() {
        return new BudgetProposalLaRate();
    }
}
