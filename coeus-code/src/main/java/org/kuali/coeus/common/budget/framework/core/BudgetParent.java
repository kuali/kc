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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.document.Document;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;

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
	
	public List<? extends Budget> getBudgetVersionOverviews();
	public Budget getNewBudget();
	public Integer getNextBudgetVersionNumber();

	
}
