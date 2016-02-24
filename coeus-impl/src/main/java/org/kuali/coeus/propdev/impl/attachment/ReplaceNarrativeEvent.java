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

import org.kuali.coeus.propdev.impl.attachment.AddNarrativeEvent;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.ReplaceNarrativeRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Event triggered when a Key Person is added to a
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.2 $
 */
public class ReplaceNarrativeEvent extends AddNarrativeEvent {
    /**
     * Constructs a ReplaceNarrativeEvent with the given errorPathPrefix, document, and narrative.
     * 
     * @param errorPathPrefix
     * @param document
     * @param narrative
     */
    public ReplaceNarrativeEvent(String errorPathPrefix, ProposalDevelopmentDocument document, Narrative narrative) {
        super(errorPathPrefix, document, narrative);
    }

    /**
     * Constructs an AddNarrativeEvent with the given errorPathPrefix, document, and proposalPerson.
     * 
     * @param errorPathPrefix
     * @param document
     * @param narrative
     */
    public ReplaceNarrativeEvent(String errorPathPrefix, Document document, Narrative narrative) {
        this(errorPathPrefix, (ProposalDevelopmentDocument) document, narrative);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return ReplaceNarrativeRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ReplaceNarrativeRule) rule).processReplaceNarrativeBusinessRules(this);
    }
}
