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
package org.kuali.kra.irb.onlinereview.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.onlinereview.rules.AddOnlineReviewCommentRule;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddProtocolOnlineReviewCommentEvent extends KcDocumentEventBase {

    
    
    private static final Log LOG = LogFactory.getLog(AddProtocolOnlineReviewCommentEvent.class);
    private final CommitteeScheduleMinute committeeScheduleMinute;
    private final long onlineReviewIndex;
  
    /**
     * Creates a new event.
     * @param document the document.
     * @param newProtocolNotepad the new attachment to be added.
     */
    public AddProtocolOnlineReviewCommentEvent(final ProtocolOnlineReviewDocument document,
        final CommitteeScheduleMinute newCommitteeScheduleMinute, final long onlineReviewIndex ) {
        super("adding new protocol notepad", "notesAttachmentsHelper", document);
        this.onlineReviewIndex = onlineReviewIndex;
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newCommitteeScheduleMinute == null) {
            throw new IllegalArgumentException("the newCommitteeScheduleMinute is null");
        }
        
        this.committeeScheduleMinute = newCommitteeScheduleMinute;
    }
  
    
    
    
    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.committeeScheduleMinute + " on doc # " + this.getDocument().getDocumentNumber());
    }

    public Class<AddOnlineReviewCommentRule> getRuleInterfaceClass() {
        return AddOnlineReviewCommentRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddProtocolOnlineReviewComment(this);
    }

    /**
     * Gets the committeeScheduleMinute attribute. 
     * @return Returns the committeeScheduleMinute.
     */
    public CommitteeScheduleMinute getCommitteeScheduleMinute() {
        return committeeScheduleMinute;
    }

    public ProtocolOnlineReviewDocument getProtocolOnlineReviewDocument() {
        return (ProtocolOnlineReviewDocument)getDocument();
    }




    /**
     * Gets the onlineReviewIndex attribute. 
     * @return Returns the onlineReviewIndex.
     */
    public long getOnlineReviewIndex() {
        return onlineReviewIndex;
    }
    
}
