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
