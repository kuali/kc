/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.personnel;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class BudgetPersonnelAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetPersonnelAuditRule.class);
    
    public boolean processRunPersonnelAuditBusinessRules(Document document) {
        boolean valid = true;
        
        if (!(document instanceof BudgetDocument)) {
            return false;
        }
        
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        BudgetDocument budgetDocument = (BudgetDocument) document;
        Budget budget = ((BudgetDocument) document).getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        for (BudgetPerson budgetPerson: budget.getBudgetPersons()) {
            if (budgetPerson.getRolodexId() != null) {
                ProposalPersonRole role = budgetParent.getProposalNonEmployeeRole(budgetPerson.getRolodexId());
                if (role != null) { budgetPerson.setRole(role.getDescription()); }
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
            GlobalVariables.getAuditErrorMap().put("budgetPersonnelAuditWarnings", new AuditCluster(Constants.BUDGET_PERSONNEL_PAGE, auditErrors, Constants.AUDIT_WARNINGS));
        }
        
        return valid;
    }
    
}
