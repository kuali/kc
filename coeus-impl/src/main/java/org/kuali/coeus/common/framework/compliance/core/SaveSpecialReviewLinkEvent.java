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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.document.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the event for saving the Special Review links to the Protocol Funding Sources.
 * @param <T> The subclass of Special Review
 */
public class SaveSpecialReviewLinkEvent<T extends SpecialReview<? extends SpecialReviewExemption>> extends KcDocumentEventBaseExtension {
    
    private List<T> specialReviews;
    
    private List<String> linkedProtocolNumbers;
    
    /**
     * Constructs a SaveSpecialReviewLinkEvent.
     * @param document
     * @param specialReviews
     */
    public SaveSpecialReviewLinkEvent(Document document, List<T> specialReviews) {
        this(document, specialReviews, new ArrayList<String>());
    }
    
    /**
     * Constructs a SaveSpecialReviewLinkEvent.
     * @param document
     * @param specialReviews
     * @param linkedProtocolNumbers
     */
    public SaveSpecialReviewLinkEvent(Document document, List<T> specialReviews, List<String> linkedProtocolNumbers) {
        super("saving link of protocol funding source for special review to document " + getDocumentId(document), Constants.EMPTY_STRING, document);
        this.specialReviews = specialReviews;
        this.linkedProtocolNumbers = linkedProtocolNumbers;
    }

    public List<T> getSpecialReviews() {
        return specialReviews;
    }

    public void setSpecialReviews(List<T> specialReviews) {
        this.specialReviews = specialReviews;
    }
    
    public List<String> getLinkedProtocolNumbers() {
        return linkedProtocolNumbers;
    }

    public void setLinkedProtocolNumbers(List<String> linkedProtocolNumbers) {
        this.linkedProtocolNumbers = linkedProtocolNumbers;
    }

    @Override
    public KcBusinessRule<SaveSpecialReviewLinkEvent<T>> getRule() {
        return new SaveSpecialReviewLinkRule<T>();
    }
    
}
