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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * Event triggered when a Key Person is added to a
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.4 $
 */
public class SaveNarrativesEvent extends NarrativeEventBase {

    private List<Narrative> originalNarratives;
    
    /**
     * Constructs an AddNarrativeEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param narrative
     */
    public SaveNarrativesEvent(String errorPathPrefix, ProposalDevelopmentDocument document, Narrative narrative, List<Narrative> originalNarratives) {
        super("Adding narrative to document " + getDocumentId(document), errorPathPrefix, document, narrative);
        this.originalNarratives = originalNarratives;
    }

    /**
     * Constructs an AddNarrativeEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param document
     * @param narrative
     */
    public SaveNarrativesEvent(String errorPathPrefix, Document document, Narrative narrative) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document, narrative, null);
    }
    
    /**
     * Get the original list of narratives to compare against.
     * @return the original narratives
     */
    public List<Narrative> getOriginalNarratives() {
        return this.originalNarratives;
    }

    @Override
    public Class getRuleInterfaceClass() {
        return SaveNarrativesRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SaveNarrativesRule) rule).processSaveNarrativesBusinessRules(this);
    }
    
}
