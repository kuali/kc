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
package org.kuali.kra.subaward.subawardrule;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class SubAwardAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule{
    
    private static final String SUBAWARD_AUDIT_ERRORS = "subawardAuditErrors";
    private static final String SUBAWARD_AUDIT_WARNINGS = "subawardAuditWarnings";
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        List<AuditError> auditErrors = new ArrayList<AuditError>(); 
        SubAwardDocument subAwardDocument = (SubAwardDocument)document;
        subAwardDocument.getSubAward().getSubAwardContactsList().size();
        if(subAwardDocument.getSubAward().getSubAwardContactsList().size()<=0){
            valid = false;
            auditErrors.add(new AuditError(Constants.SUBAWARD_AUDIT_RULE_ERROR_KEY, 
                    KeyConstants.ERROR_SUBAWARD_CONTACT, 
                    Constants.MAPPING_SUBAWARD_PAGE + "." + Constants.MAPPING_SUBAWARD_CONTACT_PANEL
                    ));
        }
       
        reportAndCreateAuditCluster(auditErrors);            
        
        return valid;
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster( List<AuditError> auditErrors ) {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(SUBAWARD_AUDIT_ERRORS, new AuditCluster(Constants.MAPPING_SUBAWARD_CONTACT_PANEL,
                    auditErrors, Constants.AUDIT_ERRORS));
        }
    }

}
