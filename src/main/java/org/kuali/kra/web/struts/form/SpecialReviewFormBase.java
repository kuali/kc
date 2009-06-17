/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web.struts.form;

import java.util.List;

import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * This interface is a base class for SpecialReviewForm. 
 * KualiForm which implements special review should implement this interface.
 * It handles all required operations to maintain new SpecialReview BO
 */
public interface SpecialReviewFormBase<E extends AbstractSpecialReviewExemption> {
    /**
     * 
     * Get the new SpcialReview BO
     * @return SpecialReview
     */
    public AbstractSpecialReview<E> getNewSpecialReview();
    /**
     * 
     * Get the list of new SpecialReviewExemptions
     * @return
     */
    public List<E> getNewSpecialReviewExemptions();
    /**
     * 
     * Get the Document associated with the form
     * @return ResearchDocument
     */
    public ResearchDocumentBase getResearchDocument();
    
    /**
     * 
     * Get the list of ExemptionTypeCodes
     * @return
     */
    public String[] getNewExemptionTypeCodes();
}
