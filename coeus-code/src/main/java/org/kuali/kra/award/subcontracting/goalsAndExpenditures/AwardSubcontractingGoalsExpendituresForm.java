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

import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmounts;
import org.kuali.rice.kns.web.struts.form.KualiForm;

@SuppressWarnings("deprecation")
public class AwardSubcontractingGoalsExpendituresForm extends KualiForm {


    private static final long serialVersionUID = 4542622372669064380L;
    
    private boolean readOnly;
    private String awardNumber;
    private String awardId;
    private boolean displayGoalsExpendituresDetails;
    private boolean containingUnsavedChanges;
    
    private AwardSubcontractingBudgetedGoals awardSubcontractingBudgetedGoals;
    
    private SubcontractingExpenditureCategoryAmounts subcontractingExpenditureCategoryAmounts;  
    

    public AwardSubcontractingGoalsExpendituresForm() {
        this.setAwardSubcontractingBudgetedGoals(new AwardSubcontractingBudgetedGoals());
        this.setDisplayGoalsExpendituresDetails(true);
        
        this.setSubcontractingExpenditureCategoryAmounts(new SubcontractingExpenditureCategoryAmounts());
    }
    
    
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardSubcontractingBudgetedGoals(AwardSubcontractingBudgetedGoals awardSubcontractingBudgetedGoals) {
        this.awardSubcontractingBudgetedGoals = awardSubcontractingBudgetedGoals;
    }

    public AwardSubcontractingBudgetedGoals getAwardSubcontractingBudgetedGoals() {
        return awardSubcontractingBudgetedGoals;
    }


    public void setDisplayGoalsExpendituresDetails(boolean displayGoalsExpendituresDetails) {
        this.displayGoalsExpendituresDetails = displayGoalsExpendituresDetails;
    }


    public boolean isDisplayGoalsExpendituresDetails() {
        return displayGoalsExpendituresDetails;
    }


    public void setContainingUnsavedChanges(boolean containingUnsavedChanges) {
        this.containingUnsavedChanges = containingUnsavedChanges;
    }


    public boolean isContainingUnsavedChanges() {
        return containingUnsavedChanges;
    }


    
    public void setSubcontractingExpenditureCategoryAmounts(SubcontractingExpenditureCategoryAmounts subcontractingExpenditureCategoryAmounts) {
        this.subcontractingExpenditureCategoryAmounts = subcontractingExpenditureCategoryAmounts;
    }


    public SubcontractingExpenditureCategoryAmounts getSubcontractingExpenditureCategoryAmounts() {
        return subcontractingExpenditureCategoryAmounts;
    }

}