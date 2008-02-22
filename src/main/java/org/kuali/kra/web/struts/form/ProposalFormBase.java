/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.web.struts.form;

/**
 * This class contains methods common to ProposalDevelopment and Budget forms.
 */
public class ProposalFormBase extends KraTransactionalDocumentFormBase {
    
    private String newBudgetVersionName;
    private Integer finalBudgetVersion;
    
    public Integer getFinalBudgetVersion() {
        return finalBudgetVersion;
    }
    public void setFinalBudgetVersion(Integer finalBudgetVersion) {
        this.finalBudgetVersion = finalBudgetVersion;
    }
    public String getNewBudgetVersionName() {
        return newBudgetVersionName;
    }
    public void setNewBudgetVersionName(String newBudgetVersionName) {
        this.newBudgetVersionName = newBudgetVersionName;
    }

}
