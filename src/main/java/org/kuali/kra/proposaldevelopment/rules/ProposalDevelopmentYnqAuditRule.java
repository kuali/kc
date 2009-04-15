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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProposalDevelopmentYnqAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentYnqAuditRule.class);
    
    /**
     * 
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        Map<String, List<AuditError>> auditErrorsMap = new HashMap<String, List<AuditError>>();
        List<AuditError> auditErrors;
        Set<String> ynqPanelNames = new HashSet<String>();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        
        if(errorMap.containsKeyMatchingPattern("document.proposalYnq*")) {
            List erroredProperties = errorMap.getPropertiesWithErrors();
            for(Object property : erroredProperties) {
                String key = (String) property;
                if(key.startsWith("document.proposalYnq")) {
                    valid = false;
                    String groupName = key.substring(key.indexOf("[")+1, key.indexOf("]"));
                    boolean newlyAdded = ynqPanelNames.add(groupName);
                    
                    if(newlyAdded) {
                        auditErrors = new ArrayList<AuditError>();
                        auditErrorsMap.put(groupName, auditErrors);
                    } else {
                        auditErrors = auditErrorsMap.get(groupName);
                    }
                    
                    TypedArrayList errorMessageList = (TypedArrayList) errorMap.get(property);
                    
                    for(int i=0; i< errorMessageList.size(); i++) {
                        ErrorMessage message = (ErrorMessage) errorMessageList.get(i);
                        AuditError auditError = new AuditError(key, message.getErrorKey(), Constants.QUESTIONS_PAGE + "." + groupName, message.getMessageParameters());
                        auditErrors.add(auditError);
                    }
                    
                    errorMap.remove(property);
                }
            }
        }
        
        if (ynqPanelNames.size() > 0) {
            for(String panel: ynqPanelNames) {
                GlobalVariables.getAuditErrorMap().put("ynqAuditErrors" + panel, new AuditCluster(panel, auditErrorsMap.get(panel), Constants.AUDIT_ERRORS));
            }
        }
        
        return valid;   
    }
}
