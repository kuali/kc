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
package org.kuali.kra.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * This class implements a custom lookup for S2S Grants.gov Opportunity Lookup
 */
@Transactional
public class BudgetExpenseLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     * It calls the S2sService#searchOpportunity service to look up the opportunity
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        //LookupUtils.removeHiddenCriteriaFields( getBusinessObjectClass(), fieldValues );
        setBackLocation(fieldValues.get(KNSConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KNSConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KNSConstants.REFERENCES_TO_REFRESH));
        String budgetCategoryTypeCode = fieldValues.get("budgetCategoryTypeCode");
        fieldValues.remove("budgetCategoryTypeCode");
        List searchResults;
        List searchResultsReturn = new ArrayList();
        String categoryTypeName = null;
        // handle onoffcampusflag
        if (fieldValues.get(Constants.ON_OFF_CAMPUS_FLAG).equalsIgnoreCase("Y")) {
            fieldValues.put(Constants.ON_OFF_CAMPUS_FLAG, "N");
       } else if (fieldValues.get(Constants.ON_OFF_CAMPUS_FLAG).equalsIgnoreCase("N")) {
            fieldValues.put(Constants.ON_OFF_CAMPUS_FLAG, "F");
       }

        searchResults = super.getSearchResults(fieldValues);
        
        for (Iterator iterator = searchResults.iterator(); iterator.hasNext();) {
            CostElement costElement = (CostElement) iterator.next();
            costElement.refreshReferenceObject("budgetCategory");
            // TODO : need more test for ce maint doc which will display all ce and budgetcategorytypecode=""
            if(StringUtils.isBlank(budgetCategoryTypeCode) || StringUtils.equalsIgnoreCase(costElement.getBudgetCategory().getBudgetCategoryTypeCode(),budgetCategoryTypeCode)){
                searchResultsReturn.add(costElement);
                // TODO : what is categoryTypeName for ?
                if(categoryTypeName==null){
                    categoryTypeName = costElement.getBudgetCategory().getBudgetCategoryType().getDescription();
                }
            }
        }       
        if(StringUtils.isNotBlank(budgetCategoryTypeCode)) {
            GlobalVariables.getMessageList().add(Constants.BUDGET_EXPENSE_LOOKUP_MESSAGE1);
        }
        //GlobalVariables.getMessageList().add(categoryTypeName);
        //GlobalVariables.getMessageList().add(Constants.BUDGET_EXPENSE_LOOKUP_MESSAGE2);
        return searchResultsReturn;
    }
}
