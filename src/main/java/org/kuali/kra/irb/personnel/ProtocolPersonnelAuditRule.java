/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.personnel;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;


/**
 * Rules that invoke audit mode for KeyPersonnel
 */
public class ProtocolPersonnelAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolPersonnelAuditRule.class);
    private List<AuditError> auditErrors;
    private static final String PERSONNEL_AUDIT_ERRORS = "personnelAuditErrors";
    
    /**
     * 
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean isValid = true;
        ProtocolDocument protocolDocument = (ProtocolDocument)document;
        auditErrors = new ArrayList<AuditError>();
        isValid = getProtocolPersonnelService().isValidStudentFacultyMatch(protocolDocument.getProtocol().getProtocolPersons());

        if (!isValid) {
            addErrorToAuditErrors();
        }
        reportAndCreateAuditCluster();
        
        return isValid;

    }
       
    /**
     * This method creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     */
    protected void addErrorToAuditErrors() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.PROTOCOL_PERSONNEL_PAGE);
        sb.append(".");
        sb.append(Constants.PROTOCOL_PERSONNEL_PANEL_ANCHOR);
        auditErrors.add(new AuditError(Constants.PROTOCOL_PRINCIPAL_INVESTIGATOR_KEY, 
                                        KeyConstants.ERROR_PROTOCOL_INVESTIGATOR_INVALID, 
                                        sb.toString()));   
    }

    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(PERSONNEL_AUDIT_ERRORS, new AuditCluster(Constants.PROTOCOL_PERSONNEL_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    /**
     * This method is to get personnel sevice
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return getService(ProtocolPersonnelService.class);
    }
}
