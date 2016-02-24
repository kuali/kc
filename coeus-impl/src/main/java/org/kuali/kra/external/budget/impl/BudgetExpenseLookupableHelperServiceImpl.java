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
package org.kuali.kra.external.budget.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component("budgetExpenseLookupableHelperService")
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BudgetExpenseLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {
    
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

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        String budgetCategoryTypeCode = fieldValues.get("budgetCategoryTypeCode");
        fieldValues.remove("budgetCategoryTypeCode");
        String categoryTypeName = null;
        // handle onoffcampusflag
        if (fieldValues.get(Constants.ON_OFF_CAMPUS_FLAG).equalsIgnoreCase("Y")) {
            fieldValues.put(Constants.ON_OFF_CAMPUS_FLAG, "N");
       } else if (fieldValues.get(Constants.ON_OFF_CAMPUS_FLAG).equalsIgnoreCase("N")) {
            fieldValues.put(Constants.ON_OFF_CAMPUS_FLAG, "F");
       }

        List<CostElement> searchResults = (List<CostElement>) super.getSearchResults(fieldValues);
        List<CostElement> searchResultsReturn = CollectionUtils.createCorrectImplementationForCollection(searchResults);
        for (Iterator iterator = searchResults.iterator(); iterator.hasNext();) {
            CostElement costElement = (CostElement) iterator.next();
            costElement.refreshReferenceObject("budgetCategory");
            if(StringUtils.isBlank(budgetCategoryTypeCode) || StringUtils.equalsIgnoreCase(costElement.getBudgetCategory().getBudgetCategoryTypeCode(),budgetCategoryTypeCode)){
                searchResultsReturn.add(costElement);
                if(categoryTypeName==null){
                    categoryTypeName = costElement.getBudgetCategory().getBudgetCategoryType().getDescription();
                }
            }
        }       
        if(StringUtils.isNotBlank(budgetCategoryTypeCode)) {
            KNSGlobalVariables.getMessageList().add(Constants.BUDGET_EXPENSE_LOOKUP_MESSAGE1);
        }
        return searchResultsReturn;
    }
}
