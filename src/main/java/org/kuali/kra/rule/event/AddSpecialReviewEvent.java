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

import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.AddSpecialReviewRule;
import org.kuali.rice.kns.document.Document;

/**
 * This class represents the event for adding special review.
 * @param <T> The subclass of AbstractSpecialReview
 */
public class AddSpecialReviewEvent<T extends AbstractSpecialReview<? extends AbstractSpecialReviewExemption>> extends KraDocumentEventBaseExtension {
    
    private T specialReview;

    /**
     * Constructs an AddProposalSpecialReviewEvent with the given errorPathPrefix, document, and proposalSpecialReview.
     * 
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSpecialReview
     */
    public AddSpecialReviewEvent(String errorPathPrefix, Document document, T specialReview) {
        super("adding special review to document " + getDocumentId(document), errorPathPrefix, document);
        this.specialReview = specialReview;
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
    
    @Override
    public BusinessRuleInterface<AddSpecialReviewEvent<T>> getRule() {
        return new AddSpecialReviewRule<T>(getErrorPathPrefix());
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