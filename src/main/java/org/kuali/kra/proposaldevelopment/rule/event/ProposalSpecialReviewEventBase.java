/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public abstract class ProposalSpecialReviewEventBase  extends KraDocumentEventBase implements ProposalSpecialReviewEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(ProposalSpecialReviewEventBase.class);

private ProposalSpecialReview proposalSpecialReview;

/**
* @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
*/
protected ProposalSpecialReviewEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document,
    ProposalSpecialReview proposalSpecialReview) {
super(description, errorPathPrefix, document);

// by doing a deep copy, we are ensuring that the business rule class can't update
// the original object by reference
this.proposalSpecialReview = (ProposalSpecialReview) ObjectUtils.deepCopy(proposalSpecialReview);
logEvent();
}

/**
* @return <code>{@link ProposalSpecialReview}</code> that triggered this event.
*/
public ProposalSpecialReview getProposalSpecialReview() {
return proposalSpecialReview;
}

/**
* @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
*/
public void validate() {
super.validate();
if (getProposalSpecialReview() == null) {
    throw new IllegalArgumentException("invalid (null) proposal specialreview");
}
}

/**
* Logs the event type and some information about the associated special review
*/
protected void logEvent() {
StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
logMessage.append(" with ");

// vary logging detail as needed
if (getProposalSpecialReview() == null) {
    logMessage.append("null proposalSpecialReview");
}
else {
    logMessage.append(getProposalSpecialReview().toString());
}

LOG.debug(logMessage);
}


}
