/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
