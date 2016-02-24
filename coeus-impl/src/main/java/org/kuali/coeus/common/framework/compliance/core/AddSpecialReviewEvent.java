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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Represents the event for adding a Special Review.
 * @param <T> The subclass of SpecialReview
 */
public class AddSpecialReviewEvent<T extends SpecialReview<? extends SpecialReviewExemption>> extends KcDocumentEventBaseExtension {
    
    private static final String NEW_SPECIAL_REVIEW_FIELD = "specialReviewHelper.newSpecialReview";
    
    private T specialReview;
    
    private List<T> specialReviews;
    
    private boolean isIrbProtocolLinkingEnabled;
    
    /**
     * Constructs an AddProposalSpecialReviewEvent.
     * 
     * @param document The document containing the Special Review
     * @param specialReview The Special Review object to validate
     * @param specialReviews The existing Special Review objects
     * @param isIrbProtocolLinkingEnabled Whether or not Protocol linking is enabled
     */
    public AddSpecialReviewEvent(Document document, T specialReview, List<T> specialReviews, boolean isIrbProtocolLinkingEnabled) {
        super("adding special review to document " + getDocumentId(document), NEW_SPECIAL_REVIEW_FIELD, document);
        this.specialReview = specialReview;
        this.specialReviews = specialReviews;
        this.isIrbProtocolLinkingEnabled = isIrbProtocolLinkingEnabled;
    }

    public AddSpecialReviewEvent(Document document, T specialReview, List<T> specialReviews, boolean isIrbProtocolLinkingEnabled, String errorPathPrefix) {
        super("adding special review to document " + getDocumentId(document), errorPathPrefix, document);
        this.specialReview = specialReview;
        this.specialReviews = specialReviews;
        this.isIrbProtocolLinkingEnabled = isIrbProtocolLinkingEnabled;
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

    public boolean getIsIrbProtocolLinkingEnabled() {
        return isIrbProtocolLinkingEnabled;
    }

    public void setIsIrbProtocolLinkingEnabled(boolean isIrbProtocolLinkingEnabled) {
        this.isIrbProtocolLinkingEnabled = isIrbProtocolLinkingEnabled;
    }

    @Override
    public KcBusinessRule<AddSpecialReviewEvent<T>> getRule() {
        return new AddSpecialReviewRule<T>();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((specialReview == null) ? 0 : specialReview.hashCode());
        return result;
    }

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
