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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;

import java.sql.Date;
import java.util.List;


public interface BudgetParent {
    public String getBudgetStatus();
    public void setBudgetStatus(String budgetStatus);
    public String getActivityTypeCode();
    public Date getRequestedStartDateInitial();
    public Date getRequestedEndDateInitial();
    public ActivityType getActivityType();
    public String getUnitNumber();
    public Unit getUnit();
    public List<PersonRolodex> getPersonRolodexList();
    public ContactRole getProposalNonEmployeeRole(Integer rolodexId);
    public PersonRolodex getProposalEmployee(String personId);
    public PersonRolodex getProposalNonEmployee(Integer rolodexId);
    public ContactRole getProposalEmployeeRole(String personId);
    public String getHierarchyStatus();
    public String getDefaultBudgetStatusParameter();
    public boolean isParentInHierarchyComplete();
    
    public String getParentNumber();
    public String getParentTitle();
    public String getParentPIName();
    public String getIsOwnedByUnit();
    public Integer getParentInvestigatorFlag(String personId, Integer flag);
    public String  getParentTypeName();
	public boolean isProposalBudget();
	
	public BudgetParentDocument<? extends BudgetParent> getDocument();
	public List<? extends Budget> getBudgets();
	public Budget getNewBudget();
	public Integer getNextBudgetVersionNumber();
	
}
