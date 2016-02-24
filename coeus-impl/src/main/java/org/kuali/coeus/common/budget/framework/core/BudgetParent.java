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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;

import java.sql.Date;
import java.util.List;


public interface BudgetParent {
    String getBudgetStatus();
    void setBudgetStatus(String budgetStatus);
    String getActivityTypeCode();
    Date getRequestedStartDateInitial();
    Date getRequestedEndDateInitial();
    ActivityType getActivityType();
    String getUnitNumber();
    Unit getUnit();
    List<? extends PersonRolodex> getPersonRolodexList();
    ContactRole getProposalNonEmployeeRole(Integer rolodexId);
    PersonRolodex getProposalEmployee(String personId);
    PersonRolodex getProposalNonEmployee(Integer rolodexId);
    ContactRole getProposalEmployeeRole(String personId);
    String getHierarchyStatus();
    String getDefaultBudgetStatusParameter();
    boolean isParentInHierarchyComplete();
    
    String getParentNumber();
    String getParentTitle();
    String getParentPIName();
    String getOwnedByUnitNumber();
    Integer getParentInvestigatorFlag(String personId, Integer flag);
    String  getParentTypeName();
	boolean isProposalBudget();
	
	BudgetParentDocument<? extends BudgetParent> getDocument();
	List<? extends Budget> getBudgets();
	Budget getNewBudget();
	Integer getNextBudgetVersionNumber();
	
}
