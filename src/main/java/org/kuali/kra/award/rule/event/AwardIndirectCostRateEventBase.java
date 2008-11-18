/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.award.bo.AwardIndirectCostRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public abstract class AwardIndirectCostRateEventBase  extends KraDocumentEventBase implements AwardIndirectCostRateEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(AwardIndirectCostRateEventBase.class);

private AwardIndirectCostRate awardIndirectCostRate;

/**
* @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
*/
protected AwardIndirectCostRateEventBase(String description, String errorPathPrefix, AwardDocument document,
    AwardIndirectCostRate awardIndirectCostRate) {
super(description, errorPathPrefix, document);

// by doing a deep copy, we are ensuring that the business rule class can't update
// the original object by reference
this.awardIndirectCostRate = (AwardIndirectCostRate) ObjectUtils.deepCopy(awardIndirectCostRate);
logEvent();
}

/**
* @return <code>{@link ProposalSpecialReview}</code> that triggered this event.
*/
public AwardIndirectCostRate getAwardIndirectCostRate() {
return awardIndirectCostRate;
}

/**
* @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
*/
public void validate() {
super.validate();
if (getAwardIndirectCostRate() == null) {
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
if (getAwardIndirectCostRate() == null) {
    logMessage.append("null proposalSpecialReview");
}
else {
    logMessage.append(getAwardIndirectCostRate().toString());
}

LOG.debug(logMessage);
}


}
