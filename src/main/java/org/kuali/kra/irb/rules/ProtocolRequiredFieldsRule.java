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
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.SaveProtocolRequiredFieldsRule;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;

public class ProtocolRequiredFieldsRule extends ProtocolDocumentRule implements SaveProtocolRequiredFieldsRule {
    
    private static final String PROTOCOL_TITLE_FORM_ELEMENT="document.protocol.title";
    private static final String PROTOCOL_PIID_FORM_ELEMENT="document.protocolHelper.personId";
    private static final String PROTOCOL_LUN_FORM_ELEMENT="protocolHelper.leadUnitNumber";
    private static final String PROTOCOL_TYPE_FORM_ELEMENT="document.protocol.protocolTypeCode";

    
    public boolean processSaveProtocolRequiredFieldsRules(SaveProtocolRequiredFieldsEvent saveProtocolRequiredFieldsEvent) {

        boolean isValid = true;

        ProtocolDocument document = (ProtocolDocument) saveProtocolRequiredFieldsEvent.getDocument();

        GlobalVariables.getErrorMap().addToErrorPath("document.protocol");
        getDictionaryValidationService().validateBusinessObject(document.getProtocol(), false);
        GlobalVariables.getErrorMap().removeFromErrorPath("document.protocol");
        isValid = GlobalVariables.getErrorMap().isEmpty();
        

        if   (StringUtils.isBlank(document.getProtocol().getTitle())) {
            isValid = false;
            reportError(PROTOCOL_TITLE_FORM_ELEMENT,   KeyConstants.ERROR_PROTOCOL_TITLE_NOT_FOUND);
        } 
        if   (document.getProtocol().getPrincipalInvestigator()== null ||
              StringUtils.isBlank(document.getProtocol().getPrincipalInvestigator().getPersonName()) || 
              StringUtils.isBlank(document.getProtocol().getPrincipalInvestigator().getPersonId())) {
            isValid = false;
            reportError(PROTOCOL_PIID_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_NAME_NOT_FOUND);
        } 
        if   (StringUtils.isNotEmpty(document.getProtocol().getLeadUnitNumber()) &&
              document.getProtocol().getLeadUnitForValidation() == null             ) {
              isValid = false;
              reportError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID);
        } else if   (document.getProtocol().getLeadUnitForValidation() == null   ) {
              isValid = false;
              reportError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NAME_NOT_FOUND);
        }
        if   (StringUtils.isBlank(document.getProtocol().getProtocolTypeCode()) ) {
              isValid = false;
              reportError(PROTOCOL_TYPE_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_TYPE_NOT_FOUND);
        }
        return isValid;
    }
    
    public DictionaryValidationService getDictionaryValidationService() {
        return  KraServiceLocator.getService(DictionaryValidationService.class);
    }


}
