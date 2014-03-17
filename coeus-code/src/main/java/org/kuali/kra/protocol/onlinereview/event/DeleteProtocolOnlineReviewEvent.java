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
package org.kuali.kra.protocol.onlinereview.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.rules.DeleteOnlineReviewRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class DeleteProtocolOnlineReviewEvent extends KcDocumentEventBase {
    
    private static final Log LOG = LogFactory.getLog(DeleteProtocolOnlineReviewEvent.class);
    private String reason = null;
    private String noteText = null;
    private int maxLength;
       
    public DeleteProtocolOnlineReviewEvent(final ProtocolOnlineReviewDocumentBase document,
                                                         final String deleteReason,
                                                         final String deleteNoteText,
                                                         final int reasonMaxLength) {
        super("delete protocol online review", "Are you sure you want to delete this document?", document);
        this.reason = deleteReason;
        this.noteText = deleteNoteText;
        this.maxLength = reasonMaxLength;
    }
 
    @Override
    protected void logEvent() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("disapprove protocol online review comment event reason=" + reason);
        }
    }

    public Class<DeleteOnlineReviewRule> getRuleInterfaceClass() {
        return DeleteOnlineReviewRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processDeleteOnlineReview(this);

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
