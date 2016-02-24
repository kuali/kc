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
package org.kuali.kra.protocol.onlinereview.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.onlinereview.rules.SaveProtocolOnlineReviewRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

public class SaveProtocolOnlineReviewEvent extends KcDocumentEventBase {

    
    
    private static final Log LOG = LogFactory.getLog(SaveProtocolOnlineReviewEvent.class);
    private final List<CommitteeScheduleMinuteBase> minutes;
    private List<ProtocolReviewAttachmentBase> reviewAttachments;
    private final long onlineReviewIndex;
  
    /**
     * Creates a new event.
     * @param document the document.
     * @param newProtocolNotepad the new attachment to be added.
     */
    public SaveProtocolOnlineReviewEvent(final ProtocolOnlineReviewDocumentBase document,
        final List<CommitteeScheduleMinuteBase> minutes, final long onlineReviewIndex ) {
        super("adding new protocol notepad", "notesAttachmentsHelper", document);
        this.onlineReviewIndex = onlineReviewIndex;
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (minutes == null) {
            throw new IllegalArgumentException("the newCommitteeScheduleMinute is null");
        }
        
        this.minutes = minutes;
        this.reviewAttachments =document.getProtocolOnlineReview().getReviewAttachments();
    }
  
    
    
    
    @Override
    protected void logEvent() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("save #" + this.getDocument().getDocumentNumber());
        }
    }

    public Class<SaveProtocolOnlineReviewRule> getRuleInterfaceClass() {
        return SaveProtocolOnlineReviewRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processSaveProtocolOnlineReview(this);
    }

   
    public ProtocolOnlineReviewDocumentBase getProtocolOnlineReviewDocument() {
        return (ProtocolOnlineReviewDocumentBase)getDocument();
    }

    /**
     * Gets the onlineReviewIndex attribute. 
     * @return Returns the onlineReviewIndex.
     */
    public long getOnlineReviewIndex() {
        return onlineReviewIndex;
    }

    /**
     * Gets the minutes attribute. 
     * @return Returns the minutes.
     */
    public List<CommitteeScheduleMinuteBase> getMinutes() {
        return minutes;
    }


    public List<ProtocolReviewAttachmentBase> getReviewAttachments() {
        return reviewAttachments;
    }
    
}
