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
package org.kuali.kra.award.document;

import java.util.ArrayList;

import org.kuali.core.web.format.Formatter;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetPrintForm;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.infrastructure.RateDecimalFormatter;

public class AwardDocument extends ResearchDocumentBase {
    private static final String DETAIL_TYPE_CODE = "D";
    private static final String AWARD_NAMESPACE_CODE = "KRA-A";
    
    public AwardDocument(){
        super();        
    }
}
