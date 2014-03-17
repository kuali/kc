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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.unit.Unit;

public interface UnitLookupDao {
    
    /**
     * This method finds the unit object whose number matches the argument <code>unitNumber</code>. The lookup logic is 
     * case insensitive i.e. the return value is the same irrespective of the case of the characters in the argument. 
     * @param unitNumber
     * @return the matching unit object or null if no match was found.
     */
    public Unit findUnitbyNumberCaseInsensitive(String unitNumber);

}
