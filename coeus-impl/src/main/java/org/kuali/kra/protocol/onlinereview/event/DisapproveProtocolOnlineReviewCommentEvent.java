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
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.rules.DisapproveOnlineReviewCommentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.util.KRADConstants;

public class DisapproveProtocolOnlineReviewCommentEvent extends KcDocumentEventBase {
    
    private static final Log LOG = LogFactory.getLog(DisapproveProtocolOnlineReviewCommentEvent.class);
    private String reason = null;
    private String noteText = null;
    private int maxLength;
       
    public DisapproveProtocolOnlineReviewCommentEvent(final ProtocolOnlineReviewDocumentBase document,
                                                         final String disapprovalReason,
                                                         final String disapprovalNoteText,
                                                         final int reasonMaxLength) {
        super("disapprove protocol online review comment", KRADConstants.DOCUMENT_DISAPPROVE_QUESTION, document);
        this.reason = disapprovalReason;
        this.noteText = disapprovalNoteText;
        this.maxLength = reasonMaxLength;
    }
 
    @Override
    protected void logEvent() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("disapprove protocol online review comment event reason=" + reason);
        }
    }

    public Class<DisapproveOnlineReviewCommentRule> getRuleInterfaceClass() {
        return DisapproveOnlineReviewCommentRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processDisapproveOnlineReviewComment(this);

    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    
}
