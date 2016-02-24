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
package org.kuali.kra.protocol.protocol.research;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

public abstract class ProtocolResearchAreaAuditRuleBase extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {
    
    private static final String ADDITIONAL_INFORMATION_AUDIT_ERRORS = "additionalInformationAuditErrors";
    
    private List<AuditError> auditErrors;
    
    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean isValid = true;
        ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) document;
        auditErrors = new ArrayList<AuditError>();
        
        isValid = !protocolDocument.getProtocol().isEmptyProtocolResearchAreas();

        if (!isValid) {
            addErrorToAuditErrors();
        }
        isValid &= isEveryResearchAreaActive(protocolDocument);
        reportAndCreateAuditCluster();
        
        return isValid;
    }
    
    /**
     * Creates and adds the Audit Error to the <code>{@link List&lt;AuditError&gt;}</code> auditError.
     */
    protected void addErrorToAuditErrors() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.PROTOCOL_PROTOCOL_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.PROTOCOL_PROTOCOL_RESEARCH_AREA_PANEL_ANCHOR);
        auditErrors.add(new AuditError(Constants.PROTOCOL_RESEARCH_AREA_KEY,
                                        KeyConstants.ERROR_PROTOCOL_RESEARCH_AREA_REQUIRED,
                                        stringBuilder.toString()));   
    }

    
    private boolean isEveryResearchAreaActive(ProtocolDocumentBase document) {
        boolean inactiveFound = false;
        String inactiveResearchAreaCode = "";
        
        List<ProtocolResearchAreaBase> pras = document.getProtocol().getProtocolResearchAreas();
        // iterate over all the research areas for this protocol looking for inactive research areas
        if(CollectionUtils.isNotEmpty(pras)) {
            for (ProtocolResearchAreaBase protocolResearchArea : pras) {
                if(!(protocolResearchArea.getResearchAreas().isActive())) {
                    inactiveFound = true;
                    if (StringUtils.isBlank(inactiveResearchAreaCode)) {
                        inactiveResearchAreaCode = protocolResearchArea.getResearchAreaCode();
                    } else {
                        inactiveResearchAreaCode = inactiveResearchAreaCode + ", " + protocolResearchArea.getResearchAreaCode();
                    }
                }
            }
        }
        // if we found any inactive research areas in the above loop, report as a single error key suffixed by the list of indices of the inactive areas
        if(inactiveFound) { 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Constants.PROTOCOL_PROTOCOL_PAGE);
            stringBuilder.append(".");
            stringBuilder.append(Constants.PROTOCOL_PROTOCOL_RESEARCH_AREA_PANEL_ANCHOR);
            auditErrors.add(new AuditError(Constants.PROTOCOL_RESEARCH_AREA_KEY,
                                            KeyConstants.ERROR_PROTOCOL_RESEARCH_AREA_NOT_ACTIVE,
                                            stringBuilder.toString(), new String[] {inactiveResearchAreaCode}));   
        }
        
        return !inactiveFound;
    }

    /**
     * Creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(ADDITIONAL_INFORMATION_AUDIT_ERRORS, 
                    new AuditCluster(Constants.PROTOCOL_PROTOCOL_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    

}
