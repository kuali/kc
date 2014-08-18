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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("deprecation")
public class AwardSubcontractingGoalsExpendituresRule extends KcTransactionalDocumentRuleBase {

    private String awardId;
    private DictionaryValidationService dictionaryValidationService;
    private Lookupable awardLookupable;
    private final static String AWARD_NUMBER = "awardNumber";
    private final static String SUB_PLAN_FLAG = "subPlanFlag";
    private final static String SUB_PLAN_FLAG_VAL = "Y";
    private final static String AWARD_LOOKUPABLE = "awardLookupable";

    
    @SuppressWarnings({ "unchecked" })
    public boolean validateAwardNumber(String awardNumber) {        
        boolean rulePassed = false;
        String ddEntryName = AwardSubcontractingBudgetedGoals.class.getSimpleName();
        // first check that it was provided
        this.getDictionaryValidationService().validateAttributeRequired(ddEntryName, AWARD_NUMBER, awardNumber, false, AWARD_NUMBER);
        rulePassed = GlobalVariables.getMessageMap().hasNoErrors();

        if (rulePassed) {
            rulePassed = false;
            // then check proper formatting according to DD entry
            this.getDictionaryValidationService().validateAttributeFormat(ddEntryName, AWARD_NUMBER, awardNumber, AWARD_NUMBER);
            rulePassed = GlobalVariables.getMessageMap().hasNoErrors();
        }

        if (rulePassed) {
            rulePassed = false;
            // now check if this award number belongs to a valid award (subPlan = "Y")
            // we will just re-use the award lookupable service here -- DRY!
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(SUB_PLAN_FLAG, SUB_PLAN_FLAG_VAL);
            fieldValues.put(AWARD_NUMBER, awardNumber);            
            List<Award> awardsFound = (List<Award>) this.getAwardLookupable().getSearchResults(fieldValues);
            if ((awardsFound != null) && (!(awardsFound.isEmpty()))) {
                rulePassed = true;
                awardId = awardsFound.get(0).getAwardId().toString();
            }
            else {
                // put the error message in message map
                this.getErrorReporter().reportError(AWARD_NUMBER, KeyConstants.SUB_PLAN_AWARD_NOT_FOUND, awardNumber);
            }
        }
        return rulePassed;
    }
    
    
    public DictionaryValidationService getDictionaryValidationService() {
        if (this.dictionaryValidationService == null) {
            this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return this.dictionaryValidationService;
    }

    public void setDictionaryValidationService(DictionaryValidationService dictionaryValidationService) {
        this.dictionaryValidationService = dictionaryValidationService;
    }
    

    public String getAwardId() {
        return this.awardId;
    }


    public void setAwardLookupable(Lookupable awardLookupable) {
        this.awardLookupable = awardLookupable;
    }


    public Lookupable getAwardLookupable() {
        if(this.awardLookupable == null) {
            this.awardLookupable = KNSServiceLocator.getLookupable(AWARD_LOOKUPABLE);
            this.awardLookupable.setBusinessObjectClass(Award.class);
        }
        return this.awardLookupable;
    }
}
