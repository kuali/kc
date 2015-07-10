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
package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.coeus.common.budget.framework.rate.AbstractInstituteRate;

/**
* This rule checks that an institute rate type &amp; rate class are valid for the specific maintenance document.
* <p>
* For example: if dealing with a LA rate maintenance document then that document can only contain an
* <strong>LA</strong> rate type &amp; <strong>LA</strong> rate class.
* </p>
*/
public interface InstituteRateRateTypeRateClassRule extends org.kuali.rice.krad.rules.rule.BusinessRule {

    /**
     * Validates if the rate type code and rate type class are valid for the AbstractInstituteRate rate type.
     * @param rate the AbstractInstituteRate to check
     * @return true is valid
     * @throws NullPointerException if rate is null
     */
    public abstract boolean validateRateTypeAndRateClass(final AbstractInstituteRate rate);

}
