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
package org.kuali.coeus.propdev.impl.budget.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.TbnPerson;
import org.kuali.coeus.propdev.impl.core.AddLineHelper;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.DataObjectService;


public class AddProjectPersonnelHelper extends AddLineHelper {


    private BudgetPerson editBudgetPerson;
    private String editLineIndex;
    private DataObjectService dataObjectService;
    private List<TbnPerson> tbnPersons;
    private BudgetLineItem budgetLineItem;
    private BudgetPersonnelDetails budgetPersonnelDetail;
    private BudgetPeriod currentTabBudgetPeriod;
    private String budgetPersonGroupName;
    
    public AddProjectPersonnelHelper() {
       super();
       initPersonDetails();
    }
    
    public void reset() {
        super.reset();
        initPersonDetails();
    }
    
    private void initPersonDetails() {
        editBudgetPerson = new BudgetPerson();
        editLineIndex = null;
        budgetLineItem = new BudgetLineItem();
        budgetPersonnelDetail = new BudgetPersonnelDetails();
        budgetPersonGroupName="";
        currentTabBudgetPeriod = new BudgetPeriod();
    }
    
	public BudgetPerson getEditBudgetPerson() {
		return editBudgetPerson;
	}

	public void setEditBudgetPerson(BudgetPerson editBudgetPerson) {
		this.editBudgetPerson = editBudgetPerson;
	}

	public String getEditLineIndex() {
		return editLineIndex;
	}

	public void setEditLineIndex(String editLineIndex) {
		this.editLineIndex = editLineIndex;
	}

	public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
        	dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public List<TbnPerson> getTbnPersons() {
		if(tbnPersons.isEmpty()) {
			this.tbnPersons = getDataObjectService().findAll(TbnPerson.class).getResults();
		}
		return tbnPersons;
	}

	public void setTbnPersons(List<TbnPerson> tbnPersons) {
		this.tbnPersons = tbnPersons;
	}

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}

	public BudgetPersonnelDetails getBudgetPersonnelDetail() {
		return budgetPersonnelDetail;
	}

	public void setBudgetPersonnelDetail(
			BudgetPersonnelDetails budgetPersonnelDetail) {
		this.budgetPersonnelDetail = budgetPersonnelDetail;
	}

	public BudgetPeriod getCurrentTabBudgetPeriod() {
		return currentTabBudgetPeriod;
	}

	public void setCurrentTabBudgetPeriod(BudgetPeriod currentTabBudgetPeriod) {
		this.currentTabBudgetPeriod = currentTabBudgetPeriod;
	}

	public String getBudgetPersonGroupName() {
		return budgetPersonGroupName;
	}

	public void setBudgetPersonGroupName(String budgetPersonGroupName) {
		this.budgetPersonGroupName = budgetPersonGroupName;
	}

}
