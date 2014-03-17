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
package org.kuali.kra.subaward.subawardrule;

import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardContact;


public interface SubAwardContactRule extends
org.kuali.rice.krad.rules.rule.BusinessRule {
	/**.
	 *
	 * This method is for @param subAwardContact rule validation
	 * @param subAwardContact
	 * @param subAward
	 * @return boolean value
	 */
    public boolean processAddSubAwardContactBusinessRules(
    SubAwardContact subAwardContact, SubAward subAward);
}
