/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.SaveProtocolRequiredFieldsRule;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;

public class ProtocolRequiredFieldsRule extends ProtocolDocumentRule implements SaveProtocolRequiredFieldsRule {

    private static final String PROPERTY_REQD_FIELDS = "protocolRequiredFields"; 
    
    public boolean processSaveProtocolRequiredFieldsRules(SaveProtocolRequiredFieldsEvent saveProtocolRequiredFieldsEvent) {
        boolean isValid = true;

        ProtocolDocument document = (ProtocolDocument) saveProtocolRequiredFieldsEvent.getDocument();
        if (document == null) {
            ;
        }
        if (document.getProtocol() == null) {
            
        } 
        
        if   (StringUtils.isBlank(document.getProtocol().getTitle())) {
            isValid = false;
            reportError("document.protocol.title",   KeyConstants.ERROR_PROTOCOL_TITLE_NOT_FOUND);
        } 
        if   (StringUtils.isBlank(document.getProtocol().getPrincipalInvestigatorId()) || 
              StringUtils.isBlank(document.getProtocol().getPrincipalInvestigatorName())) {
            isValid = false;
            reportError("principalInvestigator", KeyConstants.ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_NAME_NOT_FOUND);
        } 
        if   (StringUtils.isBlank(document.getProtocol().getLeadUnitNumber()) || 
                StringUtils.isBlank(document.getProtocol().getLeadUnitName())) {
              isValid = false;
              reportError("document.protocol.leadUnitNumber", KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NAME_NOT_FOUND);
        }
        if   (StringUtils.isBlank(document.getProtocol().getProtocolTypeCode()) ) {
              isValid = false;
              reportError("document.protocol.protocolTypeCode", KeyConstants.ERROR_PROTOCOL_TYPE_NOT_FOUND);
        }
        return isValid;
    }

}
