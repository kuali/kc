/*
 * Copyright 2006-2009 The Kuali Foundation Licensed under the Educational
 * Community License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.opensource.org/licenses/ecl1.php Unless required by applicable
 * law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.kra.budget.rule;

import org.kuali.kra.bo.AbstractInstituteRate;

/**
* This rule checks that an institute rate type & rate class are valid for the specific maintenance document.
* <p>
* For example: if dealing with a LA rate maintenance document then that document can only contain an
* <bold>LA</bold> rate type & <bold>LA</bold> rate class.
* </p>
*/
public interface InstituteRateRateTypeRateClassRule {

    /**
     * Validates if the rate type code and rate type class are valid for the AbstractInstituteRate rate type.
     * @param rate the AbstractInstituteRate to check
     * @return true is valid
     * @throws NullPointerException if rate is null
     */
    public abstract boolean validateRateTypeAndRateClass(final AbstractInstituteRate rate);

}
