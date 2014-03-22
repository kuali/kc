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



import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.subaward.subawardrule.AddSubAwardAttachmentRule;
import org.kuali.kra.subaward.subawardrule.events.SubAwardAttachmentEvent;
import org.kuali.kra.subaward.bo.SubAwardAttachments;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class SubAwardAttachmentEventBase extends KcDocumentEventBase implements SubAwardAttachmentEvent {
    
    private SubAwardAttachments attachment;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(SubAwardAttachmentEventBase.class);
    
    protected SubAwardAttachmentEventBase(String description, String errorPathPrefix, SubAwardDocument document, SubAwardAttachments attachment) {
        super(description, errorPathPrefix, document);
        this.attachment = attachment;
        logEvent();
    }
    
    protected SubAwardAttachmentEventBase( String description, String errorPathPrefix, SubAwardDocument document ) {
        super( description, errorPathPrefix, document );
        logEvent();
    }

    @Override
    protected void logEvent() {
        if( LOG.isDebugEnabled() )
            LOG.debug(getDescription());
    }

    public SubAwardAttachments getSubAwardAttachments() {
       return attachment;
    }

    public void setSubAwardAttachments(SubAwardAttachments attachment) {
        this.attachment = attachment;
    }

    
    public Class getRuleInterfaceClass() {
       return AddSubAwardAttachmentRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return false;
    }

}
