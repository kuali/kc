/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.permissions.impl.rule.event;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUser;
import org.kuali.coeus.common.permissions.impl.rule.PermissionsRule;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * The AddPermissionsUserEvent is generated when a user is to be added to
 * a document via the Permissions tab web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AddPermissionsUserEvent extends KcDocumentEventBase {
    
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
    
    @Override
    public void validate() {
        super.validate();
        if (this.newUser == null) {
            throw new IllegalArgumentException("invalid (null) permissions user");
        }
    }
    
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

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processAddPermissionsUserBusinessRules(getDocument(), 
                                                                                users, newUser);
    }
}
