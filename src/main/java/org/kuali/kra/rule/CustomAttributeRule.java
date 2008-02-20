/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rule;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;

public interface CustomAttributeRule extends BusinessRule {
    
    /**
     * 
     * This method is to validate format/length/required for custom data.
     * @param saveCustomAttributeEvent
     * @return
     */
    public boolean processCustomAttributeRules(SaveCustomAttributeEvent saveCustomAttributeEvent);

}
