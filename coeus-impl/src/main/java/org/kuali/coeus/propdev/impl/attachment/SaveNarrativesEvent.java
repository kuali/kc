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
