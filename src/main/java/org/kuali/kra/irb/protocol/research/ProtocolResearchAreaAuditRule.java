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
package org.kuali.kra.irb.protocol.research;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class ProtocolResearchAreaAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {
    
    private static final String ADDITIONAL_INFORMATION_AUDIT_ERRORS = "additionalInformationAuditErrors";
    
    private List<AuditError> auditErrors;
    
    /**
     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean isValid = true;
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        auditErrors = new ArrayList<AuditError>();
        
        isValid = !getProtocolResearchAreaService().isEmptyProtocolResearchAreas(protocolDocument.getProtocol());

        if (!isValid) {
            addErrorToAuditErrors();
        }
        isValid &= isResearchAreaActive(protocolDocument);
        reportAndCreateAuditCluster();
        
        return isValid;
    }
    
    /**
     * Creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
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

    
    private boolean isResearchAreaActive(ProtocolDocument document) {
        boolean inactiveFound = false;
        String inactiveResearchAreaCode = "";
        
        List<ProtocolResearchArea> pras = document.getProtocol().getProtocolResearchAreas();
        // iterate over all the research areas for this protocol looking for inactive research areas
        if(CollectionUtils.isNotEmpty(pras)) {
            for (ProtocolResearchArea protocolResearchArea : pras) {
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
            KNSGlobalVariables.getAuditErrorMap().put(ADDITIONAL_INFORMATION_AUDIT_ERRORS, 
                    new AuditCluster(Constants.PROTOCOL_PROTOCOL_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    private ProtocolResearchAreaService getProtocolResearchAreaService() {
        return KraServiceLocator.getService(ProtocolResearchAreaService.class);
    }

}