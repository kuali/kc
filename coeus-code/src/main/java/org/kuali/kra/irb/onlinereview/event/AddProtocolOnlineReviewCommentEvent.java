/*
 * Copyright 2005-2014 The Kuali Foundation
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
