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

import org.apache.commons.collections.ListUtils;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.SaveSpecialReviewRule;
import org.kuali.rice.kns.document.Document;

/**
 * Represents the event for saving a special review.
 */
public class SaveSpecialReviewEvent<T extends AbstractSpecialReview<? extends AbstractSpecialReviewExemption>> extends KraDocumentEventBaseExtension {
    
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
    }

    public List<T> getSpecialReviews() {
        return specialReviews;
    }

    public void setSpecialReviews(List<T> specialReviews) {
        this.specialReviews = specialReviews;
    }
    
    @Override
    public BusinessRuleInterface<SaveSpecialReviewEvent<T>> getRule() {
        return new SaveSpecialReviewRule<T>(getErrorPathPrefix());
    }
    
    /**
     * {@inheritDoc}
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((specialReviews == null) ? 0 : specialReviews.hashCode());
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
        SaveSpecialReviewEvent<T> other = (SaveSpecialReviewEvent<T>) obj;
        if (specialReviews == null) {
            if (other.specialReviews != null) {
                return false;
            }
        } else if (!ListUtils.isEqualList(specialReviews, other.specialReviews)) {
            return false;
        }
        return true;
    }
    
}