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
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;

import java.util.List;


/**
 * Base implementation for events triggered when a Key Person state is modified on a
 * <code>{@link ProposalDevelopmentDocument}</code>
 * 
 */
public abstract class NarrativeEventBase extends KcDocumentEventBase implements NarrativeEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(NarrativeEventBase.class);

    private Narrative narrative;
    private List<Narrative> narratives;

    /**
     * 
     * Constructs a NarrativeEventBase
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param narrative
     */
    protected NarrativeEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document,
            Narrative narrative) {
        super(description, errorPathPrefix, document);
        if (narrative != null) {
            this.narrative = narrative;
        }
        narratives = document.getDevelopmentProposal().getNarratives();
        logEvent();
    }

    /**
     * 
     * Constructs a NarrativeEventBase.
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    protected NarrativeEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document) {
        super(description, errorPathPrefix, document);
        narratives = document.getDevelopmentProposal().getNarratives();
    }


    /**
     * @return <code>{@link Narrative}</code> that triggered this event.
     */
    public Narrative getNarrative() {
        return narrative;
    }

    /**
     * @return <code>{@link Narrative}</code> that triggered this event.
     */
    public List<Narrative> getNarratives() {
        return narratives;
    }

    /**
     * Logs the event type and some information about the associated accountingLine
     */
    protected void logEvent() {
        LOG.debug(getDescription());
    }
}
