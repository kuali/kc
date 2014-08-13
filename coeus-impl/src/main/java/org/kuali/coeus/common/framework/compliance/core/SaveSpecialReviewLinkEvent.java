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