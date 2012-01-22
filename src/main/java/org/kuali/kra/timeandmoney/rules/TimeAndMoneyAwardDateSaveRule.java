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
package org.kuali.kra.timeandmoney.rules;

import org.kuali.kra.timeandmoney.rule.event.TimeAndMoneyAwardDateSaveEvent;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class...
 */
public interface TimeAndMoneyAwardDateSaveRule extends BusinessRule {

    /**
     * This method processes must be implemented and enforces all add method business rules.
     * @param timeAndMoneyAwardAmountTransactionSaveEvent
     * @return
     */
    boolean processSaveAwardDatesBusinessRules(TimeAndMoneyAwardDateSaveEvent 
            timeAndMoneyAwardDateSaveEvent);
}
