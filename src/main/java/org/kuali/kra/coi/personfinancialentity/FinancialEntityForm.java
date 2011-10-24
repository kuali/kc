/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.personfinancialentity;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.web.struts.form.KualiForm;

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
    
    /**
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        populateFalseCheckboxes(request);
    }

    public FinancialEntityHelper getFinancialEntityHelper() {
        return financialEntityHelper;
    }

    public void setFinancialEntityHelper(FinancialEntityHelper financialEntityHelper) {
        this.financialEntityHelper = financialEntityHelper;
    }

    @Override
    public void reset(ActionMapping mapping, ServletRequest request) {
        // TODO Auto-generated method stub
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

    public String getCoiDocId() {
        return coiDocId;
    }

    public void setCoiDocId(String coiDocId) {
        this.coiDocId = coiDocId;
    }


}
