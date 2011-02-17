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
package org.kuali.kra.common.specialreview.rule;

import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.rules.SpecialReviewRuleBase;
import org.kuali.kra.rule.BusinessRuleInterface;

/**
 * Runs the rule processing for adding a Special Review.
 * @param <T> The subclass of Special Review
 */
public class AddSpecialReviewRule<T extends SpecialReview<? extends SpecialReviewExemption>> extends SpecialReviewRuleBase<T> 
    implements BusinessRuleInterface<AddSpecialReviewEvent<T>> {

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(AddSpecialReviewEvent<T> event) {
        return processAddSpecialReviewEvent(event);
    }

}
