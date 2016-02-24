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
package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleBase;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

@KcBusinessRule("budgetPersonnelAuditRule")
public class BudgetPersonnelAuditRule extends BudgetAuditRuleBase {

    @KcEventMethod
    @Deprecated
    public boolean processRunPersonnelAuditBusinessRules(BudgetAuditEvent event) {
        boolean valid = true;
        
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        Budget budget = event.getBudget();
        BudgetParent budgetParent = budget.getBudgetParent().getDocument().getBudgetParent();
        for (BudgetPerson budgetPerson: budget.getBudgetPersons()) {
            if (budgetPerson.getRolodexId() != null) {
                ContactRole role = budgetParent.getProposalNonEmployeeRole(budgetPerson.getRolodexId());
                if (role != null) { budgetPerson.setRole(role.getRoleDescription()); }
            } else if (budgetPerson.getPersonId() != null) {
                PersonRolodex proposalPerson = budgetParent.getProposalEmployee(budgetPerson.getPersonId());
                if (proposalPerson != null && proposalPerson.isOtherSignificantContributorFlag()) {
                    // Audit Error
                    auditErrors.add(new AuditError(
                            "document.budget.budgetPersonnel.osc." + budgetPerson.getPersonId(), KeyConstants.WARNING_PERSONNEL_OTHER_SIGNIFICANT_CONTRIBUTOR, 
                            Constants.BUDGET_PERSONNEL_PAGE, new String[] { budgetPerson.getPersonName() } ));
                }
            } else if (budgetPerson.getRolodexId() != null) {
                PersonRolodex proposalPerson = budgetParent.getProposalNonEmployee(budgetPerson.getRolodexId());
                if (proposalPerson != null && proposalPerson.isOtherSignificantContributorFlag()) {
                    // Audit Error
                    auditErrors.add(new AuditError(
                            "document.budget.budgetPersonnel.osc." + budgetPerson.getRolodexId(), KeyConstants.WARNING_PERSONNEL_OTHER_SIGNIFICANT_CONTRIBUTOR, 
                            Constants.BUDGET_PERSONNEL_PAGE, new String[] { budgetPerson.getPersonName() } ));
                }
            }
        }
        
        if (auditErrors.size() > 0) {
        	getGlobalVariableService().getAuditErrorMap().put("budgetPersonnelAuditWarnings", new AuditCluster(Constants.BUDGET_PERSONNEL_PAGE, auditErrors, Constants.AUDIT_WARNINGS));
        }
        
        return valid;
    }

    @KcEventMethod
    public boolean processRunPersonnelAuditBusinessRules(BudgetAuditRuleEvent event) {
        Budget budget = event.getBudget();
        for (BudgetPerson budgetPerson: budget.getBudgetPersons()) {
            PersonRolodex proposalPerson = budgetPerson.getPersonRolodex();
            if (proposalPerson != null && proposalPerson.isOtherSignificantContributorFlag()) {
                BudgetConstants.BudgetAuditRules budgetProjectPersonnelRule = BudgetConstants.BudgetAuditRules.PROJECT_PERSONNEL;
    			List<AuditError> auditErrors = getAuditErrors(budgetProjectPersonnelRule, false);
                auditErrors.add(new AuditError(
                		budgetProjectPersonnelRule.getPageId(), KeyConstants.WARNING_PERSONNEL_OTHER_SIGNIFICANT_CONTRIBUTOR, 
                		budgetProjectPersonnelRule.getPageId(), new String[] { budgetPerson.getPersonName() } ));
                return false;
            }
        }
        return true;
    }
    
}
