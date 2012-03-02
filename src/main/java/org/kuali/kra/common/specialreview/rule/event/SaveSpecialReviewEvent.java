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

import org.apache.commons.collections.ListUtils;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.rule.SaveSpecialReviewRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * Represents the event for saving the Special Reviews.
 * @param <T> The subclass of Special Review
 */
public class SaveSpecialReviewEvent<T extends SpecialReview<? extends SpecialReviewExemption>> extends KraDocumentEventBaseExtension {
    
    private String arrayErrorPathPrefix;
    
    private List<T> specialReviews;
    
    private boolean validateProtocol;
    
    /**
     * Constructs a SaveSpecialReviewEvent.
     * 
     * @param errorPathPrefix
     * @param document
     * @param specialReview
     */
    public SaveSpecialReviewEvent(String arrayErrorPathPrefix, Document document, List<T> specialReviews, boolean validateProtocol) {
        super("saving special review to document " + getDocumentId(document), Constants.EMPTY_STRING, document);
        this.arrayErrorPathPrefix = arrayErrorPathPrefix;
        this.specialReviews = specialReviews;
        this.validateProtocol = validateProtocol;
    }

    public String getArrayErrorPathPrefix() {
        return arrayErrorPathPrefix;
    }

    public void setArrayErrorPathPrefix(String arrayErrorPathPrefix) {
        this.arrayErrorPathPrefix = arrayErrorPathPrefix;
    }

    public List<T> getSpecialReviews() {
        return specialReviews;
    }

    public void setSpecialReviews(List<T> specialReviews) {
        this.specialReviews = specialReviews;
    }

    public boolean getValidateProtocol() {
        return validateProtocol;
    }

    public void setValidateProtocol(boolean validateProtocol) {
        this.validateProtocol = validateProtocol;
    }

    @Override
    public BusinessRuleInterface<SaveSpecialReviewEvent<T>> getRule() {
        return new SaveSpecialReviewRule<T>();
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
        SaveSpecialReviewEvent<?> other = (SaveSpecialReviewEvent<?>) obj;
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