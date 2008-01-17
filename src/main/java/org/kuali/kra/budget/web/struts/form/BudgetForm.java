/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

public class BudgetForm extends KraTransactionalDocumentFormBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetForm.class);

    private String newBudgetVersionName;
    private Integer finalBudgetVersion;
    
    public BudgetForm() {
        super();
        this.setDocument(new BudgetDocument());
        initialize();
    }

    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.budget.document.BudgetDocument.class.getName())).getHeaderTabNavigation());
    }

    public BudgetDocument getBudgetDocument() {
        return (BudgetDocument) this.getDocument();
    }
    
    public String getNewBudgetVersionName() {
        return newBudgetVersionName;
    }

    public void setNewBudgetVersionName(String newBudgetVersionName) {
        this.newBudgetVersionName = newBudgetVersionName;
    }
    
    public Integer getFinalBudgetVersion() {
        return finalBudgetVersion;
    }

    public void setFinalBudgetVersion(Integer finalBudgetVersion) {
        this.finalBudgetVersion = finalBudgetVersion;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        // if there are more ...
    }

}
