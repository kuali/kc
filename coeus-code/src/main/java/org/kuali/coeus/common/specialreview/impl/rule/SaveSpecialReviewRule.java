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
package org.kuali.coeus.common.specialreview.impl.rule;

import org.kuali.coeus.common.specialreview.impl.bo.SpecialReview;
import org.kuali.coeus.common.specialreview.impl.bo.SpecialReviewExemption;
import org.kuali.coeus.common.specialreview.impl.rule.event.SaveSpecialReviewEvent;
import org.kuali.coeus.common.specialreview.impl.rules.SpecialReviewRuleBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;

/**
 * Runs the rule processing for saving the Special Reviews.
 * @param <T> The subclass of SpecialReview
 */
public class SaveSpecialReviewRule<T extends SpecialReview<? extends SpecialReviewExemption>> extends SpecialReviewRuleBase<T> 
    implements KcBusinessRule<SaveSpecialReviewEvent<T>> {

    @Override
    public boolean processRules(SaveSpecialReviewEvent<T> event) {
        return processSaveSpecialReviewEvent(event);
    }

}
