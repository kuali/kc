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
package org.kuali.kra.common.specialreview.rule.event;

import java.util.List;

import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.rule.AddSpecialReviewRule;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * Represents the event for adding a Special Review.
 * @param <T> The subclass of SpecialReview
 */
public class AddSpecialReviewEvent<T extends SpecialReview<? extends SpecialReviewExemption>> extends KraDocumentEventBaseExtension {
    
    private static final String NEW_SPECIAL_REVIEW_FIELD = "specialReviewHelper.newSpecialReview";
    
    private T specialReview;
    
    private List<T> specialReviews;
    
    private boolean isProtocolLinkingEnabled;
    
    /**
     * Constructs an AddProposalSpecialReviewEvent.
     * 
     * @param document The document containing the Special Review
     * @param specialReview The Special Review object to validate
     * @param specialReviews The existing Special Review objects
     * @param isProtocolLinkingEnabled Whether or not Protocol linking is enabled
     */
    public AddSpecialReviewEvent(Document document, T specialReview, List<T> specialReviews, boolean isProtocolLinkingEnabled) {
        super("adding special review to document " + getDocumentId(document), NEW_SPECIAL_REVIEW_FIELD, document);
        this.specialReview = specialReview;
        this.specialReviews = specialReviews;
        this.isProtocolLinkingEnabled = isProtocolLinkingEnabled;
    }

    public T getSpecialReview() {
        return specialReview;
    }

    public void setSpecialReview(T specialReview) {
        this.specialReview = specialReview;
    }

    public List<T> getSpecialReviews() {
        return specialReviews;
    }

    public void setSpecialReviews(List<T> specialReviews) {
        this.specialReviews = specialReviews;
    }

    public boolean getIsProtocolLinkingEnabled() {
        return isProtocolLinkingEnabled;
    }

    public void setIsProtocolLinkingEnabled(boolean isProtocolLinkingEnabled) {
        this.isProtocolLinkingEnabled = isProtocolLinkingEnabled;
    }

    @Override
    public BusinessRuleInterface<AddSpecialReviewEvent<T>> getRule() {
        return new AddSpecialReviewRule<T>();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AddSpecialReviewEvent<?> other = (AddSpecialReviewEvent<?>) obj;
        if (specialReview == null) {
            if (other.specialReview != null) {
                return false;
            }
        } else if (!specialReview.equals(other.specialReview)) {
            return false;
        }
        return true;
    }

}