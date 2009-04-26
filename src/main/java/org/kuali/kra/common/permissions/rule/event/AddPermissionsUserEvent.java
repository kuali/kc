/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.permissions.rule.event;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * The AddPermissionsUserEvent is generated when a user is to be added to
 * a document via the Permissions tab web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AddPermissionsUserEvent extends KraDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddPermissionsUserEvent.class);
    
    private PermissionsUser newUser;
    private List<User> users;
    
    
    /**
     * Constructs a AddPermissionsUserEvent.
     * @param document the document
     * @param users the current list of users who have roles in the document
     * @param newUser the new user along with a role to add to the document
     */
    public AddPermissionsUserEvent(Document document, List<User> users, PermissionsUser newUser) {
        super("adding user and role to document " + getDocumentId(document), "", document);
        
        this.users = users;
        this.newUser = newUser;
    
        logEvent();
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEventBase#validate()
     */
    public void validate() {
        super.validate();
        if (this.newUser == null) {
            throw new IllegalArgumentException("invalid (null) permissions user");
        }
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.newUser == null) {
            logMessage.append("null newUser");
        }
        else {
            logMessage.append(this.newUser.toString());
        }

        LOG.debug(logMessage);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processAddPermissionsUserBusinessRules(getDocument(), 
                                                                                users, newUser);
    }
}
