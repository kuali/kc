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
package org.kuali.kra.award.rule.event;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.rule.AddAwardAttachmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class AwardAttachmentEventBase extends KcDocumentEventBase implements AwardAttachmentEvent  {

    private AwardAttachment attachment;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(AwardAttachmentEventBase.class);
    
    protected AwardAttachmentEventBase(String description, String errorPathPrefix, AwardDocument document, AwardAttachment attachment) {
        super(description, errorPathPrefix, document);
        this.attachment = attachment;
        logEvent();
    }
    
    protected AwardAttachmentEventBase( String description, String errorPathPrefix, AwardDocument document ) {
        super( description, errorPathPrefix, document );
        logEvent();
    }

    @Override
    protected void logEvent() {
        if( LOG.isDebugEnabled() )
            LOG.debug(getDescription());
    }

    public AwardAttachment getAwardAttachment() {
       return attachment;
    }

    public void setAwardAttachment(AwardAttachment attachment) {
        this.attachment = attachment;
    }

    
    public Class getRuleInterfaceClass() {
       return AddAwardAttachmentRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return false;
    }
    
}
