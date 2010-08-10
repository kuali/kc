/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.rule.event;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * Represents the event for saving a special review.
 */
@SuppressWarnings("unchecked")
public class SaveSpecialReviewEvent<T extends AbstractSpecialReview> extends KraDocumentEventBase {
    
    private static final Log LOG = LogFactory.getLog(SaveSpecialReviewEvent.class);
    
    private List<T> specialReviews;

    /**
     * Constructs a SaveSpecialReviewEvent.
     * @param errorPathPrefix
     * @param document
     * @param specialReview
     */
    public SaveSpecialReviewEvent(String errorPathPrefix, Document document, List<T> specialReviews) {
        super("saving special review to document " + getDocumentId(document), errorPathPrefix, document);
        this.specialReviews = specialReviews;
        logEvent();
    }

    public List<T> getSpecialReviews() {
        return specialReviews;
    }

    public void setSpecialReviews(List<T> specialReviews) {
        this.specialReviews = specialReviews;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return SpecialReviewRule.class;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SpecialReviewRule) rule).processSaveSpecialReviewEvent(this);
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");
        if (getSpecialReviews() == null) {
            logMessage.append("null specialReview");
        } else {
            logMessage.append(getSpecialReviews().toString());
        }
        LOG.debug(logMessage);
    }

}