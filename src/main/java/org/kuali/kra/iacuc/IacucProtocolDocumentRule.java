/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.document.Document;

/**
 * Main Business Rule class for <code>{@link IacucProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class IacucProtocolDocumentRule extends ResearchDocumentRuleBase  implements BusinessRuleInterface {

    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        retval &= super.processCustomRouteDocumentBusinessRules(document);

        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof IacucProtocolDocument)) {
            return false;
        }

        // TODO : uncomment following when we start to implement disclosure
//        MessageMap errorMap = GlobalVariables.getMessageMap();
//        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
//        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
//               document, getMaxDictionaryValidationDepth(),
//               VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);

        boolean valid = true;
        IacucProtocolDocument iacucProtocolDocument = (IacucProtocolDocument) document;
        return valid;
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
