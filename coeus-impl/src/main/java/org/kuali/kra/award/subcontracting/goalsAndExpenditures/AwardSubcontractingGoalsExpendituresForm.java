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
