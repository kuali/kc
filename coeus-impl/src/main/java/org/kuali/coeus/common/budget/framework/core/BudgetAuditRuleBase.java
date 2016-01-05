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

import java.util.ArrayList;
import java.util.List;

import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BudgetAuditRuleBase {

	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	protected List<AuditError> getAuditErrors(BudgetConstants.BudgetAuditRules auditRule, boolean auditError) {
		return getAuditCluster(auditRule, auditError).getAuditErrorList();
	}
	
	protected List<AuditError> getAuditErrors(BudgetConstants.BudgetAuditRules auditRule, String appendLabel, boolean auditError) {
        AuditCluster auditCluster = getAuditCluster(auditRule, false);
        auditCluster.setLabel(auditCluster.getLabel().concat(appendLabel));
		return auditCluster.getAuditErrorList();
	}
	
	protected AuditCluster getAuditCluster(BudgetConstants.BudgetAuditRules auditRule, boolean auditError) {
		String auditKey = auditRule.getWarningKey();
		String auditCategory = Constants.AUDIT_WARNINGS;
		
		if(auditError) {
			auditKey = auditRule.getErrorKey();
			auditCategory = Constants.AUDIT_ERRORS;
		}
		
        AuditCluster auditCluster = getGlobalVariableService().getAuditErrorMap().get(auditKey);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<>();
            auditCluster = new AuditCluster(auditRule.getLabel(), auditErrors, auditCategory);
            getGlobalVariableService().getAuditErrorMap().put(auditKey, auditCluster);
        }
        return auditCluster;
	}
	
	protected GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

}


