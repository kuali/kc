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
package org.kuali.kra.budget.rules;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
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
        for (BudgetPerson budgetPerson: budgetDocument.getBudgetPersons()) {
            if (budgetPerson.getRolodexId() != null) {
                ProposalPersonRole role = budgetDocument.getProposal().getDevelopmentProposal().getProposalNonEmployeeRole(budgetPerson.getRolodexId());
                if (role != null) { budgetPerson.setRole(role.getDescription()); }
            } else if (budgetPerson.getPersonId() != null) {
                ProposalPerson proposalPerson = budgetDocument.getProposal().getDevelopmentProposal().getProposalEmployee(budgetPerson.getPersonId());
                if (proposalPerson != null && proposalPerson.isOtherSignificantContributorFlag()) {
                    // Audit Error
                    auditErrors.add(new AuditError(
                            "document.budgetPersonnel.osc." + budgetPerson.getPersonId(), KeyConstants.WARNING_PERSONNEL_OTHER_SIGNIFICANT_CONTRIBUTOR, 
                            Constants.BUDGET_PERSONNEL_PAGE, new String[] { budgetPerson.getPersonName() } ));
                }
            } else if (budgetPerson.getRolodexId() != null) {
                ProposalPerson proposalPerson = budgetDocument.getProposal().getDevelopmentProposal().getProposalNonEmployee(budgetPerson.getRolodexId());
                if (proposalPerson != null && proposalPerson.isOtherSignificantContributorFlag()) {
                    // Audit Error
                    auditErrors.add(new AuditError(
                            "document.budgetPersonnel.osc." + budgetPerson.getRolodexId(), KeyConstants.WARNING_PERSONNEL_OTHER_SIGNIFICANT_CONTRIBUTOR, 
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
