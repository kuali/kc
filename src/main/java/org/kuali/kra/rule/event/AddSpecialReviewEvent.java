/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class represents the event for adding special review.
 */
public class AddSpecialReviewEvent<T extends AbstractSpecialReview> extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddSpecialReviewEvent.class);

    private T specialReview;

    public String NEW_SPECIAL_REVIEW = "newSpecialReview";
    /**
     * Constructs an AddProposalSpecialReviewEvent with the given errorPathPrefix, document, and proposalSpecialReview.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSpecialReview
     */
    @SuppressWarnings("unchecked")
    public AddSpecialReviewEvent(String errorPathPrefix, Document document, T specialReview) {
        super("adding special review to document " + getDocumentId(document), errorPathPrefix, document);
        this.specialReview = (T) ObjectUtils.deepCopy(specialReview);
        logEvent();
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return SpecialReviewRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SpecialReviewRule) rule).processAddSpecialReviewEvent(this);
    }

    /**
     * Gets the specialReview attribute.
     * 
     * @return Returns the specialReview.
     */
    public T getSpecialReview() {
        return specialReview;
    }

    /**
     * Sets the specialReview attribute value.
     * 
     * @param specialReview The specialReview to set.
     */
    public void setSpecialReview(T specialReview) {
        this.specialReview = specialReview;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#validate()
     */
    public void validate() {
        super.validate();
        if (getSpecialReview() == null) {
            throw new IllegalArgumentException("invalid (null) specialreview");
        }
    }

    /**
     * Logs the event type and some information about the associated special review
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");
        if (getSpecialReview() == null) {
            logMessage.append("null specialReview");
        }else {
            logMessage.append(getSpecialReview().toString());
        }
        LOG.debug(logMessage);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((specialReview == null) ? 0 : specialReview.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddSpecialReviewEvent other = (AddSpecialReviewEvent) obj;
        if (specialReview == null) {
            if (other.specialReview != null)
                return false;
        }
        else if (!specialReview.equals(other.specialReview))
            return false;
        return true;
    }

}
