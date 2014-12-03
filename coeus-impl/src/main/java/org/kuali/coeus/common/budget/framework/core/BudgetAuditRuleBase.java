/*
 * Copyright 2005-2014 The Kuali Foundation
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
		
        AuditCluster auditCluster = (AuditCluster) getGlobalVariableService().getAuditErrorMap().get(auditKey);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<AuditError>();
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


