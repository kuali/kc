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
package org.kuali.kra.subaward.subawardrule.events;

import org.kuali.kra.subaward.bo.SubAwardAttachments;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.AddSubAwardAttachmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddSubAwardAttachmentEvent extends SubAwardAttachmentEventBase {
    
    public AddSubAwardAttachmentEvent(String description, String errorPathPrefix, SubAwardDocument document, SubAwardAttachments attachment) {
        super(description, errorPathPrefix, document, attachment);
    }
    
   public AddSubAwardAttachmentEvent(String description, String errorPathPrefix, SubAwardDocument document) {
        super(description, errorPathPrefix, document);
        // TODO Auto-generated constructor stub
    }
   /**
    * @see org.kuali.rice.krad.rules.rule.event.DocumentEvent#getRuleInterfaceClass()
    */
   public Class getRuleInterfaceClass() {
       return AddSubAwardAttachmentRule.class;
   }

   /**
    * @see org.kuali.rice.krad.rules.rule.event.DocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
    */
   public boolean invokeRuleMethod(BusinessRule rule) {
       return ((AddSubAwardAttachmentRule) rule).processsAddSubawardAttachmentRule(this);
   }

}
