/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget.person;

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.TbnPerson;
import org.kuali.coeus.propdev.impl.budget.nonpersonnel.AddProjectBudgetLineItemHelper;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.DataObjectService;


public class AddProjectPersonnelHelper extends AddProjectBudgetLineItemHelper {

    private BudgetPerson editBudgetPerson;
    private DataObjectService dataObjectService;
    private List<TbnPerson> tbnPersons;
    private BudgetPersonnelDetails budgetPersonnelDetail;
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
        budgetPersonnelDetail = new BudgetPersonnelDetails();
        budgetPersonGroupName="";
        tbnPersons = new ArrayList<TbnPerson>();
    }
    
	public BudgetPerson getEditBudgetPerson() {
		return editBudgetPerson;
	}

	public void setEditBudgetPerson(BudgetPerson editBudgetPerson) {
		this.editBudgetPerson = editBudgetPerson;
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

	public BudgetPersonnelDetails getBudgetPersonnelDetail() {
		return budgetPersonnelDetail;
	}

	public void setBudgetPersonnelDetail(
			BudgetPersonnelDetails budgetPersonnelDetail) {
		this.budgetPersonnelDetail = budgetPersonnelDetail;
	}

	public String getBudgetPersonGroupName() {
		return budgetPersonGroupName;
	}

	public void setBudgetPersonGroupName(String budgetPersonGroupName) {
		this.budgetPersonGroupName = budgetPersonGroupName;
	}
	
	public String getJobTitle() {
		if(getEditBudgetPerson().getJobCode() != null) {
			getDataObjectService().wrap(getEditBudgetPerson()).fetchRelationship("jobCodeRef");
		}
		return getEditBudgetPerson().getJobCodeRef() != null ? getEditBudgetPerson().getJobCodeRef().getJobTitle() : "";
	}

	public String getAppointmentType() {
		getDataObjectService().wrap(getBudgetPersonnelDetail()).fetchRelationship("budgetPerson");
		BudgetPerson budgetPerson = getBudgetPersonnelDetail().getBudgetPerson();
		return budgetPerson != null ? budgetPerson.getAppointmentType().getDescription() : "";
	}

}
