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
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is for rule validation for subAward
 * AmountInfo section...
 */
public interface SubAwardAmountInfoRule extends BusinessRule {

/**.
	 * This method is for rule validation while
	 *  adding subAwardamount info details
	 *@param subAwardAmountInfo the subAwardAmountInfo
	 * @param subAward The subAward
	 * @return boolean value
	 */
	public boolean processAddSubAwardAmountInfoBusinessRules(
    SubAwardAmountInfo subAwardAmountInfo, SubAward subAward);
}
