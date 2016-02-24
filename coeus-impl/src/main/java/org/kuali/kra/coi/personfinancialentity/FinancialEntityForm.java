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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.document.Document;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 * This class is the struts form for financial entity maintenance
 */
public class FinancialEntityForm  extends KualiForm {
    private static final long serialVersionUID = -7825455832928793712L;
    private FinancialEntityHelper financialEntityHelper;
    // textarea needs formKey & document.  
    private String formKey;
    private Document document;
    private boolean readOnly;
    private FinancialEntitySummaryHelper financialEntitySummaryHelper;
    private FinancialEntitySummaryBean currentSummary;
    private FinancialEntitySummaryBean previousSummary;
    private String coiDocId;

    public FinancialEntityForm() {
        super();
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
       setFinancialEntityHelper(new FinancialEntityHelper(this));
       setFinancialEntitySummaryHelper(new FinancialEntitySummaryHelper(this));
       currentSummary = new FinancialEntitySummaryBean(this);
       previousSummary = new FinancialEntitySummaryBean(this);

    }

    public FinancialEntitySummaryBean getCurrentSummary() {
        return currentSummary;
    }

    public void setCurrentSummary(FinancialEntitySummaryBean currentSummary) {
        this.currentSummary = currentSummary;
    }

    public FinancialEntitySummaryBean getPreviousSummary() {
        return previousSummary;
    }

    public void setPreviousSummary(FinancialEntitySummaryBean previousSummary) {
        this.previousSummary = previousSummary;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public void reset(final ActionMapping mapping, final HttpServletRequest request) {
        super.reset(mapping, request);
//        getFinancialEntityHelper().setEditEntityIndex(-1);
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    
    @Override
    public void populate(HttpServletRequest request) {
        financialEntityHelper.refreshData();
        super.populate(request);
        populateFalseCheckboxes(request);
    }

    public FinancialEntityHelper getFinancialEntityHelper() {
        return financialEntityHelper;
    }

    public void setFinancialEntityHelper(FinancialEntityHelper financialEntityHelper) {
        this.financialEntityHelper = financialEntityHelper;
    }

    private void setFinancialEntitySummaryHelper(FinancialEntitySummaryHelper financialEntitySummaryHelper) {
       this.financialEntitySummaryHelper = financialEntitySummaryHelper;
        
    }
    
    @Override
    public void reset(ActionMapping mapping, ServletRequest request) {

        super.reset(mapping, request);
    }

    /**
     * Uses the "checkboxToReset" parameter to find checkboxes which had not been
     * populated in the request and attempts to populate them
     * populateFalseCheckboxes(request) is only for trxdocform, so we have to do it here for this non-trxform
     * @param request the request to populate
     */
    @SuppressWarnings("unchecked")
    private void populateFalseCheckboxes(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.get("checkboxToReset") != null) {
            final String[] checkboxesToReset = request.getParameterValues("checkboxToReset");
            if(checkboxesToReset != null && checkboxesToReset.length > 0) {
                for (int i = 0; i < checkboxesToReset.length; i++) {
                    String propertyName = (String) checkboxesToReset[i];
                    if ( !StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) == null ) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_FALSE_STR_VALUE_DISPLAY, parameterMap);
                    }  
                    else if ( !StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) != null && parameterMap.get(propertyName).length >= 1 && parameterMap.get(propertyName)[0].equalsIgnoreCase("on") ) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_TRUE_STR_VALUE_DISPLAY, parameterMap); 
                    }
                }
            }
        }
    }

    public FinancialEntitySummaryHelper getFinancialEntitySummaryHelper() {
        return financialEntitySummaryHelper;
    }

    public String getCoiDocId() {
        return coiDocId;
    }

    public void setCoiDocId(String coiDocId) {
        this.coiDocId = coiDocId;
    }

}
