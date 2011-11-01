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
package org.kuali.kra.budget.core;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class...
 */
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
    public boolean isSponsorNihMultiplePi();
    public String getHierarchyStatus();
    public String getDefaultBudgetStatusParameter();
    public boolean isParentInHierarchyComplete();
    
    public String getParentNumber();
    public String getParentTitle();
    public String getParentPIName();
    public String getIsOwnedByUnit();
    public Integer getParentInvestigatorFlag(String personId, Integer flag);
    public String  getParentTypeName();
 
}
