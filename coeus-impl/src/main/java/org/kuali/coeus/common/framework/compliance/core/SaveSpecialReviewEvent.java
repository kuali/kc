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
package org.kuali.coeus.common.framework.compliance.core;

import org.apache.commons.collections4.ListUtils;
import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Represents the event for saving the Special Reviews.
 * @param <T> The subclass of Special Review
 */
public class SaveSpecialReviewEvent<T extends SpecialReview<? extends SpecialReviewExemption>> extends KcDocumentEventBaseExtension {
    
    private String arrayErrorPathPrefix;
    
    private List<T> specialReviews;
    
    private boolean validateIrbProtocol;
    private boolean validateIacucProtocol;
    
    /**
     * Constructs a SaveSpecialReviewEvent.
     *
     */
    public SaveSpecialReviewEvent(String arrayErrorPathPrefix, Document document, List<T> specialReviews, boolean validateIrbProtocol, boolean validateIacucProtocol) {
        super("saving special review to document " + getDocumentId(document), Constants.EMPTY_STRING, document);
        this.arrayErrorPathPrefix = arrayErrorPathPrefix;
        this.specialReviews = specialReviews;
        this.validateIrbProtocol = validateIrbProtocol;
        this.validateIacucProtocol = validateIacucProtocol;
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

    public boolean getValidateIrbProtocol() {
        return validateIrbProtocol;
    }

    public void setValidateIrbProtocol(boolean validateIrbProtocol) {
        this.validateIrbProtocol = validateIrbProtocol;
    }
    public boolean getValidateIacucProtocol() {
        return validateIacucProtocol;
    }

    public void setValidateIacucProtocol(boolean validateIacucProtocol) {
        this.validateIacucProtocol = validateIacucProtocol;
    }

    @Override
    public KcBusinessRule<SaveSpecialReviewEvent<T>> getRule() {
        return new SaveSpecialReviewRule<T>();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((specialReviews == null) ? 0 : specialReviews.hashCode());
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