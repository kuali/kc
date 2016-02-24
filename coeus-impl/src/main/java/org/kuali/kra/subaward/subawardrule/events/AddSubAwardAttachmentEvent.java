/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
