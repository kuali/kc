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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
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
