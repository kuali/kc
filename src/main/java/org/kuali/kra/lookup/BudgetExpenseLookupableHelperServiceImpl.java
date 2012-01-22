/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * This class implements a custom lookup for S2S Grants.gov Opportunity Lookup
 */
@Transactional
public class BudgetExpenseLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    private static final String KFS_ON_PARM_NMSPC_CD = "KC-AWARD";
    private static final String KFS_ON_PARM_DTL_TYP_CD = "Document";
    private static final String KFS_ON_PARM_NM = "FIN_SYSTEM_INTEGRATION_ON";
    private static final String KFS_ON_OFF_VALUE = "OFF";
    private static final String KFS_FIELD_NAME = "financialObjectCode";
 

    @Override
    public List<Row> getRows() {
        List<Row> oldRows = super.getRows();
        
        String kfsOnParameterValue = getParameterService().getParameterValueAsString(KFS_ON_PARM_NMSPC_CD, KFS_ON_PARM_DTL_TYP_CD, KFS_ON_PARM_NM);
        
        List<Row> rows = new ArrayList<Row>();
        if (!StringUtils.equals(kfsOnParameterValue, KFS_ON_OFF_VALUE)) {
            rows.addAll(oldRows);
        } else {
            for (Row oldRow : oldRows) {
                Row row = new Row();
                List<Field> fields = new ArrayList<Field>();
                for (Field oldField : oldRow.getFields()) {
                    if (!StringUtils.equals(oldField.getPropertyName(), KFS_FIELD_NAME)) {
                        fields.add(oldField);
                    }
                }
                row.setFields(fields);
                rows.add(row);
            }
        }
        
        return rows;
    }

    @Override
    public List<Column> getColumns() {
        List<Column> oldColumns = super.getColumns();
        
        String kfsOnParameterValue = getParameterService().getParameterValueAsString(KFS_ON_PARM_NMSPC_CD, KFS_ON_PARM_DTL_TYP_CD, KFS_ON_PARM_NM);
        
        List<Column> columns = new ArrayList<Column>();
        if (!StringUtils.equals(kfsOnParameterValue, KFS_ON_OFF_VALUE)) {
            columns.addAll(oldColumns);
        } else {
            for (Column oldColumn : oldColumns) {
                if (!StringUtils.equals(oldColumn.getPropertyName(), KFS_FIELD_NAME)) {
                    columns.add(oldColumn);
                }
            }
        }
        
        return columns;
    }
    
    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     * It calls the S2sService#searchOpportunity service to look up the opportunity
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        //LookupUtils.removeHiddenCriteriaFields( getBusinessObjectClass(), fieldValues );
        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
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
            KNSGlobalVariables.getMessageList().add(Constants.BUDGET_EXPENSE_LOOKUP_MESSAGE1);
        }
        //KNSGlobalVariables.getMessageList().add(categoryTypeName);
        //KNSGlobalVariables.getMessageList().add(Constants.BUDGET_EXPENSE_LOOKUP_MESSAGE2);
        return searchResultsReturn;
    }
}
