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
package org.kuali.kra.coi;

import org.kuali.kra.coi.disclosure.DisclosurePersonUnit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;

/**
 * 
 * This class is the rule class for coidisclosuredocument
 */
public class CoiDisclosureDocumentRule extends ResearchDocumentRuleBase implements BusinessRuleInterface {


    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        retval &= super.processCustomRouteDocumentBusinessRules(document);

        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof CoiDisclosureDocument)) {
            return false;
        }

        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document,
                getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);

        boolean valid = true;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        valid &= processReporterUnitRules(coiDisclosureDocument);
        return valid;
    }


    public boolean processReporterUnitRules(CoiDisclosureDocument document) {
        boolean isValid = true;

        GlobalVariables.getMessageMap().addToErrorPath("document.coiDisclosureList[0].disclosurePersons[0]");
        if (org.apache.commons.collections.CollectionUtils.isEmpty(document.getCoiDisclosure().getDisclosureReporter()
                .getDisclosurePersonUnits())) {
            GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_ONE_UNIT, "Disclosure Reporter");

        }
        else {
            boolean leadUnitFound = false;
            for (DisclosurePersonUnit unit : document.getCoiDisclosure().getDisclosureReporter().getDisclosurePersonUnits()) {
                if (unit.isLeadUnitFlag()) {
                    leadUnitFound = true;
                    break;
                }
            }
            if (!leadUnitFound) {
                GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_LEAD_UNIT_REQUIRED);

            }

        }
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        GlobalVariables.getMessageMap().removeFromErrorPath("document.coiDisclosureList[0].disclosurePersons[0]");
        
        return isValid;
    }

    /**
     * 
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(KraDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }

}
